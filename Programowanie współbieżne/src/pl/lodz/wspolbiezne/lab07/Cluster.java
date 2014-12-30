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
	
	public static void main(String[] args) {
		new Cluster(4444);
	}

	public Cluster(int portNumber) {
		logger = Obliczenia.getCustomLogger();
		logger.info("Server started at 127.0.0.1:" + portNumber);
		try {
			ServerSocket serverSocket = new ServerSocket(portNumber);
			Socket clientSocket = serverSocket.accept();

			processInput(clientSocket);
			serverSocket.close();
			logger.info("Connection closed");

		} catch (IOException e) {
			logger.severe("Exception caught when trying to listen on port "
					+ portNumber + " or listening for a connection");
			logger.severe(e.getMessage());
			System.exit(1);
		} catch (ClassNotFoundException e) {
			logger.severe("le skastowany typ int[][][] / double[][][]");
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
				outputStream, kkSocket.getSendBufferSize());
		ObjectOutputStream oos = new ObjectOutputStream(bufferedOut);

		InputStream inputStream = kkSocket.getInputStream();
		BufferedInputStream bufferedIn = new BufferedInputStream(inputStream,
				kkSocket.getReceiveBufferSize());
		ObjectInputStream ois = new ObjectInputStream(bufferedIn);

		if (inputStream.markSupported()) {
			logger.info("mark supported");
		} else {
			logger.severe("mark not supported");
		}

		MacierzeDto macierze;
		long uptime = System.currentTimeMillis();
		while (true) {
			long startTime2 = System.currentTimeMillis();
			if (inputStream.available() != 0) {
				uptime = System.currentTimeMillis();
				if ((macierze = (MacierzeDto) ois.readUnshared()) != null) {
					long size2 = Obliczenia.sizeOf(macierze);
					logger.info("Przyjêto macierz do obliczenia");
					logger.info("Trwa odbieranie "
							+ Obliczenia.humanReadableByteCount(size2, false));
					long duration2 = System.currentTimeMillis() - startTime2;
					long speed2 = (long) (size2 / (duration2 / 1000d));
					logger.info("Read speed: " + size2 + " bytes in "
							+ duration2 + "ms");
					logger.info("Read speed: "
							+ Obliczenia.humanReadableByteCount(speed2, false)
							+ "/s");
					long liczenieDuration = System.currentTimeMillis();
					Obliczenia obliczenia = new Obliczenia();
					ResultDto result = obliczenia.processInput(macierze);
					logger.info("Czas mno¿enia macierzy: "
							+ (System.currentTimeMillis() - liczenieDuration)
							+ "ms");
					long startTime = System.currentTimeMillis();
					int sizeOfResult = Obliczenia.sizeOf(result);
					logger.info("Trwa wysy³anie obliczeñ ("
							+ sizeOfResult
							+ " bytes)"
							+ " ("
							+ Obliczenia.humanReadableByteCount(sizeOfResult,
									false) + ")");
					oos.writeObject(result);
					oos.flush();
					long duration = System.currentTimeMillis() - startTime;
					long speed = (long) ((long) sizeOfResult / (duration / 1000d));
					logger.info("Write speed: " + sizeOfResult + " bytes in "
							+ duration + "ms");
					logger.info("Write speed: "
							+ Obliczenia.humanReadableByteCount(speed, false)
							+ "/s");
				}
			}
			int timeout = 20_000;
			if (System.currentTimeMillis() - uptime > timeout) {
				ois.close();
				oos.close();
				kkSocket.close();
				logger.info("Nast¹pi³ timeout: " + timeout / 1000 + "s");
				break;
			}
		}
	}
}
