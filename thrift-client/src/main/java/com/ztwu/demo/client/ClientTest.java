/*
 * 文件名：ClientTest.java
 * 版权：Copyright 2007-2016 zxiaofan.com. Co. Ltd. All Rights Reserved. 
 * 描述： ClientTest.java
 * 修改人：yunhai
 * 修改时间：2016年3月25日
 * 修改内容：新增
 */
package com.ztwu.demo.client;

import com.ztwu.demo.thrift.HelloWorldService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * 客户端实现
 * 
 * @author ztwu2
 */
public class ClientTest {

    /**
     * 调用Hello服务
     */
    public void startClient() {

        try {

            // 设置调用的服务地址为本地，端口为6789
            TTransport transport = new TSocket("localhost", 1234);
            transport.open();

            // 数据传输协议有：二进制协议、压缩协议、JSON格式协议
            // 这里使用的是二进制协议
            // 协议要和服务端一致
            TProtocol protocol = new TBinaryProtocol(transport);
            // 单接口
//            HelloWorldService.Client client = new HelloWorldService.Client(protocol);
            // 多接口调用
            TMultiplexedProtocol mp1 = new TMultiplexedProtocol(protocol, "test");
            HelloWorldService.Client client = new HelloWorldService.Client(mp1);
            // 调用服务器端的服务方法
            System.out.println(client.sayHello("ztwu"));
            System.out.println(client.getRandom());

            // 关闭
            transport.close();
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ClientTest clientTest = new ClientTest();
        clientTest.startClient();
    }

}
