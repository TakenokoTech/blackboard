package takenoko.tech.blackboardapp.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import lombok.Getter;

/**
 * Created by たけのこ on 2017/05/04.
 */

public class StorageModel implements Serializable {

    final static String log = "----StorageModel----";

    @Getter
    private ArrayList<byte[]> mBitmapArray = new ArrayList();

    public StorageModel(ArrayList<Bitmap> bitmaps, ArrayList<Canvas> canvases) {
        if( bitmaps == null || canvases == null ) return;
        try { setByteBitmap(bitmaps); } catch (Exception e) { mBitmapArray = null; }
    }

    public void setByteBitmap(final ArrayList<Bitmap> arrayList) throws IOException {
        mBitmapArray = new ArrayList<byte[]>();
        for(int i = 0; i < arrayList.size(); i++) {
            ByteArrayOutputStream byteos = new ByteArrayOutputStream();
            arrayList.get(i).compress(Bitmap.CompressFormat.PNG, 100, byteos);
            mBitmapArray.add(byteos.toByteArray());
        }
    }

    public ArrayList<Bitmap> getBitmaps() {
        ArrayList<Bitmap> arrayList = new ArrayList<>();
        for(int i = 0; i < mBitmapArray.size(); i++) {
            if (mBitmapArray.get(i) != null && mBitmapArray.get(i).length > 0) {
                arrayList.add(BitmapFactory.decodeByteArray(mBitmapArray.get(i), 0, mBitmapArray.get(i).length));
            }
        }
        return arrayList;
    }
}
