package freedom.wealth.practice.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import freedom.wealth.practice.R;
import freedom.wealth.practice.fragment.ClassifyFragment;
import freedom.wealth.practice.fragment.HomeFragment;
import freedom.wealth.practice.fragment.UserFragment;
import freedom.wealth.practice.utils.FragmentController;

public class MainActivity extends BaseActivity {

    @BindView(R.id.imgHome)
    ImageView mImgHome;
    @BindView(R.id.txtHome)
    TextView mTxtHome;
    @BindView(R.id.linearHome)
    LinearLayout mLinearHome;
    @BindView(R.id.imgFl)
    ImageView mImgFl;
    @BindView(R.id.txtFl)
    TextView mTxtFl;
    @BindView(R.id.linearFenLei)
    LinearLayout mLinearFenLei;
    @BindView(R.id.imgUser)
    ImageView mImgUser;
    @BindView(R.id.txtUser)
    TextView mTxtUser;
    @BindView(R.id.linearUser)
    LinearLayout mLinearUser;
    private FragmentController mController;
    private List<Fragment> mFragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();

    }


    public void initView() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new HomeFragment());
        mFragmentList.add(new ClassifyFragment());
        mFragmentList.add(new UserFragment());
        mController = FragmentController.getInstance(this, R.id.content, true, mFragmentList);
        mController.showFragment(0);
        mImgHome.setSelected(true);
        mTxtHome.setSelected(true);
    }

    @OnClick({R.id.linearHome, R.id.linearFenLei, R.id.linearUser})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linearHome:
                setSelected();
                mImgHome.setSelected(true);
                mTxtHome.setSelected(true);
                mController.showFragment(0);
                break;
            case R.id.linearFenLei:
                setSelected();
                mImgFl.setSelected(true);
                mImgFl.setSelected(true);
                mController.showFragment(1);
                break;
            case R.id.linearUser:
                setSelected();
                mImgUser.setSelected(true);
                mTxtUser.setSelected(true);
                mController.showFragment(2);
                break;
        }
    }

    private void setSelected(){
        mImgHome.setSelected(false);
        mTxtHome.setSelected(false);
        mImgFl.setSelected(false);
        mTxtFl.setSelected(false);
        mImgUser.setSelected(false);
        mTxtUser.setSelected(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mController.onDestroy();
    }
}
