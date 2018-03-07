package com.navinfo.wecloud.base.widget.scypwd;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.navinfo.wecloud.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title      : [CustomScypwdView]_[RelativeLayout]</p>
 * <p>Description: [自定义安防密码键盘控件]</p>
 * <p>Copyright  : Copyright (c) 2017</p>
 * <p>Company    : 北京四维图新科技股份有限公司</p>
 * <p>Department : Telematics业务部</p>
 *
 * @author : zhangzhichen
 * @version : 1.0
 */
public class CustomScyPwdView extends RelativeLayout implements View.OnClickListener {

    private Context mContext;
    private String password;// 输入的密码
    private String[] listPwd; //保存密码的数组
    private GridView gridView;    //用GrideView布局键盘，其实并不是真正的键盘，只是模拟键盘的功能
    private ArrayList<Map<String, String>> valueList;    //适配adapter集合
    private TextView tvForget;
    private int currentIndex = -1;    //用于记录当前输入密码格位置
    private RelativeLayout rllCancel; //取消按钮点击包裹
    private OnScyPwdFinishListener onScyPwdFinishListener;
    private ImageView[] ivPwd;

    public interface OnScyPwdFinishListener {
        void inputFinish();
    }

    public CustomScyPwdView(Context context) {
        this(context, null);
    }

    public CustomScyPwdView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        View view = View.inflate(context, R.layout.custom_scypwd_keyboard, null);

        valueList = new ArrayList<Map<String, String>>();
    //    tvList = new TextView[6];

        listPwd = new String[6];
        ivPwd = new ImageView[6];

        tvForget = (TextView) view.findViewById(R.id.tv_forgetPwd);
        tvForget.setOnClickListener(this);

        rllCancel = (RelativeLayout) view.findViewById(R.id.img_cancel_rlt);
        rllCancel.setOnClickListener(this);
//        tvList[0] = (TextView) view.findViewById(R.id.tv_pass1);
//        tvList[1] = (TextView) view.findViewById(R.id.tv_pass2);
//        tvList[2] = (TextView) view.findViewById(R.id.tv_pass3);
//        tvList[3] = (TextView) view.findViewById(R.id.tv_pass4);
//        tvList[4] = (TextView) view.findViewById(R.id.tv_pass5);
//        tvList[5] = (TextView) view.findViewById(R.id.tv_pass6);

        ivPwd[0] = (ImageView) view.findViewById(R.id.iv_pass1);
        ivPwd[1] = (ImageView) view.findViewById(R.id.iv_pass2);
        ivPwd[2] = (ImageView) view.findViewById(R.id.iv_pass3);
        ivPwd[3] = (ImageView) view.findViewById(R.id.iv_pass4);
        ivPwd[4] = (ImageView) view.findViewById(R.id.iv_pass5);
        ivPwd[5] = (ImageView) view.findViewById(R.id.iv_pass6);

        gridView = (GridView) view.findViewById(R.id.gv_keybord);

        setView();
        addView(view);// 必须要，不然不显示控件
    }

    @Override
    public void onClick(View view) {

    }

    public void setOnScyPwdFinishListener(OnScyPwdFinishListener onScyPwdFinishListener) {
        this.onScyPwdFinishListener = onScyPwdFinishListener;
    }

    private void setView() {
        /* 初始化按钮上应该显示的数字 */
        for (int i = 1; i < 13; i++) {
            Map<String, String> map = new HashMap<String, String>();
            if (i < 10) {
                map.put("name", String.valueOf(i));
            } else if (i == 10) {
                map.put("name", "");
            } else if (i == 11) {
                map.put("name", String.valueOf(0));
            } else if (i == 12) {
                map.put("name", "删除");
            }
            valueList.add(map);
        }

        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position < 11 && position != 9) {    //点击0~9按钮
                    if (currentIndex >= -1 && currentIndex < 5) {      //判断输入位置————要小心数组越界

                        ++currentIndex;
                        listPwd[currentIndex] = valueList.get(position).get("name");
                        ivPwd[currentIndex].setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.key_4_1_black));
                    //    tvList[++currentIndex].setText(valueList.get(position).get("name"));

                        if(currentIndex == 5) {
                            password = "";     //每次触发都要先将strPassword置空，再重新获取，避免由于输入删除再输入造成混乱
                            for (int i = 0; i < 6; i++) {
                                password += listPwd[i];
                            }

                            if (onScyPwdFinishListener != null)
                                onScyPwdFinishListener.inputFinish();    //接口中要实现的方法，完成密码输入完成后的响应逻辑
                        }
                    }
                } else {
                    if (position == 11) {      //点击退格键
                        if (currentIndex - 1 >= -1) {      //判断是否删除完毕
                            listPwd[currentIndex] = "";
                            ivPwd[currentIndex].setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.key_4_1));
                            currentIndex--;
//                            tvList[currentIndex--].setText("");
                        }
                    }
                }
            }
        });
    }

    //设置监听方法，在第6位输入完成后触发
    public void setOnFinishInput() {
//        tvList[5].addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                if (s.toString().length() == 1) {
//                    password = "";     //每次触发都要先将strPassword置空，再重新获取，避免由于输入删除再输入造成混乱
//                    for (int i = 0; i < 6; i++) {
//                        password += tvList[i].getText().toString().trim();
//                    }
//
//                    if (onScyPwdFinishListener != null)
//                        onScyPwdFinishListener.inputFinish();    //接口中要实现的方法，完成密码输入完成后的响应逻辑
//                }
//            }
//        });
    }

    /* 获取输入的密码 */
    public String getStrPassword() {
        return password;
    }

    public RelativeLayout getRllCancel() {
        return rllCancel;
    }

    /* 暴露忘记密码的按钮，可以灵活改变响应 */
    public TextView getForgetTextView() {
        return tvForget;
    }

    /* 暴露清空密码框，使得输入完后变为初始状态 */
    public void clearInputView() {
        int i = 0;
        while (i <= 5) {
            listPwd[i] = "";
            ivPwd[i].setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.key_4_1));
            i++;
        }
        currentIndex = -1;
    }

    //GrideView的适配器
    BaseAdapter adapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return valueList.size();
        }

        @Override
        public Object getItem(int position) {
            return valueList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.custom_scypwd_keyboard_item, null);
                viewHolder = new ViewHolder();
                viewHolder.btnKey = (TextView) convertView.findViewById(R.id.btn_keys);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.btnKey.setText(valueList.get(position).get("name"));
            if (position == 9) {
                viewHolder.btnKey.setBackgroundResource(R.drawable.scypwd_shape_selector_key_del);
                viewHolder.btnKey.setEnabled(false);
            }
            if (position == 11) {
                viewHolder.btnKey.setBackgroundResource(R.drawable.scypwd_shape_selector_key_del);
            }

            return convertView;
        }
    };

    /**
     * 存放控件，在指定位置进行
     */
    public final class ViewHolder {
        public TextView btnKey;
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        clearInputView();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }
}
