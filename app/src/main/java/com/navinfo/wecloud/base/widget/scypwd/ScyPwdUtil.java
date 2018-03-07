package com.navinfo.wecloud.base.widget.scypwd;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.navinfo.wecloud.R;
import com.navinfo.wecloud.base.AppCache;
import com.navinfo.wecloud.base.AppConfig;
import com.navinfo.wecloud.base.util.SecurityUtils;
import com.navinfo.wecloud.base.util.ShareUtil;
import com.navinfo.wecloud.view.dialog.HintSecurityPwdDialog;
import com.navinfo.wecloud.view.main.SettingActivity;


/**
 * <p>Title      : [ScyPwdUtil]_[Object]</p>
 * <p>Description: [安防密码管理工具]</p>
 * <p>Copyright  : Copyright (c) 2017</p>
 * <p>Company    : 北京四维图新科技股份有限公司</p>
 * <p>Department : Telematics业务部</p>
 *
 * @author : zhangzhichen
 * @version : 1.0
 */
public class ScyPwdUtil {
    // 第一次远程控制提示开通指纹
    public final static String IS_FIRST_OPEN_FINGER = "isFirst_open_finger";

    private FingerprintManager manager;
    private KeyguardManager mKeyManager;
    private HintSecurityPwdDialog hintSecurityPwdDialog;

    private FragmentActivity mContext;
    private ScyPwdPopupWindow scyPwdPopupWindow;
    private HintOpenFingerPopupWindow hintOpenFingerPopupWindow;
    private FingerPrintDialog fingerPrintDialog;
    private ScyPwdUtilListener scyPwdUtilListener;
    private ScyPwdUtilOpenFingerListener scyPwdUtilOpenFingerListener;
    private int viewId;

    public interface ScyPwdUtilListener {
        void onUtilScyPwdFinish(String password, int flag);// 最后传入的是经过MD5的pwd，和标识是否为指纹验证(1为指纹，0为安防)
    }

    public interface ScyPwdUtilOpenFingerListener {
        void onOpenFingerHint();// 跳转至设置界面开通指纹
    }

    private static ScyPwdUtil scyPwdUtil;

    private ScyPwdUtil(FragmentActivity c) {
        mContext = c;
    }

    public static ScyPwdUtil getInstance(FragmentActivity activity) {
        if (scyPwdUtil == null) {
            scyPwdUtil = new ScyPwdUtil(activity);
        }

        scyPwdUtil.mContext = activity;
        return scyPwdUtil;
    }

    /**
     * 初始化指纹和密码弹出框
     *
     * @param id：为popupWindow基于哪一个id显示（格式：R.id.xxx）
     */
    public void initScyPwd(int id) {

        if (!AppCache.getInstance().isHasScyPwd(mContext)) {
            if (hintSecurityPwdDialog == null) {
                hintSecurityPwdDialog = new HintSecurityPwdDialog(mContext, R.style.ActionSheetDialogStyle);
            }

            hintSecurityPwdDialog.setCanceledOnTouchOutside(false);
            hintSecurityPwdDialog.setCancelable(false);
            hintSecurityPwdDialog.setOnHintSecurityPwdistener(new HintSecurityPwdDialog.OnHintSecurityPwdistener() {
                @Override
                public void onJumpSetSecurityPwd() {
                    Intent intent = new Intent(mContext, SettingActivity.class);
                    mContext.startActivity(intent);
                }
            });
            hintSecurityPwdDialog.show();
            return;
        }

        viewId = id;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            manager = mContext.getSystemService(FingerprintManager.class);
            mKeyManager = (KeyguardManager) mContext.getSystemService(Context.KEYGUARD_SERVICE);

            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                return;
            }

            //判断硬件是否支持指纹识别
            if (!manager.isHardwareDetected()) {
                //没有指纹识别模块
                initPasswordPopWin(id);
                return;
            }

            boolean hasEnroll = false;//指纹是否有录入，false没有录入;添加该异常用于三星指纹权限异常时使用
            try {
                hasEnroll = manager.hasEnrolledFingerprints();
            } catch (Exception e) {
                hasEnroll = false;
            }

