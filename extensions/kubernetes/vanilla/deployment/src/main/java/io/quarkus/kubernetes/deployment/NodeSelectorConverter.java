package io.quarkus.kubernetes.deployment;

import io.quarkus.kubernetes.deployment.nodeselector.NodeSelector;
import io.quarkus.kubernetes.deployment.nodeselector.NodeSelectorBuilder;

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
