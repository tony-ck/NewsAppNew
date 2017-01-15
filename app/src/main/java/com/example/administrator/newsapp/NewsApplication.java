package com.example.administrator.newsapp;

import android.app.Application;
import android.content.Context;
import com.example.administrator.newsapp.finals.OtherFinals;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.File;

/**
 *
 * 程序入口，进行必要的初始化操作
 * Created by Administrator on 2017/1/14.
 */

public class NewsApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader(getApplicationContext());
        makeDirects();
    }

    /**
     * 一次创建程序所需要的文件夹
     */
    private void makeDirects() {
        File img = new File(OtherFinals.DIR_IMG);
        if (!img.exists()) {
            img.mkdirs();
        }

        img = new File(OtherFinals.DIR_CACHE);
        if (!img.exists()) {
            img.mkdirs();
        }
    }
    /**
     * init ImageLoader
     *
     * @param context
     */
    public static void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you
        // may tune some of them,
        // or you can create default configuration by
        // ImageLoaderConfiguration.createDefault(this);
        // method.
//		File cacheDir = new File(Environment.getExternalStorageDirectory()+File.separator+"xinzhoulvye");
        File cacheDir = new File(OtherFinals.DIR_CACHE);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
                .diskCache(new UnlimitedDiscCache(cacheDir))
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)// Not
                // necessary
                // in
                // common
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }
}
