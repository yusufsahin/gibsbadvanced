package tr.gov.gib.taskman.service;

import tr.gov.gib.taskman.dto.RoleDto;

import java.util.List;

public interface RoleService {
    RoleDto save(RoleDto roleDto);

    RoleDto getById(Long id) throws Exception;

    List<RoleDto> getRoles();
    void delete(Long id);

    RoleDto update(RoleDto roleDto);

    RoleDto getByName(String name);


    RoleDto addPrivilegeByPrivilegeId(Long id, Long privilegeId);

    RoleDto addPrivilegeByPrivilegeName(Long id, String privilegeName);

}
