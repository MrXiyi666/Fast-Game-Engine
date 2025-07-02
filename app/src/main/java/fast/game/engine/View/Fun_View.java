package fast.game.engine.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.annotation.NonNull;
import org.luaj.vm2.LuaValue;


public class Fun_View extends View {

    public boolean update = false;
    public LuaValue Down=null;
    public LuaValue Up=null;
    public LuaValue Move=null;
    public LuaValue Draw=null;
    public  LuaValue Click=null;
    public Fun_View(Context context) {
        super(context);
        this.setId(View.generateViewId());
        this.setVisibility(View.INVISIBLE);
        postDelayed(()->{
            setVisibility(View.VISIBLE);
        },10);
        this.setOnClickListener(view -> {
            if(Click != null && Click.isfunction()){
                Click.call();
            }
        });
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

    public void show(){
        this.setVisibility(View.VISIBLE);
    }

    public void hide(){
        this.setVisibility(View.GONE);
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