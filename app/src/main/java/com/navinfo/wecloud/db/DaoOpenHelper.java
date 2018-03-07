package com.navinfo.wecloud.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by liudepeng on 2017/2/16 0016.
 * mail:liudepeng@navinfo.com
 */
public class DaoOpenHelper extends DaoMaster.OpenHelper {
    public DaoOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        MigrationHelper.migrate(db, UserDao.class);
    }
}
