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
import java.util.HashMap;

import lombok.Getter;
import lombok.Setter;
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
    @Getter @Setter
    static int strokeWidth = 10;
    @Getter @Setter
    static int strokeColor = Color.argb(200, 240, 240, 240);
    @Getter
    static HashMap<String, Integer> colorMap = new HashMap<>();

    // maskRect
    @Getter RectF menuMaskRect;
    @Getter RectF debugerMaskRect;
    @Getter RectF statusMaskRect;
    @Getter RectF subStatusMaskRect;
    @Getter RectF dialogMaskRect;

    public SensitiveTouchModel(Context context) {
        this.context = context;
        settingPenLine();
        settingColor();
    }

    public static void settingPenLine() {
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

    public void settingColor() {
        colorMap.put("WHITE",   Color.argb(200, 240, 240, 240));
        colorMap.put("PINK",    Color.argb(200, 247, 171, 173));
        colorMap.put("YELLOW",  Color.argb(200, 255, 255, 146));
        colorMap.put("BLUE",    Color.argb(200, 126, 203, 220));
        colorMap.put("GREEN",   Color.argb(200,   0, 255, 126));
        colorMap.put("ORANGE",  Color.argb(200, 255, 126,   0));
    }

    public void settingMask(DrawSurfaceModel model) {
        menuMaskRect = new RectF(
                0,
                0,
                context.getResources().getDimension(R.dimen.menu_list_width),
                model.getSurfaceY()
        );
        debugerMaskRect = new RectF(
                0,
                model.getSurfaceY() - context.getResources().getDimension(R.dimen.debug_height),
                model.getSurfaceX(),
                model.getSurfaceY()
        );
        statusMaskRect = new RectF(
                model.getSurfaceX() - context.getResources().getDimension(R.dimen.status_margin),
                context.getResources().getDimension(R.dimen.status_margin),
                model.getSurfaceX() - context.getResources().getDimension(R.dimen.status_height) - context.getResources().getDimension(R.dimen.status_margin),
                context.getResources().getDimension(R.dimen.status_height) + context.getResources().getDimension(R.dimen.status_margin)
        );
        subStatusMaskRect = new RectF(
                model.getSurfaceX() - context.getResources().getDimension(R.dimen.status_margin),
                context.getResources().getDimension(R.dimen.status_margin),
                model.getSurfaceX() - context.getResources().getDimension(R.dimen.status_height) - context.getResources().getDimension(R.dimen.status_margin),
                model.getSurfaceY()
        );
        dialogMaskRect = new RectF(
                0,
                0,
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
