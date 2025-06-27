package fast.game.engine.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;
import org.luaj.vm2.LuaValue;
import fast.game.engine.fun.Fun;

public class Fast_View extends View {
    private Paint paint;
    private int widthPercentage = 1;
    private int heightPercentage = 1;
    private int xPercentage=1, yPercentage=1;
    public boolean update = false;
    public boolean bool_create = true;
    public LuaValue Down=null, Up=null, Move=null, Draw=null;
    public Fast_View(Context context) {
        super(context);
        this.setId(View.generateViewId());
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.argb(192,0,0,0));
    }
    public void setSize(int widthPercentage, int heightPercentage) {
        this.widthPercentage = widthPercentage;
        this.heightPercentage = heightPercentage;
        requestLayout();
    }
    public void setXY(int xPercentage, int yPercentage) {
        this.xPercentage = xPercentage;
        this.yPercentage = yPercentage;
        bool_create = true;
    }
    public void show(){
        this.setVisibility(View.VISIBLE);
    }

    public void hide(){
        this.setVisibility(View.GONE);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        if (getParent() instanceof View parentView) {
            int width = parentView.getWidth() * this.widthPercentage / 100;
            int height = parentView.getHeight() * this.heightPercentage / 100;
            setMeasuredDimension(width, height);
        }

    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        if(bool_create){
            if (getParent() instanceof View parentView) {
                int x = (int) (parentView.getWidth() * xPercentage / 100.0f);
                int y = (int) (parentView.getHeight() * yPercentage / 100.0f);
                setX(x);
                setY(y);
            }
            bool_create = false;
        }
        canvas.drawRoundRect(new RectF(0,0,getWidth(), getHeight()), Fun.dp2px(20), Fun.dp2px(20), paint);
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