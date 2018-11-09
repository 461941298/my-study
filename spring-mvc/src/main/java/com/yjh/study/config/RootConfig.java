package com.yjh.study.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * @author yjh
 * @discrption
 */
@ComponentScan(
        basePackages = "com.yjh.study",
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class})
        },
        useDefaultFilters = false
)
public class RootConfig {
}
