package mypackage;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;


public class MonView extends View{
float xText, yText;

public MonView(Context context, AttributeSet attrs) {
    super(context, attrs);
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
    p.setTextSize(100);
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