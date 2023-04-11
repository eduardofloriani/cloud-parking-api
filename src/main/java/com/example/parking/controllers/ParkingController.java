package com.example.parking.controllers;

import com.example.parking.dtos.ParkingCreateDto;
import com.example.parking.dtos.ParkingDto;
import com.example.parking.mappers.ParkingMapper;
import com.example.parking.model.ParkingModel;
import com.example.parking.services.ParkingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
@Tag(name = "Parking Controller")
public class ParkingController {

    private final ParkingService parkingService;
    private final ParkingMapper parkingMapper;

    public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
        this.parkingService = parkingService;
        this.parkingMapper = parkingMapper;
    }

    @GetMapping
    @Operation(summary = "Find all parkings.")
    public ResponseEntity<List<ParkingDto>> findAll() {
        List<ParkingModel> parkingList = parkingService.findAll();
        return ResponseEntity.ok(parkingMapper.toParkingDtoList(parkingList));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find a parking lot by id.")
    public ResponseEntity<ParkingDto> findById(@PathVariable String id) {
        ParkingModel parking = parkingService.findById(id);
        return ResponseEntity.ok(parkingMapper.toParkingDto(parking));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a parking lot by id.")
    public ResponseEntity delete(@PathVariable String id) {
        parkingService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @Operation(summary = "Create")
    public ResponseEntity<ParkingDto> create(@RequestBody ParkingCreateDto dto) {
        var parkingCreate = parkingMapper.toParkingCreate(dto);
        ParkingModel parking = parkingService.create(parkingCreate);
        ParkingDto result = parkingMapper.toParkingDto(parking);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update")
    public ResponseEntity<ParkingDto> update(@PathVariable String id, @RequestBody ParkingCreateDto dto) {
        var parkingCreate = parkingMapper.toParkingCreate(dto);
        ParkingModel parking = parkingService.update(id, parkingCreate);
        ParkingDto result = parkingMapper.toParkingDto(parking);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/{id}")
    @Operation(summary = "Leave the parking lot and calculate bill")
    public ResponseEntity<ParkingDto> checkOut(@PathVariable String id) {
        ParkingModel parking = parkingService.checkOut(id);
        return ResponseEntity.ok(parkingMapper.toParkingDto(parking));
    }

}
