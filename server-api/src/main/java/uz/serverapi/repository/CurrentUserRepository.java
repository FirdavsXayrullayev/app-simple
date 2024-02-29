package uz.serverapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.serverapi.model.CurrentUserModel;

@Repository
public interface CurrentUserRepository extends JpaRepository<CurrentUserModel, Integer> {
}
