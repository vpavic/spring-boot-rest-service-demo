package demo.interfaces.product;

import demo.domain.model.product.Product;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

@Relation(collectionRelation = "item")
class ProductResource extends ResourceSupport {

    private final String name;

    private final double price;

    private ProductResource(String name, double price) {
        this.name = name;
        this.price = price;
    }

    static ProductResource fromProduct(Product product) {
        return new ProductResource(product.getName(), product.getPrice());
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

}
