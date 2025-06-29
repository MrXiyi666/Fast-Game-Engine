package fast.game.engine.data;

import android.graphics.Bitmap;
import fast.game.engine.fun.Fun;

public class Fun_List_Horizontal_Data {
    Bitmap bitmap=null;
    String name="";

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setBitmap(String path) {
        this.bitmap = Fun.loadBitmap(path);
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public String getName() {
        return name;
    }

}
