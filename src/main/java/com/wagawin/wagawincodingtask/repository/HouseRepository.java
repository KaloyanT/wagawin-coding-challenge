package com.wagawin.wagawincodingtask.repository;

import com.wagawin.wagawincodingtask.model.House;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

public interface HouseRepository extends CrudRepository<House, Long> {

    @Cacheable(value = "houseCache")
    Iterable<House> findAll();

    @Cacheable(value = "houseCache")
    House findByHouseId(final long houseId);

    @CacheEvict(value = "houseCache", allEntries = true)
    <S extends House> S save(S house);

    @CacheEvict(value = "houseCache", allEntries = true)
    void delete(final long houseId);

}
