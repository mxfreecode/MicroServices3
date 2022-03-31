package com.user.app.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.user.app.model.Booking;

@FeignClient(name = "booking", url = "http://localhost:9094")
public interface BookingClient {
	
	@GetMapping("/booking/{userId}")
	List<Booking> getBookingsByUserId(@PathVariable("userId") Integer userId);
}
