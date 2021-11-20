package co.guiromao.libraryapp.utils;

import org.modelmapper.ModelMapper;

public class ModelMapperClass {

    private static ModelMapper modelMapper;

    public static ModelMapper getInstance() {
        if (modelMapper != null) {
            return modelMapper;
        }

        modelMapper = new ModelMapper();

        return modelMapper;
    }
}
