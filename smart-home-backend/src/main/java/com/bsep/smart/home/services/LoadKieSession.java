package com.bsep.smart.home.services;

import lombok.RequiredArgsConstructor;
import org.drools.core.BeliefSystemType;
import org.drools.core.SessionConfiguration;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.definition.KiePackage;
import org.kie.api.definition.rule.Rule;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoadKieSession {

    public KieSession execute() throws IOException {
        KieSession kSession = createKieSessionFromDRL();
        printDrl(kSession);
        return kSession;
    }

    private KieSession createKieSessionFromDRL() throws IOException {
        KieBaseConfiguration config = KnowledgeBaseFactory.newKnowledgeBaseConfiguration();

        config.setOption(EventProcessingOption.STREAM);

        KieHelper kieHelper = new KieHelper();
        getResourceFolderFiles(kieHelper);
        Results results = kieHelper.verify();

        if (results.hasMessages(Message.Level.WARNING, Message.Level.ERROR)) {
            List<Message> messages = results.getMessages(Message.Level.WARNING, Message.Level.ERROR);
            for (Message message : messages) {
                System.out.println("Error: " + message.getText());
            }

            throw new IllegalStateException("Compilation errors were found. Check the logs.");
        }
        KieBase kieBase = kieHelper.build(config);

        KieSessionConfiguration ksConf = KnowledgeBaseFactory.newKnowledgeSessionConfiguration();
        ((SessionConfiguration) ksConf).setBeliefSystemType(BeliefSystemType.DEFEASIBLE);
        return kieBase.newKieSession(ksConf, null);
    }

    private static void getResourceFolderFiles(KieHelper kieHelper) throws IOException {
        ClassLoader cl = Thread.currentThread().getContextClassLoader().getClass().getClassLoader();
        ResourcePatternResolver resolver = new
                PathMatchingResourcePatternResolver(cl);
        Resource[] resources = resolver.getResources("/rules/**/*.drl");
        for (Resource resource : resources) {
            kieHelper.addResource(ResourceFactory.newFileResource(resource.getFile()),
                    ResourceType.DRL);
        }
    }

    public void printDrl(KieSession kSession) {
        KieBase kieBase = kSession.getKieBase();
        Iterable<KiePackage> packages = kieBase.getKiePackages();

        for (KiePackage kiePackage : packages) {
            Collection<Rule> rules = kiePackage.getRules();

            for (Rule rule : rules) {
                String ruleName = rule.getName();
                System.out.println("Rule name: " + ruleName);
            }
        }
    }

}
