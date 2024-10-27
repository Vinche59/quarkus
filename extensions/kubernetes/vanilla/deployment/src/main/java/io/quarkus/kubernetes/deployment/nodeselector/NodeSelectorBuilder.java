package io.quarkus.kubernetes.deployment.nodeselector;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

/**
 * Copied from dekorate.
 * TODO: This should be removed and replaced after
 * https://github.com/dekorateio/dekorate/pull/1299 is merged and Dekorate is bumped.
 */
public class NodeSelectorBuilder extends NodeSelectorFluent<NodeSelectorBuilder>
        implements VisitableBuilder<NodeSelector, NodeSelectorBuilder> {

    public NodeSelectorBuilder() {
        this(new NodeSelector());
    }

    public NodeSelectorBuilder(NodeSelectorFluent<?> fluent) {
        this(fluent, new NodeSelector());
    }

    public NodeSelectorBuilder(NodeSelectorFluent<?> fluent, NodeSelector instance) {
        this.fluent = fluent;
        fluent.copyInstance(instance);
    }

    public NodeSelectorBuilder(NodeSelector instance) {
        this.fluent = this;
        this.copyInstance(instance);
    }

    NodeSelectorFluent<?> fluent;

    public EditableNodeSelector build() {
        EditableNodeSelector buildable = new EditableNodeSelector(fluent.getKey(), fluent.getValue());
        return buildable;
    }
}