package com.ksw.util;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class MakeDownFile
{
    public void fileUrlReadAndDownload(String fileAddress, String downPath)
    {
        int size = 1024 * 1024;
        OutputStream outStream = null;
        URLConnection uCon = null;
        InputStream is = null;
        try
        {
            URL Url;
            byte[] buf;
            int byteRead;
            int byteWritten = 0;
            Url = new URL(fileAddress);
            outStream = new BufferedOutputStream(new FileOutputStream(downPath));
            uCon = Url.openConnection();
            is = uCon.getInputStream();
            buf = new byte[size];
            while ((byteRead = is.read(buf)) != -1)
            {
                outStream.write(buf, 0, byteRead);
                byteWritten += byteRead;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                is.close();
                outStream.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
