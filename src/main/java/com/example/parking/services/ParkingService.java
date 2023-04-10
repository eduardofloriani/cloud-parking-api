package com.example.parking.services;

import com.example.parking.dtos.ParkingCreateDto;
import com.example.parking.exceptions.ParkingNotFoundException;
import com.example.parking.model.ParkingModel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ParkingService {

    private static Map<String, ParkingModel> parkingModelMap = new HashMap<>();

    public List<ParkingModel> findAll() {
        return new ArrayList<>(parkingModelMap.values());
    }

    public ParkingModel findById(String id) {
        ParkingModel parking = parkingModelMap.get(id);
        if (parking == null) {
            throw new ParkingNotFoundException(id);
        }
        return parking;
    }

    public ParkingModel create(ParkingModel parkingCreate) {
        String uuid = getUUID();
        parkingCreate.setId(uuid);
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingModelMap.put(uuid, parkingCreate);
        return parkingCreate;
    }

    public ParkingModel update(String id, ParkingModel parkingModel) {
        ParkingModel parking = findById(id);
        parking.setColor(parkingModel.getColor());
        parkingModelMap.replace(id, parkingModel);
        return parking;
    }

    public void delete(String id) {
        findById(id);
        parkingModelMap.remove(id);
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
