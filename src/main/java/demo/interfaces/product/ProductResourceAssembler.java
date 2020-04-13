package demo.interfaces.product;

import demo.domain.model.product.Product;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

class ProductResourceAssembler extends RepresentationModelAssemblerSupport<Product, ProductResource> {

    static final ProductResourceAssembler instance = new ProductResourceAssembler();

    ProductResourceAssembler() {
        super(ProductController.class, ProductResource.class);
    }

    @Override
    public ProductResource toModel(Product entity) {
        ProductResource resource = createModelWithId(entity.getId(), entity);
        resource.add(linkTo(methodOn(ProductController.class).getAll(null, null)).withRel(IanaLinkRelations.COLLECTION));
        return resource;
    }

    @Override
    protected ProductResource instantiateModel(Product entity) {
        return ProductResource.fromProduct(entity);
    }

}
