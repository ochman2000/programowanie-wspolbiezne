package pl.lodz.wspolbiezne.lab07;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class Cluster {
	Logger logger;

	public Cluster(int portNumber) {
		logger = Obliczenia.getCustomLogger();
		logger.info("Server started at 127.0.0.1:" + portNumber);
		try {
			ServerSocket serverSocket = new ServerSocket(portNumber);
			Socket clientSocket = serverSocket.accept();

			processInput(clientSocket);
			serverSocket.close();

		} catch (IOException e) {
			logger.severe("Exception caught when trying to listen on port "
					+ portNumber + " or listening for a connection");
			logger.severe(e.getMessage());
			System.exit(1);
		} catch (ClassNotFoundException e) {
			logger.severe("èle skastowany typ int[][][] / double[][][]");
			System.exit(1);
		}
	}

	private void processInput(Socket kkSocket) throws IOException,
			ClassNotFoundException {
		int receiveBufferSize = kkSocket.getReceiveBufferSize();
		logger.info("Rozmiar bufora: (" + receiveBufferSize + " bytes) ("
				+ Obliczenia.humanReadableByteCount(receiveBufferSize, false)
				+ ")");
		OutputStream outputStream = kkSocket.getOutputStream();
		BufferedOutputStream bufferedOut = new BufferedOutputStream(
				outputStream);
		ObjectOutputStream oos = new ObjectOutputStream(bufferedOut);
		oos.flush();

		InputStream inputStream = kkSocket.getInputStream();
		BufferedInputStream bufferedIn = new BufferedInputStream(
				inputStream);
		ObjectInputStream ois = new ObjectInputStream(bufferedIn);

		System.out.println("Trwa nas≥uchiwanie...");
		MacierzeDto macierze;
		Object[] o;
		while (true) {
			if (inputStream.available() != 0) {

				if ((o = (Object[]) ois.readObject()) != null) {
					macierze = (MacierzeDto) o[0];
					logger.info("PrzyjÍto macierz do obliczenia");
					Obliczenia obliczenia = new Obliczenia();
					ResultDto result = obliczenia.processInput(macierze);
					long startTime = System.currentTimeMillis();
					int sizeOfResult = Obliczenia.sizeOf(result);
					logger.info("Trwa wysy≥anie obliczeÒ ("
							+ sizeOfResult
							+ " bytes)"
							+ " ("
							+ Obliczenia.humanReadableByteCount(sizeOfResult,
									false) + ")");
					o = new Object[] {result};
					oos.writeObject(o);
					oos.flush();
					long duration = System.currentTimeMillis() - startTime;
					long speed = (long) ((long) sizeOfResult / (duration / 1000d));
					logger.info("Write speed: "
							+ Obliczenia.humanReadableByteCount(speed, false)
							+ "/s");
				}
			}
			if (inputStream.available()==-1) {
				logger.severe("connection closed");
			}
		}
	}

	public static void main(String[] args) {
		new Cluster(4444);
	}
}
