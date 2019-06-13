/*
 * 文件名：HelloServiceImpl.java
 * 版权：Copyright 2007-2016 zxiaofan.com. Co. Ltd. All Rights Reserved. 
 * 描述： HelloServiceImpl.java
 * 修改人：yunhai
 * 修改时间：2016年3月25日
 * 修改内容：新增
 */
package com.ztwu.demo.service;

import com.ztwu.demo.thrift.HelloWorldService;

/**
 * 服务端实现类
 * 
 * @author ztwu2
 */
public class HelloServiceImpl implements HelloWorldService.Iface {

    public String sayHello(String username) {
        return "hello " + username;
    }

    public String getRandom() {
        return "random";
    }

}
