package com.daym.blogs.config;

import com.feiniaojin.gracefulresponse.GracefulResponse;
import com.feiniaojin.gracefulresponse.api.ResponseStatusFactory;
import com.feiniaojin.gracefulresponse.defaults.DefaultResponseFactory;
import io.swagger.v3.oas.models.Operation;
import jakarta.annotation.Resource;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.web.method.HandlerMethod;


/**
 * Created with IntelliJ IDEA.
 *
 * @author: mayongchang
 * Date: 2024/4/11
 * Time: 14:58
 * To change this template use File | Settings | File Templates.
 * Description:
 */

public class GlobalResponseOperationCustomizer implements OperationCustomizer {


    @Override
    public Operation customize(Operation operation, HandlerMethod handlerMethod) {
        Class<?> returnType = handlerMethod.getMethod().getReturnType();

        Class<?> wrapperType = GracefulResponse.class;
        String returnTypeSimpleName = returnType.getSimpleName();
        String wrapperTypeSimpleName = wrapperType.getSimpleName();

        if (returnType != wrapperType) {
            String schemaName = wrapperTypeSimpleName + returnTypeSimpleName;
            operation.getResponses().forEach((responseName, apiResponse) -> {
                if (!responseName.equals("200")) {
                    return;
                }
                if (apiResponse.getContent() == null) {
                    return;
                }
                apiResponse.getContent().forEach((contentName, mediaType) -> mediaType.getSchema().set$ref("#/components/schemas/" + schemaName));
            });
        }


        return operation;
    }
}
