package tr.gov.gib.taskman.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.gov.gib.taskman.dao.RefreshTokenRepository;
import tr.gov.gib.taskman.dao.model.RefreshToken;
import tr.gov.gib.taskman.dao.model.User;
import tr.gov.gib.taskman.dto.UserDto;
import tr.gov.gib.taskman.service.RefreshTokenService;

import java.util.Date;
import java.util.UUID;

import static tr.gov.gib.taskman.util.Constant.REFRESH_TOKEN_VALIDITY_SECONDS;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    private final ModelMapper modelMapper;


    @Autowired
    public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository, ModelMapper modelMapper) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean isRefreshExpired(RefreshToken token) {
        return token.getExpiryDate().before(new Date());
    }

    @Override
    public RefreshToken createRefreshToken(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        RefreshToken token = new RefreshToken();
        token.setUser(user);
        token.setToken(UUID.randomUUID().toString());
        token.setExpiryDate(new Date(System.currentTimeMillis() + REFRESH_TOKEN_VALIDITY_SECONDS * 1000));
        refreshTokenRepository.save(token);
        return token;
    }

    @Override
    public RefreshToken findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }
}
