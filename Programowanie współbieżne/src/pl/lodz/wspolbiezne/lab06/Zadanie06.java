package pl.lodz.wspolbiezne.lab06;

public class Zadanie06 {
	public static void main(String[] args) {

		String hostName = "localhost";
		int portNumber = 8080;
		new KnockKnockServer(portNumber);
		new KnockKnockClient(hostName, portNumber);
	}
}
