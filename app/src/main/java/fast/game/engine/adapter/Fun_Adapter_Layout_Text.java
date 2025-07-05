package fast.game.engine.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
import fast.game.engine.fun.Fun;

public class Fun_Adapter_Layout_Text extends BaseAdapter {
    private List<String> dataList;
    private Context context;
    private int Text_Mix_Size=10;
    public int ta = 255, tr = 255, tg = 255, tb = 255;
    public Fun_Adapter_Layout_Text(Context context, List<String> dataList){
        this.context=context;
        this.dataList=dataList;
    }
    @Override
    public int getCount() {
        return dataList != null ? dataList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return dataList != null && position < dataList.size() ? dataList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setText_Mix_Size(int size){
        Text_Mix_Size = size;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            holder.textView = new TextView(context);
            holder.textView.setText("物品");
            holder.textView.setBackgroundColor(Color.TRANSPARENT);
            holder.textView.setGravity(Gravity.CENTER);
            holder.textView.setPadding(Fun.DpToPx(5),Fun.DpToPx(5),Fun.DpToPx(5),Fun.DpToPx(5));
            holder.textView.setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM);
            holder.textView.setAutoSizeTextTypeUniformWithConfiguration(Text_Mix_Size, 100, 1, TypedValue.COMPLEX_UNIT_SP);
            convertView = holder.textView;
            convertView.setBackgroundColor(Color.TRANSPARENT);
            convertView.setVisibility(View.VISIBLE);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.textView.setTextColor(Color.argb(ta, tr, tg, tb));
        holder.textView.setText(dataList.get(position));
        return convertView;
    }
    static class ViewHolder {
        TextView textView;
    }
}
