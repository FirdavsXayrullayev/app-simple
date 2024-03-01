package uz.serverapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.serverapi.model.ProductDtoSample;

@Repository
public interface ProductRepositorySample extends JpaRepository<ProductDtoSample, Integer> {
}
