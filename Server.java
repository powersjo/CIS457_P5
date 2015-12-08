/*
CIS 457
Project 4 part 2
Java client server file transfer
Jonathan Powers, Kevin Anderson, Brett Greenman
 */

import java.io.*;
import java.util.*;
import java.math.BigInteger;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.file.Files;

//Server class for the client to connect to.
public class Server {
	static boolean thread1, thread2, thread3, thread4, thread5;
	static String fileName;
	static InputStream fileIn;
	static InetAddress ipAddress;
	static int port;
	static File file;
	static int packetNumber;
	static DatagramSocket serverSocket;

	public Server() throws IOException{
		thread1 = thread2 = thread3 = thread4 = thread5 = false;
		serverSocket = new DatagramSocket(9999);
		//serverSocket.setSoTimeout(30000);
		//while(true) {
			byte[] recieveData = new byte[1024];
			DatagramPacket recievePacket = new DatagramPacket(recieveData, recieveData.length);
			serverSocket.receive(recievePacket);
			// the message recieved from the client
			fileName = new String(recievePacket.getData()).trim();
			ipAddress = recievePacket.getAddress();
			port = recievePacket.getPort();
			file = new File ("Data/" + fileName);
			fileIn = new FileInputStream(file);
			packetNumber = 0;
			
			//serverSocket.disconnect();

			Thread sendThread1 = new Thread(){
				public void run(){
					System.out.println("Thread 1 Running");
					try{
						//serverSocket = new DatagramSocket();
						//serverSocket.connect(ipAddress, port);
						serverSocket.setSoTimeout(3000);
						byte[] read1b; //to add to packet to indicate packet sequence number.
						byte[] send1b = new byte[1020];
						int amountRead = fileIn.read(send1b);
						boolean send = true;
						
						while(amountRead > 0){
							packetNumber++;
							if(amountRead != send1b.length) {
								send1b = Arrays.copyOf(send1b, amountRead);
							}
							read1b = intToByteArray(packetNumber);
							byte[] sendData = concatByteArray(read1b, send1b);
							DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);
							
							System.out.println("Sent Packet: " + amountRead + "|" + send1b.length);
							serverSocket.send(sendPacket);

							send = false;
							while(!send){
								  byte[] receiveData = new byte[64];
								  DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
								  try{
									  //System.out.println("Waiting for Packet");
									  serverSocket.receive(receivePacket);
									  if(new String(receivePacket.getData()).length() > 0) {
										  //System.out.println("Acknowledgement Received");
										  amountRead = fileIn.read(send1b);
										  send = true;
									  }
								  } catch (SocketTimeoutException e){
									  	System.out.println("Resending Packet");
									  	serverSocket.send(sendPacket);
								    	send = false;
								    	continue;
								  }								  
							}
						}
						thread1 = true;
					} catch (SocketTimeoutException e){
						System.out.println(e.getStackTrace());		    
					} catch (IOException e){
						System.out.println(e.getStackTrace());
					}
				}
			};
	
