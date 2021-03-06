package vn.edu.uit.realestate.Configuration;

import java.util.HashMap;
import javax.sql.DataSource;

import org.neo4j.ogm.session.SessionFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import vn.edu.uit.realestate.Common.Common;

@Configuration
@EnableNeo4jRepositories(basePackages = Common.Constains.GRAPH_REPOSITORY_PACKAGE)
@EnableJpaRepositories(basePackages = Common.Constains.RELATIONAL_REPOSITORY_PACKAGE, transactionManagerRef = "mysqlTransactionManager")
@EnableTransactionManagement
public class DataSourceConfiguration {
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(DataSourceConfiguration.class);
	@Value("${spring.data.neo4j.uri}")
	private String neo4jBoltDriver;	
	@Value("${spring.data.neo4j.username}")
	private String neo4jUsername;
	@Value("${spring.data.neo4j.password}")
	private String neo4jPassword;
	
	@Bean
	public org.neo4j.ogm.config.Configuration configuration() {
		org.neo4j.ogm.config.Configuration configuration = new org.neo4j.ogm.config.Configuration.Builder()
				.uri(neo4jBoltDriver).credentials(neo4jUsername, neo4jPassword).build();
		return configuration;
	}

	@Bean
	public SessionFactory sessionFactory() {
		return new SessionFactory(configuration(), Common.Constains.GRAPH_MODEL_PACKAGE);
	}

	@Primary
	@Bean(name = "dataSource")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create()
				.build();
	}

	@Primary
	@Bean(name = "entityManagerFactory")
	@Autowired
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("dataSource") DataSource dataSource) {
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
		return builder.dataSource(dataSource)
				.properties(properties)
				.packages(Common.Constains.RELATIONAL_MODEL_PACKAGE)
				.build();
	}
	
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	@Autowired
	@Bean(name = "neo4jTransactionManager")
	public Neo4jTransactionManager neo4jTransactionManager() {
		return new Neo4jTransactionManager(sessionFactory());
	}

	@Autowired
	@Primary
	@Bean(name = "mysqlTransactionManager")
	public JpaTransactionManager mysqlTransactionManager(@Qualifier("entityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory)
			throws Exception {
		return new JpaTransactionManager(entityManagerFactory.getObject());
	}
	
	@Autowired
	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager(Neo4jTransactionManager neo4jTransactionManager,
			JpaTransactionManager mysqlTransactionManager) {
		return new ChainedTransactionManager(mysqlTransactionManager, neo4jTransactionManager);
	}
}