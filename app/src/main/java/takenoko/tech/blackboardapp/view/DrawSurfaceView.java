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
        eCanvas.getCanvas(0).drawColor(0,PorterDuff.Mode.CLEAR);
        // 円を描画する
        //eCanvas.getCanvas(0).drawCircle(model.getPenX(), model.getPenY(), 10, model.getRedLine());
        // 画面の領域を超えた？
        if (model.getPenX() < 0 || model.getSurfaceX() < model.getPenX()) model.setPenDx(-model.getPenDx());
        if (model.getPenY() < 0 || model.getSurfaceY() < model.getPenY()) model.setPenDy(-model.getPenDy());
        // 円の座標を移動させる
        model.setPenX(model.getPenX() + model.getPenDx());
        model.setPenY(model.getPenY() + model.getPenDy());
        // ライン描画
        eCanvas.getCanvas(0).drawPath(eCanvas.getTouchPath(), model.getPenLine());
        //eCanvas.getCanvas(0).drawPath(eCanvas.getTouchPath(), model.getBlurPanLine());
    }

    // イベント
    private void doTouchEvent() {


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
            //Log.i(log, "run");
            Canvas canvas = holder.lockCanvas();
            canvas.drawColor(0,PorterDuff.Mode.CLEAR);
            if(canvas == null) continue;
            doDraw();
            for(int i = 0; i < eCanvas.getLength(); i++) {
                canvas.drawBitmap(eCanvas.getBitmap(i), 0, 0, null);
            }
            holder.unlockCanvasAndPost(canvas);
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(log, event.getX() + "," + event.getY());
        float x = event.getX();
        float y = event.getY();
        if(sens.canceler(x)) {
            return true;
        }
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                eCanvas.getTouchPath().moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                eCanvas.getTouchPath().lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP:
                eCanvas.getTouchPath().lineTo(x, y);
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
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        Log.i(log, "surfaceDestroyed");
    }
}
