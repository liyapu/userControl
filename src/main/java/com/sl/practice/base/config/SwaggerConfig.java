package com.sl.practice.base.config;

import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-23 19:19
 *
 * Swagger2配置类
 * 通过@Configuration注解，让Spring来加载该类配置
 * 再通过@EnableSwagger2注解来启用Swagger2
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.enable}")
    private boolean enableSwagger;


    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                //控制是否开启 swagger ,生产环境一般我们需要禁用
                .enable(enableSwagger)
                .apiInfo(apiInfo())
                .select()
                //扫描多个包
                .apis(Predicates.or(
                        RequestHandlerSelectors.basePackage("com.sl.practice.web.controller")
                ))
                .build()
                .useDefaultResponseMessages(false)
                .consumes(new HashSet<>(Arrays.asList("application/json")))
                .produces(new HashSet<>(Arrays.asList("application/json")));
    }

    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://项目实际地址/swagger-ui.html
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SpringFox API接口文档") // 标题
                .description("测试api接口文档") // 描述信息
                //通过YAPI 管理接口文档返回值备注的字段含义
                .termsOfServiceUrl("")  //服务网址
                .version("1.0.0")//版本号
                .build();

    }

}