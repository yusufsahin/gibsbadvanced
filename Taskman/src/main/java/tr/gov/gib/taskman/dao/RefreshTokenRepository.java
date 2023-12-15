package tr.gov.gib.taskman.dao;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.gov.gib.taskman.dao.model.RefreshToken;

@Repository
@JaversSpringDataAuditable
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    public RefreshToken findByToken(String token);
}