package com.katyusha.aron.library.utils;

import android.content.Context;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.manager.RequestManagerRetriever;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.katyusha.aron.library.BaseApplication;

import java.io.File;
import java.util.concurrent.ExecutionException;

/**
 * Created by jixiaolong on 2018/1/22.
 */

public class GlideLoader {

    private static final String HTTP = "http";
    private static final String FILE = "file://";
    private static File imageFile;

    /**
     * 普通加载(默认不带placeHolder)
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadImage(Context context, String url, ImageView imageView) {
        if (context == null || url == null) return;
        requestBuilder(context, url).into(imageView);

    }

    /**
     * 带占位图
     * @param context
     * @param url
     * @param imageView
     * @param placeHolderRes
     */
    public static void loadWithPlaceHolder(Context context, String url, ImageView imageView, @DrawableRes int placeHolderRes ) {
        if (context == null || url == null) return;
        RequestOptions options = new RequestOptions().placeholder(placeHolderRes);
        requestBuilder(context, url, options).into(imageView);
    }

    /**
     * 加载指定尺寸的图片
     * @param context
     * @param url
     * @param imageView
     * @param width
     * @param height
     */
    public static void loadWithSize(Context context, String url, ImageView imageView, int width, int height) {
        if (context == null || url == null) return;
        RequestOptions options = new RequestOptions().override(width, height);
        requestBuilder(context, url, options).into(imageView);
    }

    /**
     * 加载本地图片(不带placeHolder)
     * @param context
     * @param path
     * @param imageView
     */
    public static void loadLocalImage(Context context, String path, ImageView imageView) {
        if (context == null || path == null) return;
        requestBuilder(context,FILE + path).into(imageView);
    }

    /**
     * 加载本地图片(带placeHolder)
     * @param context
     * @param path
     * @param imageView
     * @param placeHolder
     */
    public static void loadLocalWithPlaceHolder(Context context, String path, ImageView imageView, int placeHolder) {
        if (context == null || path == null) return;
        RequestOptions options = new RequestOptions().placeholder(placeHolder);
        requestBuilder(context, FILE + path, options).into(imageView);
    }

    /**
     * 加载圆形图片
     * @param context
     * @param url
     * @param imageView
     * @param placeHolder
     */
    public static void loadCircleImage(Context context, String url, ImageView imageView, int placeHolder) {
        if (context == null || url == null) return;
        RequestOptions options = new RequestOptions().placeholder(placeHolder).circleCrop();
        requestBuilder(context, url, options).into(imageView);
    }

    /**
     * 下载图片
     * @param url
     */
    public static void downloadImage(final String url) {
        if (!url.startsWith(HTTP)) {
            return;
        }
        final Context context = BaseApplication.getAppContext();
        new Thread(new Runnable() {
            @Override
            public void run() {
                FutureTarget<File> target = Glide.with(context).asFile().load(url).submit();
                try {
                    imageFile = target.get();//get()方法会阻塞线程，因此要在子线程中
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Glide下载的图片路径
     * @return
     */
    public static String getDownloadPath() {
        return imageFile.getPath();
    }

    /**
     * @param context
     * @param url
     * @return
     */
    private static RequestBuilder<Drawable> requestBuilder(Context context, String url) {
        RequestManager manager = Glide.with(context);
        return manager.load(url).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                return false;
            }
        });
    }

    /**
     * @param context
     * @param url
     * @param options
     * @return
     */
    private static RequestBuilder<Drawable> requestBuilder(Context context, String url, RequestOptions options) {
        RequestManager manager = Glide.with(context);
        return manager.load(url).apply(options).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                return false;
            }
        });
    }

}
