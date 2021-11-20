package co.guiromao.libraryapp.converters;

import co.guiromao.libraryapp.dto.LendObjectDto;
import co.guiromao.libraryapp.models.LendObject;
import co.guiromao.libraryapp.utils.ModelMapperClass;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class LendObjectConverter {

    private static ModelMapper modelMapper = ModelMapperClass.getInstance();

    public static LendObject dtoToLendObject(LendObjectDto dto) {
        return modelMapper.map(dto, LendObject.class);
    }

    public static LendObjectDto lendObjectToDto(LendObject lendObject) {
        return modelMapper.map(lendObject, LendObjectDto.class);
    }

}
