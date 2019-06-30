package demo.springdemo.config;

import demo.springdemo.filter.EncodeFilterConf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableConfigurationProperties(EncodeFilterConf.class)
@ConditionalOnClass(CharacterEncodingFilter.class)
@ConditionalOnProperty(prefix = "spring.http.encoding", value = "enable", matchIfMissing = true)
public class EncodeFilterAutoConfiguration {

    @Autowired
    protected EncodeFilterConf conf;

    @Bean
    @ConditionalOnMissingBean(CharacterEncodingFilter.class)
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding(conf.getCharset().name());
        filter.setForceEncoding(conf.isForce());
        return filter;
    }


}
