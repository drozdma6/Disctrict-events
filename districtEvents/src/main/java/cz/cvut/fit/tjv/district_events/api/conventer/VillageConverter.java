package cz.cvut.fit.tjv.district_events.api.conventer;

import cz.cvut.fit.tjv.district_events.api.controller.VillageDto;
import cz.cvut.fit.tjv.district_events.domain.Village;

import java.util.ArrayList;
import java.util.Collection;

public class VillageConverter {
    public static Village fromDto(VillageDto villageDto){
        return new Village(villageDto.getId(), villageDto.getName());
    }

    public static VillageDto toDto(Village village){
        return new VillageDto(village.getId(), village.getName());
    }

    public static Collection<Village> fromDtoMany(Collection<VillageDto> villageDtos) {
        Collection<Village> villages = new ArrayList<>();
        villageDtos.forEach((u) -> villages.add(fromDto(u)));
        return villages;
    }

    public static Collection<VillageDto> toDtoMany(Collection<Village> villages) {
        Collection<VillageDto> villageDtos = new ArrayList<>();
        villages.forEach((u) -> villageDtos.add(toDto(u)));
        return villageDtos;
    }
}
