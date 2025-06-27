package fast.game.engine.fun;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;

import org.luaj.vm2.Globals;

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
}
