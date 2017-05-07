package takenoko.tech.blackboardapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import takenoko.tech.blackboardapp.model.StaticModel;
import takenoko.tech.blackboardapp.util.Debuger;
import takenoko.tech.blackboardapp.util.Dialog;
import takenoko.tech.blackboardapp.model.EnhCanvasModel;
import takenoko.tech.blackboardapp.util.Setting;
import takenoko.tech.blackboardapp.util.UtilStrage;

/**
 * Created by たけのこ on 2017/05/04.
 */

public class MainActivity extends AppCompatActivity {

    final static String log = "----MainActivty----";

    @BindView(R.id.menu_layout) RelativeLayout menuLayout;
    @BindView(R.id.open_menu_image) ImageView openMenuImage;
    @BindView(R.id.open_menu_text) TextView openMenuText;
    //-----------------------------------------------------
    @BindView(R.id.debug_text) TextView debugText;
    //-----------------------------------------------------
    @BindView(R.id.status_layout) RelativeLayout statusLayout;
    @BindView(R.id.status_image) ImageView statusImage;
    @BindView(R.id.status_text) TextView statusText;
    @BindView(R.id.status_sub) LinearLayout statusSub;
    //-----------------------------------------------------
    @BindView(R.id.dialog_layout)RelativeLayout dialogLayout;
    @BindView(R.id.dialog_title)TextView dialogTitle;
    @BindView(R.id.dialog_image)ImageView dialogImage;
    @BindView(R.id.dialog_text)TextView dialogText;
    @BindView(R.id.dialog_agree)TextView dialogAgreeButton;
    @BindView(R.id.dialog_disagree)TextView dialogDisagreeButton;
    //-----------------------------------------------------
    @BindView(R.id.setting_layout) RelativeLayout settingLayout;
    @BindView(R.id.setting_title)TextView settingTitle;
    @BindView(R.id.setting_background)TextView settingBackground;
    @BindView(R.id.setting_close)TextView settingClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(log, "onCreate");
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Debuger.button(this);
        Dialog.button(this);
        Setting.button(this);
        upDate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(log, "onResume  " + EnhCanvasModel.getBitmaps().size());
        UtilStrage.load(this, null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(log, "onDestroy  " + EnhCanvasModel.getBitmaps().size());
        UtilStrage.store(this, null);
    }

    public void upDate() {
        switch (StaticModel.getPenMode()) {
            case PEN:
                statusImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_crayon));
                statusImage.setAlpha(100);
                statusText.setText(getResources().getString(R.string.menu_pen));
                break;
            case ERASER:
                statusImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_blackbord_ere));
                statusImage.setAlpha(100);
                statusText.setText(getResources().getString(R.string.menu_eraser));
                break;
            default:
                break;
        }
        switch (StaticModel.getDialogMode()) {
            case SHARE:
                dialogLayout.setVisibility(View.VISIBLE);
                dialogImage.setVisibility(View.VISIBLE);
                dialogTitle.setText(getResources().getString(R.string.dialog_share_title));
                dialogText.setText(getResources().getString(R.string.dialog_share_text));
                dialogAgreeButton.setText(getResources().getString(R.string.dialog_share_agree));
                dialogDisagreeButton.setText(getResources().getString(R.string.dialog_share_disagree));
                dialogImage.setImageBitmap(EnhCanvasModel.printBitmap());
                dialogImage.setMaxHeight((int) getResources().getDimension(R.dimen.dialog_image_height));
                break;
            case CLEAR:
                dialogLayout.setVisibility(View.VISIBLE);
                dialogImage.setVisibility(View.GONE);
                dialogTitle.setText(getResources().getString(R.string.dialog_clear_title));
                dialogText.setText(getResources().getString(R.string.dialog_clear_text));
                dialogAgreeButton.setText(getResources().getString(R.string.dialog_clear_agree));
                dialogDisagreeButton.setText(getResources().getString(R.string.dialog_clear_disagree));
                break;
            default:
                dialogLayout.setVisibility(View.INVISIBLE);
                dialogImage.setVisibility(View.GONE);
                break;
        }
        switch (StaticModel.getViewStatus()) {
            case VIEW:
                statusLayout.setVisibility(View.VISIBLE);
                statusSub.setVisibility(View.INVISIBLE);
                break;
            case SUB:
                statusLayout.setVisibility(View.VISIBLE);
                statusSub.setVisibility(View.VISIBLE);
                break;
            default:
                statusSub.setVisibility(View.INVISIBLE);
                break;
        }
        switch (StaticModel.getMenuMode()) {
            case INVISIBLE:
                statusLayout.setVisibility(View.INVISIBLE);
                statusSub.setVisibility(View.INVISIBLE);
                menuLayout.setVisibility(View.INVISIBLE);
                openMenuImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu));
                openMenuText.setText(getResources().getString(R.string.menu_open_button));
                break;
            default:
                menuLayout.setVisibility(View.VISIBLE);
                openMenuImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_close));
                openMenuText.setText(getResources().getString(R.string.menu_close_button));
                break;
        }
        switch (StaticModel.getSettingMode()) {
            case VIEW:
                settingLayout.setVisibility(View.VISIBLE);
                break;
            case NONE:
                settingLayout.setVisibility(View.INVISIBLE);
                break;
        }
    }
}
