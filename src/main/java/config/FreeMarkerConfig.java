package config;

import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FreeMarkerConfig {
    @Bean
    public freemarker.template.Configuration freeMarkerConfiguration() {
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(new Version("2.3.32"));
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setFallbackOnNullLoopVariable(false);
        cfg.setLogTemplateExceptions(false);
        return cfg;
    }
}
