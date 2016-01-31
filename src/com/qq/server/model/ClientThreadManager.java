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
	//return the number of online-friends
		public static String getOnlineFriends(){
			Iterator iterator = map.keySet().iterator();
			String onlineFriends=" ";
			while(iterator.hasNext()){
				onlineFriends += iterator.next().toString() + " ";
			}
			return onlineFriends;
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
