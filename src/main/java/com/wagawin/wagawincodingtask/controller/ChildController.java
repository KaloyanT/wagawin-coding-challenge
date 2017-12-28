package com.wagawin.wagawincodingtask.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.wagawin.wagawincodingtask.model.Child;
import com.wagawin.wagawincodingtask.model.Daughter;
import com.wagawin.wagawincodingtask.model.Son;
import com.wagawin.wagawincodingtask.repository.ChildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChildController {

    @Autowired
    private ChildRepository childRepository;

    @RequestMapping(value = "/child/info", method = RequestMethod.GET)
    public ResponseEntity<?> getChildInfo(@RequestParam("childId") long childId) {

        if(childId < 1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Child child = childRepository.findByChildId(childId);

        if(child == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode response = mapper.createObjectNode();
        JsonNode parent = mapper.valueToTree(child.getPerson());
        JsonNode meal = null;

        if(child.getMeals().size() > 0) {
            // Get the first one which is also the most favourite one
            meal = mapper.valueToTree(child.getMeals().get(0));
        } else {
            meal = mapper.valueToTree(child.getMeals());
        }

        response.set("parent", parent);
        response.set("meal", meal);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/color", method = RequestMethod.GET)
    public ResponseEntity<?> getColor(@RequestParam("childId") long childId) {

        if(childId < 1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Child child = childRepository.findByChildId(childId);
        // Simulate an Eager Fetching in Order to avoid Hibernate Lazy Initialization Exception.
        // The Exception occurs because this endpoint only fetches the Child data without the Person
        // and Meal data. If the /child/info endpoint is called for the same childId which is already
        // cached, we get the Exception since the data for Person and Meal is missing. Alternatively
        // we could switch to Eager fetching or keep the session open, but this is good enough since
        // we are going to use the data in the other endpoint anyways
        child.getPerson().toString();
        child.getMeals().toString();

        if(child == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode response = mapper.createObjectNode();

        if(child instanceof Son) {
            response.put("bicycleColor", ((Son) child).getBicycleColor());
        } else if(child instanceof Daughter) {
            response.put("hairColor", ((Daughter) child).getHairColor());
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
