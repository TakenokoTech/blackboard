package takenoko.tech.blackboardapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import takenoko.tech.blackboardapp.R;
import takenoko.tech.blackboardapp.model.MenuListModel;
import takenoko.tech.blackboardapp.model.StaticModel;
import takenoko.tech.blackboardapp.util.Debuger;
import takenoko.tech.blackboardapp.util.ListViewAdapter;

/**
 * Created by たけのこ on 2017/05/01.
 */

public class MenuListView extends ListView {

    final static String log = "----menuListView----";

    Context context;
    MenuListModel model = new MenuListModel();

    public MenuListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        model.setAdapter(new ListViewAdapter(context, R.layout.list_item));
        model.getAdapter().add("ペン", getResources().getDrawable(R.drawable.ic_crayon), new OnClickToPen());
        model.getAdapter().add("けしごむ", getResources().getDrawable(R.drawable.ic_blackbord_ere), new OnClickToEraser());
        model.getAdapter().add("さくじょ", getResources().getDrawable(R.drawable.ic_garbage_can), new OnClickToClear());
        model.getAdapter().add("ほぞん", getResources().getDrawable(R.drawable.ic_download), new OnClickToDownload());
        setAdapter(model.getAdapter());
    }

    private class OnClickToPen implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Log.i(log, "OnClickToPen");
            StaticModel.setPenMode(StaticModel.PenMode.PEN);
        }
    }
    private class OnClickToEraser implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Log.i(log, "OnClickToEraser");
            StaticModel.setPenMode(StaticModel.PenMode.ERASER);
        }
    }
    private class OnClickToPenSize implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Log.i(log, "OnClickToPenSize");
        }
    }
    private class OnClickToClear implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Log.i(log, "OnClickToClear");
            StaticModel.setClearMode(StaticModel.ClearMode.CLEAR);
        }
    }
    private class OnClickToDownload implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Log.i(log, "OnClickToDownload");
            Debuger.print(context, "OnClickToDownload");
        }
    }
    private class OnClickToClose implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Log.i(log, "OnClickToPenSize");
        }
    }
}
