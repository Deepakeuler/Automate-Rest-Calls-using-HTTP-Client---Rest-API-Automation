package com.qa.base;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

    public Properties prop;
    public int Response_status_code_200 = 200;
    public int Response_status_code_500 = 500;
    public int Response_status_code_400 = 400;
    public int Response_status_code_401 = 401;
    public int Response_status_code_201 = 201;


    public TestBase() {

        try{
            prop = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("usr.dir")+"src/main/java/com/qa/config/config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
