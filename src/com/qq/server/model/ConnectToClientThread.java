package com.qq.server.model;
/*
 * Thread link server to a client
 * must be loading message unstopably
 */

import java.net.*;
import java.util.PrimitiveIterator.OfDouble;

import com.qq.common.Message;
import com.qq.common.MessageType;

import java.io.*;

public class ConnectToClientThread extends Thread{
	Socket socket;
	
	public ConnectToClientThread(Socket s){
		socket = s;
	}
	
	public void run(){

		boolean bool = true;
		while( true){
			
			try {
				//receive the message from the sender client
				if(/*socket.isConnected()&&!(socket.isClosed())*/true){
					if(socket.isClosed()){
						System.out.println("socket is close in ConnectToClientThread");
					}
					ObjectInputStream objectInputStream =new ObjectInputStream(
							socket.getInputStream());
					//if(objectInputStream.read()!=-1){
						Message message = (Message)objectInputStream.readObject();
						if(message == null){
							System.out.println("null message");
						}
					if(message.getMessageType().equals(MessageType.NormalMessage)){
							System.out.println("From : " + message.getSenderName() + 
																	"\nTo : " + message.getReceiverName() + 
																	"\nContent : " + message.getMessage() + 
																	"\nOn : " + message.getSendTime() + " in ConnectToClientThread\n\n");
							//send the message to the receiver client
							ConnectToClientThread receiverThread = ClientThreadManager.getClientThread(
									message.getReceiverName());
							ObjectOutputStream objectOutputStream = new ObjectOutputStream(
									receiverThread.socket.getOutputStream());
							objectOutputStream.writeObject(message);
							//objectInputStream.close();
							//objectOutputStream.close();
						
					}else if(message.getMessageType().equals(MessageType.GetOnlineFriendsMessage)){
						System.out.println("return online friends");
						Message onlineFriendsMessage = new Message();
						onlineFriendsMessage.setMessageType(MessageType.ReturnOnlineFriendsMessage);
						onlineFriendsMessage.setMessage(ClientThreadManager.getOnlineFriends());
						onlineFriendsMessage.setReceiverName(message.getSenderName());
						ObjectOutputStream objectOutputStream1 = 
								new ObjectOutputStream(socket.getOutputStream());
						System.out.println("FRIEND " + onlineFriendsMessage.getMessage());
						objectOutputStream1.writeObject(onlineFriendsMessage);
						//objectOutputStream1.close();
					}
				}else{		
						//System.out.println("socket is close in ConnectToClientThread");
						//break;
					}
			//	}else{
				//		bool = false;
						//System.out.println("Thread stop");
			//	}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//break;
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				
			}
			
		}
	
	}
	
}
