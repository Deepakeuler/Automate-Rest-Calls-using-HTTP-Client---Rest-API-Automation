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

    //1. GET Met
    public void get(String url) throws ClientProtocolException,IOException {
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

        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("Status Code is ----->"+statusCode);

        //Now getting response
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");//UTF-8 -> String default charSet

        JSONObject responseJson= new JSONObject(responseString);//Converting rresponse string into Json obj

        System.out.println("Response Json from API --->"+responseJson);

        Header[] headerArray = closeableHttpResponse.getAllHeaders();

        HashMap<String, String> allHeaders = new HashMap<>();

        for(int i = 0; i<headerArray.length; i++){
            allHeaders.put(headerArray[i].getName(), headerArray[i].getValue());
        }

        System.out.println("Headers Array ---->"+allHeaders);

    }

}
