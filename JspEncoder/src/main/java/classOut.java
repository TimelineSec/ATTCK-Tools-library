import java.io.*;
import java.lang.reflect.InvocationTargetException;

class classOut {
    public classOut(String srcFile,String desFile) throws ClassNotFoundException, NoSuchMethodException, IOException, InvocationTargetException, IllegalAccessException {
        FileInputStream fis = new FileInputStream(srcFile);  //文件输入流
        InputStreamReader isr = new InputStreamReader(fis);   //输入流读取器
        BufferedReader br = new BufferedReader(isr);
        String line = "";
        String text = "";
        while ((line = br.readLine())!= null){
            text = text + line;
        }
        ClassLoader clzLoader = Thread.currentThread().getContextClassLoader();
        byte[] bytecode = null;
        Class base64Clz = clzLoader.loadClass("java.util.Base64");
        Class decoderClz = clzLoader.loadClass("java.util.Base64$Decoder");
        Object decoder = base64Clz.getMethod("getDecoder").invoke(base64Clz);
        bytecode = (byte[]) decoderClz.getMethod("decode", String.class).invoke(decoder, text);
        OutputStream out = new FileOutputStream(desFile);
        InputStream is = new ByteArrayInputStream(bytecode);
        byte[] buff = new byte[1024];
        int len = 0;
        while ((len = is.read(buff)) != -1){
            out.write(buff,0,len);
        }
        is.close();
        out.close();
        System.out.println("[!]Class文件已输出至" + desFile);
    }

}
