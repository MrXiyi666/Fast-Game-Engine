package fun.android.fastgameengine;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import fast.game.engine.fun.Fun;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fun.Create(this);
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