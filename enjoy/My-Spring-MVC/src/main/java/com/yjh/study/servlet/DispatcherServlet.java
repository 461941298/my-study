package com.yjh.study.servlet;

import com.yjh.study.annotation.Component;
import com.yjh.study.annotation.Qualifer;
import com.yjh.study.annotation.RequestMapping;
import com.yjh.study.handler.ParamResolverAdapter;
import com.yjh.study.tools.MyMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 * @author yjh
 * @discrption
 */
public class DispatcherServlet extends HttpServlet {

    //存放包扫瞄时获得的必要全类名
    private List<String> classNames = new ArrayList<>();
    //存放所有实例化的Bean，即IOC容器
    private Map<String, Object> beans = new HashMap<>();
    //存放url与方法映射, key为url， value为Method
    private Map<String, MyMethod> handlerMapping = new HashMap<>();
    //

    @Override
    public void init() throws ServletException {
        try {
            //1. 扫瞄指定的包，加载beanClass
            scanPackage("com.yjh.study");
            //2. 实现IOC容器
            doInstance();
            //3. 实现依赖注入
            doDI();
            //4. 实现url与方法的映射匹配
            urlMappingMethod();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("初始化窗口失败" + e.getMessage());
        }
    }

    private void urlMappingMethod() {
        //遍历beans
        for (Map.Entry<String, Object> entry : beans.entrySet()) {
            //拿到bean实例
            Object bean = entry.getValue();
            //拿到bean实例的Class
            Class<?> beanClass = bean.getClass();
            //定义类上的url映射
            String urlOnClass = null;
            if (beanClass.isAnnotationPresent(RequestMapping.class)) {
                RequestMapping requestMappingOnClass = beanClass.getAnnotation(RequestMapping.class);
                urlOnClass = requestMappingOnClass.value();
            }
            //拿到bean的所有方法
            Method[] methods = bean.getClass().getMethods();
            //遍历methods
            for (Method method : methods) {
                if (method.isAnnotationPresent(RequestMapping.class)) {
                    RequestMapping requestMappingOnMethod = method.getAnnotation(RequestMapping.class);
                    String urlOnMethod = requestMappingOnMethod.value();
                    String url = urlOnClass + urlOnMethod;
                    handlerMapping.put(url, new MyMethod(bean, method));
                }
            }

        }
    }


    /**
     * 把实例化后的对象做依赖注入
     */
    private void doDI() throws IllegalAccessException {
        //遍历beans
        for (Map.Entry<String, Object> entry : beans.entrySet()) {
            //拿到bean实例
            Object bean = entry.getValue();
            //拿到bean实例的属性
            Field[] fields = bean.getClass().getFields();
            //遍历bean属性
            for (Field field : fields) {
                //如果field被@Qualifer标注
                if (field.isAnnotationPresent(Qualifer.class)) {
                    //取得标注对象
                    Qualifer qualifer = field.getAnnotation(Qualifer.class);
                    //取得标注值
                    String value = qualifer.value();
                    //true表示禁止访问检查，为后面的属性赋值做准备
                    field.setAccessible(true);
                    //field赋值
                    field.set(bean, beans.get(value));
                }
            }
        }
    }

    /**
     * 将扫瞄到的类实现化，并加入到
     */
    private void doInstance() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //遍历包扫瞄后的classNames容器
        for (String className : classNames) {
            //通过全类得到Class对象
            Class<?> aClass = Class.forName(className);
            //判断class对象是否被@Component标注
            if (aClass.isAnnotationPresent(Component.class)) {
                //拿到注解对象
                Component component = aClass.getAnnotation(Component.class);
                //注解的值
                String value = component.value();
                //将Class对象实例化后存入beans容器
                beans.put(value, aClass.newInstance());
            }
        }
    }

    /**
     * 扫瞄指定的包，加载beanClass
     *
     * @param basePackage
     */
    private void scanPackage(String basePackage) throws ClassNotFoundException {
        //通过指定的包名找到相应的文件路径
        URL basePackageUrl = this.getClass().getResource(packageToPathName(basePackage));
        if (basePackageUrl == null) {
            throw new RuntimeException("不能找到包路径" + basePackage);
        }

        //通过url获取File对象
        String baseFilePath = basePackageUrl.getFile();
        File basePackageFile = new File(baseFilePath);

        //获取子文件名称
        String[] childrenNames = basePackageFile.list();

        for (String childName : childrenNames) {
            //子文件的路径
            String childPath = baseFilePath + childName;
            //子文件的包名称
            String childPackage = basePackage + "." + childName;
            //如果是文件夹则递归调用
            if (new File(childPath).isDirectory()) {
                scanPackage(childPackage);
            } else {
                //添加前先将.class后缀去掉
                classNames.add(childPackage.replace(".class", ""));
            }
        }
    }

    private String packageToPathName(String basePackage) {
        String result = "/" + basePackage.replaceAll("\\.", "/");
        return result;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //拿到uri
        String uri = req.getRequestURI();

        //找到处理url的方法
        MyMethod method = handlerMapping.get(uri);

        //解析请求参数
        ParamResolverAdapter paramResolverAdapter = (ParamResolverAdapter) beans.get("ParamResolverAdapter");
        Object[] args = paramResolverAdapter.resolver(req, resp, method.getMethod(), beans);

        //使用方法的invoke
        try {
            method.invoke(args);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
