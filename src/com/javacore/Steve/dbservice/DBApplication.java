package com.javacore.Steve.dbservice;

import com.javacore.Steve.dbservice.data.Table;
import com.javacore.Steve.dbservice.data.query.QueryResult;
import com.javacore.Steve.dbservice.dbstate.DBState;
import com.javacore.Steve.dbservice.dbstate.DBStateInit;
import com.javacore.Steve.dbservice.dbstate.DBStateRunning;
import com.javacore.Steve.dbservice.dbstate.DBStateStop;

import java.util.HashMap;
import java.util.Map;

public enum DBApplication {
    INSTANCE;

    private Map<String, Table> tables = new HashMap<>();

    public static final String DATA_ENCRYPTION_LEVEL = "LOW";
    private DBState currentState;
    public DBState stateInit = new DBStateInit("Initializing");
    public DBState stateRun = new DBStateRunning("Running");
    public DBState stateStop = new DBStateStop("Shutting Down");

    public void start() {
        changeState(stateInit);
    }

    public void stop() {
        currentState.onStop();
    }

    public QueryResult query(String query) {
        return currentState.onQuery(query);
    }

    public String getStateName() {
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

    public void addTable(String tableName, Table table) {
        tables.put(tableName, table);
    }

    public Table getTable(String tableName) {
        return tables.get(tableName);
    }

}
