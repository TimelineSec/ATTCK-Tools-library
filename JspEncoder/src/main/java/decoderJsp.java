import java.io.*;
import java.math.BigInteger;
public class decoderJsp {
    public decoderJsp(String srcFile, String desFile) throws IOException {
        FileInputStream fis = new FileInputStream(srcFile);  //文件输入流
        InputStreamReader isr = new InputStreamReader(fis);   //输入流读取器
        BufferedReader br = new BufferedReader(isr);
        String line = "";
        String text = "";
        while ((line = br.readLine())!= null){
            text = text + line;
        }
        String regex = "\\\\u+";
        String result = text.replaceAll(regex,"\\\\u");
        String[] split = result.split("\\\\u");
        String result1 = "";
        for (int i=0;i<split.length;i++){
            if(split[i].length()<4){
                result1 = result1 + split[i];
            }else {
                 int headerInt = Integer.parseInt(new BigInteger(split[i].substring(0,4),16).toString());
                 char splitHeader = (char)headerInt;
                 String splitFooter = split[i].substring(4);
                 result1 = result1 + splitHeader+splitFooter;
            }
        }
        FileWriter writer = new FileWriter(desFile);
        writer.write("");
        writer.write(result1);
        writer.flush();
        writer.close();
        System.out.println("目标文件已输出至" + desFile);
    }
}
