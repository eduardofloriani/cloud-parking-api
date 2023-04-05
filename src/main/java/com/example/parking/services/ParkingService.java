package com.example.parking.services;

import com.example.parking.model.ParkingModel;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    private static Map<String, ParkingModel> parkingModelMap = new HashMap<>();

    static {
        var id = getUUID();
        ParkingModel parkingModel = new ParkingModel(id, "DMS-1111", "SC", "CELTA", "PRETO");
        parkingModelMap.put(id, parkingModel);
    }

    public List<ParkingModel> findAll() {
        return new ArrayList<>(parkingModelMap.values());
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
