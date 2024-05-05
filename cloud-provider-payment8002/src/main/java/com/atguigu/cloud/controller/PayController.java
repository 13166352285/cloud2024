package com.atguigu.cloud.controller;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.util.BeanUtils;
import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.entities.PayDto;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description
 * @Author chentian
 * @Date 2024/4/25
 */
@RestController
@Slf4j
@Tag(name = "支付微服务模块",description = "支付CRUD")
public class PayController {

    @Resource
    PayService payService;

    @PostMapping(value = "/pay/add")
    @Operation(summary = "新增",description = "新增支付流水方法,json串做参数")
    public ResultData<String> addPay(@RequestBody Pay pay) {
        int add = payService.add(pay);
        return ResultData.success("成功插入记录，返回值： " + add);
    }

    @DeleteMapping(value = "/pay/del/{id}")
    @Operation(summary = "删除",description = "删除支付流水方法")
    public ResultData<Integer> deleteById(@PathVariable("id") Integer id) {
        return ResultData.success(payService.delte(id));
    }

    @PutMapping(value = "/pay/update")
    @Operation(summary = "修改",description = "修改支付流水方法")
    public ResultData<String> updatePay(@RequestBody PayDto payDto) {
        Pay pay = new Pay();
        BeanUtil.copyProperties(payDto, pay);
        int update = payService.update(pay);
        return ResultData.success("成功修改记录，返回值： " + update);
    }

    @GetMapping(value = "/pay/get/{id}")
    @Operation(summary = "按照ID查流水",description = "查询支付流水方法")
    public ResultData<Pay> getById(@PathVariable("id") Integer id) {
        if (id==-4) throw new RuntimeException("id不能为负数");
        return ResultData.success(payService.getById(id));
    }

    @GetMapping(value = "pay/getAll")
    @Operation(summary = "查询所有支付数据",description = "查询所有支付数据")
    public ResultData<List<Pay>> getAll() {
        return ResultData.success(payService.getAll());
    }


    @Value("${server.port}")
    private String port;
    @GetMapping(value = "/pay/get/info")
    public String getInfo(@Value("${atguigu.info}") String atguiguInfo){
        return "atguiguInfo:" + atguiguInfo + "\t" + "port: " + port;
    }
}
