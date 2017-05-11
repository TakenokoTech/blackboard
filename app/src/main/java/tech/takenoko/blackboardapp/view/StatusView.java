package tech.takenoko.blackboardapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import tech.takenoko.blackboardapp.MainActivity;
import tech.takenoko.blackboardapp.model.StaticModel;

/**
 * Created by たけのこ on 2017/05/07.
 */

public class StatusView extends RelativeLayout {

    Context context;

    public StatusView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setOnClickListener(onClickListener);
    }

    OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (StaticModel.getViewStatus()) {
                case SUB:
                    StaticModel.setViewStatus(StaticModel.ViewStatus.VIEW);
                    break;
                case VIEW:
                    StaticModel.setViewStatus(StaticModel.ViewStatus.SUB);
                    break;
            }
            ((MainActivity)context).upDate();
        }
    };
}
