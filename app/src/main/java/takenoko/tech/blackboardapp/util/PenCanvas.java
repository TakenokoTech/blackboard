package takenoko.tech.blackboardapp.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import takenoko.tech.blackboardapp.model.SensitiveTouchModel;

/**
 * Created by たけのこ on 2017/05/05.
 */

public class PenCanvas extends View {

    Paint line = new Paint(SensitiveTouchModel.getPenLine());

    public PenCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Path path = new Path();
        path.moveTo(getWidth()/2,getHeight()/2);
        path.lineTo(getWidth()/2,getHeight()/2+1);
        canvas.drawPath(path, line);
    }

    public void setLineSize(int size) {
        line.setStrokeWidth(size);
        invalidate();
    }
}
