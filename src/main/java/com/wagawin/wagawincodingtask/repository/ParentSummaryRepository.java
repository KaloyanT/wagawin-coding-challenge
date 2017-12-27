package com.wagawin.wagawincodingtask.repository;

import com.wagawin.wagawincodingtask.model.ParentSummary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Set;

public interface ParentSummaryRepository extends CrudRepository<ParentSummary, Long> {

    @Query("SELECT prs FROM ParentSummary prs")
    Set<ParentSummary> getAll();

    ParentSummary findByAmountOfChildren(final long amountOfChildren);

    @Query("SELECT prs.amountOfPersons FROM ParentSummary prs")
    List<Long> getParentsHavingNChildrenList();

    @Query("SELECT MAX(prs.amountOfChildren) FROM ParentSummary prs")
    Long getMaxAmountOfChildren();
}
