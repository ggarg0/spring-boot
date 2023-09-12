package com.cache.demo.app.configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.cache.CacheProperties.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CaffeineCacheConfig {


	  @Bean
	  public Caffeine caffeineConfig() {
	    return Caffeine.newBuilder()
	        .expireAfterWrite(300, TimeUnit.SECONDS)
	        .initialCapacity(10);
	  }

	  @Bean
	  public CacheManager cacheManager(Caffeine caffeine) {
	    CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
	    caffeineCacheManager.setCaffeine(caffeine);
	    return caffeineCacheManager;
	  }
}