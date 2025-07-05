--========================================================
--=======================角色状态界面=======================
Scene_Status = Scene_Base:new();
--========================================================
function Scene_Status:Create()
    Scene_Base.Create(self);
    self:Create_Scroll_Window();
    self:Create_Background();
    self:Create_Sprite();
    self:Create_Basic_Information();
end;
--=======================状态背景图=======================
function Scene_Status:Create_Background()
    self.back = self.Bitmap:loadBitmap("img/status/back" .. self:Random_Number(0,4) .. ".png");
    self.back = self.Bitmap:ZoomSize(self.back, 100,100);
    self.avatar_bitmap = self.Bitmap:loadBitmap("img/status/avatar.png");
end;
--=======================背景精灵=======================
function Scene_Status:Create_Sprite()
    self.sprite = Java:Sprite();
    self.sprite:setSize(100, 200);
    self.sprite.Draw = function(c)
        local canvas = Java:Canvas(c);
        canvas:drawBitmap(self.back, 0, 0, self.paint);
    end;
    self.sprite.Click = function()
        self.back = self.Bitmap:loadBitmap("img/status/back" .. self:Random_Number(0,4) .. ".png");
        self.back = self.Bitmap:ZoomSize(self.back, 100,100);
        self.sprite:invalidate();
    end;
    self.scroll:addChild(self.sprite);
end;
--=======================人物基础信息=======================
function Scene_Status:Create_Basic_Information()
    self.avatar_image = Java:Image();
    self.avatar_image:setSize(100, 100);
    self.avatar_image:setXY(0, 0);
    self.avatar_image:setImageBitmap(self.avatar_bitmap);
    self.avatar_window = Java:Window();
    self.avatar_window:setSize(30, 14);
    self.avatar_window:setXY(65, 5);
    self.avatar_window:setWindowTitle(false);
    self.avatar_window:addChild(self.avatar_image);

    self.name_text = Java:Text();
    self.name_text:setText("姓名：");
    self.name_text:setGravity(16);
    self.name_text:setSize(100, 5);
    self.name_text:setXY(2, 5);
    self.name_text:setTextColor(255, 100, 100, 200);
    self.name_text:setStrokeWidth(3);

    self.name_data_text = Java:Text();
    self.name_data_text:setText("谢羽");
    self.name_data_text:setGravity(16);
    self.name_data_text:setSize(100, 5);
    self.name_data_text:setXY(25, 5);
    self.name_data_text:setTextColor(255, 0, 0, 0);
    self.name_data_text:setStrokeWidth(3);
    self.name_data_text.Click = function()
        Java:Mess("修改名字");
    end;

    self.boundary_text = Java:Text();
    self.boundary_text:setGravity(16);
    self.boundary_text:setText("境界：");
    self.boundary_text:setSize(100, 5);
    self.boundary_text:setXY(2, 10);
    self.boundary_text:setTextColor(255, 100, 100, 200);
    self.boundary_text:setStrokeWidth(3);

    self.boundary_data_text = Java:Text();
    self.boundary_data_text:setGravity(16);
    self.boundary_data_text:setText("初级");
    self.boundary_data_text:setSize(100, 5);
    self.boundary_data_text:setXY(25, 10);
    self.boundary_data_text:setTextColor(255, 0, 0, 0);
    self.boundary_data_text:setStrokeWidth(3);

    self.equip_text = Java:Text();
    self.equip_text:setText("装备");
    self.equip_text:setSize(100, 5);
    self.equip_text:setGravity(16);
    self.equip_text:setXY(2, 15);
    self.equip_text:setTextColor(255, 100, 100, 200);
    self.equip_text:setStrokeWidth(3);

    self.arms_text = Java:Text();
    self.arms_text:setText("武器：");
    self.arms_text:setGravity(16);
    self.arms_text:setSize(100, 5);
    self.arms_text:setXY(2, 20);
    self.arms_text:setTextColor(255, 100, 100, 200);
    self.arms_text:setStrokeWidth(3);

    self.arms_data_text = Java:Text();
    self.arms_data_text:setText("战魂刀");
    self.arms_data_text:setGravity(16);
    self.arms_data_text:setSize(100, 5);
    self.arms_data_text:setXY(25, 20);
    self.arms_data_text:setTextColor(255, 0, 0, 0);
    self.arms_data_text:setStrokeWidth(3);

    self.dress_text = Java:Text();
    self.dress_text:setText("衣服：");
    self.dress_text:setGravity(16);
    self.dress_text:setSize(100, 5);
    self.dress_text:setXY(2, 25);
    self.dress_text:setTextColor(255, 100, 100, 200);
    self.dress_text:setStrokeWidth(3);

    self.dress_data_text = Java:Text();
    self.dress_data_text:setText("粗布衣");
    self.dress_data_text:setGravity(16);
    self.dress_data_text:setSize(100, 5);
    self.dress_data_text:setXY(25, 25);
    self.dress_data_text:setTextColor(255, 0, 0, 0);
    self.dress_data_text:setStrokeWidth(3);

    self.bute_text = Java:Text();
    self.bute_text:setText("属性：");
    self.bute_text:setGravity(16);
    self.bute_text:setSize(100, 5);
    self.bute_text:setXY(2, 35);
    self.bute_text:setTextColor(255, 100, 100, 200);
    self.bute_text:setStrokeWidth(3);
    self.scroll_text = Java:Scroll_Window_Text();
    self.scroll_text:setText("力量：10\n速度：10\n防御：10\n敏捷：10\n幸运：10\n暴击：10\n\n");
    self.scroll_text:setSize(70, 30);
    self.scroll_text:setXY(25, 35);

    self.scroll:addChild(self.avatar_window);
    self.scroll:addChild(self.name_text);
    self.scroll:addChild(self.name_data_text);
    self.scroll:addChild(self.boundary_text);
    self.scroll:addChild(self.boundary_data_text);
    self.scroll:addChild(self.equip_text);
    self.scroll:addChild(self.arms_text);
    self.scroll:addChild(self.arms_data_text);
    self.scroll:addChild(self.dress_text);
    self.scroll:addChild(self.dress_data_text);
    self.scroll:addChild(self.bute_text);
    self.scroll:addChild(self.scroll_text);
end;
--======================================================
function Scene_Status:Create_Scroll_Window()
    self.scroll = Java:Scroll_Window();
    self.scroll:setSize(80, 60);
    self.scroll:setXY(10,10);
    self.window_base:addChild(self.scroll);
end;
--======================================================
function Scene_Status:Start()
    Scene_Base.Start(self);
end;
--======================================================
function Scene_Status:Stop()
    Scene_Base.Stop(self);
end;
--======================================================
function Scene_Status:Destroy()
    Scene_Base.Destroy(self);
end;
--======================================================
function Scene_Status:Clear()
    Scene_Base.Clear(self);
    self.back=nil;
end;