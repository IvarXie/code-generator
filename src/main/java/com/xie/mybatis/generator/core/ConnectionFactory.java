package com.xie.mybatis.generator.core;

import com.xie.mybatis.generator.entity.Database;
import com.xie.mybatis.generator.utils.YamlUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author xie.wenbo
 * @Description TODO
 * @CreationDate: 2018-08-27 11:58
 */
public class ConnectionFactory {
	private Database database = YamlUtils.getMysqlProperties().getDatabase();
	private BlockingQueue<Connection> connectionPool = new LinkedBlockingQueue<>();
	private final byte[] lock = new byte[0];

	/**
	 * 获取连接，连接池未满则创建连接并放入连接池先
	 * 
	 * 从连接池取出一个连接
	 * @return connection
	 */
	public Connection getConnection() {
		synchronized (lock) {
			try {
				Connection connection;
				//连接池未满，则创建新连接,并放入连接池
				if (connectionPool.size() < database.getPoolSize()) {
					connection = createNewConnection();
					connectionPool.put(connection);
				//	return connection;
				} 
				connection = connectionPool.take();
				return connection;
				
			} catch (ClassNotFoundException | SQLException | InterruptedException e) {
				throw new RuntimeException(e);
			}
		}

	}

	
	/**
	 * 创建新连接
	 * @return connection
	 * @Throws ClassNotFoundException
	 * @Throws SQLException
	 */
	
	private Connection createNewConnection() throws ClassNotFoundException, SQLException {
		Connection connection;
		Class.forName(database.getDriver());
		connection = DriverManager.getConnection(database.getUrl(), database.getUsername(), database.getPassword());
		return connection;
	}

    /**
	 * 关闭连接，重新放入连接池
	 * @param connection
	 */
	public void close(Connection connection) {
		synchronized (lock) {
			connectionPool.add(connection);
		}
	}

	private ConnectionFactory(){
	}
	
	public static ConnectionFactory getInstance(){
		return FactoryHolder.factory;
	}
	
	private static class FactoryHolder{
		static ConnectionFactory factory=new ConnectionFactory();
	}

}
