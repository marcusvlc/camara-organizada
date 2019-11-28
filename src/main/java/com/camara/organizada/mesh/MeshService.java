package com.camara.organizada.mesh;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mesh")
public class MeshService {

	@GetMapping("/health")
	public String healthCheck() {
		return "such";
	}

}
