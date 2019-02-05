package demo.domain.model.product;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ProductGenerator implements ApplicationRunner {

    private final ProductRepository repository;

    public ProductGenerator(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(ApplicationArguments args) {
        this.repository.save(new Product("chair", 9.99));
        this.repository.save(new Product("desk", 99.99));
        this.repository.save(new Product("laptop", 999.99));
    }

}
