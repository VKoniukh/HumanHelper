package ua.helper.humanhelper.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ua.helper.humanhelper.model.Home;
import ua.helper.humanhelper.model.dto.HomeDto;

@Mapper
public interface EntityMapper {

    EntityMapper INSTANCE = Mappers.getMapper(EntityMapper.class);

    HomeDto HomeToHomeDto(Home home);

    Home HomeDtoToHome(HomeDto homeDto);
}
