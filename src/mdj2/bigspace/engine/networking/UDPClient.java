package mdj2.bigspace.engine.networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPClient {

	
	private DatagramSocket socket;
	private String address;
	private int port;
	
	public UDPClient (String address, int port) {
		this.address = address;
		this.port = port;
		
		try {
			socket = new DatagramSocket(25300);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean connect() {
		boolean connected;
		try {
			socket.connect(InetAddress.getByName(address), port);
			connected = true;
		} catch (UnknownHostException e) {
			connected = false;
			e.printStackTrace();
		}
		return connected;
	}
	
	public synchronized void send(String msg) {
		byte msgData[] = msg.getBytes();
		DatagramPacket packet = new DatagramPacket(msgData, msgData.length);
		try {
			socket.send(packet);
			System.out.println("Packet Sent!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
