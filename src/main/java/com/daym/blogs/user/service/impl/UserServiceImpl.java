package com.daym.blogs.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daym.blogs.user.entity.SystemUsers;
import com.daym.blogs.user.mapper.UserMapper;
import com.daym.blogs.user.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @author:  
 * Date: 2024/4/9
 * Time: 13:27
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, SystemUsers> implements UserService {

}
