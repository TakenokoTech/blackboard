package tech.takenoko.blackboardapp.util;

import android.content.Context;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import tech.takenoko.blackboardapp.MainActivity;
import tech.takenoko.blackboardapp.R;

/**
 * Created by たけのこ on 2017/05/14.
 */

public class BannerAdmob {

    static AdView mAdView;

    public static void setup(final Context context) {
        MobileAds.initialize(context, context.getString(R.string.banner_ad_unit_id));
        mAdView = (AdView) ((MainActivity)context).findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
}
