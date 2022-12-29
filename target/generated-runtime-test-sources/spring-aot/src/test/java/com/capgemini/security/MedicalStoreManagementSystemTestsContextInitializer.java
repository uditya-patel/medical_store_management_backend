package com.capgemini.security;

import com.capgemini.jwthelper.JwtUtil;
import com.capgemini.service.CustomerUserDetailsService;
import java.lang.reflect.Field;
import org.springframework.aot.beans.factory.BeanDefinitionRegistrar;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.accept.ContentNegotiationStrategy;

public final class MedicalStoreManagementSystemTestsContextInitializer {
  public static void registerJwtAuthenticationFilter(DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("jwtAuthenticationFilter", JwtAuthenticationFilter.class)
        .instanceSupplier((instanceContext) -> {
          JwtAuthenticationFilter bean = new JwtAuthenticationFilter();
          instanceContext.field("customUserDetailsService", CustomerUserDetailsService.class)
              .invoke(beanFactory, (attributes) -> {
                Field customUserDetailsServiceField = ReflectionUtils.findField(JwtAuthenticationFilter.class, "customUserDetailsService", CustomerUserDetailsService.class);
                ReflectionUtils.makeAccessible(customUserDetailsServiceField);
                ReflectionUtils.setField(customUserDetailsServiceField, bean, attributes.get(0));
              });
                  instanceContext.field("jwtUtil", JwtUtil.class)
                      .invoke(beanFactory, (attributes) -> {
                        Field jwtUtilField = ReflectionUtils.findField(JwtAuthenticationFilter.class, "jwtUtil", JwtUtil.class);
                        ReflectionUtils.makeAccessible(jwtUtilField);
                        ReflectionUtils.setField(jwtUtilField, bean, attributes.get(0));
                      });
                          return bean;
                        }).register(beanFactory);
                  }

                  public static void registerSecurityConfig(
                      DefaultListableBeanFactory beanFactory) {
                    BeanDefinitionRegistrar.of("securityConfig", SecurityConfig.class)
                        .instanceSupplier((instanceContext) -> {
                          SecurityConfig bean = new SecurityConfig();
                          instanceContext.method("setApplicationContext", ApplicationContext.class)
                              .invoke(beanFactory, (attributes) -> bean.setApplicationContext(attributes.get(0)));
                          instanceContext.method("setObjectPostProcessor", ObjectPostProcessor.class)
                              .invoke(beanFactory, (attributes) -> bean.setObjectPostProcessor(attributes.get(0)));
                          instanceContext.method("setAuthenticationConfiguration", AuthenticationConfiguration.class)
                              .invoke(beanFactory, (attributes) -> bean.setAuthenticationConfiguration(attributes.get(0)));
                          instanceContext.method("setContentNegotationStrategy", ContentNegotiationStrategy.class)
                              .resolve(beanFactory, false).ifResolved((attributes) -> bean.setContentNegotationStrategy(attributes.get(0)));
                          instanceContext.method("setTrustResolver", AuthenticationTrustResolver.class)
                              .resolve(beanFactory, false).ifResolved((attributes) -> bean.setTrustResolver(attributes.get(0)));
                          instanceContext.field("customerDetailsService", CustomerUserDetailsService.class)
                              .invoke(beanFactory, (attributes) -> bean.customerDetailsService = attributes.get(0));
                                  instanceContext.field("jwtFilter", JwtAuthenticationFilter.class)
                                      .invoke(beanFactory, (attributes) -> {
                                        Field jwtFilterField = ReflectionUtils.findField(SecurityConfig.class, "jwtFilter", JwtAuthenticationFilter.class);
                                        ReflectionUtils.makeAccessible(jwtFilterField);
                                        ReflectionUtils.setField(jwtFilterField, bean, attributes.get(0));
                                      });
                                          instanceContext.field("entryPoint", JwtAuthenticationEntryPoint.class)
                                              .invoke(beanFactory, (attributes) -> {
                                                Field entryPointField = ReflectionUtils.findField(SecurityConfig.class, "entryPoint", JwtAuthenticationEntryPoint.class);
                                                ReflectionUtils.makeAccessible(entryPointField);
                                                ReflectionUtils.setField(entryPointField, bean, attributes.get(0));
                                              });
                                                  return bean;
                                                }).register(beanFactory);
                                          }
                                        }
