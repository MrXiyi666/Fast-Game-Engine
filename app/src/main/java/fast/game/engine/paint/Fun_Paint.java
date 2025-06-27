package fast.game.engine.paint;

import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

import fast.game.engine.fun.Fun;

public class Fun_Paint {
    private Paint paint;
    public Fun_Paint(){
        this.paint = new Paint();
    }
    public Paint getPaint(){
        return paint;
    }

    public void setTextSize(int size){
        paint.setTextSize(Fun.dp2px(size));
    }
    public int getSize(){
        return Fun.px2dp(paint.getTextSize());
    }

    public int getTextHeight(String text){
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        int textHeight = bounds.height(); // 获取文本高度
        return textHeight;
    }

    public int getTextWidth(String text){
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        int textWidth = bounds.width(); // 获取文本宽度
        return textWidth;
    }
    public void setAntiAlias(boolean b){
        paint.setAntiAlias(b);
    }
    public void setColor(int a, int r, int g, int b){
        paint.setARGB(a, r, g, b);
    }

    public void setStrokeWidth(int index){
        paint.setStrokeWidth(index);
    }

    public void setShadowLayer(int radius, int dx, int dy, int a, int r, int g, int b){
        paint.setShadowLayer(radius, dx, dy, Color.argb(a,r,g,b));
    }

    public void setMaskFilter(int radius, int ordinal){
        switch(ordinal){
            case 0:
                paint.setMaskFilter(new BlurMaskFilter(Fun.dp2px(radius), BlurMaskFilter.Blur.INNER));
                break;
            case 1:
                paint.setMaskFilter(new BlurMaskFilter(Fun.dp2px(radius), BlurMaskFilter.Blur.NORMAL));
                break;
            case 2:
                paint.setMaskFilter(new BlurMaskFilter(Fun.dp2px(radius), BlurMaskFilter.Blur.OUTER));
                break;
            case 3:
                paint.setMaskFilter(new BlurMaskFilter(Fun.dp2px(radius), BlurMaskFilter.Blur.SOLID));
                break;
        }

    }

    public void setTypeface(int index){
        switch (index){
            case 0:
                paint.setTypeface(Typeface.DEFAULT);
                break;
            case 1:
                paint.setTypeface(Typeface.DEFAULT_BOLD);
                break;
            case 2:
                paint.setTypeface(Typeface.MONOSPACE);
                break;
            case 3:
                paint.setTypeface(Typeface.SANS_SERIF);
                break;
            case 4:
                paint.setTypeface(Typeface.SERIF);
                break;
        }

    }

    public void setTextAlign(int index){
        switch (index){
            case 0:
                paint.setTextAlign(Paint.Align.LEFT);
                break;
            case 1:
                paint.setTextAlign(Paint.Align.CENTER);
                break;
            case 2:
                paint.setTextAlign(Paint.Align.RIGHT);
                break;

        }

    }
}
