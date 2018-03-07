package com.navinfo.wecloud.baidupush;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.widget.Toast;

import com.baidu.android.pushservice.PushMessageReceiver;
import com.navinfo.wecloud.base.AppContext;
import com.navinfo.wecloud.base.util.SystemUtils;
import com.navinfo.wecloud.base.util.log.LogUtils;
import com.navinfo.wecloud.db.DaoManager;
import com.navinfo.wecloud.db.Message;
import com.navinfo.wecloud.view.message.MessageActivity;

import java.util.List;

/**
 * Created by liudepeng on 2018-2-27.
 * mail:liudepeng@navinfo.com
 */

public class PushReceiver extends PushMessageReceiver {
    public static final int MESSAGE_TYPE_PUBLIC = 0;
    public static final int MESSAGE_TYPE_PRIVATE = 1;

    /**
     * 调用PushManager.startWork后，sdk将对push
     * server发起绑定请求，这个过程是异步的。绑定请求的结果通过onBind返回。 如果您需要用单播推送，需要把这里获取的channel
     * id和user id上传到应用server中，再调用server接口用channel id和user id给单个手机或者用户推送。
     *
     * @param context   BroadcastReceiver的执行Context
     * @param errorCode 绑定接口返回值，0 - 成功
     * @param appid     应用id。errorCode非0时为null
     * @param userId    应用user id。errorCode非0时为null
     * @param channelId 应用channel id。errorCode非0时为null
     * @param requestId 向服务端发起的请求id。在追查问题时有用；
     * @return none
     */
    @Override
    public void onBind(Context context, int errorCode, String appid, String userId, String channelId, String requestId) {
        String responseString = "onBind errorCode=" + errorCode + " appid="
                + appid + " userId=" + userId + " channelId=" + channelId
                + " requestId=" + requestId;
        LogUtils.d(TAG, responseString);


        switch (errorCode) {
            case 0:
                // 绑定成功
                LogUtils.d(TAG, "绑定成功");
                AppContext.isPushInit = true;
                break;
            default:
                LogUtils.d(TAG, "绑定失败 errorCode = " + errorCode);
        }
    }


    @Override
    public void onUnbind(Context context, int message, String customContentString) {

    }

