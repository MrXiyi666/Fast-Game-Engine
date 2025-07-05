package fast.game.engine.View;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ListView;
import org.luaj.vm2.LuaValue;
import java.util.ArrayList;
import java.util.List;
import fast.game.engine.adapter.Fun_Adapter_Layout_Text;
import fast.game.engine.fun.Fun;

public class Fun_List_Text extends ListView {

    private final List<String> dataList = new ArrayList<>();
    private Fun_Adapter_Layout_Text adapter;
    public LuaValue Close=null, Click=null;
    public int window_radius=20;
    public int ba=50, br=0, bg=0, bb=0;
    public Fun_List_Text(Context context) {
        super(context);
        // 设置初始布局参数
        this.setVerticalScrollBarEnabled(false);
        this.setHorizontalScrollBarEnabled(false);
        this.setNestedScrollingEnabled(true);
        this.setId(View.generateViewId());
        this.setDividerHeight(Fun.DpToPx(1));
        adapter = new Fun_Adapter_Layout_Text(context, dataList);
        this.setAdapter(adapter);
        this.setOnItemClickListener((parent, view, position, id) -> {
            if(Click != null && Click.isfunction()){
                Click.call(LuaValue.valueOf(position), LuaValue.valueOf(dataList.get(position)));
            }
        });
        // 创建带圆角的背景
        GradientDrawable background = new GradientDrawable();
        background.setCornerRadius(Fun.DpToPx(window_radius));
        background.setColor(Color.argb(ba,br,bg,bb));
        this.setBackground(background);
        this.setClipToOutline(true);
        this.setVisibility(View.INVISIBLE);
        postDelayed(()-> setVisibility(View.VISIBLE),10);
    }
    public void setColor(int a, int r, int g, int b){
        // 创建带圆角的背景
        GradientDrawable background = new GradientDrawable();
        background.setCornerRadius(Fun.DpToPx(window_radius));
        background.setColor(Color.argb(a, r, g, b));
        this.setBackground(background);
    }
    public void setTextColor(int a, int r, int g, int b){
        adapter.ta=a;
        adapter.tr=r;
        adapter.tg=g;
        adapter.tb=b;
        adapter.notifyDataSetChanged();
    }
    public void setText_Mix_Size(int data){
        adapter.setText_Mix_Size(data);
        adapter.notifyDataSetChanged();
    }
    // 添加元素
    public void addItem(String item) {
        dataList.add(item);
        adapter.notifyDataSetChanged();
    }
    public void reviseItem(String item){
        for(int i=0;i<dataList.size();i++){
            if(item.equals(dataList.get(i))){
                dataList.set(i, item);
            }
        }
    }
    // 删除元素
    public void removeItem(int position) {
        if (position >= 0 && position < dataList.size()) {
            dataList.remove(position);
            adapter.notifyDataSetChanged();
        }
    }
    public void clearItems() {
        dataList.clear();
        adapter.notifyDataSetChanged();
    }
    public int getItemCount() {
        return dataList.size();
    }
    public void update(){
        adapter.notifyDataSetChanged();
    }
    public void show(){
        this.setVisibility(View.VISIBLE);
    }

    public int fu_width=0,fu_height=0;
    private ViewTreeObserver.OnGlobalLayoutListener globalLayoutListener;
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        View parentView = (View) getParent();
        globalLayoutListener = () -> {
            if(fu_width==parentView.getWidth() || fu_height == parentView.getHeight()){
                return;
            }
            fu_width = parentView.getWidth();
            fu_height = parentView.getHeight();
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            int width = (int) (fu_width * widthPercentage / 100.0f);
            int height = (int) (fu_height * heightPercentage / 100.0f);
            layoutParams.width = width;
            layoutParams.height = height;
            setLayoutParams(layoutParams);
            int x = (int) (fu_width * xPercentage / 100.0f);
            int y = (int) (fu_height * yPercentage / 100.0f);
            setX(x);setY(y);
        };
        parentView.getViewTreeObserver().addOnGlobalLayoutListener(globalLayoutListener);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (getParent() instanceof View parentView) {
            if (globalLayoutListener != null) {
                parentView.getViewTreeObserver().removeOnGlobalLayoutListener(globalLayoutListener);
            }
        }
    }
    int xPercentage=0,yPercentage=0,widthPercentage=0,heightPercentage=0;

    public void setSize(int widthPercentage, int heightPercentage) {
        this.widthPercentage=widthPercentage;
        this.heightPercentage=heightPercentage;
    }
    public void setXY(int xPercentage, int yPercentage) {
        this.xPercentage=xPercentage;
        this.yPercentage=yPercentage;
    }
    public void Destroy(){
        if (adapter != null) {
            this.setAdapter(null);
            adapter=null;
        }
    }

}