            if (!hasEnroll) {// 指纹没有录入，弹出安防密码
                initPasswordPopWin(id);
            } else {
                boolean fpControl = AppCache.getInstance().isFpControl();
                if (!fpControl) {

                    String keyString = ShareUtil.getString(IS_FIRST_OPEN_FINGER, "TRUE", mContext);
                    if ("TRUE".equals(keyString)) {
                        ShareUtil.saveString(IS_FIRST_OPEN_FINGER, "FALSE", mContext);
                        initHintOpenFingerPopupWin(id);
                    } else
                        initPasswordPopWin(id);
                } else {
                    if (mKeyManager.isDeviceLocked()) {// 如果当前设备被锁定
//                        initPasswordPopWin(id);
//                        mKeyManager.isKeyguardLocked();

                        //        if (fingerPrintDialog == null)
                        fingerPrintDialog = new FingerPrintDialog(mContext, R.style.ActionSheetDialogStyle);
                        fingerPrintDialog.setFingerPrintInputCallback(callback);
                        fingerPrintDialog.setCanceledOnTouchOutside(false);
                        if (!mContext.isFinishing())
                            fingerPrintDialog.show();

                    } else {
                        //    if (fingerPrintDialog == null)
                        fingerPrintDialog = new FingerPrintDialog(mContext, R.style.ActionSheetDialogStyle);
                        fingerPrintDialog.setFingerPrintInputCallback(callback);
                        fingerPrintDialog.setCanceledOnTouchOutside(false);
                        if (!mContext.isFinishing())
                            fingerPrintDialog.show();
                    }
                }
            }

        } else {// 手机不支持指纹，直接弹出密码框
            initPasswordPopWin(id);
        }
        clearPasswordView();// 因为单例所以清空
    }

    private void initHintOpenFingerPopupWin(final int id) {

        hintOpenFingerPopupWindow = HintOpenFingerPopupWindow.getInstance(mContext);
        hintOpenFingerPopupWindow.setOnHintOpenFingerListener(new HintOpenFingerPopupWindow.OnHintOpenFingerListener() {
            @Override
            public void onSkip() {
                hintOpenFingerPopupWindow.dismiss();
                initPasswordPopWin(id);
            }

            @Override
            public void onOpenFinger() {
                hintOpenFingerPopupWindow.dismiss();
                if (scyPwdUtilOpenFingerListener != null)
                    scyPwdUtilOpenFingerListener.onOpenFingerHint();
            }
        });
        hintOpenFingerPopupWindow.showAtLocation(mContext.findViewById(id), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    private void initPasswordPopWin(int id) {

        scyPwdPopupWindow = ScyPwdPopupWindow.getInstance(mContext);
        scyPwdPopupWindow.setIsFromControl(true);
        scyPwdPopupWindow.setOnPasswordIsTrueListener(new ScyPwdPopupWindow.OnPasswordTrueListener() {

            @Override
            public void onCancelClick() {
                // popupWindow隐藏时恢复屏幕正常透明度
                setBackgroundAlpha(1.0f);
            }

            @Override
            public void onPasswordFinish(String safetyPassword) {
                safetyPassword = SecurityUtils.md5(safetyPassword);
                scyPwdUtilListener.onUtilScyPwdFinish(safetyPassword, 0);
            }
        });

        scyPwdPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // popupWindow隐藏时恢复屏幕正常透明度
                setBackgroundAlpha(1.0f);
            }
        });

        scyPwdPopupWindow.showAtLocation(mContext.findViewById(id), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        setBackgroundAlpha(0.5f);//设置屏幕透明度
    }

    private void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = (mContext).getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        (mContext).getWindow().setAttributes(lp);
    }

    /**
     * 设置工具类的回调
     *
     * @param listener：传入Listener
     */
    public void setSafetyPasswordUtilListener(ScyPwdUtilListener listener) {
        this.scyPwdUtilListener = listener;
    }

    public void setOpenFingerListener(ScyPwdUtilOpenFingerListener scyPwdUtilOpenFingerListener) {
        this.scyPwdUtilOpenFingerListener = scyPwdUtilOpenFingerListener;
    }

    private FingerPrintDialog.FingerPrintInputCallback callback = new FingerPrintDialog.FingerPrintInputCallback() {

        @Override
        public void onCancelClick() {
        }

        @Override
        public void onFingerError(boolean isCancel) {
            fingerPrintDialog.dismiss();
            if (isCancel) {
            } else {
                initPasswordPopWin(viewId);
            }
        }

        @Override
        public void onFingerSuccess() {

            String selectedVin = AppConfig.getInstance().getVin();
            String tokenId = AppConfig.getInstance().getTokenId();
            String pwd = SecurityUtils.md5(selectedVin + tokenId);// 使用指纹验证时传入

            if (scyPwdUtilListener != null)
                scyPwdUtilListener.onUtilScyPwdFinish(pwd, 1);
            fingerPrintDialog.dismiss();
        }

        @Override
        public void onFingerFailed() {

        }
    };

    public void clearPasswordView() {
        if (scyPwdPopupWindow != null && scyPwdPopupWindow.isShowing()) {
            scyPwdPopupWindow.clearPassword();
        }
    }
}
