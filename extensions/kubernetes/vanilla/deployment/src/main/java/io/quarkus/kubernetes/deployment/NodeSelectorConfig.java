package io.quarkus.kubernetes.deployment;

import java.util.Optional;

public interface NodeSelectorConfig {
    /**
     * The key of the nodeSelector.
     */
    Optional<String> key();

    /**
     * The value of the nodeSelector.
     */
    Optional<String> value();
}
