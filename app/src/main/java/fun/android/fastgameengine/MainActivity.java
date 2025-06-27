package fun.android.fastgameengine;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import fast.game.engine.GameMain;
import fast.game.engine.fun.Fun;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fun.Create(this);
        setContentView(Fun.layout);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Fun.globals.get("Start").call();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Fun.globals.get("Stop").call();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Fun.globals.get("Destroy").call();
    }
}