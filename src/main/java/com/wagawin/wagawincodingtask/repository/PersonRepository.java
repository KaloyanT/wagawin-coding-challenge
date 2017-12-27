package com.wagawin.wagawincodingtask.repository;

import com.wagawin.wagawincodingtask.model.Person;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long> {

    @Cacheable(value = "personCache")
    Iterable<Person> findAll();

    @Cacheable(value = "personCache")
    Person findByPersonId(final long personId);

    @CacheEvict(value = "personCache", allEntries = true)
    <S extends Person> S save(S house);

    @CacheEvict(value = "personCache", allEntries = true)
    void delete(final long personId);

    @Query("SELECT p.personId FROM Person p")
    List<Long> getPersonIds();

    @Query(value = "SELECT COUNT(*) FROM (SELECT COUNT(*) alias FROM child c " +
            "GROUP BY c.person_person_id HAVING COUNT(c.person_person_id) = :childrenCount) alias2;", nativeQuery = true)
    Long countNumberOfPersonsHavingNChildren(@Param("childrenCount") final long childrenCount);
}
