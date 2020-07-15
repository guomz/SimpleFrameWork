package com.guomz.simpleframework.util;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * 根据包名获取class字节码文件从而加载类
 */
public class ClassUtil {

    /**
     * 根据包名提取改路径下的所有类
     * @param packageName
     * @return
     */
    public static Set<Class<?>> extractPackageClass(String packageName) {
        Set<Class<?>> classSet = null;
        //根据类加载器获得包名所在的物理路径
        ClassLoader classLoader = getClassLoader();
        URL packagePath = classLoader.getResource(packageName.replace(".", "/"));
        //获取对应的文件引用
        File packageFile = null;
        try {
            packageFile = new File(packagePath.getPath());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("获取包名的文件引用失败");
        }
        //当前路径为文件夹则进行class文件提取
        if (packageFile.isDirectory()){
            classSet = new HashSet<>();
            extractClassFile(packageFile, classSet, packageName);
        }
        return classSet;
    }

    /**
     * 递归遍历包路径将class文件转换为类装载到set中
     * @param packageFile
     * @param classSet
     * @param packageName
     */
    private static void extractClassFile(File packageFile, Set<Class<?>> classSet, String packageName){
        File files[] = packageFile.listFiles(new FileFilter() {
            //为listFile方法设置过滤条件
            //只列出文件夹，对于字节码class文件则直接加入set集合
            @Override
            public boolean accept(File path) {
                if (path.isDirectory()){
                    return true;
                }else {
                    if (path.getAbsolutePath().endsWith(".class")){
                        classSet.add(getClassFile(path, packageName));
                    }
                }
                return false;
            }
        });

        if (files == null){
            throw new RuntimeException("获取子文件失败");
        }

        for (File file: files){
            extractClassFile(file, classSet, packageName);
        }
    }

    /**
     * 根据路径获得class对象
     * @param path
     * @param packageName
     * @return
     */
    private static Class<?> getClassFile(File path, String packageName) {
        String abFilePath = path.getAbsolutePath();
        abFilePath = abFilePath.replace(File.separator, ".");
        String fullClassPath = abFilePath.substring(abFilePath.indexOf(packageName), abFilePath.lastIndexOf(".class"));
        try {
            return Class.forName(fullClassPath);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("类加载失败");
        }
    }


    /**
     * 获取当前线程的类加载器
     * @return
     */
    private static ClassLoader getClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }

    public static void main(String[] args) {
        extractPackageClass("com.guomz.simpleframework.entity");
    }
}
