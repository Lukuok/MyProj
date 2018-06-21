package com.ksw.util;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MakeXML
{
    private H2X h2x;
    
    public MakeXML(URL url, String savePath)
    {
        //xml로 변환
        h2x = new H2X(getHtmlText(url, savePath));
        
        //파일 저장
        writeText(h2x.getXMLString(), savePath);
    }
    
    //xml파일 생성
    public void writeText(String xmlString, String savePath)
    {
        File file = new File(savePath);
        try
        {
            FileWriter f_writer = new FileWriter(file, false);
            BufferedWriter b_writer = new BufferedWriter(f_writer);
            b_writer.write(xmlString);
            b_writer.close();
            f_writer.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    //html소스를 얻어온다.
    public String getHtmlText(URL url, String savePath)
    {
        HttpURLConnection con = null;
        StringBuilder response = new StringBuilder();
        try
        {
            BufferedReader rd = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = rd.readLine()) != null)
            {
                response.append(line);
//                response.append("\\n");
            }
            rd.close();
        }
        catch (Exception e)
        {
            System.out.println("HtmlText 에러 발생");
        }
        finally
        {
            if (con != null)
                con.disconnect();
        }
        writeText(response.toString(), savePath+".html");
        return response.toString();
    }
}
