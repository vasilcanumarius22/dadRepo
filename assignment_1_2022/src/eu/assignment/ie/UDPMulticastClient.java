package eu.assignment.ie;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPMulticastClient {
	public static DatagramSocket dsocket = null;

	public static void main(String[] args) throws IOException {
		
		if(isInt(args[0])) {
			sender(args[0].trim());
		}
	}
	
	// method for converting string to int
		public static boolean isInt(String strNum) {
			try {
				int mark = Integer.parseInt(strNum);
				boolean range = (mark >= 1 && mark < 11) ? true : false;
				return range;
			} catch (NumberFormatException e){
				e.printStackTrace();
				return false;
		    }
		}
	
	public static void sender(String marks) {
		try {
			InetAddress multicast_addr = InetAddress.getByName("239.255.255.254");
			
			dsocket = new DatagramSocket();
			dsocket.setBroadcast(true);
			
			byte[] buff = marks.getBytes();
			DatagramPacket dpacket = new DatagramPacket(buff, buff.length, multicast_addr, 2806);
			
			dsocket.send(dpacket);
			dsocket.close();
			
//			System.out.println("Mark is: " + marks);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	

}
