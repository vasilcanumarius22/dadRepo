package eu.assignment.ie;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.*;

public class UDPClient_assignment_1 implements Runnable {
	

	public static void main(String[] args)  {
		Thread th = new Thread(new UDPClient_assignment_1());
		th.start();
	}
	
	public void UDPMessage (String ip, int port) throws IOException {
		// Initializing UDP parameters
		InetAddress multicast_addr = InetAddress.getByName("239.255.255.254"); // Class D IP Address ->
		MulticastSocket msocket = new MulticastSocket(2806); // Port -> 2806
		byte[] buff = new byte[256];
		
		// joinGroup(InetAddress groupAddr)
		// !IMPORTANT: joinGroup now needs "Multicast Group" and "Network Interface" also -> deprecated since java14 ¯\_(ツ)_/¯
		// joinGroup(group, netIf);
		msocket.joinGroup(multicast_addr); // multicast_addr is the multicast group
		
		while(true) {
			System.out.println("Hello there...");
			// initialization and configure of DatagramPacket
			DatagramPacket dpacket = new DatagramPacket(buff, buff.length);
			msocket.receive(dpacket);
			
			// mark reading
//			String marksInString = new String(dpacket.getData(), 0, dpacket.getLength());
//			int marksInInt = Integer.parseInt(marksInString);
			int marksInInt = Integer.parseInt(new String(dpacket.getData()).trim());			
			
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
			UDPMessage("239.255.255.254", 2806);
        } catch(IOException e) {
            e.printStackTrace();
        }
	}

}
