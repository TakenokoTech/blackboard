package takenoko.tech.blackboardapp.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import takenoko.tech.blackboardapp.R;
import takenoko.tech.blackboardapp.util.UtilStrage;

/**
 * Created by たけのこ on 2017/05/08.
 */

public class IOGridView extends GridView {

    Context context;

    public IOGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        update();
    }

    public void update() {
        ArrayList<Bitmap> list = new ArrayList<>();
        list.add(UtilStrage.get(context, "storageModel1.obj"));
        list.add(UtilStrage.get(context, "storageModel2.obj"));
        list.add(UtilStrage.get(context, "storageModel3.obj"));
        list.add(UtilStrage.get(context, "storageModel4.obj"));
        BitmapAdapter adapter = new BitmapAdapter(context, R.layout.list_import, list);
        setAdapter(adapter);
    }

    class BitmapAdapter extends ArrayAdapter<Bitmap> {

        class ViewHolder {
            TextView textView;
            ImageView imageView;
        }
        private int resourceId;

        public BitmapAdapter(Context context, int resource, List<Bitmap> bitmaps) {
            super(context, resource, bitmaps);
            resourceId = resource;
        }

        public View getView(int position, View view, ViewGroup parent) {
            ViewHolder holder;
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(resourceId, null);
                holder = new ViewHolder();
                holder.imageView = (ImageView)view.findViewById(R.id.list_import_image);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            holder.imageView.setImageBitmap(getItem(position));
            // holder.imageView.setLayoutParams(new FrameLayout.LayoutParams(view.getHeight(), view.getHeight()));
            return view;
        }
    }
}
