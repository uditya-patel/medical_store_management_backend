package com.capgemini.jwthelper.controller;

import com.capgemini.jwthelper.JwtUtil;
import com.capgemini.service.CustomerUserDetailsService;
import com.capgemini.service.ILoginService;
import java.lang.reflect.Field;
import org.springframework.aot.beans.factory.BeanDefinitionRegistrar;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.util.ReflectionUtils;

public final class MedicalStoreManagementSystemTestsContextInitializer {
  public static void registerJwtController(DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("jwtController", JwtController.class)
        .instanceSupplier((instanceContext) -> {
          JwtController bean = new JwtController();
          instanceContext.field("authenticationManager", AuthenticationManager.class)
              .invoke(beanFactory, (attributes) -> {
                Field authenticationManagerField = ReflectionUtils.findField(JwtController.class, "authenticationManager", AuthenticationManager.class);
                ReflectionUtils.makeAccessible(authenticationManagerField);
                ReflectionUtils.setField(authenticationManagerField, bean, attributes.get(0));
              });
                  instanceContext.field("customUserDetailsService", CustomerUserDetailsService.class)
                      .invoke(beanFactory, (attributes) -> {
                        Field customUserDetailsServiceField = ReflectionUtils.findField(JwtController.class, "customUserDetailsService", CustomerUserDetailsService.class);
                        ReflectionUtils.makeAccessible(customUserDetailsServiceField);
                        ReflectionUtils.setField(customUserDetailsServiceField, bean, attributes.get(0));
                      });
                          instanceContext.field("iLoginService", ILoginService.class)
                              .invoke(beanFactory, (attributes) -> {
                                Field iLoginServiceField = ReflectionUtils.findField(JwtController.class, "iLoginService", ILoginService.class);
                                ReflectionUtils.makeAccessible(iLoginServiceField);
                                ReflectionUtils.setField(iLoginServiceField, bean, attributes.get(0));
                              });
                                  instanceContext.field("jwtUtil", JwtUtil.class)
                                      .invoke(beanFactory, (attributes) -> {
                                        Field jwtUtilField = ReflectionUtils.findField(JwtController.class, "jwtUtil", JwtUtil.class);
                                        ReflectionUtils.makeAccessible(jwtUtilField);
                                        ReflectionUtils.setField(jwtUtilField, bean, attributes.get(0));
                                      });
                                          return bean;
                                        }).register(beanFactory);
                                  }
                                }
