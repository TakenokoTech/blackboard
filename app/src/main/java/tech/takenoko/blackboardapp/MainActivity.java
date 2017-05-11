package tech.takenoko.blackboardapp;

import android.graphics.Color;
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
import tech.takenoko.blackboardapp.model.EnhCanvasModel;
import tech.takenoko.blackboardapp.model.SettingModel;
import tech.takenoko.blackboardapp.model.StaticModel;
import tech.takenoko.blackboardapp.util.Dialog;
import tech.takenoko.blackboardapp.util.Setting;
import tech.takenoko.blackboardapp.util.UtilStrage;
import tech.takenoko.blackboardapp.view.IOGridView;

import static tech.takenoko.blackboardapp.model.SettingModel.FlameBool.OFF;
import static tech.takenoko.blackboardapp.model.SettingModel.FlameBool.ON;

/**
 * Created by たけのこ on 2017/05/04.
 */

public class MainActivity extends AppCompatActivity {

    final static String log = "----MainActivty----";

    @BindView(R.id.menu_layout) RelativeLayout menuLayout;
    @BindView(R.id.open_menu_image) ImageView openMenuImage;
    @BindView(R.id.open_menu_text) TextView openMenuText;
    //-----------------------------------------------------
    @BindView(R.id.debug_layout) RelativeLayout debugLayout;
    @BindView(R.id.debug_text) TextView debugText;
    @BindView(R.id.save1_button) TextView save1Button;
    @BindView(R.id.load1_button) TextView load1Button;
    @BindView(R.id.save2_button) TextView save2Button;
    @BindView(R.id.load2_button) TextView load2Button;
    //-----------------------------------------------------
    @BindView(R.id.overlay_layout) RelativeLayout overlayLayout;
    //-----------------------------------------------------
    @BindView(R.id.status_layout) RelativeLayout statusLayout;
    @BindView(R.id.status_image) ImageView statusImage;
    @BindView(R.id.status_text) TextView statusText;
    @BindView(R.id.status_sub) LinearLayout statusSub;
    //-----------------------------------------------------
    @BindView(R.id.import_layout) RelativeLayout importLayout;
    @BindView(R.id.import_title) TextView importTitle;
    @BindView(R.id.import_text) TextView importText;
    @BindView(R.id.import_agree) TextView importAgree;
    @BindView(R.id.import_disagree) TextView importDisagree;
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
    @BindView(R.id.setting_flame_on)TextView settingFlameOn;
    @BindView(R.id.setting_flame_off)TextView settingFlameOff;
    @BindView(R.id.setting_close)TextView settingClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(log, "onCreate");
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Dialog.button(this);
        Setting.button(this);
        upDate();
        debug();
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(log, "onResume  " + EnhCanvasModel.getBitmaps().size());
        overlayLayout.setVisibility(View.VISIBLE);
        UtilStrage.load(this, null);
        overlayLayout.setVisibility(View.INVISIBLE);
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
        switch (StaticModel.getIoDialogMode()) {
            case IMPORT:
                IOGridView.update();
                importTitle.setText(getResources().getString(R.string.import_title));
                importText.setText(getResources().getString(R.string.import_text));
                importAgree.setText(getResources().getString(R.string.import_agree));
                importDisagree.setText(getResources().getString(R.string.import_disagree));
                importLayout.setVisibility(View.VISIBLE);
                break;
            case EXPORT:
                IOGridView.update();
                importTitle.setText(getResources().getString(R.string.export_title));
                importText.setText(getResources().getString(R.string.export_text));
                importAgree.setText(getResources().getString(R.string.export_agree));
                importDisagree.setText(getResources().getString(R.string.export_disagree));
                importLayout.setVisibility(View.VISIBLE);
                break;
            default:
                importLayout.setVisibility(View.INVISIBLE);
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
                debugLayout.setVisibility(View.INVISIBLE);
                openMenuImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu));
                openMenuText.setText(getResources().getString(R.string.menu_open_button));
                break;
            default:
                menuLayout.setVisibility(View.VISIBLE);
                debugLayout.setVisibility(View.VISIBLE);
                openMenuImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_close));
                openMenuText.setText(getResources().getString(R.string.menu_close_button));
                break;
        }
        switch (StaticModel.getSettingMode()) {
            case VIEW:
                settingLayout.setVisibility(View.VISIBLE);
                settingFlameOff.setBackgroundColor(Color.argb(128, 216, 216, 216));
                settingFlameOn.setBackgroundColor(Color.argb(128, 216, 216, 216));
                settingFlameOff.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { SettingModel.setFlameBool(OFF); upDate();}});
                settingFlameOn.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { SettingModel.setFlameBool(ON); upDate();}});
                if(SettingModel.getFlameBool() == OFF) {
                    settingFlameOff.setBackgroundColor(Color.argb(216, 128, 128, 128));
                } else {
                    settingFlameOn.setBackgroundColor(Color.argb(216, 192, 0, 0));
                }
                break;
            case NONE:
                settingLayout.setVisibility(View.INVISIBLE);
                break;
        }
    }

    private void debug() {
        overlayLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {}
        });
        save1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                overlayLayout.setVisibility(View.VISIBLE);
                UtilStrage.store(getBaseContext(), "storageModel1.obj");
                overlayLayout.setVisibility(View.INVISIBLE);
            }
        });
        load1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                overlayLayout.setVisibility(View.VISIBLE);
                UtilStrage.load(getBaseContext(), "storageModel1.obj");
                overlayLayout.setVisibility(View.INVISIBLE);
            }
        });
        save2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                overlayLayout.setVisibility(View.VISIBLE);
                UtilStrage.store(getBaseContext(), "storageModel2.obj");
                overlayLayout.setVisibility(View.INVISIBLE);
            }
        });
        load2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                overlayLayout.setVisibility(View.VISIBLE);
                UtilStrage.load(getBaseContext(), "storageModel2.obj");
                overlayLayout.setVisibility(View.INVISIBLE);
            }
        });
    }
}
