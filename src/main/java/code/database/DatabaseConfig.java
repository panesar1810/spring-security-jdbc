package code.database;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class DatabaseConfig {
	
	@Bean
    protected DataSource datasource() {
        return DataSourceBuilder.create()
        .driverClassName("com.mysql.cj.jdbc.Driver")
        .url("jdbc:mysql://us-cdbr-east-02.cleardb.com/heroku_a6cd8e95fe8a3f3")
        .username("be1a3ff398cd29").password("5ccb85c8").build(); 
    }
	
    @Bean 
    protected NamedParameterJdbcTemplate getNamedTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
    
    @Bean
    protected JdbcTemplate getJdbcTemplate(DataSource dataSource) {
    	return new JdbcTemplate(dataSource);
    }
    
}
