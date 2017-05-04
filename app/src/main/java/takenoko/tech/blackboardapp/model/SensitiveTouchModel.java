package takenoko.tech.blackboardapp.model;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;

import java.io.Serializable;

import lombok.Getter;
import takenoko.tech.blackboardapp.R;

/**
 * Created by たけのこ on 2017/05/02.
 */

public class SensitiveTouchModel implements Serializable {

    final static String log = "--SensitiveTouchModel--";

    Context context;
    @Getter static Paint penLine = new Paint();
    @Getter static Paint blurPanLine = new Paint();
    @Getter static Paint eraserLine = new Paint();
    @Getter static Paint eraserRect = new Paint();

    Point prevPoint = null;
    @Getter static int strokeWidth = 10;
    int strokeColor = Color.argb(200, 240, 240, 240);

    // maskRect
    @Getter RectF menuMasKRect;
    @Getter RectF debugerMasKRect;
    @Getter RectF statusMasKRect;

    public SensitiveTouchModel(Context context) {
        this.context = context;
        settingPenLine();
    }

    public void settingPenLine() {
        penLine.setColor(strokeColor);
        penLine.setStyle(Paint.Style.STROKE);
        penLine.setStrokeJoin(Paint.Join.ROUND);
        penLine.setStrokeCap(Paint.Cap.ROUND);
        penLine.setStrokeWidth(strokeWidth);
        blurPanLine.set(penLine);
        blurPanLine.setStrokeWidth(strokeWidth/2*3);
        blurPanLine.setMaskFilter(new BlurMaskFilter(strokeWidth*2, BlurMaskFilter.Blur.NORMAL));
        eraserLine.set(penLine);
        eraserLine.setColor(Color.TRANSPARENT);
        eraserLine.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        eraserRect.set(eraserLine);
        eraserRect.setColor(Color.RED);
        eraserRect.setStyle(Paint.Style.FILL);
    }

    public void settingMask(DrawSurfaceModel model) {
        menuMasKRect = new RectF(
                0,
                0,
                context.getResources().getDimension(R.dimen.menu_list_width),
                model.getSurfaceY()
        );
        debugerMasKRect = new RectF(
                0,
                model.getSurfaceY() - context.getResources().getDimension(R.dimen.debug_height),
                model.getSurfaceX(),
                model.getSurfaceY()
        );
        statusMasKRect = new RectF(
                model.getSurfaceX(),
                0,
                model.getSurfaceX() - context.getResources().getDimension(R.dimen.status_height),
                context.getResources().getDimension(R.dimen.status_height)
        );
    }

    public boolean canceler(float x) {
        return context.getResources().getDimension(R.dimen.menu_list_width) >= x;
    }

    public void calcLine() {
        //Debuger.print(context, "" + strokeWidth);
    }
}
