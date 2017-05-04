package takenoko.tech.blackboardapp.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import takenoko.tech.blackboardapp.R;

/**
 * Created by たけのこ on 2017/05/02.
 */

public class ListViewAdapter extends BaseAdapter {

    String log = "----ListViewAdapter----";

    class ViewHolder {
        TextView textView;
        ImageView imageView;
    }
    class ButtonModel {
        String text;
        Drawable image;
        View.OnClickListener func;
        ButtonModel(String text, Drawable image, View.OnClickListener func) {
            this.text = text;
            this.image = image;
            this.func = func;
        }
    }
    private LayoutInflater inflater;
    private int itemLayoutId;
    private ArrayList<ButtonModel> buttonList = new ArrayList();

    public ListViewAdapter(Context context, int itemLayoutId) {
        super();
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.itemLayoutId = itemLayoutId;
    }
    public void add(String text, Drawable image, View.OnClickListener func) {
        buttonList.add(new ButtonModel(text, image, func));
    }
    @Override
    public int getCount() {return buttonList.size();}
    @Override
    public Object getItem(int i) {return null;}
    @Override
    public long getItemId(int i) {return 0;}
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null) {
            view = inflater.inflate(itemLayoutId, viewGroup, false);
            holder = new ViewHolder();
            holder.textView = (TextView) view.findViewById(R.id.list_text);
            holder.imageView = (ImageView) view.findViewById(R.id.list_image);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.imageView.setImageDrawable(buttonList.get(i).image);
        holder.textView.setText(buttonList.get(i).text);
        view.setOnClickListener(buttonList.get(i).func);
        return view;
    }
}