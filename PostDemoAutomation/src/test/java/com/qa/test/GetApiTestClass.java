package com.qa.test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

public class GetApiTestClass extends TestBase {

    TestBase testBase;
    String serviceurl;
    String apiUrl;
    String url;
    CloseableHttpResponse closeableHttpResponse;

    @BeforeMethod
    public void setUp() throws ClientProtocolException, IOException {
        testBase = new TestBase();
        serviceurl = prop.getProperty("URL");
        apiUrl = prop.getProperty("serviceURL");
        url = serviceurl + apiUrl;
    }

    @Test
    public void getAPITest() throws IOException {
        RestClient restClient = new RestClient();
        closeableHttpResponse = restClient.get(url);

        // Status Code
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("Status Code is ----->" + statusCode);
        Assert.assertEquals(statusCode,Response_status_code_200,"assert code is not 200");

        // Json String response
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");//UTF-8 -> String default charSet
        JSONObject responseJson = new JSONObject(responseString);//Converting response string into Json obj
        System.out.println("Response Json from API --->" + responseJson);
        //all headers
        Header[] headerArray = closeableHttpResponse.getAllHeaders();
        HashMap<String, String> allHeaders = new HashMap<>();

        for (int i = 0; i < headerArray.length; i++) {
            allHeaders.put(headerArray[i].getName(), headerArray[i].getValue());
        }

        System.out.println("Headers Array ---->"+allHeaders);
    }
}
