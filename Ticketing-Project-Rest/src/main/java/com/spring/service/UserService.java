package com.spring.service;

import com.spring.dto.UserDTO;
import com.spring.entity.User;
import com.spring.exception.TicketingProjectException;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface UserService {

    List<UserDTO> listAllUsers();

    UserDTO findByUserName(String username) throws AccessDeniedException;

    UserDTO save(UserDTO dto) throws TicketingProjectException;

    UserDTO update(UserDTO dto) throws TicketingProjectException, AccessDeniedException;

    void delete(String username) throws TicketingProjectException;

    void deleteByUserName(String username);

    List<UserDTO> listAllByRole(String role);

    Boolean checkIfUserCanBeDeleted(User user);

    UserDTO confirm(User user);

}
