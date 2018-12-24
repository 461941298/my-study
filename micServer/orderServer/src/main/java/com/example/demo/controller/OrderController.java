package com.example.demo.controller;

import com.example.demo.pojo.Order;
import com.example.demo.pojo.Product;
import com.example.demo.utils.LoadBalance;
import com.example.demo.utils.RamdomLoadBalance;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private RestTemplate restTemplate;

    private LoadBalance loadBalance = new RamdomLoadBalance();

    @RequestMapping("/getOrder/{id}")
    public Object getOrder(@PathVariable("id") String id ) {
        Product product = restTemplate.getForObject("http://"+loadBalance.choseServiceHost()+"/product/getProduct/1", Product.class);
        return new Order(id,"orderName",product);
    }
}
