package com.qa.client;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

public class RestClient {

    //1. GET Method
    public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
        //CloseableHttpClient --> Abstract class,  one method is there in
        // HttpClients --> createDefault() : it will create an http default client
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // Different classes are there for Http methods like HttpGet
        // Created new HttpGet connection with this URL
        HttpGet httpGet = new HttpGet(url); // http get request

        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet); // passed http get request object --> will do work like
        // clicking send button in postman(Executes it)
        //CloseableHttpResponse is an interface --Executes a request using the default context and processes the response using the
        // given response handler. CloseableHttpResponse.execute(HttpUriRequest request) Executes HTTP request using the default context.


        return closeableHttpResponse;
    }


    //2. Get Method with Headers


}
