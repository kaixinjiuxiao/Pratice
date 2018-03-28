package freedom.wealth.practice.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import freedom.wealth.practice.R;
import freedom.wealth.practice.entry.HomeArticle;
import freedom.wealth.practice.view.CircleImageView;

/**
 * @author: captain
 * Time:  2018/3/28 0028
 * Describe:
 */
public class CeshiAdapter extends RecyclerView.Adapter<CeshiAdapter.ArticalViewholder> {
    private Context mContext;
    private List<HomeArticle.DataBean.DatasBean> mList;
    private HomeArticleAdapter.OnClickListener mOnClickListener;

    public HomeArticleAdapter.OnClickListener getOnClickListener() {
        return mOnClickListener;
    }

    public void setOnClickListener(HomeArticleAdapter.OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    public CeshiAdapter(Context context, List<HomeArticle.DataBean.DatasBean> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public ArticalViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.ceshi,parent,false);

        return new ArticalViewholder(view);
    }



    @Override
    public void onBindViewHolder(ArticalViewholder holder, final int position) {
        holder.mAuthor.setText(mList.get(position).getAuthor());
        holder.mTime.setText(mList.get(position).getNiceDate());
        holder.mTitle.setText(mList.get(position).getTitle());
        holder.mClassify.setText(mList.get(position).getSuperChapterName());
//        if (mList.get(position).getZan() == 1) {
//            holder.mImgZan.setSelected(true);
//        }
//        holder.mLinearItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mOnClickListener.onClick(position);
//           }
//        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ArticalViewholder extends RecyclerView.ViewHolder {
       private CircleImageView mImgAuthor;
        private ImageView mImgZan;
        private TextView mAuthor,mTime,mTitle,mClassify;
        public ArticalViewholder(View itemView) {
            super(itemView);
            mImgAuthor = (CircleImageView)itemView.findViewById(R.id.imgAuthor);
           // mImgZan =(ImageView)itemView.findViewById(R.id.imgZan);
            mAuthor =(TextView)itemView.findViewById(R.id.author);
            mTime =(TextView)itemView.findViewById(R.id.time);
            mTitle =(TextView)itemView.findViewById(R.id.title);
            mClassify =(TextView)itemView.findViewById(R.id.classify);
        }
    }

    public interface OnClickListener {
        void onClick(int position);
    }
}
