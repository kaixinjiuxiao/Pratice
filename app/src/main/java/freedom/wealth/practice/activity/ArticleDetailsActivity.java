package freedom.wealth.practice.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
public class ArticleDetailsActivity extends BaseActivity {
    @BindView(R.id.imgBack)
    ImageView mImgBack;
    @BindView(R.id.articleTitle)
    TextView mArticleTitle;
    @BindView(R.id.webview)
    WebView mWebview;
    @BindView(R.id.progressbar)
    ProgressBar mProgressbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        String url = getIntent().getStringExtra("link");
        if(TextUtils.isEmpty(url)){
            return;
        }
        WebSettings webSettings = mWebview.getSettings();
        webSettings.setDefaultFontSize(16);
        webSettings.setSupportZoom(false);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setAllowFileAccess(true);// 设置允许访问文件数据
        webSettings.setBuiltInZoomControls(false);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setSavePassword(false);
        mWebview.requestFocusFromTouch();
        mWebview.setWebViewClient(new MyWebViewClient());
        mWebview.setWebChromeClient(new MyWebChromClient());
        mWebview.loadUrl(url);
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            mArticleTitle.setText(view.getTitle());
        }
    }

    private class MyWebChromClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
//                mProgressbar.setVisibility(View.VISIBLE);
//                if(newProgress>=95){
//                    mProgressbar.setVisibility(View.GONE);
//                }
        }
    }

    @OnClick(R.id.imgBack)
    public void onClick() {
        finish();
    }
}
