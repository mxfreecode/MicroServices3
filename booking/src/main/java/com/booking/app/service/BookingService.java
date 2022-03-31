package com.booking.app.service;

import java.beans.Beans;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.app.client.TrainClient;
import com.booking.app.client.UserClient;
import com.booking.app.dto.BookingRequestDTO;
import com.booking.app.entity.Booking;
import com.booking.app.model.Train;
import com.booking.app.model.User;
import com.booking.app.repository.BookingRepo;

@Service
public class BookingService {
	
	@Autowired
	BookingRepo bookingRepo;
	
	@Autowired
	UserClient userClient;
	
	@Autowired
	TrainClient trainClient;
	
	public List<Booking> getBookings(){
		return bookingRepo.findAll();
	}
	
	public List<Booking> getBookingByUserId(Integer userId) {
		return bookingRepo.findByUserId(userId);
	}
	
	public void saveBooking(BookingRequestDTO bookingRequestDTO) {
		Booking booking = new Booking();
		BeanUtils.copyProperties(bookingRequestDTO, booking);
		bookingRepo.save(booking);
	}
	
	public List<User> getUsers(){
		return userClient.getUser();
	}
	
	public Optional<User> getUserByUserId(Integer userId){
		return userClient.getUserByUserId(userId);
	}
	
	public List<Train> getTrains(){
		return trainClient.getTrains();
	}
	
	public List<Train> getTrainsPerParams(String source, String destination, LocalDate date) {
		return trainClient.getTrainsPerParams(source, destination, date);
	}
	
	public Optional<Train> getTrainByTrainId(Integer trainId){
		return trainClient.getTrainByTrainId(trainId);
	}
}
