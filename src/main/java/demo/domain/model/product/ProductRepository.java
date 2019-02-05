package demo.domain.model.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.UUID;

public interface ProductRepository extends Repository<Product, UUID> {

    Product save(Product product);

    Product findById(UUID id);

    Page<Product> findAll(Pageable pageable);

    void delete(Product product);

}
