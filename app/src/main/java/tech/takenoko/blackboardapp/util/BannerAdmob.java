package tech.takenoko.blackboardapp.util;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import tech.takenoko.blackboardapp.MainActivity;
import tech.takenoko.blackboardapp.R;

/**
 * Created by たけのこ on 2017/05/14.
 */

public class BannerAdmob {

    final static String log = "----BannerAdmob----";

    static AdView mAdView;

    public static void setup(final Context context) {
        MobileAds.initialize(context, context.getString(R.string.banner_ad_unit_id));
        mAdView = (AdView) ((MainActivity)context).findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int i) {
                switch (i) {
                    case AdRequest.ERROR_CODE_INTERNAL_ERROR:
                        Log.i(log, "ERROR_CODE_INTERNAL_ERROR");
                        break;
                    case AdRequest.ERROR_CODE_INVALID_REQUEST:
                        Log.i(log, "ERROR_CODE_INVALID_REQUEST");
                        break;
                    case AdRequest.ERROR_CODE_NETWORK_ERROR:
                        Log.i(log, "ERROR_CODE_NETWORK_ERROR");
                        break;
                    case AdRequest.ERROR_CODE_NO_FILL:
                        Log.i(log, "ERROR_CODE_NO_FILL");
                        break;
                }
            }
            @Override
            public void onAdLoaded() {
                Log.i(log, "onAdLoaded");
            }
        });
    }
}
