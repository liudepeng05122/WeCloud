package com.navinfo.wecloud.view.remotecontrol;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.navinfo.wecloud.R;
import com.navinfo.wecloud.base.AppCache;
import com.navinfo.wecloud.base.AppConfig;
import com.navinfo.wecloud.base.util.SecurityUtils;
import com.navinfo.wecloud.base.widget.scypwd.ScyPwdUtil;
import com.navinfo.wecloud.presenter.remotecontrol.RemoteControlPresenterImpl;
import com.navinfo.wecloud.view.base.BaseFragment;
import com.navinfo.wecloud.view.main.SettingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liudepeng on 2018-3-5.
 * mail:liudepeng@navinfo.com
 */

public class VehicleControlFragment extends BaseFragment {
    private RemoteControlPresenterImpl remoteControlPresenter;

    public VehicleControlFragment() {
    }

    @SuppressLint("ValidFragment")
    public VehicleControlFragment(RemoteControlPresenterImpl remoteControlPresenter) {
        this.remoteControlPresenter = remoteControlPresenter;
    }

    @BindView(R.id.lnl_unlock)
    LinearLayout lnlUnLock;
    @BindView(R.id.lnl_lock)
    LinearLayout lnlLock;
    @BindView(R.id.lnl_hornflashinglights)
    LinearLayout lnlHornflashinglights;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vehicle_control, null);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @OnClick({R.id.lnl_unlock, R.id.lnl_lock, R.id.lnl_hornflashinglights})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lnl_unlock:
                if (!isPermit())
                    return;
                sendCommand(AppConfig.VEHICLE_COMMAND_UNLOCKING);
                break;
            case R.id.lnl_lock:
                if (!isPermit())
                    return;
                sendCommand(AppConfig.VEHICLE_COMMAND_SHUTTING);
                break;
            case R.id.lnl_hornflashinglights:
                if (!isPermit())
                    return;
                sendCommand(AppConfig.VEHICLE_COMMAND_LIGHT);
                break;
            default:
        }
    }

    private boolean isPermit() {
//        if (TextUtils.isEmpty(AppConfig.getInstance().getTokenId())) {
//            mContext.startActivity(new Intent(mContext, LoginActivity.class));
//            return false;
//        }

        return true;
    }

    private void sendCommand(int command) {
        if (AppCache.getInstance().isScyPwdVaild()) {

            if (!AppCache.getInstance().isFpControl()) {
//                HavalControlEvent event = new HavalControlEvent();
//                event.setCmdCode(command);
//                event.setFlag(0);
//                event.setScyPwd(AppCache.getInstance().getScyPwd());
//                EventBus.getDefault().post(event);
                remoteControlPresenter.sendCommand(command, 0, AppCache.getInstance().getScyPwd());
                return;
            } else {
                String selectedVin = AppConfig.getInstance().getVin();
                String tokenId = AppConfig.getInstance().getTokenId();
                String pwd = SecurityUtils.md5(selectedVin + tokenId);// 使用指纹验证时传入

//                HavalControlEvent event = new HavalControlEvent();
//                event.setCmdCode(command);
//                event.setFlag(1);
//                event.setScyPwd(pwd);
//                EventBus.getDefault().post(event);
                remoteControlPresenter.sendCommand(command, 1, pwd);
                return;
            }
        }

        startScyPwdVerify(command);
    }

    /**
     * 检验安防密码或指纹
     */
    private void startScyPwdVerify(final int cmdCode) {

        AppCache.CUR_CONTROL_CMD = cmdCode;

        ScyPwdUtil scyPwdUtil = ScyPwdUtil.getInstance(getActivity());
        scyPwdUtil.initScyPwd(R.id.iv_lock);
        scyPwdUtil.setSafetyPasswordUtilListener(new ScyPwdUtil.ScyPwdUtilListener() {
            @Override
            public void onUtilScyPwdFinish(String password, int flag) {

//                HavalControlEvent event = new HavalControlEvent();
//                event.setCmdCode(cmdCode);
//                event.setFlag(flag);
//                event.setScyPwd(password);
//                EventBus.getDefault().post(event);
                remoteControlPresenter.sendCommand(cmdCode, flag, password);
            }
        });

        scyPwdUtil.setOpenFingerListener(new ScyPwdUtil.ScyPwdUtilOpenFingerListener() {
            @Override
            public void onOpenFingerHint() {
                startActivity(new Intent(mContext, SettingActivity.class));
            }
        });
    }

}
