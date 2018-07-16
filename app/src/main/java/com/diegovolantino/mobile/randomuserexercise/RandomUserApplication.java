package com.diegovolantino.mobile.randomuserexercise;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

/**
 * Created by Diego Pablo Volantino on 12/7/18.
 */

public class RandomUserApplication extends Application {

    private Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();

        appContext = this;

        // ImageLoader initialization
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisk(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .resetViewBeforeLoading(true).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext())
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .threadPoolSize(2)
                // 10 MB
                .memoryCacheSize(10 * 1024 * 1024)
                // .memoryCache(new WeakMemoryCache())
                .denyCacheImageMultipleSizesInMemory()
                .defaultDisplayImageOptions(defaultOptions)
                .imageDownloader(new BaseImageDownloader(appContext))
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs() // Not necessary in common
                .build();

        // init with config
        ImageLoader.getInstance().init(config);
        // clear cache
        ImageLoader.getInstance().clearDiskCache();
        ImageLoader.getInstance().clearMemoryCache();

        ImageLoader.getInstance().init(config);
    }
}