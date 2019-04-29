package com.javacore.Steve.db;

import com.javacore.Steve.db.data.QueryResult;
import com.javacore.Steve.db.dbstate.DBState;
import com.javacore.Steve.db.dbstate.DBStateInit;
import com.javacore.Steve.db.dbstate.DBStateRunning;
import com.javacore.Steve.db.dbstate.DBStateStop;

public enum DBApplication {
    INSTANCE;

    public static final String DATA_ENCRYPTION_LEVEL = "LOW";
    private DBState currentState;
    public DBState stateInit = new DBStateInit();
    public DBState stateRun = new DBStateRunning();
    public DBState stateStop = new DBStateStop();

    public void start() {
//        changeState(stateInit);
    }

    public void stop() {
        currentState.onStop();
    }

    public QueryResult query(String query) {
        return null;
    }
}

