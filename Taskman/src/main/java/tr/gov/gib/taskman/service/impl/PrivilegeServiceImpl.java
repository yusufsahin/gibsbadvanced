package tr.gov.gib.taskman.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.gov.gib.taskman.dao.PrivilegeRepository;
import tr.gov.gib.taskman.dao.model.Privilege;
import tr.gov.gib.taskman.dto.PrivilegeDto;
import tr.gov.gib.taskman.service.PrivilegeService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {

    @Autowired
    private PrivilegeRepository privilegeRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PrivilegeDto save(PrivilegeDto privilegeDto) {
        Privilege privilege = privilegeRepository.findById(privilegeDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Privilege not found"));
        return modelMapper.map(privilege, PrivilegeDto.class);
    }

    @Override
    public PrivilegeDto getById(Long id) {
        Privilege privilege = privilegeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Privilege not found"));
        return modelMapper.map(privilege, PrivilegeDto.class);
    }

    @Override
    public List<PrivilegeDto> getPrivileges() {
        List<Privilege> privileges = privilegeRepository.findAll();
        return privileges.stream()
                .map(privilege -> modelMapper.map(privilege, PrivilegeDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        privilegeRepository.deleteById(id);
    }

    @Override
    public PrivilegeDto update(PrivilegeDto privilegeDto) {
        Privilege existingPrivilege = privilegeRepository.findById(privilegeDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Privilege not found"));
        // Update properties
        // existingPrivilege.set...();
        privilegeRepository.save(existingPrivilege);
        return modelMapper.map(existingPrivilege, PrivilegeDto.class);
    }
}
