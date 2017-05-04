package takenoko.tech.blackboardapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import takenoko.tech.blackboardapp.model.StaticModel;
import takenoko.tech.blackboardapp.util.Debuger;

/**
 * Created by たけのこ on 2017/05/04.
 */

public class MainActivity extends AppCompatActivity {

    final static String log = "----MainActivty----";
    @BindView(R.id.menu_layout) RelativeLayout menuLayout;
    @BindView(R.id.open_menu_image) ImageView openMenuImage;
    @BindView(R.id.open_menu_text) TextView openMenuText;
    @BindView(R.id.debug_text) TextView debugText;
    @BindView(R.id.status_image) ImageView statusImage;
    @BindView(R.id.status_text) TextView statusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(log, "onCreate");
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Debuger.button(this);
        upDate();
    }

    public void upDate() {
        switch (StaticModel.getMenuMode()) {
            case INVISIBLE:
                menuLayout.setVisibility(View.INVISIBLE);
                openMenuImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu));
                openMenuText.setText("メニュー");
                break;
            default:
                menuLayout.setVisibility(View.VISIBLE);
                openMenuImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_close));
                openMenuText.setText("とじる");
                break;
        }
        switch (StaticModel.getPenMode()) {
            case PEN:
                statusImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_crayon));
                statusImage.setAlpha(100);
                statusText.setText("ペン");
                break;
            case ERASER:
                statusImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_blackbord_ere));
                statusImage.setAlpha(100);
                statusText.setText("けしごむ");
                break;
            default:
                break;
        }
    }
}
