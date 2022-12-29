package com.capgemini.service;

import com.capgemini.repository.BillingRepository;
import com.capgemini.repository.ILoginRepository;
import com.capgemini.repository.ProductRepository;
import java.lang.reflect.Field;
import org.springframework.aot.beans.factory.BeanDefinitionRegistrar;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.util.ReflectionUtils;

public final class ContextBootstrapInitializer {
  public static void registerBillingServiceImpl(DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("billingServiceImpl", BillingServiceImpl.class)
        .instanceSupplier((instanceContext) -> {
          BillingServiceImpl bean = new BillingServiceImpl();
          instanceContext.field("billRepository", BillingRepository.class)
              .invoke(beanFactory, (attributes) -> {
                Field billRepositoryField = ReflectionUtils.findField(BillingServiceImpl.class, "billRepository", BillingRepository.class);
                ReflectionUtils.makeAccessible(billRepositoryField);
                ReflectionUtils.setField(billRepositoryField, bean, attributes.get(0));
              });
                  instanceContext.field("productRepository", ProductRepository.class)
                      .invoke(beanFactory, (attributes) -> {
                        Field productRepositoryField = ReflectionUtils.findField(BillingServiceImpl.class, "productRepository", ProductRepository.class);
                        ReflectionUtils.makeAccessible(productRepositoryField);
                        ReflectionUtils.setField(productRepositoryField, bean, attributes.get(0));
                      });
                          return bean;
                        }).register(beanFactory);
                  }

                  public static void registerCustomerUserDetailsService(
                      DefaultListableBeanFactory beanFactory) {
                    BeanDefinitionRegistrar.of("customerUserDetailsService", CustomerUserDetailsService.class)
                        .instanceSupplier((instanceContext) -> {
                          CustomerUserDetailsService bean = new CustomerUserDetailsService();
                          instanceContext.field("loginRepository", ILoginRepository.class)
                              .invoke(beanFactory, (attributes) -> bean.loginRepository = attributes.get(0));
                                  return bean;
                                }).register(beanFactory);
                          }

                          public static void registerLoginServiceImpl(
                              DefaultListableBeanFactory beanFactory) {
                            BeanDefinitionRegistrar.of("loginServiceImpl", LoginServiceImpl.class)
                                .instanceSupplier((instanceContext) -> {
                                  LoginServiceImpl bean = new LoginServiceImpl();
                                  instanceContext.field("loginRepository", ILoginRepository.class)
                                      .invoke(beanFactory, (attributes) -> bean.loginRepository = attributes.get(0));
                                          return bean;
                                        }).register(beanFactory);
                                  }

                                  public static void registerProductServiceImpl(
                                      DefaultListableBeanFactory beanFactory) {
                                    BeanDefinitionRegistrar.of("productServiceImpl", ProductServiceImpl.class)
                                        .instanceSupplier((instanceContext) -> {
                                          ProductServiceImpl bean = new ProductServiceImpl();
                                          instanceContext.field("productRepository", ProductRepository.class)
                                              .invoke(beanFactory, (attributes) -> {
                                                Field productRepositoryField = ReflectionUtils.findField(ProductServiceImpl.class, "productRepository", ProductRepository.class);
                                                ReflectionUtils.makeAccessible(productRepositoryField);
                                                ReflectionUtils.setField(productRepositoryField, bean, attributes.get(0));
                                              });
                                                  instanceContext.field("billRepository", BillingRepository.class)
                                                      .invoke(beanFactory, (attributes) -> {
                                                        Field billRepositoryField = ReflectionUtils.findField(ProductServiceImpl.class, "billRepository", BillingRepository.class);
                                                        ReflectionUtils.makeAccessible(billRepositoryField);
                                                        ReflectionUtils.setField(billRepositoryField, bean, attributes.get(0));
                                                      });
                                                          return bean;
                                                        }).register(beanFactory);
                                                  }
                                                }
