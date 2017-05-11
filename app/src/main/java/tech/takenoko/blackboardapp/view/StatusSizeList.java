package tech.takenoko.blackboardapp.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import tech.takenoko.blackboardapp.R;
import tech.takenoko.blackboardapp.model.SensitiveTouchModel;
import tech.takenoko.blackboardapp.util.PenCanvas;


/**
 * Created by たけのこ on 2017/05/06.
 */

public class StatusSizeList extends ListView {

    final static String log = "----StatusSizeList----";

    class SizeAdapter extends BaseAdapter {

        class ViewHolder {
            TextView textView;
            PenCanvas imageView;
        }
        private int sizeArray[] = {1, 3, 5, 10, 25, 50, 100};
        private LayoutInflater inflater;
        private int itemLayoutId;

        public SizeAdapter(Context context, int itemLayoutId) {
            super();
            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.itemLayoutId = itemLayoutId;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            ViewHolder holder;
            if(view == null) {
                view = inflater.inflate(itemLayoutId, viewGroup, false);
                holder = new ViewHolder();
                holder.textView = (TextView) view.findViewById(R.id.list_status_text);
                holder.imageView = (PenCanvas) view.findViewById(R.id.list_status_image);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            holder.imageView.setLineSize(sizeArray[i]);
            holder.textView.setText(sizeArray[i]+"");
            holder.imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    SensitiveTouchModel.setStrokeWidth(sizeArray[i]);
                    SensitiveTouchModel.settingPenLine();
                    notifyDataSetChanged();
                }
            });
            if(sizeArray[i] == SensitiveTouchModel.getPenLine().getStrokeWidth()) {
                holder.imageView.setBackgroundColor(Color.argb(128, 255, 0, 0));
            } else {
                holder.imageView.setBackgroundColor(Color.argb(51, 0, 0, 0));
            }
            return view;
        }
        @Override public int getCount() {return sizeArray.length;}
        @Override public Object getItem(int i) {return null;}
        @Override public long getItemId(int i) {return 0;}
    }

    public StatusSizeList(Context context, AttributeSet attrs) {
        super(context, attrs);
        SizeAdapter adapter = new SizeAdapter(context, R.layout.list_status_item);
        setAdapter(adapter);
    }

}

