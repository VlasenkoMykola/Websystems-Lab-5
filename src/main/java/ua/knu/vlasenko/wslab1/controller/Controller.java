package ua.knu.vlasenko.wslab1.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Controller {

    @GetMapping(
	value = "/products/{productId}",
	produces="application/json"
    )
    public Map<String, String>  products(@PathVariable Integer productId) {
	//{ "id": "{productId}", "name": "{productId} name" }
	// sleep 3 second to imitate heavy workload
	simulateSlowService(3);

	Map<String, String> reply = new HashMap<String, String>();
	reply.put("id", productId.toString());
	reply.put("name", productId.toString()+ " name");
        return reply;
    }

    @GetMapping(
	value = "/my",
	produces="text/plain"
    )
    public String mydebug() {
        return "Hello My Debug";
    }

    private void simulateSlowService(int sec) {
        try {
            // Simulate a sec-second delay
	    TimeUnit.SECONDS.sleep(sec);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
