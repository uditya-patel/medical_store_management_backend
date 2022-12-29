package org.springframework.aot;

import com.capgemini.MedicalStoreManagementSystem;
import com.capgemini.entity.Billing;
import com.capgemini.entity.Product;
import com.capgemini.entity.User;
import com.capgemini.exception.CustomException;
import com.capgemini.exception.ExceptionHandlerAdvice;
import com.capgemini.exception.GlobalExceptionHandler;
import com.capgemini.jwthelper.JwtUtil;
import com.capgemini.repository.BillingRepository;
import com.capgemini.repository.ILoginRepository;
import com.capgemini.repository.ProductRepository;
import com.capgemini.security.JwtAuthenticationEntryPoint;
import com.capgemini.security.SecurityConfig;
import java.lang.Class;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.validation.Validator;
import org.springdoc.core.SpringDocConfigProperties;
import org.springdoc.core.SpringDocConfiguration;
import org.springdoc.core.SwaggerUiConfigParameters;
import org.springdoc.core.SwaggerUiConfigProperties;
import org.springdoc.core.SwaggerUiOAuthProperties;
import org.springdoc.webmvc.core.SpringDocWebMvcConfiguration;
import org.springdoc.webmvc.ui.SwaggerConfig;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.aot.beans.factory.BeanDefinitionRegistrar;
import org.springframework.aot.context.annotation.ImportAwareBeanPostProcessor;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.LazyInitializationExcludeFilter;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.boot.autoconfigure.availability.ApplicationAvailabilityAutoConfiguration;
import org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration;
import org.springframework.boot.autoconfigure.context.LifecycleAutoConfiguration;
import org.springframework.boot.autoconfigure.context.LifecycleProperties;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.autoconfigure.dao.PersistenceExceptionTranslationAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jdbc.JdbcRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcProperties;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.metadata.DataSourcePoolMetadataProvidersConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationAutoConfiguration;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationProperties;
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskSchedulingProperties;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.boot.autoconfigure.transaction.TransactionProperties;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.web.client.RestTemplateBuilderConfigurer;
import org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.embedded.TomcatWebServerFactoryCustomizer;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryCustomizer;
import org.springframework.boot.autoconfigure.web.servlet.TomcatServletWebServerFactoryCustomizer;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration;
import org.springframework.boot.availability.ApplicationAvailabilityBean;
import org.springframework.boot.context.properties.BoundConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;
import org.springframework.boot.jackson.JsonComponentModule;
import org.springframework.boot.jackson.JsonMixinModule;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.task.TaskExecutorBuilder;
import org.springframework.boot.task.TaskSchedulerBuilder;
import org.springframework.boot.validation.beanvalidation.MethodValidationExcludeFilter;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.ErrorPageRegistrarBeanPostProcessor;
import org.springframework.boot.web.server.WebServerFactoryCustomizerBeanPostProcessor;
import org.springframework.boot.web.servlet.DelegatingFilterProxyRegistrationBean;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.filter.OrderedFormContentFilter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.context.support.DefaultLifecycleProcessor;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.geo.GeoModule;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.data.jdbc.core.convert.DataAccessStrategy;
import org.springframework.data.jdbc.core.convert.JdbcConverter;
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
import org.springframework.data.jdbc.core.convert.RelationResolver;
import org.springframework.data.jdbc.core.mapping.JdbcMappingContext;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jpa.repository.config.JpaMetamodelMappingContextFactoryBean;
import org.springframework.data.jpa.repository.query.JpaQueryMethodFactory;
import org.springframework.data.jpa.repository.support.DefaultJpaContext;
import org.springframework.data.jpa.repository.support.EntityManagerBeanDefinitionRegistrarPostProcessor;
import org.springframework.data.jpa.repository.support.JpaEvaluationContextExtension;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.relational.core.dialect.Dialect;
import org.springframework.data.repository.core.support.PropertiesBasedNamedQueries;
import org.springframework.data.repository.core.support.RepositoryFragmentsFactoryBean;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;
import org.springframework.data.web.config.ProjectingArgumentResolverRegistrar;
import org.springframework.data.web.config.SortHandlerMethodArgumentResolverCustomizer;
import org.springframework.data.web.config.SpringDataJacksonConfiguration;
import org.springframework.data.web.config.SpringDataWebConfiguration;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.SharedEntityManagerCreator;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.configuration.ObjectPostProcessorConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.context.DelegatingApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.WebInvocationPrivilegeEvaluator;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.AbstractTransactionManagementConfiguration;
import org.springframework.transaction.annotation.ProxyTransactionManagementConfiguration;
import org.springframework.transaction.event.TransactionalEventListenerFactory;
import org.springframework.transaction.interceptor.BeanFactoryTransactionAttributeSourceAdvisor;
import org.springframework.transaction.interceptor.TransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.PathMatcher;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.RequestContextFilter;
import org.springframework.web.method.support.CompositeUriComponentsContributor;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.RequestToViewNameTranslator;
import org.springframework.web.servlet.ThemeResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.function.support.HandlerFunctionAdapter;
import org.springframework.web.servlet.function.support.RouterFunctionMapping;
import org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter;
import org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.resource.ResourceUrlProvider;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.util.UrlPathHelper;
import org.springframework.web.util.pattern.PathPatternParser;

/**
 * AOT generated context for {@code MedicalStoreManagementSystemTests}.
 */
public class MedicalStoreManagementSystemTestsContextInitializer implements ApplicationContextInitializer<GenericApplicationContext> {
  private ImportAwareBeanPostProcessor createImportAwareBeanPostProcessor() {
    Map<String, String> mappings = new LinkedHashMap<>();
    mappings.put("org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration", "com.capgemini.security.SecurityConfig");
    mappings.put("org.springframework.transaction.annotation.ProxyTransactionManagementConfiguration", "org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration$EnableTransactionManagementConfiguration$JdkDynamicAutoProxyConfiguration");
    return new ImportAwareBeanPostProcessor(mappings);
  }

  @Override
  public void initialize(GenericApplicationContext context) {
    // infrastructure
    DefaultListableBeanFactory beanFactory = context.getDefaultListableBeanFactory();
    beanFactory.setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver());
    beanFactory.addBeanPostProcessor(createImportAwareBeanPostProcessor());

