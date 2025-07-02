--======================================================
Scene_Base = {};
Scene_Base.__index = Scene_Base
--======================================================
function Scene_Base:new()
    local init = {};
    setmetatable(init, self);
    self.__index = self
    return init;
end;
--======================================================
function Scene_Base:Create()
    self:Clear();
    self.Paint = Java:Paint();
    self.Paint:setTextSize(20);
    self.Bitmap = Java:Bitmap();
    self.window_base = Java:Window();
    self.window_base:setSize(100, 100);
    self.window_base:setXY(0, 0);
    self.window_base:setWindowTitle(false);
    Java:addWindow(self.window_base);
end;
--======================================================
function Scene_Base:Start()

end;
--======================================================
function Scene_Base:Stop()

end;
--======================================================
function Scene_Base:Destroy()
    self:Clear();
end;
--======================================================
function Scene_Base:Clear()
    Java:removeWindowAll();
    self.window_base=nil;
    self.Paint=nil;
    self.Bitmap=nil;
end;
--=========================生成随机数====================
function Scene_Base:Random_Number(a, b)
    math.randomseed(os.time());
   return math.random(a, b);
end