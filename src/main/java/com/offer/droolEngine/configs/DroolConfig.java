package com.offer.droolEngine.configs;

import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Slf4j
@Configuration
public class DroolConfig {
    private final KieServices kieServices = KieServices.Factory.get();

    private KieFileSystem getKieFileSystem() {
        KieFileSystem kieFileSystem = KieServices.get().newKieFileSystem();
        kieFileSystem.write(ResourceFactory.newClassPathResource("rules/discount_rules.xls"));
        return kieFileSystem;
    }

    private void getKieRepository() {
        KieRepository kieRepository = kieServices.getRepository();
        kieRepository.addKieModule(kieRepository::getDefaultReleaseId);
    }

    @Bean
    public KieContainer getKieContainer() throws IOException {
        log.info("Container created...");
        getKieRepository();
        KieBuilder kb = kieServices.newKieBuilder(getKieFileSystem());
        kb.buildAll();

        if (kb.getResults().hasMessages(Message.Level.ERROR)) {
            throw new IllegalStateException("Error building KieContainer: " + kb.getResults());
        }

        KieModule kieModule = kb.getKieModule();
        return kieServices.newKieContainer(kieModule.getReleaseId());
    }

    @Bean
    public KieSession getKieSession(KieContainer kieContainer) {
        log.info("Session Created...");
        return kieContainer.newKieSession();
    }
}
