package cn.edu.hust.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "cn.edu.hust.repository")
@EnableTransactionManagement
public class JPAConfig {
    @Bean
    @ConfigurationProperties(prefix ="spring.datasource")
    public DataSource dataSource()
    {
        return DataSourceBuilder.create().build();
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean()
    {
        HibernateJpaVendorAdapter jpaVendorAdapter=new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setGenerateDdl(false);
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean=new LocalContainerEntityManagerFactoryBean();
        //设置数据源
        localContainerEntityManagerFactoryBean.setDataSource(dataSource());
        //设置JPA适配器
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        //设置实体扫描的包名
        localContainerEntityManagerFactoryBean.setPackagesToScan("cn.edu.hust.domain");
        return localContainerEntityManagerFactoryBean;
    }

    /**
     * 设置事务管理
     * @param entityManagerFactory
     * @return
     */
    @Bean
    public PlatformTransactionManager platformTransactionManager(EntityManagerFactory entityManagerFactory)
    {
        JpaTransactionManager jpaTransactionManager=new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        return jpaTransactionManager;
    }
}
