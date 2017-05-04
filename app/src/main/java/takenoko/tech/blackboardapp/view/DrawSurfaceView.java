package takenoko.tech.blackboardapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import takenoko.tech.blackboardapp.model.DrawSurfaceModel;
import takenoko.tech.blackboardapp.model.SensitiveTouchModel;
import takenoko.tech.blackboardapp.model.StaticModel;
import takenoko.tech.blackboardapp.util.EnhCanvas;

/**
 * Created by たけのこ on 2017/04/29.
 */

public class DrawSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    final static String log = "----DrawSurfaceView----";

    // モデル
    DrawSurfaceModel model = new DrawSurfaceModel();
    SensitiveTouchModel sens;
    EnhCanvas eCanvas = new EnhCanvas();

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
            canvas.drawRect(sens.getMenuMasKRect(), sens.getEraserRect());
        }
        if(StaticModel.getDebugMode() == StaticModel.DebugMode.VIEW) {
            canvas.drawRect(sens.getDebugerMasKRect(), sens.getEraserRect());
        }
        if(StaticModel.getViewStatus() == StaticModel.ViewStatus.VIEW) {
            canvas.drawOval(sens.getStatusMasKRect(), sens.getEraserRect());
        }
        return canvas;
    }
    // イベント
    private void doTouchEvent() {


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
            Canvas canvas = holder.lockCanvas();
            canvas.drawColor(0,PorterDuff.Mode.CLEAR);
            if(canvas == null) continue;
            doDraw();
            for(int i = 0; i < eCanvas.getLength(); i++) {
                canvas.drawBitmap(eCanvas.getBitmap(i), 0, 0, null);
            }
            canvas = doMask(canvas);
            holder.unlockCanvasAndPost(canvas);
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(log, event.getX() + "," + event.getY());
        float x = event.getX();
        float y = event.getY();
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
        holder = surfaceHolder;
        thread = new Thread(this);
    }
    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {
        Log.i(log, "surfaceChanged");
        model.setSurfaceX(width);
        model.setSurfaceY(height);
        if (thread != null) thread.start();
        eCanvas.addCanvas(width, height);
        sens.settingMask(model);
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        Log.i(log, "surfaceDestroyed");
    }
}
