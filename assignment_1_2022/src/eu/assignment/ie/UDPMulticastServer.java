package eu.assignment.ie;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.*;

public class UDPMulticastServer implements Runnable {
	List<Integer> markStorage = new ArrayList<>();
	
	public static void main (String[] args) {
		Thread th = new Thread(new UDPMulticastServer());
		th.start();
	}
	
	public void UDPReceiver (String ip_addr, int port) throws IOException {
			// Initializing UDP parameters
			InetAddress multicast_addr = InetAddress.getByName("239.255.255.254"); // Class D IP Address -> 239.255.255.254
			MulticastSocket msocket = new MulticastSocket(2806); // Port -> 2806
			byte[] buff = new byte[1024];
			
			// joinGroup(InetAddress groupAddr)
			// !IMPORTANT: joinGroup now needs "Multicast Group" and "Network Interface" also -> deprecated since java14 ¯\_(ツ)_/¯
			// joinGroup(group, netIf);
			msocket.joinGroup(multicast_addr); // multicast_addr is the multicast group
			
			while(true) {
				System.out.println("Hello there");
				// initialization and configure of DatagramPacket
				DatagramPacket dpacket = new DatagramPacket(buff, buff.length);
				msocket.receive(dpacket);
				
				// mark reading
//				String marksInString = new String(dpacket.getData(), 0, dpacket.getLength());
//				int marksInInt = Integer.parseInt(marksInString);
				int mark = Integer.parseInt(new String(dpacket.getData()).trim());			
				
				// creating an array to store the marks
				
				markStorage.add(mark);

				// find max mark
				// !! Not good enough... something with caches
//				Integer maxMark = 0;
//				for (int i = 0; i < markStorage.size(); i++) {
//					// if mark is max then print
//					if(markStorage.get(i) > maxMark) {
//						maxMark = markStorage.get(i);
//						System.out.println("General Kenobi...\nMax Mark is: " + maxMark);
//					}
//				}
				
				// Better version with Comparator Function
				System.out.println("General Kenobi...\nMax Mark is: " + markStorage.stream().max(Comparator.naturalOrder()).get());
			}

	}
	
	@Override
	public void run() {
		try {
			UDPReceiver("239.255.255.254", 2806);
        } catch(IOException e) {
            e.printStackTrace();
        }
	}
}