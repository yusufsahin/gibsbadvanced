package tr.gov.gib.taskman.service;

import tr.gov.gib.taskman.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto findByUsername(String username);
    UserDto save(UserDto userDto);

    List<UserDto> getAllUsers();
    void deleteById(Long id);

    UserDto update(UserDto userDto);
    UserDto addRoleByRoleId(Long id,Long roleId);

    UserDto addRoleByRoleName(Long id,String roleName);
}
