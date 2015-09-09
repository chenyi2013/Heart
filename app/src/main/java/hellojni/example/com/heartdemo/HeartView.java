package hellojni.example.com.heartdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by kevin on 15/9/8.
 */
public class HeartView extends View {


    private Paint mPaint;
    private Path path = new Path();
    private Path path1 = new Path();
    private Path path2 = new Path();
    private Path path3 = new Path();
    private Path path4 = new Path();


    private Path path5 = new Path();
    private Path path6 = new Path();
    private Path path7 = new Path();
    private Path path8 = new Path();


    private Bitmap bitmap = null;

    private int degree = 180;
    private double t = 0;
    private int tag = 1;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:

                    tag = 1;

                    if (degree < 360) {
                        degree++;
                        invalidate();
                        sendEmptyMessageDelayed(1, 20);
                    } else {
                        degree = 180;
                        sendEmptyMessageDelayed(2, 20);
                    }
                    break;
                case 2:
                    tag = 2;
                    if (degree < 360) {
                        degree++;
                        invalidate();
                        sendEmptyMessageDelayed(2, 20);
                    } else {
                        degree = 180;
                        sendEmptyMessageDelayed(3, 20);
                    }
                    break;
                case 3:
                    tag = 3;

                    if (t < 1) {
                        t = t + 0.0056;
                        invalidate();
                        sendEmptyMessageDelayed(3, 20);
                    } else {
                        t = 0;
                        sendEmptyMessageDelayed(4, 20);
                    }
                    break;
                case 4:

                    tag = 4;

                    if (t < 1) {
                        t = t + 0.0056;
                        invalidate();
                        sendEmptyMessageDelayed(4, 20);
                    } else {
                        t = 0;
                        sendEmptyMessageDelayed(1, 20);
                    }
                    break;

            }

        }
    };


    public HeartView(Context context) {
        super(context);
        init();
    }

    public HeartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HeartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setFilterBitmap(true);

        path.addCircle(60, 60, 50, Path.Direction.CCW);
        path.addCircle(160, 60, 50, Path.Direction.CCW);

        path1.moveTo(10, 60);
        path1.lineTo(110, 210);
        path1.lineTo(210, 60);
        path1.close();

        path2.moveTo(10, 10);
        path2.lineTo(210, 10);
        path2.lineTo(210, 60);
        path2.lineTo(10, 60);
        path2.close();

        path3.moveTo(10, 60);
        path3.quadTo(10, 110, 110, 210);
        path3.quadTo(210, 110, 210, 60);


        path5.addCircle(60, 60, 45, Path.Direction.CCW);
        path5.addCircle(160, 60, 45, Path.Direction.CCW);


        path8.moveTo(30, 60);
        path8.quadTo(30, 110, 110, 190);
        path8.quadTo(190, 110, 190, 60);

        path7.addCircle(80, 60, 30, Path.Direction.CCW);
        path7.addCircle(140, 60, 30, Path.Direction.CCW);
        path6.moveTo(50, 60);
        path6.quadTo(50, 110, 110, 160);
        path6.quadTo(170, 110, 170, 60);
        path6.close();

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.e);
        bitmap = bitmap.createScaledBitmap(bitmap, 200, 200, true);


    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        handler.sendEmptyMessageDelayed(1, 0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.parseColor("#666666"));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.parseColor("#eeeeee"));


        canvas.save();
        canvas.clipPath(path1);
        canvas.clipPath(path2, Region.Op.REPLACE);
        canvas.drawCircle(60, 60, 50, mPaint);
        canvas.drawCircle(160, 60, 50, mPaint);
        canvas.restore();

        canvas.save();
        canvas.drawPath(path3, mPaint);
        canvas.drawPath(path4, mPaint);
        canvas.restore();


        canvas.save();
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        switch (tag) {
            case 1:
                double x = (60 + 50 * Math.cos(degree * Math.PI * 2 / 360f));
                double y = (60 + 50 * Math.sin(degree * Math.PI * 2 / 360f));
                canvas.drawCircle((float) x, (float) y, 6, mPaint);
                break;
            case 2:
                double x1 = (160 + 50 * Math.cos(degree * Math.PI * 2 / 360f));
                double y1 = (60 + 50 * Math.sin(degree * Math.PI * 2 / 360f));
                canvas.drawCircle((float) x1, (float) y1, 6, mPaint);
                break;
            case 3:
                double x3 = Math.pow(1 - t, 2) * 210 + 2 * t * (1 - t) * 210 + Math.pow(t, 2) * 110;
                double y3 = Math.pow(1 - t, 2) * 60 + 2 * t * (1 - t) * 110 + Math.pow(t, 2) * 210;
                canvas.drawCircle((float) x3, (float) y3, 6, mPaint);
                break;
            case 4:
                double x2 = Math.pow(1 - t, 2) * 110 + 2 * t * (1 - t) * 10 + Math.pow(t, 2) * 10;
                double y2 = Math.pow(1 - t, 2) * 210 + 2 * t * (1 - t) * 110 + Math.pow(t, 2) * 60;
                canvas.drawCircle((float) x2, (float) y2, 6, mPaint);
                break;
        }
        canvas.restore();


        canvas.save();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.parseColor("#bbbbbb"));
        canvas.clipPath(path5);
        canvas.clipPath(path2, Region.Op.REPLACE);
        canvas.drawCircle(70, 60, 40, mPaint);
        canvas.drawCircle(150, 60, 40, mPaint);
        canvas.restore();


        canvas.save();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.parseColor("#bbbbbb"));
        canvas.drawPath(path8, mPaint);
        canvas.restore();

        canvas.save();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.parseColor("#bbbbbb"));
        canvas.clipPath(path6);
        canvas.clipPath(path7, Region.Op.UNION);
        canvas.drawBitmap(bitmap, 0, 0, mPaint);
        canvas.restore();

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        handler.removeCallbacks(null);
    }
}
