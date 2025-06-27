package fast.game.engine.fun;

import android.content.res.AssetManager;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.JsePlatform;
public class LuaJInit {
    public static void Init(){
        // 获取Lua全局环境
        Fun.globals = JsePlatform.standardGlobals();
        Fun.globals.set("Java", CoerceJavaToLua.coerce(new JavaToLua()));
        // 加载并执行Lua脚本
        LuaValue chunk = Fun.globals.load(Fun_File.ReadTxt("", "main.lua"));
        chunk.call();

        // 获取Lua函数
        Fun.globals.get("Create").call();
    }
}
