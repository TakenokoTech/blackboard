package tech.takenoko.blackboardapp.util;

import android.content.Context;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import tech.takenoko.blackboardapp.MainActivity;
import tech.takenoko.blackboardapp.R;

/**
 * Created by たけのこ on 2017/05/04.
 */

public class Debuger {

    private static int line = 0;
    private static String[] log = {"", "", "", "", "", "", "", "", "", ""};

    public static void print(final Context context, final String text) {
        String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
        log[0] = date + " | " + String.format("%05d", line++) + " | " + text + "\n" + log[0];
        if ((line % 10) == 0) {
            for (int i = 5; i >= 0; i--) log[i + 1] = log[i];
            log[0] = "";
        }
        final String l = log[0] + log[1] + log[2] + log[3] + log[4];
        ((MainActivity) context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView debugText = (TextView) ((MainActivity) context).findViewById(R.id.debug_text);
                debugText.setText(l);
            }
        });
    }

}
