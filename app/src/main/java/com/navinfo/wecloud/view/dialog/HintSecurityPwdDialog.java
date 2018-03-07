package com.navinfo.wecloud.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.navinfo.wecloud.R;


/**
 * <p>Title      : [HintSecurityPwdDialog]_[Dialog]</p>
 * <p>Description: [提示设置安防密码对话框]</p>
 * <p>Copyright  : Copyright (c) 2017</p>
 * <p>Company    : 北京四维图新科技股份有限公司</p>
 * <p>Department : Telematics业务部</p>
 *
 * @author : zhangzhichen
 * @version : 1.0
 */
public class HintSecurityPwdDialog extends Dialog implements View.OnClickListener {

    private OnHintSecurityPwdistener onHintSecurityPwdistener;

    public HintSecurityPwdDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public void setOnHintSecurityPwdistener(OnHintSecurityPwdistener onHintSecurityPwdistener) {
        this.onHintSecurityPwdistener = onHintSecurityPwdistener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_hint_security_pwd);
        findViewById(R.id.tv_cancel).setOnClickListener(this);
        findViewById(R.id.tv_setting).setOnClickListener(this);
    }


    public interface OnHintSecurityPwdistener {
        void onJumpSetSecurityPwd();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_setting:
                if(onHintSecurityPwdistener != null)
                    onHintSecurityPwdistener.onJumpSetSecurityPwd();

                dismiss();
                break;
            case R.id.tv_cancel:
                dismiss();
                break;
            default:
                break;
        }
    }
}
