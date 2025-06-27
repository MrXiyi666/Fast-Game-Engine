package fast.game.engine.layout;

import android.content.Context;
import android.graphics.Color;
import android.widget.RelativeLayout;

public class Layout extends RelativeLayout {

    public Layout(Context context) {
        super(context);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        this.setLayoutParams(layoutParams);
        this.setBackgroundColor(Color.rgb(150,150,150));
    }
}
