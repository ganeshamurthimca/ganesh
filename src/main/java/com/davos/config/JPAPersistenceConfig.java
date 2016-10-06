/**
 * 
 */
package com.davos.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author vigneshwaran.b
 *
 */
@Configuration
@EnableJpaRepositories(basePackages = { "com.davos.repository" })
@PropertySource(value = { "classpath:config/db.properties" })
@EnableTransactionManagement
public class JPAPersistenceConfig {

	/*
	 * JDBC Constants
	 */
	private static final String PROPERTY_DB_DRIVER = "db.driver";
	private static final String PROPERTY_DB_URL = "db.url";
	private static final String PROPERTY_DB_USERNAME = "db.username";
	private static final String PROPERTY_DB_PASSWORD = "db.password";
	// private static final String PROPERTY_INIT_DATABASE = "init-db";

	/*
	 * Hibernate Constants
	 */
	// private static final String PROPERTY_HIBERNATE_DIALECT =
	// "${hibernate.dialect}";
	private static final String PROPERTY_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	private static final String PROPERTY_HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
	private static final String PROPERTY_HIBERNATE_GENERATEDDL = "hibernate.generateddl";

	/*
	 * JPA Contants
	 */
	private static final String PROPERTY_PACKAGE_TO_SCAN = "com.davos.domain";

	@Autowired
	private Environment env;

	@Bean
	@Qualifier(value = "transactionManager")
	public PlatformTransactionManager transactionManager() {
		EntityManagerFactory entityManagerFactory = entityManagerFactory().getObject();
		return new JpaTransactionManager(entityManagerFactory);
	}

	@Bean
	@Qualifier(value = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		localContainerEntityManagerFactoryBean.setDataSource(dataSource());
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter());
		localContainerEntityManagerFactoryBean.setPackagesToScan(PROPERTY_PACKAGE_TO_SCAN);
		localContainerEntityManagerFactoryBean.setJpaProperties(properties());
		localContainerEntityManagerFactoryBean.afterPropertiesSet();
		localContainerEntityManagerFactoryBean.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
		return localContainerEntityManagerFactoryBean;
	}

	@Bean
	@Qualifier(value = "dataSource")
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty(PROPERTY_DB_DRIVER));
		dataSource.setUrl(env.getProperty(PROPERTY_DB_URL));
		dataSource.setUsername(env.getProperty(PROPERTY_DB_USERNAME));
		dataSource.setPassword(env.getProperty(PROPERTY_DB_PASSWORD));

		return dataSource;
	}

	@Bean
	public HibernateExceptionTranslator hibernateExceptionTranslator() {
		return new HibernateExceptionTranslator();
	}

	@Bean
	public DataSourceInitializer dataSourceInitializer() {
		DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
		dataSourceInitializer.setDataSource(dataSource());
		if (env.getProperty(PROPERTY_HIBERNATE_HBM2DDL_AUTO).equalsIgnoreCase("create")) {

			ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
			resourceDatabasePopulator.addScript(new ClassPathResource("/config/insert.sql"));

			dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
		}

		return dataSourceInitializer;

	}

	@Bean
	public HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setGenerateDdl(Boolean.parseBoolean(PROPERTY_HIBERNATE_GENERATEDDL));
		hibernateJpaVendorAdapter.setShowSql(Boolean.parseBoolean(PROPERTY_HIBERNATE_SHOW_SQL));
		return hibernateJpaVendorAdapter;
	}

	public Properties properties() {
		Properties properties = new Properties();
		properties.setProperty(PROPERTY_HIBERNATE_HBM2DDL_AUTO, env.getProperty(PROPERTY_HIBERNATE_HBM2DDL_AUTO));
		/*
		 * Below Code will help to audit the table
		 */
		properties.setProperty("org.hibernate.envers.audit_table_suffix", "_audit_log");
		return properties;
	}
}
