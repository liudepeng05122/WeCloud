package com.navinfo.wecloud.base.widget.scypwd;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.navinfo.wecloud.R;


/**
 * <p>Title      : [HintOpenFingerPopupWindow]_[PopupWindow]</p>
 * <p>Description: [提示开通指纹通知框]</p>
 * <p>Copyright  : Copyright (c) 2017</p>
 * <p>Company    : 北京四维图新科技股份有限公司</p>
 * <p>Department : Telematics业务部</p>
 *
 * @author : zhangzhichen
 * @version : 1.0
 */
public class HintOpenFingerPopupWindow extends PopupWindow {

    private View mView;
    private Button btnOpen;
    private TextView tvSkip;
    private OnHintOpenFingerListener onHintOpenFingerListener;

    public interface OnHintOpenFingerListener {

        void onSkip();//跳过
        void onOpenFinger(); //开通指纹
    }

    private static HintOpenFingerPopupWindow openFingerPopupWindow;

    public static HintOpenFingerPopupWindow getInstance(Context context) {
        if (openFingerPopupWindow == null)
            openFingerPopupWindow = new HintOpenFingerPopupWindow(context);
        return openFingerPopupWindow;
    }

    private HintOpenFingerPopupWindow(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        init(context);
        setPopupWindow();

        setFocusable(false);
        setOutsideTouchable(false);
    }

    public void clearHintOpenFingerPopup() {
        if (openFingerPopupWindow != null) {
            openFingerPopupWindow = null;
        }
    }

    public void setOnHintOpenFingerListener(OnHintOpenFingerListener onHintOpenFingerListener) {
        this.onHintOpenFingerListener = onHintOpenFingerListener;
    }

    /**
     * 初始化
     *
     * @param context
     */
    private void init(final Context context) {

        LayoutInflater inflater = LayoutInflater.from(context);
        //绑定布局
        mView = inflater.inflate(R.layout.layout_open_finger_popup_window, null);
        btnOpen = (Button) mView.findViewById(R.id.btn_open);
        tvSkip = (TextView) mView.findViewById(R.id.tv_skip);

        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onHintOpenFingerListener != null)
                    onHintOpenFingerListener.onOpenFinger();
            }
        });

        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onHintOpenFingerListener != null)
                    onHintOpenFingerListener.onSkip();
            }
        });
    }

    /**
     * 设置窗口的相关属性
     */
    @SuppressLint("InlinedApi")
    private void setPopupWindow() {
        this.setContentView(mView);// 设置View
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);// 设置弹出窗口的宽
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);// 设置弹出窗口的高
        this.setFocusable(true);// 设置弹出窗口可
        this.setBackgroundDrawable(new ColorDrawable(0x00ffffff));// 设置背景透明
        mView.setOnTouchListener(new View.OnTouchListener() {// 如果触摸位置在窗口外面则销毁

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                return true;
            }
        });
    }
}
