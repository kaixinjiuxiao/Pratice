package freedom.wealth.practice.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import freedom.wealth.practice.R;
import freedom.wealth.practice.activity.AboutActivity;
import freedom.wealth.practice.activity.CollectActivity;
import freedom.wealth.practice.activity.LoginActivity;
import freedom.wealth.practice.utils.PreferenceUtil;
import freedom.wealth.practice.view.CircleImageView;

/**
 * @author: captain
 * Time:  2018/3/27 0027
 * Describe:
 */
public class UserFragment extends BaseFragment {
    @BindView(R.id.photo)
    CircleImageView mPhoto;
    @BindView(R.id.userName)
    TextView mUserName;
    @BindView(R.id.relativeCollect)
    RelativeLayout mRelativeCollect;
    @BindView(R.id.relativeAbout)
    RelativeLayout mRelativeAbout;
    @BindView(R.id.relativeExit)
    RelativeLayout mRelativeExit;
    Unbinder unbinder;
    private View mView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_user, container, false);
            PreferenceUtil.init(getActivity());
            unbinder = ButterKnife.bind(this, mView);
        }
        return mView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.relativeCollect, R.id.relativeAbout, R.id.relativeExit,R.id.photo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.relativeCollect:
                Intent intent = new Intent();
                if(TextUtils.isEmpty(PreferenceUtil.getString("token",""))){
                    intent.setClass(getActivity(), LoginActivity.class);
                }else{
                    intent.setClass(getActivity(),CollectActivity.class);
                }
                startActivity(intent);
                break;
            case R.id.relativeAbout:
                Intent intent2 = new Intent(getActivity(), AboutActivity.class);
                startActivity(intent2);
                break;
            case R.id.relativeExit:
                break;
            case R.id.photo:
                break;
        }
    }


}
