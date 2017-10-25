package myGoBang;

import java.awt.Color; 
import java.awt.Cursor; 
import java.awt.Dimension; 
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.Image;
import java.awt.RadialGradientPaint; 
import java.awt.RenderingHints; 
import java.awt.Toolkit; 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import java.awt.event.MouseMotionListener; 
import java.awt.geom.Ellipse2D;

import javax.swing.*;

import goBang.Point; 

public class Board extends JPanel implements MouseListener { 
	public static int MAR = 30;
	public static int GRID = 35;
	public static int ROW = 15;
	public static int COL = 15;
	
	Point[] chessList = new Point[(ROW + 1) * (COL + 1)];
	boolean blackTurn = true;
	boolean gameOver = false;
	int chessCount;
	int xIndex, yIndex;
	
	Image img;
	Image shadows;
	Color color;
	public Board(){
		
		img = Toolkit.getDefaultToolkit().getImage("D:/JavaProject/Experiments/src/myGoBang/board.jpg");
		shadows = Toolkit.getDefaultToolkit().getImage("shadows.jpg");
		addMouseListener(this);
		addMouseMotionListener(new MouseMotionListener(){

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				int x1 = (e.getX() - MAR + GRID/2)/GRID;
				int y1 = (e.getY() - MAR + GRID/2)/GRID;
				if(x1 < 0 || x1 > ROW || y1 < 0 || y1 > COL || gameOver || findChess(x1,y1))
				{
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); 
				}
				else
				{
					setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
				
			}
			
		});
	}
	
	public void paintComponment(Graphics g){
		
		super.paintComponent(g);
		
		int imgW = img.getWidth(this);
		int imgH = img.getHeight(this);
		int FWidth = getWidth();
		int FHeight = getHeight();
		int x = (FWidth - imgW) / 2;
		int y = (FHeight - imgH) / 2;
		g.drawImage(img, x, y, null); 
		  
		   
		  for(int i=0;i<=ROW;i++){//������ 
		   g.drawLine(MAR, MAR+i*GRID, MAR+COL*GRID, MAR+i*GRID); 
		  } 
		  for(int i=0;i<=COL;i++){//������ 
		   g.drawLine(MAR+i*GRID, MAR, MAR+i*GRID, MAR+ROW*GRID); 
		    
		  } 
		   
		  //������ 
		  for(int i=0;i<chessCount;i++){ 
		   //���񽻲��x��y���� 
		   int xPos=chessList[i].getX()*GRID+MAR; 
		   int yPos=chessList[i].getY()*GRID+MAR; 
		   g.setColor(chessList[i].getColor());//������ɫ 
		   // g.fillOval(xPos-Point.DIAMETER/2, yPos-Point.DIAMETER/2, 
		       //Point.DIAMETER, Point.DIAMETER); 
		   //g.drawImage(shadows, xPos-Point.DIAMETER/2, yPos-Point.DIAMETER/2, Point.DIAMETER, Point.DIAMETER, null); 
		   color=chessList[i].getColor(); 
		   if(color==Color.black){ 
		    RadialGradientPaint paint = new RadialGradientPaint(xPos-Point.DIAMETER/2+25, yPos-Point.DIAMETER/2+10, 20, new float[]{0f, 1f} 
		    , new Color[]{Color.WHITE, Color.BLACK}); 
		    ((Graphics2D) g).setPaint(paint); 
		    ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
		    ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT); 
		 
		   } 
		   else if(color==Color.white){ 
		    RadialGradientPaint paint = new RadialGradientPaint(xPos-Point.DIAMETER/2+25, yPos-Point.DIAMETER/2+10, 70, new float[]{0f, 1f} 
		    , new Color[]{Color.WHITE, Color.BLACK}); 
		    ((Graphics2D) g).setPaint(paint); 
		    ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
		    ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT); 
		 
		   } 
		   
		   Ellipse2D e = new Ellipse2D.Float(xPos-Point.DIAMETER/2, yPos-Point.DIAMETER/2, 34, 35); 
		   ((Graphics2D) g).fill(e); 
		   //������һ�����ӵĺ���ο� 
		    
		   if(i==chessCount-1){//��������һ������ 
		    g.setColor(Color.red); 
		    g.drawRect(xPos-Point.DIAMETER/2, yPos-Point.DIAMETER/2, 
		       34, 35); 
		   } 
		  } 
		 } 
		  
		 public void mousePressed(MouseEvent e){//���������ϰ���ʱ���� 
		   
		  //��Ϸ����ʱ���������� 
		  if(gameOver) return; 
		   
		  String colorName=blackTurn?"����":"����"; 
		   
		  //�������������λ��ת������������ 
		  xIndex=(e.getX()-MAR+GRID/2)/GRID; 
		  yIndex=(e.getY()-MAR+GRID/2)/GRID; 
		   
		  //���������ⲻ���� 
		  if(xIndex<0||xIndex>ROW||yIndex<0||yIndex>COL) 
		   return; 
		   
		  //���x��yλ���Ѿ������Ӵ��ڣ������� 
		  if(findChess(xIndex,yIndex))return; 
		   
		  //���Խ���ʱ�Ĵ��� 
		  Point ch=new Point(xIndex,yIndex,blackTurn?Color.black:Color.white); 
		  chessList[chessCount++]=ch; 
		  repaint();//֪ͨϵͳ���»��� 
		  
		   
		  //���ʤ���������ʾ��Ϣ�����ܼ������� 
		   
		  if(isWin()){ 
		   String msg=String.format("��ϲ��%sӮ�ˣ�", colorName); 
		   JOptionPane.showMessageDialog(this, msg); 
		   gameOver=true; 
		  } 
		  blackTurn=!blackTurn; 
		  } 
		 //����mouseListener�ķ��� 
		 public void mouseClicked(MouseEvent e){ 
		  //��갴��������ϵ���ʱ���� 
		 } 
		  
		 public void mouseEntered(MouseEvent e){ 
		  //�����뵽�����ʱ���� 
		 } 
		 public void mouseExited(MouseEvent e){ 
		  //����뿪���ʱ���� 
		 } 
		 public void mouseReleased(MouseEvent e){ 
		  //��갴ť��������ͷ�ʱ���� 
		 } 
		 //�����������в����Ƿ�������Ϊx��y�����Ӵ��� 
		 private boolean findChess(int x,int y){ 
		  for(Point c:chessList){ 
		   if(c!=null&&c.getX()==x&&c.getY()==y) 
		    return true; 
		  } 
		  return false; 
		 } 
		  
		  
		 private boolean isWin(){ 
		  int continueCount=1;//�������ӵĸ��� 
		  
		  //��������Ѱ�� 
		  for(int x=xIndex-1;x>=0;x--){ 
		   Color c=blackTurn?Color.black:Color.white; 
		   if(getChess(x,yIndex,c)!=null){ 
		    continueCount++; 
		   }else 
		    break; 
		  } 
		  //������Ѱ�� 
		  for(int x=xIndex+1;x<=COL;x++){ 
		   Color c=blackTurn?Color.black:Color.white; 
		   if(getChess(x,yIndex,c)!=null){ 
		    continueCount++; 
		   }else 
		    break; 
		  } 
		  if(continueCount>=5){ 
		    return true; 
		  }else 
		  continueCount=1; 
		   
		  //������һ���������� 
		  //�������� 
		  for(int y=yIndex-1;y>=0;y--){ 
		   Color c=blackTurn?Color.black:Color.white; 
		   if(getChess(xIndex,y,c)!=null){ 
		    continueCount++; 
		   }else 
		    break; 
		  } 
		  //��������Ѱ�� 
		  for(int y=yIndex+1;y<=ROW;y++){ 
		   Color c=blackTurn?Color.black:Color.white; 
		   if(getChess(xIndex,y,c)!=null) 
		    continueCount++; 
		   else 
		    break; 
		   
		  } 
		  if(continueCount>=5) 
		   return true; 
		  else 
		   continueCount=1; 
		   
		   
		  //������һ�������������б�� 
		  //����Ѱ�� 
		  for(int x=xIndex+1,y=yIndex-1;y>=0&&x<=COL;x++,y--){ 
		   Color c=blackTurn?Color.black:Color.white; 
		   if(getChess(x,y,c)!=null){ 
		    continueCount++; 
		   } 
		   else break; 
		  } 
		  //����Ѱ�� 
		  for(int x=xIndex-1,y=yIndex+1;x>=0&&y<=ROW;x--,y++){ 
		   Color c=blackTurn?Color.black:Color.white; 
		   if(getChess(x,y,c)!=null){ 
		    continueCount++; 
		   } 
		   else break; 
		  } 
		  if(continueCount>=5) 
		   return true; 
		  else continueCount=1; 
		   
		   
		  //������һ�������������б�� 
		  //����Ѱ�� 
		  for(int x=xIndex-1,y=yIndex-1;x>=0&&y>=0;x--,y--){ 
		   Color c=blackTurn?Color.black:Color.white; 
		   if(getChess(x,y,c)!=null) 
		    continueCount++; 
		   else break; 
		  } 
		  //����Ѱ�� 
		  for(int x=xIndex+1,y=yIndex+1;x<=COL&&y<=ROW;x++,y++){ 
		   Color c=blackTurn?Color.black:Color.white; 
		   if(getChess(x,y,c)!=null) 
		    continueCount++; 
		   else break; 
		  } 
		  if(continueCount>=5) 
		   return true; 
		  else continueCount=1; 
		   
		  return false; 
		  } 
		  
		  
		 private Point getChess(int xIndex,int yIndex,Color color){ 
		  for(Point p:chessList){ 
		   if(p!=null&&p.getX()==xIndex&&p.getY()==yIndex 
		     &&p.getColor()==color) 
		    return p; 
		  } 
		  return null; 
		 } 
		  
		  
		 public void restartGame(){ 
		  //������� 
		  for(int i=0;i<chessList.length;i++){ 
		   chessList[i]=null; 
		  } 
		  //�ָ���Ϸ��صı���ֵ 
		  blackTurn=true; 
		  gameOver=false; //��Ϸ�Ƿ���� 
		  chessCount =0; //��ǰ�������Ӹ��� 
		  repaint(); 
		 } 
		  
		 //���� 
		 public void goback(){ 
		  if(chessCount==0) 
		   return ; 
		  chessList[chessCount-1]=null; 
		  chessCount--; 
		  if(chessCount>0){ 
		   xIndex=chessList[chessCount-1].getX(); 
		   yIndex=chessList[chessCount-1].getY(); 
		  } 
		  blackTurn=!blackTurn; 
		  repaint(); 
		 } 
		  
		 //����Dimension 
		 
		 public Dimension getPreferredSize(){ 
		  return new Dimension(MAR*2+GRID*COL,MAR*2 
		       +GRID*ROW); 
		 } 
		}