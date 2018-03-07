package com.navinfo.wecloud.view.login;

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
import com.navinfo.wecloud.inject.component.DaggerRegisterComponent;
import com.navinfo.wecloud.inject.model.RegisterModel;
import com.navinfo.wecloud.presenter.login.RegisterPresenterImpl;
import com.navinfo.wecloud.view.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static com.navinfo.wecloud.base.AppConfig.REGISTER;

/**
 * Created by liudepeng on 2018-2-25.
 * mail:liudepeng@navinfo.com
 */

public class RegisterActivity extends BaseActivity implements RegisterView {

    //type类型 REGISTER or FORGET_PASSWORD
    private int type = REGISTER;

    @Inject
    RegisterPresenterImpl registerPresenter;

    @BindView(R.id.tv_activity_register_title)
    TextView tvTitle;
    @BindView(R.id.ib_activity_register_back)
    ImageButton ibActivityRegisterBack;
    @BindView(R.id.et_activity_register_phone_number)
    EditText etActivityRegisterPhoneNumber;
    @BindView(R.id.btn_register)
    Button btnRegister;

    @Override
    public int getContentView() {
        return R.layout.activity_register;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerRegisterComponent.builder().registerModel(new RegisterModel(this)).build().inject(this);

        type = getIntent().getIntExtra("type", REGISTER);
        initView();
    }

    public void initView() {
        if (type == REGISTER) {
            tvTitle.setText(R.string.login_register);
            etActivityRegisterPhoneNumber.setHint(R.string.register_phone_number_hint);
        } else {
            tvTitle.setText(R.string.forget_password);
            etActivityRegisterPhoneNumber.setHint(R.string.forget_password_phone_number_hint);
        }
    }

    @OnClick({R.id.ib_activity_register_back, R.id.btn_register})
    public void onClick(View view) {
        if (ClickUtil.isFastDoubleClick()) {
            return;
        }
        switch (view.getId()) {
            case R.id.ib_activity_register_back:
                finish();
                break;
            case R.id.btn_register:
                if (phoneNumberVerify()) {
                    showProgressDialog();
                    registerPresenter.phoneNumberCommit(etActivityRegisterPhoneNumber.getText().toString(), type);
                } else {
                    Toast.makeText(this, R.string.register_phone_number_error, Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    private boolean phoneNumberVerify() {
        if (etActivityRegisterPhoneNumber.getText().toString().length() != 11) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void goRegisterSmsVerActivity() {
        Intent intent = new Intent(this, RegisterSmsVerActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("phoneNumber", etActivityRegisterPhoneNumber.getText().toString());
        startActivity(intent);
    }

    @Override
    public void dismissProgressDialog() {
        hideProgressDialog();
    }
}
