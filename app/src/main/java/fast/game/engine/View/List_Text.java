package fast.game.engine.View;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import org.luaj.vm2.LuaValue;

import java.util.ArrayList;
import java.util.List;

import fast.game.engine.adapter.Fun_Adapter_Layout_Text;
import fast.game.engine.fun.Fun;

public class List_Text extends ListView {
    private int widthPercentage = 0;
    private int heightPercentage = 0;
    private int xPercentage=0, yPercentage=0;
    private int Fu_Width=0,Fu_Height=0;
    private final List_Text fun;
    private ViewTreeObserver.OnGlobalLayoutListener layoutListener;
    private ViewGroup parentView;

    private final List<String> dataList = new ArrayList<>();
    private Fun_Adapter_Layout_Text adapter;
    public LuaValue Close=null, Click=null;
    public int window_radius=20;
    public int ba=50, br=0, bg=0, bb=0;
    public List_Text(Context context) {
        super(context);
        fun=this;
        // 设置初始布局参数
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        this.setVerticalScrollBarEnabled(false);
        this.setHorizontalScrollBarEnabled(false);
        this.setLayoutParams(params);
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
    public void setHeight_Tage(int data){
        adapter.setHeight_Tage(data);
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
    public void setSize(int widthPercentage, int heightPercentage) {
        this.widthPercentage = widthPercentage;
        this.heightPercentage = heightPercentage;
        post(()->{
            ViewGroup.LayoutParams params = fun.getLayoutParams();
            params.width = (int) (Fu_Width * fun.widthPercentage / 100.0f);;
            params.height = (int) (Fu_Height * fun.heightPercentage / 100.0f);
            fun.setLayoutParams(params);
        });

    }

    public void setXY(int xPercentage, int yPercentage) {
        this.xPercentage = xPercentage;
        this.yPercentage = yPercentage;
        post(()->{
            int x = (int) (Fu_Width * fun.xPercentage / 100.0f);
            int y = (int) (Fu_Height * fun.yPercentage / 100.0f);
            fun.setX(x);
            fun.setY(y);
        });
    }

    public void show(){
        this.setVisibility(View.VISIBLE);
    }

    public void hide(){
        this.setVisibility(View.GONE);
    }
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        // 获取父布局
        parentView =  (ViewGroup)getParent();
        if (parentView == null) {
            return;
        }
        layoutListener = () -> {
            int parentWidth = parentView.getWidth();
            int parentHeight = parentView.getHeight();
            if (Fu_Width != parentWidth || Fu_Height !=parentHeight) {
                int x = (int) (Fu_Width * fun.xPercentage / 100.0f);
                int y = (int) (Fu_Height * fun.yPercentage / 100.0f);
                fun.setX(x);
                fun.setY(y);
                ViewGroup.LayoutParams params = fun.getLayoutParams();
                params.width = (int) (Fu_Width * fun.widthPercentage / 100.0f);;
                params.height = (int) (Fu_Height * fun.heightPercentage / 100.0f);
                fun.setLayoutParams(params);
            }
            Fu_Width = parentWidth;
            Fu_Height = parentHeight;
        };
        // 添加监听器
        parentView.getViewTreeObserver().addOnGlobalLayoutListener(layoutListener);
    }
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        // 移除监听器
        if (parentView != null && parentView.getViewTreeObserver().isAlive()) {
            parentView.getViewTreeObserver().removeOnGlobalLayoutListener(layoutListener);
        }
    }
    public void Destroy(){
        if (adapter != null) {
            this.setAdapter(null);
            adapter=null;
        }
    }

}
