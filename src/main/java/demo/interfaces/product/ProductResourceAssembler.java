package demo.interfaces.product;

import demo.domain.model.product.Product;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

class ProductResourceAssembler extends ResourceAssemblerSupport<Product, ProductResource> {

    ProductResourceAssembler() {
        super(ProductController.class, ProductResource.class);
    }

    @Override
    public ProductResource toResource(Product entity) {
        ProductResource resource = createResourceWithId(entity.getId(), entity);
        resource.add(linkTo(methodOn(ProductController.class).findAll(null, null)).withRel("collection"));
        return resource;
    }

    @Override
    protected ProductResource instantiateResource(Product entity) {
        return ProductResource.fromProduct(entity);
    }

}
