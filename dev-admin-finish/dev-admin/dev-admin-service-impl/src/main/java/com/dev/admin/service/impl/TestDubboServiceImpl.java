package com.dev.admin.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dev.admin.service.TestDubboService;

/**
 * 类描述
 * Created by yuboon on 2019/03/25
 */
@Service
public class TestDubboServiceImpl implements TestDubboService {

    @Override
    public String sayHello(String str) {
        return str + "123";
    }
}
