package springConfig;

import java.util.Properties;
import java.util.TimeZone;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration("Config")
@EnableTransactionManagement
@EnableWebSecurity
@ComponentScan(basePackages = { "service", "dao", "dto", "validator", "model" })
public class Config {

	@PersistenceContext
	EntityManager em;

	@Bean
	public DataSource dataSource(HikariConfig config) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		return new HikariDataSource(config);
	}

	@Bean
	public HikariConfig hikariConfig() {
		return new HikariConfig("/hikari.properties");
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setPackagesToScan(new String[] { "model" });
		sessionFactory.setHibernateProperties(hibernateProperties());

		return sessionFactory;
	}

	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		txManager.setNestedTransactionAllowed(true);

		return txManager;
	}

	@Bean
	public JPAQueryFactory jpaQueryFactory() {
		return new JPAQueryFactory(em);
	}

	private Properties hibernateProperties() {
		return new Properties() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				setProperty("hibernate.hbm2ddl.auto", "validate");
				setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLInnoDBDialect");
				setProperty("hibernate.globally_quoted_identifiers", "true");
			}
		};
	}

}
