package tr.gov.gib.taskman.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.gov.gib.taskman.dao.PrivilegeRepository;
import tr.gov.gib.taskman.dao.RoleRepository;
import tr.gov.gib.taskman.dao.model.Privilege;
import tr.gov.gib.taskman.dao.model.Role;
import tr.gov.gib.taskman.dto.RoleDto;
import tr.gov.gib.taskman.service.RoleService;

import java.util.*;

@Service
public  class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PrivilegeRepository privilegeRepository;


    @Override
    public RoleDto save(RoleDto roleDto) {
        Role role = modelMapper.map(roleDto, Role.class);
        role = roleRepository.save(role);
        roleDto.setId(role.getId());
        return roleDto;
    }

    @Override
    public RoleDto getById(Long id) throws Exception {
        Optional<Role> optionalRole = roleRepository.findById(id);

        if(!optionalRole.isPresent())
            throw new Exception("Role not found");

        return modelMapper.map(optionalRole.get(),RoleDto.class);
    }

    @Override
    public List<RoleDto> getRoles() {
        List<Role> data= roleRepository.findAll();
        return Arrays.asList(modelMapper.map(data,RoleDto[].class));
    }

    @Override
    public void delete(Long id) {
            roleRepository.deleteById(id);
    }

    @Override
    public RoleDto update(RoleDto roleDto) {
        Optional<Role> optionalRole= roleRepository.findById(roleDto.getId());
        if(optionalRole.isEmpty())
            throw  new IllegalArgumentException("Role not found");
        optionalRole.get().setName(roleDto.getName());
        optionalRole.get().setDescription(roleDto.getDescription());
        roleRepository.save(optionalRole.get());
        return modelMapper.map(optionalRole, RoleDto.class);
    }

    @Override
    public RoleDto getByName(String name) {
        Role role = roleRepository.findByName(name);
        if(role == null)
            throw new IllegalArgumentException("Role not found");
        return modelMapper.map(role,RoleDto.class);
    }

    @Override
    public RoleDto addPrivilegeByPrivilegeId(Long id, Long privilegeId) {
        try{
            Optional<Role> role = roleRepository.findById(id);
            if(role.isPresent()){
                List<Privilege> oldPrivileges = new ArrayList<>();
                Collection<Privilege> privileges = role.get().getPrivileges();
                boolean hasPrivilege = false;
                for(Privilege p : privileges){
                    boolean isCopied = false;
                    if(p.getId() == privilegeId){
                        hasPrivilege = true;
                    }
                    for(Privilege p2 : oldPrivileges){
                        if(p.getName() == p2.getName()){
                            isCopied = true;
                        }
                    }
                    if(!isCopied)
                        oldPrivileges.add(p);
                }
                if(!hasPrivilege){
                    Privilege privilege =privilegeRepository.getReferenceById(privilegeId);
                    oldPrivileges.add(privilege);
                    role.get().setPrivileges(oldPrivileges);
                    roleRepository.save(role.get());
                    return modelMapper.map(role.get(),RoleDto.class);
                }

                throw new IllegalArgumentException("User already has this role");
            }
            throw new IllegalArgumentException("User Not Found");
        }
        catch (Exception e){
            throw new IllegalArgumentException("Role or User Not Found");
        }
    }

    @Override
    public RoleDto addPrivilegeByPrivilegeName(Long id, String privilegeName) {
        try{
            Optional<Role> role = roleRepository.findById(id);
            if(role.isPresent()){
                List<Privilege> oldPrivileges = new ArrayList<>();
                Collection<Privilege> privileges = role.get().getPrivileges();
                boolean hasPrivilege = false;
                for(Privilege p : privileges){
                    boolean isCopied = false;
                    if(p.getName() == privilegeName){
                        hasPrivilege = true;
                    }
                    for(Privilege p2 : oldPrivileges){
                        if(p.getName() == p2.getName()){
                            isCopied = true;
                        }
                    }
                    if(!isCopied)
                        oldPrivileges.add(p);
                }
                if(!hasPrivilege){
                    Privilege privilege = privilegeRepository.findByName(privilegeName);
                    oldPrivileges.add(privilege);
                    role.get().setPrivileges(oldPrivileges);
                    roleRepository.save(role.get());
                    return modelMapper.map(role.get(),RoleDto.class);
                }

                throw new IllegalArgumentException("User already has this role");
            }
            throw new IllegalArgumentException("User Not Found");
        }
        catch (Exception e){
            throw new IllegalArgumentException("Role or User Not Found");
        }
    }


}
