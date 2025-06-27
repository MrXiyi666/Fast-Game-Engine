package fast.game.engine.fun;

import android.content.Context;
import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Fun_File {
    public static String ReadTxt(String path, String name){
        StringBuilder script = new StringBuilder();
        try {
            InputStream inputStream = Fun.context.getAssets().open(path + name);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                script.append(line).append("\n");
            }
            reader.close();
            inputStream.close();
        }catch (Exception e){
            Log.w("Fun_File ReadTxt", e);
        }
        return script.toString();
    }
}