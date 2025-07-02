--=======================游戏开始界面===============================
Scene_Title = Scene_Base:new();
--======================================================
function Scene_Title:Create()
    Scene_Base.Create(self);
    self:Create_Window();
    self:Create_Bitmap();
    self:Scene_Sprite();
    self:Create_Button();
    self:Create_Text();
end;
--======================================================
function Scene_Title:Scene_Sprite()
    self.sprite = Java:Sprite();
    self.sprite:setSize(100, 100);
    self.sprite.Draw = function(c)
        local canvas = Java:Canvas(c);
        canvas:drawBitmap(self.back, 0, 0, self.paint);
    end;
    self.window_base:addChild(self.sprite);
end;
--======================================================
function Scene_Title:Create_Bitmap()
    self.back = self.Bitmap:loadBitmap("img/title/back.png");
    self.back = self.Bitmap:ZoomSize(self.back, 100,100);
end;
--======================================================
function Scene_Title:Create_Text()
    self.title_name1 = Java:Text();
    self.title_name1:setSize(100, 50);
    self.title_name1:setXY(0, 0);
    self.title_name1:setText("勇闯\n地下城");
    self.title_name1:setColor(255, 255, 255, 0);
    self.title_name1:setTextColor(255, 255, 0, 0);
    self.title_name1:setStrokeWidth(3);
    self.title_name1:setLetterSpacing(1);
    self.title_name1:openAutoSize(5,50);
    self.window_base:addChild(self.title_name1);
end;
--======================================================
function Scene_Title:Create_Button()
    self.open_game = Java:Button();
    self.open_game:setText("开始游戏");
    self.open_game:setColor(100, 0, 0, 0);
    self.open_game:setSize(60, 10);
    self.open_game:setXY(20, 60);
    self.open_game:openAutoSize(5,30);
    self.open_game.Down = function()
        self.open_game:setColor(255, 255, 0, 0);
    end;
    self.open_game.Up = function()
        self.open_game:setColor(100, 0, 0, 0);
    end;
    self.open_game.Click = function()
        scene = Scene_Status:new();
        scene:Create();
    end;
    self.keep_game = Java:Button();
    self.keep_game:setText("继续游戏");
    self.keep_game:setColor(100, 0, 0, 0);
    self.keep_game:setSize(60, 10);
    self.keep_game:setXY(20, 65);
    self.keep_game:openAutoSize(5,30);
    self.keep_game.Down = function()
        self.keep_game:setColor(255, 255, 0, 0);
    end;
    self.keep_game.Up = function()
        self.keep_game:setColor(100, 0, 0, 0);

    end;

    self.window_base:addChild(self.open_game);
    --self.window_base:addChild(self.keep_game);
end;
--======================================================
function Scene_Title:Create_Window()
    self.window_base = Java:Window();
    self.window_base:setSize(100, 100);
    self.window_base:setXY(0, 0);
    self.window_base:setWindowTitle(false);
    self.window_base:openClick();
    self.window_base:closeClick();
    Java:addWindow(self.window_base);
end;
--======================================================
function Scene_Title:Start()
    Scene_Base.Start(self);
end;
--======================================================
function Scene_Title:Stop()
    Scene_Base.Stop(self);
end;

--======================================================
function Scene_Title:Destroy()
    if self.Bitmap ~= nil then
        self.Bitmap:Release(self.back);
    end
    Scene_Base.Destroy(self);
    end;

--======================================================
function Scene_Title:Clear()
    Scene_Base.Clear(self);
    self.open_game=nil;
    self.keep_game=nil;
    self.title_name1=nil;
    self.back=nil;
    self.sprite=nil;
end