package com.navinfo.wecloud.base.widget;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.navinfo.wecloud.R;
import com.navinfo.wecloud.view.remotecontrol.RemoteControlActivity;

/**
 * <p>Title      : [CustomHavalTabView]_[RelativeLayout]</p>
 * <p>Description: [自定义Haval控制栏]</p>
 * <p>Copyright  : Copyright (c) 2017</p>
 * <p>Company    : 北京四维图新科技股份有限公司</p>
 * <p>Department : Telematics业务部</p>
 *
 * @author : zhangzhichen
 * @version : 1.0
 */
public class CustomHavalTabView extends RelativeLayout implements View.OnClickListener {

    private LinearLayout lnlControl;
    private RelativeLayout lnlBar;
    private Context mContext;
    private View mView;
    private LinearLayout lnlKey, lnlAir, lnlEngine, lnlVehicleStatus;
    private ImageView ivKey, ivAir, ivEngine, ivVehicleStatus;
    private OnFloatClickListener onFloatClickListener;

    public interface OnFloatClickListener {
        // 切换
        void onTabClick(int index);
    }

    public void setOnFloatClickListener(OnFloatClickListener onFloatClickListener) {
        this.onFloatClickListener = onFloatClickListener;
    }

    public CustomHavalTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mView = LayoutInflater.from(context).inflate(R.layout.custom_haval_tab_vlayout, this, true);
        initView();

    }

    @Override
    public void onClick(View view) {
        if (view == null)
            return;

        switch (view.getId()) {
            case R.id.lnl_key:
                if (onFloatClickListener != null)
                    onFloatClickListener.onTabClick(RemoteControlActivity.TAB_CONTROL);
                break;
            case R.id.lnl_air:
                if (onFloatClickListener != null) {
                    onFloatClickListener.onTabClick(RemoteControlActivity.TAB_AIR);
                }

                break;
            case R.id.lnl_engine:
                if (onFloatClickListener != null)
                    onFloatClickListener.onTabClick(RemoteControlActivity.TAB_ENGINE);
                break;
            case R.id.lnl_status:
                if (onFloatClickListener != null)
                    onFloatClickListener.onTabClick(RemoteControlActivity.TAB_VEHICLE_STATUS);
                break;

        }
    }

    public void setSelectedIndex(int index) {
        ivKey.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.key_2_btn_key_nor));
        ivAir.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.key_2_btn_air_nor));
        ivEngine.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.key_2_btn_engine_nor));
        ivVehicleStatus.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.key_2_btn_car_nor));

        switch (index) {
            case RemoteControlActivity.TAB_CONTROL:
                ivKey.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.key_2_btn_key_sel));
                break;
            case RemoteControlActivity.TAB_AIR:
                ivAir.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.key_2_btn_air_sel));
                break;
            case RemoteControlActivity.TAB_ENGINE:
                ivEngine.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.key_2_btn_engine_sel));
                break;
            case RemoteControlActivity.TAB_VEHICLE_STATUS:
                ivVehicleStatus.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.key_2_btn_car_sel));
                break;
            default:
                break;
        }
    }


    private void initView() {
        lnlBar = (RelativeLayout) mView.findViewById(R.id.lnl_bar);
        lnlControl = (LinearLayout) mView.findViewById(R.id.control_lnl);
        lnlKey = (LinearLayout) mView.findViewById(R.id.lnl_key);
        lnlAir = (LinearLayout) mView.findViewById(R.id.lnl_air);
        lnlEngine = (LinearLayout) mView.findViewById(R.id.lnl_engine);
        lnlVehicleStatus = (LinearLayout) mView.findViewById(R.id.lnl_status);
        ivKey = (ImageView) mView.findViewById(R.id.iv_key);
        ivAir = (ImageView) mView.findViewById(R.id.iv_air);
        ivEngine = (ImageView) mView.findViewById(R.id.iv_engine);
        ivVehicleStatus = (ImageView) mView.findViewById(R.id.iv_status);

        setSelectedIndex(RemoteControlActivity.TAB_CONTROL);

        lnlKey.setOnClickListener(this);
        lnlAir.setOnClickListener(this);
        lnlEngine.setOnClickListener(this);
        lnlVehicleStatus.setOnClickListener(this);

    }


}
