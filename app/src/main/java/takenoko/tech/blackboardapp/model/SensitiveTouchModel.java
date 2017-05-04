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
    @Getter Paint penLine = new Paint();
    @Getter Paint blurPanLine = new Paint();
    @Getter Paint eraserLine = new Paint();
    @Getter Paint eraserRect = new Paint();

    Point prevPoint = null;
    int strokeWidth = 10;
    int strokeColor = Color.argb(200, 240, 240, 240);

    // maskRect
    @Getter RectF menuMasKRect;
    @Getter RectF debugerMasKRect;

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
                model.getSurfaceY() - context.getResources().getDimension(R.dimen.debuger_height),
                model.getSurfaceX(),
                model.getSurfaceY()
        );
    }

    public boolean canceler(float x) {
        return context.getResources().getDimension(R.dimen.menu_list_width) >= x;
    }

    public void calcLine() {
        //Debuger.print(context, "" + strokeWidth);
    }
}
