--======================主文件======================
Java:addLua("", "Scene_Title.lua");


function Create()
    scene = Scene_Title:new();
end

function Start()
    scene:Start();
end

function Stop()
    scene:Stop();
end

function Destroy()
    scene:Destroy();
end