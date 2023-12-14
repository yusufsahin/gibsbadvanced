package tr.gov.gib.taskman.service;

import tr.gov.gib.taskman.dao.model.RefreshToken;
import tr.gov.gib.taskman.dto.UserDto;

public interface RefreshTokenService {
    public boolean isRefreshExpired(RefreshToken token);

    public RefreshToken createRefreshToken(UserDto userDto);

    public RefreshToken findByToken(String token);

}
