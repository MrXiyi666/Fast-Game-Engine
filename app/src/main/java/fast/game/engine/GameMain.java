package fast.game.engine;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import fast.game.engine.fun.Fun_File;
import fast.game.engine.fun.LuaJInit;

public class GameMain extends View {
    public GameMain(Context context) {
        super(context);

    }
    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawARGB(255,255,0,0);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int X, Y;
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                X = (int)event.getX();
                Y = (int)event.getY();
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                X = (int) event.getX(); // 在移动时更新 X 坐标
                Y = (int) event.getY(); // 在移动时更新 Y 坐标
                break;
            default:

        }
        return super.onTouchEvent(event);
    }
}
