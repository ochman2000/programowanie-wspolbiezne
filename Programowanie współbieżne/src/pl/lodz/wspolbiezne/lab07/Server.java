package pl.lodz.wspolbiezne.lab07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import pl.lodz.wspolbiezne.lab06.KnockKnockProtocol;

public class Server {

	public Server(int portNumber) {
		try (ServerSocket serverSocket = new ServerSocket(portNumber);
				Socket clientSocket = serverSocket.accept();
				PrintWriter out = new PrintWriter(
						clientSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(
						clientSocket.getInputStream()));) {

			String inputLine;
			int[] outputLine;

			// Initiate conversation with client
			Obliczenia obliczenia = new Obliczenia();
//			outputLine = obliczenia.processInput(null, null);
//			out.println(outputLine);

			while ((inputLine = in.readLine()) != null) {
//				outputLine = obliczenia.processInput(inputLine);
//				out.println(obliczenia.tooutputLine);

			}
		} catch (IOException e) {
			System.out
					.println("Exception caught when trying to listen on port "
							+ portNumber + " or listening for a connection");
			System.out.println(e.getMessage());
		}
	}
}
