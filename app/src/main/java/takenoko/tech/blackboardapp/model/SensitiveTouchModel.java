package takenoko.tech.blackboardapp.model;

import android.content.Context;
import android.util.Log;

import takenoko.tech.blackboardapp.R;

/**
 * Created by たけのこ on 2017/05/02.
 */

public class SensitiveTouchModel {

    final static String log = "--SensitiveTouchModel--";

    Context context;

    public SensitiveTouchModel(Context context) {
        this.context = context;
    }

    public boolean canceler(float x) {
        boolean bool =  context.getResources().getDimension(R.dimen.menu_list_width) >= x;
        Log.i(log, bool ? "TRUE" : "FALSE");
        return context.getResources().getDimension(R.dimen.menu_list_width) >= x;
    }
}
