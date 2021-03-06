package com.javacore.Steve.dbservice.dbstate;

import com.javacore.Steve.dbservice.DBApplication;
import com.javacore.Steve.dbservice.misc.DBConstants;
import com.javacore.Steve.dbservice.misc.Utils;
import com.javacore.Steve.dbservice.misc.DataHandler;
import com.javacore.Steve.dbservice.data.Table;
import com.javacore.Steve.dbservice.data.TableMetaData;
import com.javacore.Steve.dbservice.server.DBServer;

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
        } catch (Exception e) {
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
                DBApplication.INSTANCE.addTable(metaData.getTableName(), table);
            }
        });
    }

    @Override
    public void onStop() {
        //check if everything is ok
        DBApplication.INSTANCE.changeState(DBApplication.INSTANCE.stateStop);
    }
}
