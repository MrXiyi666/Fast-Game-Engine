package fast.game.engine.adapter;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.luaj.vm2.LuaValue;
import java.util.List;
import fast.game.engine.data.Fun_List_Horizontal_Data;
import fast.game.engine.fun.Fun;


public class Fun_Adapter_Layout_Horizontal extends BaseAdapter{
    private final Context context;
    private final List<Fun_List_Horizontal_Data> dataList;
    private int height_tage = 10;
    public LuaValue Close=null, Click=null;
    public int ta = 255, tr = 255, tg = 255, tb = 255;
    public Fun_Adapter_Layout_Horizontal(Context context, List<Fun_List_Horizontal_Data> dataList) {
        this.context = context;
        this.dataList = dataList;
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
            holder.imageView = new ImageView(context);
            holder.textView = new TextView(context);
            holder.imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            holder.textView.setText("物品");
            holder.textView.setGravity(Gravity.FILL_VERTICAL);
            holder.textView.setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM);
            holder.textView.setAutoSizeTextTypeUniformWithConfiguration(5,100,1, TypedValue.COMPLEX_UNIT_SP);
            convertView = getLayout(holder.imageView, holder.textView);
            convertView.setBackgroundColor(Color.TRANSPARENT);
            convertView.setVisibility(View.INVISIBLE);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.imageView.setImageBitmap(dataList.get(position).getBitmap());
        holder.textView.setTextColor(Color.argb(ta, tr, tg, tb));
        holder.textView.setText(dataList.get(position).getName());
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
            int new_height = (int) (parentView.getHeight() * height_tage / 100.0f);
            if(params.height != new_height){
                params.height = new_height;
                finalConvertView.setLayoutParams(params);
            }
            if(finalConvertView.getVisibility() == View.INVISIBLE){
                finalConvertView.setVisibility(View.VISIBLE);
            }
        });

        return convertView;
    }

    public void setHeight_Tage(int data){
        height_tage = data;
    }
    static class ViewHolder {
        TextView textView;
        ImageView imageView;

    }

    private View getLayout(ImageView imageView, TextView textView){
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(params);
        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 0.5f);
        imageParams.setMargins(Fun.DpToPx(5),Fun.DpToPx(5),Fun.DpToPx(5),Fun.DpToPx(5));
        linearLayout.addView(imageView, imageParams);
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);
        textView.setPadding(Fun.DpToPx(5),Fun.DpToPx(5),Fun.DpToPx(5),Fun.DpToPx(5));
        linearLayout.addView(textView, textParams);
        return linearLayout;
    }

}
