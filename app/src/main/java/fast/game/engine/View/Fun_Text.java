package fast.game.engine.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.appcompat.widget.AppCompatTextView;
import fast.game.engine.fun.Fun;

public class Fun_Text extends AppCompatTextView {
    private int widthPercentage = 0;
    private int heightPercentage = 0;
    private int xPercentage=0, yPercentage=0;
    private int Fu_Width=0,Fu_Height=0;
    private final Fun_Text fun;
    private ViewTreeObserver.OnGlobalLayoutListener layoutListener;
    private final AppCompatTextView MText;
    public int strokeWidth = 2;
    private ViewGroup parentView;
    public Fun_Text(Context context) {
        super(context);
        fun = this;
        this.setId(View.generateViewId());
        MText = new AppCompatTextView(context);
        this.setBackgroundColor(Color.TRANSPARENT);
        this.setVisibility(View.INVISIBLE);
        postDelayed(()->{
            setVisibility(View.VISIBLE);
        },10);
        this.setGravity(Gravity.CENTER);
        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        this.setLayoutParams(params);
    }
    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        MText.setVisibility(visibility);
    }

    @Override
    public void setTextSize(float size) {
        super.setTextSize(Fun.DpToPx(size));
        MText.setTextSize(Fun.DpToPx(size));
    }
    /*
    setGravity(int gravity)
    Gravity.CENTER 17
    Gravity.CENTER_HORIZONTAL 1
    Gravity.CENTER_VERTICAL 16
    Gravity.LEFT 3
    Gravity.TOP 48
    Gravity.END 8388613
    Gravity.END 80
     */

    public void setColor(int a, int r, int g, int b){
        MText.setBackgroundColor(Color.argb(a,r,g,b));
    }
    public void setTextColor(int a, int r, int g, int b){
        this.setTextColor(Color.argb(a, r, g, b));
        MText.setTextColor(Color.argb(a, 255-r, 255-g, 255-b));
    }
    public void setSize(int widthPercentage, int heightPercentage) {
        this.widthPercentage = widthPercentage;
        this.heightPercentage = heightPercentage;
        post(()->{
            ViewGroup.LayoutParams params = getLayoutParams();
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

    @Override
    public void setX(float x) {
        super.setX(x);
        MText.setX(x);
    }

    @Override
    public void setY(float y) {
        super.setY(y);
        MText.setY(y);
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
                ViewGroup.LayoutParams params = getLayoutParams();
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
    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params) {
        //同步布局参数
        MText.setLayoutParams(params);
        super.setLayoutParams(params);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        CharSequence tt = MText.getText();
        //两个TextView上的文字必须一致
        if (tt == null || !tt.equals(this.getText())) {
            MText.setText(getText());
            this.postInvalidate();
        }
        MText.measure(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        MText.layout(left, top, right, bottom);
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        MInit();
        MText.draw(canvas);
        super.onDraw(canvas);
    }
    public void setStrokeWidth(int data){
        strokeWidth = data;
        invalidate();
    }
    @Override
    public void setLetterSpacing(float letterSpacing) {
        super.setLetterSpacing(letterSpacing);
        MText.setLetterSpacing(letterSpacing);
    }

    public void MInit() {
        MText.getPaint().setStrokeWidth(Fun.DpToPx(strokeWidth));
        MText.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);
        MText.getPaint().setTextSize(getTextSize());
        int ma = Color.alpha(getCurrentTextColor());
        int mr = 255-Color.red(getCurrentTextColor());
        int mg = 255-Color.green(getCurrentTextColor());
        int mb = 255 - Color.blue(getCurrentTextColor());
        MText.setTextColor(Color.argb(ma, mr, mg, mb));
        MText.setGravity(getGravity());
    }
}
