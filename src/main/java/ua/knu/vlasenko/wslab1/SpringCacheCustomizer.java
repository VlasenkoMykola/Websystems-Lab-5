package ua.knu.vlasenko.wslab1;

//import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import java.util.Arrays;


@Component
public class SpringCacheCustomizer implements CacheManagerCustomizer<ConcurrentMapCacheManager> {

    @Override
    public void customize(ConcurrentMapCacheManager cacheManager) {
	String[] names = {"products"};
        cacheManager.setCacheNames(Arrays.asList(names));
    }
}
