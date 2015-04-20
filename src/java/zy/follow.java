/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zy;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author yuezhao
 */
public class follow {
    
     private final String USER_AGENT = "Mozilla/5.0";
    
    public void sendPut(String post_id,String access_token,Boolean is_following) throws MalformedURLException, IOException{
                
        
        String url = "https://api.linkedin.com/v1/posts/"+post_id+"/relation-to-viewer/is-following";
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("PUT");
        con.setRequestProperty("Authorization", "Bearer "+access_token);
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Content-Type", "application/xml");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        
        Boolean param = null;
        
        if(is_following.equals(true)){
            param = false;
        }else if(is_following.equals(false)){
            param = true;
        }

        String urlParameters = "<is-following>"+param+"</is-following>";

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
//        System.out.println("\nSending 'PUT' request to URL : " + url);
//        System.out.println("Post parameters : " + urlParameters);
//        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader( new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
        }
        in.close();

        //print result
//        System.out.println(response.toString());
    }
}
