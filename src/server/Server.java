package server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {
	private DatagramSocket serverSocket;

	public Server(int port) throws Exception {
		this.serverSocket = new DatagramSocket(port);
	}
	
	public void run() throws Exception {
		DatagramPacket packet;
		InetAddress IPAddress;
		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];
		int port;
		while (true) {
			packet = new DatagramPacket(receiveData, receiveData.length);
			serverSocket.receive(packet);
			System.out.println(new String(packet.getData()));
			IPAddress = packet.getAddress();
			port = packet.getPort();
			sendData = "ACK".getBytes();
			packet = new DatagramPacket(sendData, sendData.length, IPAddress, port);
			serverSocket.send(packet);
		}
	}

	public static void main(String[] args) throws Exception {
		new Server(8080).run();
	}
}
