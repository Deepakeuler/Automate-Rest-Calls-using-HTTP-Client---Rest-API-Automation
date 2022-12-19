package com.qa.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class PostApiTestClass extends TestBase {

    TestBase testBase;
    String serviceurl;
    String apiUrl;
    String url;
    CloseableHttpResponse closeableHttpResponse;
    RestClient restClient;

    @BeforeMethod
    public void setUp() throws ClientProtocolException, IOException {
        testBase = new TestBase();
        serviceurl = prop.getProperty("URL");
        apiUrl = prop.getProperty("serviceURL");
        url = serviceurl + apiUrl;
    }

    @Test
    public void postApiTest() throws IOException {
        restClient = new RestClient();
        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("Content_type", "application/json");
        //Aas in the map or payload we're passing user data, so we'll create an user class inside java.com.qa.data package
        //w.r.t payload we'll create an equivalent java class

        //Now we need an utility which can covert this user class into json, because along with request i can't pass this java class
        //java class --> java obj --> Json obj } which will be provided by Jackson Api
        //toh we need a dependency for that i.e Jackson-databind
        //There in a class in there -> ObjectMapper
        final ObjectMapper mapper = new ObjectMapper();
        Users users = new Users("morpheus", "leader");//expected user type
        //obj to json file
        //write value -> where exactly you want to generate your json file, toh we'll create a file in data
        //now obj to json file
        mapper.writeValue(new File("/Users/deepak.d11/Desktop/Utilities/PostDemoAutomation/src/main/java/com/qa/data/users.json"), users);

        //obj to json in String
        String userJsonString = mapper.writeValueAsString(users);
        System.out.println(userJsonString);

        //in RestClient we've already created a post call
        closeableHttpResponse = restClient.post(url,userJsonString,headerMap );

        //1. Check Status Code
        int status = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(status,Response_status_code_201,"Status code in not expected");

        //2.now json String is correct or not
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), StandardCharsets.UTF_8);

        //now String to json
        JSONObject responseJson = new JSONObject(responseString);
        System.out.println("Response from API is ----->"+responseJson);

        //json to java obj
        //Now how you'll validate the response--> for that there's a method called read value
        Users usersResponseObj = mapper.readValue(responseString,Users.class);//response and type of response & it'll return User class obj
        //actual user obj which we're getting
        System.out.println(usersResponseObj);

        Assert.assertTrue(users.getName().equals(usersResponseObj.getName()));
        Assert.assertTrue(users.getJob().equals(usersResponseObj.getJob()));

        System.out.println(usersResponseObj.getCreatedAt());
        System.out.println(usersResponseObj.getId());

    }
}
