import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.xml.sax.InputSource;

import java.io.*;
import java.util.List;

public class cDataEncodeJspx {
    public cDataEncodeJspx(String srcFile,String desFile) throws IOException, JDOMException {
        SAXBuilder sb = new SAXBuilder();
        Document doc = sb.build(new FileInputStream(srcFile));
        Element root = doc.getRootElement();
        Namespace namespace = root.getNamespace();
        List list = root.getChildren("scriptlet",namespace);
        String result = "";
        for (int i=0;i<list.size();i++){
            Element element = (Element)list.get(i);
            String context = element.getText();
            for (int j=0;j < context.length();j++){
                char ch = context.charAt(j);
                result = result + "<![CDATA[" + ch + "]]>";
            }
            element.setText(result);
        }
        List list1 = root.getChildren("declaration",namespace);
        result = "";
        for (int i=0;i<list1.size();i++){
            Element element = (Element)list1.get(i);
            String context = element.getText();
            for (int j=0;j < context.length();j++){
                char ch = context.charAt(j);
                result = result + "<![CDATA[" + ch + "]]>";
            }
            element.setText(result);
        }
        XMLOutputter outputter = new XMLOutputter();
        outputter.output(doc,new FileOutputStream(desFile));
        File fileIn = new File(desFile);
        FileInputStream fis = new FileInputStream(fileIn);
        InputStreamReader streamReader = new InputStreamReader(fis);
        BufferedReader bufread = new BufferedReader(streamReader);
        String line;
        StringBuilder strB = new StringBuilder();
        while ((line = bufread.readLine())!=null){
            strB.append(line);
        }
        streamReader.close();
        bufread.close();
        String strRes = String.valueOf(strB);
        strRes = strRes.replaceAll("&lt;","<");
        strRes = strRes.replaceAll("&gt;",">");
        File f = new File(desFile);
        FileWriter writer = new FileWriter(f);
        writer.write("");
        writer.write(strRes);
        writer.close();
        System.out.println("[!]文件编码完成，已输出至" + desFile);
    }
}
