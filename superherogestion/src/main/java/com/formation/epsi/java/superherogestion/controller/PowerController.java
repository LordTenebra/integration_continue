package com.formation.epsi.java.superherogestion.controller;

import com.formation.epsi.java.superherogestion.DTO.PowerDTO;
import com.formation.epsi.java.superherogestion.model.Power;
import com.formation.epsi.java.superherogestion.repository.PowerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(
        path = "/power",
        produces = {MediaType.APPLICATION_JSON_VALUE}
)
@RequiredArgsConstructor
public class PowerController {

    private final PowerRepository powerRepository;

    @GetMapping(path = "{id}")
    public ResponseEntity<PowerDTO> getById(@PathVariable Long id) {
        return this.powerRepository.findById(id)
                .map(power -> ResponseEntity.ok(mapToDTO(power)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<PowerDTO>> getAll() {
        return ResponseEntity.ok(
                this.powerRepository
                        .findAll()
                        .stream()
                        .map(this::mapToDTO)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<PowerDTO> create(@RequestBody PowerDTO powerDTO) {
        powerDTO.setId(0);
        Power power = mapToEntity(powerDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapToDTO(this.powerRepository.save(power)));
    }

    @PutMapping(path = "{id}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<PowerDTO> update(
            @PathVariable Long id,
            @RequestBody PowerDTO powerDTO
    ) {
        if (this.powerRepository.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if (id != powerDTO.getId()) {
            return ResponseEntity.badRequest().build();
        }

        Power powerToUpdate = mapToEntity(powerDTO);
        return ResponseEntity.ok(mapToDTO(this.powerRepository.save(powerToUpdate)));
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        this.powerRepository.deleteById(id);
    }

    private Power mapToEntity(PowerDTO powerDTO) {
        Power power = new Power();
        power.setId(powerDTO.getId());
        power.setName(powerDTO.getName());
        power.setDescription(powerDTO.getDescription());
        return power;
    }

    private PowerDTO mapToDTO(Power power) {
        return PowerDTO.builder()
                .id(power.getId())
                .name(power.getName())
                .description(power.getDescription())
                .build();
    }
}
