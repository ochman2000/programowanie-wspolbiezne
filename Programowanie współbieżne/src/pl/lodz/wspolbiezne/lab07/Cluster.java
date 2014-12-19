package pl.lodz.wspolbiezne.lab07;

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
		logger.info("Server started at 127.0.0.1:"+portNumber);
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
		InputStream inputStream = kkSocket.getInputStream();
		ObjectInputStream ois = new ObjectInputStream(inputStream);
		OutputStream outputStream = kkSocket.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(outputStream);

		MacierzeDto macierze;
		while ((macierze = (MacierzeDto) ois.readObject()) != null) {
			logger.info("PrzyjÍto macierz do obliczenia");
			Obliczenia obliczenia = new Obliczenia();
			ResultDto result = obliczenia.processInput(macierze);
			oos.writeObject(result);
		}
	}
	
	public static void main(String[] args) {
		new Cluster(4444);
	}
}
