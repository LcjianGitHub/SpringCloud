package com.lcjian.db;

import com.alibaba.druid.pool.DruidDataSource;
import com.asiainfo.exception.BusienssException;
import com.asiainfo.utils.ExceptionConstants;
import com.asiainfo.utils.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * 数据源工具类
 * @author lcjian
 */
@Configuration
@MapperScan(basePackages = "com.lcjian.demo.mapper")
public class DataSourceUtil {

    public DataSourceUtil() {
    }

    private static ResourceBundle env = ResourceBundle.getBundle("cloud-query-config");

//    @Autowired
//    private Environment env;

    /**
     * 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错
     * @return
     * @throws Exception
     */
    @Bean
    @Primary
    public DynamicDataSource dataSource() throws Exception {
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        String dbSourceNames = env.getString("dbSource.names");
        DataSource defaultSource = null;
        if(!StringUtils.isEmpty(dbSourceNames)){
        	String[] dbSourceName = dbSourceNames.split(",");
        	for(int i=0;i<dbSourceName.length;i++){
        		String sourceName = dbSourceName[i].split(":")[0];
        		String isDef = dbSourceName[i].split(":")[1];
        		targetDataSources.put(sourceName, mysqlDataSource(sourceName));
        		if("0".equals(isDef)){ //0表示为默认数据源
        			defaultSource = mysqlDataSource(sourceName);
        		}
        	}
        }else{
        	throw new BusienssException(ExceptionConstants.DBSOURCE_NAMES_EXCEPTION_01,"数据源节点为空，请查看配置信息是否正确！");
        }
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);        //该方法是AbstractRoutingDataSource的方法
        dataSource.setDefaultTargetDataSource(defaultSource);      //默认的datasource设置为myTestDbDataSource
        return dataSource;
    }

    /**
     * 获取相应的数据源对象
     * @param dbSourceName  //数据源名称
     * @return
     * @throws Exception
     */
    public DataSource mysqlDataSource(String dbSourceName) throws Exception{
        DruidDataSource druidDataSource = new DruidDataSource();
        String url = env.getString(dbSourceName+".url");
        if(StringUtils.isEmpty(url)){
        	throw new BusienssException(ExceptionConstants.DBSOURCE_NAMES_EXCEPTION_02,"详细数据源配置有问题，请查看配置是否正确！");
        }
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(env.getString(dbSourceName+".username"));
        druidDataSource.setPassword(env.getString(dbSourceName+".password"));
        druidDataSource.setDriverClassName(env.getString(dbSourceName+".driverClassName"));
        druidDataSource.setInitialSize(Integer.parseInt(env.getString(dbSourceName+".initialSize")));
        druidDataSource.setMaxActive(Integer.parseInt(env.getString(dbSourceName+".maxActive")));
        druidDataSource.setMinIdle(Integer.parseInt(env.getString(dbSourceName+".minIdle")));
        druidDataSource.setMaxWait(Integer.parseInt(env.getString(dbSourceName+".maxWait")));
        druidDataSource.setTimeBetweenEvictionRunsMillis(Integer.parseInt(env.getString(dbSourceName+".timeBetweenEvictionRunsMillis")));
        druidDataSource.setMinEvictableIdleTimeMillis(Integer.parseInt(env.getString(dbSourceName+".minEvictableIdleTimeMillis")));
        druidDataSource.setValidationQuery(env.getString(dbSourceName+".validationQuery"));
        druidDataSource.setTestWhileIdle(Boolean.parseBoolean(env.getString(dbSourceName+".testWhileIdle")));
        druidDataSource.setTestOnBorrow(Boolean.parseBoolean(env.getString(dbSourceName+".testOnBorrow")));
        druidDataSource.setTestOnReturn(Boolean.parseBoolean(env.getString(dbSourceName+".testOnReturn")));
        druidDataSource.setPoolPreparedStatements(Boolean.parseBoolean(env.getString(dbSourceName+".poolPreparedStatements")));
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(Integer.parseInt(env.getString(dbSourceName+".maxPoolPreparedStatementPerConnectionSize")));
        druidDataSource.setFilters(env.getString(dbSourceName+".filters"));
        try {
            if(null != druidDataSource) {
                druidDataSource.setFilters("wall,stat");
                druidDataSource.setUseGlobalDataSourceStat(true);
                druidDataSource.init();
            }
        } catch (Exception e) {
            throw new RuntimeException("load datasource error, dbProperties is :", e);
        }
        return druidDataSource;
    }

    /**
     * 根据数据源创建SqlSessionFactory
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory mysqlSessionFactoryBean(DynamicDataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 配置事务管理器
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }
}
