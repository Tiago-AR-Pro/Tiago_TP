package mypackage;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.os.Handler;

public class MonView extends View{
float xText, yText;
int sizeText=0;
Handler timerHandler = new Handler();
Runnable updateTimerThread = new Runnable(){
    public void run(){

        if(sizeText<150){
            sizeText +=5;
        }else{
            sizeText =5;
        }

        timerHandler.postDelayed(this, 100);
        invalidate();
    }
};

public MonView(Context context, AttributeSet attrs) {
    super(context, attrs);
    timerHandler.postDelayed(updateTimerThread,10);
    setXYText (600,600);
}
public void setXYText (float x, float y){
    xText = x;
    yText = y;
}
@Override
public void onDraw(Canvas canvas){
    super.onDraw(canvas);
    Paint p =new Paint();
    p.setColor(Color.GREEN);
    p.setTextSize(sizeText);
    p.setTextAlign(Paint.Align.CENTER);

    String texte = "Bonjour  MONDE";
    canvas.drawText(texte, xText,yText,p);
}

public boolean onTouchEvent(MotionEvent event){
    xText = event.getX();
    yText = event.getY();
    invalidate();
    return false;
}

}