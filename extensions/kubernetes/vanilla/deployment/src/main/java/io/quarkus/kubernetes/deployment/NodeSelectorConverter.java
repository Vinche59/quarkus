
package io.quarkus.kubernetes.deployment;

import io.dekorate.kubernetes.config.NodeSelector;
import io.dekorate.kubernetes.config.NodeSelectorBuilder;

public class NodeSelectorConverter {

    public static NodeSelector convert(NodeSelectorConfig nodeSelector) {
        return convertToBuilder(nodeSelector).build();
    }

    public static NodeSelectorBuilder convertToBuilder(NodeSelectorConfig nodeSelector) {
        NodeSelectorBuilder b = new NodeSelectorBuilder();
        if (nodeSelector.key().isPresent() && nodeSelector.value().isPresent()) {
            b.withKey(nodeSelector.key().get());
            b.withValue(nodeSelector.value().get());
        }

        return b;
    }

}
