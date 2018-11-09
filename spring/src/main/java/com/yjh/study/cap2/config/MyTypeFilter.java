package com.yjh.study.cap2.config;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

public class MyTypeFilter implements TypeFilter {

    /**
     * @param metadataReader        读取到当前正在扫描类的信息
     * @param metadataReaderFactory 可以获取到其他任何类信息
     * @return
     * @throws IOException
     */
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        // 获取当前注解类信息
        final AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();

        // 获取当前扫描类信息
        final ClassMetadata classMetadata = metadataReader.getClassMetadata();

        // 获取当前类资源(类的路径)
        final Resource resource = metadataReader.getResource();

        final String className = classMetadata.getClassName();
        System.out.println("---->" + className);

        if (className.contains("er")){ // 当类路径包含"er"时，则匹配成功，返回true
            return true;
        }
        return false;
    }
}
