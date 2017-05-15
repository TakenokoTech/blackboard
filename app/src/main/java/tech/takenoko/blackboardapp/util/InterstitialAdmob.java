package tech.takenoko.blackboardapp.util;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import tech.takenoko.blackboardapp.MainActivity;
import tech.takenoko.blackboardapp.R;
import tech.takenoko.blackboardapp.activity.SubActivity;
import tech.takenoko.blackboardapp.model.EnhCanvasModel;

/**
 * Created by たけのこ on 2017/05/15.
 */

public class InterstitialAdmob {

    final static String log = "----InterAdmob----";
    static final boolean EmulatorTest = true;

    private static boolean loaderdFlag = false;
    private static String id = null;
    private static InterstitialAd mInterstitialAd;
    private static AdRequest adRequest;

    public static void setup(final Context context) {
        if(EmulatorTest){
            id = context.getString(R.string.banner_ad_test_id);
        } else{
            id = context.getString(R.string.banner_ad_unit_id);
        }
        mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdUnitId(id);
        if(EmulatorTest) {
            adRequest = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .build();
        } else {
            adRequest = new AdRequest.Builder().build();
        }
        mInterstitialAd.loadAd(adRequest);
    }
    public static void showInterstitial(SubActivity activity) {
        mInterstitialAd.setAdListener(new OnAdListener(activity));
        if(mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            startMainActivity(activity);
        }
    }
    private static void startMainActivity(SubActivity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    private static class OnAdListener extends AdListener {

        private SubActivity activity;

        public OnAdListener(SubActivity activity) {
            this.activity = activity;
        }
        @Override
        public void onAdOpened() {
            Log.i(log, "onAdOpened");
        }
        @Override
        public void onAdClosed() {
            Log.i(log, "onAdClosed");
            mInterstitialAd = null;
            adRequest = null;
            EnhCanvasModel.clean();
            startMainActivity(activity);
        }
        @Override
        public void onAdFailedToLoad(int i) {
            loaderdFlag = false;
            Log.i(log, "onAdFailedToLoad");
        }
        @Override
        public void onAdLoaded() {
            loaderdFlag = true;
            Log.i(log, "onAdLoaded");
        }
        @Override
        public void onAdLeftApplication() {
            Log.i(log, "onAdLeftApplication");
        }
    }
}
