import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.zip.GZIPOutputStream;

public class Encoder {

    public String Encoder(String encodeStr) throws IOException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        GZIPOutputStream gzipOut = new GZIPOutputStream(byteOut);
        gzipOut.write(encodeStr.getBytes("UTF-8"));
        gzipOut.close();
        gzipOut.flush();
        String gzipEnStr  = byteOut.toString("iso-8859-1");
        return URLEncoder.encode(gzipEnStr,"UTF-8");
    }
}
