package com.javacore.Steve.webservice;

import java.util.Map;

public class Template {

    public String templateFileName; //html page
    public Map<String, String> values;

    public Template(String fileName, Map<String, String> values){
        this.values = values;
        this.templateFileName = fileName;
    }

    public String compile(){
        String templateText = ""; //Read file from disk
        for (String key: values.keySet()) {
            templateText = templateText.replace("{{" + key + "}}", values.get(key));
        }
        return templateText;
    }
    //values.put("firstName", "Vasia");
    //values.put("lastName", "Petrov");

}
