package fast.game.engine.bitmap;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;

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

    public void Release(Bitmap bitmap){
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float cornerRadius) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0); // 确保Canvas背景透明

        // 修正：使用不透明颜色绘制圆角矩形（颜色值任意，只要不透明）
        paint.setColor(0xFF000000); // 黑色，非透明
        canvas.drawRoundRect(rectF, Fun.DpToPx(cornerRadius), Fun.DpToPx(cornerRadius), paint);

        // 设置混合模式，只保留圆角区域内的原图内容
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    public static Bitmap getCircularBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int diameter = Math.min(width, height);

        Bitmap output = Bitmap.createBitmap(diameter, diameter, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect srcRect = new Rect(0, 0, width, height);
        final Rect dstRect = new Rect(0, 0, diameter, diameter);

        // 创建着色器，将原图铺满整个圆形区域
        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setAntiAlias(true);

        // 绘制圆形
        float radius = diameter / 2f;
        canvas.drawCircle(radius, radius, radius, paint);

        return output;
    }

    public static Bitmap getCircularBitmapWithBorder(Bitmap bitmap, int borderWidth, int a, int r, int g, int b) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int diameter = Math.min(width, height);
        Bitmap output = Bitmap.createBitmap(diameter, diameter, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Paint borderPaint = new Paint();

        // 将 borderWidth 从 dp 转换为像素（确保所有计算使用相同单位）
        float borderWidthPx = Fun.DpToPx(borderWidth);
        float center = diameter / 2f;

        // 图像圆半径 = 最大半径 - 边框宽度（像素单位）
        float imageRadius = center - borderWidthPx;

        // 绘制内层圆形（图片）
        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setAntiAlias(true);
        canvas.drawCircle(center, center, imageRadius, paint);

        // 绘制边框（使用像素单位的边框宽度）
        borderPaint.setColor(Color.argb(a, r, g, b));
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(borderWidthPx); // 修正：使用像素单位
        borderPaint.setAntiAlias(true);

        // 边框圆半径 = 图像圆半径 + 边框宽度的一半（像素单位）
        canvas.drawCircle(center, center, imageRadius + borderWidthPx/2f, borderPaint);

        return output;
    }
}
