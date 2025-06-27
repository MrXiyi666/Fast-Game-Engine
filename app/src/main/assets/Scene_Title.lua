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
    self:Scene_Sprite();
    self:Create_Back();
    self:Create_Paint();
    self:Create_Button();
    self:Create_Window();
end;
--======================================================
function Scene_Title:Scene_Sprite()
    self.sprite = Java:Sprite();
    self.sprite.Draw = function(c) self:sprite_draw(c); end;
end;
--======================================================
function Scene_Title:Create_Back()
    self.Bitmap = Java:Bitmap();
    self.back = self.Bitmap:loadBitmap("img/title/back.png");
    self.back = self.Bitmap:ZoomSize(self.back, 100,100);
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
    self.open_game:setSize(60,10);
    self.open_game:setXY(20,30);
    self.open_game:setColor(100, 255, 255, 0);
    self.open_game:setTextSize(10);
    self.open_game.update = true;
    self.open_game.Click = function()
        Java:Mess("点击了");
        self.open_game:setXY(self.index,50);
        self.index = self.index+10;
        if self.index > 60 then
            self.index=20;
        end
    end;
    self.open_game.Down = function()
        self.open_game:setColor(255, 255, 0, 0);
    end;
    self.open_game.Up = function()
        self.open_game:setColor(100, 255, 255, 0);
    end;
    self.index = 20;

end;
--======================================================
function Scene_Title:Create_Window()
    self.window_base = Java:Window();
    self.window_base:setSize(70,50);
    self.window_base:setXY(15,20);
    self.window_base:setWindowTitle(false);
    self.window_base.Close = function()
        Java:removeWindow(self.window_base);
    end;
    self.window_base:addChild(self.open_game);
    Java:addWindow(self.window_base);
end;
--======================================================
function Scene_Title:sprite_draw(c)
    local canvas = Java:Canvas(c);
    canvas:drawBitmap(self.back, 0,0, self.paint);
end;
--======================================================
function Scene_Title:open_draw(c)
    local canvas = Java:Canvas(c);
    --canvas:drawBitmap(self.back, 0,0, self.paint);
    self.Paint:setTextAlign(1);
    self.Paint:setTextSize(30);
    self.Paint:setColor(255, 255, 255, 255);
    canvas:drawText("开始游戏", self.open_game:getWidth()/2, self.Paint:getTextHeight("开始游戏"), self.paint);
end;
--======================================================
function Scene_Title:open_up(x, y)
    Java:Mess("按下了OpenGame" .. x .. "  " .. y);
end;
--======================================================
function Scene_Title:Start()

end;
--======================================================
function Scene_Title:Stop()

end;
--======================================================
function Scene_Title:Destroy()

end;