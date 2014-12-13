package pl.lodz.wspolbiezne.lab07;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public Server(int portNumber) {

		try {
			ServerSocket serverSocket = new ServerSocket(portNumber);
			Socket clientSocket = serverSocket.accept();

			processInput(clientSocket);
			serverSocket.close();

		} catch (IOException e) {
			System.out
					.println("Exception caught when trying to listen on port "
							+ portNumber + " or listening for a connection");
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.println("èle skastowany typ int[][][] / double[][][]");
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
			if (ois.equals("bye")) { // albo kiedy ca≥a macierz zosta≥a
										// wype≥niona
				ois.close();
				oos.close();
				kkSocket.close();
				break;
			}
			// Initiate conversation with client
			// Obliczenia obliczenia = new Obliczenia();
			// outputLine = obliczenia.processInput(null, null);
			// out.println(outputLine);

			// while ((inputLine = in.readLine()) != null) {
			// outputLine = obliczenia.processInput(inputLine);
			// out.println(obliczenia.tooutputLine);
			// }

			System.out.println(macierze.getColumns());
			System.out.println(macierze.getRows());
			oos.writeObject("bye"); // daj znak koÒca
		}
	}
	public static void main(String[] args) {
		new Server(4444);
	}
}
