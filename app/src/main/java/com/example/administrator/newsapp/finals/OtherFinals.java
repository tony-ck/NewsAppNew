package com.example.administrator.newsapp.finals;

import android.os.Environment;

/**
 * 其他常量类，不好分类的，放在这里面
 */

public class OtherFinals {
    //访问的数据源的地址
    public static final String JSON_URL="http://thoughtworks-ios.herokuapp.com/facts.json";
    // --------------应用缓存文件基本信息-----------------------
    public static final String PATH_SD = Environment
            .getExternalStorageDirectory().getPath() + "/Empty/";
    public static final String DIR_IMG = PATH_SD + "image/";
    public static final String DIR_CACHE = PATH_SD + "cache/";
}
