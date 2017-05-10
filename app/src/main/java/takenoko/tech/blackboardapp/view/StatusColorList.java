package takenoko.tech.blackboardapp.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import takenoko.tech.blackboardapp.R;
import takenoko.tech.blackboardapp.model.SensitiveTouchModel;

/**
 * Created by たけのこ on 2017/05/06.
 */

public class StatusColorList extends ListView {

    final static String log = "----StatusColorList----";

    class ColorAdapter extends BaseAdapter {

        class ViewHolder {
            TextView textView;
        }
        private String colorArray[] = {"WHITE", "PINK", "YELLOW", "BLUE", "GREEN", "ORANGE"};
        private LayoutInflater inflater;
        private int itemLayoutId;

        public ColorAdapter(Context context, int itemLayoutId) {
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
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            final int color = SensitiveTouchModel.getColorMap().get(colorArray[i]);
            holder.textView.setText(colorArray[i]+"");
            holder.textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    SensitiveTouchModel.setStrokeColor(color);
                    SensitiveTouchModel.settingPenLine();
                    notifyDataSetChanged();
                }
            });
            if(color == SensitiveTouchModel.getPenLine().getColor()) {
                holder.textView.setBackgroundColor(Color.argb(128, 255, 0, 0));
                holder.textView.setBackgroundColor(color);
            } else {
                holder.textView.setBackgroundColor(Color.argb(51, 0, 0, 0));
            }
            return view;
        }
        @Override public int getCount() {return colorArray.length;}
        @Override public Object getItem(int i) {return null;}
        @Override public long getItemId(int i) {return 0;}
    }

    public StatusColorList(Context context, AttributeSet attrs) {
        super(context, attrs);
        ColorAdapter adapter = new ColorAdapter(context, R.layout.list_status_color);
        setAdapter(adapter);
    }

}

