import java.io.*;
import java.util.Base64;

public class classIn {
    public  classIn(String srcFile, String desFile) throws IOException, InterruptedException {
        if (System.getProperties().getProperty("os.name").startsWith("win")||System.getProperties().getProperty("os.name").startsWith("Win")){
            Runtime.getRuntime().exec("cmd /c" + "certutil -f -encode" + " " +srcFile + " " +desFile);
            Thread.sleep(3*1000);
            BufferedReader in = new BufferedReader(new FileReader(desFile));
            String str = "";
            String result = "";
            while ((str = in.readLine())!=null){
                result = result + str;
            }
            in.close();
            result = result.replaceAll("\\\\r","");
            result = result.replaceAll("\\\\n","");
            result = result.replace("-----BEGIN CERTIFICATE-----","");
            result = result.replace("-----END CERTIFICATE-----","");
            File file = new File(desFile);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write(result);
            bw.close();
            fileWriter.close();
            System.out.println("[!]文件已base64编码到" + desFile);
        }else {
            String execString = "cat " + srcFile +" |base64 >" +desFile;
            byte[] execByte = execString.getBytes();
            String execBase = Base64.getEncoder().encodeToString(execByte);
            Runtime.getRuntime().exec("bash -c {echo," + execBase.trim()+ "}|{base64,-d}|{bash,-i}");
            System.out.println("[!]文件已base64编码到" + desFile);
        }
    }
}
