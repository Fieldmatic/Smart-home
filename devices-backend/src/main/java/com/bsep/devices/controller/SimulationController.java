package com.bsep.devices.controller;

import com.bsep.devices.service.SetAttackDeviceState;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/simulation")
@RequiredArgsConstructor
public class SimulationController {
    private final SetAttackDeviceState setAttackDeviceState;

    @PutMapping("/{id}")
    public boolean setAttackDeviceState(@PathVariable UUID id) {
        return setAttackDeviceState.execute(id);
    }
}
