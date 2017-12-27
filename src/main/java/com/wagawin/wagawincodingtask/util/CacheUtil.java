package com.wagawin.wagawincodingtask.util;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CacheUtil {

    /**
     * This method simply cleans the Cache at a specified time interval.
     * Since it is specified in part 1 of the task that the /house endpoint
     * gets 70% of all API calls, the houseCache can be kept for longer.
     * Currently all of the cache gets cleaned every hour
     */
    @Scheduled(cron = "0 0 0/1 * * ?")
    @Caching(evict = {
            @CacheEvict(value = "personCache", allEntries = true),
            @CacheEvict(value = "childCache", allEntries = true),
            @CacheEvict(value = "houseCache", allEntries = true)
    })
    public void cleanCache() {
    }
}
