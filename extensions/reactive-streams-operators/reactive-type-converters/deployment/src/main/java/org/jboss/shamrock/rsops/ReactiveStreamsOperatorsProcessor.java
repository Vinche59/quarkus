/*
 * Copyright 2018 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.shamrock.rsops;


import io.smallrye.reactive.converters.ReactiveTypeConverter;
import org.jboss.jandex.ClassInfo;
import org.jboss.jandex.DotName;
import org.jboss.shamrock.deployment.annotations.BuildProducer;
import org.jboss.shamrock.deployment.annotations.BuildStep;
import org.jboss.shamrock.deployment.builditem.CombinedIndexBuildItem;
import org.jboss.shamrock.deployment.builditem.FeatureBuildItem;
import org.jboss.shamrock.deployment.builditem.substrate.ServiceProviderBuildItem;

import java.util.Collection;

/**
 * Searches for implementations of the {@link ReactiveTypeConverter} class and register them as SPI. So the result depends
 * of the implementation added by the user in the build classpath (Maven dependencies).
 *
 * Note that if none are found, nothing is added - so declaring this augmentation is quite useless in this case.
 */
public class ReactiveStreamsOperatorsProcessor {

    private static final DotName REACTIVE_TYPE_CONVERTER = DotName.createSimple(ReactiveTypeConverter.class.getName());

    @BuildStep
    public void build(BuildProducer<ServiceProviderBuildItem> serviceProvider, BuildProducer<FeatureBuildItem> feature,
                      CombinedIndexBuildItem indexBuildItem) {
        feature.produce(new FeatureBuildItem(FeatureBuildItem.REACTIVE_CONVERTERS));
        Collection<ClassInfo> implementors = indexBuildItem.getIndex().getAllKnownImplementors(REACTIVE_TYPE_CONVERTER);

        implementors.forEach(info ->
                serviceProvider.produce(new ServiceProviderBuildItem(REACTIVE_TYPE_CONVERTER.toString(), info.toString())));
    }

}
