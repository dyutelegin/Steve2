package com.javacore.Steve.db.dbstate;

import com.javacore.Steve.db.DBApplication;
import com.javacore.Steve.db.misc.DBConstants;
import com.javacore.Steve.db.misc.Utils;
import com.javacore.Steve.db.misc.DataHandler;
import com.javacore.Steve.db.data.Table;
import com.javacore.Steve.db.data.TableMetaData;
import com.javacore.Steve.db.server.DBServer;

public class DBStateInit extends DBState {

    public DBStateInit(String name) {
        super(name);
    }

    @Override
    public void enter() {
        System.out.println("Entering DBInit state");
        initTables();
        try {
            DBServer.INSTANCE.start();
            DBApplication.INSTANCE.changeState(DBApplication.INSTANCE.stateRun);
        }
        catch (Exception e){
            e.printStackTrace();
            DBApplication.INSTANCE.changeState(DBApplication.INSTANCE.stateStop);
        }
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
        DBApplication.INSTANCE.changeState(DBApplication.INSTANCE.stateStop);
    }
}