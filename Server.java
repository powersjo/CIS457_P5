/*
CIS 457
Project 4 part 2
Java client server file transfer
Jonathan Powers, Kevin Anderson, Brett Greenman
 */

import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.nio.file.Files;
//Server class for the client to connect to.
public class Server {
	static boolean thread1, thread2, thread3, thread4, thread5;
	static String fileName;
        static InputStream fileIn;
	static InetAddress ipAddress;
	static int port;
	static File file;
	public Server() throws IOException{
		thread1 = thread2 = thread3 = thread4 = thread5 = false;
		while(true) {
			DatagramSocket serverSocket = new DatagramSocket(9876);
			byte[] recieveData = new byte[1024];
			DatagramPacket recievePacket = new DatagramPacket(recieveData, recieveData.length);
			serverSocket.receive(recievePacket);

			serverSocket.setSoTimeout(1000);
			// the message recieved from the client
			fileName = new String(recievePacket.getData()).trim();
			ipAddress = recievePacket.getAddress();
			port = recievePacket.getPort();
			
			serverSocket.close();
//			byte[] sendData = message.getBytes();
//			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length);
//			serverSocket.send(sendPacket);
//			This method takes the request from the client to find and send the file.
			
			
			file = new File ("Data/" + fileName);
			fileIn = new FileInputStream(file);

			Thread sendThread1 = new Thread(){
				public void run(){
					System.out.println("Thread 1 Running");
					//DatagramSocket threadSocket1 = new DatagramSocket();
					//threadSocket1.connect(ipAddress, port);
					//InputStream fileIn = null;
					try{
						DatagramSocket threadSocket1 = new DatagramSocket();
						threadSocket1.connect(ipAddress, port);
						byte[] read1b = new byte[8]; //to add to packet to indicate thread num.
				     	//File file = new File ("Data/" + fileName);
						//fileIn = new FileInputStream(file);	
						byte[] sendData = new byte[1024];
						int amountRead = fileIn.read(sendData);
						
						while(amountRead > 0){
							DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length);
							threadSocket1.send(sendPacket);
							amountRead = fileIn.read(sendData);
							//while(){
							  byte[] receiveData = new byte[1024];
							  DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
							  try{
							    threadSocket1.receive(receivePacket);
							  } catch (SocketTimeoutException e){
							    threadSocket1.send(sendPacket);
							    continue;
							  }
							  System.out.println("Acknowledgement Received");
							//}
						}
						//fileIn.close();
						sendData = new byte[1024];
						sendData = new String("end-of-file").getBytes();
						DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length);
						threadSocket1.send(sendPacket);
					} catch (SocketTimeoutException e){
						System.out.println(e.getStackTrace());		    
					} catch (IOException e){}
				}
			};
	
