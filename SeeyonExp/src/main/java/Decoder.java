import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.zip.GZIPInputStream;

public class Decoder {
    public String Decoder(String decodeStr) throws IOException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        decodeStr = URLDecoder.decode(decodeStr,"UTF-8");
        ByteArrayInputStream byteIn = new ByteArrayInputStream(decodeStr.getBytes("iso-8859-1"));
        GZIPInputStream gzipInput = new GZIPInputStream(byteIn);
        byte[] buffer = new byte[1024];
        int n;
        while ((n=gzipInput.read(buffer))>=0){
            byteOut.write(buffer,0,n);
        }
        return byteOut.toString();
    }
}
