package com.daym.blogs.exceptions;

import com.feiniaojin.gracefulresponse.api.ExceptionAliasFor;
import org.springframework.web.client.HttpClientErrorException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author:  
 * Date: 2024/4/9
 * Time: 10:48
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@ExceptionAliasFor(code = "1405",msg = "自定义异常信息",
        aliasFor = HttpClientErrorException.NotFound.class)
public class AliasDemoException extends RuntimeException{
}
