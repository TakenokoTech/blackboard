package tech.takenoko.blackboardapp.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import tech.takenoko.blackboardapp.MainActivity;
import tech.takenoko.blackboardapp.model.DrawSurfaceModel;
import tech.takenoko.blackboardapp.model.EnhCanvasModel;
import tech.takenoko.blackboardapp.model.SensitiveTouchModel;
import tech.takenoko.blackboardapp.model.StaticModel;

/**
 * Created by たけのこ on 2017/04/29.
 */

public class DrawSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    final static String log = "----DrawSurfaceView----";

    // モデル
    private DrawSurfaceModel model = new DrawSurfaceModel();
    private SensitiveTouchModel sens;
    private static EnhCanvasModel eCanvas = new EnhCanvasModel();

    // スレッドクラス
    private SurfaceHolder holder;
    Thread thread = null;

    // 描画
    private void doDraw() {
        // クリア
        if(StaticModel.getClearMode() == StaticModel.ClearMode.CLEAR) {
            clear();
        }
        // ライン描画
        sens.calcLine();
        switch (StaticModel.getPenMode()) {
            case PEN:
                eCanvas.getCanvas(0).drawPath(eCanvas.getTouchPath(), sens.getPenLine());
//                eCanvas.getCanvas(0).drawPath(eCanvas.getTouchPath(), sens.getBlurPanLine());
                break;
            case ERASER:
                eCanvas.getCanvas(0).drawPath(eCanvas.getTouchPath(), sens.getEraserLine());
                break;
        }
    }
    // 不要な表示のマスク
    private Canvas doMask(Canvas canvas) {
        if(StaticModel.getMenuMode() != StaticModel.MenuMode.INVISIBLE) {
            canvas.drawRect(sens.getMenuMaskRect(), sens.getEraserRect());
        }
        if(StaticModel.getMenuMode() != StaticModel.MenuMode.INVISIBLE) {
            canvas.drawRect(sens.getDebugerMaskRect(), sens.getEraserRect());
        }
        if(StaticModel.getMenuMode() == StaticModel.MenuMode.VISIBLE &&
                StaticModel.getViewStatus() != StaticModel.ViewStatus.NONE) {
            canvas.drawOval(sens.getStatusMaskRect(), sens.getEraserRect());
        }
        if(StaticModel.getMenuMode() == StaticModel.MenuMode.VISIBLE &&
                StaticModel.getViewStatus() == StaticModel.ViewStatus.SUB) {
            canvas.drawRect(sens.getSubStatusMaskRect(), sens.getEraserRect());
        }
        if(StaticModel.getIoDialogMode() != StaticModel.IoDialogMode.NONE) {
            canvas.drawRect(sens.getFullMaskRect(), sens.getEraserRect());
        }
        if(StaticModel.getDialogMode() != StaticModel.DialogMode.NONE) {
            canvas.drawRect(sens.getFullMaskRect(), sens.getEraserRect());
        }
        if(StaticModel.getSettingMode() != StaticModel.SettingMode.NONE) {
            canvas.drawRect(sens.getFullMaskRect(), sens.getEraserRect());
        }
        return canvas;
    }
    // シェア
    public static void share(Activity activity) {
        StaticModel.setDialogMode(StaticModel.DialogMode.SHARE);
        ((MainActivity)activity).upDate();
    }
    // クリア
    public void clear() {
        eCanvas.getCanvas(0).drawColor(0,PorterDuff.Mode.CLEAR);
        StaticModel.setClearMode(StaticModel.ClearMode.NONE);
    }
    // コンストラクタ
    public DrawSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i(log, "DrawSurfaceView()");
        // 半透明を設定
        getHolder().setFormat(PixelFormat.TRANSLUCENT);
        // コールバック登録
        getHolder().addCallback(this);
        // フォーカス可
        setFocusable(true);
        // このViewをトップにする
        setZOrderOnTop(true);
        // 初期化
        sens = new SensitiveTouchModel(context);
    }
    @Override
    public void run() {
        while (true) {
            if(holder == null || StaticModel.getAppStatus() == StaticModel.AppStatus.STOP) continue;
            Canvas canvas = null;
            try {
                canvas = holder.lockCanvas();
                synchronized (holder) {
                    if (canvas == null) continue;
                    canvas.drawColor(0, PorterDuff.Mode.CLEAR);
                    doDraw();
                    for (int i = 0; i < eCanvas.getLength(); i++) {
                        canvas.drawBitmap(eCanvas.getBitmap(i), 0, 0, null);
                    }
                    canvas = doMask(canvas);
                }
            } finally {
                if(canvas != null) holder.unlockCanvasAndPost(canvas);
            }
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        if(StaticModel.getDialogMode() != StaticModel.DialogMode.NONE) return true;
        if(StaticModel.getSettingMode() != StaticModel.SettingMode.NONE) return true;
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                eCanvas.getTouchPath().moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                eCanvas.getTouchPath().lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP:
                eCanvas.getTouchPath().lineTo(x, y);
                eCanvas.getTouchPath().reset();
                break;
        }
        return true;
    }
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.i(log, "surfaceCreated");
        holder = surfaceHolder;
        thread = new Thread(this);
    }
    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {
        Log.i(log, "surfaceChanged");
        model.setSurfaceX(width);
        model.setSurfaceY(height);
        if (thread != null) thread.start();
        if (eCanvas.getLength() == 0) eCanvas.addCanvas(width, height);
        sens.settingMask(model);
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        Log.i(log, "surfaceDestroyed");
    }
}
