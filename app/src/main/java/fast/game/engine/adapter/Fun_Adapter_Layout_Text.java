package fast.game.engine.adapter;

import android.content.Context;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.widget.TextViewCompat;

import java.util.List;

import fast.game.engine.fun.Fun;

public class Fun_Adapter_Layout_Text extends BaseAdapter {
    private List<String> dataList;
    private Context context;
    private int height_tage = 10;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            holder.textView = new TextView(context);
            holder.textView.setGravity(Gravity.CENTER);
            holder.textView.setText("物品");

            holder.textView.setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM);
            holder.textView.setAutoSizeTextTypeUniformWithConfiguration(5,100,1, TypedValue.COMPLEX_UNIT_SP);
            holder.textView.setIncludeFontPadding(false);
            convertView = getLayout(holder.textView);
            convertView.setVisibility(View.INVISIBLE);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.textView.setText(dataList.get(position));
        View finalConvertView = convertView;
        convertView.post(()->{
            ViewGroup parentView =  (ViewGroup) finalConvertView.getParent();
            if(parentView==null){
                return;
            }
            if(parentView.getHeight()<=0){
                return;
            }
            ViewGroup.LayoutParams params = finalConvertView.getLayoutParams();
            if(height_tage < 10){
                height_tage=10;
            }
            params.height = (int) (parentView.getHeight() * height_tage / 100.0f);
            finalConvertView.setLayoutParams(params);
            finalConvertView.setVisibility(View.VISIBLE);
            Log.w("高度",params.height +"");
        });
        return convertView;
    }
    public void setHeight_Tage(int data){
        height_tage = data;
    }
    static class ViewHolder {
        TextView textView;
    }
    private View getLayout(TextView textView){
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(params);
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        textParams.gravity = Gravity.CENTER_VERTICAL;
        textView.setPadding(Fun.DpToPx(5),Fun.DpToPx(5),Fun.DpToPx(5),Fun.DpToPx(5));
        linearLayout.addView(textView, textParams);
        return linearLayout;
    }
}
