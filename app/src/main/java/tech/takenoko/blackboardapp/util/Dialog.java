package tech.takenoko.blackboardapp.util;

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

import tech.takenoko.blackboardapp.MainActivity;
import tech.takenoko.blackboardapp.R;
import tech.takenoko.blackboardapp.model.EnhCanvasModel;
import tech.takenoko.blackboardapp.model.StaticModel;
import tech.takenoko.blackboardapp.view.IOGridView;

/**
 * Created by たけのこ on 2017/05/06.
 */

public class Dialog {

    final static String log = "----DialogView----";

    public static void button(final Context context) {
        TextView agreeButton = (TextView) ((MainActivity)context).findViewById(R.id.dialog_agree);
        TextView disagreeButton = (TextView) ((MainActivity)context).findViewById(R.id.dialog_disagree);
        TextView importAgreeButton = (TextView) ((MainActivity)context).findViewById(R.id.import_agree);
        TextView importDisagreeButton = (TextView) ((MainActivity)context).findViewById(R.id.import_disagree);
        TextView importDeleteButton = (TextView) ((MainActivity)context).findViewById(R.id.import_delete);


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

        importAgreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(log, "OnClickToImportAgree");
                clickToImport(ClickAction.AGREE, (Activity)context);
                ((MainActivity)context).upDate();
            }
        });
        importDisagreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(log, "OnClickToImportDisagree");
                clickToImport(ClickAction.DISAGREE, (Activity)context);
                ((MainActivity)context).upDate();
            }
        });
        importDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(log, "OnClickToImportDelete");
                clickToImport(ClickAction.OTHER, (Activity)context);
                ((MainActivity)context).upDate();
            }
        });
    }

    enum ClickAction {AGREE, DISAGREE, OTHER}
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
    private static void clickToImport(ClickAction action, Activity activity) {
        if(action == ClickAction.AGREE && StaticModel.getIoDialogMode() == StaticModel.IoDialogMode.IMPORT) {
            Strage.load(activity, IOGridView.getSelectFileName());
            loadAdmob(activity);
        }
        if(action == ClickAction.AGREE && StaticModel.getIoDialogMode() == StaticModel.IoDialogMode.EXPORT) {
            Strage.store(activity, IOGridView.getSelectFileName());
            loadAdmob(activity);
        }
        if(action == ClickAction.OTHER) {
            Strage.delete(activity, IOGridView.getSelectFileName());
        }
        StaticModel.setIoDialogMode(StaticModel.IoDialogMode.NONE);
    }

    //
    private static void loadAdmob(Activity activity) {
//        StaticModel.setAppStatus(StaticModel.AppStatus.STOP);
//        Intent intent = new Intent(activity, SubActivity.class);
//        activity.startActivity(intent);
//        activity.finish();
    }
}
