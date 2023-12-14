package tr.gov.gib.taskman.service;

import tr.gov.gib.taskman.dto.PrivilegeDto;

import java.util.List;

public interface PrivilegeService {
    PrivilegeDto save(PrivilegeDto privilegeDto);

    PrivilegeDto getById(Long id);

    List<PrivilegeDto> getPrivileges();


    void delete(Long id);

    PrivilegeDto update(PrivilegeDto privilegeDto);
}
