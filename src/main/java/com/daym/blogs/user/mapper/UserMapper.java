package com.daym.blogs.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.daym.blogs.user.entity.SystemUsers;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 *
 * @author:  
 * Date: 2024/4/9
 * Time: 11:24
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Mapper
public interface UserMapper extends BaseMapper<SystemUsers> {
}
