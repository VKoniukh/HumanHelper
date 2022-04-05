package ua.helper.humanhelper.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ua.helper.humanhelper.model.Home;
import ua.helper.humanhelper.model.dto.HomeDto;

@Mapper
public interface HomeMapper {

    HomeMapper INSTANCE = Mappers.getMapper(HomeMapper.class);

    HomeDto HomeToHomeDto(Home home);

    Home HomeDtoToHome(HomeDto homeDto);
}
