package tenx.spring.data.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class JpaConfiguration {
	@Value("#{dataSource}")
	private javax.sql.DataSource dataSource;

	@Value("#{jpaProperties}")
	private Properties jpaProperties;

	@Autowired
	private JpaVendorAdapter jpaVendorAdapter;

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager(
				entityManagerFactory().getObject());
	}

	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
		lef.setDataSource(this.dataSource);
		lef.setJpaProperties(jpaProperties);
		lef.setJpaVendorAdapter(jpaVendorAdapter);
		lef.setPackagesToScan("tenx.spring.data.domain");
		lef.setMappingResources("META-INF/orm.xml");
		return lef;
	}
}
