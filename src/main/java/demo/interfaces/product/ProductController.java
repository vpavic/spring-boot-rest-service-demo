package demo.interfaces.product;

import demo.domain.model.product.Product;
import demo.domain.model.product.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Controller
@RequestMapping(path = "/products")
public class ProductController {

    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<ProductResource>> getAll(PagedResourcesAssembler<Product> pageableAssembler,
            Pageable pageable) {
        Page<Product> products = this.repository.findAll(pageable);
        PagedModel<ProductResource> resources = pageableAssembler.toModel(products, ProductResourceAssembler.instance);
        for (ProductResource resource : resources) {
            resource.getLink(IanaLinkRelations.SELF).ifPresent(
                    link -> resources.add(link.withRel(IanaLinkRelations.ITEM)));
        }
        return ResponseEntity.ok(resources);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateProductRequest request) {
        Product product = new Product(request.getName(), request.getPrice());
        this.repository.save(product);
        URI location = linkTo(methodOn(ProductController.class).getById(product.getId())).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Product product = this.repository.findById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        ProductResource resource = ProductResourceAssembler.instance.toModel(product);
        return ResponseEntity.ok(resource);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<Void> updateById(@PathVariable UUID id, @RequestBody UpdateProductRequest request) {
        Product product = this.repository.findById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        product.updatePrice(request.getPrice());
        this.repository.save(product);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        Product product = this.repository.findById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        this.repository.delete(product);
        return ResponseEntity.noContent().build();
    }

}
