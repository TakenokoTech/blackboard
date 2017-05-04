package takenoko.tech.blackboardapp.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import takenoko.tech.blackboardapp.model.SensitiveTouchModel;

/**
 * Created by たけのこ on 2017/05/05.
 */

public class PenCanvas extends View {
    public PenCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Path path = new Path();
        path.moveTo(getWidth()/2,getHeight()/2);
        path.lineTo(getWidth()/2,getHeight()/2);
        canvas.drawPath(path, SensitiveTouchModel.getPenLine());
    }
}
