# JspEncoder

### 0x00 开发日志

2020-11-16 V0.2 支持Jspx下HTML、CDATA实体编码

2020-11-6 V0.1 根据野外落地畸形unicode内存马判断编码方式，支持畸形unicode加解密

### 0x01 使用方法

Jsp文件Unicode解码：java -jar JspEncoder.jar UniDe srcFile desFile

Jsp文件Unicode编码：java -jar JspEncoder.jar UniEn srcFile desFile

Jspx文件Html解码：java -jar JspEncoder.jar HtmlDe srcFile desFile

Jspx文件Html编码：java -jar JspEncoder.jar HtmlEn srcFile desFile

Jspx文件CDATA解码：java -jar JspEncoder.jar CdataDe srcFile desFile

Jspx文件CDATA编码：java -jar JspEncoder.jar CdataEn srcFile desFile

Base64文件输出为class文件：java -jar JspEncoder.jar ClassOut srcFile desFile

class文件输出为Base64文件：java -jar JspEncoder.jar ClassIn srcFile desFile

使用参考：https://mp.weixin.qq.com/s/NKksirrM5Zg5BGCu4fY8LQ

### 0x02 注意事项

在进行Unicode编码时请去掉jsp文件的类声明和引入声明模块，否则jsp文件将无法运行
