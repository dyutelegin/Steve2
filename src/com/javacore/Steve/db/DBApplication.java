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
    public DBState stateInit = new DBStateInit("Initilizing");
    public DBState stateRun = new DBStateRunning("Running...");
    public DBState stateStop = new DBStateStop("Shutting down");

    public void start() {
        changeState(stateInit);
    }

    public void stop() {
        currentState.onStop();
    }

    public QueryResult query(String query) {
        return null;
    }

    public String getStateName(){
        return currentState.getName();
    }

    public void changeState(DBState state) {
        if (currentState != null) {
            if (currentState.equals(state)) {
                return;
            } else {
                currentState.exit();
            }
        }
        currentState = state;
        currentState.enter();
    }
}

