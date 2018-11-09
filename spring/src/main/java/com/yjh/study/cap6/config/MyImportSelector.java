package com.yjh.study.cap6.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        String[] beans = new String[]{
                "com.yjh.study.cap6.bean.Bird"

        };
        return beans;
    }
}
