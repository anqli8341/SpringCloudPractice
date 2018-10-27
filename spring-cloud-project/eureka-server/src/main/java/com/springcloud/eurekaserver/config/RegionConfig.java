package com.springcloud.eurekaserver.config;

import java.util.HashMap;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.netflix.eureka.server.EurekaServerAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EurekaServerConfigBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.discovery.EurekaClientConfig;
import com.netflix.eureka.EurekaServerConfig;

/**
 * @author anquan li
 */
@Configuration
@AutoConfigureBefore(EurekaServerAutoConfiguration.class)
public class RegionConfig {
  @Bean
  @ConditionalOnMissingBean
  public EurekaServerConfig eurekaServerConfig(EurekaClientConfig clientConfig){
    EurekaServerConfigBean eurekaServerConfigBean=new EurekaServerConfigBean();
    if(clientConfig.shouldRegisterWithEureka()){
      eurekaServerConfigBean.setRegistrySyncRetries(5);
    }
    eurekaServerConfigBean.setRemoteRegionAppWhitelist(new HashMap<>());
    return eurekaServerConfigBean;
  }
}
