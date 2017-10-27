package com.katyusha.aron.library.utils;

import android.content.Context;
import android.support.v4.util.LruCache;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class BhCacheUtils {
    private final static String Per = "_bh_cache_";
    static class InnerObject implements Serializable {
        long timestamp;
        Object obj;
        InnerObject(Object obj){
            this.obj = obj;
            timestamp = System.currentTimeMillis();
        }
    }

    final static class InnerCache{
        final static int MAX_SIZE = 100;
        static LruCache<String,InnerObject> cache = new LruCache<String,InnerObject>(MAX_SIZE){
            @Override
            protected void entryRemoved(boolean evicted, String key, InnerObject oldValue, InnerObject newValue) {
                super.entryRemoved(evicted, key, oldValue, newValue);
                if(evicted && oldValue != null){
                    oldValue = null;
                }
            }

            @Override
            protected int sizeOf(String key, InnerObject value) {
                return 1;
            }
        };

        static void put(String key, InnerObject obj){
            cache.put(key,obj);
        }

        static InnerObject get(String key){
            return cache.get(key);
        }

        static void delete(String key){
            cache.remove(key);
        }

        static void clear(){
            cache.evictAll();
        }
    }

    public static <T extends Object> void saveObject(Context context, String Key, T obj){
        try {
            String key = Per + CryptoUtils.genMD5Str(Key);
            FileOutputStream fos = context.openFileOutput(key, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            InnerObject inner = new InnerObject(obj);
            InnerCache.put(key,inner);

            oos.writeObject(inner);
            fos.close();
        }catch (Exception ex){
        }
    }

    public static <T extends Object> T getObject(Context context,String key){
        return getObject(context, key, Integer.MAX_VALUE/10000);
    }

    public static <T extends Object> T getObject(Context context, String key, int second){
        T result = null;
        try{
            key = Per + CryptoUtils.genMD5Str(key);
            InnerObject obj = InnerCache.get(key);
            boolean isDisk = false;
            if(obj==null) {
                isDisk = true;
                FileInputStream fis = context.openFileInput(key);
                ObjectInputStream ois = new ObjectInputStream(fis);
                obj = (InnerObject) ois.readObject();
            }
            if(obj!=null) {
                if (System.currentTimeMillis() - obj.timestamp > second * 1000) {
                    if(isDisk){
                        deleteObject(context, key);
                    }else {
                        InnerCache.delete(key);
                    }
                }else {
                    if(isDisk){
                        InnerCache.put(key,obj);
                    }
                    result = (T)obj.obj;
                }
            }
        }catch (Exception ex){
        }
        return result;
    }

    public static void clearMemoryCache(){
        InnerCache.clear();
    }

    private static void deleteObject(Context context, String key){
        context.deleteFile(key);
    }
}
