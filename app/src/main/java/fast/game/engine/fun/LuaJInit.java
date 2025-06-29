package fast.game.engine.fun;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.JsePlatform;
public class LuaJInit {
    public static void Init(){
        Fun.globals = JsePlatform.standardGlobals();
        Fun.globals.set("Java", CoerceJavaToLua.coerce(new JavaToLua()));
        LuaValue chunk = Fun.globals.load(Fun_File.ReadTxt("", "main.lua"));
        chunk.call();
        // 获取Lua函数
        Fun.globals.get("Create").call();
    }
}
