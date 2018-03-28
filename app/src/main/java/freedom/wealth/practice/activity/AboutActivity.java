package freedom.wealth.practice.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import freedom.wealth.practice.R;

/**
 * @author: captain
 * Time:  2018/3/28 0028
 * Describe:
 */
public class AboutActivity extends BaseActivity {
    @BindView(R.id.imgBack)
    ImageView mImgBack;
    @BindView(R.id.articleTitle)
    TextView mArticleTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.imgBack)
    public void onClick() {
        finish();
    }
}
