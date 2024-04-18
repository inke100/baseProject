package com.daym.blogs.exceptions;

import com.feiniaojin.gracefulresponse.api.ExceptionAliasFor;

/**
 * Created with IntelliJ IDEA.
 *
 * @author:  
 * Date: 2024/4/9
 * Time: 10:50
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@ExceptionAliasFor(code = "1406",msg = "资源未找到",
        aliasFor = org.springframework.web.servlet.resource.NoResourceFoundException.class)
public class NoResourceFoundException extends RuntimeException {
}