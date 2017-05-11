package tech.takenoko.blackboardapp.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import tech.takenoko.blackboardapp.R;
import tech.takenoko.blackboardapp.model.SettingModel;

import static tech.takenoko.blackboardapp.model.SettingModel.BackColor.BLACK_BOARD;
import static tech.takenoko.blackboardapp.model.SettingModel.BackColor.GREEN_BOARD;
import static tech.takenoko.blackboardapp.model.SettingModel.BackColor.WHITE_BOARD;

/**
 * Created by たけのこ on 2017/05/06.
 */

public class SettingColorList extends LinearLayout {

    final static String log = "----SettingColorList----";

    private SettingModel.BackColor colorArray[] = {BLACK_BOARD, GREEN_BOARD, WHITE_BOARD};

    Context context;

    public SettingColorList(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        update();
    }

    private void update() {
        removeAllViews();
        ArrayList<View> array = new ArrayList<>();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (int i = 0; i < colorArray.length; i++) {
            View view = inflater.inflate(R.layout.list_setting_color, this, false);
            TextView textView = (TextView) view.findViewById(R.id.list_setting_text);
            TextView focusView = (TextView) view.findViewById(R.id.list_setting_focus);
            textView.setText(colorArray[i].name());
            textView.setBackgroundColor(SettingModel.getBackColorHashMap().get(colorArray[i]));
            if(SettingModel.getBackColor() == colorArray[i]) {
                focusView.setBackgroundColor(Color.argb(153, 255, 0, 0));
            } else {
                focusView.setBackgroundColor(Color.argb(153, 51, 51, 51));
            }
            final int index = i;
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    SettingModel.setBackColor(colorArray[index]);
                    update();
                }
            });
            array.add(view);
            addView(array.get(i));
        }
    }

}

