package com.daym.blogs.exceptions;

import com.feiniaojin.gracefulresponse.api.ExceptionAliasFor;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author:  
 * Date: 2024/4/9
 * Time: 10:47
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@ExceptionAliasFor(code = "1404", msg = "not found", aliasFor = NoHandlerFoundException.class)
public class NotFoundException extends RuntimeException {
}