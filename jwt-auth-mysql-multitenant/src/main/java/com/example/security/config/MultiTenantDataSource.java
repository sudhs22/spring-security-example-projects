package com.example.security.config;

import com.example.security.model.TenantContext;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


public class MultiTenantDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        String currentTenant = TenantContext.getCurrentTenant();
        //TODO: Remove this hardcoded default value
        return currentTenant == null ? "tenantA" : currentTenant;
    }

}
