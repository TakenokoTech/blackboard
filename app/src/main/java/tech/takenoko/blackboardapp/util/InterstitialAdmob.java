package tech.takenoko.blackboardapp.util;

import android.content.Context;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import tech.takenoko.blackboardapp.R;
import tech.takenoko.blackboardapp.model.StaticModel;

/**
 * Created by たけのこ on 2017/05/15.
 */

public class InterstitialAdmob {

    final static String log = "----AdmobActivity----";

    static InterstitialAd mInterstitialAd;
    static int count = 0;

    public static void setup(final Context context) {
        mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdUnitId(context.getString(R.string.banner_ad_unit_id));
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdOpened() {
                StaticModel.setAppStatus(StaticModel.AppStatus.STOP);
            }

            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                StaticModel.setAppStatus(StaticModel.AppStatus.ACTIVE);
            }
        });
        if(count == 0) requestNewInterstitial();
    }

    private static void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("SEE_YOUR_LOGCAT_TO_GET_YOUR_DEVICE_ID")
                .build();
        mInterstitialAd.loadAd(adRequest);
        count++;
    }

    public static void showInterstitial() {
//        mInterstitialAd.show();
    }

}
