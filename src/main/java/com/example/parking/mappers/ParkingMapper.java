package com.example.parking.mappers;

import com.example.parking.dtos.ParkingDto;
import com.example.parking.model.ParkingModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ParkingMapper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public ParkingDto parkingDto(ParkingModel parkingModel) {
        return MODEL_MAPPER.map(parkingModel, ParkingDto.class);
    }

    public List<ParkingDto> toParkingDtoList(List<ParkingModel> parkingModelList) {
        return parkingModelList.stream().map(this::parkingDto).collect(Collectors.toList());
    }

}
