package kr.ac.inha.board.common;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnection {
	
	public String getMethod(String apiUrl) throws Exception {   
        StringBuffer result = new StringBuffer();

        System.out.println("========================1");
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            System.out.println(url);
            System.out.println("========================2");
            urlConnection.connect();
            System.out.println("========================3");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
            System.out.println("========================4");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream, "UTF-8"));
            System.out.println("========================5");
            String returnLine;
            while((returnLine = bufferedReader.readLine()) != null) {
                result.append(returnLine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result.toString();
	}
	
}
