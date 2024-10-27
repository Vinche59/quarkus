package io.quarkus.kubernetes.deployment.nodeselector;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import io.dekorate.kubernetes.decorator.NamedResourceDecorator;
import io.dekorate.utils.Strings;
import io.fabric8.knative.serving.v1.RevisionSpecFluent;
import io.fabric8.kubernetes.api.model.ObjectMeta;

/**
 * Copied from dekorate.
 * TODO: This decorator should be removed and replaced by the Dekorate AddNodeSelectorToRevisionDecorator class after
 * https://github.com/dekorateio/dekorate/pull/1299 is merged and Dekorate is bumped.
 */
public class AddNodeSelectorToRevisionDecorator extends NamedResourceDecorator<RevisionSpecFluent<?>> {

    private final NodeSelector nodeSelector;

    public AddNodeSelectorToRevisionDecorator(NodeSelector nodeSelector) {
        this(ANY, nodeSelector);
    }

    public AddNodeSelectorToRevisionDecorator(String deploymentName, NodeSelector nodeSelector) {
        super(deploymentName);
        this.nodeSelector = nodeSelector;
    }

    public void andThenVisit(RevisionSpecFluent<?> revisionSpec, ObjectMeta resourceMeta) {
        if (Strings.isNotNullOrEmpty(nodeSelector.getKey()) && Strings.isNotNullOrEmpty(nodeSelector.getValue())) {
            Map<String, String> existing = revisionSpec.getNodeSelector();

            if (existing == null)
                existing = new HashMap<>();
            else
                existing.clear();

            existing.put(nodeSelector.getKey(), nodeSelector.getValue());
            revisionSpec.withNodeSelector(existing);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AddNodeSelectorToRevisionDecorator that = (AddNodeSelectorToRevisionDecorator) o;
        return Objects.equals(nodeSelector, that.nodeSelector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeSelector);
    }
}
