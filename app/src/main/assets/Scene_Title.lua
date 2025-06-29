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
    self:Create_Text();
    self:Create_ListText();
    self:Create_List();
    self:Create_Scroll_Text();
end;
--======================================================
function Scene_Title:Scene_Sprite()
    self.sprite = Java:Sprite();
    self.sprite:setSize(100, 100);
    --self.sprite:setXY(0, 0);
    self.sprite.Draw = function(c)
        local canvas = Java:Canvas(c);
        canvas:drawBitmap(self.back, 0, 0, self.paint);
    end;
    --self.scroll:addChild(self.sprite);
end;
--======================================================
function Scene_Title:Create_Back()
    self.Bitmap = Java:Bitmap();
    self.back = self.Bitmap:loadBitmap("img/title/back.png");
    self.back = self.Bitmap:ZoomSize(self.back, 100,100);
end;
--======================================================
function Scene_Title:Create_Text()
    self.game_name = Java:Text();
    self.game_name:setSize(100, 50);
    --self.game_name:setXY(0, 30);
    self.game_name:setText("小小怪下士");
    self.game_name:setColor(0, 0, 0, 0);
    self.game_name:setTextSize(10);
    self.game_name:setTextColor(255, 0, 100, 0);
    self.game_name:setStrokeWidth(6);
    self.game_name:setLetterSpacing(1);
    --self.scroll:addChild(self.game_name);
end
--======================================================
function Scene_Title:Create_ListText()
    self.list_text = Java:ListText();
    self.list_text:setSize(30, 20);
    self.list_text:setXY(5, 20);
    self.size = 10;
    self.list_text.Click=function(position, name)
        if self.size < 50 then
            self.size = self.size + 10;
        else
            self.size = 0;
        end
        self.list_text:setHeight_Tage(self.size);
    end;

    self.list_text:addItem("药草");
    self.list_text:addItem("龙神剑");
    self.list_text:addItem("黑水晶");
    self.list_text:addItem("白水晶");
    self.list_text:addItem("紫水晶");
    self.list_text:addItem("大水晶");
    self.list_text:addItem("小水晶");
    self.list_text:addItem("扁水晶");
    self.list_text:addItem("胖水晶");
    self.window_base:addChild(self.list_text);
end
--======================================================
function Scene_Title:Create_List()
    self.list = Java:List();
    self.list:setSize(30, 20);
    self.list:setXY(40, 20);
    self.list.Click=function(position, name)
        if self.size < 50 then
            self.size = self.size + 10;
        else
            self.size = 0;
        end
        self.list:setHeight_Tage(self.size);
    end;
    self.list_1 = Java:List_Horizontal_Data("img/title/shamozun.png", "杀魔尊");
    self.list_2 = Java:List_Horizontal_Data("img/title/shamozun.png", "杀魔尊2");
    self.list_3 = Java:List_Horizontal_Data("img/title/shamozun.png", "杀魔尊3");
    self.list_4 = Java:List_Horizontal_Data("img/title/shamozun.png", "杀魔尊4");
    self.list_5 = Java:List_Horizontal_Data("img/title/shamozun.png", "杀魔尊5");
    self.list_6 = Java:List_Horizontal_Data("img/title/shamozun.png", "杀魔尊6");
    self.list_7 = Java:List_Horizontal_Data("img/title/shamozun.png", "杀魔尊7");
    self.list_8 = Java:List_Horizontal_Data("img/title/shamozun.png", "杀魔尊8");
    self.list_9 = Java:List_Horizontal_Data("img/title/shamozun.png", "杀魔尊9");
    self.list_10 = Java:List_Horizontal_Data("img/title/shamozun.png", "杀魔尊10");
    self.list:addItem(self.list_1);
    self.list:addItem(self.list_2);
    self.list:addItem(self.list_3);
    self.list:addItem(self.list_4);
    self.list:addItem(self.list_5);
    self.list:addItem(self.list_6);
    self.list:addItem(self.list_7);
    self.list:addItem(self.list_8);
    self.list:addItem(self.list_9);
    self.list:addItem(self.list_10);
    self.window_base:addChild(self.list);
end
--======================================================
function Scene_Title:Create_Scroll_Text()
    self.scroll_text = Java:Scroll_Window_Text();
    self.scroll_text:setSize(30, 20);
    self.scroll_text:setXY(5, 45);
    self.text = "九月的风裹着蝉鸣，林小满攥着录取通知书，在新生报到处撞见了江野。他穿着白衬衫，手腕随意缠着黑色发带，正弯腰帮同学搬运行李，阳光落在他睫毛上，碎成跳动的光斑。" ..
            "社团招新那天，林小满鬼使神差填了摄影社报名表。当她抱着相机在樱花树下取景时，江野突然出现，修长手指调整她的镜头角度：' 逆光拍花瓣，要把光圈调大。' 温热气息扫过耳畔，她红着脸后退半步。" ..
            "此后每个周末，江野都会带着她穿梭校园拍景。图书馆的光影、操场的黄昏、食堂冒热气的关东煮，都定格在相机里。某次暴雨，他脱下外套罩住她和相机，自己却淋得湿透，笑着说：' 相机可比我金贵。'" ..
            "樱花再开时，江野约她去天台。他低头摩挲相机，声音发颤：' 小满，这些照片里最美的... 是你。' 晚霞染红天际，他掏出藏在背后的胶片相册，每张照片边角都写着小字，全是关于她的心动瞬间。" ..
            "毕业后，他们带着装满回忆的相机，走过无数城市。每当樱花飘落，江野仍会为她调整镜头，轻声说：' 你看，我们的故事永远不会杀青。'";
    self.scroll_text:setText(self.text);

    self.window_base:addChild(self.scroll_text);
end
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
    --self.window_base:addChild(self.open_game);
    --self.window_base:addChild(self.keep_game);
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
function Scene_Title:Start()

end;
--======================================================
function Scene_Title:Stop()

end;
--======================================================
function Scene_Title:Destroy()
    self.Bitmap:Release(self.back);
end;