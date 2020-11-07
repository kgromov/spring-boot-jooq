package guru.springframework.config;

import com.zaxxer.hikari.HikariDataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "guru.springframework")
@EnableTransactionManagement
public class DataBaseConfig {
    @Autowired
    private Environment env;

    @PostConstruct
    public void init() {
        String[] profiles = env.getActiveProfiles();
    }

    @Bean("dataSource")
    @Profile({"default", "test"})
    public DataSource dataSourceForTest() {
        return new EmbeddedDatabaseBuilder()
            .generateUniqueName(true)
            .setType(EmbeddedDatabaseType.H2)
            .setScriptEncoding("UTF-8")
            .ignoreFailedDrops(true)
            .addScript("schema.sql")
            .addScripts("data.sql")
            .build();
    }

    @Bean("mySqlProperties")
    @Primary
    @Profile({"dev", "prod"})
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean("dataSource")
    @Profile({"dev", "prod"})
    public DataSource dataSource(@Qualifier("mySqlProperties") DataSourceProperties properties) {
        return properties
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                // seems here could be set another properties aka: {defaultRowPrefetch, defaultBatchValue}
//                .setDataSourceProperties()
                .build();
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("dataSource") DataSource db) {
//        Map<String, String> properties = new HashMap<>();
//        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        return builder
                .dataSource(db)
                .packages("guru.springframework")
                .persistenceUnit("mySql")
//                .properties(properties)
                .build();
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactory") EntityManagerFactory db1EntityManagerFactory) {
        return new JpaTransactionManager(db1EntityManagerFactory);
    }
//    @Bean
    // Is used for Spring; for SpringBoot is redundant:
    // changeLog default location - db/changelog/db.changelog-master.yaml
    // or could be specified via spring.liquibase.change-log property
    public SpringLiquibase liquibase(@Qualifier("dataSource") DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:db-changelog/master.xml");
        liquibase.setDataSource(dataSource);
        return liquibase;
    }
}
