package com.navinfo.wecloud.view.message.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.navinfo.wecloud.R;
import com.navinfo.wecloud.baidupush.PushReceiver;
import com.navinfo.wecloud.base.util.TimeUtils;
import com.navinfo.wecloud.db.Message;
import com.navinfo.wecloud.view.message.MessageActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudepeng on 2018-2-28.
 * mail:liudepeng@navinfo.com
 */

public class MessageListAdapter extends BaseAdapter {
    private MessageActivity messageActivity;
    private List<Message> messageList = new ArrayList<>();
    private ViewHolder holder;

    public MessageListAdapter(MessageActivity messageActivity) {
        this.messageActivity = messageActivity;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (messageList != null) {
            return messageList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        if (messageList != null) {
            return messageList.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = LayoutInflater.from(messageActivity).inflate(R.layout.message_list_item, null);
            holder = new ViewHolder();
            holder.tvMessageListItemType = view.findViewById(R.id.message_list_item_type);
            holder.tvMessageListItemTitle = view.findViewById(R.id.message_list_item_title);
            holder.tvMessageListItemDescription = view.findViewById(R.id.message_list_item_description);
            holder.tvMessageListItemCustomContentString = view.findViewById(R.id.message_list_item_customContentString);
            holder.tvMessageListItemTime = view.findViewById(R.id.message_list_item_time);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Message message = messageList.get(i);
        if (message.getType() == PushReceiver.MESSAGE_TYPE_PUBLIC) {
            holder.tvMessageListItemType.setText("广播消息");
        } else {
            holder.tvMessageListItemType.setText("透传消息");
        }
        holder.tvMessageListItemTitle.setText(message.getTitle());
        holder.tvMessageListItemDescription.setText(message.getDescription());
        holder.tvMessageListItemCustomContentString.setText(message.getCustomContentString());
        holder.tvMessageListItemTime.setText(TimeUtils.getTimeFullFormat(message.getTime()));


        return view;
    }

    class ViewHolder {
        //        @BindView(R.id.message_list_item_type)
        public TextView tvMessageListItemType;
        //        @BindView(R.id.message_list_item_title)
        public TextView tvMessageListItemTitle;
        //        @BindView(R.id.message_list_item_description)
        public TextView tvMessageListItemDescription;
        //        @BindView(R.id.message_list_item_customContentString)
        public TextView tvMessageListItemCustomContentString;
        //        @BindView(R.id.message_list_item_time)
        public TextView tvMessageListItemTime;

//        public ViewHolder(View view) {
//            ButterKnife.bind(this, view);
//        }
    }
}
