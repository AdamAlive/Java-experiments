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
		  
		   
		  for(int i=0;i<=ROW;i++){//画横线 
		   g.drawLine(MAR, MAR+i*GRID, MAR+COL*GRID, MAR+i*GRID); 
		  } 
		  for(int i=0;i<=COL;i++){//画竖线 
		   g.drawLine(MAR+i*GRID, MAR, MAR+i*GRID, MAR+ROW*GRID); 
		    
		  } 
		   
		  //画棋子 
		  for(int i=0;i<chessCount;i++){ 
		   //网格交叉点x，y坐标 
		   int xPos=chessList[i].getX()*GRID+MAR; 
		   int yPos=chessList[i].getY()*GRID+MAR; 
		   g.setColor(chessList[i].getColor());//设置颜色 
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
		   //标记最后一个棋子的红矩形框 
		    
		   if(i==chessCount-1){//如果是最后一个棋子 
		    g.setColor(Color.red); 
		    g.drawRect(xPos-Point.DIAMETER/2, yPos-Point.DIAMETER/2, 
		       34, 35); 
		   } 
		  } 
		 } 
		  
		 public void mousePressed(MouseEvent e){//鼠标在组件上按下时调用 
		   
		  //游戏结束时，不再能下 
		  if(gameOver) return; 
		   
		  String colorName=blackTurn?"黑棋":"白棋"; 
		   
		  //将鼠标点击的坐标位置转换成网格索引 
		  xIndex=(e.getX()-MAR+GRID/2)/GRID; 
		  yIndex=(e.getY()-MAR+GRID/2)/GRID; 
		   
		  //落在棋盘外不能下 
		  if(xIndex<0||xIndex>ROW||yIndex<0||yIndex>COL) 
		   return; 
		   
		  //如果x，y位置已经有棋子存在，不能下 
		  if(findChess(xIndex,yIndex))return; 
		   
		  //可以进行时的处理 
		  Point ch=new Point(xIndex,yIndex,blackTurn?Color.black:Color.white); 
		  chessList[chessCount++]=ch; 
		  repaint();//通知系统重新绘制 
		  
		   
		  //如果胜出则给出提示信息，不能继续下棋 
		   
		  if(isWin()){ 
		   String msg=String.format("恭喜，%s赢了！", colorName); 
		   JOptionPane.showMessageDialog(this, msg); 
		   gameOver=true; 
		  } 
		  blackTurn=!blackTurn; 
		  } 
		 //覆盖mouseListener的方法 
		 public void mouseClicked(MouseEvent e){ 
		  //鼠标按键在组件上单击时调用 
		 } 
		  
		 public void mouseEntered(MouseEvent e){ 
		  //鼠标进入到组件上时调用 
		 } 
		 public void mouseExited(MouseEvent e){ 
		  //鼠标离开组件时调用 
		 } 
		 public void mouseReleased(MouseEvent e){ 
		  //鼠标按钮在组件上释放时调用 
		 } 
		 //在棋子数组中查找是否有索引为x，y的棋子存在 
		 private boolean findChess(int x,int y){ 
		  for(Point c:chessList){ 
		   if(c!=null&&c.getX()==x&&c.getY()==y) 
		    return true; 
		  } 
		  return false; 
		 } 
		  
		  
		 private boolean isWin(){ 
		  int continueCount=1;//连续棋子的个数 
		  
		  //横向向西寻找 
		  for(int x=xIndex-1;x>=0;x--){ 
		   Color c=blackTurn?Color.black:Color.white; 
		   if(getChess(x,yIndex,c)!=null){ 
		    continueCount++; 
		   }else 
		    break; 
		  } 
		  //横向向东寻找 
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
		   
		  //继续另一种搜索纵向 
		  //向上搜索 
		  for(int y=yIndex-1;y>=0;y--){ 
		   Color c=blackTurn?Color.black:Color.white; 
		   if(getChess(xIndex,y,c)!=null){ 
		    continueCount++; 
		   }else 
		    break; 
		  } 
		  //纵向向下寻找 
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
		   
		   
		  //继续另一种情况的搜索：斜向 
		  //东北寻找 
		  for(int x=xIndex+1,y=yIndex-1;y>=0&&x<=COL;x++,y--){ 
		   Color c=blackTurn?Color.black:Color.white; 
		   if(getChess(x,y,c)!=null){ 
		    continueCount++; 
		   } 
		   else break; 
		  } 
		  //西南寻找 
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
		   
		   
		  //继续另一种情况的搜索：斜向 
		  //西北寻找 
		  for(int x=xIndex-1,y=yIndex-1;x>=0&&y>=0;x--,y--){ 
		   Color c=blackTurn?Color.black:Color.white; 
		   if(getChess(x,y,c)!=null) 
		    continueCount++; 
		   else break; 
		  } 
		  //东南寻找 
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
		  //清除棋子 
		  for(int i=0;i<chessList.length;i++){ 
		   chessList[i]=null; 
		  } 
		  //恢复游戏相关的变量值 
		  blackTurn=true; 
		  gameOver=false; //游戏是否结束 
		  chessCount =0; //当前棋盘棋子个数 
		  repaint(); 
		 } 
		  
		 //悔棋 
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
		  
		 //矩形Dimension 
		 
		 public Dimension getPreferredSize(){ 
		  return new Dimension(MAR*2+GRID*COL,MAR*2 
		       +GRID*ROW); 
		 } 
		}