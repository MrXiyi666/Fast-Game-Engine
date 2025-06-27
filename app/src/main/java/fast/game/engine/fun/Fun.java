package fast.game.engine.fun;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

import org.luaj.vm2.Globals;

import fast.game.engine.layout.Layout;

public class Fun {
    public static Context context;
    public static Activity activity;
    public static int width, height;

    public static Layout layout;
    public static Globals globals;

    public static void Create(Activity c){
        activity = c;
        context=c;
        width = context.getResources().getDisplayMetrics().widthPixels;
        height = context.getResources().getDisplayMetrics().heightPixels;
        layout = new Layout(context);
        LuaJInit.Init();
    }

    public static int px2dp(float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int dp2px(float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
