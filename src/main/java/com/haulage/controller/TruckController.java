package com.haulage.controller;

import com.haulage.domain.Truck;
import com.haulage.dto.TruckCreateRequest;
import com.haulage.dto.TruckResponse;
import com.haulage.dto.TruckUpdateRequest;
import com.haulage.service.TruckService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trucks")
public class TruckController {

    private final TruckService truckService;

    public TruckController(TruckService truckService) {
        this.truckService = truckService;
    }

    @PostMapping
    public ResponseEntity<TruckResponse> create(@Valid @RequestBody TruckCreateRequest request) {
        Truck truck = truckService.createTruck(request.getRegistrationNumber(), request.getCapacity());
        if (request.getStatus() != null) {
            truck = truckService.setTruckStatus(truck.getTruckId(), request.getStatus());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(truck));
    }

    @GetMapping("/{id}")
    public TruckResponse getById(@PathVariable("id") Long id) {
        return toResponse(truckService.getTruck(id));
    }

    @GetMapping
    public List<TruckResponse> getAll() {
        return truckService.getAllTrucks().stream().map(this::toResponse).toList();
    }

    @PutMapping("/{id}")
    public TruckResponse update(@PathVariable("id") Long id, @Valid @RequestBody TruckUpdateRequest request) {
        Truck truck = truckService.updateTruck(id, request.getRegistrationNumber(), request.getCapacity());
        return toResponse(truck);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        truckService.deleteTruck(id);
    }

    private TruckResponse toResponse(Truck truck) {
        return new TruckResponse(
                truck.getTruckId(),
                truck.getRegistrationNumber(),
                truck.getCapacity(),
                truck.getStatus());
    }
}
