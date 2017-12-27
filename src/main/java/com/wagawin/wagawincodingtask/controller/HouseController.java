package com.wagawin.wagawincodingtask.controller;

import com.wagawin.wagawincodingtask.model.House;
import com.wagawin.wagawincodingtask.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/house")
public class HouseController {

    @Autowired
    private HouseRepository houseRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getHouse(@RequestParam("houseId") long houseId) {

        if(houseId < 1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        /*
           Since we have OneToOne mapping with Person being the parent entity and House being
           the child entity, we can directly get the House with the personId, because personId
           is both PK and FK in the House Entity. By doing this we are executing only 1 query
           instead of 2. In order to further optimize performance, we are implementing some
           basic caching. Each Requested House will be cached and kept in memory until a
           House Object is inserted, modified or deleted
        */
        House house = houseRepository.findByHouseId(houseId);

        if(house == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(house, HttpStatus.OK);
    }
}
