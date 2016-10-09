package chunying.fivechesses_v2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Juyn-Ying on 24/9/2016.
 */
public class Chess extends View {
    private int rows = 10;
    private int cols = 10;
    private int span, viewH, viewW, sx, sy;
    private Paint p;

    private Point[] chessPos= new Point[(rows+1)*(cols+1)];
    private int chessCount;
    private int chesscolor;
    private int ex, ey;

    private boolean gameover = false;
    private boolean isBlack = true;
    private Point ch;



    public Chess(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        //取得螢幕寬高,span每一格間距,起點
        viewH = getHeight();
        viewW = getWidth();
        span = viewH / rows - 8;
        sx = (viewW - span * rows) / 2;
        sy = (viewH - span * cols ) / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //畫棋盤
        p = new Paint();
        for (int i = 0; i <= rows; i++) {
            p.setColor(Color.BLACK);
            p.setStrokeWidth(3);
            canvas.drawLine(sx, sy + i * span, sx + cols * span, sy + i * span, p);
        }
        for (int i = 0; i <= cols; i++) {
            p.setColor(Color.BLACK);
            p.setStrokeWidth(3);
            canvas.drawLine(sx + i * span, sy, sx + i * span, sy + rows * span, p);
        }

        //畫棋子

        for (int i = 0; i < chessCount; i++) {
            p.setStyle(Paint.Style.FILL);
            chesscolor = chessPos[i].getColor();
            p.setColor(chesscolor);
            //設定grid位置
            int xpos = chessPos[i].getX() * span + sx ;
            int ypos = chessPos[i].getY() * span + sy ;
            //取得顏色
            if(chesscolor == Color.BLACK){
                p.setColor(Color.BLACK);
                canvas.drawCircle(xpos,ypos,span/2,p);
            }else if(chesscolor == Color.WHITE){
                p.setColor(Color.WHITE);
                canvas.drawCircle(xpos,ypos,span/2,p);
            }

            }

    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        //gameover = true此局結束
        if (gameover) return false ;
        //get x y position
        ex = (int) ((e.getX() - sx + span / 2) / span);
        ey = (int) ((e.getY() - sy + span / 2) / span);

        //超出棋盤範圍不可下棋
        if (ex < 0 || ex > rows || ey < 0 || ey > cols ||findChess(ex, ey)) {
            return false;
        } else {
            ch = new Point(ex, ey, isBlack ? Color.BLACK : Color.WHITE);//isBlack B=true
            chessPos[chessCount++] = ch;
            invalidate();
        }


        String winColor = isBlack ? "黑棋" : "白棋";

        if (isWin()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext(),R.style.AlertDialogCustom);
            builder.setMessage("恭喜" + winColor + "獲勝!\n再玩一局?");
            builder.setTitle("遊戲結束");
            builder.setCancelable(false);
            builder.setNegativeButton("確認", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    dorestart();
                }
            });
            builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    gameover = true;
                }
            });builder.create().show();
        }
        isBlack = !isBlack;
        return super.onTouchEvent(e);
        }

    private boolean findChess(int x,int y){
        for(Point c:chessPos){
            if(c != null && c.getX() == x && c.getY() == y)
                return true;
        }
        return false;
    }
    private Point judgeLine(int ex,int ey, int color){
        for(Point c:chessPos){
            if(c!=null&&c.getX()==ex&&c.getY()==ey&&c.getColor()==color)
                return c;
        }
        return null;
    }
    private boolean isWin(){
        int count = 1;

        //横向向西
        for(int x=ex-1;x>=0;x--){
            int c=isBlack?Color.BLACK:Color.WHITE;
            if(judgeLine(x,ey,c)!=null){
                count++;
            }else break;

        }
        //横向向東
        for(int x=ex+1;x<=cols;x++){
            int c=isBlack?Color.BLACK:Color.WHITE;
            if(judgeLine(x,ey,c)!=null){
                count++;
            }else break;

        }
        if(count>=5)
            return true;
        else
            count=1;


        //直向向上
        for(int y=ey-1;y>=0;y--){
            int c=isBlack?Color.BLACK:Color.WHITE;
            if(judgeLine(ex,y,c)!=null){
                count++;
            }else
                break;
        }

        //直向向下
        for(int y=ey+1;y<=rows;y++){
            int c=isBlack?Color.BLACK:Color.WHITE;
            if(judgeLine(ex,y,c)!=null){
                count++;
            }else break;

        }
        if(count>=5)
            return true;
        else count=1;;

        //西北
        for(int x=ex-1,y=ey-1;y>=0&&x>=0;x--,y--){
            int c=isBlack?Color.BLACK:Color.WHITE;
            if(judgeLine(x,y,c)!=null){
                count++;
            }else break;

        }
        //東南
        for(int x=ex+1,y=ey+1;y>=0&&x>=0;x++,y++){
            int c=isBlack?Color.BLACK:Color.WHITE;
            if(judgeLine(x,y,c)!=null){
                count++;
            }else break;

        }
        if(count>=5)
            return true;
        else count=1;;

        //東北
        for(int x=ex+1,y=ey-1;y>=0&&x>=0;x++,y--){
            int c=isBlack?Color.BLACK:Color.WHITE;
            if(judgeLine(x,y,c)!=null){
                count++;
            }else break;

        }
        //西南
        for(int x=ex-1,y=ey+1;y>=0&&x>=0;x--,y++){
            int c=isBlack?Color.BLACK:Color.WHITE;
            if(judgeLine(x,y,c)!=null){
                count++;
            }else break;

        }
        if(count>=5)
            return true;
        else count=1;

        return false;

    }
    public void dorestart(){
        chessCount = 0;
        gameover = false;
        isBlack = true;
        for(int i=0;i<chessPos.length;i++){
            chessPos[i] = null;
        }

        invalidate();
    }
}






class Point{
    private int x;
    private int y;
    private int color;


    public Point(int x,int y,int color){
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
    public int getColor(){
        return color;
    }
}