package freedom.wealth.practice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import freedom.wealth.practice.R;
import freedom.wealth.practice.api.NetRequestUtils;
import freedom.wealth.practice.api.RequestApi;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author: captain
 * Time:  2018/3/28 0028
 * Describe:登录页面
 */
public class LoginActivity extends BaseActivity {
    @BindView(R.id.username)
    EditText mUsername;
    @BindView(R.id.usernameWrapper)
    TextInputLayout mUsernameWrapper;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.passwordWrapper)
    TextInputLayout mPasswordWrapper;
    @BindView(R.id.login)
    Button mLogin;
    @BindView(R.id.register)
    TextView mRegister;
    @BindView(R.id.imgBack)
    ImageView mImgBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.login, R.id.register,R.id.imgBack})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                login();
                break;
            case R.id.register:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
            case R.id.imgBack:
                finish();
                break;
                default:
                    break;
        }
    }

    private void login() {
        String userName = mUsernameWrapper.getEditText().getText().toString();
        String password = mPasswordWrapper.getEditText().getText().toString();
        if (TextUtils.isEmpty(userName)) {
            mUsernameWrapper.setError("用户名不能为空");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            mPasswordWrapper.setError("密码不能为空");
            return;
        }
        Call<ResponseBody> call = NetRequestUtils.getInstance().create(RequestApi.class)
                .login(userName, password);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

}
