package uz.serverapi.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import uz.serverapi.model.ProductDtoSample;
import uz.serverapi.repository.ProductRepository;
import uz.serverapi.repository.ProductRepositorySample;

import java.util.List;

@Repository
public class ProductRepositoryImpl {
    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private EntityManager entityManager;

    public void save(List<ProductDtoSample> productSampleList) {
        StringBuilder stringBuilder = new StringBuilder("INSERT INTO product_dto_sample(id, name, price) VALUES (1, 'asd', 'ad')");
        Query query = entityManager.createQuery(stringBuilder.toString());

        query.getResultList();
        System.out.println(query.getResultList());
    }
}
