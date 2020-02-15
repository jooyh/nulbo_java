package configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import net.sf.log4jdbc.tools.Log4JdbcCustomFormatter;
import net.sf.log4jdbc.tools.LoggingType;

@Configuration
public class RootConfiguration {
	
	private static final String APP_CONFIG_FILE_PATH = "props/database.properties";
	
	@Value("${db.driverClass}")
	private Class jdbcDriverClassName; 
	@Value("${db.url}")
	private String url; 
	@Value("${db.username}")
	private String userName; 
	@Value("${db.password}")
	private String password; 
	
	@Bean 
	public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() { 
		PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
		ppc.setLocations(new Resource[] { new ClassPathResource(APP_CONFIG_FILE_PATH) }); 
		return ppc; 
	}
	@Bean 
	public DataSource dataSourceSpied() { 
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource(); 
		dataSource.setDriverClass(this.jdbcDriverClassName); 
		dataSource.setUrl(this.url); 
		dataSource.setUsername(this.userName); 
		dataSource.setPassword(this.password); 
		return dataSource; 
	}
	@Bean
	public DataSource dataSource() {
		DataSource dataSource =  this.dataSourceSpied();
		Log4JdbcCustomFormatter logFormatter = new Log4JdbcCustomFormatter();
		logFormatter.setLoggingType(LoggingType.MULTI_LINE);
		logFormatter.setSqlPrefix("SQL         :  ");
		return dataSource;
	}
	
	@Bean
	public SqlSessionFactoryBean sqlSessionFactory() {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(this.dataSource());
		sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
		Resource [] rs = {
			new ClassPathResource("mappers/userService.xml")
		};
		sqlSessionFactoryBean.setMapperLocations(rs);
		return sqlSessionFactoryBean;
	}
	
	 @Bean 
	 public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
		 return new SqlSessionTemplate(sqlSessionFactory);
	 }
}
