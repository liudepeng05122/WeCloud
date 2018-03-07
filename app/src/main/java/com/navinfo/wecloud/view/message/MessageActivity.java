package com.navinfo.wecloud.view.message;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.baidu.android.pushservice.PushManager;
import com.navinfo.wecloud.R;
import com.navinfo.wecloud.base.util.ClickUtil;
import com.navinfo.wecloud.db.Message;
import com.navinfo.wecloud.inject.component.DaggerMessageComponent;
import com.navinfo.wecloud.inject.model.MessageModel;
import com.navinfo.wecloud.presenter.message.MessagePresenterImpl;
import com.navinfo.wecloud.view.main.MainActivity;
import com.navinfo.wecloud.view.message.adapter.MessageListAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liudepeng on 2018-2-28.
 * mail:liudepeng@navinfo.com
 */

public class MessageActivity extends FragmentActivity implements MessageView {
    @Inject
    MessagePresenterImpl messagePresenter;

    private MessageListAdapter messageListAdapter;

    @BindView(R.id.ib_activity_message_back)
    ImageButton ibMessage;
    @BindView(R.id.lv_activity_message)
    ListView lvMessage;
    @BindView(R.id.et_activity_message)
    EditText etActivityMessage;
    @BindView(R.id.btn_activity_message)
    Button btnMessage;


    public int getContentView() {
        return R.layout.activity_message;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        ButterKnife.bind(this);

        DaggerMessageComponent.builder().messageModel(new MessageModel(this)).build().inject(this);

        messageListAdapter = new MessageListAdapter(this);
        lvMessage.setAdapter(messageListAdapter);
        messagePresenter.loadPushMessage();
    }

    @OnClick({R.id.ib_activity_message_back, R.id.btn_activity_message})
    public void onClick(View view) {
        if (ClickUtil.isFastDoubleClick()) {
            return;
        }
        switch (view.getId()) {
            case R.id.ib_activity_message_back:
                goMainActivity();
                break;
            case R.id.btn_activity_message:
                if (etActivityMessage.getText().toString() != null) {
                    List<String> stringList = new ArrayList<>();
                    stringList.add(etActivityMessage.getText().toString());
                    PushManager.setTags(this, stringList);
                }
                break;
            default:
        }
    }

    @Override
    public void setMessageList(List<Message> messageList) {
        messageListAdapter.setMessageList(messageList);
    }

    @Override
    public void goMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
