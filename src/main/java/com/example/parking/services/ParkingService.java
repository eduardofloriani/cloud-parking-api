package com.example.parking.services;

import com.example.parking.Repository.ParkingRepository;
import com.example.parking.exceptions.ParkingNotFoundException;
import com.example.parking.model.ParkingModel;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ParkingService {

    private final ParkingRepository parkingRepository;

    public ParkingService(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<ParkingModel> findAll() {
        return parkingRepository.findAll();
    }

    @Transactional(readOnly = true)
    public ParkingModel findById(String id) {
        return parkingRepository.findById(id).orElseThrow(() ->
                new ParkingNotFoundException(id));
    }

    @Transactional
    public ParkingModel create(ParkingModel parkingCreate) {
        String uuid = getUUID();
        parkingCreate.setId(uuid);
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingRepository.save(parkingCreate);
        return parkingCreate;
    }

    @Transactional
    public ParkingModel update(String id, ParkingModel parkingModel) {
        ParkingModel parking = findById(id);
        parking.setColor(parkingModel.getColor());
        parking.setState(parkingModel.getState());
        parking.setModel(parkingModel.getModel());
        parking.setLicense(parkingModel.getLicense());
        parkingRepository.save(parking);
        return parking;
    }

    @Transactional
    public void delete(String id) {
        findById(id);
        parkingRepository.deleteById(id);
    }

    @Transactional
    public ParkingModel checkOut(String id) {
        ParkingModel parkingModel = findById(id);
        parkingModel.setExitDate(LocalDateTime.now());
        parkingModel.setBill(ParkingCheckOut.getBill(parkingModel));
        parkingRepository.save(parkingModel);
        return parkingModel;
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
