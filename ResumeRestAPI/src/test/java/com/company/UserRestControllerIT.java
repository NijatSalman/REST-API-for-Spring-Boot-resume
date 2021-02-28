package com.company;

import com.company.dto.ResponseDTO;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.client.TestRestTemplateExtensionsKt;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.security.oauth2.client.test.OAuth2ContextConfiguration;
import org.springframework.security.oauth2.client.test.OAuth2ContextSetup;
import org.springframework.security.oauth2.client.test.RestTemplateHolder;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestOperations;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes ={ResumeRestApiApplication.class},webEnvironment = WebEnvironment.DEFINED_PORT)
@OAuth2ContextConfiguration(MyDetails.class) //each request use MyDetails.class
public class UserRestControllerIT implements RestTemplateHolder {




    //Random or Defined port will be set here
    @LocalServerPort
    private int port;

    public int getPort(){
        return port;
    }

    @Rule
    public OAuth2ContextSetup context=OAuth2ContextSetup.standard(this);

    //Spring helps to send request to your own restful api
    RestOperations restTemplate;

    @Override
    public RestOperations getRestTemplate() {
        return restTemplate;
    }

    @Override
    public void setRestTemplate(RestOperations restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Test
    public void justCallDTOObject(){
        String url="http://localhost:"+port+"/users/1";
        ResponseDTO resp = this.restTemplate.getForObject(url, ResponseDTO.class);
        System.out.println(resp);

        LinkedHashMap<String, Object> hash=(LinkedHashMap<String, Object>)  resp.getObj();

        Assert.assertEquals("must be equal 1", 1L, Long.valueOf(hash.get("id")+"").longValue());

        System.out.println(resp);
    }

}

//Helps you to login restApi application.access Restful security
class MyDetails extends ResourceOwnerPasswordResourceDetails {
    public MyDetails(final Object obj){
        UserRestControllerIT controllerTestIT=(UserRestControllerIT) obj;
        setAccessTokenUri("http://localhost:"+controllerTestIT.getPort()+"/oauth/token");
        setClientId("ClientId");
        setClientSecret("pswEncode");

        List<String> scopes=new ArrayList<String>();
        scopes.add("read");

        setScope(scopes);
        setUsername("nijatsalman@yahoo.com");
        setPassword("12345");
        setAuthenticationScheme(AuthenticationScheme.header);
    }
}