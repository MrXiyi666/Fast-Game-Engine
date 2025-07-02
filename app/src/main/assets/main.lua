--======================主文件======================
Java:addLua("lua/", "Scene_Base.lua");
Java:addLua("lua/", "Scene_Title.lua");
Java:addLua("lua/", "Scene_Status.lua");

function Create()
    scene = Scene_Status:new();
    scene:Create();
end;

function Start()
    scene:Start();
end;

function Stop()
    scene:Stop();
end;

function Destroy()
    scene:Destroy();
end;