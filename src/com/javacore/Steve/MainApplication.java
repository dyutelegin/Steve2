package com.javacore.Steve;

import com.javacore.Steve.appserver.ServerApplication;
import com.javacore.Steve.dbservice.DBApplication;
import com.javacore.Steve.webservice.WebClientApplication;

public class MainApplication {

    public static void main(String[] args) {
        try {
            DBApplication.INSTANCE.start();
            WebClientApplication.INSTANCE.start();
            ServerApplication.INSTANCE.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
