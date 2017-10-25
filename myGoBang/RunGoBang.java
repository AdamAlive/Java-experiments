package myGoBang;

import java.awt.event.*; 
import java.awt.*; 
 
import javax.swing.*;

import goBang.StartChessJFrame; 

public class RunGoBang extends JFrame{
	private Board chessBoard;
	private JPanel toolbar;
	private JButton startButton, backButton, exitButton;
	
	private JMenuBar menuBar;
	private JMenu systemMenu;
	private JMenuItem startMenuItem, exitMenuItem, backMenuItem;
	
	public RunGoBang(){
		setTitle("五子棋Java小程序 ");
		chessBoard = new Board();
		
		Container contentPane = getContentPane();
		contentPane.add(chessBoard);
		chessBoard.setOpaque(true);
		
		menuBar = new JMenuBar();
		systemMenu = new JMenu("系统菜单");
		
		startMenuItem = new JMenuItem("重开");
		exitMenuItem =new JMenuItem("退出"); 
		backMenuItem =new JMenuItem("悔棋"); 
		
		systemMenu.add(startMenuItem); 
		systemMenu.add(exitMenuItem); 
		systemMenu.add(backMenuItem);
		
		MyItemListener oak = new MyItemListener();
		
		this.startMenuItem.addActionListener(oak);
		backMenuItem.addActionListener(oak);
		exitMenuItem.addActionListener(oak);
		
		menuBar.add(systemMenu);
		setJMenuBar(menuBar);
		
		toolbar = new JPanel();
		startButton = new JButton("重开");
		exitButton = new JButton("退出");
		backButton = new JButton("撤回");
		
		toolbar.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		toolbar.add(startButton);
		toolbar.add(exitButton);
		toolbar.add(backButton);
		
		startButton.addActionListener(oak);
		exitButton.addActionListener(oak);
		backButton.addActionListener(oak);
		
		add(toolbar,BorderLayout.SOUTH);
		add(chessBoard);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();//自适应大小的函数
	}



private class MyItemListener implements ActionListener{ 
	  public void actionPerformed(ActionEvent e){ 
	   Object obj=e.getSource();//获得事件源 
	   if(obj==startButton){ 
	    //重新开始 
	    //JFiveFrame.this内部类引用外部类 
	    System.out.println("重新开始"); 
	    chessBoard.restartGame(); 
	   } 
	   else if (obj==exitMenuItem||obj==exitButton) 
	    System.exit(0); 
	   else if (obj==backMenuItem||obj==backButton){ 
	    System.out.println("悔棋"); 
	    chessBoard.goback(); 
	   } 
	  } 
	 } 



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RunGoBang t = new RunGoBang();
		t.setVisible(true);

	}

}