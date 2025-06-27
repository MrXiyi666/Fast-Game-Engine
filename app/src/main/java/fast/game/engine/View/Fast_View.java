package fast.game.engine.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.annotation.NonNull;
import org.luaj.vm2.LuaValue;

public class Fast_View extends View {
    private int widthPercentage = 0;
    private int heightPercentage = 0;
    private int xPercentage=0, yPercentage=0;
    public boolean update = false;
    public LuaValue Down=null;
    public LuaValue Up=null;
    public LuaValue Move=null;
    public LuaValue Draw=null;
    private int Fu_Width=0,Fu_Height=0;
    private Fast_View fun;
    private ViewTreeObserver.OnGlobalLayoutListener layoutListener;
    private ViewGroup parentView;
    public Fast_View(Context context) {
        super(context);
        fun = this;
        this.setId(View.generateViewId());
    }
    public int getTextHeight(String text){
        Paint paint = new Paint();
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        // 获取文本高度
        return bounds.height();
    }
    public int getTextWidth(String text){
        Paint paint = new Paint();
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        return bounds.width();
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
    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.TRANSPARENT);
        if(Draw != null && Draw.isfunction()){
            Draw.call(LuaValue.userdataOf(canvas));
        }
        if(update){
            invalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        int X, Y;
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                X = (int)event.getX();
                Y = (int)event.getY();
                if(Down != null && Down.isfunction()){
                    Down.call(LuaValue.valueOf(X), LuaValue.valueOf(Y));
                }
                break;
            case MotionEvent.ACTION_UP:
                X = (int)event.getX();
                Y = (int)event.getY();
                if(Up != null && Up.isfunction()){
                    Up.call(LuaValue.valueOf(X), LuaValue.valueOf(Y));
                }
                break;
            case MotionEvent.ACTION_MOVE:
                X = (int) event.getX();
                Y = (int) event.getY();
                if(Move != null && Move.isfunction()){
                    Move.call(LuaValue.valueOf(X), LuaValue.valueOf(Y));
                }
                break;
            default:
        }
        return true;
    }
}