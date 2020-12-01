package org.example;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author GuJun
 * @date 2020/12/1
 */
public class 同步加载 {
    private static final LoadingCache<String, Object> cache = Caffeine
            .newBuilder()
            .expireAfterWrite(Duration.ofMillis(1000))
            .build(new CacheLoader<String, Object>() {
                /**
                 * 单个 key 的值加载
                 * @param s
                 * @return
                 * @throws Exception
                 */
                @Override
                public @Nullable Object load(@NonNull String s) throws Exception {
                    return s + System.currentTimeMillis();
                }

                /**
                 * 如果没有重写 loadAll 方法则默认的 loadAll 回循环调用 load 方法，一般重写优化性能
                 * @param keys
                 * @return
                 * @throws Exception
                 */
                @Override
                public @NonNull Map<String, Object> loadAll(@NonNull Iterable<? extends String> keys) throws Exception {
                    Map<String, Object> ret = new HashMap<>();
                    for (String key : keys) {
                        ret.put(key, key+System.currentTimeMillis());
                    }
                    return ret;
                }
            });

    public static void main(String[] args) {

    }
}
