package az.devlab.pdftextsearch.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "az.devlab.pdftextsearch.repository")
public class ElasticsearchConfig {

}
