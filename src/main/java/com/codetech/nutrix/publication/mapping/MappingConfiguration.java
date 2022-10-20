package com.codetech.nutrix.publication.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("publicationMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public PublicationMapper publicationMapper(){
        return new PublicationMapper();
    }


}
