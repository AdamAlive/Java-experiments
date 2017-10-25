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
		setTitle("������JavaС���� ");
		chessBoard = new Board();
		
		Container contentPane = getContentPane();
		contentPane.add(chessBoard);
		chessBoard.setOpaque(true);
		
		menuBar = new JMenuBar();
		systemMenu = new JMenu("ϵͳ�˵�");
		
		startMenuItem = new JMenuItem("�ؿ�");
		exitMenuItem =new JMenuItem("�˳�"); 
		backMenuItem =new JMenuItem("����"); 
		
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
		startButton = new JButton("�ؿ�");
		exitButton = new JButton("�˳�");
		backButton = new JButton("����");
		
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
		pack();//����Ӧ��С�ĺ���
	}



private class MyItemListener implements ActionListener{ 
	  public void actionPerformed(ActionEvent e){ 
	   Object obj=e.getSource();//����¼�Դ 
	   if(obj==startButton){ 
	    //���¿�ʼ 
	    //JFiveFrame.this�ڲ��������ⲿ�� 
	    System.out.println("���¿�ʼ"); 
	    chessBoard.restartGame(); 
	   } 
	   else if (obj==exitMenuItem||obj==exitButton) 
	    System.exit(0); 
	   else if (obj==backMenuItem||obj==backButton){ 
	    System.out.println("����"); 
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