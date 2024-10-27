package io.quarkus.kubernetes.deployment.nodeselector;

import io.fabric8.kubernetes.api.builder.BaseFluent;

/**
 * Copied from dekorate.
 * TODO: This should be removed and replaced after
 * https://github.com/dekorateio/dekorate/pull/1299 is merged and Dekorate is bumped.
 */
public class NodeSelectorFluent<A extends NodeSelectorFluent<A>> extends BaseFluent<A> {
    private String key;
    private String value;

    public NodeSelectorFluent() {
    }

    public NodeSelectorFluent(NodeSelector instance) {
        this.copyInstance(instance);
    }

    protected void copyInstance(NodeSelector instance) {
        instance = (instance != null ? instance : new NodeSelector());
        if (instance != null) {
            this.withKey(instance.getKey());
            this.withValue(instance.getValue());
        }
    }

    public String getKey() {
        return this.key;
    }

    public A withKey(String key) {
        this.key = key;
        return (A) this;
    }

    public boolean hasKey() {
        return this.key != null;
    }

    public String getValue() {
        return this.value;
    }

    public A withValue(String value) {
        this.value = value;
        return (A) this;
    }

    public boolean hasValue() {
        return this.value != null;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        NodeSelectorFluent that = (NodeSelectorFluent) o;
        if (!java.util.Objects.equals(key, that.key))
            return false;
        if (!java.util.Objects.equals(value, that.value))
            return false;
        return true;
    }

    public int hashCode() {
        return java.util.Objects.hash(key, value, super.hashCode());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (key != null) {
            sb.append("key:");
            sb.append(key + ",");
        }
        if (value != null) {
            sb.append("value:");
            sb.append(value);
        }
        sb.append("}");
        return sb.toString();
    }

}