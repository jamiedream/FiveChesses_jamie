package jamie20160927;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RadialGradientPaint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.sun.prism.GraphicsPipeline;


//棋子設定
class Point{
	  private int x;
	  private int y;
	  private Color color;
	  
	  public Point(int x,int y,Color color){
		  this.x=x;
		  this.y=y;
		  this.color=color;
	  } 
	  
	  public int getX(){
		  return x;
	  }
	  public int getY(){
		  return y;
	  }
	  public Color getColor(){
		  return color;
	  }	  
	}
//main function
public class Chesses extends JPanel{
	int margin = 25;
	int gridSpan = 30;
	int rows = 20;
	int cols = 20;
	
	Point[] chessPos= new Point[(rows+1)*(cols+1)];
	int chessCount;
	Color chesscolor;
	int ex, ey;
	
	boolean gameover = false;
	boolean isBlack = true;
	Point ch;
	
	
	public Chesses() {
		ChessesListener cl = new ChessesListener();
		addMouseListener(cl);
		addMouseMotionListener(cl);
		
		
	}
	public void dorestart(){
		chessCount = 0;
		gameover = false;
		isBlack = true;
		for(int i=0;i<chessPos.length;i++){
		chessPos[i] = null;
		}
		
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//畫棋盤		
		//橫線
		for(int i=0; i<=rows; i++){
			g.setColor(Color.BLACK);
			g.drawLine(margin, margin+i*gridSpan, margin+cols*gridSpan, margin+i*gridSpan);
		}
		//直線
		for(int i=0; i<=cols; i++){
			g.setColor(Color.BLACK);
			g.drawLine(margin+i*gridSpan, margin, margin+i*gridSpan, margin+rows*gridSpan);
		}
		
		//畫棋子		
		for(int i=0; i<chessCount; i++){
			//設定grid位置
			int xpos = chessPos[i].getX()*gridSpan+margin-gridSpan/2;
			int ypos = chessPos[i].getY()*gridSpan+margin-gridSpan/2;
			//取得顏色
			g.setColor(chessPos[i].getColor());
			chesscolor = chessPos[i].getColor();			
			
			if(chesscolor == Color.BLACK){
				Graphics2D g2d = (Graphics2D)g;
				g2d.setColor(Color.BLACK);
				g2d.fillOval(xpos, ypos, gridSpan, gridSpan);								
			}else if(chesscolor == Color.WHITE){
				Graphics2D g2d = (Graphics2D)g;
				g2d.setColor(Color.WHITE);
				g2d.fillOval(xpos, ypos, gridSpan, gridSpan);
			}
			
			//紅框
			if(chessCount-1 == i){
				g.setColor(Color.red);
				g.draw3DRect(xpos, ypos, gridSpan, gridSpan, true);
			}
			
			
		}
	}	

	protected class ChessesListener extends MouseAdapter{

		@Override
		public void mousePressed(MouseEvent e) {
			super.mousePressed(e);
			//gameover = true此局結束
			if(gameover)return;

			//get x y position which player clicked 
			ex = (e.getX()-margin+gridSpan/2)/gridSpan;
			ey = (e.getY()-margin+gridSpan/2)/gridSpan;
			//超出棋盤範圍不可下棋
			if(ex<0||ex>rows||ey<0||ey>cols)return;
			//判斷位置可否下棋
			if(findChess(ex,ey))return;

			ch = new Point(ex, ey, isBlack?Color.BLACK:Color.WHITE);//isBlack B=true
			chessPos[chessCount++]=ch;
			repaint();
			
			String winColor = isBlack?"黑棋":"白棋";;
			if(isWin()){
				int re = JOptionPane.showConfirmDialog(
						null, "恭喜"+winColor+"獲勝! "+"再玩一局?", "遊戲結束",
						JOptionPane.YES_NO_OPTION);
				gameover=true;
				if(re == JOptionPane.YES_OPTION){
					dorestart();
				}
				
			}	
			isBlack = !isBlack;//B||W
	
		}	
		private boolean findChess(int x,int y){
			for(Point c:chessPos){
				if(c != null && c.getX() == x && c.getY() == y)
					return true;	
			}
			return false;
		}
		
		private Point judgeLine(int ex,int ey, Color color){
			for(Point c:chessPos){
				if(c!=null&&c.getX()==ex&&c.getY()==ey&&c.getColor()==color)
					return c;
			}
			return null;
		}
		private boolean isWin(){
			int count = 1;

			//横向向西寻找
		   for(int x=ex-1;x>=0;x--){
			   Color c=isBlack?Color.black:Color.white;
			   if(judgeLine(x,ey,c)!=null){
				   count++;
			   }else break;
			   
		   }
			//横向向東寻找
		   for(int x=ex+1;x<=cols;x++){
			   Color c=isBlack?Color.black:Color.white;
			   if(judgeLine(x,ey,c)!=null){
				   count++;
			   }else break;
			   
		   }
		   if(count>=5)
	    	   return true;
	       else 
	    	   count=1;
		   

		 //直向向上寻找		   
		   for(int y=ey-1;y>=0;y--){
			   Color c=isBlack?Color.black:Color.white;
			   if(judgeLine(ex,y,c)!=null){
				   count++;
			   }else 
				   break;
		   } 
		   
		 //直向向下寻找
		   for(int y=ey+1;y<=rows;y++){
			   Color c=isBlack?Color.black:Color.white;
			   if(judgeLine(ex,y,c)!=null){
				   count++;
			   }else break;
			   
		   }
		   if(count>=5)
	    	   return true;
	       else count=1;; 
		   
		//西北寻找
		   for(int x=ex-1,y=ey-1;y>=0&&x>=0;x--,y--){
			   Color c=isBlack?Color.black:Color.white;
			   if(judgeLine(x,y,c)!=null){
				   count++;
			   }else break;
			   
		   }
		 //東南寻找
		   for(int x=ex+1,y=ey+1;y>=0&&x>=0;x++,y++){
			   Color c=isBlack?Color.black:Color.white;
			   if(judgeLine(x,y,c)!=null){
				   count++;
			   }else break;
			   
		   }
		   if(count>=5)
	    	   return true;
	       else count=1;;   
		 
		//東北寻找
		   for(int x=ex+1,y=ey-1;y>=0&&x>=0;x++,y--){
			   Color c=isBlack?Color.black:Color.white;
			   if(judgeLine(x,y,c)!=null){
				   count++;
			   }else break;
			   
		   }
		 //西南寻找
		   for(int x=ex-1,y=ey+1;y>=0&&x>=0;x--,y++){
			   Color c=isBlack?Color.black:Color.white;
			   if(judgeLine(x,y,c)!=null){
				   count++;
			   }else break;
			   
		   }		   
		   if(count>=5)
	    	   return true;
	       else count=1;
		   
		return false; 
		
		}
	}
		
	
}
