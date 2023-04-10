package com.example.parking.services;

import com.example.parking.exceptions.ParkingNotFoundException;
import com.example.parking.model.ParkingModel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ParkingService {

    private static Map<String, ParkingModel> parkingModelMap = new HashMap<>();

    static {
        var id = getUUID();
        var id1 = getUUID();
        ParkingModel parkingModel = new ParkingModel(id, "DMS-1111", "SC", "CELTA", "PRETO");
        ParkingModel parkingModel1 = new ParkingModel(id1, "WAS-1234", "SP", "VW GOL", "VERMELHO");
        parkingModelMap.put(id, parkingModel);
        parkingModelMap.put(id1, parkingModel1);
    }

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

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
