package com.camara.organizada.services;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CacheService {
	
    @Cacheable(cacheNames = "default")
    public String cacheThis(){
        System.out.println("Returning NOT from cache!");
        return "this Is it";
    }
   
}
