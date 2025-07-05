package fast.game.engine.window;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;
import fast.game.engine.fun.Fun;

public class Fun_Scroll_Window extends ScrollView {
    public int window_radius=20;
    public int ba=150, br=0, bg=0, bb=0;
    private Fun_Window linear;
    public Fun_Scroll_Window(Context context) {
        super(context);
        linear = new Fun_Window(context);
        linear.setSize(100,100);
        linear.setWindowTitle(false);
        this.setNestedScrollingEnabled(true);
        this.setVerticalScrollBarEnabled(false);
        this.setHorizontalScrollBarEnabled(false);
        addView(linear);
        GradientDrawable background = new GradientDrawable();
        background.setColor(Color.argb(ba,br,bg,bb));
        background.setCornerRadius(Fun.DpToPx(window_radius));
        this.setBackground(background);
        this.setClipToOutline(true);
        this.setVisibility(View.INVISIBLE);
        postDelayed(()-> setVisibility(View.VISIBLE),10);
    }

    public void addChild(View view){
        linear.addView(view);
    }

    public void removeChild(View view){
        linear.removeView(view);
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

}
