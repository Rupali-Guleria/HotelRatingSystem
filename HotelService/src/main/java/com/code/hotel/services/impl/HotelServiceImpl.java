package com.code.hotel.services.impl;

import com.code.hotel.entities.Hotel;
import com.code.hotel.repositories.HotelRepository;
import com.code.hotel.services.HotelService;
import com.code.hotel.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel create(Hotel hotel) {
        String hotelid= UUID.randomUUID().toString();
        hotel.setId(hotelid);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel get(String id) {
        return hotelRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("hotel with given id is not found"));
    }
}
