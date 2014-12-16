package pl.lodz.wspolbiezne.lab07;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Logger;

public class Client {
	
	private Logger logger;
	private final int LICZBA_PROCESOR”W = 4;
	private int N;
	int[][] A={ { 1, 2, 3, 4 },
				{ 5, 6, 7, 8 },
				{ 9, 10, 11, 12 },
				{ 13, 14, 15, 16 } };

	int[][] B={ { 1, 2, 3, 4 }, 
				{ 5, 6, 7, 8 }, 
				{ 9, 10, 11, 12 },
				{ 13, 14, 15, 16 } };

	public Client(String hostName, int portNumber) {
		logger = Obliczenia.getCustomLogger();
		logger.info("Connecting to server at port: "+portNumber+" ...");
		N = A.length;
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
			System.err.println("èle skastowany typ int[][][] / double[][][]");
			System.exit(1);
		}
	}
	
	private void dispatch(Socket kkSocket) throws IOException, ClassNotFoundException {
		OutputStream outputStream = kkSocket.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(outputStream); 
		Obliczenia obliczenia = new Obliczenia(A, B);
		
		for (int proces = 0; proces < LICZBA_PROCESOR”W; proces++) {
			int start = getBeginningOfInterval(proces, LICZBA_PROCESOR”W);
			int end = getEndOfInterval(proces, LICZBA_PROCESOR”W);
			MacierzeDto C = obliczenia.getBlock(start, end);
			Logger.getGlobal().info("Wysy≥ka bloku nr: "+proces);
			oos.writeObject(C);
			logger.info("Dispatched process no. "+proces);
		}
		
		//tutaj dodaj wyniki, ktÛre przyjdπ z powrotem z serwera.
		// PamiÍtaj, øeby przy zliczaniu wziπÊ pod uwagÍ indeksy kolumn.
		InputStream inputStream = kkSocket.getInputStream();
		ObjectInputStream ois = new ObjectInputStream(inputStream);
		MacierzeDto macierze;
		while ((macierze = (MacierzeDto) ois.readObject()) != null) {
			if (ois.equals("bye")) { // albo kiedy ca≥a macierz zosta≥a
										// wype≥niona
				ois.close();
				oos.close();
				kkSocket.close();
				break;
			}
		}
	}

	public int getBeginningOfInterval(int interval, int totalIntervals) {
		if (totalIntervals <= interval) {
			throw new IllegalArgumentException(
					"Przedzia≥ nie moøe byÊ wiÍkszy niø: " + totalIntervals
							+ " a podano: " + interval);
		}
		double fraction = (double) interval / (double) totalIntervals;
		return (int) (fraction * N);
	}
	
	public int getEndOfInterval(int interval, int totalIntervals) {
		if (totalIntervals <= interval) {
			throw new IllegalArgumentException(
					"Przedzia≥ nie moøe byÊ wiÍkszy niø: " + totalIntervals
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
