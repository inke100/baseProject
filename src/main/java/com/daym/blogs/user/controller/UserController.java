package com.daym.blogs.user.controller;

import com.daym.blogs.base.controller.BaseController;
import com.daym.blogs.user.entity.SystemUsers;
import com.daym.blogs.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author:  
 * Date: 2024/4/2
 * Time: 11:11
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Tag(
        name = "用户"
)
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController extends BaseController<SystemUsers>{

    @Resource
    private UserService userService;
    @Operation(summary = "demo")
    //用户的增删改查 及修改密码
    @GetMapping
    @ResponseBody
    public Map<String, String> getUser(){
        return
                Collections.singletonMap("key","111");
    }
    @GetMapping("/")
    @ResponseBody
    public String getUser1(){
        return "12345";
    }
    @GetMapping("/boolean")
    @ResponseBody
    public boolean getTrue(){
        return true;
    }
    @GetMapping("/boolean2")
    @ResponseBody
    public Boolean getTrue2(){
        return true;
    }
    @GetMapping("/sys")
    @ResponseBody
    public SystemUsers getSysUser(@RequestParam("id") Integer id){
        return userService.getById(id);
    }
    @GetMapping("/sys/list")
    @ResponseBody
    public List<SystemUsers> getSysUserList(){
        return userService.list();
    }
}
