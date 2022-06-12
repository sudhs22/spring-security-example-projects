package com.example.security.config;

import com.example.security.model.Tenant;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class MultiTenantConfiguration {

    @Autowired
    private MultiTenantDataSourceProperties dataSourceProperties;

    @Bean
    public MultiTenantDataSource multiTenantDataSource(){
        Map<Object, Object> targetDataSources = new HashMap<>();
        Map<String, Tenant> tenantMap = dataSourceProperties.getDataSources();
        MultiTenantDataSource multiTenantDataSource = new MultiTenantDataSource();
        for(String tenant : tenantMap.keySet()){
            Tenant currentTenant = tenantMap.get(tenant);
            targetDataSources.put(tenant, buildDataSource(currentTenant));
        }
        multiTenantDataSource.setTargetDataSources(targetDataSources);
        return multiTenantDataSource;
    }

    private DataSource buildDataSource(Tenant currentTenant) {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(currentTenant.getDriverClassName());
        dataSourceBuilder.url(currentTenant.getUrl());
        dataSourceBuilder.username(currentTenant.getUsername());
        dataSourceBuilder.password(currentTenant.getPassword());
        dataSourceBuilder.type(HikariDataSource.class);
        DataSource dataSource = dataSourceBuilder.build();
        return dataSource;
    }
}
