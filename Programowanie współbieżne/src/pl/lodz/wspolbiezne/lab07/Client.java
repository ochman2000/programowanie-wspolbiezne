package pl.lodz.wspolbiezne.lab07;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.StreamCorruptedException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.logging.Logger;

public class Client {

	private final int LICZBA_PROCESORÓW = 1;
	private final int N = 1024;
	private Logger logger;
	private long start;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	// NIESTETY JAVA NIE JEST TAKA SPRYTNA I MUSZÊ POWTÓRZYÆ TO TRZY RAZY,
	// ABY KOMPILATOR PRZYPISA£ INNE ADRESY
	private double[][] A = new double[N][N];
	private double[][] B = new double[N][N];
	private double[][] C = new double[N][N];

	public Client(String hostName, int portNumber) {
		start = System.currentTimeMillis();
		logger = Obliczenia.getCustomLogger();
		logger.info("Connecting to server at port: " + portNumber + " ...");
		for (int i = 0; i < N; i++) {
			A[i] = new Random().doubles(N).toArray();
			B[i] = new Random().doubles(N).toArray();
			C[i] = new Random().doubles(N).toArray();
		}
		try {
			Socket kkSocket = new Socket(hostName, portNumber);
			int receiveBufferSize = kkSocket.getReceiveBufferSize();
			logger.info("Rozmiar bufora: ("
					+ receiveBufferSize
					+ " bytes) ("
					+ Obliczenia.humanReadableByteCount(receiveBufferSize,
							false) + ")");
			dispatch(kkSocket);
			logger.info("Zakoñczono obliczanie.");
			System.out.println("Ca³kowity czas wykonania: "
					+ (int) ((System.currentTimeMillis() - start) / 1000)
					+ " sekund.");
		} catch (UnknownHostException e) {
			logger.severe("Don't know about host " + hostName);
			System.exit(1);
		} catch (StreamCorruptedException e) {
			logger.severe("This constructor will block until the corresponding"
					+ " ObjectOutputStream has written and flushed the header.");
			logger.severe(e.getMessage());
			System.exit(1);
		} catch (IOException e) {
			logger.severe("Couldn't get I/O for the connection to " + hostName);
			logger.severe(e.getMessage());
			System.exit(1);
		} catch (ClassNotFoundException e) {
			logger.severe("le skastowany typ int[][][] / double[][][]");
			System.exit(1);
		}
	}

	@SuppressWarnings("unused")
	private void dispatch(Socket kkSocket) throws IOException,
			ClassNotFoundException {

		InputStream inputStream = kkSocket.getInputStream();
		BufferedInputStream bufferedIn = new BufferedInputStream(inputStream);

		OutputStream outputStream = kkSocket.getOutputStream();
		BufferedOutputStream bufferedOut = new BufferedOutputStream(
				outputStream);

		logger.info("Trwa mno¿enie macierzy AxB");
		Obliczenia obliczenia = new Obliczenia(A, B);
		double[][] AB = multiply(bufferedIn, bufferedOut, obliczenia);

		logger.info("Trwa mno¿enie macierzy ABxC");
		obliczenia = new Obliczenia(AB, C);
		double[][] ABC = multiply(bufferedIn, bufferedOut, obliczenia);

		ois.close();
		oos.close();
		kkSocket.close();

		if (N <= 8) {
			System.out.println(Obliczenia.toString(ABC));
		}
	}

	private double[][] multiply(BufferedInputStream bis,
			BufferedOutputStream bos, Obliczenia obliczenia)
			throws IOException, ClassNotFoundException {

		if (oos == null) {
			oos = new ObjectOutputStream(bos);
		}
		// oos.flush();

		for (int proces = 0; proces < LICZBA_PROCESORÓW; proces++) {
			int start = getBeginningOfInterval(proces, LICZBA_PROCESORÓW);
			int end = getEndOfInterval(proces, LICZBA_PROCESORÓW);
			MacierzeDto C = obliczenia.getBlock(start, end);
			Logger.getGlobal().info("Trwa wysy³ka bloku nr " + proces);
			long startTime = System.currentTimeMillis();

			oos.writeObject(C);
			oos.flush();

			int sizeOfC = Obliczenia.sizeOf(C);
			long duration = System.currentTimeMillis() - startTime;
			logger.info("Zakoñczono przesy³anie bloku nr " + proces + " ("
					+ Obliczenia.humanReadableByteCount(sizeOfC, false) + ")");
			long speed = (long) (sizeOfC / (duration / 1000d));
			logger.info("Write speed: " + sizeOfC + " bytes in " + duration
					+ "ms");
			logger.info("Write speed: "
					+ Obliczenia.humanReadableByteCount(speed, false) + "/s");
		}

		if (ois == null) {
			ois = new ObjectInputStream(bis);
		}

		ResultDto macierze;
		double[][] AB = new double[N][N];
		int i = LICZBA_PROCESORÓW;
		int size;
		while (true) {
			if (bis.available() != 0) {
				logger.info("Stream available");
				long startTime = System.currentTimeMillis();
				if ((macierze = (ResultDto) ois.readUnshared()) != null) {
					size = Obliczenia.sizeOf(macierze);
					logger.info("Trwa odbieranie "
							+ Obliczenia.humanReadableByteCount(size, false));
					long duration = System.currentTimeMillis() - startTime;
					long speed = (long) (size / (duration / 1000d));
					logger.info("Read speed: " + size + " bytes in " + duration
							+ "ms");
					logger.info("Read speed: "
							+ Obliczenia.humanReadableByteCount(speed, false)
							+ "/s");
					obliczenia.merge(macierze, AB);
					if (--i == 0)
						break;
				}
			}
		}
		return AB;

	}

	public int getBeginningOfInterval(int interval, int totalIntervals) {
		if (totalIntervals <= interval) {
			throw new IllegalArgumentException(
					"Przedzia³ nie mo¿e byæ wiêkszy ni¿: " + totalIntervals
							+ " a podano: " + interval);
		}
		double fraction = (double) interval / (double) totalIntervals;
		return (int) (fraction * N);
	}

	public int getEndOfInterval(int interval, int totalIntervals) {
		if (totalIntervals <= interval) {
			throw new IllegalArgumentException(
					"Przedzia³ nie mo¿e byæ wiêkszy ni¿: " + totalIntervals
							+ " a podano: " + interval);
		}
		double rozmiarPrzedzialu = (double) N / (double) totalIntervals;
		double fraction = (double) interval / (double) totalIntervals;
		return (int) ((fraction * N) + rozmiarPrzedzialu);
	}

	public static void main(String[] args) {
		new Client("localhost", 4444);
	}
}
