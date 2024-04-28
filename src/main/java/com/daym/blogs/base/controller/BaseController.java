package com.daym.blogs.base.controller;

import cn.hutool.core.util.ClassUtil;
import cn.zhxu.bs.BeanSearcher;
import cn.zhxu.bs.SearchResult;
import cn.zhxu.bs.util.MapUtils;
import com.baomidou.mybatisplus.extension.service.IService;
import com.daym.blogs.util.DemoUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.ParameterizedType;
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

    @Resource
    protected BeanSearcher beanSearcher;
    @Resource
    protected HttpServletRequest request;

    /**
     * 查询 单条记录
     * @param id
     * @return
     */
    @GetMapping("/getOne")
    @ResponseBody
    public T getOne(String id){
        return baseService.getById(id);
    }

    /**
     * 保存
     * @param t
     * @return
     */
    @PostMapping("/save")
    @ResponseBody
    public boolean save(T t){
        boolean saveFlag = baseService.save(t);
        return saveFlag;
    }

    /**
     * 移除id
     * @param id
     * @return
     */
    @PostMapping("/remove")
    @ResponseBody
    public boolean removeById(String id){
        boolean removeFlag = baseService.removeById(id);
        return removeFlag;
    }

    /**
     * 移除多个id
     * @param ids
     * @return
     */
    @PostMapping("/removeBatch")
    @ResponseBody
    public boolean removeById(List<String> ids){
        boolean removeFlag = baseService.removeByIds(ids);
        return removeFlag;
    }

    /**
     *  request中的参数 作为查询条件
     * @return
     */
    @PostMapping("/bs/list")
    @ResponseBody
    public SearchResult<T> beanSearchList(){

          return beanSearcher.search(DemoUtil.getTClass(this), MapUtils.flat(request.getParameterMap()));

    }

    /**
     * 查询所有
     * @return
     */
    @PostMapping("/bs/list/all")
    @ResponseBody
    public List<T> beanSearchListAll(){

        return beanSearcher.searchAll(DemoUtil.getTClass(this), MapUtils.flat(request.getParameterMap()));

    }



}
