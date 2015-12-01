/*
CIS 457
Project 1 part 2
Java client server file transfer
Jonathan Powers, Kevin Anderson, Brett Greenman
 */

import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.nio.file.Files;
//Server class for the client to connect to.
public class Server {
	static int received = 0;
	static int sent = 0;
	DatagramSocket serverSocket = new DatagramSocket(9876);
	static String fileName;
	public Server() throws IOException{
		
		while(true) {
			byte[] recieveData = new byte[1024];
			DatagramPacket recievePacket = new DatagramPacket(recieveData, recieveData.length);
			serverSocket.receive(recievePacket);

			// the message recieved from the client
			fileName = new String(recievePacket.getData()).trim();
			InetAddress ipAddress = recievePacket.getAddress();
			int port = recievePacket.getPort();
			
			serverSocket.connect(ipAddress, port);
//			byte[] sendData = message.getBytes();
//			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length);
//			serverSocket.send(sendPacket);
//			This method takes the request from the client to find and send the file.
			
			
			
			
				
			Thread sendThread = new Thread(){
				public void run(){
					System.out.println("Thread Running");
					InputStream fileIn = null;
					try{
				     	File file = new File ("Data/" + fileName);
						fileIn = new FileInputStream(file);	
						byte[] sendData = new byte[1024];
						int amountRead = fileIn.read(sendData);
						
						while(amountRead > 0){
							if((sent - received) < 5) {
								DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length);
								serverSocket.send(sendPacket);
								amountRead = fileIn.read(sendData);
							}
						}
						fileIn.close();
						sendData = new byte[1024];
						sendData = new String("end-of-file").getBytes();
						DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length);
						serverSocket.send(sendPacket);
					} catch (IOException e){
						//output.println("File does not exist");
						System.out.println("File not found...");	    
					}
				}
			};
			
			Thread receiveThread = new Thread(){
				public void run(){
					System.out.println("Thread Running");
					try{
						while(serverSocket.isConnected()){
							byte[] receiveData = new byte[1024];
							DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
							serverSocket.receive(receivePacket);
							received++;
							System.out.println("Acknowledgement Received: "  + received);
						}
					} catch (IOException e){
						//output.println("File does not exist");
						System.out.println("File not found...");	    
					}
				}
			};
			  
			sendThread.start();
			receiveThread.start();					
		}	    
	}
}
