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

import fast.game.engine.adapter.Fun_Adapter_Layout_Horizontal;
import fast.game.engine.data.Fun_List_Horizontal_Data;
import fast.game.engine.fun.Fun;

public class Fun_List extends ListView {
    private int widthPercentage = 0;
    private int heightPercentage = 0;
    private int xPercentage=0, yPercentage=0;
    private final Fun_List fun;
    private ViewTreeObserver.OnGlobalLayoutListener layoutListener;
    private ViewGroup parentView;
    private int Fu_Width=0,Fu_Height=0;
    public int window_radius=20;
    public int ba=150, br=255, bg=255, bb=255;
    public Fun_Adapter_Layout_Horizontal adapter;
    public LuaValue Close=null, Click=null;
    private final List<Fun_List_Horizontal_Data> dataList = new ArrayList<>();
    public Fun_List(Context context) {
        super(context);
        fun=this;
        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        this.setLayoutParams(params);
        // 设置初始布局参数
        this.setId(View.generateViewId());
        this.setVerticalScrollBarEnabled(false);
        this.setHorizontalScrollBarEnabled(false);
        adapter = new Fun_Adapter_Layout_Horizontal(context, dataList);
        this.setAdapter(adapter);
        // 创建带圆角的背景
        GradientDrawable background = new GradientDrawable();
        background.setCornerRadius(Fun.DpToPx(window_radius));
        background.setColor(Color.argb(ba,br,bg,bb));
        this.setBackground(background);
        this.setClipToOutline(true);
        this.setVisibility(View.INVISIBLE);
        postDelayed(()-> setVisibility(View.VISIBLE),10);
        this.setOnItemClickListener((parent, view, position, id) -> {
            if(Click != null && Click.isfunction()){
                Click.call(LuaValue.valueOf(position), LuaValue.valueOf(dataList.get(position).getName()));
            }
        });
    }
    public void setHeight_Tage(int data){
        adapter.setHeight_Tage(data);
        adapter.notifyDataSetChanged();
    }
    public void addItem(Fun_List_Horizontal_Data item) {
        dataList.add(item);
        adapter.notifyDataSetChanged();
    }
    public void removeItem(int position) {
        if (position >= 0 && position < dataList.size()) {
            dataList.remove(position);
            adapter.notifyDataSetChanged();
        }
    }
    public void removeItem(String name) {
        for(Fun_List_Horizontal_Data data : dataList){
            if(data.getName().equals(name)){
                dataList.remove(data);
                adapter.notifyDataSetChanged();
            }
        }
    }
    public void clearItems() {
        dataList.clear();
        adapter.notifyDataSetChanged();
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
                ViewGroup.LayoutParams params =  getLayoutParams();
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
        if (parentView != null && parentView.getViewTreeObserver().isAlive()) {
            parentView.getViewTreeObserver().removeOnGlobalLayoutListener(layoutListener);
        }
    }

    public void setSize(int widthPercentage, int heightPercentage) {
        this.widthPercentage = widthPercentage;
        this.heightPercentage = heightPercentage;
        post(()->{
            ViewGroup.LayoutParams params =  getLayoutParams();
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
}
