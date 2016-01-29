package com.qq.server.model;
/*
 * 等待客户端连接
 * 
 */
import java.net.*;
import java.io.*;
import java.util.*;

import javax.net.ssl.SSLContext;
import javax.swing.text.AbstractDocument.BranchElement;

import com.qq.common.Message;
import com.qq.common.User;
public class QQServer {

	public QQServer() throws IOException, ClassNotFoundException{
		ServerSocket serverSocket  = new ServerSocket(9999);
		boolean bool = true;
		while(bool){
			Socket socket  = serverSocket.accept();
			/*BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			String info = bufferedReader.readLine();
			*/
			if(socket.getInputStream().read()!=-1){
				ObjectInputStream objectInputStream = new ObjectInputStream(
						socket.getInputStream());
				User user = (User)objectInputStream.readObject();
				System.out.println(user.getQQNumber());
				System.out.println(user.getPassword());
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(
						socket.getOutputStream());
				Message message = new Message();
				MysqlConnector mysqlConnector = new MysqlConnector("root", "hxh896900488");
				if(mysqlConnector.checkUser(user.getQQNumber(), user.getPassword())){
					message.setMessageType("1");
					objectOutputStream.writeObject(message);
					
					//start a thread for a client request and add it into the hashmap in server
					ConnectToClientThread clientThread = new ConnectToClientThread(socket);
					ClientThreadManager.addClient(user.getQQNumber(), clientThread);
					clientThread.start();
					
				}else{
					message.setMessageType("2");
					objectOutputStream.writeObject(message);
					socket.close();
				}
			}else{
				bool = false;
			}
		}
	}
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		QQServer server = new QQServer();
	}

}
