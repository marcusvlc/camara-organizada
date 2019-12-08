package com.camara.organizada.mesh;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mesh")
public class MeshService {

	private long lastAccess = System.currentTimeMillis();
	private int countAccess;
	private int THRESHOLD = 10000;
	
	@GetMapping("/health")
	public HttpStatus healthCheck() {
		long now = System.currentTimeMillis();
		if (now - lastAccess < THRESHOLD && countAccess < 5) {
			countAccess = countAccess + 1;
			System.out.println(countAccess);
			lastAccess = now;
		} else if (now - lastAccess > THRESHOLD ) {
			countAccess = 0;
			lastAccess = now;
		}
		
		System.out.println(lastAccess);
		if (countAccess >= 5) {
			return HttpStatus.BANDWIDTH_LIMIT_EXCEEDED;
		} else {
			return HttpStatus.ACCEPTED;
			
		}

	}

}
