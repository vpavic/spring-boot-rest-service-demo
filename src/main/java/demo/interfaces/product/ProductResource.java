package demo.interfaces.product;

import demo.domain.model.product.Product;
import org.springframework.hateoas.ResourceSupport;

class ProductResource extends ResourceSupport {

    private final String name;

    private final double price;

    private ProductResource(String name, double price) {
        this.name = name;
        this.price = price;
    }

    static ProductResource fromEntity(Product product) {
        return new ProductResource(product.getName(), product.getPrice());
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

}
