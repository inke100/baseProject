package com.daym.blogs.user.controller;

import com.daym.blogs.constant.CommonConstant;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.function.BiFunction;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Date: 2024/4/16
 * Time: 9:41
 * To change this template use File | Settings | File Templates.
 * Description:
 */

@RestController("/redis")
public class RedisController {
     @Resource
    private RedisTemplate<String, String> redisTemplate;
    @PostMapping("/add")
    @ResponseBody
    public String addData(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
        return CommonConstant.SUCCESS_MSG;

    }
    @GetMapping("/get")
    @ResponseBody
    public Object getData(String key) {
        String value = redisTemplate.opsForValue().get(key);
        return value;
    }

    @PostMapping("/update")
    @ResponseBody
    public void updateData(String key, String newValue) {
        redisTemplate.opsForValue().set(key, newValue);
    }
    @PostMapping("/delete")
    @ResponseBody
    public void deleteData(String key) {
        redisTemplate.delete(key);
    }


    public Set<String> listAllCacheKeys() {
        return redisTemplate.keys("*");
    }
    @PostMapping("/list")
    @ResponseBody
    public Set<String> printAllCacheContents() {
       return listAllCacheKeys();
    }
    @PostMapping("/bigKey")
    @ResponseBody
    public HashMap<String, List<String>> getBigKey() {
        HashMap<String, List<String>> bigKey = new HashMap<>();
        bigKey.put("String", getKeysByCondition((redisTemplate, key) -> redisTemplate.opsForValue().size(key) > 5 * 1024 * 1024));
        bigKey.put("list", getKeysByCondition((redisTemplate, key) -> redisTemplate.opsForList().size(key) > 20000));
        bigKey.put("zset", getKeysByCondition((redisTemplate, key) -> redisTemplate.opsForZSet().size(key) > 10000));
        bigKey.put("hash", getKeysByCondition((redisTemplate, key) -> redisTemplate.opsForHash().values(key).stream()
                .mapToLong(value -> value.toString().getBytes().length).sum() > 100 * 1024 * 1024));
        return bigKey;
    }

    private List<String> getKeysByCondition(BiFunction<RedisTemplate<String, String>, String, Boolean> condition) {
        List<String> result = new ArrayList<>();
        Set<String> keys = redisTemplate.keys("*");
        for (String key : keys) {
            if (condition.apply(redisTemplate, key)) {
                result.add(key);
            }
        }
        return result;
    }

      
}
