package org.springdoc.core;

import java.util.Optional;
import org.springdoc.core.converters.AdditionalModelsConverter;
import org.springdoc.core.converters.FileSupportConverter;
import org.springdoc.core.converters.ModelConverterRegistrar;
import org.springdoc.core.converters.PageableOpenAPIConverter;
import org.springdoc.core.converters.PolymorphicModelConverter;
import org.springdoc.core.converters.PropertyCustomizingConverter;
import org.springdoc.core.converters.ResponseSupportConverter;
import org.springdoc.core.converters.SchemaPropertyDeprecatingConverter;
import org.springdoc.core.customizers.DelegatingMethodParameterCustomizer;
import org.springdoc.core.providers.SpringDataWebPropertiesProvider;
import org.springdoc.core.providers.SpringWebProvider;
import org.springdoc.core.providers.WebConversionServiceProvider;
import org.springframework.aot.beans.factory.BeanDefinitionRegistrar;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.MessageSource;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

public final class MedicalStoreManagementSystemTestsContextInitializer {
  public static void registerSpringDocConfiguration_SpringDocSpringDataWebPropertiesProvider(
      DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("org.springdoc.core.SpringDocConfiguration$SpringDocSpringDataWebPropertiesProvider", SpringDocConfiguration.SpringDocSpringDataWebPropertiesProvider.class)
        .instanceSupplier(SpringDocConfiguration.SpringDocSpringDataWebPropertiesProvider::new).register(beanFactory);
  }

