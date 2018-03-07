package com.navinfo.wecloud.view.remotecontrol;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import com.navinfo.wecloud.R;
import com.navinfo.wecloud.base.util.log.LogUtils;
import com.navinfo.wecloud.base.widget.CustomHavalTabView;
import com.navinfo.wecloud.inject.component.DaggerRemoteControlComponent;
import com.navinfo.wecloud.inject.model.RemoteControlModel;
import com.navinfo.wecloud.presenter.remotecontrol.RemoteControlPresenterImpl;
import com.navinfo.wecloud.view.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by liudepeng on 2018-3-5.
 * mail:liudepeng@navinfo.com
 */

public class RemoteControlActivity extends BaseActivity implements RemoteControlView {
    public static final int TAB_CONTROL = 0;          //车锁fragment
    public static final int TAB_AIR = 1;              //空调fragment
    public static final int TAB_ENGINE = 2;           //引擎fragment
    public static final int TAB_VEHICLE_STATUS = 3;   //车况fragment

    @Inject
    RemoteControlPresenterImpl remoteControlPresenter;

    @BindView(R.id.vp_activity_remote_control)
    ViewPager viewPager;
    @BindView(R.id.tv_activity_remote_control)
    CustomHavalTabView tabView;

    private VehicleControlFragment vehicleControlFragment;
    private AirFragment airFragment;
    private EngineFragment engineFragment;
    private VehicleStatusFragment vehicleStatusFragment;

    /**
     * 页面集合
     */
    List<Fragment> fragmentList;
    FragStatePagerAdapter fragStatePagerAdapter;
    //当前选中的项
    private int currenttab = -1;

    @Override
    public int getContentView() {
        return R.layout.activity_remote_control;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerRemoteControlComponent.builder().remoteControlModel(new RemoteControlModel(this)).build().inject(this);

        initView();
    }

    private void initView() {
        fragmentList = new ArrayList<>();
        vehicleControlFragment = new VehicleControlFragment(remoteControlPresenter);
        airFragment = new AirFragment(remoteControlPresenter);
        engineFragment = new EngineFragment(remoteControlPresenter);
        vehicleStatusFragment = new VehicleStatusFragment(remoteControlPresenter);

        fragmentList.add(vehicleControlFragment);
        fragmentList.add(airFragment);
        fragmentList.add(engineFragment);
        fragmentList.add(vehicleStatusFragment);

        fragStatePagerAdapter = new FragStatePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fragStatePagerAdapter);
        viewPager.setOffscreenPageLimit(4);

        tabView.setOnFloatClickListener(new CustomHavalTabView.OnFloatClickListener() {

            @Override
            public void onTabClick(int index) {
                LogUtils.d("ldp", "index = " + index);
                viewPager.setCurrentItem(index, false);
                tabView.setSelectedIndex(index);
            }
        });
    }

    class FragStatePagerAdapter extends FragmentPagerAdapter {
        public FragStatePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            if (fragmentList.size() > position) {
                return fragmentList.get(position);
            }

            return null;
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public void startUpdate(ViewGroup container) {
            super.startUpdate(container);//这句话要放在最前面，否则会报错
        }


        /**
         * 每次更新完成ViewPager的内容后，调用该接口，此处复写主要是为了让导航按钮上层的覆盖层能够动态的移动
         */

        @Override
        public void finishUpdate(ViewGroup container) {
            super.finishUpdate(container);//这句话要放在最前面，否则会报错

            int currentItem = viewPager.getCurrentItem();
            if (currentItem == currenttab) {
                return;
            }

            currenttab = viewPager.getCurrentItem();
            tabView.setSelectedIndex(currenttab);
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            super.setPrimaryItem(container, position, object);
        }
    }

}
