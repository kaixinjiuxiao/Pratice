package freedom.wealth.practice.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @author: captain
 * Time:  2018/3/27 0027
 * Describe:
 */
public  class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // ButterKnife.bind(this);
    }



}
