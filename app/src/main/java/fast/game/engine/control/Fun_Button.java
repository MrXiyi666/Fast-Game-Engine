package fast.game.engine.control;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.appcompat.widget.AppCompatButton;
import org.luaj.vm2.LuaValue;
import fast.game.engine.fun.Fun;
import fast.game.engine.window.Fun_Window;

public class Fun_Button extends AppCompatButton {
    private Paint paint;
    private int widthPercentage = 0;
    private int heightPercentage = 0;
    private int xPercentage=0, yPercentage=0;
    public boolean update = false;
    private Fun_Button fun;
    private Fun_Window parentView;
    private int Fu_Width=0,Fu_Height=0;
    private ViewTreeObserver.OnGlobalLayoutListener layoutListener;
    public LuaValue Click=null, Down=null, Up=null, Move=null;
    public Fun_Button(Context context) {
        super(context);
        fun = this;
        this.setId(View.generateViewId());
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.argb(192,0,0,0));
        this.setBackgroundColor(Color.TRANSPARENT);
        this.setTextColor(Color.WHITE);
        this.setOnClickListener(view -> {
            if(Click != null && Click.isfunction()){
                Click.call();
            }
        });

    }

    public void setColor(int a, int r, int g, int b){
        paint.setColor(Color.argb(a,r,g,b));
        invalidate();
    }

    @Override
    public void setTextSize(float size) {
        super.setTextSize(Fun.DpToPx(size));
        invalidate();
    }

    public void setTextColor(int a, int r, int g, int b){
        this.setTextColor(Color.argb(a,r,g,b));
        invalidate();
    }
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        // 获取父布局
        parentView = (Fun_Window) getParent();
        if (parentView != null) {
            layoutListener = () -> {
                int parentWidth = parentView.getWidth();
                int parentHeight = parentView.getHeight() + parentView.getPaddingTop();
                if (Fu_Width != parentWidth || Fu_Height !=parentHeight) {
                    int x = (int) (Fu_Width * fun.xPercentage / 100.0f);
                    int y = (int) (Fu_Height * fun.yPercentage / 100.0f);
                    fun.setX(x);
                    fun.setY(y);
                    ViewGroup.LayoutParams params = fun.getLayoutParams();
                    params.width = (int) (Fu_Width * fun.widthPercentage / 100.0f);;
                    params.height = (int) (Fu_Height * fun.heightPercentage / 100.0f);
                    fun.setLayoutParams(params);
                    Log.w("窗口", "测量了");
                }
                Fu_Width = parentWidth;
                Fu_Height = parentHeight;
                Log.w("窗口", "测量结束");
            };
            // 添加监听器
            parentView.getViewTreeObserver().addOnGlobalLayoutListener(layoutListener);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        // 移除监听器
        if (parentView != null && parentView.getViewTreeObserver().isAlive()) {
            parentView.getViewTreeObserver().removeOnGlobalLayoutListener(layoutListener);
            Log.w("窗口", "移除了");
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
            requestLayout();
        });

    }
    public void show(){
        this.setVisibility(View.VISIBLE);
    }

    public void hide(){
        this.setVisibility(View.GONE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRoundRect(new RectF(0,0,getWidth(), getHeight()), Fun.DpToPx(20), Fun.DpToPx(20), paint);
        super.onDraw(canvas);
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