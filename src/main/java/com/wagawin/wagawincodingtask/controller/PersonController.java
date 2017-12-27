package com.wagawin.wagawincodingtask.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.wagawin.wagawincodingtask.repository.ParentSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private ParentSummaryRepository parentSummaryRepository;
    
    @RequestMapping(value = "/persons/children", method = RequestMethod.GET)
    public ResponseEntity<?> getPersonsHavingNChildrenArray() {

        // The indices of this list will be the number of children and the elements at a given
        // index will be the number of parents having that many children. For example a value of 3
        // at index 0 will mean that 3 Persons don't have a child. As long as the entries from ParentSummary
        // are populated correctly, i.e. there are no missing entries (indices), and nothing gets deleted,
        // this will work fine. Since the data gets refreshed every 15 Minutes, don't make a request to refresh it
        List<Long> parentsHavingNChildrenList = parentSummaryRepository.getParentsHavingNChildrenList();

        if(parentsHavingNChildrenList == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode response = mapper.createObjectNode();
        ArrayNode listAsArray = mapper.valueToTree(parentsHavingNChildrenList);

        response.putArray("personHavingNChildrenArray").addAll(listAsArray);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
