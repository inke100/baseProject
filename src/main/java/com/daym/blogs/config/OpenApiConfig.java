package com.daym.blogs.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: mayongchang
 * Date: 2024/4/9
 * Time: 17:38
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()

                .info(new Info()
                        .title("文档标题")
                        .description("文档描述")
                        .contact(new Contact().name("作者").email("邮箱").url("可以写你的博客地址或不填"))
                        .version("v3.0"));
    }
  /*  @Bean
    public GlobalResponseOperationCustomizer globalResponseOperationCustomizer() {
        return new GlobalResponseOperationCustomizer();
    }*/


}
