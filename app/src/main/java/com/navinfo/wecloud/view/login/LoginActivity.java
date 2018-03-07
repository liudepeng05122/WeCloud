package com.navinfo.wecloud.view.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.navinfo.wecloud.R;
import com.navinfo.wecloud.base.util.ClickUtil;
import com.navinfo.wecloud.inject.component.DaggerLoginComponent;
import com.navinfo.wecloud.inject.model.LoginModel;
import com.navinfo.wecloud.presenter.login.LoginPresenterImpl;
import com.navinfo.wecloud.view.base.BaseActivity;
import com.navinfo.wecloud.view.main.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static com.navinfo.wecloud.base.AppConfig.FORGET_PASSWORD;
import static com.navinfo.wecloud.base.AppConfig.REGISTER;

/**
 * Created by liudepeng on 2018-2-25.
 * mail:liudepeng@navinfo.com
 */

public class LoginActivity extends BaseActivity implements LoginView {

    @Inject
    LoginPresenterImpl loginPresenter;

    @BindView(R.id.et_login_phone_number)
    EditText etLoginPhoneNumber;
    @BindView(R.id.et_login_password)
    EditText etLoginPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_forget_password)
    TextView tvForgetPassword;
    @BindView(R.id.tv_new_user)
    TextView tvNewUser;

    @Override
    public int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerLoginComponent.builder().loginModel(new LoginModel(this)).build().inject(this);
    }

    @OnClick({R.id.btn_login, R.id.tv_forget_password, R.id.tv_new_user})
    public void onClick(View view) {
        if (ClickUtil.isFastDoubleClick()) {
            return;
        }
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.tv_forget_password:
                goForgetPassword();
                break;
            case R.id.tv_new_user:
                goRegister();
                break;
            default:
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void login() {
        if (verifyData()) {
            showProgressDialog();
            loginPresenter.login(etLoginPhoneNumber.getText().toString(), etLoginPassword.getText().toString());
        } else {
            Toast.makeText(this, R.string.login_phone_number_error, Toast.LENGTH_SHORT).show();
        }
    }

    public boolean verifyData() {
        if (etLoginPhoneNumber.getText().toString().length() == 11 &&
                etLoginPassword.getText().toString().length() >= 6) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void goForgetPassword() {
        Intent intent = new Intent(this, RegisterActivity.class);
        intent.putExtra("type", FORGET_PASSWORD);
        startActivity(intent);
    }

    @Override
    public void goRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        intent.putExtra("type", REGISTER);
        startActivity(intent);
    }

    @Override
    public void goMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void dismissProgressDialog() {
        hideProgressDialog();
    }

}
