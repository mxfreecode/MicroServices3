package com.booking.app.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booking.app.dto.BookingRequestDTO;
import com.booking.app.entity.Booking;
import com.booking.app.model.Train;
import com.booking.app.model.User;
import com.booking.app.service.BookingService;

import feign.Body;

@RestController
@RequestMapping("/booking")
public class BookingController {

	@Autowired
	BookingService bookingService;
	
	@GetMapping("/{userId}")
	public List<Booking> getBookingsByUserId(@PathVariable("userId") Integer userId){
		return bookingService.getBookingByUserId(userId);
	}
	
	@GetMapping("/bookings")
	public List<Booking> getBooking(){
		return bookingService.getBookings();
	}
	
	@GetMapping("/users")
	public List<User> getUser(){
		return bookingService.getUsers();
	}
	
	@GetMapping("/trains")
	public List<Train> getTrains(){
		return bookingService.getTrains();
	}

	@GetMapping("/journeys")
	public List<Train> getTrainsPerParams(@RequestParam("source") String source, @RequestParam("destination") String destination, @RequestParam("date") String date){
		LocalDate localDate = LocalDate.parse(date);
		return bookingService.getTrainsPerParams(source, destination, localDate);
	}
	
	@GetMapping("/trains/{trainId}")
	public Optional<Train> getTrainsByTrainId(@PathVariable("trainId") Integer trainId){
		return bookingService.getTrainByTrainId(trainId);
	}
	
	@GetMapping("/user/{userId}")
	public Optional<User> getUserByUserId(@PathVariable("userId") Integer userId){
		return bookingService.getUserByUserId(userId);
	}
	
	@PostMapping("/savebooking")
	public ResponseEntity<String> saveBooking(@RequestBody BookingRequestDTO bookingRequestDTO) {
		Optional<User> optionalUser = bookingService.getUserByUserId(bookingRequestDTO.getUserId());
		Optional<Train> optionalTrain = bookingService.getTrainByTrainId(bookingRequestDTO.getTrainId());
		if(!optionalUser.isPresent())
			return new ResponseEntity<String>("User not found", HttpStatus.ACCEPTED);
		if(!optionalTrain.isPresent())
			return new ResponseEntity<String>("Train not found", HttpStatus.ACCEPTED);
		bookingService.saveBooking(bookingRequestDTO);
		return new ResponseEntity<String>("Booking Saved", HttpStatus.ACCEPTED);
	}
}
