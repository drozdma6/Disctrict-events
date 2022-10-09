package cz.cvut.fit.tjv.district_events.api.controller;

import cz.cvut.fit.tjv.district_events.api.conventer.VillageConverter;
import cz.cvut.fit.tjv.district_events.business.EntityNotFoundException;
import cz.cvut.fit.tjv.district_events.business.EntityStateException;
import cz.cvut.fit.tjv.district_events.business.VillageService;
import cz.cvut.fit.tjv.district_events.domain.Village;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@RestController
public class VillageController {
    private final VillageService villageService;

    VillageController(VillageService villageService) {
        this.villageService = villageService;
    }

    @GetMapping("/villages")
    Collection<VillageDto> all() {return VillageConverter.toDtoMany(villageService.readAll());
    }

    @PostMapping("/villages")
    VillageDto newVillage(@RequestBody VillageDto villageDto){
        if (villageDto.getName() == null) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Name is null");
        }
        Village village = VillageConverter.fromDto(villageDto);
        try {
            this.villageService.create(village);
        } catch (EntityStateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Duplicate id");
        }
        village = this.villageService.readById(village.getId()).orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Village Not Found")
        );
        return VillageConverter.toDto(village);
    }

    @PutMapping("/villages/{id}")
    VillageDto updateVillage(@RequestBody VillageDto villageDto, @PathVariable Long id){
        villageService.readById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Village with id not found"
                ));
        Village village = VillageConverter.fromDto(villageDto);
        try{
            this.villageService.update(village);
        } catch (EntityStateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Village ID is not unique");
        }
        return villageDto;
    }

    @DeleteMapping("villages/{id}")
    void deleteVillage(@PathVariable Long id){
        try{
            villageService.deleteById(id);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Village has been deleted");
        } catch (EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Village was not found");
        } catch (
                DataIntegrityViolationException e){ //unable delete village if some event is organized there
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                              "Events are organized in village");
        }
    }
}
