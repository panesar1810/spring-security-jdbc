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
        .url("jdbc:mysql://localhost:3306/security")
        .username("root").password("MySQL$1810").build(); 
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