  public static void registerSpringDocSpringDataWebPropertiesProvider_springDataWebPropertiesProvider(
      DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("springDataWebPropertiesProvider", SpringDataWebPropertiesProvider.class).withFactoryMethod(SpringDocConfiguration.SpringDocSpringDataWebPropertiesProvider.class, "springDataWebPropertiesProvider", Optional.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(SpringDocConfiguration.SpringDocSpringDataWebPropertiesProvider.class).springDataWebPropertiesProvider(attributes.get(0)))).register(beanFactory);
  }

  public static void registerSpringDocConfiguration_SpringDocPageableConfiguration(
      DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("org.springdoc.core.SpringDocConfiguration$SpringDocPageableConfiguration", SpringDocConfiguration.SpringDocPageableConfiguration.class)
        .instanceSupplier(SpringDocConfiguration.SpringDocPageableConfiguration::new).register(beanFactory);
  }

  public static void registerSpringDocPageableConfiguration_pageableOpenAPIConverter(
      DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("pageableOpenAPIConverter", PageableOpenAPIConverter.class).withFactoryMethod(SpringDocConfiguration.SpringDocPageableConfiguration.class, "pageableOpenAPIConverter")
        .instanceSupplier(() -> beanFactory.getBean(SpringDocConfiguration.SpringDocPageableConfiguration.class).pageableOpenAPIConverter()).register(beanFactory);
  }

  public static void registerSpringDocPageableConfiguration_delegatingMethodParameterCustomizer(
      DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("delegatingMethodParameterCustomizer", DelegatingMethodParameterCustomizer.class).withFactoryMethod(SpringDocConfiguration.SpringDocPageableConfiguration.class, "delegatingMethodParameterCustomizer", Optional.class, Optional.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(SpringDocConfiguration.SpringDocPageableConfiguration.class).delegatingMethodParameterCustomizer(attributes.get(0), attributes.get(1)))).register(beanFactory);
  }

  public static void registerSpringDocConfiguration_WebConversionServiceConfiguration(
      DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("org.springdoc.core.SpringDocConfiguration$WebConversionServiceConfiguration", SpringDocConfiguration.WebConversionServiceConfiguration.class)
        .instanceSupplier(SpringDocConfiguration.WebConversionServiceConfiguration::new).register(beanFactory);
  }

  public static void registerWebConversionServiceConfiguration_webConversionServiceProvider(
      DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("webConversionServiceProvider", WebConversionServiceProvider.class).withFactoryMethod(SpringDocConfiguration.WebConversionServiceConfiguration.class, "webConversionServiceProvider", Optional.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(SpringDocConfiguration.WebConversionServiceConfiguration.class).webConversionServiceProvider(attributes.get(0)))).register(beanFactory);
  }

  public static void registerSpringDocConfiguration_OpenApiResourceAdvice(
      DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("org.springdoc.core.SpringDocConfiguration$OpenApiResourceAdvice", SpringDocConfiguration.OpenApiResourceAdvice.class)
        .instanceSupplier(() -> beanFactory.getBean(SpringDocConfiguration.class).new OpenApiResourceAdvice()).register(beanFactory);
  }

  public static void registerSpringDocConfiguration_localSpringDocParameterNameDiscoverer(
      DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("localSpringDocParameterNameDiscoverer", LocalVariableTableParameterNameDiscoverer.class).withFactoryMethod(SpringDocConfiguration.class, "localSpringDocParameterNameDiscoverer")
        .instanceSupplier(() -> beanFactory.getBean(SpringDocConfiguration.class).localSpringDocParameterNameDiscoverer()).register(beanFactory);
  }

  public static void registerSpringDocConfiguration_additionalModelsConverter(
      DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("additionalModelsConverter", AdditionalModelsConverter.class).withFactoryMethod(SpringDocConfiguration.class, "additionalModelsConverter")
        .instanceSupplier(() -> beanFactory.getBean(SpringDocConfiguration.class).additionalModelsConverter()).register(beanFactory);
  }

  public static void registerSpringDocConfiguration_propertyCustomizingConverter(
      DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("propertyCustomizingConverter", PropertyCustomizingConverter.class).withFactoryMethod(SpringDocConfiguration.class, "propertyCustomizingConverter", Optional.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(SpringDocConfiguration.class).propertyCustomizingConverter(attributes.get(0)))).register(beanFactory);
  }

  public static void registerSpringDocConfiguration_fileSupportConverter(
      DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("fileSupportConverter", FileSupportConverter.class).withFactoryMethod(SpringDocConfiguration.class, "fileSupportConverter")
        .instanceSupplier(() -> beanFactory.getBean(SpringDocConfiguration.class).fileSupportConverter()).register(beanFactory);
  }

  public static void registerSpringDocConfiguration_responseSupportConverter(
      DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("responseSupportConverter", ResponseSupportConverter.class).withFactoryMethod(SpringDocConfiguration.class, "responseSupportConverter")
        .instanceSupplier(() -> beanFactory.getBean(SpringDocConfiguration.class).responseSupportConverter()).register(beanFactory);
  }

  public static void registerSpringDocConfiguration_schemaPropertyDeprecatingConverter(
      DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("schemaPropertyDeprecatingConverter", SchemaPropertyDeprecatingConverter.class).withFactoryMethod(SpringDocConfiguration.class, "schemaPropertyDeprecatingConverter")
        .instanceSupplier(() -> beanFactory.getBean(SpringDocConfiguration.class).schemaPropertyDeprecatingConverter()).register(beanFactory);
  }

  public static void registerSpringDocConfiguration_polymorphicModelConverter(
      DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("polymorphicModelConverter", PolymorphicModelConverter.class).withFactoryMethod(SpringDocConfiguration.class, "polymorphicModelConverter")
        .instanceSupplier(() -> beanFactory.getBean(SpringDocConfiguration.class).polymorphicModelConverter()).register(beanFactory);
  }

  public static void registerSpringDocConfiguration_openAPIBuilder(
      DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("openAPIBuilder", OpenAPIService.class).withFactoryMethod(SpringDocConfiguration.class, "openAPIBuilder", Optional.class, SecurityService.class, SpringDocConfigProperties.class, PropertyResolverUtils.class, Optional.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(SpringDocConfiguration.class).openAPIBuilder(attributes.get(0), attributes.get(1), attributes.get(2), attributes.get(3), attributes.get(4)))).register(beanFactory);
  }

  public static void registerSpringDocConfiguration_modelConverterRegistrar(
      DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("modelConverterRegistrar", ModelConverterRegistrar.class).withFactoryMethod(SpringDocConfiguration.class, "modelConverterRegistrar", Optional.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(SpringDocConfiguration.class).modelConverterRegistrar(attributes.get(0)))).register(beanFactory);
  }

  public static void registerSpringDocConfiguration_operationBuilder(
      DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("operationBuilder", OperationService.class).withFactoryMethod(SpringDocConfiguration.class, "operationBuilder", GenericParameterService.class, RequestBodyService.class, SecurityService.class, PropertyResolverUtils.class, Optional.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(SpringDocConfiguration.class).operationBuilder(attributes.get(0), attributes.get(1), attributes.get(2), attributes.get(3), attributes.get(4)))).register(beanFactory);
  }

  public static void registerSpringDocConfiguration_propertyResolverUtils(
      DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("propertyResolverUtils", PropertyResolverUtils.class).withFactoryMethod(SpringDocConfiguration.class, "propertyResolverUtils", ConfigurableBeanFactory.class, MessageSource.class, SpringDocConfigProperties.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(SpringDocConfiguration.class).propertyResolverUtils(attributes.get(0), attributes.get(1), attributes.get(2)))).register(beanFactory);
  }

  public static void registerSpringDocConfiguration_requestBodyBuilder(
      DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("requestBodyBuilder", RequestBodyService.class).withFactoryMethod(SpringDocConfiguration.class, "requestBodyBuilder", GenericParameterService.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(SpringDocConfiguration.class).requestBodyBuilder(attributes.get(0)))).register(beanFactory);
  }

  public static void registerSpringDocConfiguration_securityParser(
      DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("securityParser", SecurityService.class).withFactoryMethod(SpringDocConfiguration.class, "securityParser", PropertyResolverUtils.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(SpringDocConfiguration.class).securityParser(attributes.get(0)))).register(beanFactory);
  }

  public static void registerSpringDocConfiguration_genericReturnTypeParser(
      DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("genericReturnTypeParser", ReturnTypeParser.class).withFactoryMethod(SpringDocConfiguration.class, "genericReturnTypeParser")
        .instanceSupplier(() -> beanFactory.getBean(SpringDocConfiguration.class).genericReturnTypeParser()).register(beanFactory);
  }

  public static void registerSpringDocConfiguration_parameterBuilder(
      DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("parameterBuilder", GenericParameterService.class).withFactoryMethod(SpringDocConfiguration.class, "parameterBuilder", PropertyResolverUtils.class, Optional.class, Optional.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(SpringDocConfiguration.class).parameterBuilder(attributes.get(0), attributes.get(1), attributes.get(2)))).register(beanFactory);
  }

  public static void registerSpringDocConfiguration_springDocProviders(
      DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("springDocProviders", SpringDocProviders.class).withFactoryMethod(SpringDocConfiguration.class, "springDocProviders", Optional.class, Optional.class, Optional.class, Optional.class, Optional.class, SpringWebProvider.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(SpringDocConfiguration.class).springDocProviders(attributes.get(0), attributes.get(1), attributes.get(2), attributes.get(3), attributes.get(4), attributes.get(5)))).register(beanFactory);
  }
}
