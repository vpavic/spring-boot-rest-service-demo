package demo.interfaces;

import demo.interfaces.product.ProductController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Controller
@RequestMapping(path = "/")
public class RootController {

    @GetMapping
    public ResponseEntity<CollectionModel<Void>> getRoot() {
        CollectionModel<Void> resources = new CollectionModel<>(List.of());
        resources.add(linkTo(methodOn(ProductController.class).getAll(null, null)).withRel("products"));
        return ResponseEntity.ok(resources);
    }

}
