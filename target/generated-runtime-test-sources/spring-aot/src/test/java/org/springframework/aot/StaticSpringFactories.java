package org.springframework.aot;

import java.lang.Class;
import java.lang.Object;
import java.lang.String;
import java.util.function.Supplier;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.SpringBootExceptionReporter;
import org.springframework.boot._FactoryProvider;
import org.springframework.boot.context.config.ConfigDataLocationResolver;
import org.springframework.boot.diagnostics.FailureAnalyzer;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.sql.init.dependency.DatabaseInitializerDetector;
import org.springframework.boot.sql.init.dependency.DependsOnDatabaseInitializationDetector;
import org.springframework.boot.test.context.DefaultTestExecutionListenersPostProcessor;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.core.NativeDetector;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.data.util.CustomCollectionRegistrar;
import org.springframework.data.util.ProxyUtils;
import org.springframework.test.context.ContextCustomizerFactory;
import org.springframework.test.context.TestExecutionListener;
import org.springframework.util.ClassUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Class generated - do not edit this file
 */
public abstract class StaticSpringFactories {
  public static MultiValueMap<Class, Supplier<Object>> factories = new LinkedMultiValueMap();

  public static MultiValueMap<Class, String> names = new LinkedMultiValueMap();

  static {
    System.setProperty("spring.xml.ignore", "true");
    if (!NativeDetector.inNativeImage()) {
      System.setProperty("org.graalvm.nativeimage.imagecode", "runtime");
    }
    if (ClassUtils.isPresent("org.hibernate.Session", null)) {
      System.setProperty("hibernate.bytecode.provider", "none");
    }
    factories.add(org.springframework.boot.autoconfigure.template.TemplateAvailabilityProvider.class, () -> new org.springframework.boot.autoconfigure.thymeleaf.ThymeleafTemplateAvailabilityProvider());
    names.add(TestExecutionListener.class, "org.springframework.boot.test.autoconfigure.restdocs.RestDocsTestExecutionListener");
    names.add(TestExecutionListener.class, "org.springframework.boot.test.autoconfigure.web.client.MockRestServiceServerResetTestExecutionListener");
    names.add(TestExecutionListener.class, "org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrintOnlyOnFailureTestExecutionListener");
    names.add(TestExecutionListener.class, "org.springframework.boot.test.autoconfigure.web.servlet.WebDriverTestExecutionListener");
    names.add(TestExecutionListener.class, "org.springframework.boot.test.autoconfigure.webservices.client.MockWebServiceServerTestExecutionListener");
    names.add(TestExecutionListener.class, "org.springframework.test.context.web.ServletTestExecutionListener");
    names.add(TestExecutionListener.class, "org.springframework.test.context.support.DirtiesContextBeforeModesTestExecutionListener");
    names.add(TestExecutionListener.class, "org.springframework.test.context.event.ApplicationEventsTestExecutionListener");
    names.add(TestExecutionListener.class, "org.springframework.aot.test.AotDependencyInjectionTestExecutionListener");
    names.add(TestExecutionListener.class, "org.springframework.test.context.support.DirtiesContextTestExecutionListener");
    names.add(TestExecutionListener.class, "org.springframework.test.context.transaction.TransactionalTestExecutionListener");
    names.add(TestExecutionListener.class, "org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener");
    names.add(TestExecutionListener.class, "org.springframework.test.context.event.EventPublishingTestExecutionListener");
    names.add(TestExecutionListener.class, "org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener");
    names.add(TestExecutionListener.class, "org.springframework.security.test.context.support.ReactorContextTestExecutionListener");
    names.add(SpringBootExceptionReporter.class, "org.springframework.boot.diagnostics.FailureAnalyzers");
    names.add(ConfigDataLocationResolver.class, "org.springframework.boot.context.config.ConfigTreeConfigDataLocationResolver");
    names.add(ConfigDataLocationResolver.class, "org.springframework.boot.context.config.StandardConfigDataLocationResolver");
    factories.add(ApplicationListener.class, () -> _FactoryProvider.clearCachesApplicationListener());
    factories.add(org.springframework.context.ApplicationListener.class, () -> new org.springframework.boot.builder.ParentContextCloserApplicationListener());
    factories.add(org.springframework.context.ApplicationListener.class, () -> new org.springframework.boot.context.FileEncodingApplicationListener());
    factories.add(org.springframework.context.ApplicationListener.class, () -> new org.springframework.boot.context.config.AnsiOutputApplicationListener());
    factories.add(org.springframework.context.ApplicationListener.class, () -> new org.springframework.boot.context.config.DelegatingApplicationListener());
    factories.add(org.springframework.context.ApplicationListener.class, () -> new org.springframework.boot.context.logging.LoggingApplicationListener());
    factories.add(org.springframework.context.ApplicationListener.class, () -> new org.springframework.boot.env.EnvironmentPostProcessorApplicationListener());
    factories.add(org.springframework.context.ApplicationListener.class, () -> new org.springframework.nativex.NativeListener());
    factories.add(ContextCustomizerFactory.class, () -> org.springframework.boot.test.context.filter._FactoryProvider.excludeFilterContextCustomizerFactory());
    factories.add(ContextCustomizerFactory.class, () -> org.springframework.boot.test.graphql.tester._FactoryProvider.httpGraphQlTesterContextCustomizerFactory());
    factories.add(ContextCustomizerFactory.class, () -> org.springframework.boot.test.json._FactoryProvider.duplicateJsonObjectContextCustomizerFactory());
    factories.add(ContextCustomizerFactory.class, () -> org.springframework.boot.test.mock.mockito._FactoryProvider.mockitoContextCustomizerFactory());
    factories.add(ContextCustomizerFactory.class, () -> org.springframework.boot.test.web.client._FactoryProvider.testRestTemplateContextCustomizerFactory());
    factories.add(ContextCustomizerFactory.class, () -> org.springframework.boot.test.web.reactive.server._FactoryProvider.webTestClientContextCustomizerFactory());
    factories.add(ContextCustomizerFactory.class, () -> org.springframework.boot.test.autoconfigure.actuate.metrics._FactoryProvider.metricsExportContextCustomizerFactory());
    factories.add(ContextCustomizerFactory.class, () -> org.springframework.boot.test.autoconfigure.properties._FactoryProvider.propertyMappingContextCustomizerFactory());
    factories.add(ContextCustomizerFactory.class, () -> org.springframework.boot.test.autoconfigure.web.servlet._FactoryProvider.webDriverContextCustomizerFactory());
    factories.add(ContextCustomizerFactory.class, () -> org.springframework.test.context.web.socket._FactoryProvider.mockServerContainerContextCustomizerFactory());
    factories.add(ContextCustomizerFactory.class, () -> org.springframework.test.context.support._FactoryProvider.dynamicPropertiesContextCustomizerFactory());
    factories.add(DependsOnDatabaseInitializationDetector.class, () -> org.springframework.boot.sql.init.dependency._FactoryProvider.annotationDependsOnDatabaseInitializationDetector());
    factories.add(DependsOnDatabaseInitializationDetector.class, () -> org.springframework.boot.jdbc._FactoryProvider.springJdbcDependsOnDatabaseInitializationDetector());
    factories.add(DependsOnDatabaseInitializationDetector.class, () -> org.springframework.boot.jooq._FactoryProvider.jooqDependsOnDatabaseInitializationDetector());
    names.add(DependsOnDatabaseInitializationDetector.class, "org.springframework.boot.orm.jpa.JpaDependsOnDatabaseInitializationDetector");
    factories.add(DependsOnDatabaseInitializationDetector.class, () -> org.springframework.boot.autoconfigure.batch._FactoryProvider.jobRepositoryDependsOnDatabaseInitializationDetector());
    factories.add(DependsOnDatabaseInitializationDetector.class, () -> org.springframework.boot.autoconfigure.quartz._FactoryProvider.schedulerDependsOnDatabaseInitializationDetector());
    factories.add(DependsOnDatabaseInitializationDetector.class, () -> org.springframework.boot.autoconfigure.session._FactoryProvider.jdbcIndexedSessionRepositoryDependsOnDatabaseInitializationDetector());
    names.add(RepositoryFactorySupport.class, "org.springframework.data.jdbc.repository.support.JdbcRepositoryFactory");
    names.add(RepositoryFactorySupport.class, "org.springframework.data.jpa.repository.support.JpaRepositoryFactory");
    factories.add(DatabaseInitializerDetector.class, () -> org.springframework.boot.flyway._FactoryProvider.flywayDatabaseInitializerDetector());
    factories.add(DatabaseInitializerDetector.class, () -> org.springframework.boot.jdbc._FactoryProvider.abstractDataSourceInitializerDatabaseInitializerDetector());
    factories.add(DatabaseInitializerDetector.class, () -> org.springframework.boot.jdbc.init._FactoryProvider.dataSourceScriptDatabaseInitializerDetector());
    factories.add(DatabaseInitializerDetector.class, () -> org.springframework.boot.liquibase._FactoryProvider.liquibaseDatabaseInitializerDetector());
    names.add(DatabaseInitializerDetector.class, "org.springframework.boot.orm.jpa.JpaDatabaseInitializerDetector");
    factories.add(DatabaseInitializerDetector.class, () -> org.springframework.boot.r2dbc.init._FactoryProvider.r2dbcScriptDatabaseInitializerDetector());
    factories.add(DatabaseInitializerDetector.class, () -> org.springframework.boot.autoconfigure.flyway._FactoryProvider.flywayMigrationInitializerDatabaseInitializerDetector());
    factories.add(org.springframework.data.web.config.SpringDataJacksonModules.class, () -> new org.springframework.data.web.config.SpringDataJacksonConfiguration());
    factories.add(ProxyUtils.ProxyDetector.class, () -> org.springframework.data.jpa.util._FactoryProvider.hibernateProxyDetector());
    factories.add(org.springframework.data.jdbc.repository.config.DialectResolver.JdbcDialectProvider.class, () -> new org.springframework.data.jdbc.repository.config.DialectResolver.DefaultDialectProvider());
    factories.add(CustomCollectionRegistrar.class, () -> org.springframework.data.util._FactoryProvider.customCollectionsVavrCollections());
    factories.add(CustomCollectionRegistrar.class, () -> org.springframework.data.util._FactoryProvider.customCollectionsEclipseCollections());
    factories.add(DefaultTestExecutionListenersPostProcessor.class, () -> org.springframework.boot.test.autoconfigure._FactoryProvider.springBootDependencyInjectionTestExecutionListenerPostProcessor());
    factories.add(org.springframework.boot.context.config.ConfigDataLoader.class, () -> new org.springframework.boot.context.config.ConfigTreeConfigDataLoader());
    factories.add(org.springframework.boot.context.config.ConfigDataLoader.class, () -> new org.springframework.boot.context.config.StandardConfigDataLoader());
    names.add(SpringApplicationRunListener.class, "org.springframework.boot.context.event.EventPublishingRunListener");
    factories.add(org.springframework.boot.diagnostics.FailureAnalysisReporter.class, () -> new org.springframework.boot.diagnostics.LoggingFailureAnalysisReporter());
    names.add(EnvironmentPostProcessor.class, "org.springframework.boot.cloud.CloudFoundryVcapEnvironmentPostProcessor");
    names.add(EnvironmentPostProcessor.class, "org.springframework.boot.context.config.ConfigDataEnvironmentPostProcessor");
    names.add(EnvironmentPostProcessor.class, "org.springframework.boot.env.RandomValuePropertySourceEnvironmentPostProcessor");
    factories.add(org.springframework.boot.env.EnvironmentPostProcessor.class, () -> new org.springframework.boot.env.SpringApplicationJsonEnvironmentPostProcessor());
    factories.add(org.springframework.boot.env.EnvironmentPostProcessor.class, () -> new org.springframework.boot.env.SystemEnvironmentPropertySourceEnvironmentPostProcessor());
    factories.add(org.springframework.boot.env.EnvironmentPostProcessor.class, () -> new org.springframework.boot.reactor.DebugAgentEnvironmentPostProcessor());
    factories.add(EnvironmentPostProcessor.class, () -> org.springframework.boot.autoconfigure.integration._FactoryProvider.integrationPropertiesEnvironmentPostProcessor());
    factories.add(EnvironmentPostProcessor.class, () -> org.springframework.boot.test.web._FactoryProvider.springBootTestRandomPortEnvironmentPostProcessor());
    names.add(FailureAnalyzer.class, "org.springframework.boot.web.context.MissingWebServerFactoryBeanFailureAnalyzer");
    names.add(FailureAnalyzer.class, "org.springframework.boot.context.config.ConfigDataNotFoundFailureAnalyzer");
    names.add(FailureAnalyzer.class, "org.springframework.boot.context.properties.IncompatibleConfigurationFailureAnalyzer");
    names.add(FailureAnalyzer.class, "org.springframework.boot.context.properties.NotConstructorBoundInjectionFailureAnalyzer");
    names.add(FailureAnalyzer.class, "org.springframework.boot.diagnostics.analyzer.BeanCurrentlyInCreationFailureAnalyzer");
    names.add(FailureAnalyzer.class, "org.springframework.boot.diagnostics.analyzer.BeanDefinitionOverrideFailureAnalyzer");
    names.add(FailureAnalyzer.class, "org.springframework.boot.diagnostics.analyzer.BeanNotOfRequiredTypeFailureAnalyzer");
    names.add(FailureAnalyzer.class, "org.springframework.boot.diagnostics.analyzer.BindFailureAnalyzer");
    names.add(FailureAnalyzer.class, "org.springframework.boot.diagnostics.analyzer.BindValidationFailureAnalyzer");
    names.add(FailureAnalyzer.class, "org.springframework.boot.diagnostics.analyzer.UnboundConfigurationPropertyFailureAnalyzer");
    names.add(FailureAnalyzer.class, "org.springframework.boot.diagnostics.analyzer.MutuallyExclusiveConfigurationPropertiesFailureAnalyzer");
    names.add(FailureAnalyzer.class, "org.springframework.boot.diagnostics.analyzer.NoSuchMethodFailureAnalyzer");
    names.add(FailureAnalyzer.class, "org.springframework.boot.diagnostics.analyzer.NoUniqueBeanDefinitionFailureAnalyzer");
    names.add(FailureAnalyzer.class, "org.springframework.boot.diagnostics.analyzer.PortInUseFailureAnalyzer");
    names.add(FailureAnalyzer.class, "org.springframework.boot.diagnostics.analyzer.ValidationExceptionFailureAnalyzer");
    names.add(FailureAnalyzer.class, "org.springframework.boot.diagnostics.analyzer.InvalidConfigurationPropertyNameFailureAnalyzer");
    names.add(FailureAnalyzer.class, "org.springframework.boot.diagnostics.analyzer.InvalidConfigurationPropertyValueFailureAnalyzer");
    names.add(FailureAnalyzer.class, "org.springframework.boot.diagnostics.analyzer.PatternParseFailureAnalyzer");
    names.add(FailureAnalyzer.class, "org.springframework.boot.liquibase.LiquibaseChangelogMissingFailureAnalyzer");
    names.add(FailureAnalyzer.class, "org.springframework.boot.web.embedded.tomcat.ConnectorStartFailureAnalyzer");
    names.add(FailureAnalyzer.class, "org.springframework.boot.autoconfigure.data.redis.RedisUrlSyntaxFailureAnalyzer");
    names.add(FailureAnalyzer.class, "org.springframework.boot.autoconfigure.diagnostics.analyzer.NoSuchBeanDefinitionFailureAnalyzer");
    names.add(FailureAnalyzer.class, "org.springframework.boot.autoconfigure.jdbc.DataSourceBeanCreationFailureAnalyzer");
    names.add(FailureAnalyzer.class, "org.springframework.boot.autoconfigure.jdbc.HikariDriverConfigurationFailureAnalyzer");
    names.add(FailureAnalyzer.class, "org.springframework.boot.autoconfigure.jooq.NoDslContextBeanFailureAnalyzer");
    names.add(FailureAnalyzer.class, "org.springframework.boot.autoconfigure.r2dbc.ConnectionFactoryBeanCreationFailureAnalyzer");
    names.add(FailureAnalyzer.class, "org.springframework.boot.autoconfigure.r2dbc.MissingR2dbcPoolDependencyFailureAnalyzer");
    names.add(FailureAnalyzer.class, "org.springframework.boot.autoconfigure.r2dbc.MultipleConnectionPoolConfigurationsFailureAnalzyer");
    names.add(FailureAnalyzer.class, "org.springframework.boot.autoconfigure.r2dbc.NoConnectionFactoryBeanFailureAnalyzer");
    names.add(FailureAnalyzer.class, "org.springframework.boot.autoconfigure.session.NonUniqueSessionRepositoryFailureAnalyzer");
    names.add(FailureAnalyzer.class, "org.springframework.nativex.GeneratedClassNotFoundExceptionFailureAnalyzer");
    names.add(FailureAnalyzer.class, "org.springframework.nativex.ClassNotFoundExceptionNativeFailureAnalyzer");
    names.add(FailureAnalyzer.class, "org.springframework.nativex.NoSuchMethodExceptionNativeFailureAnalyzer");
    factories.add(org.springframework.beans.BeanInfoFactory.class, () -> new org.springframework.beans.ExtendedBeanInfoFactory());
    factories.add(org.springframework.context.ApplicationContextInitializer.class, () -> new org.springframework.boot.context.ConfigurationWarningsApplicationContextInitializer());
    factories.add(org.springframework.context.ApplicationContextInitializer.class, () -> new org.springframework.boot.context.ContextIdApplicationContextInitializer());
    factories.add(org.springframework.context.ApplicationContextInitializer.class, () -> new org.springframework.boot.context.config.DelegatingApplicationContextInitializer());
    factories.add(org.springframework.context.ApplicationContextInitializer.class, () -> new org.springframework.boot.rsocket.context.RSocketPortInfoApplicationContextInitializer());
    factories.add(org.springframework.context.ApplicationContextInitializer.class, () -> new org.springframework.boot.web.context.ServerPortInfoApplicationContextInitializer());
    factories.add(ApplicationContextInitializer.class, () -> org.springframework.boot.autoconfigure._FactoryProvider.sharedMetadataReaderFactoryContextInitializer());
    factories.add(org.springframework.context.ApplicationContextInitializer.class, () -> new org.springframework.boot.autoconfigure.logging.ConditionEvaluationReportLoggingListener());
    factories.add(org.springframework.boot.logging.LoggingSystemFactory.class, () -> new org.springframework.boot.logging.logback.LogbackLoggingSystem.Factory());
    factories.add(org.springframework.boot.logging.LoggingSystemFactory.class, () -> new org.springframework.boot.logging.log4j2.Log4J2LoggingSystem.Factory());
    factories.add(org.springframework.boot.logging.LoggingSystemFactory.class, () -> new org.springframework.boot.logging.java.JavaLoggingSystem.Factory());
    factories.add(org.springframework.boot.env.PropertySourceLoader.class, () -> new org.springframework.boot.env.PropertiesPropertySourceLoader());
    factories.add(org.springframework.boot.env.PropertySourceLoader.class, () -> new org.springframework.boot.env.YamlPropertySourceLoader());
  }
}
