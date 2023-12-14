package tr.gov.gib.taskman.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tr.gov.gib.taskman.dao.RoleRepository;
import tr.gov.gib.taskman.dao.UserRepository;
import tr.gov.gib.taskman.dao.model.Privilege;
import tr.gov.gib.taskman.dao.model.Role;
import tr.gov.gib.taskman.dto.UserDto;
import tr.gov.gib.taskman.service.UserService;
import  tr.gov.gib.taskman.dao.model.User;
import java.util.*;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service(value = "userService")
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public UserDto findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto save(UserDto userDto) {
        User u = modelMapper.map(userDto, User.class);
        u = userRepository.save(u);
        userDto.setId(u.getId());
        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> data = userRepository.findAll();
        return Arrays.asList(modelMapper.map(data, UserDto[].class));
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);

    }

    @Override
    public UserDto update(UserDto userDto) {
        User userDb = userRepository.getReferenceById(userDto.getId());
        if (userDb == null)
            throw new IllegalArgumentException("User Does Not Exist ID:" + userDto.getId());

        //userDb.setUserName(userDto.getUserName());
        userDb.setFirstname(userDto.getFirstname());
        userDb.setLastname(userDto.getLastname());
        userDb.setPhonenum(userDto.getPhonenum());
        userDb.setPicture(userDto.getPicture());
        userDb.setFirstname(userDto.getFirstname());
        userDb.setLastname(userDto.getLastname());
        userRepository.save(userDb);
        return modelMapper.map(userDb, UserDto.class);
    }

    @Override
    public UserDto addRoleByRoleId(Long id, Long roleId) {
        try{
            Optional<User> user = userRepository.findById(id);
            if(user.isPresent()){
                List<Role> oldRoles = new ArrayList<>();
                Collection<Role> roles = user.get().getRoles();
                boolean hasRole = false;
                for(Role r : roles){
                    boolean isCopied = false;
                    if(r.getId() == roleId){
                        hasRole = true;
                    }
                    for(Role r2 : oldRoles){
                        if(r.getName() == r2.getName()){
                            isCopied = true;
                        }
                    }
                    if(!isCopied)
                        oldRoles.add(r);
                }
                if(!hasRole){
                    Role role =roleRepository.findById(roleId).get();
                    oldRoles.add(role);
                    user.get().setRoles(oldRoles);
                    userRepository.save(user.get());
                    return modelMapper.map(user.get(),UserDto.class);
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
    public UserDto addRoleByRoleName(Long id, String roleName) {
        try{
            Optional<User> user = userRepository.findById(id);
            if(user.isPresent()){
                List<Role> oldRoles = new ArrayList<>();
                Collection<Role> roles = user.get().getRoles();
                boolean hasRole = false;
                for(Role r : roles){
                    boolean isCopied = false;
                    if(r.getName() == roleName){
                        hasRole = true;
                    }
                    for(Role r2 : oldRoles){
                        if(r.getName() == r2.getName()){
                            isCopied = true;
                        }
                    }
                    if(!isCopied)
                        oldRoles.add(r);
                }
                if(!hasRole){
                    Role role = roleRepository.findByName(roleName);
                    oldRoles.add(role);
                    user.get().setRoles(oldRoles);
                    userRepository.save(user.get());
                    return modelMapper.map(user.get(),UserDto.class);
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        List<String> privileges = getPrivileges(user.getRoles());
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        privileges.forEach(privilege -> {
            //authorities.add(new SimpleGrantedAuthority(role.getName()));
            authorities.add(new SimpleGrantedAuthority(privilege));
        });
        return authorities;
        //return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
    private List<String> getPrivileges(Collection<Role> roles) {

        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();
        for (Role role : roles) {
            privileges.add(role.getName());
            collection.addAll(role.getPrivileges());
        }
        for (Privilege item : collection) {
            privileges.add(item.getName());
        }
        return privileges;
    }

}
