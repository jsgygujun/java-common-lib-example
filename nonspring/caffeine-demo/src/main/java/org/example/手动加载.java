package org.example;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.time.Duration;

/**
 * @author GuJun
 * @date 2020/12/1
 */
public class 手动加载 {
    private static final Cache<String, Object> cache = Caffeine.newBuilder()
            .expireAfterWrite(Duration.ofMillis(2000)) // 基于时间失效->写入之后开始计时失效
            .maximumSize(1000) // 最大缓存容量
            .build();

    public static void main(String[] args) throws Exception {
        // 读取缓存，读不到则返回null
        System.out.println(cache.getIfPresent("key")); // null

        // 读缓存，读不到则调用func接口获取，获得的数据自动假如到缓存中
        System.out.println(cache.get("key", k -> k + System.currentTimeMillis()));

        // 休眠秒，是缓存失效
        Thread.sleep(2000); // 使缓存失效

        // 以Map形式读取所有缓存
        System.out.println(cache.asMap()); // {key=key1606812983615}

        // 删除缓存
        cache.invalidate("key"); // 删除缓存
    }
}
