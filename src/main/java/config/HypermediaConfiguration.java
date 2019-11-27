package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.mediatype.hal.HalConfiguration;
import org.springframework.hateoas.mediatype.hal.HalConfiguration.RenderSingleLinks;

@Configuration
public class HypermediaConfiguration {

    @Bean
    public HalConfiguration halConfiguration() {
        return new HalConfiguration().withRenderSingleLinksFor(IanaLinkRelations.ITEM, RenderSingleLinks.AS_ARRAY);
    }

}
