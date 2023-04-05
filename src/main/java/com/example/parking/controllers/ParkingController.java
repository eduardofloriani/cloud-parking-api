package com.example.parking.controllers;

import com.example.parking.dtos.ParkingDto;
import com.example.parking.mappers.ParkingMapper;
import com.example.parking.model.ParkingModel;
import com.example.parking.services.ParkingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    private final ParkingService parkingService;
    private final ParkingMapper parkingMapper;

    public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
        this.parkingService = parkingService;
        this.parkingMapper = parkingMapper;
    }

    @GetMapping
    public List<ParkingDto> findAll() {
        List<ParkingModel> parkingList = parkingService.findAll();
        return parkingMapper.toParkingDtoList(parkingList);
    }

}
