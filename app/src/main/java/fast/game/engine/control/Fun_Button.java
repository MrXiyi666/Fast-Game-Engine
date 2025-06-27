package fast.game.engine.control;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.appcompat.widget.AppCompatButton;
import org.luaj.vm2.LuaValue;
import fast.game.engine.fun.Fun;

public class Fun_Button extends AppCompatButton {
    private Paint paint;
    private int widthPercentage = 1;
    private int heightPercentage = 1;
    public boolean update = false;
    public LuaValue Click=null, Down=null, Up=null, Move=null;
    public Fun_Button(Context context) {
        super(context);
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

    public void setSize(int widthPercentage, int heightPercentage) {
        this.widthPercentage = widthPercentage;
        this.heightPercentage = heightPercentage;
        post(() -> {
            ViewParent parent = getParent();
            if (parent instanceof ViewGroup) {
                ViewGroup parentViewGroup = (ViewGroup) parent;

                // 获取父ViewGroup的宽高
                int parentWidth = parentViewGroup.getWidth();
                int parentHeight = parentViewGroup.getHeight();

                // 如果父ViewGroup的宽高尚未确定，则不进行位置调整
                if (parentWidth > 0 && parentHeight > 0) {
                    // 计算当前View的宽高
                    int width = parentWidth * this.widthPercentage / 100;
                    int height = parentHeight * this.heightPercentage / 100;
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
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec,heightMeasureSpec);
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
                setMeasuredDimension(width, height);
                requestLayout();
            }
        }
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