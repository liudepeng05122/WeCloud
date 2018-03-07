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

public class EngineFragment extends BaseFragment {
    private RemoteControlPresenterImpl remoteControlPresenter;

    public EngineFragment() {
    }

    @SuppressLint("ValidFragment")
    public EngineFragment(RemoteControlPresenterImpl remoteControlPresenter) {
        this.remoteControlPresenter = remoteControlPresenter;
    }

    @BindView(R.id.sb_engine_runtime)
    SeekBar sbEngineRuntime;
    @BindView(R.id.tv_engine_runtime)
    TextView tvEngineRuntime;
    @BindView(R.id.lnl_open_engine)
    LinearLayout lnlOpen;
    @BindView(R.id.lnl_close_engine)
    LinearLayout lnlClose;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_engine, null);
        unbinder = ButterKnife.bind(this, view);

        initView();

        return view;
    }

    private void initView() {
        sbEngineRuntime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvEngineRuntime.setText("运行时间：" + i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sbEngineRuntime.setProgress(15);
    }

    @OnClick({R.id.lnl_open_engine, R.id.lnl_close_engine})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lnl_open_engine:
                break;
            case R.id.lnl_close_engine:
                break;
            default:

        }
    }
}
