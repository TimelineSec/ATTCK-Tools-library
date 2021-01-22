import org.jdom2.JDOMException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, JDOMException, InterruptedException {
      System.out.println("" +
                  "[*]工具使用：\n" +
                  "[*]JSP文件Unicode解码：java -jar toolName UniDe srcFile desFile\n" +
                  "[*]JSP文件Unicode编码：java -jar toolName UniEn srcFile desFile\n" +
                  "[*]JSPX文件Html解码：java -jar toolName HtmlDe srcFile desFile\n" +
                  "[*]JSPX文件Html编码：java -jar toolName HtmlEn srcFile desFile\n" +
                  "[*]JSPX文件CDATA解码：java -jar toolName CdataDe srcFile desFile\n" +
                  "[*]JSPX文件CDATA编码：java -jar toolName CdataEn srcFile desFile\n" +
                  "[*]Base64解码为字节码并输出为class文件：java -jar toolName classOut srcFile desFile\n" +
                  "[*]Class文件编码为Base64：java -jar toolName classIn srcFile desFile\n" +
                  "[*]Please enjoy it!");
      if (args[0].equals("UniDe")){
          decoderJsp decoder = new decoderJsp(args[1],args[2]);
      }else if (args[0].equals("UniEn")){
          encoderJsp encoder = new encoderJsp(args[1],args[2]);
      }else if (args[0].equals("classOut")){
          classOut clo = new classOut(args[1],args[2]);
      }else if (args[0].equals("classIn")){
          classIn cli = new classIn(args[1],args[2]);
      }else if(args[0].equals("HtmlEn")){
          htmlEncodeJspx htmlEncodeJspx = new htmlEncodeJspx(args[1],args[2]);
      }else if(args[0].equals("HtmlDe")){
          htmlDecodeJspx htmlDecodeJspx = new htmlDecodeJspx(args[1],args[2]);
      }else if(args[0].equals("CdataEn")){
          cDataEncodeJspx cDataEncodeJspx = new cDataEncodeJspx(args[1],args[2]);
      }else if(args[0].equals("CdataDe")){
          cDataDecodeJspx cDataDecodeJspx = new cDataDecodeJspx(args[1],args[2]);
      }else {
          System.out.println("[!]命令格式错误，请重新输入!");
      }
    }
}

