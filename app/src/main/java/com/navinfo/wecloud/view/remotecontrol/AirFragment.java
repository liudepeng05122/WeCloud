package com.navinfo.wecloud.view.remotecontrol;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.navinfo.wecloud.R;
import com.navinfo.wecloud.presenter.remotecontrol.RemoteControlPresenterImpl;
import com.navinfo.wecloud.view.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liudepeng on 2018-3-5.
 * mail:liudepeng@navinfo.com
 */

public class AirFragment extends BaseFragment {
    private RemoteControlPresenterImpl remoteControlPresenter;

    public AirFragment() {
    }

    @SuppressLint("ValidFragment")
    public AirFragment(RemoteControlPresenterImpl remoteControlPresenter) {
        this.remoteControlPresenter = remoteControlPresenter;
    }

    @BindView(R.id.sb_runtime)
    SeekBar sbRuntime;
    @BindView(R.id.tv_runtime)
    TextView tvRuntime;
    @BindView(R.id.sb_temperature)
    SeekBar sbTemperature;
    @BindView(R.id.tv_temperature)
    TextView tvTemperature;
    @BindView(R.id.lnl_open_air)
    LinearLayout lnlOpen;
    @BindView(R.id.lnl_close_air)
    LinearLayout lnlClose;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_air, null);
        unbinder = ButterKnife.bind(this, view);

        initView();
        return view;
    }

    private void initView() {
        sbRuntime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvRuntime.setText("运行时间：" + i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sbTemperature.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvTemperature.setText("温度：" + i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sbRuntime.setProgress(15);
        sbTemperature.setProgress(20);
    }

    @OnClick({R.id.lnl_open_air, R.id.lnl_close_air})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lnl_open_air:
                break;
            case R.id.lnl_close_air:
                break;
            default:
        }
    }

}
