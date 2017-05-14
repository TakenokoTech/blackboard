package tech.takenoko.blackboardapp.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tech.takenoko.blackboardapp.R;
import tech.takenoko.blackboardapp.model.SettingModel;
import tech.takenoko.blackboardapp.util.Strage;

/**
 * Created by たけのこ on 2017/05/08.
 */

public class IOGridView extends GridView {

    final static String log = "----IOGridView----";

    static Context context;
    static int select = 0;
    static ArrayList<Bitmap> list = new ArrayList<>();
    static BitmapAdapter adapter = null;

    public IOGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        update();
        adapter = new BitmapAdapter(context, R.layout.list_import, list);
        setAdapter(adapter);
    }

    public static void update() {
        list = new ArrayList<>();
        list.add(Strage.get(context, context.getResources().getString(R.string.io_file_01)));
        list.add(Strage.get(context, context.getResources().getString(R.string.io_file_02)));
        list.add(Strage.get(context, context.getResources().getString(R.string.io_file_03)));
        list.add(Strage.get(context, context.getResources().getString(R.string.io_file_04)));
        list.add(Strage.get(context, context.getResources().getString(R.string.io_file_05)));
        list.add(Strage.get(context, context.getResources().getString(R.string.io_file_06)));
        //list.add(UtilStrage.get(context, context.getResources().getString(R.string.io_file_07)));
        //list.add(UtilStrage.get(context, context.getResources().getString(R.string.io_file_08)));
        //list.add(UtilStrage.get(context, context.getResources().getString(R.string.io_file_09)));
        //list.add(UtilStrage.get(context, context.getResources().getString(R.string.io_file_10)));
        if(adapter != null) adapter.notifyDataSetChanged();
    }

    public static String getSelectFileName() {
        switch (select) {
            case 0: return context.getResources().getString(R.string.io_file_01);
            case 1: return context.getResources().getString(R.string.io_file_02);
            case 2: return context.getResources().getString(R.string.io_file_03);
            case 3: return context.getResources().getString(R.string.io_file_04);
            case 4: return context.getResources().getString(R.string.io_file_05);
            case 5: return context.getResources().getString(R.string.io_file_06);
            case 6: return context.getResources().getString(R.string.io_file_07);
            case 7: return context.getResources().getString(R.string.io_file_08);
            case 8: return context.getResources().getString(R.string.io_file_09);
            case 9: return context.getResources().getString(R.string.io_file_10);
            default: return null;
        }
    }

    class BitmapAdapter extends ArrayAdapter<Bitmap> {

        class ViewHolder {
            RelativeLayout importLayout;
            TextView textView;
            ImageView imageView;
        }
        private int resourceId;

        public BitmapAdapter(Context context, int resource, List<Bitmap> bitmaps) {
            super(context, resource, bitmaps);
            resourceId = resource;
        }

        public View getView(final int position, View view, ViewGroup parent) {
            ViewHolder holder;
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(resourceId, null);
                holder = new ViewHolder();
                holder.importLayout = (RelativeLayout) view.findViewById(R.id.list_import_layout);
                holder.textView = (TextView) view.findViewById(R.id.list_import_text);
                holder.imageView = (ImageView) view.findViewById(R.id.list_import_image);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            holder.textView.setText(String.valueOf(position + 1));
            holder.imageView.setImageBitmap(getItem(position));
            holder.imageView.setBackgroundColor(SettingModel.getBackColorARGB());
            holder.imageView.setOnClickListener(new OnClickListener() {
                @Override public void onClick(View view) {
                    select = position;
                    notifyDataSetChanged();
                }
            });
            if(select == position) {
                holder.importLayout.setBackgroundColor(Color.RED);
            } else {
                holder.importLayout.setBackgroundColor(Color.TRANSPARENT);
            }
            // holder.imageView.setLayoutParams(new FrameLayout.LayoutParams(view.getHeight(), view.getHeight()));
            return view;
        }

        public Bitmap getItem(int position) { return list.get(position); }
    }
}
