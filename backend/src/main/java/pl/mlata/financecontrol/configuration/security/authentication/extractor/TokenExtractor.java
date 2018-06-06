package pl.mlata.financecontrol.configuration.security.authentication.extractor;

public interface TokenExtractor {
    String extract(String payload);
}
