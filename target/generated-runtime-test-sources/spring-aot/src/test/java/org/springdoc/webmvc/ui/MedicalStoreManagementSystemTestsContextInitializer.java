package org.springdoc.webmvc.ui;

import java.lang.String;
import java.lang.reflect.Field;
import java.util.Optional;
import org.springdoc.core.SpringDocConfigProperties;
import org.springdoc.core.SwaggerUiConfigParameters;
import org.springdoc.core.SwaggerUiConfigProperties;
import org.springdoc.core.SwaggerUiOAuthProperties;
import org.springdoc.core.providers.SpringWebProvider;
import org.springframework.aot.beans.factory.BeanDefinitionRegistrar;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.util.ReflectionUtils;

public final class MedicalStoreManagementSystemTestsContextInitializer {
  public static void registerSwaggerConfig_swaggerWelcome(DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("swaggerWelcome", SwaggerWelcomeWebMvc.class).withFactoryMethod(SwaggerConfig.class, "swaggerWelcome", SwaggerUiConfigProperties.class, SpringDocConfigProperties.class, SwaggerUiConfigParameters.class, SpringWebProvider.class)
        .instanceSupplier((instanceContext) -> {
          SwaggerWelcomeWebMvc bean = instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(SwaggerConfig.class).swaggerWelcome(attributes.get(0), attributes.get(1), attributes.get(2), attributes.get(3)));
          instanceContext.field("mvcServletPath", String.class)
              .invoke(beanFactory, (attributes) -> {
                Field mvcServletPathField = ReflectionUtils.findField(SwaggerWelcomeWebMvc.class, "mvcServletPath", String.class);
                ReflectionUtils.makeAccessible(mvcServletPathField);
                ReflectionUtils.setField(mvcServletPathField, bean, attributes.get(0));
              });
                  return bean;
                }).register(beanFactory);
          }

          public static void registerSwaggerConfig_swaggerConfigResource(
              DefaultListableBeanFactory beanFactory) {
            BeanDefinitionRegistrar.of("swaggerConfigResource", SwaggerConfigResource.class).withFactoryMethod(SwaggerConfig.class, "swaggerConfigResource", SwaggerWelcomeCommon.class)
                .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(SwaggerConfig.class).swaggerConfigResource(attributes.get(0)))).register(beanFactory);
          }

          public static void registerSwaggerConfig_indexPageTransformer(
              DefaultListableBeanFactory beanFactory) {
            BeanDefinitionRegistrar.of("indexPageTransformer", SwaggerIndexTransformer.class).withFactoryMethod(SwaggerConfig.class, "indexPageTransformer", SwaggerUiConfigProperties.class, SwaggerUiOAuthProperties.class, SwaggerUiConfigParameters.class, SwaggerWelcomeCommon.class)
                .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(SwaggerConfig.class).indexPageTransformer(attributes.get(0), attributes.get(1), attributes.get(2), attributes.get(3)))).register(beanFactory);
          }

          public static void registerSwaggerConfig_swaggerWebMvcConfigurer(
              DefaultListableBeanFactory beanFactory) {
            BeanDefinitionRegistrar.of("swaggerWebMvcConfigurer", SwaggerWebMvcConfigurer.class).withFactoryMethod(SwaggerConfig.class, "swaggerWebMvcConfigurer", SwaggerUiConfigParameters.class, SwaggerIndexTransformer.class, Optional.class)
                .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(SwaggerConfig.class).swaggerWebMvcConfigurer(attributes.get(0), attributes.get(1), attributes.get(2)))).register(beanFactory);
          }
        }
