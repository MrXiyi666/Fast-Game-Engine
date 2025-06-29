package fast.game.engine.window;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import fast.game.engine.fun.Fun;

public class Fun_Scroll_Window_Text extends ScrollView{
    public int widthPercentage = 0;
    public int heightPercentage = 0;
    public int xPercentage=0, yPercentage=0;
    public Fun_Scroll_Window_Text fun;
    public int Fu_Width=0,Fu_Height=0;
    public ViewTreeObserver.OnGlobalLayoutListener layoutListener;
    public ViewGroup parentView;
    private final LinearLayout linear;
    public int ba=150, br=255, bg=255, bb=255;
    private final TextView textView;
    public int window_radius=20;
    public Fun_Scroll_Window_Text(Context context) {
        super(context);
        fun=this;
        this.setVerticalScrollBarEnabled(false);
        this.setHorizontalScrollBarEnabled(false);
        linear = new LinearLayout(context);
        linear.setOrientation(LinearLayout.VERTICAL);


        textView = new TextView(context);
        textView.setGravity(Gravity.START);
        textView.setText("物品");
        textView.setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        textView.setAutoSizeTextTypeUniformWithConfiguration(5,100,1, TypedValue.COMPLEX_UNIT_SP);
        textView.setIncludeFontPadding(false);

        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        textParams.setMargins(Fun.DpToPx(5),Fun.DpToPx(5),Fun.DpToPx(5),Fun.DpToPx(5));
        linear.addView(textView, textParams);
        this.setBackgroundColor(Color.argb(150, 0, 0, 0));
        this.post(()->{
            ScrollView.LayoutParams svLayoutParams = new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            this.addView(linear, svLayoutParams);
        });
        postDelayed(()-> setVisibility(View.VISIBLE),10);
        // 创建带圆角的背景
        GradientDrawable background = new GradientDrawable();
        background.setCornerRadius(Fun.DpToPx(window_radius));
        background.setColor(Color.argb(ba,br,bg,bb));
        this.setBackground(background);
        this.setClipToOutline(true);
        this.setVisibility(View.INVISIBLE);
    }
    public void setText(String data){
        textView.setText(data);

    }
    public void setTextColr(int a, int r, int g, int b){
        textView.setTextColor(Color.argb(a,r,g,b));
    }
    public void setColor(int a, int r, int g, int b){
        // 创建带圆角的背景
        GradientDrawable background = new GradientDrawable();
        background.setCornerRadius(Fun.DpToPx(window_radius));
        background.setColor(Color.argb(a, r, g, b));
        this.setBackground(background);
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
                // 通知LinearLayout更新（如果需要）
                linear.requestLayout();
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
    public void setSize(int widthPercentage, int heightPercentage) {
        this.widthPercentage = widthPercentage;
        this.heightPercentage = heightPercentage;
        post(()->{
            ViewGroup.LayoutParams params = getLayoutParams();
            params.width = (int) (Fu_Width * fun.widthPercentage / 100.0f);;
            params.height = (int) (Fu_Height * fun.heightPercentage / 100.0f);
            fun.setLayoutParams(params);
            // 通知LinearLayout更新（如果需要）
            linear.requestLayout();
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
