package org.springdoc.webmvc.core;

import java.util.List;
import java.util.Optional;
import org.springdoc.core.AbstractRequestService;
import org.springdoc.core.GenericParameterService;
import org.springdoc.core.GenericResponseService;
import org.springdoc.core.OperationService;
import org.springdoc.core.PropertyResolverUtils;
import org.springdoc.core.RequestBodyService;
import org.springdoc.core.SpringDocConfigProperties;
import org.springdoc.core.SpringDocProviders;
import org.springdoc.core.providers.SpringWebProvider;
import org.springdoc.webmvc.api.OpenApiWebMvcResource;
import org.springframework.aot.beans.factory.BeanDefinitionRegistrar;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

public final class MedicalStoreManagementSystemTestsContextInitializer {
  public static void registerSpringDocWebMvcConfiguration_SpringDocWebMvcRouterConfiguration(
      DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("org.springdoc.webmvc.core.SpringDocWebMvcConfiguration$SpringDocWebMvcRouterConfiguration", SpringDocWebMvcConfiguration.SpringDocWebMvcRouterConfiguration.class)
        .instanceSupplier(SpringDocWebMvcConfiguration.SpringDocWebMvcRouterConfiguration::new).register(beanFactory);
  }

  public static void registerSpringDocWebMvcRouterConfiguration_routerFunctionProvider(
      DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("routerFunctionProvider", RouterFunctionWebMvcProvider.class).withFactoryMethod(SpringDocWebMvcConfiguration.SpringDocWebMvcRouterConfiguration.class, "routerFunctionProvider")
        .instanceSupplier(() -> beanFactory.getBean(SpringDocWebMvcConfiguration.SpringDocWebMvcRouterConfiguration.class).routerFunctionProvider()).register(beanFactory);
  }

  public static void registerSpringDocWebMvcConfiguration_openApiResource(
      DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("openApiResource", OpenApiWebMvcResource.class).withFactoryMethod(SpringDocWebMvcConfiguration.class, "openApiResource", ObjectFactory.class, AbstractRequestService.class, GenericResponseService.class, OperationService.class, SpringDocConfigProperties.class, Optional.class, Optional.class, SpringDocProviders.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(SpringDocWebMvcConfiguration.class).openApiResource(attributes.get(0), attributes.get(1), attributes.get(2), attributes.get(3), attributes.get(4), attributes.get(5), attributes.get(6), attributes.get(7)))).register(beanFactory);
  }

  public static void registerSpringDocWebMvcConfiguration_requestBuilder(
      DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("requestBuilder", RequestService.class).withFactoryMethod(SpringDocWebMvcConfiguration.class, "requestBuilder", GenericParameterService.class, RequestBodyService.class, OperationService.class, Optional.class, LocalVariableTableParameterNameDiscoverer.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(SpringDocWebMvcConfiguration.class).requestBuilder(attributes.get(0), attributes.get(1), attributes.get(2), attributes.get(3), attributes.get(4)))).register(beanFactory);
  }

  public static void registerSpringDocWebMvcConfiguration_springWebProvider(
      DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("springWebProvider", SpringWebProvider.class).withFactoryMethod(SpringDocWebMvcConfiguration.class, "springWebProvider")
        .instanceSupplier(() -> beanFactory.getBean(SpringDocWebMvcConfiguration.class).springWebProvider()).register(beanFactory);
  }

  public static void registerSpringDocWebMvcConfiguration_responseBuilder(
      DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("responseBuilder", GenericResponseService.class).withFactoryMethod(SpringDocWebMvcConfiguration.class, "responseBuilder", OperationService.class, List.class, SpringDocConfigProperties.class, PropertyResolverUtils.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(SpringDocWebMvcConfiguration.class).responseBuilder(attributes.get(0), attributes.get(1), attributes.get(2), attributes.get(3)))).register(beanFactory);
  }
}
