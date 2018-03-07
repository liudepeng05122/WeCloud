package com.navinfo.wecloud.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.query.Query;

import java.util.List;

/**
 * Created by liudepeng on 2018-2-27.
 * mail:liudepeng@navinfo.com
 */

public class DaoManager {
    private static final String TAG = DaoManager.class.getSimpleName();
    private static final String DB_NAME = "wecloud";  //声明数据库
    private volatile static DaoManager manager; //多线程名称

    private DaoSession daoSession;
    private SQLiteDatabase db;
    private DaoOpenHelper helper;
    private DaoMaster daoMaster;
    private Cursor cursorUser;
    private Cursor cursorMessage;

    private Context context;

    /**
     * 初始化各数据
     */
    public void init(Context context) {
        this.context = context;
        setupDatabase();
        cursorUser = getDb().query(getDaoSession().getUserDao().getTablename(),
                getDaoSession().getUserDao().getAllColumns(), null, null, null, null, null);

        cursorMessage = getDb().query(getDaoSession().getMessageDao().getTablename(),
                getDaoSession().getMessageDao().getAllColumns(), null, null, null, null, null);
    }

    public static DaoManager getInstance() {
        DaoManager instance = null;
        if (manager == null) {
            synchronized (DaoManager.class) {
                if (instance == null) {
                    instance = new DaoManager();
                    manager = instance;
                }
            }
        }
        return manager;
    }

    /**
     * 初始化数据库
     */
    public void setupDatabase() {
        helper = new DaoOpenHelper(context, "UIE", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        //数据库升级相关
        //daoMaster.getDatabase().needUpgrade();
    }

    /**
     * 获取SQLiteDatabase对象
     *
     * @return DaoSession对象
     */
    public SQLiteDatabase getDb() {
        return db;
    }

    /**
     * 获取DaoSession对象
     *
     * @return DaoSession对象
     */
    public DaoSession getDaoSession() {
        return daoSession;
    }

    /**
     * 获取一个新的DaoSession对象
     *
     * @return DaoSession对象
     */
    public DaoSession getNewDaoSession() {
        return daoMaster.newSession();
    }

    public void insertUser(User user) {
        getDaoSession().getUserDao().insertOrReplace(user);
        cursorUser.requery();
    }

    public void deleteUser() {
        getDaoSession().getUserDao().deleteAll();
        cursorUser.requery();
    }

    public User readUser() {
        Query query = getNewDaoSession().getUserDao().queryBuilder().build();
        List<User> UserList = query.list();
        if (UserList.size() > 0) {
            return UserList.get(0);
        } else {
            return null;
        }
    }

    public void insertMessage(Message message) {
        getDaoSession().getMessageDao().insertOrReplace(message);
        cursorMessage.requery();
    }

    public void deleteMessage(Message message) {
        getDaoSession().getMessageDao().delete(message);
        cursorUser.requery();
    }

    public List<Message> readMessage() {
        Query query = getNewDaoSession().getMessageDao().queryBuilder().build();
        List<Message> messageList = query.list();
        if (messageList.size() > 0) {
            return messageList;
        } else {
            return null;
        }
    }
}
