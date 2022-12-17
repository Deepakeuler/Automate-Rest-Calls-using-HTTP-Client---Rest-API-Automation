package com.qa.test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetApiTestClass extends TestBase {

    TestBase testBase;
    String serviceurl;
    String apiUrl;
    String url;

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
        restClient.get(url);
    }
}
