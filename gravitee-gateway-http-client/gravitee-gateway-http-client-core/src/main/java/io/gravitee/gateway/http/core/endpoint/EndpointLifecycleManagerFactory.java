/**
 * Copyright (C) 2015 The Gravitee team (http://gravitee.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.gravitee.gateway.http.core.endpoint;

import io.gravitee.common.spring.factory.AbstractAutowiringFactoryBean;
import io.gravitee.gateway.env.GatewayConfiguration;
import io.gravitee.gateway.http.core.endpoint.impl.DefaultEndpointLifecycleManager;
import io.gravitee.gateway.http.core.endpoint.impl.tenant.MultiTenantAwareEndpointLifecycleManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author David BRASSELY (david.brassely at graviteesource.com)
 * @author GraviteeSource Team
 */
public class EndpointLifecycleManagerFactory extends AbstractAutowiringFactoryBean<EndpointLifecycleManager> {

    @Autowired
    private GatewayConfiguration gatewayConfiguration;

    @Override
    protected EndpointLifecycleManager doCreateInstance() throws Exception {
        if (gatewayConfiguration.tenant().isPresent()) {
            return new MultiTenantAwareEndpointLifecycleManager(gatewayConfiguration.tenant().get());
        }

        return new DefaultEndpointLifecycleManager();
    }

    @Override
    public Class<?> getObjectType() {
        return EndpointLifecycleManager.class;
    }
}