    BeanDefinitionRegistrar.of("org.springframework.context.annotation.internalPersistenceAnnotationProcessor", PersistenceAnnotationBeanPostProcessor.class)
        .instanceSupplier(PersistenceAnnotationBeanPostProcessor::new).customize((bd) -> bd.setRole(2)).register(beanFactory);
    BeanDefinitionRegistrar.of("medicalStoreManagementSystem", MedicalStoreManagementSystem.class)
        .instanceSupplier(MedicalStoreManagementSystem::new).register(beanFactory);
    com.capgemini.controller.MedicalStoreManagementSystemTestsContextInitializer.registerAdminController(beanFactory);
    com.capgemini.controller.MedicalStoreManagementSystemTestsContextInitializer.registerUserController(beanFactory);
    BeanDefinitionRegistrar.of("billing", Billing.class)
        .instanceSupplier(() -> new Billing()).register(beanFactory);
    BeanDefinitionRegistrar.of("customException", CustomException.class)
        .instanceSupplier(CustomException::new).register(beanFactory);
    BeanDefinitionRegistrar.of("exceptionHandlerAdvice", ExceptionHandlerAdvice.class)
        .instanceSupplier(ExceptionHandlerAdvice::new).register(beanFactory);
    BeanDefinitionRegistrar.of("globalExceptionHandler", GlobalExceptionHandler.class)
        .instanceSupplier(GlobalExceptionHandler::new).register(beanFactory);
    BeanDefinitionRegistrar.of("jwtUtil", JwtUtil.class)
        .instanceSupplier(JwtUtil::new).register(beanFactory);
    com.capgemini.jwthelper.controller.MedicalStoreManagementSystemTestsContextInitializer.registerJwtController(beanFactory);
    BeanDefinitionRegistrar.of("jwtAuthenticationEntryPoint", JwtAuthenticationEntryPoint.class)
        .instanceSupplier(JwtAuthenticationEntryPoint::new).register(beanFactory);
    com.capgemini.security.MedicalStoreManagementSystemTestsContextInitializer.registerJwtAuthenticationFilter(beanFactory);
    com.capgemini.security.MedicalStoreManagementSystemTestsContextInitializer.registerSecurityConfig(beanFactory);
    com.capgemini.service.MedicalStoreManagementSystemTestsContextInitializer.registerBillingServiceImpl(beanFactory);
    com.capgemini.service.MedicalStoreManagementSystemTestsContextInitializer.registerCustomerUserDetailsService(beanFactory);
    com.capgemini.service.MedicalStoreManagementSystemTestsContextInitializer.registerLoginServiceImpl(beanFactory);
    com.capgemini.service.MedicalStoreManagementSystemTestsContextInitializer.registerProductServiceImpl(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.security.config.annotation.configuration.ObjectPostProcessorConfiguration", ObjectPostProcessorConfiguration.class)
        .instanceSupplier(ObjectPostProcessorConfiguration::new).customize((bd) -> bd.setRole(2)).register(beanFactory);
    BeanDefinitionRegistrar.of("objectPostProcessor", ResolvableType.forClassWithGenerics(ObjectPostProcessor.class, Object.class)).withFactoryMethod(ObjectPostProcessorConfiguration.class, "objectPostProcessor", AutowireCapableBeanFactory.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(ObjectPostProcessorConfiguration.class).objectPostProcessor(attributes.get(0)))).customize((bd) -> bd.setRole(2)).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration", AuthenticationConfiguration.class)
        .instanceSupplier((instanceContext) -> {
          AuthenticationConfiguration bean = new AuthenticationConfiguration();
          instanceContext.method("setApplicationContext", ApplicationContext.class)
              .invoke(beanFactory, (attributes) -> bean.setApplicationContext(attributes.get(0)));
          instanceContext.method("setObjectPostProcessor", ObjectPostProcessor.class)
              .invoke(beanFactory, (attributes) -> bean.setObjectPostProcessor(attributes.get(0)));
          instanceContext.method("setGlobalAuthenticationConfigurers", List.class)
              .resolve(beanFactory, false).ifResolved((attributes) -> bean.setGlobalAuthenticationConfigurers(attributes.get(0)));
          return bean;
        }).register(beanFactory);
    BeanDefinitionRegistrar.of("authenticationManagerBuilder", AuthenticationManagerBuilder.class).withFactoryMethod(AuthenticationConfiguration.class, "authenticationManagerBuilder", ObjectPostProcessor.class, ApplicationContext.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(AuthenticationConfiguration.class).authenticationManagerBuilder(attributes.get(0), attributes.get(1)))).register(beanFactory);
    BeanDefinitionRegistrar.of("enableGlobalAuthenticationAutowiredConfigurer", GlobalAuthenticationConfigurerAdapter.class).withFactoryMethod(AuthenticationConfiguration.class, "enableGlobalAuthenticationAutowiredConfigurer", ApplicationContext.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> AuthenticationConfiguration.enableGlobalAuthenticationAutowiredConfigurer(attributes.get(0)))).register(beanFactory);
    org.springframework.security.config.annotation.authentication.configuration.MedicalStoreManagementSystemTestsContextInitializer.registerAuthenticationConfiguration_initializeUserDetailsBeanManagerConfigurer(beanFactory);
    org.springframework.security.config.annotation.authentication.configuration.MedicalStoreManagementSystemTestsContextInitializer.registerAuthenticationConfiguration_initializeAuthenticationProviderBeanManagerConfigurer(beanFactory);
    org.springframework.security.config.annotation.web.configuration.MedicalStoreManagementSystemTestsContextInitializer.registerWebSecurityConfiguration(beanFactory);
    BeanDefinitionRegistrar.of("delegatingApplicationListener", DelegatingApplicationListener.class).withFactoryMethod(WebSecurityConfiguration.class, "delegatingApplicationListener")
        .instanceSupplier(() -> WebSecurityConfiguration.delegatingApplicationListener()).register(beanFactory);
    BeanDefinitionRegistrar.of("webSecurityExpressionHandler", ResolvableType.forClassWithGenerics(SecurityExpressionHandler.class, FilterInvocation.class)).withFactoryMethod(WebSecurityConfiguration.class, "webSecurityExpressionHandler")
        .instanceSupplier(() -> beanFactory.getBean(WebSecurityConfiguration.class).webSecurityExpressionHandler()).customize((bd) -> bd.setDependsOn(new String[] { "springSecurityFilterChain" })).register(beanFactory);
    BeanDefinitionRegistrar.of("springSecurityFilterChain", Filter.class).withFactoryMethod(WebSecurityConfiguration.class, "springSecurityFilterChain")
        .instanceSupplier(() -> beanFactory.getBean(WebSecurityConfiguration.class).springSecurityFilterChain()).register(beanFactory);
    BeanDefinitionRegistrar.of("privilegeEvaluator", WebInvocationPrivilegeEvaluator.class).withFactoryMethod(WebSecurityConfiguration.class, "privilegeEvaluator")
        .instanceSupplier(() -> beanFactory.getBean(WebSecurityConfiguration.class).privilegeEvaluator()).customize((bd) -> bd.setDependsOn(new String[] { "springSecurityFilterChain" })).register(beanFactory);
    BeanDefinitionRegistrar.of("conversionServicePostProcessor", BeanFactoryPostProcessor.class).withFactoryMethod(WebSecurityConfiguration.class, "conversionServicePostProcessor")
        .instanceSupplier(() -> WebSecurityConfiguration.conversionServicePostProcessor()).register(beanFactory);
    org.springframework.security.config.annotation.web.configuration.MedicalStoreManagementSystemTestsContextInitializer.registerWebMvcSecurityConfiguration(beanFactory);
    org.springframework.security.config.annotation.web.configuration.MedicalStoreManagementSystemTestsContextInitializer.registerWebMvcSecurityConfiguration_requestDataValueProcessor(beanFactory);
    org.springframework.security.config.annotation.web.configuration.MedicalStoreManagementSystemTestsContextInitializer.registerHttpSecurityConfiguration(beanFactory);
    org.springframework.security.config.annotation.web.configuration.MedicalStoreManagementSystemTestsContextInitializer.registerHttpSecurityConfiguration_httpSecurity(beanFactory);
    BeanDefinitionRegistrar.of("passwordEncoder", BCryptPasswordEncoder.class).withFactoryMethod(SecurityConfig.class, "passwordEncoder")
        .instanceSupplier(() -> beanFactory.getBean(SecurityConfig.class).passwordEncoder()).register(beanFactory);
    BeanDefinitionRegistrar.of("authenticationManagerBean", AuthenticationManager.class).withFactoryMethod(SecurityConfig.class, "authenticationManagerBean")
        .instanceSupplier(() -> beanFactory.getBean(SecurityConfig.class).authenticationManagerBean()).register(beanFactory);
    org.springframework.boot.autoconfigure.MedicalStoreManagementSystemTestsContextInitializer.registerAutoConfigurationPackages_BasePackages(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration", PropertyPlaceholderAutoConfiguration.class)
        .instanceSupplier(PropertyPlaceholderAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("propertySourcesPlaceholderConfigurer", PropertySourcesPlaceholderConfigurer.class).withFactoryMethod(PropertyPlaceholderAutoConfiguration.class, "propertySourcesPlaceholderConfigurer")
        .instanceSupplier(() -> PropertyPlaceholderAutoConfiguration.propertySourcesPlaceholderConfigurer()).register(beanFactory);
    org.springframework.boot.autoconfigure.jackson.MedicalStoreManagementSystemTestsContextInitializer.registerJacksonAutoConfiguration_Jackson2ObjectMapperBuilderCustomizerConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.jackson.MedicalStoreManagementSystemTestsContextInitializer.registerJackson2ObjectMapperBuilderCustomizerConfiguration_standardJacksonObjectMapperBuilderCustomizer(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor", ConfigurationPropertiesBindingPostProcessor.class)
        .instanceSupplier(ConfigurationPropertiesBindingPostProcessor::new).customize((bd) -> bd.setRole(2)).register(beanFactory);
    org.springframework.boot.context.properties.MedicalStoreManagementSystemTestsContextInitializer.registerConfigurationPropertiesBinder_Factory(beanFactory);
    org.springframework.boot.context.properties.MedicalStoreManagementSystemTestsContextInitializer.registerConfigurationPropertiesBinder(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.context.properties.BoundConfigurationProperties", BoundConfigurationProperties.class)
        .instanceSupplier(BoundConfigurationProperties::new).customize((bd) -> bd.setRole(2)).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.context.properties.EnableConfigurationPropertiesRegistrar.methodValidationExcludeFilter", MethodValidationExcludeFilter.class)
        .instanceSupplier(() -> MethodValidationExcludeFilter.byAnnotation(ConfigurationProperties.class)).customize((bd) -> bd.setRole(2)).register(beanFactory);
    BeanDefinitionRegistrar.of("spring.jackson-org.springframework.boot.autoconfigure.jackson.JacksonProperties", JacksonProperties.class)
        .instanceSupplier(JacksonProperties::new).register(beanFactory);
    org.springframework.boot.autoconfigure.jackson.MedicalStoreManagementSystemTestsContextInitializer.registerJacksonAutoConfiguration_JacksonObjectMapperBuilderConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.jackson.MedicalStoreManagementSystemTestsContextInitializer.registerJacksonObjectMapperBuilderConfiguration_jacksonObjectMapperBuilder(beanFactory);
    org.springframework.boot.autoconfigure.jackson.MedicalStoreManagementSystemTestsContextInitializer.registerJacksonAutoConfiguration_ParameterNamesModuleConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.jackson.MedicalStoreManagementSystemTestsContextInitializer.registerParameterNamesModuleConfiguration_parameterNamesModule(beanFactory);
    org.springframework.boot.autoconfigure.jackson.MedicalStoreManagementSystemTestsContextInitializer.registerJacksonAutoConfiguration_JacksonObjectMapperConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.jackson.MedicalStoreManagementSystemTestsContextInitializer.registerJacksonObjectMapperConfiguration_jacksonObjectMapper(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration", JacksonAutoConfiguration.class)
        .instanceSupplier(JacksonAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("jsonComponentModule", JsonComponentModule.class).withFactoryMethod(JacksonAutoConfiguration.class, "jsonComponentModule")
        .instanceSupplier(() -> beanFactory.getBean(JacksonAutoConfiguration.class).jsonComponentModule()).register(beanFactory);
    BeanDefinitionRegistrar.of("jsonMixinModule", JsonMixinModule.class).withFactoryMethod(JacksonAutoConfiguration.class, "jsonMixinModule", ApplicationContext.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(JacksonAutoConfiguration.class).jsonMixinModule(attributes.get(0)))).register(beanFactory);
    org.springframework.boot.autoconfigure.websocket.servlet.MedicalStoreManagementSystemTestsContextInitializer.registerWebSocketServletAutoConfiguration_TomcatWebSocketConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.websocket.servlet.MedicalStoreManagementSystemTestsContextInitializer.registerTomcatWebSocketConfiguration_websocketServletWebServerCustomizer(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration", WebSocketServletAutoConfiguration.class)
        .instanceSupplier(WebSocketServletAutoConfiguration::new).register(beanFactory);
    org.springframework.boot.autoconfigure.web.servlet.MedicalStoreManagementSystemTestsContextInitializer.registerServletWebServerFactoryConfiguration_EmbeddedTomcat(beanFactory);
    org.springframework.boot.autoconfigure.web.servlet.MedicalStoreManagementSystemTestsContextInitializer.registerEmbeddedTomcat_tomcatServletWebServerFactory(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration", ServletWebServerFactoryAutoConfiguration.class)
        .instanceSupplier(ServletWebServerFactoryAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("servletWebServerFactoryCustomizer", ServletWebServerFactoryCustomizer.class).withFactoryMethod(ServletWebServerFactoryAutoConfiguration.class, "servletWebServerFactoryCustomizer", ServerProperties.class, ObjectProvider.class, ObjectProvider.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(ServletWebServerFactoryAutoConfiguration.class).servletWebServerFactoryCustomizer(attributes.get(0), attributes.get(1), attributes.get(2)))).register(beanFactory);
    BeanDefinitionRegistrar.of("tomcatServletWebServerFactoryCustomizer", TomcatServletWebServerFactoryCustomizer.class).withFactoryMethod(ServletWebServerFactoryAutoConfiguration.class, "tomcatServletWebServerFactoryCustomizer", ServerProperties.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(ServletWebServerFactoryAutoConfiguration.class).tomcatServletWebServerFactoryCustomizer(attributes.get(0)))).register(beanFactory);
    BeanDefinitionRegistrar.of("server-org.springframework.boot.autoconfigure.web.ServerProperties", ServerProperties.class)
        .instanceSupplier(ServerProperties::new).register(beanFactory);
    BeanDefinitionRegistrar.of("webServerFactoryCustomizerBeanPostProcessor", WebServerFactoryCustomizerBeanPostProcessor.class)
        .instanceSupplier(WebServerFactoryCustomizerBeanPostProcessor::new).customize((bd) -> bd.setSynthetic(true)).register(beanFactory);
    BeanDefinitionRegistrar.of("errorPageRegistrarBeanPostProcessor", ErrorPageRegistrarBeanPostProcessor.class)
        .instanceSupplier(ErrorPageRegistrarBeanPostProcessor::new).customize((bd) -> bd.setSynthetic(true)).register(beanFactory);
    org.springframework.boot.autoconfigure.web.servlet.MedicalStoreManagementSystemTestsContextInitializer.registerDispatcherServletAutoConfiguration_DispatcherServletConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.web.servlet.MedicalStoreManagementSystemTestsContextInitializer.registerDispatcherServletConfiguration_dispatcherServlet(beanFactory);
    BeanDefinitionRegistrar.of("spring.mvc-org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties", WebMvcProperties.class)
        .instanceSupplier(WebMvcProperties::new).register(beanFactory);
    org.springframework.boot.autoconfigure.web.servlet.MedicalStoreManagementSystemTestsContextInitializer.registerDispatcherServletAutoConfiguration_DispatcherServletRegistrationConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.web.servlet.MedicalStoreManagementSystemTestsContextInitializer.registerDispatcherServletRegistrationConfiguration_dispatcherServletRegistration(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration", DispatcherServletAutoConfiguration.class)
        .instanceSupplier(DispatcherServletAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration", TaskExecutionAutoConfiguration.class)
        .instanceSupplier(TaskExecutionAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("taskExecutorBuilder", TaskExecutorBuilder.class).withFactoryMethod(TaskExecutionAutoConfiguration.class, "taskExecutorBuilder", TaskExecutionProperties.class, ObjectProvider.class, ObjectProvider.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(TaskExecutionAutoConfiguration.class).taskExecutorBuilder(attributes.get(0), attributes.get(1), attributes.get(2)))).register(beanFactory);
    BeanDefinitionRegistrar.of("applicationTaskExecutor", ThreadPoolTaskExecutor.class).withFactoryMethod(TaskExecutionAutoConfiguration.class, "applicationTaskExecutor", TaskExecutorBuilder.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(TaskExecutionAutoConfiguration.class).applicationTaskExecutor(attributes.get(0)))).customize((bd) -> bd.setLazyInit(true)).register(beanFactory);
    BeanDefinitionRegistrar.of("spring.task.execution-org.springframework.boot.autoconfigure.task.TaskExecutionProperties", TaskExecutionProperties.class)
        .instanceSupplier(TaskExecutionProperties::new).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration", ValidationAutoConfiguration.class)
        .instanceSupplier(ValidationAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("defaultValidator", LocalValidatorFactoryBean.class).withFactoryMethod(ValidationAutoConfiguration.class, "defaultValidator", ApplicationContext.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> ValidationAutoConfiguration.defaultValidator(attributes.get(0)))).customize((bd) -> {
      bd.setPrimary(true);
      bd.setRole(2);
    }).register(beanFactory);
    BeanDefinitionRegistrar.of("methodValidationPostProcessor", MethodValidationPostProcessor.class).withFactoryMethod(ValidationAutoConfiguration.class, "methodValidationPostProcessor", Environment.class, Validator.class, ObjectProvider.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> ValidationAutoConfiguration.methodValidationPostProcessor(attributes.get(0), attributes.get(1), attributes.get(2)))).register(beanFactory);
    org.springframework.boot.autoconfigure.web.servlet.error.MedicalStoreManagementSystemTestsContextInitializer.registerErrorMvcAutoConfiguration_WhitelabelErrorViewConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.web.servlet.error.MedicalStoreManagementSystemTestsContextInitializer.registerWhitelabelErrorViewConfiguration_error(beanFactory);
    org.springframework.boot.autoconfigure.web.servlet.error.MedicalStoreManagementSystemTestsContextInitializer.registerWhitelabelErrorViewConfiguration_beanNameViewResolver(beanFactory);
    org.springframework.boot.autoconfigure.web.servlet.error.MedicalStoreManagementSystemTestsContextInitializer.registerErrorMvcAutoConfiguration_DefaultErrorViewResolverConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.web.servlet.error.MedicalStoreManagementSystemTestsContextInitializer.registerDefaultErrorViewResolverConfiguration_conventionErrorViewResolver(beanFactory);
    BeanDefinitionRegistrar.of("spring.web-org.springframework.boot.autoconfigure.web.WebProperties", WebProperties.class)
        .instanceSupplier(WebProperties::new).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration", ErrorMvcAutoConfiguration.class).withConstructor(ServerProperties.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> new ErrorMvcAutoConfiguration(attributes.get(0)))).register(beanFactory);
    BeanDefinitionRegistrar.of("errorAttributes", DefaultErrorAttributes.class).withFactoryMethod(ErrorMvcAutoConfiguration.class, "errorAttributes")
        .instanceSupplier(() -> beanFactory.getBean(ErrorMvcAutoConfiguration.class).errorAttributes()).register(beanFactory);
    BeanDefinitionRegistrar.of("basicErrorController", BasicErrorController.class).withFactoryMethod(ErrorMvcAutoConfiguration.class, "basicErrorController", ErrorAttributes.class, ObjectProvider.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(ErrorMvcAutoConfiguration.class).basicErrorController(attributes.get(0), attributes.get(1)))).register(beanFactory);
    org.springframework.boot.autoconfigure.web.servlet.error.MedicalStoreManagementSystemTestsContextInitializer.registerErrorMvcAutoConfiguration_errorPageCustomizer(beanFactory);
    org.springframework.boot.autoconfigure.web.servlet.error.MedicalStoreManagementSystemTestsContextInitializer.registerErrorMvcAutoConfiguration_preserveErrorControllerTargetClassPostProcessor(beanFactory);
    org.springframework.boot.autoconfigure.web.servlet.MedicalStoreManagementSystemTestsContextInitializer.registerWebMvcAutoConfiguration_EnableWebMvcConfiguration(beanFactory);
    BeanDefinitionRegistrar.of("requestMappingHandlerAdapter", RequestMappingHandlerAdapter.class).withFactoryMethod(WebMvcAutoConfiguration.EnableWebMvcConfiguration.class, "requestMappingHandlerAdapter", ContentNegotiationManager.class, FormattingConversionService.class, org.springframework.validation.Validator.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(WebMvcAutoConfiguration.EnableWebMvcConfiguration.class).requestMappingHandlerAdapter(attributes.get(0), attributes.get(1), attributes.get(2)))).register(beanFactory);
    org.springframework.boot.autoconfigure.web.servlet.MedicalStoreManagementSystemTestsContextInitializer.registerEnableWebMvcConfiguration_welcomePageHandlerMapping(beanFactory);
    BeanDefinitionRegistrar.of("localeResolver", LocaleResolver.class).withFactoryMethod(WebMvcAutoConfiguration.EnableWebMvcConfiguration.class, "localeResolver")
        .instanceSupplier(() -> beanFactory.getBean(WebMvcAutoConfiguration.EnableWebMvcConfiguration.class).localeResolver()).register(beanFactory);
    BeanDefinitionRegistrar.of("themeResolver", ThemeResolver.class).withFactoryMethod(WebMvcAutoConfiguration.EnableWebMvcConfiguration.class, "themeResolver")
        .instanceSupplier(() -> beanFactory.getBean(WebMvcAutoConfiguration.EnableWebMvcConfiguration.class).themeResolver()).register(beanFactory);
    BeanDefinitionRegistrar.of("flashMapManager", FlashMapManager.class).withFactoryMethod(WebMvcAutoConfiguration.EnableWebMvcConfiguration.class, "flashMapManager")
        .instanceSupplier(() -> beanFactory.getBean(WebMvcAutoConfiguration.EnableWebMvcConfiguration.class).flashMapManager()).register(beanFactory);
    BeanDefinitionRegistrar.of("mvcConversionService", FormattingConversionService.class).withFactoryMethod(WebMvcAutoConfiguration.EnableWebMvcConfiguration.class, "mvcConversionService")
        .instanceSupplier(() -> beanFactory.getBean(WebMvcAutoConfiguration.EnableWebMvcConfiguration.class).mvcConversionService()).register(beanFactory);
    BeanDefinitionRegistrar.of("mvcValidator", org.springframework.validation.Validator.class).withFactoryMethod(WebMvcAutoConfiguration.EnableWebMvcConfiguration.class, "mvcValidator")
        .instanceSupplier(() -> beanFactory.getBean(WebMvcAutoConfiguration.EnableWebMvcConfiguration.class).mvcValidator()).register(beanFactory);
    BeanDefinitionRegistrar.of("mvcContentNegotiationManager", ContentNegotiationManager.class).withFactoryMethod(WebMvcAutoConfiguration.EnableWebMvcConfiguration.class, "mvcContentNegotiationManager")
        .instanceSupplier(() -> beanFactory.getBean(WebMvcAutoConfiguration.EnableWebMvcConfiguration.class).mvcContentNegotiationManager()).register(beanFactory);
    BeanDefinitionRegistrar.of("requestMappingHandlerMapping", RequestMappingHandlerMapping.class).withFactoryMethod(WebMvcConfigurationSupport.class, "requestMappingHandlerMapping", ContentNegotiationManager.class, FormattingConversionService.class, ResourceUrlProvider.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(WebMvcConfigurationSupport.class).requestMappingHandlerMapping(attributes.get(0), attributes.get(1), attributes.get(2)))).register(beanFactory);
    BeanDefinitionRegistrar.of("mvcPatternParser", PathPatternParser.class).withFactoryMethod(WebMvcConfigurationSupport.class, "mvcPatternParser")
        .instanceSupplier(() -> beanFactory.getBean(WebMvcConfigurationSupport.class).mvcPatternParser()).register(beanFactory);
    BeanDefinitionRegistrar.of("mvcUrlPathHelper", UrlPathHelper.class).withFactoryMethod(WebMvcConfigurationSupport.class, "mvcUrlPathHelper")
        .instanceSupplier(() -> beanFactory.getBean(WebMvcConfigurationSupport.class).mvcUrlPathHelper()).register(beanFactory);
    BeanDefinitionRegistrar.of("mvcPathMatcher", PathMatcher.class).withFactoryMethod(WebMvcConfigurationSupport.class, "mvcPathMatcher")
        .instanceSupplier(() -> beanFactory.getBean(WebMvcConfigurationSupport.class).mvcPathMatcher()).register(beanFactory);
    BeanDefinitionRegistrar.of("viewControllerHandlerMapping", HandlerMapping.class).withFactoryMethod(WebMvcConfigurationSupport.class, "viewControllerHandlerMapping", FormattingConversionService.class, ResourceUrlProvider.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(WebMvcConfigurationSupport.class).viewControllerHandlerMapping(attributes.get(0), attributes.get(1)))).register(beanFactory);
    BeanDefinitionRegistrar.of("beanNameHandlerMapping", BeanNameUrlHandlerMapping.class).withFactoryMethod(WebMvcConfigurationSupport.class, "beanNameHandlerMapping", FormattingConversionService.class, ResourceUrlProvider.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(WebMvcConfigurationSupport.class).beanNameHandlerMapping(attributes.get(0), attributes.get(1)))).register(beanFactory);
    BeanDefinitionRegistrar.of("routerFunctionMapping", RouterFunctionMapping.class).withFactoryMethod(WebMvcConfigurationSupport.class, "routerFunctionMapping", FormattingConversionService.class, ResourceUrlProvider.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(WebMvcConfigurationSupport.class).routerFunctionMapping(attributes.get(0), attributes.get(1)))).register(beanFactory);
    BeanDefinitionRegistrar.of("resourceHandlerMapping", HandlerMapping.class).withFactoryMethod(WebMvcConfigurationSupport.class, "resourceHandlerMapping", ContentNegotiationManager.class, FormattingConversionService.class, ResourceUrlProvider.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(WebMvcConfigurationSupport.class).resourceHandlerMapping(attributes.get(0), attributes.get(1), attributes.get(2)))).register(beanFactory);
    BeanDefinitionRegistrar.of("mvcResourceUrlProvider", ResourceUrlProvider.class).withFactoryMethod(WebMvcConfigurationSupport.class, "mvcResourceUrlProvider")
        .instanceSupplier(() -> beanFactory.getBean(WebMvcConfigurationSupport.class).mvcResourceUrlProvider()).register(beanFactory);
    BeanDefinitionRegistrar.of("defaultServletHandlerMapping", HandlerMapping.class).withFactoryMethod(WebMvcConfigurationSupport.class, "defaultServletHandlerMapping")
        .instanceSupplier(() -> beanFactory.getBean(WebMvcConfigurationSupport.class).defaultServletHandlerMapping()).register(beanFactory);
    BeanDefinitionRegistrar.of("handlerFunctionAdapter", HandlerFunctionAdapter.class).withFactoryMethod(WebMvcConfigurationSupport.class, "handlerFunctionAdapter")
        .instanceSupplier(() -> beanFactory.getBean(WebMvcConfigurationSupport.class).handlerFunctionAdapter()).register(beanFactory);
    BeanDefinitionRegistrar.of("mvcUriComponentsContributor", CompositeUriComponentsContributor.class).withFactoryMethod(WebMvcConfigurationSupport.class, "mvcUriComponentsContributor", FormattingConversionService.class, RequestMappingHandlerAdapter.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(WebMvcConfigurationSupport.class).mvcUriComponentsContributor(attributes.get(0), attributes.get(1)))).register(beanFactory);
    BeanDefinitionRegistrar.of("httpRequestHandlerAdapter", HttpRequestHandlerAdapter.class).withFactoryMethod(WebMvcConfigurationSupport.class, "httpRequestHandlerAdapter")
        .instanceSupplier(() -> beanFactory.getBean(WebMvcConfigurationSupport.class).httpRequestHandlerAdapter()).register(beanFactory);
    BeanDefinitionRegistrar.of("simpleControllerHandlerAdapter", SimpleControllerHandlerAdapter.class).withFactoryMethod(WebMvcConfigurationSupport.class, "simpleControllerHandlerAdapter")
        .instanceSupplier(() -> beanFactory.getBean(WebMvcConfigurationSupport.class).simpleControllerHandlerAdapter()).register(beanFactory);
    BeanDefinitionRegistrar.of("handlerExceptionResolver", HandlerExceptionResolver.class).withFactoryMethod(WebMvcConfigurationSupport.class, "handlerExceptionResolver", ContentNegotiationManager.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(WebMvcConfigurationSupport.class).handlerExceptionResolver(attributes.get(0)))).register(beanFactory);
    BeanDefinitionRegistrar.of("mvcViewResolver", ViewResolver.class).withFactoryMethod(WebMvcConfigurationSupport.class, "mvcViewResolver", ContentNegotiationManager.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(WebMvcConfigurationSupport.class).mvcViewResolver(attributes.get(0)))).register(beanFactory);
    BeanDefinitionRegistrar.of("mvcHandlerMappingIntrospector", HandlerMappingIntrospector.class).withFactoryMethod(WebMvcConfigurationSupport.class, "mvcHandlerMappingIntrospector")
        .instanceSupplier(() -> beanFactory.getBean(WebMvcConfigurationSupport.class).mvcHandlerMappingIntrospector()).customize((bd) -> bd.setLazyInit(true)).register(beanFactory);
    BeanDefinitionRegistrar.of("viewNameTranslator", RequestToViewNameTranslator.class).withFactoryMethod(WebMvcConfigurationSupport.class, "viewNameTranslator")
        .instanceSupplier(() -> beanFactory.getBean(WebMvcConfigurationSupport.class).viewNameTranslator()).register(beanFactory);
    org.springframework.boot.autoconfigure.web.servlet.MedicalStoreManagementSystemTestsContextInitializer.registerWebMvcAutoConfiguration_WebMvcAutoConfigurationAdapter(beanFactory);
    BeanDefinitionRegistrar.of("defaultViewResolver", InternalResourceViewResolver.class).withFactoryMethod(WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter.class, "defaultViewResolver")
        .instanceSupplier(() -> beanFactory.getBean(WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter.class).defaultViewResolver()).register(beanFactory);
    BeanDefinitionRegistrar.of("viewResolver", ContentNegotiatingViewResolver.class).withFactoryMethod(WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter.class, "viewResolver", BeanFactory.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter.class).viewResolver(attributes.get(0)))).register(beanFactory);
    BeanDefinitionRegistrar.of("requestContextFilter", RequestContextFilter.class).withFactoryMethod(WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter.class, "requestContextFilter")
        .instanceSupplier(() -> WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter.requestContextFilter()).register(beanFactory);
    org.springframework.boot.autoconfigure.web.servlet.MedicalStoreManagementSystemTestsContextInitializer.registerWebMvcAutoConfiguration_ResourceChainCustomizerConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.web.servlet.MedicalStoreManagementSystemTestsContextInitializer.registerResourceChainCustomizerConfiguration_resourceHandlerRegistrationCustomizer(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration", WebMvcAutoConfiguration.class)
        .instanceSupplier(WebMvcAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("formContentFilter", OrderedFormContentFilter.class).withFactoryMethod(WebMvcAutoConfiguration.class, "formContentFilter")
        .instanceSupplier(() -> beanFactory.getBean(WebMvcAutoConfiguration.class).formContentFilter()).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springdoc.core.SpringDocConfigProperties", SpringDocConfigProperties.class)
        .instanceSupplier(SpringDocConfigProperties::new).register(beanFactory);
    org.springdoc.core.MedicalStoreManagementSystemTestsContextInitializer.registerSpringDocConfiguration_SpringDocSpringDataWebPropertiesProvider(beanFactory);
    org.springdoc.core.MedicalStoreManagementSystemTestsContextInitializer.registerSpringDocSpringDataWebPropertiesProvider_springDataWebPropertiesProvider(beanFactory);
    org.springdoc.core.MedicalStoreManagementSystemTestsContextInitializer.registerSpringDocConfiguration_SpringDocPageableConfiguration(beanFactory);
    org.springdoc.core.MedicalStoreManagementSystemTestsContextInitializer.registerSpringDocPageableConfiguration_pageableOpenAPIConverter(beanFactory);
    org.springdoc.core.MedicalStoreManagementSystemTestsContextInitializer.registerSpringDocPageableConfiguration_delegatingMethodParameterCustomizer(beanFactory);
    org.springdoc.core.MedicalStoreManagementSystemTestsContextInitializer.registerSpringDocConfiguration_WebConversionServiceConfiguration(beanFactory);
    org.springdoc.core.MedicalStoreManagementSystemTestsContextInitializer.registerWebConversionServiceConfiguration_webConversionServiceProvider(beanFactory);
    org.springdoc.core.MedicalStoreManagementSystemTestsContextInitializer.registerSpringDocConfiguration_OpenApiResourceAdvice(beanFactory);
    BeanDefinitionRegistrar.of("org.springdoc.core.SpringDocConfiguration", SpringDocConfiguration.class)
        .instanceSupplier(SpringDocConfiguration::new).register(beanFactory);
    org.springdoc.core.MedicalStoreManagementSystemTestsContextInitializer.registerSpringDocConfiguration_localSpringDocParameterNameDiscoverer(beanFactory);
    org.springdoc.core.MedicalStoreManagementSystemTestsContextInitializer.registerSpringDocConfiguration_additionalModelsConverter(beanFactory);
    org.springdoc.core.MedicalStoreManagementSystemTestsContextInitializer.registerSpringDocConfiguration_propertyCustomizingConverter(beanFactory);
    org.springdoc.core.MedicalStoreManagementSystemTestsContextInitializer.registerSpringDocConfiguration_fileSupportConverter(beanFactory);
    org.springdoc.core.MedicalStoreManagementSystemTestsContextInitializer.registerSpringDocConfiguration_responseSupportConverter(beanFactory);
    org.springdoc.core.MedicalStoreManagementSystemTestsContextInitializer.registerSpringDocConfiguration_schemaPropertyDeprecatingConverter(beanFactory);
    org.springdoc.core.MedicalStoreManagementSystemTestsContextInitializer.registerSpringDocConfiguration_polymorphicModelConverter(beanFactory);
    org.springdoc.core.MedicalStoreManagementSystemTestsContextInitializer.registerSpringDocConfiguration_openAPIBuilder(beanFactory);
    org.springdoc.core.MedicalStoreManagementSystemTestsContextInitializer.registerSpringDocConfiguration_modelConverterRegistrar(beanFactory);
    org.springdoc.core.MedicalStoreManagementSystemTestsContextInitializer.registerSpringDocConfiguration_operationBuilder(beanFactory);
    org.springdoc.core.MedicalStoreManagementSystemTestsContextInitializer.registerSpringDocConfiguration_propertyResolverUtils(beanFactory);
    org.springdoc.core.MedicalStoreManagementSystemTestsContextInitializer.registerSpringDocConfiguration_requestBodyBuilder(beanFactory);
    org.springdoc.core.MedicalStoreManagementSystemTestsContextInitializer.registerSpringDocConfiguration_securityParser(beanFactory);
    org.springdoc.core.MedicalStoreManagementSystemTestsContextInitializer.registerSpringDocConfiguration_genericReturnTypeParser(beanFactory);
    org.springdoc.core.MedicalStoreManagementSystemTestsContextInitializer.registerSpringDocConfiguration_parameterBuilder(beanFactory);
    org.springdoc.core.MedicalStoreManagementSystemTestsContextInitializer.registerSpringDocConfiguration_springDocProviders(beanFactory);
    BeanDefinitionRegistrar.of("org.springdoc.core.SwaggerUiConfigParameters", SwaggerUiConfigParameters.class).withConstructor(SwaggerUiConfigProperties.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> new SwaggerUiConfigParameters(attributes.get(0)))).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springdoc.core.SwaggerUiConfigProperties", SwaggerUiConfigProperties.class)
        .instanceSupplier(SwaggerUiConfigProperties::new).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springdoc.core.SwaggerUiOAuthProperties", SwaggerUiOAuthProperties.class)
        .instanceSupplier(SwaggerUiOAuthProperties::new).register(beanFactory);
    org.springdoc.webmvc.core.MedicalStoreManagementSystemTestsContextInitializer.registerSpringDocWebMvcConfiguration_SpringDocWebMvcRouterConfiguration(beanFactory);
    org.springdoc.webmvc.core.MedicalStoreManagementSystemTestsContextInitializer.registerSpringDocWebMvcRouterConfiguration_routerFunctionProvider(beanFactory);
    BeanDefinitionRegistrar.of("org.springdoc.webmvc.core.SpringDocWebMvcConfiguration", SpringDocWebMvcConfiguration.class)
        .instanceSupplier(SpringDocWebMvcConfiguration::new).register(beanFactory);
    org.springdoc.webmvc.core.MedicalStoreManagementSystemTestsContextInitializer.registerSpringDocWebMvcConfiguration_openApiResource(beanFactory);
    org.springdoc.webmvc.core.MedicalStoreManagementSystemTestsContextInitializer.registerSpringDocWebMvcConfiguration_requestBuilder(beanFactory);
    org.springdoc.webmvc.core.MedicalStoreManagementSystemTestsContextInitializer.registerSpringDocWebMvcConfiguration_springWebProvider(beanFactory);
    org.springdoc.webmvc.core.MedicalStoreManagementSystemTestsContextInitializer.registerSpringDocWebMvcConfiguration_responseBuilder(beanFactory);
    BeanDefinitionRegistrar.of("org.springdoc.webmvc.ui.SwaggerConfig", SwaggerConfig.class)
        .instanceSupplier(SwaggerConfig::new).register(beanFactory);
    org.springdoc.webmvc.ui.MedicalStoreManagementSystemTestsContextInitializer.registerSwaggerConfig_swaggerWelcome(beanFactory);
    org.springdoc.webmvc.ui.MedicalStoreManagementSystemTestsContextInitializer.registerSwaggerConfig_swaggerConfigResource(beanFactory);
    org.springdoc.webmvc.ui.MedicalStoreManagementSystemTestsContextInitializer.registerSwaggerConfig_indexPageTransformer(beanFactory);
    org.springdoc.webmvc.ui.MedicalStoreManagementSystemTestsContextInitializer.registerSwaggerConfig_swaggerWebMvcConfigurer(beanFactory);
    org.springframework.boot.autoconfigure.aop.MedicalStoreManagementSystemTestsContextInitializer.registerAspectJAutoProxyingConfiguration_JdkDynamicAutoProxyConfiguration(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.aop.config.internalAutoProxyCreator", AnnotationAwareAspectJAutoProxyCreator.class)
        .instanceSupplier(AnnotationAwareAspectJAutoProxyCreator::new).customize((bd) -> {
      bd.setRole(2);
      bd.getPropertyValues().addPropertyValue("order", -2147483648);
    }).register(beanFactory);
    org.springframework.boot.autoconfigure.aop.MedicalStoreManagementSystemTestsContextInitializer.registerAopAutoConfiguration_AspectJAutoProxyingConfiguration(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.aop.AopAutoConfiguration", AopAutoConfiguration.class)
        .instanceSupplier(AopAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.availability.ApplicationAvailabilityAutoConfiguration", ApplicationAvailabilityAutoConfiguration.class)
        .instanceSupplier(ApplicationAvailabilityAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("applicationAvailability", ApplicationAvailabilityBean.class).withFactoryMethod(ApplicationAvailabilityAutoConfiguration.class, "applicationAvailability")
        .instanceSupplier(() -> beanFactory.getBean(ApplicationAvailabilityAutoConfiguration.class).applicationAvailability()).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration", JtaAutoConfiguration.class)
        .instanceSupplier(JtaAutoConfiguration::new).register(beanFactory);
    org.springframework.boot.autoconfigure.jdbc.MedicalStoreManagementSystemTestsContextInitializer.registerDataSourceConfiguration_Hikari(beanFactory);
    org.springframework.boot.autoconfigure.jdbc.MedicalStoreManagementSystemTestsContextInitializer.registerHikari_dataSource(beanFactory);
    org.springframework.boot.autoconfigure.jdbc.MedicalStoreManagementSystemTestsContextInitializer.registerDataSourceJmxConfiguration_Hikari(beanFactory);
    org.springframework.boot.autoconfigure.jdbc.MedicalStoreManagementSystemTestsContextInitializer.registerDataSourceJmxConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.jdbc.MedicalStoreManagementSystemTestsContextInitializer.registerDataSourceAutoConfiguration_PooledDataSourceConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.jdbc.metadata.MedicalStoreManagementSystemTestsContextInitializer.registerDataSourcePoolMetadataProvidersConfiguration_HikariPoolDataSourceMetadataProviderConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.jdbc.metadata.MedicalStoreManagementSystemTestsContextInitializer.registerHikariPoolDataSourceMetadataProviderConfiguration_hikariPoolDataSourceMetadataProvider(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.jdbc.metadata.DataSourcePoolMetadataProvidersConfiguration", DataSourcePoolMetadataProvidersConfiguration.class)
        .instanceSupplier(DataSourcePoolMetadataProvidersConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration", DataSourceAutoConfiguration.class)
        .instanceSupplier(DataSourceAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("spring.datasource-org.springframework.boot.autoconfigure.jdbc.DataSourceProperties", DataSourceProperties.class)
        .instanceSupplier(DataSourceProperties::new).register(beanFactory);
    org.springframework.boot.autoconfigure.orm.jpa.MedicalStoreManagementSystemTestsContextInitializer.registerJpaBaseConfiguration_JpaWebConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.orm.jpa.MedicalStoreManagementSystemTestsContextInitializer.registerJpaWebConfiguration_openEntityManagerInViewInterceptor(beanFactory);
    org.springframework.boot.autoconfigure.orm.jpa.MedicalStoreManagementSystemTestsContextInitializer.registerJpaWebConfiguration_openEntityManagerInViewInterceptorConfigurer(beanFactory);
    org.springframework.boot.autoconfigure.orm.jpa.MedicalStoreManagementSystemTestsContextInitializer.registerHibernateJpaConfiguration(beanFactory);
    BeanDefinitionRegistrar.of("transactionManager", PlatformTransactionManager.class).withFactoryMethod(JpaBaseConfiguration.class, "transactionManager", ObjectProvider.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(JpaBaseConfiguration.class).transactionManager(attributes.get(0)))).register(beanFactory);
    BeanDefinitionRegistrar.of("jpaVendorAdapter", JpaVendorAdapter.class).withFactoryMethod(JpaBaseConfiguration.class, "jpaVendorAdapter")
        .instanceSupplier(() -> beanFactory.getBean(JpaBaseConfiguration.class).jpaVendorAdapter()).register(beanFactory);
    BeanDefinitionRegistrar.of("entityManagerFactoryBuilder", EntityManagerFactoryBuilder.class).withFactoryMethod(JpaBaseConfiguration.class, "entityManagerFactoryBuilder", JpaVendorAdapter.class, ObjectProvider.class, ObjectProvider.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(JpaBaseConfiguration.class).entityManagerFactoryBuilder(attributes.get(0), attributes.get(1), attributes.get(2)))).register(beanFactory);
    BeanDefinitionRegistrar.of("entityManagerFactory", LocalContainerEntityManagerFactoryBean.class).withFactoryMethod(JpaBaseConfiguration.class, "entityManagerFactory", EntityManagerFactoryBuilder.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(JpaBaseConfiguration.class).entityManagerFactory(attributes.get(0)))).customize((bd) -> {
      bd.setPrimary(true);
      bd.setDependsOn(new String[] { "dataSourceScriptDatabaseInitializer" });
    }).register(beanFactory);
    BeanDefinitionRegistrar.of("spring.jpa.hibernate-org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties", HibernateProperties.class)
        .instanceSupplier(HibernateProperties::new).register(beanFactory);
    BeanDefinitionRegistrar.of("spring.jpa-org.springframework.boot.autoconfigure.orm.jpa.JpaProperties", JpaProperties.class)
        .instanceSupplier(JpaProperties::new).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration", HibernateJpaAutoConfiguration.class)
        .instanceSupplier(HibernateJpaAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration", ConfigurationPropertiesAutoConfiguration.class)
        .instanceSupplier(ConfigurationPropertiesAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.context.LifecycleAutoConfiguration", LifecycleAutoConfiguration.class)
        .instanceSupplier(LifecycleAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("lifecycleProcessor", DefaultLifecycleProcessor.class).withFactoryMethod(LifecycleAutoConfiguration.class, "defaultLifecycleProcessor", LifecycleProperties.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(LifecycleAutoConfiguration.class).defaultLifecycleProcessor(attributes.get(0)))).register(beanFactory);
    BeanDefinitionRegistrar.of("spring.lifecycle-org.springframework.boot.autoconfigure.context.LifecycleProperties", LifecycleProperties.class)
        .instanceSupplier(LifecycleProperties::new).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.dao.PersistenceExceptionTranslationAutoConfiguration", PersistenceExceptionTranslationAutoConfiguration.class)
        .instanceSupplier(PersistenceExceptionTranslationAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("persistenceExceptionTranslationPostProcessor", PersistenceExceptionTranslationPostProcessor.class).withFactoryMethod(PersistenceExceptionTranslationAutoConfiguration.class, "persistenceExceptionTranslationPostProcessor", Environment.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> PersistenceExceptionTranslationAutoConfiguration.persistenceExceptionTranslationPostProcessor(attributes.get(0)))).register(beanFactory);
    org.springframework.boot.autoconfigure.jdbc.MedicalStoreManagementSystemTestsContextInitializer.registerJdbcTemplateConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.jdbc.MedicalStoreManagementSystemTestsContextInitializer.registerJdbcTemplateConfiguration_jdbcTemplate(beanFactory);
    org.springframework.boot.autoconfigure.jdbc.MedicalStoreManagementSystemTestsContextInitializer.registerNamedParameterJdbcTemplateConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.jdbc.MedicalStoreManagementSystemTestsContextInitializer.registerNamedParameterJdbcTemplateConfiguration_namedParameterJdbcTemplate(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration", JdbcTemplateAutoConfiguration.class)
        .instanceSupplier(JdbcTemplateAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("spring.jdbc-org.springframework.boot.autoconfigure.jdbc.JdbcProperties", JdbcProperties.class)
        .instanceSupplier(JdbcProperties::new).register(beanFactory);
    org.springframework.boot.sql.init.dependency.MedicalStoreManagementSystemTestsContextInitializer.registerDatabaseInitializationDependencyConfigurer_DependsOnDatabaseInitializationPostProcessor(beanFactory);
    org.springframework.boot.autoconfigure.jdbc.MedicalStoreManagementSystemTestsContextInitializer.registerDataSourceTransactionManagerAutoConfiguration_JdbcTransactionManagerConfiguration(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration", DataSourceTransactionManagerAutoConfiguration.class)
        .instanceSupplier(DataSourceTransactionManagerAutoConfiguration::new).register(beanFactory);
    org.springframework.boot.autoconfigure.data.jdbc.MedicalStoreManagementSystemTestsContextInitializer.registerJdbcRepositoriesAutoConfiguration_SpringBootJdbcConfiguration(beanFactory);
    BeanDefinitionRegistrar.of("jdbcMappingContext", JdbcMappingContext.class).withFactoryMethod(AbstractJdbcConfiguration.class, "jdbcMappingContext", Optional.class, JdbcCustomConversions.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(AbstractJdbcConfiguration.class).jdbcMappingContext(attributes.get(0), attributes.get(1)))).register(beanFactory);
    BeanDefinitionRegistrar.of("jdbcConverter", JdbcConverter.class).withFactoryMethod(AbstractJdbcConfiguration.class, "jdbcConverter", JdbcMappingContext.class, NamedParameterJdbcOperations.class, RelationResolver.class, JdbcCustomConversions.class, Dialect.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(AbstractJdbcConfiguration.class).jdbcConverter(attributes.get(0), attributes.get(1), attributes.get(2), attributes.get(3), attributes.get(4)))).register(beanFactory);
    BeanDefinitionRegistrar.of("jdbcCustomConversions", JdbcCustomConversions.class).withFactoryMethod(AbstractJdbcConfiguration.class, "jdbcCustomConversions")
        .instanceSupplier(() -> beanFactory.getBean(AbstractJdbcConfiguration.class).jdbcCustomConversions()).register(beanFactory);
    BeanDefinitionRegistrar.of("jdbcAggregateTemplate", JdbcAggregateTemplate.class).withFactoryMethod(AbstractJdbcConfiguration.class, "jdbcAggregateTemplate", ApplicationContext.class, JdbcMappingContext.class, JdbcConverter.class, DataAccessStrategy.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(AbstractJdbcConfiguration.class).jdbcAggregateTemplate(attributes.get(0), attributes.get(1), attributes.get(2), attributes.get(3)))).register(beanFactory);
    BeanDefinitionRegistrar.of("dataAccessStrategyBean", DataAccessStrategy.class).withFactoryMethod(AbstractJdbcConfiguration.class, "dataAccessStrategyBean", NamedParameterJdbcOperations.class, JdbcConverter.class, JdbcMappingContext.class, Dialect.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(AbstractJdbcConfiguration.class).dataAccessStrategyBean(attributes.get(0), attributes.get(1), attributes.get(2), attributes.get(3)))).register(beanFactory);
    BeanDefinitionRegistrar.of("jdbcDialect", Dialect.class).withFactoryMethod(AbstractJdbcConfiguration.class, "jdbcDialect", NamedParameterJdbcOperations.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(AbstractJdbcConfiguration.class).jdbcDialect(attributes.get(0)))).register(beanFactory);
    org.springframework.boot.autoconfigure.data.jdbc.MedicalStoreManagementSystemTestsContextInitializer.registerJdbcRepositoriesAutoConfiguration_JdbcRepositoriesConfiguration(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.data.jdbc.JdbcRepositoriesAutoConfiguration", JdbcRepositoriesAutoConfiguration.class)
        .instanceSupplier(JdbcRepositoriesAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration", JpaRepositoriesAutoConfiguration.class)
        .instanceSupplier(JpaRepositoriesAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("emBeanDefinitionRegistrarPostProcessor", EntityManagerBeanDefinitionRegistrarPostProcessor.class)
        .instanceSupplier(EntityManagerBeanDefinitionRegistrarPostProcessor::new).customize((bd) -> bd.setLazyInit(true)).register(beanFactory);
    BeanDefinitionRegistrar.of("jpaMappingContext", JpaMetamodelMappingContextFactoryBean.class)
        .instanceSupplier(JpaMetamodelMappingContextFactoryBean::new).customize((bd) -> bd.setLazyInit(true)).register(beanFactory);
    BeanDefinitionRegistrar.of("jpaContext", DefaultJpaContext.class).withConstructor(Set.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> new DefaultJpaContext(attributes.get(0)))).customize((bd) -> bd.setLazyInit(true)).register(beanFactory);
    org.springframework.data.jpa.util.MedicalStoreManagementSystemTestsContextInitializer.registerJpaMetamodelCacheCleanup(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.data.jpa.repository.support.JpaEvaluationContextExtension", JpaEvaluationContextExtension.class).withConstructor(char.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> new JpaEvaluationContextExtension(attributes.get(0)))).customize((bd) -> bd.getConstructorArgumentValues().addIndexedArgumentValue(0, '\\')).register(beanFactory);
    BeanDefinitionRegistrar.of("billingRepository", ResolvableType.forClassWithGenerics(JpaRepositoryFactoryBean.class, BillingRepository.class, Billing.class, Long.class)).withConstructor(Class.class)
        .instanceSupplier((instanceContext) -> {
          JpaRepositoryFactoryBean bean = instanceContext.create(beanFactory, (attributes) -> new JpaRepositoryFactoryBean(attributes.get(0)));
          instanceContext.method("setEntityPathResolver", ObjectProvider.class)
              .invoke(beanFactory, (attributes) -> bean.setEntityPathResolver(attributes.get(0)));
          instanceContext.method("setQueryMethodFactory", JpaQueryMethodFactory.class)
              .invoke(beanFactory, (attributes) -> bean.setQueryMethodFactory(attributes.get(0)));
          return bean;
        }).customize((bd) -> {
      bd.getConstructorArgumentValues().addIndexedArgumentValue(0, "com.capgemini.repository.BillingRepository");
      MutablePropertyValues propertyValues = bd.getPropertyValues();
      propertyValues.addPropertyValue("queryLookupStrategyKey", QueryLookupStrategy.Key.CREATE_IF_NOT_FOUND);
      propertyValues.addPropertyValue("lazyInit", false);
      propertyValues.addPropertyValue("namedQueries", BeanDefinitionRegistrar.inner(PropertiesBasedNamedQueries.class).withConstructor(Properties.class)
          .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> new PropertiesBasedNamedQueries(attributes.get(0)))).customize((bd_) -> bd_.getConstructorArgumentValues().addIndexedArgumentValue(0, BeanDefinitionRegistrar.inner(PropertiesFactoryBean.class)
          .instanceSupplier(PropertiesFactoryBean::new).customize((bd__) -> {
        MutablePropertyValues propertyValues__ = bd__.getPropertyValues();
        propertyValues__.addPropertyValue("locations", "classpath*:META-INF/jpa-named-queries.properties");
        propertyValues__.addPropertyValue("ignoreResourceNotFound", true);
      }).toBeanDefinition())).toBeanDefinition());
      propertyValues.addPropertyValue("repositoryFragments", BeanDefinitionRegistrar.inner(RepositoryFragmentsFactoryBean.class).withConstructor(List.class)
          .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> new RepositoryFragmentsFactoryBean(attributes.get(0)))).customize((bd_) -> bd_.getConstructorArgumentValues().addIndexedArgumentValue(0, Collections.emptyList())).toBeanDefinition());
      propertyValues.addPropertyValue("transactionManager", "transactionManager");
      propertyValues.addPropertyValue("entityManager", BeanDefinitionRegistrar.inner(SharedEntityManagerCreator.class).withFactoryMethod(SharedEntityManagerCreator.class, "createSharedEntityManager", EntityManagerFactory.class)
          .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> SharedEntityManagerCreator.createSharedEntityManager(attributes.get(0)))).customize((bd_) -> bd_.getConstructorArgumentValues().addIndexedArgumentValue(0, new RuntimeBeanReference("entityManagerFactory"))).toBeanDefinition());
      propertyValues.addPropertyValue("escapeCharacter", '\\');
      propertyValues.addPropertyValue("mappingContext", new RuntimeBeanReference("jpaMappingContext"));
      propertyValues.addPropertyValue("enableDefaultTransactions", true);
    }).register(beanFactory);
    BeanDefinitionRegistrar.of("ILoginRepository", ResolvableType.forClassWithGenerics(JpaRepositoryFactoryBean.class, ILoginRepository.class, User.class, Integer.class)).withConstructor(Class.class)
        .instanceSupplier((instanceContext) -> {
          JpaRepositoryFactoryBean bean = instanceContext.create(beanFactory, (attributes) -> new JpaRepositoryFactoryBean(attributes.get(0)));
          instanceContext.method("setEntityPathResolver", ObjectProvider.class)
              .invoke(beanFactory, (attributes) -> bean.setEntityPathResolver(attributes.get(0)));
          instanceContext.method("setQueryMethodFactory", JpaQueryMethodFactory.class)
              .invoke(beanFactory, (attributes) -> bean.setQueryMethodFactory(attributes.get(0)));
          return bean;
        }).customize((bd) -> {
      bd.getConstructorArgumentValues().addIndexedArgumentValue(0, "com.capgemini.repository.ILoginRepository");
      MutablePropertyValues propertyValues = bd.getPropertyValues();
      propertyValues.addPropertyValue("queryLookupStrategyKey", QueryLookupStrategy.Key.CREATE_IF_NOT_FOUND);
      propertyValues.addPropertyValue("lazyInit", false);
      propertyValues.addPropertyValue("namedQueries", BeanDefinitionRegistrar.inner(PropertiesBasedNamedQueries.class).withConstructor(Properties.class)
          .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> new PropertiesBasedNamedQueries(attributes.get(0)))).customize((bd_) -> bd_.getConstructorArgumentValues().addIndexedArgumentValue(0, BeanDefinitionRegistrar.inner(PropertiesFactoryBean.class)
          .instanceSupplier(PropertiesFactoryBean::new).customize((bd__) -> {
        MutablePropertyValues propertyValues__ = bd__.getPropertyValues();
        propertyValues__.addPropertyValue("locations", "classpath*:META-INF/jpa-named-queries.properties");
        propertyValues__.addPropertyValue("ignoreResourceNotFound", true);
      }).toBeanDefinition())).toBeanDefinition());
      propertyValues.addPropertyValue("repositoryFragments", BeanDefinitionRegistrar.inner(RepositoryFragmentsFactoryBean.class).withConstructor(List.class)
          .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> new RepositoryFragmentsFactoryBean(attributes.get(0)))).customize((bd_) -> bd_.getConstructorArgumentValues().addIndexedArgumentValue(0, Collections.emptyList())).toBeanDefinition());
      propertyValues.addPropertyValue("transactionManager", "transactionManager");
      propertyValues.addPropertyValue("entityManager", BeanDefinitionRegistrar.inner(SharedEntityManagerCreator.class).withFactoryMethod(SharedEntityManagerCreator.class, "createSharedEntityManager", EntityManagerFactory.class)
          .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> SharedEntityManagerCreator.createSharedEntityManager(attributes.get(0)))).customize((bd_) -> bd_.getConstructorArgumentValues().addIndexedArgumentValue(0, new RuntimeBeanReference("entityManagerFactory"))).toBeanDefinition());
      propertyValues.addPropertyValue("escapeCharacter", '\\');
      propertyValues.addPropertyValue("mappingContext", new RuntimeBeanReference("jpaMappingContext"));
      propertyValues.addPropertyValue("enableDefaultTransactions", true);
    }).register(beanFactory);
    BeanDefinitionRegistrar.of("productRepository", ResolvableType.forClassWithGenerics(JpaRepositoryFactoryBean.class, ProductRepository.class, Product.class, Integer.class)).withConstructor(Class.class)
        .instanceSupplier((instanceContext) -> {
          JpaRepositoryFactoryBean bean = instanceContext.create(beanFactory, (attributes) -> new JpaRepositoryFactoryBean(attributes.get(0)));
          instanceContext.method("setEntityPathResolver", ObjectProvider.class)
              .invoke(beanFactory, (attributes) -> bean.setEntityPathResolver(attributes.get(0)));
          instanceContext.method("setQueryMethodFactory", JpaQueryMethodFactory.class)
              .invoke(beanFactory, (attributes) -> bean.setQueryMethodFactory(attributes.get(0)));
          return bean;
        }).customize((bd) -> {
      bd.getConstructorArgumentValues().addIndexedArgumentValue(0, "com.capgemini.repository.ProductRepository");
      MutablePropertyValues propertyValues = bd.getPropertyValues();
      propertyValues.addPropertyValue("queryLookupStrategyKey", QueryLookupStrategy.Key.CREATE_IF_NOT_FOUND);
      propertyValues.addPropertyValue("lazyInit", false);
      propertyValues.addPropertyValue("namedQueries", BeanDefinitionRegistrar.inner(PropertiesBasedNamedQueries.class).withConstructor(Properties.class)
          .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> new PropertiesBasedNamedQueries(attributes.get(0)))).customize((bd_) -> bd_.getConstructorArgumentValues().addIndexedArgumentValue(0, BeanDefinitionRegistrar.inner(PropertiesFactoryBean.class)
          .instanceSupplier(PropertiesFactoryBean::new).customize((bd__) -> {
        MutablePropertyValues propertyValues__ = bd__.getPropertyValues();
        propertyValues__.addPropertyValue("locations", "classpath*:META-INF/jpa-named-queries.properties");
        propertyValues__.addPropertyValue("ignoreResourceNotFound", true);
      }).toBeanDefinition())).toBeanDefinition());
      propertyValues.addPropertyValue("repositoryFragments", BeanDefinitionRegistrar.inner(RepositoryFragmentsFactoryBean.class).withConstructor(List.class)
          .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> new RepositoryFragmentsFactoryBean(attributes.get(0)))).customize((bd_) -> bd_.getConstructorArgumentValues().addIndexedArgumentValue(0, Collections.emptyList())).toBeanDefinition());
      propertyValues.addPropertyValue("transactionManager", "transactionManager");
      propertyValues.addPropertyValue("entityManager", BeanDefinitionRegistrar.inner(SharedEntityManagerCreator.class).withFactoryMethod(SharedEntityManagerCreator.class, "createSharedEntityManager", EntityManagerFactory.class)
          .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> SharedEntityManagerCreator.createSharedEntityManager(attributes.get(0)))).customize((bd_) -> bd_.getConstructorArgumentValues().addIndexedArgumentValue(0, new RuntimeBeanReference("entityManagerFactory"))).toBeanDefinition());
      propertyValues.addPropertyValue("escapeCharacter", '\\');
      propertyValues.addPropertyValue("mappingContext", new RuntimeBeanReference("jpaMappingContext"));
      propertyValues.addPropertyValue("enableDefaultTransactions", true);
    }).register(beanFactory);
    org.springframework.boot.autoconfigure.http.MedicalStoreManagementSystemTestsContextInitializer.registerHttpMessageConvertersAutoConfiguration_StringHttpMessageConverterConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.http.MedicalStoreManagementSystemTestsContextInitializer.registerStringHttpMessageConverterConfiguration_stringHttpMessageConverter(beanFactory);
    org.springframework.boot.autoconfigure.http.MedicalStoreManagementSystemTestsContextInitializer.registerJacksonHttpMessageConvertersConfiguration_MappingJackson2HttpMessageConverterConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.http.MedicalStoreManagementSystemTestsContextInitializer.registerMappingJackson2HttpMessageConverterConfiguration_mappingJackson2HttpMessageConverter(beanFactory);
    org.springframework.boot.autoconfigure.http.MedicalStoreManagementSystemTestsContextInitializer.registerJacksonHttpMessageConvertersConfiguration(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration", HttpMessageConvertersAutoConfiguration.class)
        .instanceSupplier(HttpMessageConvertersAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("messageConverters", HttpMessageConverters.class).withFactoryMethod(HttpMessageConvertersAutoConfiguration.class, "messageConverters", ObjectProvider.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(HttpMessageConvertersAutoConfiguration.class).messageConverters(attributes.get(0)))).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.data.web.config.ProjectingArgumentResolverRegistrar", ProjectingArgumentResolverRegistrar.class)
        .instanceSupplier(ProjectingArgumentResolverRegistrar::new).register(beanFactory);
    org.springframework.data.web.config.MedicalStoreManagementSystemTestsContextInitializer.registerProjectingArgumentResolverRegistrar_projectingArgumentResolverBeanPostProcessor(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.data.web.config.SpringDataWebConfiguration", SpringDataWebConfiguration.class).withConstructor(ApplicationContext.class, ObjectFactory.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> new SpringDataWebConfiguration(attributes.get(0), attributes.get(1)))).register(beanFactory);
    BeanDefinitionRegistrar.of("pageableResolver", PageableHandlerMethodArgumentResolver.class).withFactoryMethod(SpringDataWebConfiguration.class, "pageableResolver")
        .instanceSupplier(() -> beanFactory.getBean(SpringDataWebConfiguration.class).pageableResolver()).register(beanFactory);
    BeanDefinitionRegistrar.of("sortResolver", SortHandlerMethodArgumentResolver.class).withFactoryMethod(SpringDataWebConfiguration.class, "sortResolver")
        .instanceSupplier(() -> beanFactory.getBean(SpringDataWebConfiguration.class).sortResolver()).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.data.web.config.SpringDataJacksonConfiguration", SpringDataJacksonConfiguration.class)
        .instanceSupplier(SpringDataJacksonConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("jacksonGeoModule", GeoModule.class).withFactoryMethod(SpringDataJacksonConfiguration.class, "jacksonGeoModule")
        .instanceSupplier(() -> beanFactory.getBean(SpringDataJacksonConfiguration.class).jacksonGeoModule()).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration", SpringDataWebAutoConfiguration.class).withConstructor(SpringDataWebProperties.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> new SpringDataWebAutoConfiguration(attributes.get(0)))).register(beanFactory);
    BeanDefinitionRegistrar.of("pageableCustomizer", PageableHandlerMethodArgumentResolverCustomizer.class).withFactoryMethod(SpringDataWebAutoConfiguration.class, "pageableCustomizer")
        .instanceSupplier(() -> beanFactory.getBean(SpringDataWebAutoConfiguration.class).pageableCustomizer()).register(beanFactory);
    BeanDefinitionRegistrar.of("sortCustomizer", SortHandlerMethodArgumentResolverCustomizer.class).withFactoryMethod(SpringDataWebAutoConfiguration.class, "sortCustomizer")
        .instanceSupplier(() -> beanFactory.getBean(SpringDataWebAutoConfiguration.class).sortCustomizer()).register(beanFactory);
    BeanDefinitionRegistrar.of("spring.data.web-org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties", SpringDataWebProperties.class)
        .instanceSupplier(SpringDataWebProperties::new).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration", ProjectInfoAutoConfiguration.class).withConstructor(ProjectInfoProperties.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> new ProjectInfoAutoConfiguration(attributes.get(0)))).register(beanFactory);
    BeanDefinitionRegistrar.of("spring.info-org.springframework.boot.autoconfigure.info.ProjectInfoProperties", ProjectInfoProperties.class)
        .instanceSupplier(ProjectInfoProperties::new).register(beanFactory);
    org.springframework.boot.autoconfigure.security.servlet.MedicalStoreManagementSystemTestsContextInitializer.registerSpringBootWebSecurityConfiguration_ErrorPageSecurityFilterConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.security.servlet.MedicalStoreManagementSystemTestsContextInitializer.registerErrorPageSecurityFilterConfiguration_errorPageSecurityFilter(beanFactory);
    org.springframework.boot.autoconfigure.security.servlet.MedicalStoreManagementSystemTestsContextInitializer.registerSpringBootWebSecurityConfiguration(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration", SecurityAutoConfiguration.class)
        .instanceSupplier(SecurityAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("authenticationEventPublisher", DefaultAuthenticationEventPublisher.class).withFactoryMethod(SecurityAutoConfiguration.class, "authenticationEventPublisher", ApplicationEventPublisher.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(SecurityAutoConfiguration.class).authenticationEventPublisher(attributes.get(0)))).register(beanFactory);
    BeanDefinitionRegistrar.of("spring.security-org.springframework.boot.autoconfigure.security.SecurityProperties", SecurityProperties.class)
        .instanceSupplier(SecurityProperties::new).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration", SecurityFilterAutoConfiguration.class)
        .instanceSupplier(SecurityFilterAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("securityFilterChainRegistration", DelegatingFilterProxyRegistrationBean.class).withFactoryMethod(SecurityFilterAutoConfiguration.class, "securityFilterChainRegistration", SecurityProperties.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(SecurityFilterAutoConfiguration.class).securityFilterChainRegistration(attributes.get(0)))).register(beanFactory);
    org.springframework.boot.autoconfigure.sql.init.MedicalStoreManagementSystemTestsContextInitializer.registerDataSourceInitializationConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.sql.init.MedicalStoreManagementSystemTestsContextInitializer.registerDataSourceInitializationConfiguration_dataSourceScriptDatabaseInitializer(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.sql.init.SqlInitializationAutoConfiguration", SqlInitializationAutoConfiguration.class)
        .instanceSupplier(SqlInitializationAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("spring.sql.init-org.springframework.boot.autoconfigure.sql.init.SqlInitializationProperties", SqlInitializationProperties.class)
        .instanceSupplier(SqlInitializationProperties::new).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration", TaskSchedulingAutoConfiguration.class)
        .instanceSupplier(TaskSchedulingAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("scheduledBeanLazyInitializationExcludeFilter", LazyInitializationExcludeFilter.class).withFactoryMethod(TaskSchedulingAutoConfiguration.class, "scheduledBeanLazyInitializationExcludeFilter")
        .instanceSupplier(() -> TaskSchedulingAutoConfiguration.scheduledBeanLazyInitializationExcludeFilter()).register(beanFactory);
    BeanDefinitionRegistrar.of("taskSchedulerBuilder", TaskSchedulerBuilder.class).withFactoryMethod(TaskSchedulingAutoConfiguration.class, "taskSchedulerBuilder", TaskSchedulingProperties.class, ObjectProvider.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(TaskSchedulingAutoConfiguration.class).taskSchedulerBuilder(attributes.get(0), attributes.get(1)))).register(beanFactory);
    BeanDefinitionRegistrar.of("spring.task.scheduling-org.springframework.boot.autoconfigure.task.TaskSchedulingProperties", TaskSchedulingProperties.class)
        .instanceSupplier(TaskSchedulingProperties::new).register(beanFactory);
    org.springframework.boot.autoconfigure.thymeleaf.MedicalStoreManagementSystemTestsContextInitializer.registerThymeleafAutoConfiguration_ThymeleafJava8TimeDialect(beanFactory);
    org.springframework.boot.autoconfigure.thymeleaf.MedicalStoreManagementSystemTestsContextInitializer.registerThymeleafJava8TimeDialect_java8TimeDialect(beanFactory);
    org.springframework.boot.autoconfigure.thymeleaf.MedicalStoreManagementSystemTestsContextInitializer.registerThymeleafAutoConfiguration_ThymeleafSecurityDialectConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.thymeleaf.MedicalStoreManagementSystemTestsContextInitializer.registerThymeleafSecurityDialectConfiguration_securityDialect(beanFactory);
    org.springframework.boot.autoconfigure.thymeleaf.MedicalStoreManagementSystemTestsContextInitializer.registerThymeleafWebMvcConfiguration_ThymeleafViewResolverConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.thymeleaf.MedicalStoreManagementSystemTestsContextInitializer.registerThymeleafViewResolverConfiguration_thymeleafViewResolver(beanFactory);
    org.springframework.boot.autoconfigure.thymeleaf.MedicalStoreManagementSystemTestsContextInitializer.registerThymeleafAutoConfiguration_ThymeleafWebMvcConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.thymeleaf.MedicalStoreManagementSystemTestsContextInitializer.registerThymeleafWebMvcConfiguration_resourceUrlEncodingFilter(beanFactory);
    org.springframework.boot.autoconfigure.thymeleaf.MedicalStoreManagementSystemTestsContextInitializer.registerThymeleafAutoConfiguration_DefaultTemplateResolverConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.thymeleaf.MedicalStoreManagementSystemTestsContextInitializer.registerDefaultTemplateResolverConfiguration_defaultTemplateResolver(beanFactory);
    org.springframework.boot.autoconfigure.thymeleaf.MedicalStoreManagementSystemTestsContextInitializer.registerTemplateEngineConfigurations_DefaultTemplateEngineConfiguration(beanFactory);
    org.springframework.boot.autoconfigure.thymeleaf.MedicalStoreManagementSystemTestsContextInitializer.registerDefaultTemplateEngineConfiguration_templateEngine(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration", ThymeleafAutoConfiguration.class)
        .instanceSupplier(ThymeleafAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("spring.thymeleaf-org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties", ThymeleafProperties.class)
        .instanceSupplier(ThymeleafProperties::new).register(beanFactory);
    org.springframework.transaction.annotation.MedicalStoreManagementSystemTestsContextInitializer.registerProxyTransactionManagementConfiguration(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.transaction.config.internalTransactionAdvisor", BeanFactoryTransactionAttributeSourceAdvisor.class).withFactoryMethod(ProxyTransactionManagementConfiguration.class, "transactionAdvisor", TransactionAttributeSource.class, TransactionInterceptor.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(ProxyTransactionManagementConfiguration.class).transactionAdvisor(attributes.get(0), attributes.get(1)))).customize((bd) -> bd.setRole(2)).register(beanFactory);
    BeanDefinitionRegistrar.of("transactionAttributeSource", TransactionAttributeSource.class).withFactoryMethod(ProxyTransactionManagementConfiguration.class, "transactionAttributeSource")
        .instanceSupplier(() -> beanFactory.getBean(ProxyTransactionManagementConfiguration.class).transactionAttributeSource()).customize((bd) -> bd.setRole(2)).register(beanFactory);
    BeanDefinitionRegistrar.of("transactionInterceptor", TransactionInterceptor.class).withFactoryMethod(ProxyTransactionManagementConfiguration.class, "transactionInterceptor", TransactionAttributeSource.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(ProxyTransactionManagementConfiguration.class).transactionInterceptor(attributes.get(0)))).customize((bd) -> bd.setRole(2)).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.transaction.config.internalTransactionalEventListenerFactory", TransactionalEventListenerFactory.class).withFactoryMethod(AbstractTransactionManagementConfiguration.class, "transactionalEventListenerFactory")
        .instanceSupplier(() -> AbstractTransactionManagementConfiguration.transactionalEventListenerFactory()).customize((bd) -> bd.setRole(2)).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration$EnableTransactionManagementConfiguration$JdkDynamicAutoProxyConfiguration", TransactionAutoConfiguration.EnableTransactionManagementConfiguration.JdkDynamicAutoProxyConfiguration.class)
        .instanceSupplier(TransactionAutoConfiguration.EnableTransactionManagementConfiguration.JdkDynamicAutoProxyConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration$EnableTransactionManagementConfiguration", TransactionAutoConfiguration.EnableTransactionManagementConfiguration.class)
        .instanceSupplier(TransactionAutoConfiguration.EnableTransactionManagementConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration$TransactionTemplateConfiguration", TransactionAutoConfiguration.TransactionTemplateConfiguration.class)
        .instanceSupplier(TransactionAutoConfiguration.TransactionTemplateConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("transactionTemplate", TransactionTemplate.class).withFactoryMethod(TransactionAutoConfiguration.TransactionTemplateConfiguration.class, "transactionTemplate", PlatformTransactionManager.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(TransactionAutoConfiguration.TransactionTemplateConfiguration.class).transactionTemplate(attributes.get(0)))).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration", TransactionAutoConfiguration.class)
        .instanceSupplier(TransactionAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("platformTransactionManagerCustomizers", TransactionManagerCustomizers.class).withFactoryMethod(TransactionAutoConfiguration.class, "platformTransactionManagerCustomizers", ObjectProvider.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(TransactionAutoConfiguration.class).platformTransactionManagerCustomizers(attributes.get(0)))).register(beanFactory);
    BeanDefinitionRegistrar.of("spring.transaction-org.springframework.boot.autoconfigure.transaction.TransactionProperties", TransactionProperties.class)
        .instanceSupplier(TransactionProperties::new).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration", RestTemplateAutoConfiguration.class)
        .instanceSupplier(RestTemplateAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("restTemplateBuilderConfigurer", RestTemplateBuilderConfigurer.class).withFactoryMethod(RestTemplateAutoConfiguration.class, "restTemplateBuilderConfigurer", ObjectProvider.class, ObjectProvider.class, ObjectProvider.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(RestTemplateAutoConfiguration.class).restTemplateBuilderConfigurer(attributes.get(0), attributes.get(1), attributes.get(2)))).customize((bd) -> bd.setLazyInit(true)).register(beanFactory);
    BeanDefinitionRegistrar.of("restTemplateBuilder", RestTemplateBuilder.class).withFactoryMethod(RestTemplateAutoConfiguration.class, "restTemplateBuilder", RestTemplateBuilderConfigurer.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(RestTemplateAutoConfiguration.class).restTemplateBuilder(attributes.get(0)))).customize((bd) -> bd.setLazyInit(true)).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration$TomcatWebServerFactoryCustomizerConfiguration", EmbeddedWebServerFactoryCustomizerAutoConfiguration.TomcatWebServerFactoryCustomizerConfiguration.class)
        .instanceSupplier(EmbeddedWebServerFactoryCustomizerAutoConfiguration.TomcatWebServerFactoryCustomizerConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("tomcatWebServerFactoryCustomizer", TomcatWebServerFactoryCustomizer.class).withFactoryMethod(EmbeddedWebServerFactoryCustomizerAutoConfiguration.TomcatWebServerFactoryCustomizerConfiguration.class, "tomcatWebServerFactoryCustomizer", Environment.class, ServerProperties.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(EmbeddedWebServerFactoryCustomizerAutoConfiguration.TomcatWebServerFactoryCustomizerConfiguration.class).tomcatWebServerFactoryCustomizer(attributes.get(0), attributes.get(1)))).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration", EmbeddedWebServerFactoryCustomizerAutoConfiguration.class)
        .instanceSupplier(EmbeddedWebServerFactoryCustomizerAutoConfiguration::new).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration", HttpEncodingAutoConfiguration.class).withConstructor(ServerProperties.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> new HttpEncodingAutoConfiguration(attributes.get(0)))).register(beanFactory);
    BeanDefinitionRegistrar.of("characterEncodingFilter", CharacterEncodingFilter.class).withFactoryMethod(HttpEncodingAutoConfiguration.class, "characterEncodingFilter")
        .instanceSupplier(() -> beanFactory.getBean(HttpEncodingAutoConfiguration.class).characterEncodingFilter()).register(beanFactory);
    org.springframework.boot.autoconfigure.web.servlet.MedicalStoreManagementSystemTestsContextInitializer.registerHttpEncodingAutoConfiguration_localeCharsetMappingsCustomizer(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration", MultipartAutoConfiguration.class).withConstructor(MultipartProperties.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> new MultipartAutoConfiguration(attributes.get(0)))).register(beanFactory);
    BeanDefinitionRegistrar.of("multipartConfigElement", MultipartConfigElement.class).withFactoryMethod(MultipartAutoConfiguration.class, "multipartConfigElement")
        .instanceSupplier(() -> beanFactory.getBean(MultipartAutoConfiguration.class).multipartConfigElement()).register(beanFactory);
    BeanDefinitionRegistrar.of("multipartResolver", StandardServletMultipartResolver.class).withFactoryMethod(MultipartAutoConfiguration.class, "multipartResolver")
        .instanceSupplier(() -> beanFactory.getBean(MultipartAutoConfiguration.class).multipartResolver()).register(beanFactory);
    BeanDefinitionRegistrar.of("spring.servlet.multipart-org.springframework.boot.autoconfigure.web.servlet.MultipartProperties", MultipartProperties.class)
        .instanceSupplier(MultipartProperties::new).register(beanFactory);
    BeanDefinitionRegistrar.of("org.springframework.orm.jpa.SharedEntityManagerCreator#0", EntityManager.class).withFactoryMethod(SharedEntityManagerCreator.class, "createSharedEntityManager", EntityManagerFactory.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> SharedEntityManagerCreator.createSharedEntityManager(attributes.get(0)))).customize((bd) -> {
      bd.setPrimary(true);
      bd.setLazyInit(true);
      bd.getConstructorArgumentValues().addIndexedArgumentValue(0, new RuntimeBeanReference("entityManagerFactory"));
    }).register(beanFactory);
  }
}
