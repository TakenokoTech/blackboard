package tech.takenoko.blackboardapp.activity;

import android.app.Activity;
import android.os.Bundle;

import tech.takenoko.blackboardapp.R;
import tech.takenoko.blackboardapp.model.StaticModel;
import tech.takenoko.blackboardapp.util.InterstitialAdmob;

/**
 * Created by たけのこ on 2017/05/15.
 */

public class SubActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        InterstitialAdmob.showInterstitial(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        StaticModel.setAppStatus(StaticModel.AppStatus.ACTIVE);
    }
}