package fast.game.engine.View;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.appcompat.widget.AppCompatImageView;


public class Fun_ImageView extends AppCompatImageView {
    public Fun_ImageView(Context context) {
        super(context);
        setScaleType(ScaleType.FIT_CENTER);
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

}