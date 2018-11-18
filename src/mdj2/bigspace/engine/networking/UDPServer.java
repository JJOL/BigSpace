package mdj2.bigspace.engine.networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer implements Runnable {

	private DatagramSocket socket;
	private Thread serverThread;
	private boolean running;
	private int port;
	
	public UDPServer(int port) {
		this.port = port;
		try {
			socket = new DatagramSocket(port);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		running = false;
	}
	
	public void startServer() {
		if (!running && serverThread == null) {
			running = true;
			serverThread = new Thread(this);
			serverThread.setName("Socket Server");
			serverThread.start();
			System.out.println("Server listening on port..." + port);
		}
	}

	@Override
	public void run() {
		while (running) {
			DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
			try {
				socket.receive(packet);
				process(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void process(DatagramPacket packet) {
		byte data[] = packet.getData();
		int size = packet.getLength();
		byte msgData[] = new byte[size];
		for (int i = 0; i < size; i++) {
			msgData[i] = data[i];
		}
		
		String msg = new String(msgData);
		System.out.println("[Server] Incoming from port "+packet.getPort()+": " + msg);
	}
	
	public void send(String msg) {
		send(msg.getBytes());
	}
	
	public void send(byte msgData[]) {
		byte data[] = new byte[1024];
		for (int i = 0; i < msgData.length; i++) {
			data[i] = msgData[i];
		}
		
		
	}
}
