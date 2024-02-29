package uz.serverapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.serverapi.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
