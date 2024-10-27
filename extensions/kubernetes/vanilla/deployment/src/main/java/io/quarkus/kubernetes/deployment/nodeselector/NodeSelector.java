package io.quarkus.kubernetes.deployment.nodeselector;

import io.fabric8.kubernetes.api.model.NodeSelectorBuilder;

/**
 * Copied from dekorate.
 * TODO: This should be removed and replaced after
 * https://github.com/dekorateio/dekorate/pull/1299 is merged and Dekorate is bumped.
 */
public class NodeSelector {
    private String key;
    private String value;

    public NodeSelector() {
    }

    public NodeSelector(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return this.key;
    }

    public String getValue() {
        return this.value;
    }

    public static NodeSelectorBuilder newBuilder() {
        return new NodeSelectorBuilder();
    }

    public static NodeSelectorBuilder newBuilderFromDefaults() {
        return new NodeSelectorBuilder();
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        NodeSelector that = (NodeSelector) o;
        if (!java.util.Objects.equals(key, that.key))
            return false;
        if (!java.util.Objects.equals(value, that.value))
            return false;
        return true;
    }

    public int hashCode() {
        return java.util.Objects.hash(key, value, super.hashCode());
    }

}