import java.io.*;

public class encoderJsp {
    public encoderJsp(String srcFile, String desFile) throws IOException {
        FileInputStream fis = new FileInputStream(srcFile);  //文件输入流
        InputStreamReader isr = new InputStreamReader(fis);   //输入流读取器
        BufferedReader br = new BufferedReader(isr);         //字符流读取器
        String line = "";
        String text = "";
        while ((line = br.readLine())!= null){
            text += line;
        }
        String subString = text.substring(2,text.length()-2);
        char[] charArray = subString.toCharArray();
        String result = "<%";
        for (int i=0;i<charArray.length;i++){
            if (i==0 || i%8 == 0){
                    String firstHex = Integer.toHexString(charArray[i]);
                    if (firstHex.length()==2){
                        firstHex = "00" + firstHex;
                        result = result + "\\uu" + firstHex;
                }
            }else if ((i%8 != 0) && (i%9 == 0)){
                    String nineHex = Integer.toHexString(charArray[i]);
                    if (nineHex.length() == 2) {
                        nineHex = "00" + nineHex;
                        result = result + "\\uuu" + nineHex;
                }
            }else if ((i != 0) && (i%8 != 0) && (i%9 != 0) && (i%55 == 0)){
                    result = result + charArray[i];
            } else{
                    String elseHex = Integer.toHexString(charArray[i]);
                    if (elseHex.length() == 2) {
                        elseHex = "00" + elseHex;
                        result = result + "\\u" + elseHex;
                  }
            }
        }
        result = result + "%>";
        FileWriter writer = new FileWriter(desFile);
        writer.write("");
        writer.write(result);
        writer.flush();
        writer.close();
        System.out.println("[!]文件编码完成，已输出至" + desFile);
    }
}
