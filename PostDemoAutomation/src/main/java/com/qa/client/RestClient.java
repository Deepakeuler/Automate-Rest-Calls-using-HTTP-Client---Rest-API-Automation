package com.qa.client;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class RestClient {

    //1. GET Method
    public void get(String url) throws ClientProtocolException,IOException {
        //CloseableHttpClient --> Abstract class,  one method is there in HttpClients --> createDefault() : it will create an http default client
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // Different classes are there for Http methods like HttpGet
        // Created new HttpGet connection with this URL
        HttpGet httpGet = new HttpGet(url); // http get request
        httpClient.execute(httpGet); // passed http get request object --> will do work like clicking send button in postman(Executes it)

    }

}
