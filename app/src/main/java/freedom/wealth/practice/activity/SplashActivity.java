package freedom.wealth.practice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import freedom.wealth.practice.R;

/**
 * @author: captain
 * Time:  2018/3/27 0027
 * Describe:
 */
public class SplashActivity extends BaseActivity {
    @BindView(R.id.img)
    ImageView mImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        AlphaAnimation animation = new AlphaAnimation(1.0f, 0.0f);
        animation.setDuration(2000);
        animation.setFillAfter(true);
        mImg.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent();
                intent.setClass(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


}
