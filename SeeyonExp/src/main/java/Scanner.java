public class Scanner{
    public String Scanner(String poc,String url,String method,String res) throws Exception {
        HTTPClient httpClient = new HTTPClient();
        String result = "";
        if (method.toLowerCase().equals("post")) {
            result = httpClient.sendPost(url, poc);
        } else {
            result = httpClient.getRes(url);
        }
        if (result.contains(res)) {
            return url + "\n[!]:漏洞存在";
        } else {
            return url + "[*]:漏洞不存在";
        }
    }
}
