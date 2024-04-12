package com.daym.blogs.advice;

import com.feiniaojin.gracefulresponse.GracefulResponseProperties;
import com.feiniaojin.gracefulresponse.api.ExcludeFromGracefulResponse;
import com.feiniaojin.gracefulresponse.api.ResponseFactory;
import com.feiniaojin.gracefulresponse.data.Response;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: mayongchang
 * Date: 2024/4/9
 * Time: 9:32
 * To change this template use File | Settings | File Templates.
 * Description:对String 的返回调整
 */
//@ControllerAdvice
@Order(value = 1000)
public class StringResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    private final Logger logger = LoggerFactory.getLogger(StringResponseBodyAdvice.class);

    @Resource
    private ResponseFactory responseFactory;
    @Resource
    private GracefulResponseProperties properties;

    /**
     * 路径过滤器
     */
    private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();

    /**
     * 只处理不返回void的，并且MappingJackson2HttpMessageConverter支持的类型.
     *
     * @param methodParameter 方法参数
     * @param clazz           处理器
     * @return 是否支持
     */
    @Override
    public boolean supports(MethodParameter methodParameter,
                            Class<? extends HttpMessageConverter<?>> clazz) {
        Method method = methodParameter.getMethod();

        //method为空、返回值为void、非JSON，直接跳过
        if (Objects.isNull(method)
                || method.getReturnType().equals(Void.TYPE)
                || !StringHttpMessageConverter.class.isAssignableFrom(clazz)) {
            logger.debug("Graceful Response:method为空、返回值为void、非String，跳过");
            return false;
        }

        //有ExcludeFromGracefulResponse注解修饰的，也跳过
        if (method.isAnnotationPresent(ExcludeFromGracefulResponse.class)) {
            if (logger.isDebugEnabled()) {
                logger.debug("Graceful Response:方法被@ExcludeFromGracefulResponse注解修饰，跳过:methodName={}", method.getName());
            }
            return false;
        }

        //配置了例外包路径，则该路径下的controller都不再处理
        List<String> excludePackages = properties.getExcludePackages();
        if (!CollectionUtils.isEmpty(excludePackages)) {
            // 获取请求所在类的的包名
            String packageName = method.getDeclaringClass().getPackage().getName();
            if (excludePackages.stream().anyMatch(item -> ANT_PATH_MATCHER.match(item, packageName))) {
                logger.debug("Graceful Response:匹配到excludePackages例外配置，跳过:packageName={},", packageName);
                return false;
            }
        }
        logger.debug("Graceful Response:非空返回值，需要进行封装");
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> clazz,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        if (body == null) {
            return responseFactory.newSuccessInstance();
        } else if (body instanceof Response) {
            return body;
        } else {
            return responseFactory.newSuccessInstance(body);
        }
    }

}
