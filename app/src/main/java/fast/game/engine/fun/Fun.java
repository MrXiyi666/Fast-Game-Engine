package fast.game.engine.fun;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.WindowManager;

import org.luaj.vm2.Globals;

import java.io.IOException;
import java.io.InputStream;

public class Fun {
    public static Context context;
    public static Activity activity;
    public static int width, height;

    public static Globals globals;

    public static void Create(Activity c){
        activity = c;
        context=c;
        width = context.getResources().getDisplayMetrics().widthPixels;
        height = context.getResources().getDisplayMetrics().heightPixels;
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View decorView = activity.getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        LuaJInit.Init();
    }

    public static int PxToDp(float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int DpToPx(float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    // px转sp工具方法
    public static float PxToSp(float px) {
        return px / context.getResources().getDisplayMetrics().scaledDensity;
    }
    /**
     * 将 SP 值转换为 PX 值，保证文字大小不变
     * @param spValue SP 值
     * @return PX 值
     */
    public static int SpToPx(float spValue) {
        final float fontScale = Resources.getSystem().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f); // +0.5f 是为了四舍五入
    }
    public static Bitmap loadBitmap(String path) {
        AssetManager am = Fun.context.getAssets();
        InputStream inputStream = null;
        try {
            inputStream = am.open(path);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
