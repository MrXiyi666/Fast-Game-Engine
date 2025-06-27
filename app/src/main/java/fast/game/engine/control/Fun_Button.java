package fast.game.engine.control;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;

import org.luaj.vm2.LuaValue;

import fast.game.engine.fun.Fun;

public class Fun_Button extends AppCompatButton {
    private Paint paint;
    private int xPercentage=1, yPercentage=1;
    private int widthPercentage = 1;
    private int heightPercentage = 1;
    public boolean update = false;
    public boolean bool_create = true;
    public LuaValue Click=null;
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
        super.setTextSize(Fun.dp2px(size));
        invalidate();
    }

    public void setTextColor(int a, int r, int g, int b){
        this.setTextColor(Color.argb(a,r,g,b));
        invalidate();
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
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        int width = Fun.width * this.widthPercentage / 100;
        int height = Fun.height * this.heightPercentage / 100;
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRoundRect(new RectF(0,0,getWidth(), getHeight()), Fun.dp2px(20), Fun.dp2px(20), paint);
        if(bool_create){
            if (getParent() instanceof View parentView) {
                int x = (int) (Fun.width * xPercentage / 100.0f);
                int y = (int) (Fun.height * yPercentage / 100.0f);
                setX(x);
                setY(y);
            }
            bool_create = false;
        }
        super.onDraw(canvas);
    }
}
