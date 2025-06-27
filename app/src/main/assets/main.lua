--======================主文件======================
Java:addLua("", "Scene_Title.lua");


function Create()
    scene = Scene_Title:new();
    scene.sprite:setSize(100, 100);
    scene.sprite:setXY(0, 0);
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