package pl.lodz.wspolbiezne.lab07;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.logging.Logger;

public class Client {
	
	private final int LICZBA_PROCESORÓW = 8;
	private final int N = 64;
	private Logger logger;
	private long start;
	
	//NIESTETY JAVA NIE JEST TAKA SPRYTNA I MUSZÊ POWTÓRZYÆ TO TRZY RAZY,
	//ABY KOMPILATOR PRZYPISA£ INNE ADRESY
	double[][] A = new double[N][N];
	double[][] B = new double[N][N];
	double[][] C = new double[N][N];

	public Client(String hostName, int portNumber) {
		start = System.currentTimeMillis();
		logger = Obliczenia.getCustomLogger();
		logger.info("Connecting to server at port: "+portNumber+" ...");
		for (int i=0; i<N; i++) {
			A[i] = new Random().doubles(N).toArray();
			B[i] = new Random().doubles(N).toArray();
			C[i] = new Random().doubles(N).toArray();
		}
		try {
			Socket kkSocket = new Socket(hostName, portNumber);
			dispatch(kkSocket);
			logger.info("Zakoñczono obliczanie.");
			System.out.println("Ca³kowity czas wykonania: "+
					(int)((System.currentTimeMillis()-start)/1000)+" sekund.");
		} catch (UnknownHostException e) {
			logger.severe("Don't know about host " + hostName);
			System.exit(1);
		} catch (IOException e) {
			logger.severe("Couldn't get I/O for the connection to "
					+ hostName);
			System.exit(1);
		} catch (ClassNotFoundException e) {
			logger.severe("le skastowany typ int[][][] / double[][][]");
			System.exit(1);
		}
	}
	
	@SuppressWarnings("unused")
	private void dispatch(Socket kkSocket) throws IOException, ClassNotFoundException {
		OutputStream outputStream = kkSocket.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(outputStream); 
		InputStream inputStream = kkSocket.getInputStream();
		ObjectInputStream ois = new ObjectInputStream(inputStream);
		
		logger.info("Trwa mno¿enie macierzy AxB");
		Obliczenia obliczenia = new Obliczenia(A, B);
		double[][] AB = multiply(oos, ois, obliczenia);
		
		logger.info("Trwa mno¿enie macierzy ABxC");
		obliczenia = new Obliczenia(AB, C);
		double[][] ABC = multiply(oos, ois, obliczenia);
		
		if (N<=8) {
			System.out.println(Obliczenia.toString(ABC));
		}
	}

	private double[][] multiply(ObjectOutputStream oos, ObjectInputStream ois,
			Obliczenia obliczenia) throws IOException, ClassNotFoundException {
		for (int proces = 0; proces < LICZBA_PROCESORÓW; proces++) {
			int start = getBeginningOfInterval(proces, LICZBA_PROCESORÓW);
			int end = getEndOfInterval(proces, LICZBA_PROCESORÓW);
			MacierzeDto C = obliczenia.getBlock(start, end);
			Logger.getGlobal().info("Trwa wysy³ka bloku nr "+proces);
			oos.writeObject(C);
			logger.info("Zakoñczono przesy³anie bloku nr "+proces+" ("
							+Obliczenia.sizeOf(C)+" bytes)");
		}
		
		//tutaj dodaj wyniki, które przyjd¹ z powrotem z serwera.
		// Pamiêtaj, ¿eby przy zliczaniu wzi¹æ pod uwagê indeksy kolumn.
		ResultDto macierze;
		double[][] AB = new double[N][N];
		int i=LICZBA_PROCESORÓW;
		while ((macierze = (ResultDto) ois.readObject()) != null) {
			obliczenia.merge(macierze, AB);
			if (--i==0) break;
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
