package com.daym.blogs.base.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Date: 2024/4/17
 * Time: 17:51
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Slf4j
@Component
public abstract class BaseController<T> {
    @Resource
    protected IService<T> baseService;
    @GetMapping("/getOne")
    @ResponseBody
    public T getOne(String id){
        return baseService.getById(id);
    }
    @PostMapping("/save")
    @ResponseBody
    public boolean save(T t){
        boolean saveFlag = baseService.save(t);
        return saveFlag;
    }
    @PostMapping("/remove")
    @ResponseBody
    public boolean removeById(String id){
        boolean removeFlag = baseService.removeById(id);
        return removeFlag;
    }
    @PostMapping("/removeBatch")
    @ResponseBody
    public boolean removeById(List<String> ids){
        boolean removeFlag = baseService.removeByIds(ids);
        return removeFlag;
    }
}
