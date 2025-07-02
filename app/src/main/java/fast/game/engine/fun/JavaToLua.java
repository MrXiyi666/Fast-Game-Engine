package fast.game.engine.fun;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;
import fast.game.engine.View.Fun_View;
import fast.game.engine.View.Fun_ImageView;
import fast.game.engine.View.Fun_List;
import fast.game.engine.View.Fun_Text;
import fast.game.engine.View.Fun_List_Text;
import fast.game.engine.bitmap.Fun_Bitmap;
import fast.game.engine.View.Fun_Button;
import fast.game.engine.data.Fun_List_Horizontal_Data;
import fast.game.engine.paint.Fun_Paint;
import fast.game.engine.window.Fun_Scroll_Window;
import fast.game.engine.window.Fun_Scroll_Window_Text;
import fast.game.engine.window.Fun_Window;

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
    public Fun_View Sprite(){
        return new Fun_View(Fun.context);
    }
    public Fun_Button Button(){
        return new Fun_Button(Fun.context);
    }
    public Fun_Text Text(){
        return new Fun_Text(Fun.context);
    }
    public Fun_Window Window(){
        return new Fun_Window(Fun.context);
    }

    public Fun_Bitmap Bitmap(){
        return new Fun_Bitmap();
    }
    public Fun_Paint Paint(){
        return new Fun_Paint();
    }
    public void addWindow(Fun_Window window){
        Fun.activity.setContentView(window);
    }
    public void removeWindow(Fun_Window window){
        ViewGroup rootView = Fun.activity.findViewById(android.R.id.content);
        TransitionManager.beginDelayedTransition(rootView);
        rootView.removeView(window);
    }
    public void removeWindowAll(){
        ViewGroup rootView = Fun.activity.findViewById(android.R.id.content);
        TransitionManager.beginDelayedTransition(rootView);
        rootView.removeAllViews();
    }

    public Fun_List_Text ListText(){
        return new Fun_List_Text(Fun.context);
    }
    public Fun_List List(){
        return new Fun_List(Fun.context);
    }
    public Fun_ImageView Image(){
        return new Fun_ImageView(Fun.context);
    }

    public Fun_List_Horizontal_Data List_Horizontal_Data(){
        return new Fun_List_Horizontal_Data();
    }
    public Fun_List_Horizontal_Data List_Horizontal_Data(String path, String name){
        Fun_List_Horizontal_Data data = new Fun_List_Horizontal_Data();
        data.setBitmap(path);
        data.setName(name);
        return data;
    }
    public Fun_List_Horizontal_Data List_Horizontal_Data(Bitmap bitmap, String name){
        Fun_List_Horizontal_Data data = new Fun_List_Horizontal_Data();
        data.setBitmap(bitmap);
        data.setName(name);
        return data;
    }

    public Fun_Scroll_Window_Text Scroll_Window_Text(){
      return new Fun_Scroll_Window_Text(Fun.context);
    }

    public Fun_Scroll_Window Scroll_Window(){
        return new Fun_Scroll_Window(Fun.context);
    }


}
