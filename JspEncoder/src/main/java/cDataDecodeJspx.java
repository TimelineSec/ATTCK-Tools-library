import java.io.*;

public class cDataDecodeJspx {
    public cDataDecodeJspx(String srcFile,String desFile) throws IOException {
        File fileIn = new File(srcFile);
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
        strRes = strRes.replaceAll("<!\\[CDATA\\[","");
        strRes = strRes.replaceAll("]]>","");
        File f = new File(desFile);
        FileWriter writer = new FileWriter(f);
        writer.write("");
        writer.write(strRes);
        writer.close();
        System.out.println("目标文件输出至" + desFile);
    }
}
