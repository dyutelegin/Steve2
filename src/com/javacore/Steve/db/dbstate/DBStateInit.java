package com.javacore.Steve.db.dbstate;

import com.javacore.Steve.db.DBApplication;
import com.javacore.Steve.db.misc.DBConstants;
import com.javacore.Steve.db.misc.Utils;
import com.javacore.Steve.db.misc.DataHandler;
import com.javacore.Steve.db.data.Table;
import com.javacore.Steve.db.data.TableMetaData;

public class DBStateInit extends DBState {
    @Override
    public void enter() {
        System.out.println("Entering DBInit state");
        initTables();
//        DBApplication.INSTANCE.changeState(DBApplication.INSTANCE.stateRun);
    }

    private void initTables() {
        Utils.readDir(DBConstants.TABLE_DIR, new DataHandler() {
            @Override
            public void handleFile(String filePath) {
                TableMetaData metaData = TableMetaData.loadFromFile(filePath);
                Table table = new Table(metaData);
                table.load();
            }
        });
    }

    @Override
    public void onStop() {
        //check if everything is ok
  //      DBApplication.INSTANCE.changeState(DBApplication.INSTANCE.stateStop);
    }
}