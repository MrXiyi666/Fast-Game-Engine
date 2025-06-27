# 项目名称
快速游戏引擎。
#
使用教程
#
fast.game.engine 是游戏引擎源代码 复制到自己的Android 工程内 java文件夹下
#
assets 创建main.lua初始文档
#
导入libs内的jar luaj-jse-3.0.2.jar
#
MainActivity 写入如下代码
@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
Fun.Create(this);
}
#
用于初始化游戏引擎
#
添加一个窗口
```Lua
window_base = Java:Window();
window_base:setSize(70, 30);
window_base:setXY(15,30);
window_base:setWindowTitle(true);
Java:addWindow(window_base);
window_base.Close = function() Java:removeWindow(self.window_base); end;
window_base.Up = function() Java:Mess("点击了"); end;
```