package freedom.wealth.practice.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import freedom.wealth.practice.R;

/**
 * @author: captain
 * Time:  2018/3/27 0027
 * Describe:
 */
public class CeshiActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
    }

    private void initView() {
        ImageView oimg = findViewById(R.id.img);
        AlphaAnimation animation = new AlphaAnimation(1.0f, 0.0f);
        animation.setDuration(2000);
        animation.setFillAfter(true);
        oimg.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.e("tag", "???");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.e("tag", "???22222");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
