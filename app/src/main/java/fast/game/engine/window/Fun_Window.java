package fast.game.engine.window;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import org.luaj.vm2.LuaValue;
import fast.game.engine.View.Fast_View;
import fast.game.engine.control.Fun_Button;
import fast.game.engine.fun.Fun;

public class Fun_Window extends RelativeLayout {
    public Paint paint;
    public int window_radius=20;
    private int widthPercentage = 1, widthMeasureSpec=1;
    private int heightPercentage = 1, heightMeasureSpec=1;
    public boolean update = false;
    public LuaValue Down=null;
    public LuaValue Up=null;
    public LuaValue Move=null;
    public LuaValue Draw=null;
    public int ba=192, br=0, bg=0, bb=0;
    public int ta=255, tr=0, tg=0, tb=0;
    private float rectBottom;
    public LuaValue Close=null;
    public int close_a=255, close_r=255, close_g=255, close_b=255;
    public boolean window_title = true;
    public Fun_Window(Context context) {
        super(context);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        this.setLayoutParams(layoutParams);
        this.setBackgroundColor(Color.argb(192, 0,0,0));
        this.setId(View.generateViewId());
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.argb(ba,br,bg,bb));
        this.setBackgroundColor(Color.TRANSPARENT);
    }
    public void addChild(Fast_View view){
        this.addView(view);
    }
    public void removeChild(Fast_View view){
        this.removeView(view);
    }

    public void addChild(Fun_Button view){
        this.addView(view);
    }


    public void removeChild(Fun_Button view){
        this.removeView(view);
    }
    public void addChild(Fun_Window view){
        this.addView(view);
    }
    public void removeChild(Fun_Window view){
        this.removeView(view);
    }
    public void removeChildAll(){
        this.removeAllViews();
    }
    public int getTextHeight(String text){
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        // 获取文本高度
        return bounds.height();
    }
    public int getTextWidth(String text){
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        // 获取文本宽度
        return bounds.width();
    }
    public void setSize(int widthPercentage, int heightPercentage) {
        this.widthPercentage = widthPercentage;
        this.heightPercentage = heightPercentage;
        post(() -> {
            ViewParent parent = getParent();
            if (parent instanceof ViewGroup parentViewGroup) {
                // 获取父ViewGroup的宽高
                int parentWidth = parentViewGroup.getWidth();
                int parentHeight = parentViewGroup.getHeight();

                // 如果父ViewGroup的宽高尚未确定，则不进行位置调整
                if (parentWidth > 0 && parentHeight > 0) {
                    // 计算当前View的宽高
                    int width = parentWidth * this.widthPercentage / 100;
                    int height = parentHeight * this.heightPercentage / 100;

                    // 确保计算的宽高符合MeasureSpec的要求
                    width = Math.min(width, MeasureSpec.getSize(this.widthMeasureSpec));
                    height = Math.min(height, MeasureSpec.getSize(this.heightMeasureSpec));
                    setMeasuredDimension(width, height);
                    requestLayout();
                }
            }
        });
    }
    public void setXY(int xPercentage, int yPercentage) {
        post(() -> {
            ViewParent parent = getParent();
            if (parent instanceof ViewGroup parentViewGroup) {

                // 获取父ViewGroup的宽高
                int parentWidth = parentViewGroup.getWidth();
                int parentHeight = parentViewGroup.getHeight();

                // 如果父ViewGroup的宽高尚未确定，则不进行位置调整
                if (parentWidth > 0 && parentHeight > 0) {
                    // 计算当前View的绘制位置
                    int x = (int) (parentWidth * xPercentage / 100.0f);
                    int y = (int) (parentHeight * yPercentage / 100.0f);

                    // 设置当前View的位置
                    setX(x);
                    setY(y);
                }
            }
        });
    }
    public void show(){
        this.setVisibility(View.VISIBLE);
    }

    public void hide(){
        this.setVisibility(View.GONE);
    }
    public void setWindowTitle(boolean b){
        window_title = b;
        invalidate();
        if(b==false){
            this.post(()->{
                setPadding(0, 0, 0, 0);
            });
        }
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        this.widthMeasureSpec = widthMeasureSpec;
        this.heightMeasureSpec = heightMeasureSpec;
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        ViewParent parent = getParent();
        if (parent instanceof ViewGroup parentViewGroup) {
            // 获取父ViewGroup的宽高
            int parentWidth = parentViewGroup.getWidth();
            int parentHeight = parentViewGroup.getHeight();

            // 如果父ViewGroup的宽高尚未确定，则不进行位置调整
            if (parentWidth > 0 && parentHeight > 0) {
                // 计算当前View的宽高
                int width = parentWidth * this.widthPercentage / 100;
                int height = parentHeight * this.heightPercentage / 100;

                // 确保计算的宽高符合MeasureSpec的要求
                width = Math.min(width, MeasureSpec.getSize(this.widthMeasureSpec));
                height = Math.min(height, MeasureSpec.getSize(this.heightMeasureSpec));
                setMeasuredDimension(width, height);
                requestLayout();
            }
        }
    }
    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.TRANSPARENT);
        paint.setColor(Color.argb(ba,br,bg,bb));
        canvas.drawRoundRect(new RectF(0,0,getWidth(), getHeight()), Fun.DpToPx(window_radius), Fun.DpToPx(window_radius), paint);
        if(Draw != null && Draw.isfunction()){
            Draw.call(LuaValue.userdataOf(canvas));
        }
        if(window_title){
            draw_Top_Rect(canvas);
            drawClose(canvas);
        }
        if(update){
            invalidate();
        }
    }
    public void draw_Top_Rect(Canvas canvas){
        paint.setColor(Color.argb(ta,tr,tg,tb));
        float rectTop = 0;
        rectBottom = (float) (Fun.height * 4) / 100;
        this.post(()->{
            setPadding(0, (int) rectBottom, 0, 0);
        });

        canvas.drawRect(0, rectTop, getWidth(), rectBottom, paint);
    }
    private int Close_X;

    private void drawClose(Canvas canvas){
        paint.setColor(Color.argb(close_a, close_r, close_g, close_b));
        paint.setTextSize(rectBottom);
        paint.setTextAlign(Paint.Align.LEFT);
        float paddingHeight = (rectBottom - getTextHeight("⊗")) / 2;
        Close_X = (int) (getWidth() - rectBottom);
        canvas.drawText("⊗", Close_X, getTextHeight("⊗") + paddingHeight, paint);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int X, Y;
        super.onTouchEvent(event);
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                X = (int)event.getX();
                Y = (int)event.getY();
                if(Down != null && Down.isfunction()){
                    Down.call(LuaValue.valueOf(X), LuaValue.valueOf(Y));
                }
                if(event.getX() > Close_X && event.getX() < getWidth() && event.getY() > 0 && event.getY() < rectBottom){
                    close_a = 100;
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                X = (int)event.getX();
                Y = (int)event.getY();
                if(Up != null && Up.isfunction()){
                    Up.call(LuaValue.valueOf(X), LuaValue.valueOf(Y));
                }
                if(close_a==100){
                    if(Close != null && Close.isfunction()){
                        Close.call();
                    }else{
                        close_a=255;
                        invalidate();
                    }
                }else{
                    if(close_a!=255){
                        close_a=255;
                        invalidate();
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                X = (int) event.getX();
                Y = (int) event.getY();
                if(Move != null && Move.isfunction()){
                    Move.call(LuaValue.valueOf(X), LuaValue.valueOf(Y));
                }
                if(event.getX() < Close_X || event.getX() > getWidth() || event.getY() < 0 || event.getY() > rectBottom){
                    if(close_a!=255){
                        invalidate();
                    }
                    close_a=255;
                }else{
                    if(close_a!=100){
                        invalidate();
                    }
                    close_a=100;
                }

                break;
        }
        return true;
    }
}