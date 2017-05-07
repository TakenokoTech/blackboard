package takenoko.tech.blackboardapp.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ShareCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import takenoko.tech.blackboardapp.MainActivity;
import takenoko.tech.blackboardapp.R;
import takenoko.tech.blackboardapp.model.EnhCanvasModel;
import takenoko.tech.blackboardapp.model.StaticModel;

/**
 * Created by たけのこ on 2017/05/06.
 */

public class Dialog {

    final static String log = "----DialogView----";

    public static void button(final Context context) {
        TextView agreeButton = (TextView) ((MainActivity)context).findViewById(R.id.dialog_agree);
        TextView disagreeButton = (TextView) ((MainActivity)context).findViewById(R.id.dialog_disagree);

        agreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(log, "OnClickToAgree");
                switch(StaticModel.getDialogMode()) {
                    case SHARE: clickToShare(ClickAction.AGREE, (Activity)context); break;
                    case CLEAR: clickToClear(ClickAction.AGREE, (Activity)context); break;
                }
                ((MainActivity)context).upDate();
            }
        });

        disagreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(log, "OnClickToDisagree");
                switch(StaticModel.getDialogMode()) {
                    case SHARE: clickToShare(ClickAction.DISAGREE, (Activity)context); break;
                    case CLEAR: clickToClear(ClickAction.DISAGREE, (Activity)context); break;
                }
                ((MainActivity)context).upDate();
            }
        });
    }

    enum ClickAction {AGREE, DISAGREE}
    private static void clickToShare(ClickAction action, Activity activity) {
        if(action == ClickAction.AGREE) {
            try {
                File downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                File imageFile = new File(downloadDir.getPath() + "/cache.jpg");
                FileOutputStream out = new FileOutputStream(imageFile);
                boolean res = EnhCanvasModel.printBitmap().compress(Bitmap.CompressFormat.PNG, 100, out);
                // boolean res = EnhCanvas.getBitmap(0).compress(Bitmap.CompressFormat.PNG, 100, out);
                Debuger.print((Context) activity, Uri.fromFile(imageFile).getPath() + "::" + res);
                ShareCompat.IntentBuilder builder = ShareCompat.IntentBuilder.from(activity);
                builder.setChooserTitle("setChooserTitle")
                        .setSubject("setSubject")
                        .setText("setText")
                        .setStream(Uri.fromFile(imageFile))
                        .setType("image/png")
                        .startChooser();
            } catch (IOException e) {
            }
        }
        StaticModel.setDialogMode(StaticModel.DialogMode.NONE);
    }
    private static void clickToClear(ClickAction action, Activity activity) {
        if(action == ClickAction.AGREE) {
            StaticModel.setClearMode(StaticModel.ClearMode.CLEAR);
        }
        StaticModel.setDialogMode(StaticModel.DialogMode.NONE);
    }
}
