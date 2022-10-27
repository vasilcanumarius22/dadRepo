package eu.assignment.ie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UDPClient_assignment_1 implements Runnable {
	

	public static void main(String[] args)  {
		Thread th = new Thread(new UDPClient_assignment_1());
		th.start();
	}
	
	public void UDPMessage (String ip_address, int port) throws IOException {
		// Initializing UDP parameters
		InetAddress multicast_addr = InetAddress.getByName(ip_address); // Class D IP Address -> 224.0.0.1
		MulticastSocket msocket = new MulticastSocket(port); // Port -> 2806
		byte[] buff = new byte[256];
		
		// joinGroup(InetAddress groupAddr)
		// !IMPORTANT: joinGroup now needs "Multicast Group" and "Network Interface" also
		// joinGroup(group, netIf);
		msocket.joinGroup(multicast_addr); // multicast_addr is the multicast group
		
		while(true) {
			// initialization and configure of DatagramPacket
			DatagramPacket dpacket = new DatagramPacket(buff, buff.length, multicast_addr, port);
			msocket.receive(dpacket);
			
			// mark reading
			String marksInString = new String(dpacket.getData(), 0, dpacket.getLength());
			int marksInInt = Integer.parseInt(marksInString);
			
			// creating an array to store the marks
			List<Integer> markStorage = new ArrayList<>();
			markStorage.add(marksInInt);

			// find max mark
			Integer maxMark = 0;
			for (int i = 0; i < markStorage.size(); i++) {
				// if mark is max then print
				if(markStorage.get(i) > maxMark) {
					maxMark = markStorage.get(i);
					System.out.println("Max Mark = " + maxMark);
				}
			}
			
		}
	}
	
	@Override
	public void run() {
		try {
			UDPMessage("240.0.0.1", 2806);
        } catch(IOException e) {
            e.printStackTrace();
        }
	}

}
