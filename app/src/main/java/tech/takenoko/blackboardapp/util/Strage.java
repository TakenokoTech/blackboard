package tech.takenoko.blackboardapp.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import tech.takenoko.blackboardapp.model.EnhCanvasModel;
import tech.takenoko.blackboardapp.model.StorageModel;

/**
 * Created by たけのこ on 2017/05/06.
 */

public class Strage {

    final static String log = "----UtilStrage----";

    /** ファイル名 */
    private final static String FILE_NAME = "storageModel.obj";

    /** 保存 */
    public static boolean store(Context context, String fileName) {
        if(fileName == null) fileName = FILE_NAME;
        StorageModel storageModel = new StorageModel(EnhCanvasModel.getBitmaps(), EnhCanvasModel.getCanvases());
        try {
            ObjectOutputStream out = new ObjectOutputStream(context.openFileOutput(fileName, Context.MODE_PRIVATE));
            out.writeObject(storageModel);
            out.close();
            Log.d(log, "store  " + storageModel.getMBitmapArray().size());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(log, "bad store");
            return false;
        }
    }

    /** 読み込み */
    public static boolean load(Context context, String fileName) {
        if(fileName == null) fileName = FILE_NAME;
        StorageModel storageModel = null;
        try {
            ObjectInputStream in = new ObjectInputStream(context.openFileInput(fileName));
            storageModel = (StorageModel) in.readObject();
            in.close();
            if(storageModel == null || storageModel.getBitmaps() == null || storageModel.getBitmaps().size() < 1) return false;
            EnhCanvasModel.loadCanvas(storageModel.getBitmaps());
            Log.d(log, "load  " + storageModel.getMBitmapArray().size());
            return true;
        } catch (Exception e) {
            Log.d(log, "bad load");
            return false;
        }
    }

    /** 削除 */
    public static boolean delete(Context context, String fileName) {
        if(fileName == null) fileName = FILE_NAME;
        try {
            context.deleteFile(fileName);
            Log.d(log, "delete  " + fileName);
            return true;
        } catch (Exception e) {
            Log.d(log, "bad delete");
            return false;
        }
    }

    public static Bitmap get(Context context, String fileName) {
        if(fileName == null) fileName = FILE_NAME;
        try {
            ObjectInputStream in = new ObjectInputStream(context.openFileInput(fileName));
            StorageModel storageModel = (StorageModel) in.readObject();
            in.close();
            if(storageModel == null || storageModel.getBitmaps() == null || storageModel.getBitmaps().size() < 1) return null;
            Log.d(log, "load  " + storageModel.getMBitmapArray().size());
            return storageModel.getBitmaps().get(0);
        } catch (Exception e) {
            Log.d(log, "bad load");
            return null;
        }
    }
}
