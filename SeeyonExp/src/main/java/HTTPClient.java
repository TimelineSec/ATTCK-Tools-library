import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import sun.misc.BASE64Decoder;

import java.io.IOException;

public class HTTPClient {
    public String sendPost(String url, String param) throws Exception {
        if(url.startsWith("http:")){
            HttpPost httpPost = new HttpPost(url);
            StringEntity stringEntity = new StringEntity(param);
            stringEntity.setContentType("application/x-www-form-urlencoded");
            //httpPost.setHeader("Connection","close");
            httpPost.setEntity(stringEntity);
            HttpResponse response = new DefaultHttpClient().execute(httpPost);
            String result = EntityUtils.toString(response.getEntity());
            System.out.println(result);
            return result;
        }else{
            CloseableHttpClient client;
            URIBuilder uriBuilder = new URIBuilder(url);
            int port = uriBuilder.getPort();
            if (port != 443){
                client = new SSLClient(uriBuilder.getPort());
            }else {
                client = new SSLClient(443);
            }
            HttpPost httpPost = new HttpPost(uriBuilder.build());
            StringEntity stringEntity = new StringEntity(param);
            stringEntity.setContentType("application/x-www-form-urlencoded");
            //httpPost.setHeader("Connection","close");
            httpPost.setEntity(stringEntity);
            CloseableHttpResponse response = client.execute(httpPost);
            String result = EntityUtils.toString(response.getEntity());
            System.out.println(result);
            return result;
        }
    }
    //http与https 得到回显内容
    public String getRes(String url) throws Exception {
        String result;
        if (url.startsWith("http:")){
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(url);
            //request.setHeader("Connection","close");
            HttpResponse response = client.execute(request);
            result = EntityUtils.toString(response.getEntity());
            if (result != null){
                result = decode(result);
                System.out.println(result);
            }
        }else {
            CloseableHttpClient client;
            URIBuilder uriBuilder = new URIBuilder(url);
            int port = uriBuilder.getPort();
            if (port != 443){
                client = new SSLClient(uriBuilder.getPort());
            }else {
                client = new SSLClient(443);
            }
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            CloseableHttpResponse response = client.execute(httpGet);
            result = EntityUtils.toString(response.getEntity());
            if (result != null) {
                result = decode(result);
                System.out.println(result);
            }
        }
        return result;
    }
    //测试是否有
    //http与https 删除回显输出文件
    public  void doDel(String url,String param) throws Exception {
        if (url.startsWith("http:")){
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Connection","close");
            StringEntity stringEntity = new StringEntity(param);
            stringEntity.setContentType("application/x-www-form-urlencoded");
            httpPost.setEntity(stringEntity);
            HttpClient client = new DefaultHttpClient();
            HttpResponse response = new DefaultHttpClient().execute(httpPost);
            if (response != null){
                System.out.println("[*]命令执行完毕");
            }
        }else {
            CloseableHttpClient client;
            URIBuilder uriBuilder = new URIBuilder(url);
            int port = uriBuilder.getPort();
            if (port != 443){
                client = new SSLClient(uriBuilder.getPort());
            }else {
                client = new SSLClient(443);
            }
            HttpPost httpPost = new HttpPost(uriBuilder.build());
            httpPost.setHeader("Connection","close");
            StringEntity stringEntity = new StringEntity(param);
            stringEntity.setContentType("application/x-www-form-urlencoded");
            httpPost.setEntity(stringEntity);
            CloseableHttpResponse response = client.execute(httpPost);
            if (response !=null){
                System.out.println("[*]命令执行完毕");
            }
        }
    }
    //BASE65解码模块
    public String decode(String string) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        String deStr = "";
        for(int i=0;i<string.length();i=i+2){
            String str2 = string.substring(i,i+2);
            char char2 = (char)(Integer.parseInt(str2,16)-1);
            deStr = deStr + char2;
        }
        deStr = deStr.replaceAll("ê","");
        String realStr = new String(decoder.decodeBuffer(deStr),"UTF-8");
        return realStr;
    }
}
