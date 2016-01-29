package com.qq.server.view;
/*
 * 服务器控制界面
 */
import javax.swing.*;

import com.qq.server.model.QQServer;

import java.awt.event.*;
import java.io.IOException;
public class ServerFrame  extends JFrame{
	
	JPanel totalpanel;
	JButton startButton,closeButton;
	
	public ServerFrame(){
		totalpanel = new JPanel();
		startButton = new JButton("Start Server");
		closeButton = new JButton("Close Server");
		totalpanel.add(startButton);
		totalpanel.add(closeButton);
		startButton.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				try {
					QQServer qqServer = new QQServer();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		this.add(totalpanel);
		this.setTitle("Server Controller");
		this.setSize(900,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerFrame serverFrame = new ServerFrame();
	}

}
