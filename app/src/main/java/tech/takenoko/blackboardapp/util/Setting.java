package tech.takenoko.blackboardapp.util;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import tech.takenoko.blackboardapp.MainActivity;
import tech.takenoko.blackboardapp.R;
import tech.takenoko.blackboardapp.model.StaticModel;


/**
 * Created by たけのこ on 2017/05/06.
 */

public class Setting {

    final static String log = "----Setting----";

    public static void button(final Context context) {
        TextView closeButton = (TextView) ((MainActivity)context).findViewById(R.id.setting_close);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(log, "OnClickToClose");
                clickToClose();
                ((MainActivity)context).upDate();
            }
        });
    }
    private static void clickToClose() {
        StaticModel.setSettingMode(StaticModel.SettingMode.NONE);
    }
}