			Thread sendThread2 = new Thread(){
				public void run(){
					System.out.println("Thread 2 Running");
					try{
						//serverSocket = new DatagramSocket();
						//serverSocket.connect(ipAddress, port);
						serverSocket.setSoTimeout(3000);
						byte[] read2b; //to add to packet to indicate packet sequence number.
						byte[] send2b = new byte[1020];
						int amountRead = fileIn.read(send2b);
						boolean send = true;
						
						while(amountRead > 0){
							packetNumber++;
							if(amountRead != send2b.length) {
								send2b = Arrays.copyOf(send2b, amountRead);
							}
							read2b = intToByteArray(packetNumber);
							byte[] sendData = concatByteArray(read2b, send2b);
							DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);
							
							System.out.println("Sent Packet: " + amountRead + "|" + send2b.length);
							serverSocket.send(sendPacket);
							send = false;
							while(!send){
								  byte[] receiveData = new byte[64];
								  DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
								  try{
									  //System.out.println("Waiting for Packet");
									  serverSocket.receive(receivePacket);
									  if(new String(receivePacket.getData()).length() > 0) {
										  //System.out.println("Acknowledgement Received");
										  amountRead = fileIn.read(send2b);
										  send = true;
									  }
								  } catch (SocketTimeoutException e){
									  	System.out.println("Resending Packet");
									  	serverSocket.send(sendPacket);
								    	send = false;
								    	continue;
								  }								  
							}
						}
						thread2 = true;
					} catch (SocketTimeoutException e){
						System.out.println(e.getStackTrace());		    
					} catch (IOException e){
						System.out.println(e.getStackTrace());
					}
				}
			};

			Thread sendThread3 = new Thread(){
				public void run(){
					System.out.println("Thread 2 Running");
					try{
						//serverSocket = new DatagramSocket();
						//serverSocket.connect(ipAddress, port);
						serverSocket.setSoTimeout(3000);
						byte[] read3b; //to add to packet to indicate packet sequence number.
						byte[] send3b = new byte[1020];
						int amountRead = fileIn.read(send3b);
						boolean send = true;
						
						while(amountRead > 0){
							packetNumber++;
							if(amountRead != send3b.length) {
								send3b = Arrays.copyOf(send3b, amountRead);
							}
							read3b = intToByteArray(packetNumber);
							byte[] sendData = concatByteArray(read3b, send3b);
							DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);
							
							System.out.println("Sent Packet: " + amountRead + "|" + send3b.length);
							serverSocket.send(sendPacket);
							send = false;
							while(!send){
								  byte[] receiveData = new byte[64];
								  DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
								  try{
									  //System.out.println("Waiting for Packet");
									  serverSocket.receive(receivePacket);
									  if(new String(receivePacket.getData()).length() > 0) {
										  //System.out.println("Acknowledgement Received");
										  amountRead = fileIn.read(send3b);
										  send = true;
									  }
								  } catch (SocketTimeoutException e){
									  	System.out.println("Resending Packet");
									  	serverSocket.send(sendPacket);
								    	send = false;
								    	continue;
								  }								  
							}
						}
						thread3 = true;
					} catch (SocketTimeoutException e){
						System.out.println(e.getStackTrace());		    
					} catch (IOException e){
						System.out.println(e.getStackTrace());
					}
				}
			};

			Thread sendThread4 = new Thread(){
				public void run(){
					System.out.println("Thread 2 Running");
					try{
						//serverSocket = new DatagramSocket();
						//serverSocket.connect(ipAddress, port);
						serverSocket.setSoTimeout(3000);
						byte[] read4b; //to add to packet to indicate packet sequence number.
						byte[] send4b = new byte[1020];
						int amountRead = fileIn.read(send4b);
						boolean send = true;
						
						while(amountRead > 0){
							packetNumber++;
							if(amountRead != send4b.length) {
								send4b = Arrays.copyOf(send4b, amountRead);
							}
							read4b = intToByteArray(packetNumber);
							byte[] sendData = concatByteArray(read4b, send4b);
							DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);
							
							System.out.println("Sent Packet: " + amountRead + "|" + send4b.length);
							serverSocket.send(sendPacket);
							send = false;
							while(!send){
								  byte[] receiveData = new byte[64];
								  DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
								  try{
									  //System.out.println("Waiting for Packet");
									  serverSocket.receive(receivePacket);
									  if(new String(receivePacket.getData()).length() > 0) {
										  //System.out.println("Acknowledgement Received");
										  amountRead = fileIn.read(send4b);
										  send = true;
									  }
								  } catch (SocketTimeoutException e){
									  	System.out.println("Resending Packet");
									  	serverSocket.send(sendPacket);
								    	send = false;
								    	continue;
								  }								  
							}
						}
						thread4 = true;
					} catch (SocketTimeoutException e){
						System.out.println(e.getStackTrace());		    
					} catch (IOException e){
						System.out.println(e.getStackTrace());
					}
				}
			};

			Thread sendThread5 = new Thread(){
				public void run(){
					System.out.println("Thread 2 Running");
					try{
						//serverSocket = new DatagramSocket();
						//serverSocket.connect(ipAddress, port);
						serverSocket.setSoTimeout(3000);
						byte[] read5b; //to add to packet to indicate packet sequence number.
						byte[] send5b = new byte[1020];
						int amountRead = fileIn.read(send5b);
						boolean send = true;
						
						while(amountRead > 0){
							packetNumber++;
							if(amountRead != send5b.length) {
								send5b = Arrays.copyOf(send5b, amountRead);
							}
							read5b = intToByteArray(packetNumber);
							byte[] sendData = concatByteArray(read5b, send5b);
							DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);
							
							System.out.println("Sent Packet: " + amountRead + "|" + send5b.length);
							serverSocket.send(sendPacket);
							send = false;
							while(!send){
								  byte[] receiveData = new byte[64];
								  DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
								  try{
									  //System.out.println("Waiting for Packet");
									  serverSocket.receive(receivePacket);
									  if(new String(receivePacket.getData()).length() > 0) {
										  //System.out.println("Acknowledgement Received");
										  amountRead = fileIn.read(send5b);
										  send = true;
									  }
								  } catch (SocketTimeoutException e){
									  	System.out.println("Resending Packet");
									  	serverSocket.send(sendPacket);
								    	send = false;
								    	continue;
								  }								  
							}
						}

						thread5 = true;
					} catch (SocketTimeoutException e){
						System.out.println(e.getStackTrace());		    
					} catch (IOException e){
						System.out.println(e.getStackTrace());
					}
				}
			};
			  
			sendThread1.start();
			sendThread2.start();
			sendThread3.start();
			sendThread4.start();
			sendThread5.start();

			while(true) {
				if(thread1 && thread2 && thread3 && thread4 && thread5) {
					System.out.println("End of File");
					byte[] sendData = new String("-EOF-").getBytes();
					DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);
					serverSocket.send(sendPacket);
					break;
				}
			}					
		//}	    
	}

	public static int byteArrayToInt(byte[] b) 
	{
	    int value = 0;
	    for (int i = 0; i < 4; i++) {
	        int shift = (4 - 1 - i) * 8;
	        value += (b[i] & 0x000000FF) << shift;
	    }
	    return value;
	}

	public static byte[] intToByteArray(int a)
	{
	    byte[] ret = new byte[4];
	    ret[3] = (byte) (a & 0xFF);   
	    ret[2] = (byte) ((a >> 8) & 0xFF);   
	    ret[1] = (byte) ((a >> 16) & 0xFF);   
	    ret[0] = (byte) ((a >> 24) & 0xFF);
	    return ret;
	}
	
	public byte[] concatByteArray(byte[] a, byte[] b) throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
		outputStream.write( a );
		outputStream.write( b );

		return outputStream.toByteArray( );
	}
}
