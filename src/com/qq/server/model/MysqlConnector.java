//package org.gsfan.clustermonitor.dbconnector;
package com.qq.server.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlConnector {
	
	private String userName = null;
	private String passwd = null;
	private String sql = null;
	
	private Connection connection = null;
	private PreparedStatement preStatement = null;
	private ResultSet resultSet = null;
	
	private static final String dbDriver = "com.mysql.jdbc.Driver";
//	private static final String dbDriver = "org.gjt.mm.mysql.Driver";
	private static final String dbURL = "jdbc:mysql://localhost/QQUser?user=root&password=896900488&useUnicode=true&characterEncoding=8859_1";
//	private static final String dbURL = "jdbc:mysql://localhost:3306/clusteruser";//使用主机IP会出错，这是为什么？
	
	public MysqlConnector(String userName, String passwd){
		this.userName = userName;
		this.passwd = passwd;
		
		try {
			Class.forName(dbDriver) ;
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

//		String sql = "insert into clusteruser values(2,'gsfan','0620631FGS')";

	}
	public boolean checkUser(String QQnumber,String QQpassword){

		try {
		
			connection = DriverManager.getConnection(dbURL, this.userName, this.passwd);
			
//			preStatement = connection.prepareStatement(sql);
//			preStatement.executeUpdate();
			
			sql = "select * from userInfo";
			preStatement = connection.prepareStatement(sql);
			resultSet = preStatement.executeQuery();
			
			while(resultSet.next()){//没有resultSet.next()会出现异常
				System.out.println("userName = "+resultSet.getString(2)+"\t password = "+resultSet.getString(3));
				if(QQnumber.equals(resultSet.getString(2)) &&QQpassword.equals(resultSet.getString(3))){
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public  String getUserName(String QQNumber){

		try {
		
			connection = DriverManager.getConnection(dbURL, this.userName, this.passwd);
			
//			preStatement = connection.prepareStatement(sql);
//			preStatement.executeUpdate();
			
			sql = "select * from userInfo";
			preStatement = connection.prepareStatement(sql);
			resultSet = preStatement.executeQuery();
			
			while(resultSet.next()){//没有resultSet.next()会出现异常
				//System.out.println("userName = "+resultSet.getString(2)+"\t password = "+resultSet.getString(3));
				if(QQNumber.equals(resultSet.getString(2))){
					return resultSet.getString(3);
				}else{
					return null;
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args){
		MysqlConnector connector = new MysqlConnector("root", "hxh896900488");
	}
}