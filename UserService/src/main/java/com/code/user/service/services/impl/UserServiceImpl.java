package com.code.user.service.services.impl;

import com.code.user.service.entities.Hotel;
import com.code.user.service.entities.Rating;
import com.code.user.service.entities.User;
import com.code.user.service.exceptions.ResourcsNotFoundException;
import com.code.user.service.external.services.HotelService;
import com.code.user.service.repositories.UserRepository;
import com.code.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository ;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);

        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {


        User user = userRepository.findById(userId).orElseThrow(()-> new ResourcsNotFoundException("User with given Id is not found on Server : "+userId));

       Rating [] ratingsObj = restTemplate.getForObject("http://localhost:8083/ratings/users/" + user.getUserId(), Rating[].class);


       logger.info("{}",ratingsObj);
       List<Rating>  ratingsOfUser= Arrays.stream(ratingsObj).collect(Collectors.toList());

      List<Rating> ratingObjectList  = ratingsOfUser.stream().map(rating -> {

          //  ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://localhost:8082/hotels/"+rating.getHotelId(), Hotel.class);

          Hotel hotel = hotelService.getHotel(rating.getHotelId());
         // Hotel hotel=  forEntity.getBody();
                rating.setHotel(hotel);
                return rating;

                }

        ).collect(Collectors.toList());


        user.setRatings(ratingObjectList);

        return user;
    }
}
