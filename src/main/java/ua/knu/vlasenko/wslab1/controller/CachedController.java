package ua.knu.vlasenko.wslab1.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CachedController {

    @GetMapping(
	value = "/cached/{productId}",
	produces="application/json"
    )

    @Cacheable("products")
    public Map<String, String>  products(@PathVariable Integer productId) {
	//{ "id": "{productId}", "name": "{productId} name" }
	// sleep 3 second to imitate heavy workload
	simulateSlowService(3);
	Map<String, String> reply = new HashMap<String, String>();
	reply.put("id", productId.toString());
	reply.put("name", productId.toString()+ " name");
        return reply;
    }

    private void simulateSlowService(int sec) {
        try {
            // Simulate a sec-second delay
	    TimeUnit.SECONDS.sleep(sec);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @CacheEvict(value = "products", allEntries = true)
    @Scheduled(fixedRateString = "${caching.spring.productsTTL}")
    public void emptyProductsCache() {
	System.err.println("CACHE EVICT: emptying Products cache");
    }

}
