package guru.springframework.config;

import org.jooq.SQLDialect;
import org.jooq.conf.RenderNameCase;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.jooq.impl.DefaultExecuteListenerProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;

@Configuration
public class JooqConfig {

    @Bean
    public DefaultConfiguration configuration(@Qualifier("dataSource") DataSource dataSource,
                                              ExceptionTranslator exceptionTranslator) {
        TransactionAwareDataSourceProxy dataSourceProxy = new TransactionAwareDataSourceProxy(dataSource);
        DataSourceConnectionProvider connectionProvider = new DataSourceConnectionProvider(dataSourceProxy);
        DefaultConfiguration jooqConfiguration = new DefaultConfiguration();
        jooqConfiguration.set(connectionProvider);
        jooqConfiguration.settings().setRenderNameCase(RenderNameCase.AS_IS);
        jooqConfiguration.set(new DefaultExecuteListenerProvider(exceptionTranslator));

        jooqConfiguration.set(SQLDialect.MYSQL);

        return jooqConfiguration;
    }

    @Bean
    public ExceptionTranslator exceptionTransformer() {
        return new ExceptionTranslator();
    }

    @Bean
    public DefaultDSLContext dsl(DefaultConfiguration configuration) {
        return new DefaultDSLContext(configuration);
    }
}
