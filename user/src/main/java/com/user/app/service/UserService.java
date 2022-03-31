package com.user.app.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.app.client.BookingClient;
import com.user.app.dto.LoginRequestDTO;
import com.user.app.dto.ResponseDTO;
import com.user.app.entity.User;
import com.user.app.model.Booking;
import com.user.app.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BookingClient bookingClient;
	
	public List<User> getUsers(){
		return (List<User>) userRepository.findAll();
	}
	
	public User saveUsers(User user) {
		return userRepository.save(user);
	}
	
	public Optional<User> getById(Integer userId){
		return userRepository.findById(userId);
	}
	
	
	public Optional<User> getByUsername(String username)
	{
		return userRepository.findByUsername(username);
	}
	
	public boolean deleteUser(Integer userId) {
		try {
			userRepository.deleteById(userId);
			return true;
		}catch(Exception err) {
			return false;
		}
	}
	
	public Optional<User> getUserByUserId(Integer userId){
		return userRepository.findByUserId(userId);
	}
	
	public ResponseDTO login(@Valid LoginRequestDTO loginRequestDTO) {
		Optional<User> optionalUser = userRepository.findByUsernameAndPassword(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
		ResponseDTO responseDTO = new ResponseDTO("", 0);
		
		if(!optionalUser.isPresent()){
			responseDTO.setMessage("Bad credentials");
			responseDTO.setStatusCode(404);
		}
		else{
			responseDTO.setMessage("User accepted");
			responseDTO.setStatusCode(200);
		}
		return responseDTO;
	}
	
	public List<Booking> getBookingsByUserId(Integer userId){
		return bookingClient.getBookingsByUserId(userId);
	}
}
