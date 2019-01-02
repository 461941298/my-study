package com.yjh.study.server.impl;

import com.yjh.study.server.api.Say;

public class SayImpl implements Say {
    @Override
    public String hello(String param) {
        return "Hello " + param;
    }
}
