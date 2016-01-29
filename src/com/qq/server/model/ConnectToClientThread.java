package com.qq.server.model;
/*
 * Thread link server to a client
 * must be loading message unstopably
 */

import java.net.*;

import com.qq.common.Message;

import java.io.*;

public class ConnectToClientThread extends Thread{
	Socket socket;
	
	public ConnectToClientThread(Socket s){
		socket = s;
	}
	
	public void run(){
		ObjectInputStream objectInputStream = null;
		boolean bool = true;
		while( bool){
			
			try {
				//receive the message from the sender client
				if(socket!=null&&
						socket.getInputStream().read()!=-1){
					objectInputStream = new ObjectInputStream(
							socket.getInputStream());
					Message message = (Message)objectInputStream.readObject();
					if(!(message.getMessageType().equals("4"))){
						System.out.println("From : " + message.getSenderName() + 
																"\nTo : " + message.getReceiverName() + 
																"\nContent : " + message.getMessage() + 
																"\nOn : " + message.getSendTime() + "in ConnectToClientThread\n\n");
						//send the message to the receiver client
						ConnectToClientThread receiverThread = ClientThreadManager.getClientThread(
								message.getReceiverName());
						ObjectOutputStream objectOutputStream = new ObjectOutputStream(
								receiverThread.socket.getOutputStream());
						objectOutputStream.writeObject(message);
						objectInputStream.close();
						objectOutputStream.close();
					}		
				}else{
						bool = false;
						//System.out.println("Thread stop");
				}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				
			}
			
		}
	
	}
	
}
