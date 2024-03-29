package demo.springdemo.filter;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.nio.charset.Charset;

@ConfigurationProperties(prefix = "spring.http.encoding")
public class EncodeFilterConf {
    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    private Charset charset = DEFAULT_CHARSET;
    private boolean force;

    public Charset getCharset() {
        return charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    public boolean isForce() {
        return force;
    }

    public void setForce(boolean force) {
        this.force = force;
    }
}