			Thread sendThread2 = new Thread(){
				public void run(){
					System.out.println("Thread 2 Running");
					//DatagramSocket threadSocket2 = new DatagramSocket();
					//threadSocket2.connect(ipAddress, port);
					//InputStream fileIn = null;
					try{
						DatagramSocket threadSocket2 = new DatagramSocket();
						threadSocket2.connect(ipAddress, port);
						byte[] read2b = new byte[8];
				     	//File file = new File ("Data/" + fileName);
						//fileIn = new FileInputStream(file);	
						byte[] sendData = new byte[1024];
						int amountRead = fileIn.read(sendData);
						
						while(amountRead > 0){
							DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length);
							threadSocket2.send(sendPacket);
							amountRead = fileIn.read(sendData);
  
                                                        byte[] receiveData = new byte[1024];
							DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
							threadSocket2.receive(receivePacket);
							System.out.println("Acknowledgement Received");
						}
						//fileIn.close();
						sendData = new byte[1024];
						sendData = new String("end-of-file").getBytes();
						DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length);
						threadSocket2.send(sendPacket);
					} catch (SocketTimeoutException e){
						System.out.println(e.getStackTrace());		    
					} catch (IOException e){}
				}
			};

			Thread sendThread3 = new Thread(){
				public void run(){
					System.out.println("Thread 3 Running");
					//DatagramSocket threadSocket3 = new DatagramSocket();
					//threadSocket3.connect(ipAddress, port);
					//InputStream fileIn = null;
					try{
						DatagramSocket threadSocket3 = new DatagramSocket();
						threadSocket3.connect(ipAddress, port);
						byte[] read3b = new byte[8];
						//File file = new File ("Data/" + fileName);
						//fileIn = new FileInputStream(file);	
						byte[] sendData = new byte[1024];
						int amountRead = fileIn.read(sendData);
						
						while(amountRead > 0){
							DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length);
							threadSocket3.send(sendPacket);
							amountRead = fileIn.read(sendData);
  
                                                        byte[] receiveData = new byte[1024];
							DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
							threadSocket3.receive(receivePacket);
							System.out.println("Acknowledgement Received");
						}
						//fileIn.close();
						sendData = new byte[1024];
						sendData = new String("end-of-file").getBytes();
						DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length);
						threadSocket3.send(sendPacket);
					} catch (SocketTimeoutException e){
						System.out.println(e.getStackTrace());		    
					} catch (IOException e){}
				}
			};

			Thread sendThread4 = new Thread(){
				public void run(){
					System.out.println("Thread 4 Running");
					//DatagramSocket threadSocket4 = new DatagramSocket();
					//threadSocket4.connect(ipAddress, port);
					//InputStream fileIn = null;
					try{
						DatagramSocket threadSocket4 = new DatagramSocket();
						threadSocket4.connect(ipAddress, port);
						byte[] read4b = new byte[8];
				     	//File file = new File ("Data/" + fileName);
						//fileIn = new FileInputStream(file);	
						byte[] sendData = new byte[1024];
						int amountRead = fileIn.read(sendData);
						
						while(amountRead > 0){
							DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length);
							threadSocket4.send(sendPacket);
							amountRead = fileIn.read(sendData);
  
                                                        byte[] receiveData = new byte[1024];
							DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
							threadSocket4.receive(receivePacket);
							System.out.println("Acknowledgement Received");
						}
						//fileIn.close();
						sendData = new byte[1024];
						sendData = new String("end-of-file").getBytes();
						DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length);
						threadSocket4.send(sendPacket);
					} catch (SocketTimeoutException e){
						System.out.println(e.getStackTrace());	    
					} catch (IOException e){}
				}
			};

			Thread sendThread5 = new Thread(){
				public void run(){
					System.out.println("Thread 5 Running");
					//DatagramSocket threadSocket5 = new DatagramSocket();
					//threadSocket5.connect(ipAddress, port);
					//InputStream fileIn = null;
					try{
						DatagramSocket threadSocket5 = new DatagramSocket();
						threadSocket5.connect(ipAddress, port);
						byte[] read5b = new byte[8];
				     	//File file = new File ("Data/" + fileName);
						//fileIn = new FileInputStream(file);	
						byte[] sendData = new byte[1024];
						int amountRead = fileIn.read(sendData);
						
						while(amountRead > 0){
							DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length);
							threadSocket5.send(sendPacket);
							amountRead = fileIn.read(sendData);
  
                                                        byte[] receiveData = new byte[1024];
							DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
							threadSocket5.receive(receivePacket);
							System.out.println("Acknowledgement Received");
						}
						//fileIn.close();
						sendData = new byte[1024];
						sendData = new String("end-of-file").getBytes();
						DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length);
						threadSocket5.send(sendPacket);
					} catch (SocketTimeoutException e){
						System.out.println(e.getStackTrace());	    
					} catch (IOException e){}
				}
			};
			
			/*Thread receiveThread = new Thread(){
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
			};*/
			  
			sendThread1.start();
			sendThread2.start();
			sendThread3.start();
			sendThread4.start();
			sendThread5.start();
			//receiveThread.start();					
		}	    
	}
}
