package com.example.parking.controllers;

import com.example.parking.dtos.ParkingCreateDto;
import com.example.parking.dtos.ParkingDto;
import com.example.parking.mappers.ParkingMapper;
import com.example.parking.model.ParkingModel;
import com.example.parking.services.ParkingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<ParkingDto>> findAll() {
        List<ParkingModel> parkingList = parkingService.findAll();
        return ResponseEntity.ok(parkingMapper.toParkingDtoList(parkingList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingDto> findById(@PathVariable String id) {
        ParkingModel parking = parkingService.findById(id);
        return ResponseEntity.ok(parkingMapper.toParkingDto(parking));
    }

    @PostMapping
    public ResponseEntity<ParkingDto> create(@RequestBody ParkingCreateDto dto) {
        var parkingCreate = parkingMapper.toParkingCreate(dto);
        ParkingModel parking = parkingService.create(parkingCreate);
        ParkingDto result = parkingMapper.toParkingDto(parking);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}
