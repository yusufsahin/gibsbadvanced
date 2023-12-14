package tr.gov.gib.taskman.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import tr.gov.gib.taskman.dao.model.RefreshToken;
import tr.gov.gib.taskman.dto.AuthToken;
import tr.gov.gib.taskman.dto.LoginUserDto;
import tr.gov.gib.taskman.dto.UserDto;
import tr.gov.gib.taskman.security.TokenProvider;
import tr.gov.gib.taskman.service.RefreshTokenService;
import tr.gov.gib.taskman.service.UserService;
import tr.gov.gib.taskman.util.ApiPath;

import java.io.UnsupportedEncodingException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(ApiPath.AuthCtrl.CTRL)
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    private RefreshTokenService refreshTokenService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@Valid @RequestBody LoginUserDto loginUser) throws AuthenticationException, UnsupportedEncodingException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);


        AuthToken authToken = new AuthToken();

        UserDto userDetail= userService.findByUsername(loginUser.getUsername());
        final String token = jwtTokenUtil.generateToken(authentication);
        final RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetail);
        UserDto authUser= new UserDto();

        authUser.setId(userDetail.getId());
        authUser.setUsername(userDetail.getUsername());
        authUser.setEmail(userDetail.getEmail());
        authUser.setFirstname(userDetail.getFirstname());
        authUser.setLastname(userDetail.getLastname());
        authUser.setPhonenum(userDetail.getPhonenum());
        authUser.setPicture(userDetail.getPicture());
        authToken.setToken(token);
        authToken.setRefreshToken(refreshToken.getToken());
        authToken.setUser(authUser);


        return ResponseEntity.ok(authToken);
    }

}
