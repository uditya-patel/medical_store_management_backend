package com.capgemini.controller;

import com.capgemini.service.BillingService;
import com.capgemini.service.ILoginService;
import com.capgemini.service.ProductService;
import java.lang.reflect.Field;
import org.springframework.aot.beans.factory.BeanDefinitionRegistrar;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.ReflectionUtils;

public final class ContextBootstrapInitializer {
  public static void registerAdminController(DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("adminController", AdminController.class)
        .instanceSupplier((instanceContext) -> {
          AdminController bean = new AdminController();
          instanceContext.field("bCryptPasswordEncoder", BCryptPasswordEncoder.class)
              .invoke(beanFactory, (attributes) -> {
                Field bCryptPasswordEncoderField = ReflectionUtils.findField(AdminController.class, "bCryptPasswordEncoder", BCryptPasswordEncoder.class);
                ReflectionUtils.makeAccessible(bCryptPasswordEncoderField);
                ReflectionUtils.setField(bCryptPasswordEncoderField, bean, attributes.get(0));
              });
                  instanceContext.field("loginService", ILoginService.class)
                      .invoke(beanFactory, (attributes) -> {
                        Field loginServiceField = ReflectionUtils.findField(AdminController.class, "loginService", ILoginService.class);
                        ReflectionUtils.makeAccessible(loginServiceField);
                        ReflectionUtils.setField(loginServiceField, bean, attributes.get(0));
                      });
                          instanceContext.field("billService", BillingService.class)
                              .invoke(beanFactory, (attributes) -> {
                                Field billServiceField = ReflectionUtils.findField(AdminController.class, "billService", BillingService.class);
                                ReflectionUtils.makeAccessible(billServiceField);
                                ReflectionUtils.setField(billServiceField, bean, attributes.get(0));
                              });
                                  instanceContext.field("productService", ProductService.class)
                                      .invoke(beanFactory, (attributes) -> {
                                        Field productServiceField = ReflectionUtils.findField(AdminController.class, "productService", ProductService.class);
                                        ReflectionUtils.makeAccessible(productServiceField);
                                        ReflectionUtils.setField(productServiceField, bean, attributes.get(0));
                                      });
                                          return bean;
                                        }).register(beanFactory);
                                  }

                                  public static void registerUserController(
                                      DefaultListableBeanFactory beanFactory) {
                                    BeanDefinitionRegistrar.of("userController", UserController.class)
                                        .instanceSupplier((instanceContext) -> {
                                          UserController bean = new UserController();
                                          instanceContext.field("loginService", ILoginService.class)
                                              .invoke(beanFactory, (attributes) -> {
                                                Field loginServiceField = ReflectionUtils.findField(UserController.class, "loginService", ILoginService.class);
                                                ReflectionUtils.makeAccessible(loginServiceField);
                                                ReflectionUtils.setField(loginServiceField, bean, attributes.get(0));
                                              });
                                                  instanceContext.field("bCryptPasswordEncoder", BCryptPasswordEncoder.class)
                                                      .invoke(beanFactory, (attributes) -> {
                                                        Field bCryptPasswordEncoderField = ReflectionUtils.findField(UserController.class, "bCryptPasswordEncoder", BCryptPasswordEncoder.class);
                                                        ReflectionUtils.makeAccessible(bCryptPasswordEncoderField);
                                                        ReflectionUtils.setField(bCryptPasswordEncoderField, bean, attributes.get(0));
                                                      });
                                                          instanceContext.field("billService", BillingService.class)
                                                              .invoke(beanFactory, (attributes) -> {
                                                                Field billServiceField = ReflectionUtils.findField(UserController.class, "billService", BillingService.class);
                                                                ReflectionUtils.makeAccessible(billServiceField);
                                                                ReflectionUtils.setField(billServiceField, bean, attributes.get(0));
                                                              });
                                                                  instanceContext.field("productService", ProductService.class)
                                                                      .invoke(beanFactory, (attributes) -> {
                                                                        Field productServiceField = ReflectionUtils.findField(UserController.class, "productService", ProductService.class);
                                                                        ReflectionUtils.makeAccessible(productServiceField);
                                                                        ReflectionUtils.setField(productServiceField, bean, attributes.get(0));
                                                                      });
                                                                          return bean;
                                                                        }).register(beanFactory);
                                                                  }
                                                                }
