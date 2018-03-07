package com.navinfo.wecloud.view.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.navinfo.wecloud.R;
import com.navinfo.wecloud.base.util.ClickUtil;
import com.navinfo.wecloud.inject.component.DaggerRegisterSetNewPwdComponent;
import com.navinfo.wecloud.inject.model.RegisterSetNewPwdModel;
import com.navinfo.wecloud.presenter.login.RegisterSetNewPwdPresenterImpl;
import com.navinfo.wecloud.view.base.BaseActivity;
import com.navinfo.wecloud.view.main.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static com.navinfo.wecloud.base.AppConfig.REGISTER;

/**
 * Created by liudepeng on 2018-2-25.
 * mail:liudepeng@navinfo.com
 */

public class RegisterSetNewPwdActivity extends BaseActivity implements RegisterSetNewPwdView {
    public static final String TAG = "RegisterSetNewPwdActivity";

    //type类型 REGISTER or FORGET_PASSWORD
    private int type = REGISTER;
    private String phoneNumber = "";

    @Inject
    RegisterSetNewPwdPresenterImpl registerSetNewPwdPresenter;

    @BindView(R.id.ib_activity_register_set_new_pwd_back)
    ImageButton ibActivityRegisterSetNewPwdBack;
    @BindView(R.id.tv_activity_register_set_new_pwd_title)
    TextView tvTitle;
    @BindView(R.id.et_activity_register_new_pwd)
    EditText etActivityRegisterNewPwd;
    @BindView(R.id.et_activity_register_confirm_pwd)
    EditText etActivityRegisterConfirmPwd;
    @BindView(R.id.btn_activity_register_new_pwd)
    Button btnNext;

    @Override
    public int getContentView() {
        return R.layout.activity_register_set_new_pwd;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerRegisterSetNewPwdComponent.builder().registerSetNewPwdModel(new RegisterSetNewPwdModel(this)).build().inject(this);

        type = getIntent().getIntExtra("type", REGISTER);
        phoneNumber = getIntent().getStringExtra("phoneNumber");
        initView();
    }

    private void initView() {
        if (type == REGISTER) {
            tvTitle.setText(R.string.login_register);
        } else {
            tvTitle.setText(R.string.forget_password);
        }
    }

    @OnClick({R.id.ib_activity_register_set_new_pwd_back, R.id.btn_activity_register_new_pwd})
    public void onClick(View view) {
        if (ClickUtil.isFastDoubleClick()) {
            return;
        }
        switch (view.getId()) {
            case R.id.ib_activity_register_set_new_pwd_back:
                finish();
                break;
            case R.id.btn_activity_register_new_pwd:
                if (etActivityRegisterConfirmPwd.getText().toString().length() < 6 ||
                        etActivityRegisterNewPwd.getText().toString().length() < 6) {
                    Toast.makeText(this, R.string.register_password_error, Toast.LENGTH_SHORT).show();
                } else {
                    if (etActivityRegisterConfirmPwd.getText().toString().equals(etActivityRegisterNewPwd.getText().toString())) {

                        registerSetNewPwdPresenter.setNewPwd(phoneNumber, etActivityRegisterNewPwd.getText().toString(), type, TAG, 0, true);
                    } else {
                        Toast.makeText(this, R.string.register_password_not_same_error, Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void goMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void dismissProgressDialog() {
        hideProgressDialog();
    }

    @Override
    public void onSetNewPwdStart() {
//        showProgressDialog();
    }

    @Override
    public void onSetNewPwdSuccess() {
//        hideProgressDialog();
        goMainActivity();
    }

    @Override
    public void onSetNewPwdError() {
//        hideProgressDialog();
    }
}
