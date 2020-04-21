package com.easyup.easyup;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

class HttpClientExample {
    List<NameValuePair> nameValuePairs;

    public HttpClientExample(List<NameValuePair> nameValuePairs) {

        this.nameValuePairs = nameValuePairs;


    }

    private final String USER_AGENT = "Mozilla/5.0";


    // HTTP POST request
    public String sendPost(String urle) throws Exception {


        String url = urle;


        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);


        post.setEntity(new UrlEncodedFormEntity(this.nameValuePairs));

        HttpResponse response = client.execute(post);

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        return result.toString();

    }


}


