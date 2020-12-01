package org.example;

import com.github.benmanes.caffeine.cache.AsyncCacheLoader;
import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * @author GuJun
 * @date 2020/12/1
 */
public class 异步加载 {
    private static AsyncLoadingCache<String, Object> cache = Caffeine
            .newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(Duration.ofMillis(1000))
            .buildAsync(new AsyncCacheLoader<String, Object>() {
                @Override
                public @NonNull CompletableFuture<Object> asyncLoad(@NonNull String key, @NonNull Executor executor) {
                    return null;
                }

                @Override
                public @NonNull CompletableFuture<Map<String, Object>> asyncLoadAll(@NonNull Iterable<? extends String> keys, @NonNull Executor executor) {
                    return null;
                }
            });

    public static void main(String[] args) {

    }
}
