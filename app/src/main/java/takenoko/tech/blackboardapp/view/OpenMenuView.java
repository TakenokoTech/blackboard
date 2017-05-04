package takenoko.tech.blackboardapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import takenoko.tech.blackboardapp.MainActivity;
import takenoko.tech.blackboardapp.model.StaticModel;

/**
 * Created by たけのこ on 2017/05/04.
 */

public class OpenMenuView extends RelativeLayout {

    MainActivity activity;

    public OpenMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(new OnClickToButton());
        activity = (MainActivity)context;
    }

    class OnClickToButton implements OnClickListener {
        @Override
        public void onClick(View view) {
            if(StaticModel.getMenuMode() != StaticModel.MenuMode.INVISIBLE) {
                StaticModel.setMenuMode(StaticModel.MenuMode.INVISIBLE);
            } else {
                StaticModel.setMenuMode(StaticModel.MenuMode.PEN_COLOR);
            }
            activity.upDate();
        }
    }
}
