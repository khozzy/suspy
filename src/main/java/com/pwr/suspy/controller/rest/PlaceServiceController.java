package com.pwr.suspy.controller.rest;


import com.pwr.suspy.domain.Place;
import com.pwr.suspy.service.PlaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service/places")
public class PlaceServiceController {

    private static final Logger logger = LoggerFactory.getLogger(PlaceServiceController.class);

    private PlaceService placeService;

    @Autowired
    public PlaceServiceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @RequestMapping(method = RequestMethod.GET, headers = "accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    public Page<Place> getPlaces(
            @RequestParam(value = "query", defaultValue = "Suspy is the best") String query,
            @RequestParam(value = "pageNum", defaultValue = "0") Long pageNum,
            @RequestParam(value = "numOfResults", defaultValue = "10") Long numOfResults)
            {

        return placeService.findPlaces(
                query,
                new PageRequest(
                        pageNum.intValue(),
                        numOfResults.intValue(),
                        new Sort(Sort.Direction.ASC, "name")));
    }

    @RequestMapping(value = "/{placeID}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<Place> getPlace(
            @PathVariable("placeID") Long placeID){

        if (placeService.exists(placeID)) {
            return new ResponseEntity<>(placeService.findById(placeID), new HttpHeaders(), HttpStatus.OK);
        }

        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<String> createPlace(
            @RequestBody Place place) {

        place = placeService.createNewPlace(place);
        return new ResponseEntity<>("Place " + place.getName() + " created.", new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{placeID}", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> updatePlace(
            @PathVariable("placeID") Long placeID,
            @RequestBody Place place){

        //userService.update(userID, user);
        return new ResponseEntity<>("Place " + place.getName() + " updated.", new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{placeID}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deletePlace(
            @PathVariable("placeID") Long placeID)
            {

        placeService.delete(placeID);
        Place place = placeService.findById(placeID);
        return new ResponseEntity<>("Place " + place.getName() + " deleted.", new HttpHeaders(), HttpStatus.GONE);
    }

    @RequestMapping(value = "/{placeID}/changeAcceptState", method = RequestMethod.GET)
    public ResponseEntity<String> verifyPlace(
            @PathVariable("placeID") Long placeID) {

        Place place = placeService.findById(placeID);
        placeService.acceptPlace(place);
        return new ResponseEntity<>("Place" + place.getName() + " accepted.", new HttpHeaders(), HttpStatus.OK);
    }

}
