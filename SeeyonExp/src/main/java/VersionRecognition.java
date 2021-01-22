import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VersionRecognition {
    public void VersionRecognition(String url) throws Exception {
        HTTPClient httpClient = new HTTPClient();
        JsonRead jsonRead = new JsonRead();
        JSONObject vulObject = jsonRead.JsonRead();
        JSONArray versions = (JSONArray) vulObject.get("ImpactVersion");
        JSONArray attUrl = (JSONArray) vulObject.get("url");
        String method = vulObject.get("pocMethod").toString();
        String poc = vulObject.get("pocContent").toString();
        String res = vulObject.get("Response").toString();
        List<Object> versionsList = versions.toList();
        List<String> versionList1 = new ArrayList<>();
        for (Object version:versionsList) {
            versionList1.add((String)version);
        }
        if (url.startsWith("http:")){
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
            String result = EntityUtils.toString(response.getEntity());
            if (result != null){
                Integer indexVersionFlag = result.lastIndexOf("all-min.css");
                String version = result.substring(indexVersionFlag,indexVersionFlag+21);
                String[] versionArr = version.split("=");
                version = versionArr[1];
                version = version.replace("_",".").toLowerCase();
                for (String versionString : versionList1) {
                    if (version.startsWith(versionString.toLowerCase())){
                        System.out.println("[*]命中影响版本，开始扫描");
                        String attackUrl="";
                        for (Object atturl:attUrl) {
                            attackUrl = url + atturl;
                        }
                        Scanner scanner = new Scanner();
                        System.out.println(scanner.Scanner(poc,attackUrl,method,res));
                    }
                    continue;
                }
            }else {
                System.out.println("[!]网页无内容，请检查后重新运行");
            }
        }else {
            CloseableHttpClient client = new SSLClient();
            URIBuilder uriBuilder = new URIBuilder(url);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            CloseableHttpResponse response = client.execute(httpGet);
            String result = EntityUtils.toString(response.getEntity());
            if (result != null) {
                Integer indexVersionFlag = result.lastIndexOf("all-min.css");
                String version = result.substring(indexVersionFlag,indexVersionFlag+21);
                String[] versionArr = version.split("=");
                version = versionArr[1];
                version = version.replace("_",".").toLowerCase();
                for (String versionString : versionList1) {
                    if (version.startsWith(versionString.toLowerCase())){
                        System.out.println("[*]命中影响版本，开始扫描");
                        String attackUrl="";
                        for (Object atturl:attUrl) {
                            attackUrl = url + atturl;
                        }
                        Scanner scanner = new Scanner();
                        System.out.println(scanner.Scanner(poc,attackUrl,method,res));
                    }
                    continue;
                }
            }else {
                System.out.println("[!]网页无内容，请检查后重新运行");
            }
        }
    }
}
