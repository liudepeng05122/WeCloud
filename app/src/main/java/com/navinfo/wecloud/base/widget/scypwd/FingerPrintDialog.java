package com.navinfo.wecloud.base.widget.scypwd;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.navinfo.wecloud.R;


/**
 * <p>Title      : [FingerPrintDialog]_[Dialog]</p>
 * <p>Description: [安防密码输入对话框]</p>
 * <p>Copyright  : Copyright (c) 2017</p>
 * <p>Company    : 北京四维图新科技股份有限公司</p>
 * <p>Department : Telematics业务部</p>
 *
 * @author : wangyam
 * @version : 1.0
 */
@TargetApi(Build.VERSION_CODES.M)
public class FingerPrintDialog extends Dialog implements View.OnClickListener {
    private TextView cancelTv;
    private TextView verifyFingerTv;
    private FingerprintManager manager;
    private KeyguardManager mKeyManager;
    private Context mContext;
    private FingerPrintInputCallback fingerPrintInputCallback;
    private boolean isClickCancel;

    public FingerPrintDialog(Context context) {
        this(context, R.style.ActionSheetDialogStyle);
    }

    public FingerPrintDialog(Context context, int theme) {
        super(context, theme);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_finger_print);

//        startListening(null);
        cancelTv = (TextView) findViewById(R.id.dialog_finger_print_cancel);
        verifyFingerTv = (TextView) findViewById(R.id.please_verify_finger);

        cancelTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(fingerPrintInputCallback != null)
            fingerPrintInputCallback.onCancelClick();

        fingerPrintInputCallback.onFingerError(true);
        isClickCancel = true;
        mCancellationSignal.cancel();// 取消时也会调用onAuthenticationError方法
        dismiss();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    @Override
    public void setCanceledOnTouchOutside(boolean cancel) {
        super.setCanceledOnTouchOutside(cancel);
    }

    /**
     * 回调接口，处理相应逻辑
     */
    public interface FingerPrintInputCallback {
        void onFingerError(boolean isCancel);// 为指纹验证失败超过5次时调用,后续调用密码弹出框

        void onFingerSuccess();

        void onFingerFailed();//有需要时实现，为指纹验证失败调用

        void onCancelClick();
    }

    public void setFingerPrintInputCallback(FingerPrintInputCallback callback) {
        fingerPrintInputCallback = callback;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (isShowing()) {
            mCancellationSignal = new CancellationSignal();

            mSelfCancelled = new FingerprintManager.AuthenticationCallback() {
                @Override
                public void onAuthenticationError(int errorCode, CharSequence errString) {
                    //但多次指纹密码验证错误后，进入此方法；并且，不能短时间内调用指纹验证
//            Toast.makeText(mContext, errString, Toast.LENGTH_SHORT).show();
//            showAuthenticationScreen();
                    if (isClickCancel) {

                    } else {
                        verifyFingerTv.setText("指纹错误已达上限，即将切换验证方式");
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                if (fingerPrintInputCallback != null) {
                                    fingerPrintInputCallback.onFingerError(false);
                                }
                                verifyFingerTv.setText("请验证已有的指纹");
                                dismiss();
                            }
                        }, 3000);
                    }

                }

                @Override
                public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                    verifyFingerTv.setText(helpString);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            verifyFingerTv.setText("请验证已有的指纹");
                        }
                    }, 500);
//            Toast.makeText(mContext, helpString, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {

                    if (fingerPrintInputCallback != null) {
                        fingerPrintInputCallback.onFingerSuccess();
                    }
                    dismiss();
                }

                @Override
                public void onAuthenticationFailed() {
                    if (fingerPrintInputCallback != null) {
                        fingerPrintInputCallback.onFingerFailed();
                    }
                    verifyFingerTv.setText("指纹校验失败");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            verifyFingerTv.setText("请验证已有的指纹");
                        }
                    }, 500);
//            Toast.makeText(mContext, "指纹校验失败", Toast.LENGTH_SHORT).show();
                }
            };


            manager = mContext.getSystemService(FingerprintManager.class);
            mKeyManager = (KeyguardManager) mContext.getSystemService(Context.KEYGUARD_SERVICE);
            startListening(null);
        }
    }

    /**
     * 开启指纹认证
     *
     * @param cryptoObject
     */
    public void startListening(FingerprintManager.CryptoObject cryptoObject) {
        //android studio 上，没有这个会报错
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(mContext, "没有指纹识别权限", Toast.LENGTH_SHORT).show();
            return;
        }
        manager.authenticate(cryptoObject, mCancellationSignal, 0, mSelfCancelled, null);

    }

    private CancellationSignal mCancellationSignal;

    //指纹回调方法
    private FingerprintManager.AuthenticationCallback mSelfCancelled;
}
