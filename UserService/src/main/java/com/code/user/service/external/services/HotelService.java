package com.code.user.service.external.services;


import com.code.user.service.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name= "HOTEL-SERVICE:8088")
public interface HotelService {

    @GetMapping("/hotels/{hotelId}")
    Hotel getHotel(@PathVariable("hotelId") String hotelId );

}
