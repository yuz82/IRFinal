/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zy;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
 
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;

/**
 *
 * @author yuezhao
 */
public class getToken {
   
    private final String USER_AGENT = "Mozilla/5.0";
    
    // HTTP POST request
    public String sendPost(String id, String code) throws Exception {
 
        String url = "https://www.linkedin.com/uas/oauth2/accessToken";
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String grant_type = new String();
        String redirect_uri = new String();
        String client_id = new String();
        String client_secret = new String();
        
        grant_type = "authorization_code";
        redirect_uri = "http%3A%2F%2Flocalhost%3A8983%2Fsolr%2Fsearch%2Findex.html%3Fid%3D"+id;//g-41203-S-193130289";
        client_id = "78uift3465j6c6";
        client_secret = "0FDCfIXfPZzrlIxe";
        String urlParameters = "id="+id+"&grant_type="+grant_type+"&code="+code+"&redirect_uri="+redirect_uri
                +"&client_id="+client_id+"&client_secret="+client_secret;


        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
//        System.out.println("\nSending 'POST' request to URL : " + url);
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
        String json = response.toString();
        String[] split1 = json.split(":");
        String[] split2 = split1[1].split(",");
        String[] split3 = split2[0].split("\"");
        String access_token = split3[1];
        
//        System.out.println(access_token);
        return access_token;

}
    
}
