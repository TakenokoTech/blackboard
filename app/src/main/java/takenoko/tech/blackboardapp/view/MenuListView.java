package takenoko.tech.blackboardapp.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import takenoko.tech.blackboardapp.MainActivity;
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
        model.getAdapter().add(getResources().getString(R.string.menu_pen), getResources().getDrawable(R.drawable.ic_crayon), new OnClickToPen());
        model.getAdapter().add(getResources().getString(R.string.menu_eraser), getResources().getDrawable(R.drawable.ic_blackbord_ere), new OnClickToEraser());
        model.getAdapter().add(getResources().getString(R.string.menu_clear), getResources().getDrawable(R.drawable.ic_garbage_can), new OnClickToClear());
        // model.getAdapter().add("ほぞん", getResources().getDrawable(R.drawable.ic_download), new OnClickToDownload());
        model.getAdapter().add(getResources().getString(R.string.menu_share), getResources().getDrawable(R.drawable.ic_share), new OnClickToShare());
        // model.getAdapter().add(getResources().getString(R.string.menu_import), getResources().getDrawable(R.drawable.ic_image), new OnClickToImport());
        model.getAdapter().add(getResources().getString(R.string.menu_setting), getResources().getDrawable(R.drawable.ic_setting), new OnClickToSetting());
        setAdapter(model.getAdapter());
    }

    private class OnClickToPen implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Log.i(log, "OnClickToPen");
            StaticModel.setPenMode(StaticModel.PenMode.PEN);
            ((MainActivity)context).upDate();
        }
    }
    private class OnClickToEraser implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Log.i(log, "OnClickToEraser");
            StaticModel.setPenMode(StaticModel.PenMode.ERASER);
            ((MainActivity)context).upDate();
        }
    }
    private class OnClickToClear implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Log.i(log, "OnClickToClear");
            StaticModel.setDialogMode(StaticModel.DialogMode.CLEAR);
            ((MainActivity)context).upDate();
        }
    }
    private class OnClickToDownload implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Log.i(log, "OnClickToDownload");
            Debuger.print(context, "OnClickToDownload");
            DrawSurfaceView.share((Activity)context);
        }
    }
    private class OnClickToShare implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Log.i(log, "OnClickToShare");
            Debuger.print(context, "OnClickToShare");
            DrawSurfaceView.share((Activity)context);
        }
    }
    private class OnClickToImport implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Log.i(log, "OnClickToImport");
            Debuger.print(context, "OnClickToImport");
        }
    }
    private class OnClickToSetting implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Log.i(log, "OnClickToSetting");
            Debuger.print(context, "OnClickToSetting");
            StaticModel.setSettingMode(StaticModel.SettingMode.VIEW);
            ((MainActivity)context).upDate();
        }
    }
}
