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
        ProductResource resource = ProductResource.fromEntity(entity);
        resource.add(linkTo(methodOn(ProductController.class).findOne(entity.getId())).withSelfRel());
        resource.add(linkTo(methodOn(ProductController.class).findAll(null, null)).withRel("products"));
        return resource;
    }

}
