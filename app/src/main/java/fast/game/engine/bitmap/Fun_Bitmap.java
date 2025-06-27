package fast.game.engine.bitmap;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import java.io.IOException;
import java.io.InputStream;

import fast.game.engine.fun.Fun;

public class Fun_Bitmap{
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

    public static Bitmap loadBitmap(String path, int PageWidth, int PageHeight) {
        AssetManager am = Fun.context.getAssets();
        InputStream inputStream = null;
        try {
            inputStream = am.open(path);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            int targetWidth = (int) (Fun.width * PageWidth / 100.0f);
            int targetHeight = (int) (Fun.height * PageHeight / 100.0f);
            return Bitmap.createScaledBitmap(bitmap, targetWidth, targetHeight, true);
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

    public Canvas getCanvas(Bitmap bitmap){
        return new Canvas(bitmap);
    }

    public Bitmap ZoomSize(Bitmap bitmap, int PageWidth, int PageHeight){
        int targetWidth = (int) (Fun.width * PageWidth / 100.0f);
        int targetHeight = (int) (Fun.height * PageHeight / 100.0f);
       return Bitmap.createScaledBitmap(bitmap, targetWidth, targetHeight, true);
    }

    public Bitmap ZoomWidth(Bitmap bitmap, int PageData){
        int targetWidth = (int) (Fun.width * PageData / 100.0f);
        return Bitmap.createScaledBitmap(bitmap, targetWidth, bitmap.getHeight(), true);
    }

    public Bitmap ZoomHeight(Bitmap bitmap, int PageData){
        int targetHeight = (int) (Fun.width * PageData / 100.0f);
        return Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), targetHeight, true);
    }


}
