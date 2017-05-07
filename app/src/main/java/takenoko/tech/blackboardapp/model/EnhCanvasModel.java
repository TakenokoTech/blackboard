package takenoko.tech.blackboardapp.model;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.util.Log;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by たけのこ on 2017/04/30.
 */

public class EnhCanvasModel {

    final static String log = "----EnhCanvasModel----";

    @Getter @Setter
    Path touchPath = new Path();
    @Getter @Setter
    private static ArrayList<Bitmap> bitmaps = new ArrayList();
    @Getter @Setter
    private static ArrayList<Canvas> canvases = new ArrayList<>();

    public EnhCanvasModel() {}

    public void addCanvas(int width, int height) {
        if(bitmaps.size() > 0) return;
        bitmaps.add(Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888));
        canvases.add(new Canvas(getBitmap(getLength()-1)));
        getCanvas(getLength()-1).drawColor(0, PorterDuff.Mode.CLEAR);
    }

    public static void loadCanvas(ArrayList<Bitmap> arr) {
        bitmaps = new ArrayList();
        canvases = new ArrayList();
        for(int i = 0; i < arr.size(); i++) {
            Bitmap bitmap = arr.get(i).copy(Bitmap.Config.ARGB_8888, true);
            bitmaps.add(bitmap);
            canvases.add(new Canvas(getBitmap(getLength()-1)));
        }
        Log.i(log, "loadCanvas  " + bitmaps.size());
    }

    public static Bitmap getBitmap(int i) {
        return bitmaps.get(i);
    }

    public Canvas getCanvas(int i) {
        return canvases.get(i);
    }

    public static int getLength() {
        return bitmaps.size();
    }

    public static Bitmap printBitmap() {
        Paint paint = new Paint();
        paint.setColor(SettingModel.getBackColorARGB());
        Bitmap bitmap = Bitmap.createBitmap(bitmaps.get(0).getWidth(), bitmaps.get(0).getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawRect(0, 0, bitmap.getWidth(), bitmap.getHeight(), paint);
        canvas.drawBitmap(EnhCanvasModel.getBitmap(0), 0, 0, null);
        return bitmap;
    }
}
