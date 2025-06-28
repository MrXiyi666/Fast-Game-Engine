package fast.game.engine.window;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import org.luaj.vm2.LuaValue;
import fast.game.engine.View.Fast_View;
import fast.game.engine.control.Fun_Button;
import fast.game.engine.fun.Fun;

public class Fun_Window extends RelativeLayout {
    public Paint paint;
    public int window_radius=20;
    private int widthPercentage = 0;
    private int heightPercentage = 0;
    private int xPercentage=0, yPercentage=0;
    public boolean update = false;
    public LuaValue Down=null;
    public LuaValue Up=null;
    public LuaValue Move=null;
    public LuaValue Draw=null;
    public int ba=150, br=0, bg=0, bb=0;
    public int ta=255, tr=0, tg=0, tb=0;
    public float rectBottom=0;
    public LuaValue Close=null, Click;
    public int close_a=255, close_r=255, close_g=255, close_b=255;
    public boolean window_title = true, window_close;
    private final Fun_Window fun;
    private int Fu_Width=0,Fu_Height=0;
    private ViewTreeObserver.OnGlobalLayoutListener layoutListener;
    private ViewGroup parentView;
    public Fun_Window(Context context) {
        super(context);
        fun = this;
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        this.setLayoutParams(layoutParams);
        this.setId(View.generateViewId());
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.argb(ba,br,bg,bb));
        GradientDrawable background = new GradientDrawable();
        background.setColor(Color.argb(ba,br,bg,bb));
        background.setCornerRadius(Fun.DpToPx(window_radius));
        this.setBackground(background);
        this.setClipToOutline(true);
    }
    public void addChild(Fast_View view){
        this.addView(view);
    }
    public void removeChild(Fast_View view){
        this.removeView(view);
    }

    public void addChild(Fun_Button view){
        post(()->{
            this.addView(view);
        });

    }


    public void removeChild(Fun_Button view){
        post(()->{
            this.removeView(view);
        });

    }
    public void addChild(Fun_Window view){
        post(()->{
            this.addView(view);
        });

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
    public void openWindowTitle(boolean b){
        window_title = b;
        if(!b){
            setPadding(0, 0, 0, 0);
        }
        invalidate();
    }
    public void openClick(){
        this.setSoundEffectsEnabled(true);
        this.setOnClickListener(view -> {
            if(!window_close){
                if(Click != null && Click.isfunction()){
                    Click.call();
                }
            }
        });
    }
    public void closeClick(){
        Click=null;
        this.setSoundEffectsEnabled(false);
        this.setOnClickListener(view -> {

        });
    }
    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.TRANSPARENT);
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
        setPadding(0, (int) rectBottom, 0, 0);
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
                if(!window_close){
                    if(Y > rectBottom){
                        if(Down != null && Down.isfunction()){
                            Down.call(LuaValue.valueOf(X), LuaValue.valueOf(Y));
                        }
                    }

                }

                if(event.getX() > Close_X && event.getX() < getWidth() && event.getY() > 0 && event.getY() < rectBottom){
                    close_a = 100;
                    window_close = true;
                    invalidate();
                }else{
                    window_close=false;
                }
                break;
            case MotionEvent.ACTION_UP:
                X = (int)event.getX();
                Y = (int)event.getY();
                if(!window_close){
                    if(Y > rectBottom){
                        if(Up != null && Up.isfunction()){
                            Up.call(LuaValue.valueOf(X), LuaValue.valueOf(Y));
                        }
                    }
                }else{
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
                    window_close = false;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                X = (int) event.getX();
                Y = (int) event.getY();
                if(!window_close){
                    if(Y > rectBottom){
                        if(Move != null && Move.isfunction()){
                            Move.call(LuaValue.valueOf(X), LuaValue.valueOf(Y));
                        }
                    }
                }else{
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
                }
                break;
        }
        return true;
    }
}