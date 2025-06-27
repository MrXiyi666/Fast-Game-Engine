package fast.game.engine.fun;

import android.graphics.Canvas;
import android.util.Log;
import android.widget.Toast;
import fast.game.engine.View.Fast_View;
import fast.game.engine.bitmap.Fun_Bitmap;
import fast.game.engine.control.Fun_Button;
import fast.game.engine.paint.Fun_Paint;

public class JavaToLua {

    public void Fun_Log(String name, String text){
        Log.w(name, text);
    }
    public void Mess(String text){
        Toast.makeText(Fun.context, text, Toast.LENGTH_SHORT).show();
    }

    public void addLua(String path, String name){
        Fun.globals.load(Fun_File.ReadTxt(path, name)).call();
    }
    public Canvas Canvas(Canvas canvas){
        return canvas;
    }
    public void drawRGB(Canvas canvas, int r, int g, int b){
        canvas.drawRGB(r,g,b);
    }
    public void addChild(Fast_View view){
        Fun.layout.addView(view);
    }

    public void addChild(Fun_Button view){
        Fun.layout.addView(view);
    }

    public Fast_View Sprite(){
        return new Fast_View(Fun.context);
    }

    public Fun_Button Button(){
        return new Fun_Button(Fun.context);
    }
    public Fun_Bitmap Bitmap(){
        return new Fun_Bitmap();
    }

    public Fun_Paint Paint(){
        return new Fun_Paint();
    }
}
