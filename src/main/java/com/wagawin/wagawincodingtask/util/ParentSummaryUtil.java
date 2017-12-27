package com.wagawin.wagawincodingtask.util;

import com.wagawin.wagawincodingtask.model.ParentSummary;
import com.wagawin.wagawincodingtask.repository.ParentSummaryRepository;
import com.wagawin.wagawincodingtask.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class ParentSummaryUtil {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ParentSummaryRepository parentSummaryRepository;

    @PostConstruct
    private void init() {
        // If the ParentSummary table is empty, initialize it. That means, if the
        // table has no entries, create them. Every entry in the table is a given
        // number of children a person can have. The entries start at 0 and go to some
        // reasonable maximal value. Since there isn't one specified, we will take 10.
        Set<ParentSummary> parentSummaries = parentSummaryRepository.getAll();

        if(parentSummaries == null || parentSummaries.isEmpty()) {
            for(long i = 0; i <= 10; i++) {
                ParentSummary temp = new ParentSummary();
                temp.setAmountOfChildren(i);
                temp.setAmountOfPersons(0L);
                parentSummaryRepository.save(temp);
            }
        }

        // Update the ParentSummary data. This can be skipped, since the data gets updated every
        // 15 Minutes
        // updateParentSummary();
    }

    @Scheduled(cron = "0 0/15 * * * ?")
    @Async
    public void updateParentSummary() {

        for(long childrenCount = 0; childrenCount <= parentSummaryRepository.getMaxAmountOfChildren(); childrenCount++) {
            long personCount = personRepository.countNumberOfPersonsHavingNChildren(childrenCount);
            ParentSummary prs = parentSummaryRepository.findByAmountOfChildren(childrenCount);
            prs.setAmountOfPersons(personCount);
            parentSummaryRepository.save(prs);
        }
    }

}
