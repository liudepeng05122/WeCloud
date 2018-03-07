package com.navinfo.wecloud.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.navinfo.wecloud.R;
import com.navinfo.wecloud.base.util.ClickUtil;
import com.navinfo.wecloud.inject.component.DaggerRegisterSmsVerComponent;
import com.navinfo.wecloud.inject.model.RegisterSmsVerModel;
import com.navinfo.wecloud.presenter.login.RegisterSmsVerPresenterImpl;
import com.navinfo.wecloud.view.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static com.navinfo.wecloud.base.AppConfig.REGISTER;

/**
 * Created by liudepeng on 2018-2-25.
 * mail:liudepeng@navinfo.com
 */

public class RegisterSmsVerActivity extends BaseActivity implements RegisterSmsVerView {

    //type类型 REGISTER or FORGET_PASSWORD
    private int type = REGISTER;
    private String phoneNumber = "";

    @Inject
    RegisterSmsVerPresenterImpl registerSmsVerPresenter;

    @BindView(R.id.tv_activity_register_sms_title)
    TextView tvTitle;
    @BindView(R.id.ib_activity_register_sms_ver_back)
    ImageButton ibActivityRegisterSMSVerBack;
    @BindView(R.id.et_activity_register_sms_number)
    EditText etActivityRegisterSMSVerNumber;
    @BindView(R.id.btn_register_sms_next)
    Button btnNext;
    @BindView(R.id.tv_activity_register_sms_again)
    TextView tvActivityRegisterSMSAgain;

    @Override
    public int getContentView() {
        return R.layout.activity_register_sms_ver;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerRegisterSmsVerComponent.builder().registerSmsVerModel(new RegisterSmsVerModel(this)).build().inject(this);


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

    @OnClick({R.id.ib_activity_register_sms_ver_back, R.id.btn_register_sms_next, R.id.tv_activity_register_sms_again})
    public void onClick(View view) {
        if (ClickUtil.isFastDoubleClick()) {
            return;
        }
        switch (view.getId()) {
            case R.id.ib_activity_register_sms_ver_back:
                finish();
                break;
            case R.id.btn_register_sms_next:
                registerSmsVerPresenter.smsVerify(phoneNumber, etActivityRegisterSMSVerNumber.getText().toString(), type);
                break;
            case R.id.tv_activity_register_sms_again:

                break;
            default:
                break;
        }
    }

    @Override
    public void goRegisterSetNewPwd() {
        Intent intent = new Intent(this, RegisterSetNewPwdActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("phoneNumber", phoneNumber);
        startActivity(intent);
    }

    @Override
    public void dismissProgressDialog() {
        hideProgressDialog();
    }
}



