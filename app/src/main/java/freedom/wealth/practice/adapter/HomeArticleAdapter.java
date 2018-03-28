package freedom.wealth.practice.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import freedom.wealth.practice.R;
import freedom.wealth.practice.entry.HomeArticle;
import freedom.wealth.practice.view.CircleImageView;

/**
 * @author: captain
 * Time:  2018/3/28 0028
 * Describe:
 */
public class HomeArticleAdapter extends RecyclerView.Adapter<HomeArticleAdapter.ArticalViewholder> {



    private Context mContext;
    private List<HomeArticle.DataBean.DatasBean> mList;
    private OnClickListener mOnClickListener;

    public OnClickListener getOnClickListener() {
        return mOnClickListener;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    public HomeArticleAdapter(Context context, List<HomeArticle.DataBean.DatasBean> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public ArticalViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_article, parent, false);
        return new ArticalViewholder(v);
    }

    @Override
    public void onBindViewHolder(ArticalViewholder holder, final int position) {
        holder.mAuthor.setText(mList.get(position).getAuthor());
        holder.mTime.setText(mList.get(position).getNiceDate());
        holder.mTitle.setText(mList.get(position).getTitle());
        holder.mClassify.setText(mList.get(position).getSuperChapterName());
        if (mList.get(position).getZan() == 1) {
            holder.mImgZan.setSelected(true);
        }
        holder.mLinearItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnClickListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ArticalViewholder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgAuthor)
        CircleImageView mImgAuthor;
        @BindView(R.id.author)
        TextView mAuthor;
        @BindView(R.id.time)
        TextView mTime;
        @BindView(R.id.title)
        TextView mTitle;
        @BindView(R.id.classify)
        TextView mClassify;
        @BindView(R.id.imgZan)
        ImageView mImgZan;
        @BindView(R.id.linearItem)
        LinearLayout mLinearItem;
        public ArticalViewholder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnClickListener {
        void onClick(int position);
    }
}
