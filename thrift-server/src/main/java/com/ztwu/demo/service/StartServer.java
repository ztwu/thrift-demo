/*
 * 文件名：StartServer.java
 * 版权：Copyright 2007-2016 zxiaofan.com. Co. Ltd. All Rights Reserved. 
 * 描述： StartServer.java
 * 修改人：yunhai
 * 修改时间：2016年3月25日
 * 修改内容：新增
 */
package com.ztwu.demo.service;

import com.ztwu.demo.thrift.HelloWorldService;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadPoolServer.Args;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * 启动服务
 * 
 * @author ztwu2
 */
public class StartServer {
    /**
     * 启动Thrift服务器
     */
    public void startServer() {

        try {
            // 定义传输的socket，设置服务端口为6789
            TServerSocket serverTransport = new TServerSocket(1234);

            // 设置协议工厂为 TBinaryProtocol.Factory
            Factory proFactory = new Factory(true, true);

            // 关联处理器与 Hello服务的实现
//            HelloWorldService.Processor processor = new HelloWorldService.Processor(new HelloServiceImpl());

            //服务器支持多接口
            TMultiplexedProcessor processor = new TMultiplexedProcessor();
            processor.registerProcessor("test",new HelloWorldService.Processor(new HelloServiceImpl()) );

            // 定义服务端的参数值
            Args args = new Args(serverTransport);
            args.processor(processor);
            args.protocolFactory(proFactory);
            TServer server = new TThreadPoolServer(args);

            // 服务端开启服务s
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("Server Start!");
        StartServer server = new StartServer();
        server.startServer();
    }
}
