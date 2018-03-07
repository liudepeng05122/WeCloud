package com.navinfo.wecloud.view.remotecontrol;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.navinfo.wecloud.R;
import com.navinfo.wecloud.presenter.remotecontrol.RemoteControlPresenterImpl;
import com.navinfo.wecloud.view.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by liudepeng on 2018-3-5.
 * mail:liudepeng@navinfo.com
 */

public class VehicleStatusFragment extends BaseFragment {
    private RemoteControlPresenterImpl remoteControlPresenter;

    public VehicleStatusFragment() {
    }

    @SuppressLint("ValidFragment")
    public VehicleStatusFragment(RemoteControlPresenterImpl remoteControlPresenter) {
        this.remoteControlPresenter = remoteControlPresenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vehicle_status, null);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }
}
