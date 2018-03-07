package com.navinfo.wecloud.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.navinfo.wecloud.R;
import com.navinfo.wecloud.base.AppCache;
import com.navinfo.wecloud.base.util.SecurityUtils;
import com.navinfo.wecloud.base.util.ShareUtil;
import com.navinfo.wecloud.view.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudepeng on 2018-3-6.
 * mail:liudepeng@navinfo.com
 */

public class SettingActivity extends BaseActivity {

    @BindView(R.id.ib_activity_setting_back)
    ImageButton ibBack;
    @BindView(R.id.et_activity_setting_ScyPwd)
    EditText etSettingScyPwd;
    @BindView(R.id.tv_activity_setting_ScyPwd)
    TextView tvSettingScyPwd;

    @Override
    public int getContentView() {
        return R.layout.activity_setting;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @OnClick({R.id.ib_activity_setting_back, R.id.tv_activity_setting_ScyPwd})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_activity_setting_back:
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.tv_activity_setting_ScyPwd:
                if (etSettingScyPwd.getText().toString().length() == 6) {
                    ShareUtil.saveString(AppCache.ScyPwd, SecurityUtils.md5(etSettingScyPwd.getText().toString()), this);
                    Toast.makeText(this, "设置成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "安防密码格式有误", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

}
