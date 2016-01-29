package com.qq.server.model;
import java.util.*;

public class ClientThreadManager {

	public static HashMap map = new HashMap<String,ConnectToClientThread>();
	public static void addClient(String user,ConnectToClientThread clientThread){
		map.put(user, clientThread);
	}
	public static ConnectToClientThread getClientThread(String user){
		return (ConnectToClientThread) map.get(user);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