    /**
     * setTags() 的回调函数。
     *
     * @param context     上下文
     * @param errorCode   错误码。0表示某些tag已经设置成功；非0表示所有tag的设置均失败。
     * @param successTags 设置成功的tag
     * @param failTags    设置失败的tag
     * @param requestId   分配给对云推送的请求的id
     */
    @Override
    public void onSetTags(Context context, int errorCode, List<String> successTags, List<String> failTags, String requestId) {
        String responseString = "onSetTags errorCode=" + errorCode
                + " successTags=" + successTags + " failTags=" + failTags
                + " requestId=" + requestId;
        LogUtils.d(TAG, responseString);

        switch (errorCode) {
            case 0:
                // 设置成功
                LogUtils.d(TAG, "设置成功");
                Toast.makeText(context, "设置成功", Toast.LENGTH_SHORT).show();
                break;
            default:
                LogUtils.d(TAG, "设置失败 errorCode = " + errorCode);
                Toast.makeText(context, "设置失败 errorCode = " + errorCode, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * delTags() 的回调函数。
     *
     * @param context     上下文
     * @param errorCode   错误码。0表示某些tag已经删除成功；非0表示所有tag均删除失败。
     * @param successTags 成功删除的tag
     * @param failTags    删除失败的tag
     * @param requestId   分配给对云推送的请求的id
     */
    @Override
    public void onDelTags(Context context, int errorCode, List<String> successTags, List<String> failTags, String requestId) {
        String responseString = "onDelTags errorCode=" + errorCode
                + " successTags=" + successTags + " failTags=" + failTags
                + " requestId=" + requestId;
        LogUtils.d(TAG, responseString);


    }

    /**
     * listTags() 的回调函数。
     *
     * @param context   上下文
     * @param errorCode 错误码。0表示列举tag成功；非0表示失败。
     * @param tags      当前应用设置的所有tag。
     * @param requestId 分配给对云推送的请求的id
     */
    @Override
    public void onListTags(Context context, int errorCode, List<String> tags, String requestId) {
        String responseString = "onListTags errorCode=" + errorCode + " tags="
                + tags;
        LogUtils.d(TAG, responseString);


    }

    /**
     * 接收透传消息的函数。
     *
     * @param context             上下文
     * @param message             推送的消息
     * @param customContentString 自定义内容,为空或者json字符串
     */
    @Override
    public void onMessage(Context context, String message, String customContentString) {
        String messageString = "透传消息 onMessage=\"" + message
                + "\" customContentString=" + customContentString;
        LogUtils.d(TAG, messageString);

        //将消息存储到数据库中
        Toast.makeText(context, messageString, Toast.LENGTH_SHORT).show();
        saveMessage(message, customContentString);
    }

    /**
     * 接收通知点击的函数。
     *
     * @param context             上下文
     * @param title               推送的通知的标题
     * @param description         推送的通知的描述
     * @param customContentString 自定义内容，为空或者json字符串
     */
    @Override
    public void onNotificationClicked(Context context, String title, String description, String customContentString) {
        String notifyString = "通知点击 onNotificationClicked title=\"" + title + "\" description=\""
                + description + "\" customContent=" + customContentString;
        LogUtils.d(TAG, notifyString);

        if (SystemUtils.isAppAlive(context, "com.navinfo.wecloud")) {
            //如果存活的话，就直接启动DetailActivity，但要考虑一种情况，就是app的进程虽然仍然在
            //但Task栈已经空了，比如用户点击Back键退出应用，但进程还没有被系统回收，如果直接启动
            //DetailActivity,再按Back键就不会返回MainActivity了。所以在启动
            //DetailActivity前，要先启动MainActivity。
            Log.i("NotificationReceiver", "the app process is alive");
            Intent intent = new Intent(context, MessageActivity.class);
            //将MainAtivity的launchMode设置成SingleTask, 或者在下面flag中加上Intent.FLAG_CLEAR_TOP,
            //如果Task栈中有MainActivity的实例，就会把它移到栈顶，把在它之上的Activity都清理出栈，
            //如果Task栈不存在MainActivity实例，则在栈顶创建
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            context.startActivity(intent);
        } else {
            //如果app进程已经被杀死，先重新启动app，将DetailActivity的启动参数传入Intent中，参数经过
            //SplashActivity传入MainActivity，此时app的初始化已经完成，在MainActivity中就可以根据传入
            // 参数跳转到DetailActivity中去了
            Log.i("NotificationReceiver", "the app process is dead");
            Intent launchIntent = context.getPackageManager().
                    getLaunchIntentForPackage("com.navinfo.wecloud");
            launchIntent.setFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
            context.startActivity(launchIntent);
        }
    }

    /**
     * 接收通知到达的函数。
     *
     * @param context             上下文
     * @param title               推送的通知的标题
     * @param description         推送的通知的描述
     * @param customContentString 自定义内容，为空或者json字符串
     */
    @Override
    public void onNotificationArrived(Context context, String title, String description, String customContentString) {
        String notifyString = "通知到达 onNotificationArrived  title=\"" + title
                + "\" description=\"" + description + "\" customContent="
                + customContentString;
        LogUtils.d(TAG, notifyString);

        //将消息存储到数据库中
        saveMessage(title, description, customContentString);
    }

    private void saveMessage(String message, String customContentString) {
        DaoManager.getInstance().insertMessage(new Message(MESSAGE_TYPE_PRIVATE, null, message, customContentString, System.currentTimeMillis()));
    }

    private void saveMessage(String title, String description, String customContentString) {
        DaoManager.getInstance().insertMessage(new Message(MESSAGE_TYPE_PUBLIC, title, description, customContentString, System.currentTimeMillis()));
    }
}
