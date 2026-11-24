package mypackage;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.os.Handler;



public class MonView extends View{
float xText, yText;
float x1, x2, y1, y2;
private int pencheH, pencheV, penche;
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
    OnTouchListener onTouchListener = new OnTouchListener(){
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            float dx, dy;
            String direction;

            switch (event.getAction()) {
                case (MotionEvent.ACTION_DOWN):
                    x1 = event.getX();
                    y1 = event.getY();
                    Log.i("pacman", "appuyé");
                    break;

                case (MotionEvent.ACTION_UP):
                    x2 = event.getX();
                    y2 = event.getY();
                    dx = x2 - x1;
                    dy = y2 - y1;

                    // Détermine la direction du glissement
                    if (Math.abs(dx) > Math.abs(dy)) {
                        if (dx > 0)
                            direction = "right";
                        else
                            direction = "left";
                    } else {
                        if (dy > 0)
                            direction = "down";
                        else
                            direction = "up";
                    }
                    Log.i("pacman", "laché " + direction);
                    Log.i("pacman", "dx = " + dx + "; dy = " + dy);
                    break;
            }

            invalidate();
            return true;
        }
    };
    setOnTouchListener(onTouchListener);
    /* accelerometre */
    Sensor accelerometre;
    SensorManager m = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
    accelerometre = m.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    final SensorEventListener mSensorEventListener = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            // Que faire en cas d'évènements sur le capteur ?
            pencheH = -(int)(sensorEvent.values[0]);
            pencheV = (int)(sensorEvent.values[1]);
            penche = pencheH * pencheH + pencheV * pencheV;
            Log.i("accel", "H=" + pencheH +" V="+ pencheV);
        }
    };

    m.registerListener(mSensorEventListener, accelerometre, SensorManager.SENSOR_DELAY_UI);
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




}