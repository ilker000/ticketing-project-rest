package com.spring.mapper;

import com.spring.dto.UserDTO;
import com.spring.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public User convertToEntity(UserDTO dto) {
        return modelMapper.map(dto, User.class);
    }

    public UserDTO convertToDto(User entity) {

        return modelMapper.map(entity, UserDTO.class);
    }
}
