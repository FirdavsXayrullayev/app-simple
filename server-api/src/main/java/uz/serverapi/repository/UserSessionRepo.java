package uz.serverapi.repository;

import org.springframework.data.repository.CrudRepository;
import uz.serverapi.model.UserSession;

public interface UserSessionRepo extends CrudRepository<UserSession, String> {
}
