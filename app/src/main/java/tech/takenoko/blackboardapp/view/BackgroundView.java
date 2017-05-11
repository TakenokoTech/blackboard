package tech.takenoko.blackboardapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by たけのこ on 2017/04/30.
 */

public class BackgroundView extends View {

    Paint paint = new Paint();

    public BackgroundView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int startColor = Color.rgb(20, 50, 20);
        int endColor = Color.rgb(0, 50, 0);
        paint.setColor(endColor);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);

        RadialGradient gradient = new RadialGradient(
                getWidth()/2,
                getHeight()/2,
                getWidth()/2,
                startColor,
                endColor,
                android.graphics.Shader.TileMode.CLAMP
        );
        Paint paint = new Paint();
        paint.setDither(true);
        paint.setShader(gradient);
        canvas.drawCircle(getWidth()/2, getHeight()/2, getWidth()/2, paint);
    }
}
