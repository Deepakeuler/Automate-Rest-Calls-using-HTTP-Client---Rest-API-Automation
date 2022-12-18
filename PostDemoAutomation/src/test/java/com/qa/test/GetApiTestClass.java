package com.qa.test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;
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
        //closeableHttpResponse this is having the complete response of a get request including header, status and string

        // Status Code
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("Status Code is ----->" + statusCode);
        Assert.assertEquals(statusCode, Response_status_code_200, "assert code is not 200");
        //How you'll validate that what is where in json response--> for this i've written one jSON utility

        //Above is actual get response--> how can you validate that per_page = 3 and other things from this response
        // Json String response
        //UTF-8(format) -> String default charSet
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        // Apart from status code, json obj we get headers too
        JSONObject responseJson = new JSONObject(responseString);//Converting response string into Json obj
        System.out.println("Response Json from API --->" + responseJson);
        //getting exact values from json
        String perPageValue = TestUtil.getValueByJPath(responseJson,"/per_page");
        System.out.println("Value of per page is ---->"+perPageValue);

        //confirming that per page value is 3
        Assert.assertEquals(Integer.parseInt(perPageValue),3);


        String total = TestUtil.getValueByJPath(responseJson,"/total");
        System.out.println("total of per page is ---->"+total);

        Assert.assertEquals(total,12);


        //get value from JSON array
        String LastName = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
        String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
        String email = TestUtil.getValueByJPath(responseJson, "/data[0]/email");
        String firstName = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
        String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");

        System.out.println(LastName);
        System.out.println(id);
        System.out.println(email);
        System.out.println(avatar);
        System.out.println(firstName);
        //all headers
        Header[] headerArray = closeableHttpResponse.getAllHeaders();
        HashMap<String, String> allHeaders = new HashMap<>();

        for (int i = 0; i < headerArray.length; i++) {
            allHeaders.put(headerArray[i].getName(), headerArray[i].getValue());
        }

        System.out.println("Headers Array ---->" + allHeaders);
    }


    @Test
    public void getAPITestWithHeaders() throws IOException {
        RestClient restClient = new RestClient();
        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("Content_type","application/json");
        headerMap.put("user_name","test@flipkart.com");
        headerMap.put("password","test123");
        headerMap.put("auth_token","12345");

        // Status Code
        closeableHttpResponse = restClient.get(url);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("Status Code is ----->" + statusCode);
        Assert.assertEquals(statusCode, Response_status_code_200, "assert code is not 200");


        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        JSONObject responseJson = new JSONObject(responseString);//Converting response string into Json obj
        System.out.println("Response Json from API --->" + responseJson);
        //getting exact values from json
        String perPageValue = TestUtil.getValueByJPath(responseJson,"/per_page");
        System.out.println("Value of per page is ---->"+perPageValue);

        //confirming that per page value is 3
        Assert.assertEquals(Integer.parseInt(perPageValue),3);

        String total = TestUtil.getValueByJPath(responseJson,"/total");
        System.out.println("total of per page is ---->"+total);
        Assert.assertEquals(total,12);

        //get value from JSON array
        String LastName = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
        String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
        String email = TestUtil.getValueByJPath(responseJson, "/data[0]/email");
        String firstName = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
        String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");

        System.out.println(LastName);
        System.out.println(id);
        System.out.println(email);
        System.out.println(avatar);
        System.out.println(firstName);
        //all headers
        Header[] headerArray = closeableHttpResponse.getAllHeaders();
        HashMap<String, String> allHeaders = new HashMap<>();

        for (int i = 0; i < headerArray.length; i++) {
            allHeaders.put(headerArray[i].getName(), headerArray[i].getValue());
        }

        System.out.println("Headers Array ---->" + allHeaders);
    }
}
