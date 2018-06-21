package com.ksw.main;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultElement;

import com.ksw.util.MakeDownFile;
import com.ksw.util.MakeXML;

public class Main
{
    public static void main(String args[]) throws Exception
    {
        String downPath = "C:/Temp/ecg/";
        String downURL = "https://www.daum.net/";
        
        process(downURL, downPath, 0);
    }

    public static void process(String pageAddr, String downPath, int node) throws MalformedURLException, DocumentException
    {
        // 다운로드 페이지
        URL url = new URL(pageAddr);
        
        // xml 이름 제작
        String xmlName = pageAddr;
        xmlName = xmlName.substring(xmlName.indexOf("//")+2);
        xmlName = xmlName.replace("/", " ");
        xmlName = xmlName.trim() + ".xml";
        
        // xml 저장 위치
        String xmlPath = downPath + "XML/";
        makeDir(xmlPath);
        
        // xml 파일 제작
        MakeXML mx = new MakeXML(url, xmlPath+xmlName);
        
        //ArrayList<String> pathList = makePathList(new File(xmlPath+xmlName), pageAddr, downPath, node);
        
//        if(pathList != null)
//        for(int i = 0; i < pathList.size(); i++)
//            process(pageAddr+pathList.get(i), downPath+pathList.get(i), 1);
        
    }
    
    public static ArrayList<String> makePathList(File file, String pageAddr, String downPath,int node) throws DocumentException
    {
        Document doc = dom4jRun(file);
        List<Node> nodes = null;
        
        if(node == 0) nodes = doc.selectNodes("/html/body/main/article/pre/a");
        else if(node == 1) nodes = doc.selectNodes("/html/body/pre/a");
        ArrayList<String> pathList = new ArrayList<String>();
        MakeDownFile mdf = new MakeDownFile();
        String newNodeString = null;
        
        makeDir(downPath);
        for(int i = 0 ; i < nodes.size(); i ++)
        {
            String nodeString = nodes.get(i).getText();
            if(checkTxt(nodeString))
                continue;
            if(nodeString.indexOf('/') == -1)
            {
                DefaultElement de = (DefaultElement) nodes.get(i);
                newNodeString = de.attributeValue("href");
//                if(nodeString.indexOf('?') != -1)
//                    newNodeString = nodeString.replace("?","-");
                mdf.fileUrlReadAndDownload(pageAddr+newNodeString, downPath+newNodeString);
            }
            else
                pathList.add(nodeString);
        }
        
        return pathList;
    }
    
    public static boolean checkTxt(String text)
    {
        if (text.equals("Name") || text.equals("Last modified") || text.equals("Size") || text.equals("Description") || text.equals("Parent Directory"))
            return true;
        return false;
    }

    // 폴더생성
    public static void makeDir(String filePath)
    {
        File file = new File(filePath);
        if (!file.exists())
            file.mkdirs();
        else return;
    }

    // 돔4J에 태운다.
    public static Document dom4jRun(File file) throws DocumentException
    {
        SAXReader reader = new SAXReader(false);
        Document document = reader.read(file);
        return document;
    }
}