package com.ams.jpa.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManagerFactory;
import java.util.Objects;

@RestController
@RequestMapping("/v1/maintenance")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Administration Services", description = "A controller for maintaining the service")
public class MaintenanceController {
    private final CacheManager cacheManager;
    private final EntityManagerFactory entityManagerFactory;

    @Operation(summary = "Evicts all caches")
    @ApiResponse(responseCode = "202", description = "caches evicted")
    @GetMapping("/clearCaches")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void clearAllCaches() {
        // this just clears the Spring Cache not the hibernate 2nd level cache!!
        cacheManager.getCacheNames().forEach(cacheName -> Objects.requireNonNull(cacheManager.getCache(cacheName)).clear());
        // clear entity cache
        entityManagerFactory.getCache().unwrap(org.hibernate.Cache.class).evictAllRegions();
    }
}