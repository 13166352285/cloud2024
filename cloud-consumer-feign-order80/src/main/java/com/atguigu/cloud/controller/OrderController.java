package com.atguigu.cloud.controller;

import cn.hutool.core.date.DateUtil;
import com.atguigu.cloud.apis.PayFeignApi;
import com.atguigu.cloud.entities.PayDto;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.resp.ReturnCodeEnum;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * @Author chentian
 * @Date 2024/4/25
 */
@RestController
public class OrderController {

    @Resource
    PayFeignApi payFeignApi;

    @PostMapping(value = "/feign/pay/add")
    public ResultData addOrder(@RequestBody PayDto payDto) {
        System.out.println("第一步：模拟本地addOrder新增订单成功(省略sql操作)，第二步：再开启addPay支付微服务远程调用");

        ResultData resultData = payFeignApi.addPay(payDto);
        return resultData;
    }

    @GetMapping(value = "/feign/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id){
        ResultData payInfo= null;
        try{
            System.out.println("调用开始----：" + DateUtil.now());
            payInfo = payFeignApi.getPayInfo(id);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("调用结束----：" + DateUtil.now());
            return ResultData.fail(ReturnCodeEnum.RC500.getCode(),e.getMessage());
        }

        return payInfo;
    }

    @GetMapping(value = "/feign/pay/mylb")
    public String mylb(){
        return payFeignApi.mlb();
    }
}
