package com.yjh.study.ch2高并发工具类.forkJoin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class FindDirFiles extends RecursiveAction {

    private File file;

    public FindDirFiles(File file) {
        this.file = file;
    }

    @Override
    protected void compute() {

        List<FindDirFiles> subTasks = new ArrayList<>();
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files){
                if(f.isDirectory()){
                    subTasks.add(new FindDirFiles(f));
                }else {
//                    遇到文件，检查
                    if(f.getAbsolutePath().endsWith(".docx")){
                        System.out.println(f.getAbsolutePath());
                    }
                }
            }
            if (!subTasks.isEmpty()){
                invokeAll(subTasks);
//                这里不调用join方法好像也可以完成功能，但是老师的代码里调用了
//                for (FindDirFiles fdf: invokeAll(subTasks)){
//                    fdf.join();
//                }

            }
        }

    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        FindDirFiles findDirFiles = new FindDirFiles(new File("C:\\Users\\yjh\\Desktop\\高并发"));
        pool.execute(findDirFiles);

        findDirFiles.join();
        System.out.println("执行完毕");
    }
}
