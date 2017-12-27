package com.wagawin.wagawincodingtask.repository;

import com.wagawin.wagawincodingtask.model.Child;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

public interface ChildRepository extends CrudRepository<Child, Long> {

    @Cacheable(value = "childCache")
    Iterable<Child> findAll();

    @Cacheable(value = "childCache")
    Child findByChildId(final long childId);

    @CacheEvict(value = "childCache", allEntries = true)
    <S extends Child> S save(S house);

    @CacheEvict(value = "childCache", allEntries = true)
    void delete(final long personId);

    // A bit of a long name, but generates exactly the query that we need
    Long countChildrenByPersonPersonIdAndChildIdIsNotNull(final long personId);
}
