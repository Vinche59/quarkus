package io.quarkus.kubernetes.deployment.nodeselector;

import io.fabric8.kubernetes.api.builder.Editable;

/**
 * Copied from dekorate.
 * TODO: This should be removed and replaced after
 * https://github.com/dekorateio/dekorate/pull/1299 is merged and Dekorate is bumped.
 */
public class EditableNodeSelector extends NodeSelector implements Editable<NodeSelectorBuilder> {
    public EditableNodeSelector() {
        super();
    }

    public EditableNodeSelector(String key, String value) {
        super(key, value);
    }

    public NodeSelectorBuilder edit() {
        return new NodeSelectorBuilder(this);
    }

}