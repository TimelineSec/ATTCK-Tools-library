import java.io.*;

public class htmlDecodeJspx {
    public htmlDecodeJspx(String srcFile,String desFile) throws IOException {
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
        String result = "";
        String nowStr = "";
        String[] valueArray = strRes.split("&");
        for (int i=0;i<valueArray.length;i++){
            if (valueArray[i].startsWith("#x")){
                if (valueArray[i].length()==5){
                    nowStr = valueArray[i];
                    nowStr = nowStr.substring(2,4);
                    int num = Integer.parseInt(nowStr,16);
                    char ch = (char)num;
                    result = result + ch;
                }else {
                    nowStr = valueArray[i];
                    nowStr = nowStr.substring(2,4);
                    int num = Integer.parseInt(nowStr,16);
                    char ch = (char)num;
                    //System.out.println(valueArray[i].length());
                    result = result + ch + valueArray[i].substring(4);
                }
            } else {
                result = result + valueArray[i];
            }
        }
        File f = new File(desFile);
        FileWriter writer = new FileWriter(f);
        writer.write("");
        writer.write(result);
        writer.close();
        System.out.println("目标文件已输出至" + desFile);
    }
}
