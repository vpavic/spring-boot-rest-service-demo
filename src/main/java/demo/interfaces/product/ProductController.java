package demo.interfaces.product;

import demo.domain.model.product.Product;
import demo.domain.model.product.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Controller
@RequestMapping(path = "/product")
public class ProductController {

    private static final ProductResourceAssembler assembler = new ProductResourceAssembler();

    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<Resources<ProductResource>> findAll(PagedResourcesAssembler<Product> pageableAssembler, Pageable pageable) {
        Page<Product> products = this.repository.findAll(pageable);
        PagedResources<ProductResource> resources = pageableAssembler.toResource(products, assembler);
        return ResponseEntity.ok(resources);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateProductRequest request) {
        Product product = new Product(request.getName(), request.getPrice());
        this.repository.save(product);
        URI location = linkTo(methodOn(ProductController.class).findOne(product.getId())).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findOne(@PathVariable UUID id) {
        Product product = this.repository.findById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        ProductResource resource = assembler.toResource(product);
        return ResponseEntity.ok(resource);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id, @RequestBody UpdateProductRequest request) {
        Product product = this.repository.findById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        product.updatePrice(request.getPrice());
        this.repository.save(product);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        Product product = this.repository.findById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        this.repository.delete(product);
        return ResponseEntity.noContent().build();
    }

}
