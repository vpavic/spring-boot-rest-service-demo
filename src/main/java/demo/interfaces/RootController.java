package demo.interfaces;

import demo.interfaces.product.ProductController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Controller
@RequestMapping(path = "/")
public class RootController {

    @GetMapping
    public ResponseEntity<CollectionModel<?>> root() {
        CollectionModel<?> resources = new CollectionModel<>(Collections.emptyList());
        resources.add(linkTo(methodOn(ProductController.class).findAll(null, null)).withRel("products"));
        return ResponseEntity.ok(resources);
    }

}
