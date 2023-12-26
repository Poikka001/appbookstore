package mate.academy.intro.util;

import mate.academy.intro.mapper.BookMapper;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            PooledDataSource dataSource = new PooledDataSource();
            dataSource.setDriver("your_database_driver");
            dataSource.setUrl("your_database_url");
            dataSource.setUsername("your_database_username");
            dataSource.setPassword("your_database_password");

            Environment environment = new Environment("development", new org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory(), dataSource);

            Configuration configuration = new Configuration(environment);
            configuration.addMapper(BookMapper.class); // Add your mapper class

            sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error initializing MyBatisUtil: " + e.getMessage());
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}

