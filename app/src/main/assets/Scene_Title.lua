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

    self.open_game:setColor(100, 255, 255, 0);
    self.open_game:setTextSize(10);
    self.open_game.update = false;
    self.open_game.Click = function()
        Java:Mess("点击了");
        --self.open_game:setXY(self.index, 30);
        self.open_game:setSize(self.index, 30);
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
    --self.window_base:addChild(self.open_game);
    self.open_game:setSize(60, 30);
    self.open_game:setXY(15, 30);
end;
--======================================================
function Scene_Title:Create_Window()
    self.window_base = Java:Window();
    self.window_base:setSize(70, 30);
    self.window_base:setXY(15,30);
    self.window_base:setWindowTitle(true);
    Java:addWindow(self.window_base);
    self.window_base.Close = function()
        Java:removeWindow(self.window_base);
    end;
    self.window_base.Up = function()
        Java:Mess("点击了");
        self.window_base:setXY(self.index, 30);

        self.index = self.index+10;
        if self.index > 60 then
            self.index=20;
        end
    end;
    self.window_base2 = Java:Window();
    self.window_base2:setSize(60, 30);
    self.window_base2:setXY(15,30);
    self.window_base2:setWindowTitle(true);
    self.window_base:addChild(self.window_base2);
    self.window_base2.Close = function()
        self.window_base:removeChild(self.window_base2);

    end;
    self.window_base2.Up = function()
        Java:Mess("点击了");
        self.window_base2:setXY(self.index, 30);

        self.index = self.index+10;
        if self.index > 60 then
            self.index=20;
        end
    end;
end;
--======================================================
function Scene_Title:sprite_draw(c)
    local canvas = Java:Canvas(c);
    canvas:drawBitmap(self.back, 0,0, self.paint);
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