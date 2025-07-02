package fast.game.engine.View;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.TextViewCompat;
import org.luaj.vm2.LuaValue;
import fast.game.engine.fun.Fun;

public class Fun_Text extends AppCompatTextView {

    public int strokeWidth = 2;
    public int ma,mr,mg,mb;
    public int za,zr,zg,zb;
    public LuaValue Click=null;
    public Fun_Text(Context context) {
        super(context);
        this.setId(View.generateViewId());
        this.setBackgroundColor(Color.TRANSPARENT);
        this.setVisibility(View.INVISIBLE);
        postDelayed(()->{
            setVisibility(View.VISIBLE);
        },10);
        this.setGravity(Gravity.CENTER);
        ma = Color.alpha(getCurrentTextColor());
        mr = 255-Color.red(getCurrentTextColor());
        mg = 255-Color.green(getCurrentTextColor());
        mb = 255-Color.blue(getCurrentTextColor());
        za = Color.alpha(getCurrentTextColor());
        zr = Color.red(getCurrentTextColor());
        zg = Color.green(getCurrentTextColor());
        zb = Color.blue(getCurrentTextColor());
        this.setOnClickListener(view -> {
            if(Click != null && Click.isfunction()){
                Click.call();
            }
        });

    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
    }

    @Override
    public void setTextSize(float size) {
        super.setTextSize(Fun.DpToPx(size));
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
    @SuppressLint("RestrictedApi")
    public void openAutoSize(){
        setAutoSizeTextTypeWithDefaults(TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        setAutoSizeTextTypeUniformWithConfiguration(5, 100, 1, TypedValue.COMPLEX_UNIT_SP);
    }
    @SuppressLint("RestrictedApi")
    public void openAutoSize(int min, int max){
        setAutoSizeTextTypeWithDefaults(TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        setAutoSizeTextTypeUniformWithConfiguration(min, max, 1, TypedValue.COMPLEX_UNIT_SP);
    }
    @SuppressLint("RestrictedApi")
    public void closeAutoSize() {
        setAutoSizeTextTypeWithDefaults(TextViewCompat.AUTO_SIZE_TEXT_TYPE_NONE);
    }
    public void setColor(int a, int r, int g, int b){
        ma=a;mr=r;mg=g;mb=b;
        invalidate();
    }
    public void setTextColor(int a, int r, int g, int b){
        za=a;zr=r;zg=g;zb=b;
        invalidate();
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
    protected void onDraw(Canvas canvas) {
        getPaint().setStrokeWidth(strokeWidth);
        getPaint().setStyle(Paint.Style.STROKE);
        setTextColor(Color.argb(ma, mr, mg, mb));
        super.onDraw(canvas);
        getPaint().setStyle(Paint.Style.FILL);
        setTextColor(Color.argb(za, zr, zg, zb));
        super.onDraw(canvas);
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = Fun.DpToPx(strokeWidth);
    }
}
