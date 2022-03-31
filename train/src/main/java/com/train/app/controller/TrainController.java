package com.train.app.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.train.app.entity.Train;
import com.train.app.service.TrainService;

@RestController
@RequestMapping("/train")
public class TrainController {
	@Autowired
	TrainService trainService;
	
	@GetMapping()
	public List<Train> getTrains(){
		return trainService.getTrains();
	}
	
	@PostMapping()
	public Train saveTrain(@RequestBody Train train) {
		return this.trainService.saveTrains(train);
	}

    @GetMapping( path = "/{trainId}")
    public Optional<Train> getTrainsById(@PathVariable("trainId") Integer trainId) {
        return this.trainService.getById(trainId);
    }
    
    @GetMapping("/query")
    public List<Train> getTrainsByDestination(@RequestParam("destination") String destination){
        return this.trainService.getByDestination(destination);
    } 
    
    @DeleteMapping( path = "/{trainId}")
    public String deleteById(@PathVariable("trainId") Integer trainId){
        boolean ok = this.trainService.deleteTrain(trainId);
        if (ok){
            return "Delete Train Successful id:" + trainId;
        }else{
            return "Cannot Deleted Train:" + trainId;
        }
    }  
    
    @GetMapping("/train/{trainId}")
    public Optional<Train> getTrainByTrainId(@PathVariable("trainId") Integer trainId){
    	return trainService.getTrainByTrainId(trainId);
    }
    
    @GetMapping("/journeys")
    public List<Train> getJourneys(@RequestParam("source") String source, @RequestParam("destination") String destination, @RequestParam("date") String date) {
    	LocalDate localDate = LocalDate.parse(date);
    	return trainService.getTrainsPerParams(source, destination, localDate);
    }
    
}
