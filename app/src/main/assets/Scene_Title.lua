--======================================================
Scene_Title = {};
Scene_Title.__index = Scene_Title
--======================================================
function Scene_Title:new()
    local init = {};
    setmetatable(init, Scene_Title);
    init:Create();
    return init;
end;
--======================================================
function Scene_Title:Create()
    --这里如何在new初始化
    self:Create_Window();
    self:Scene_Sprite();
    self:Create_Back();
    self:Create_Paint();
    self:Create_Button();

end;
--======================================================
function Scene_Title:Scene_Sprite()
    self.sprite = Java:Sprite();
    self.sprite.Draw = function(c) self:sprite_draw(c); end;
    self.window_base:addChild(self.sprite);
end;
--======================================================
function Scene_Title:Create_Back()
    self.Bitmap = Java:Bitmap();
    self.back = self.Bitmap:loadBitmap("img/title/shamozun.png");
    self.back = self.Bitmap:ZoomSize(self.back, 60,60);
    self.back = self.Bitmap:getCircularBitmapWithBorder(self.back, 50, 100,255,255,255);
end;
--======================================================
function Scene_Title:Create_Paint()
    self.Paint = Java:Paint();
    self.Paint:setTextSize(20);
    self.paint = self.Paint:getPaint();
end;
--======================================================
function Scene_Title:Create_Button()
    self.open_game = Java:Button();
    self.open_game:setText("开始游戏");
    self.open_game:setColor(100, 0, 0, 0);
    self.open_game:setSize(60, 10);
    self.open_game:setXY(20, 50);
    self.open_game:setTextSize(10);
    self.open_game.Down = function()
        self.open_game:setColor(255, 255, 0, 0);
    end;
    self.open_game.Up = function()
        self.open_game:setColor(100, 0, 0, 0);
    end;
    self.keep_game = Java:Button();
    self.keep_game:setText("继续游戏");
    self.keep_game:setColor(100, 0, 0, 0);
    self.keep_game:setSize(60, 10);
    self.keep_game:setXY(20, 65);
    self.keep_game:setTextSize(10);
    self.keep_game.Down = function()
        self.keep_game:setColor(255, 255, 0, 0);
    end;
    self.keep_game.Up = function()
        self.keep_game:setColor(100, 0, 0, 0);
    end;
    self.window_base:addChild(self.open_game);
    self.window_base:addChild(self.keep_game);
end;
--======================================================
function Scene_Title:Create_Window()
    self.window_base = Java:Window();
    self.window_base:setSize(100, 100);
    self.window_base:setXY(0,0);
    self.window_base:openWindowTitle(false);
    self.window_base:openClick();
    self.window_base:closeClick();
    Java:addWindow(self.window_base);
    self.window_base.Close = function()
        Java:removeWindow(self.window_base);
    end;
    self.window_base.Click = function()
        Java:Mess("window_base 点击了");
    end;
    self.window_base.Up = function()
        --Java:Mess("window_base 点击了");
    end;
end;
--======================================================
function Scene_Title:sprite_draw(c)
    local canvas = Java:Canvas(c);
    canvas:drawBitmap(self.back, 100,100, self.paint);
end;
--======================================================
function Scene_Title:Start()

end;
--======================================================
function Scene_Title:Stop()

end;
--======================================================
function Scene_Title:Destroy()
    self.Bitmap:Release(self.back);
end;