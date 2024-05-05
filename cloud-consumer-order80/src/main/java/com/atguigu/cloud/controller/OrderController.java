package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.PayDto;
import com.atguigu.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Description
 * @Author chentian
 * @Date 2024/4/25
 */
@RestController
public class OrderController {

//    public static final String payment_url = "http://localhost:8001";
    public static final String payment_url = "http://cloud-payment-service";//服务注册中心上的微服务名称

    @Resource
    RestTemplate restTemplate;

    @GetMapping(value = "/consumer/pay/add")
    public ResultData addOrder(PayDto payDto) {

        return restTemplate.postForObject(payment_url + "/pay/add", payDto, ResultData.class);
    }

    @GetMapping(value = "/consumer/pay/del/{id}")
    public ResultData del(@PathVariable("id") Integer id) {

        restTemplate.delete(payment_url + "/pay/del/" + id, id, ResultData.class);
        return ResultData.success(null);
    }

    @GetMapping(value = "/consumer/pay/update")
    public ResultData upadte(PayDto payDto) {

        restTemplate.put(payment_url + "/pay/update", payDto, ResultData.class);
        return ResultData.success(null);
    }

    @GetMapping(value = "/consumer/pay/get/{id}")
    public ResultData getById(@PathVariable("id") Integer id) {

        return restTemplate.getForObject(payment_url + "/pay/get/" + id, ResultData.class, id);
    }

    @GetMapping(value = "/consumer/pay/get/info")
    public String getInfoByConsul(){
        return restTemplate.getForObject(payment_url + "/pay/get/info" , String.class);
    }

    @Resource
    private DiscoveryClient discoveryClient;
    @GetMapping("/consumer/discovery")
    public String discovery()
    {
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            System.out.println(element);
        }

        System.out.println("===================================");

        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance element : instances) {
            System.out.println(element.getServiceId()+"\t"+element.getHost()+"\t"+element.getPort()+"\t"+element.getUri());
        }

        return instances.get(0).getServiceId()+":"+instances.get(0).getPort();
    }
}
