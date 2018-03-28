package freedom.wealth.practice.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import freedom.wealth.practice.R;
import freedom.wealth.practice.activity.ArticleDetailsActivity;
import freedom.wealth.practice.adapter.HomeArticleAdapter;
import freedom.wealth.practice.api.NetRequestUtils;
import freedom.wealth.practice.api.RequestApi;
import freedom.wealth.practice.entry.BannerResponse;
import freedom.wealth.practice.entry.HomeArticle;
import freedom.wealth.practice.utils.ToastUtils;
import freedom.wealth.practice.view.PullLoadMoreRecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author: captain
 * Time:  2018/3/27 0027
 * Describe:
 */
public class HomeFragment extends BaseFragment implements PullLoadMoreRecyclerView.PullLoadMoreListener {

    Unbinder unbinder;
    @BindView(R.id.banber)
    Banner mBanner;
    @BindView(R.id.pullLoadMore)
    PullLoadMoreRecyclerView mPullLoadMore;
    private View mView;
    private List<String> mDataBeans;
    private RecyclerView mRecyclerView;
    private List<HomeArticle.DataBean.DatasBean> mArticleList;
    private HomeArticleAdapter mAdapter;
    private int page =0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_home, container, false);
            unbinder = ButterKnife.bind(this, mView);
            initView();
            initData();
        }
        return mView;
    }

    private void initView() {
        mDataBeans = new ArrayList<>();
        mArticleList = new ArrayList<>();
        mRecyclerView = mPullLoadMore.getRecyclerView();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setVerticalScrollBarEnabled(true);
        mPullLoadMore.setRefreshing(true);
        mPullLoadMore.setIsLoadMore(false);
        mPullLoadMore.setOnPullLoadMoreListener(this);
        mAdapter = new HomeArticleAdapter(getActivity(),mArticleList);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnClickListener(new HomeArticleAdapter.OnClickListener() {
            @Override
            public void onClick(int position) {
                String url = mArticleList.get(position).getLink();
                Intent intent = new Intent(getActivity(), ArticleDetailsActivity.class);
                intent.putExtra("link",url);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        Call<BannerResponse> call = NetRequestUtils.getInstance().create(RequestApi.class).getBanner();
        call.enqueue(new Callback<BannerResponse>() {
            @Override
            public void onResponse(Call<BannerResponse> call, Response<BannerResponse> response) {
                if (response.body().getErrorCode() == 0) {
                    for (int i = 0; i < response.body().getData().size(); i++) {
                        mDataBeans.add(response.body().getData().get(i).getImagePath());
                    }
                    mBanner.setImageLoader(new GLideImageLoader());
                    mBanner.setImages(mDataBeans);
                    mBanner.start();

                } else {
                    ToastUtils.showShort(getActivity(), response.body().getErrorMsg());
                }
            }

            @Override
            public void onFailure(Call<BannerResponse> call, Throwable t) {

            }
        });
        getArticle(0);
    }

    private void getArticle(final int currentPage){
        Call<HomeArticle>articleCall = NetRequestUtils.getInstance().create(RequestApi.class)
                .getArticle(String.valueOf(currentPage));
        articleCall.enqueue(new Callback<HomeArticle>() {
            @Override
            public void onResponse(Call<HomeArticle> call, Response<HomeArticle> response) {
                if(response.body()==null){
                    ToastUtils.showShort(getActivity(),"请求出错");
                    return;
                }
                HomeArticle article = response.body();
                if (currentPage == 0) {
                    for (int i = 0; i < article.getData().getDatas().size(); i++) {
                        mArticleList.add(article.getData().getDatas().get(i));
                    }
                    mPullLoadMore.setAdapter(mAdapter);
                    mPullLoadMore.setPullLoadMoreCompleted();
                } else {
                    for (int i = 0; i < article.getData().getDatas().size(); i++) {
                        mArticleList.add(article.getData().getDatas().get(i));
                    }
                    mAdapter.notifyDataSetChanged();
                    mPullLoadMore.setPullLoadMoreCompleted();
                }
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<HomeArticle> call, Throwable t) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                page = 0;
                mArticleList.clear();
                if (mAdapter != null) {
                    mAdapter.notifyDataSetChanged();
                }
                getArticle(page);
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                page++;
                getArticle(page);
                mPullLoadMore.setPullLoadMoreCompleted();
            }
        }, 2000);
    }

    public class GLideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }
}
