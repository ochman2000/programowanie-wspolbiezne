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
	
	private Logger logger;
	private final int LICZBA_PROCESORÓW = 4;
	private final int N = 4;
//	int[][] A={ { 1, 2, 3, 4 },
//				{ 5, 6, 7, 8 },
//				{ 9, 10, 11, 12 },
//				{ 13, 14, 15, 16 } };
//
//	int[][] B={ { 1, 2, 3, 4 }, 
//				{ 5, 6, 7, 8 }, 
//				{ 9, 10, 11, 12 },
//				{ 13, 14, 15, 16 } };
//	
//	int[][] C={ { 1, 2, 3, 4 }, 
//				{ 5, 6, 7, 8 }, 
//				{ 9, 10, 11, 12 },
//				{ 13, 14, 15, 16 } };
	
	//NIESTETY MUSZÊ POWTÓRZYÆ TO TRZY RAZY, ABY PRZYPISA£O INNE ADRESY
	double[][] A = new double[N][N];
	double[][] B = new double[N][N];
	double[][] C = new double[N][N];

	public Client(String hostName, int portNumber) {
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
			
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + hostName);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to "
					+ hostName);
			System.exit(1);
		} catch (ClassNotFoundException e) {
			System.err.println("le skastowany typ int[][][] / double[][][]");
			System.exit(1);
		}
	}
	
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
		
		logger.info("Zakoñczono mno¿enie");
		System.out.println(Obliczenia.toString(ABC));
	}

	private double[][] multiply(ObjectOutputStream oos, ObjectInputStream ois,
			Obliczenia obliczenia) throws IOException, ClassNotFoundException {
		for (int proces = 0; proces < LICZBA_PROCESORÓW; proces++) {
			int start = getBeginningOfInterval(proces, LICZBA_PROCESORÓW);
			int end = getEndOfInterval(proces, LICZBA_PROCESORÓW);
			MacierzeDto C = obliczenia.getBlock(start, end);
			Logger.getGlobal().info("Trwa wysy³ka bloku nr "+proces);
			oos.writeObject(C);
			logger.info("Zakoñczono przesy³anie bloku nr "+proces);
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
