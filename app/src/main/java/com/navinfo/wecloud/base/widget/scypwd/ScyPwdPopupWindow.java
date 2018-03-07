package com.navinfo.wecloud.base.widget.scypwd;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.navinfo.wecloud.R;
import com.navinfo.wecloud.view.main.SettingActivity;


/**
 * <p>Title      : [ScyPwdPopupWindow]_[PopupWindow]</p>
 * <p>Description: [安防密码弹出框]</p>
 * <p>Copyright  : Copyright (c) 2017</p>
 * <p>Company    : 北京四维图新科技股份有限公司</p>
 * <p>Department : Telematics业务部</p>
 *
 * @author : zhangzhichen
 * @version : 1.0
 */
public class ScyPwdPopupWindow extends PopupWindow {

    private View mView;
    private CustomScyPwdView customScyPwdView;
    private OnPasswordTrueListener onPasswordTrueListener;
    private Handler handler;
    private boolean isFromControl = false;

    private static ScyPwdPopupWindow scyPwdPopupWindow;

    public static ScyPwdPopupWindow getInstance(Context context) {
        if (scyPwdPopupWindow == null)
            scyPwdPopupWindow = new ScyPwdPopupWindow(context);
        return scyPwdPopupWindow;
    }

    public ScyPwdPopupWindow(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        init(context);
        setPopupWindow();
    }

    public void clearPasswordViewPopup() {
        if (scyPwdPopupWindow != null) {
            scyPwdPopupWindow = null;
        }
    }

    /**
     * 初始化
     *
     * @param context
     */
    private void init(final Context context) {

        LayoutInflater inflater = LayoutInflater.from(context);
        //绑定布局
        mView = inflater.inflate(R.layout.layout_scypwd_popup_window, null, true);
        customScyPwdView = (CustomScyPwdView) mView.findViewById(R.id.scypwd_pop_view);

        //添加密码输入完成的响应
        customScyPwdView.setOnScyPwdFinishListener(new CustomScyPwdView.OnScyPwdFinishListener() {
            @Override
            public void inputFinish() {
                //输入完成后
                onPasswordTrueListener.onPasswordFinish(customScyPwdView.getStrPassword());
                handler.postDelayed(runnable, 100);
            }
        });
        customScyPwdView.setOnFinishInput();

        customScyPwdView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    if (onPasswordTrueListener != null)
                        onPasswordTrueListener.onCancelClick();
                }
                return false;
            }
        });

        /* 取消按钮的点击事件重写 */
        customScyPwdView.getRllCancel().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (onPasswordTrueListener != null)
                    onPasswordTrueListener.onCancelClick();
            }
        });
        customScyPwdView.getForgetTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Activity activity = (Activity) context;

                if (isFromControl) {
                    Intent intent = new Intent(activity, SettingActivity.class);
                    activity.startActivity(intent);
                } else {
                    Intent intent = new Intent(activity, SettingActivity.class);
                    activity.startActivity(intent);
                }
            }
        });
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            dismiss();
            customScyPwdView.clearInputView();
        }
    };

    /**
     * 设置窗口的相关属性
     */
    @SuppressLint("InlinedApi")
    private void setPopupWindow() {
        this.setContentView(mView);// 设置View
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);// 设置弹出窗口的宽
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);// 设置弹出窗口的高

        customScyPwdView.setFocusable(true);
        customScyPwdView.setFocusableInTouchMode(true);
        mView.setFocusable(true);
        mView.setFocusableInTouchMode(true);

        this.setFocusable(true);// 设置弹出窗口可

        this.setBackgroundDrawable(new ColorDrawable(0x00ffffff));// 设置背景透明
        mView.setOnTouchListener(new View.OnTouchListener() {// 如果触摸位置在窗口外面则销毁

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                int height = mView.findViewById(R.id.scypwd_pop_view).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
//                        dismiss();
                    }
                }
                return true;
            }
        });

        mView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    if (onPasswordTrueListener != null)
                        onPasswordTrueListener.onCancelClick();
                }

                return false;
            }
        });
        handler = new Handler();
    }

    public interface OnPasswordTrueListener {
        void onPasswordFinish(String safetyPassword);// 密码输入完成后回调

        void onCancelClick();
    }

    public void setOnPasswordIsTrueListener(OnPasswordTrueListener listener) {
        onPasswordTrueListener = listener;
    }

    public void clearPassword() {
        customScyPwdView.clearInputView();
    }

    /**
     * 判断是否从主界面显示
     * 如果在主界面显示，忘记安防密码跳转至设置界面，
     * 如果从设置界面显示，忘记安防密码跳转至设置安防密码界面
     *
     * @param isFromControl
     */
    public void setIsFromControl(boolean isFromControl) {
        this.isFromControl = isFromControl;
    }
}
