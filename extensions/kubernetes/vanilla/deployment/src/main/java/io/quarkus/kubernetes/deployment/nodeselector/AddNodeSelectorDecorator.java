package io.quarkus.kubernetes.deployment.nodeselector;

import java.util.Objects;

import io.dekorate.kubernetes.decorator.NamedResourceDecorator;
import io.dekorate.utils.Strings;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.PodSpecFluent;

/**
 * Copied from dekorate.
 * TODO: This decorator should be removed and replaced by the Dekorate AddNodeSelectorDecorator class after
 * https://github.com/dekorateio/dekorate/pull/1299 is merged and Dekorate is bumped.
 */
public class AddNodeSelectorDecorator extends NamedResourceDecorator<PodSpecFluent<?>> {

    private final NodeSelector nodeSelector;

    public AddNodeSelectorDecorator(String deploymentName, NodeSelector nodeSelector) {
        super(deploymentName);
        this.nodeSelector = nodeSelector;
    }

    public void andThenVisit(PodSpecFluent<?> podSpec, ObjectMeta resourceMeta) {
        if (Strings.isNotNullOrEmpty(nodeSelector.getKey()) && Strings.isNotNullOrEmpty(nodeSelector.getValue())) {
            podSpec.removeFromNodeSelector(nodeSelector.getKey());
            podSpec.addToNodeSelector(nodeSelector.getKey(), nodeSelector.getValue());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AddNodeSelectorDecorator that = (AddNodeSelectorDecorator) o;
        return Objects.equals(nodeSelector, that.nodeSelector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeSelector);
    }
}
