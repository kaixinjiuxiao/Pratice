package freedom.wealth.practice.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import freedom.wealth.practice.R;

/**
 * Author : Captain
 * Time : 2018/3/28
 * Describe :
 */

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.imgBack)
    ImageView mImgBack;
    @BindView(R.id.username)
    EditText mUsername;
    @BindView(R.id.usernameWrapper)
    TextInputLayout mUsernameWrapper;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.passwordWrapper)
    TextInputLayout mPasswordWrapper;
    @BindView(R.id.passwordAgain)
    EditText mPasswordAgain;
    @BindView(R.id.passwordWrapperAgain)
    TextInputLayout mPasswordWrapperAgain;
    @BindView(R.id.register)
    Button mRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.imgBack, R.id.register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.register:
                register();
                break;
                default:
                    break;
        }
    }

    private void register() {
        String userName = mUsernameWrapper.getEditText().getText().toString();
        String password = mPasswordWrapper.getEditText().getText().toString();
        String passRepeat = mPasswordWrapperAgain.getEditText().getText().toString();
        if(TextUtils.isEmpty(userName)){
            mUsernameWrapper.setError("用户名不能为空");
            return;
        }
        if(TextUtils.isEmpty(password)){
            mPasswordWrapper.setError("密码不能为空");
            return;
        } if(TextUtils.isEmpty(passRepeat)&&!passRepeat.equals(passRepeat)){
            mPasswordWrapper.setError("两次输入密码不一致");
            return;
        }

    }
}
