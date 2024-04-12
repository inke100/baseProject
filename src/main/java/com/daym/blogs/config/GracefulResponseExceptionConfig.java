package com.daym.blogs.config;

import com.daym.blogs.exceptions.AliasDemoException;
import com.daym.blogs.exceptions.NoResourceFoundException;
import com.daym.blogs.exceptions.NotFoundException;
import com.feiniaojin.gracefulresponse.AbstractExceptionAliasRegisterConfig;
import com.feiniaojin.gracefulresponse.ExceptionAliasRegister;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: mayongchang
 * Date: 2024/4/9
 * Time: 10:56
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Configuration
public class GracefulResponseExceptionConfig extends AbstractExceptionAliasRegisterConfig {
    @Override
    protected void registerAlias(ExceptionAliasRegister aliasRegister) {

        aliasRegister.doRegisterExceptionAlias(NotFoundException.class);
        aliasRegister.doRegisterExceptionAlias(NoResourceFoundException.class);
        aliasRegister.doRegisterExceptionAlias(AliasDemoException.class);
    }

}
