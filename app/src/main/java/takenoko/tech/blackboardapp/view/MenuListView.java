package takenoko.tech.blackboardapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import takenoko.tech.blackboardapp.R;
import takenoko.tech.blackboardapp.model.MenuListModel;
import takenoko.tech.blackboardapp.util.ListViewAdapter;

/**
 * Created by たけのこ on 2017/05/01.
 */

public class MenuListView extends ListView {

    final static String log = "----menuListView----";

    MenuListModel model = new MenuListModel();

    public MenuListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        model.setAdapter(new ListViewAdapter(context, R.layout.list_item));
        model.getAdapter().add("ペン", getResources().getDrawable(R.drawable.ic_crayon, null), new OnClickToPen());
        model.getAdapter().add("けしごむ", getResources().getDrawable(R.drawable.ic_blackbord_ere, null), new OnClickToEraser());
        model.getAdapter().add("閉じる", getResources().getDrawable(R.drawable.ic_close, null), new OnClickToClose());
        setAdapter(model.getAdapter());
    }

    class OnClickToPen implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Log.i(log, "OnClickToPen");
        }
    }
    class OnClickToEraser implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Log.i(log, "OnClickToEraser");
        }
    }
    class OnClickToPenSize implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Log.i(log, "OnClickToPenSize");
        }
    }
    class OnClickToClose implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Log.i(log, "OnClickToPenSize");
        }
    }
}
