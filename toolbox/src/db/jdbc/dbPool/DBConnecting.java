package db.jdbc.dbPool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import com.mysql.jdbc.IterateBlock;

public class DBConnecting {
	private static DBConnecting DBCon = null;
	public List<Connection> freeConList = null;
	public PreparedStatement preparedStatement = null;
	private static int POOL_FREE_NUM = 0;
	private int pool_count = 0;
	private DBConnecting(){
		FileInputStream fisContext = null;
		Properties context = null;
		try {
			//String absolutePath = new File("").getAbsolutePath();
			//System.out.println("路径====="+absolutePath);
			//fisContext = new FileInputStream(absolutePath+"/WebContent/config/context.properties");
			fisContext = new FileInputStream("D:/program/workspacing/YummyDish/WebContent/config/context.properties");
			context = new Properties();
			context.load(fisContext);
			this.pool_count = POOL_FREE_NUM = Integer.parseInt(context.getProperty("jdbc.pool.count"));
			System.out.println("连接池大小：-======"+POOL_FREE_NUM);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			context.clear();
			try {
				fisContext.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.freeConList = new ArrayList<Connection>();
		this.creatConnection();
	}
	public static DBConnecting getDBConInstance(){
		if (DBCon == null) {
			DBCon = new DBConnecting();
		}
		return DBCon;
	}
	private Connection creatConnection(){
		FileInputStream fisContext = null;
		Properties context = null;
		Connection tmpCon = null;
		try{
			System.out.println("连接池还可创建个数==="+POOL_FREE_NUM);
			if (POOL_FREE_NUM > 0) {
//				String absolutePath = new File("").getAbsolutePath();
//				fisContext = new FileInputStream(absolutePath+"/WebContent/config/context.properties");
				fisContext = new FileInputStream("D:/program/workspacing/YummyDish/WebContent/config/context.properties");
				context = new Properties();
				context.load(fisContext);
				StringBuffer JDBCUrl = new StringBuffer("jdbc:");
				JDBCUrl.append(context.getProperty("jdbc.name")).append("://").append(context.getProperty("jdbc.host"))
				.append("/").append(context.getProperty("jdbc.database")).append("?user=").append(context.getProperty("jdbc.user"))
				.append("&password=").append(context.getProperty("jdbc.password")).append("&useUnicode=")
				.append(context.getProperty("jdbc.useUnicode")).append("&characterEncoding=")
				.append(context.getProperty("jdbc.characterEncoding"));
				System.out.println(JDBCUrl);
				System.out.println("建立sql连接开始！已创建连接数===="+(this.pool_count-POOL_FREE_NUM)+"连接池可用连接个数------------"+freeConList.size());
				Class.forName("com.mysql.jdbc.Driver");
				tmpCon = DriverManager.getConnection(JDBCUrl.toString());
				freeConList.add(tmpCon);
				POOL_FREE_NUM--;
				System.out.println("建立sql连接完成！已创建连接数===="+(this.pool_count-POOL_FREE_NUM)+"连接池可用连接个数----------"+freeConList.size());
			}else{
				System.out.println("连接池已满！");
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e1){
			e1.printStackTrace();
		}catch(ClassNotFoundException e2){
			e2.printStackTrace();
		}catch(SQLException e3){
			e3.printStackTrace();
		}finally{
			if (context != null) {
				context.clear();
			}
			try {
				if (fisContext != null) {
					fisContext.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return tmpCon;
	}
	
	public  synchronized Connection  getConnection (){
		Connection currCon = null;
		System.out.println("list中有111---"+freeConList.size()+"-----个空闲资源");
		if (freeConList.size() == 0) {
			System.out.println("list中有000---"+freeConList.size()+"-----个空闲资源，尝试创建一个新的链接");
			this.creatConnection();
			if(freeConList.size() == 0) return currCon;
		}
		currCon = freeConList.get(0);
		System.out.println("拿到有效资源");
		freeConList.remove(currCon);
		System.out.println("空闲资源减少一个=--------"+freeConList.size());
		return currCon;
	}
	
	public  PreparedStatement getPreStatm(Connection cCon , String sql){
		try {
			this.preparedStatement = cCon.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.preparedStatement;
	}
	public void release(Connection rCon){
			System.out.println("释放链接!");
			freeConList.add(rCon);
			System.out.println("空闲资源个数======"+freeConList.size());
	}
	
	public  void close(){
		try {
			this.preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Iterator<Connection> it = freeConList.iterator();
			while (it.hasNext()){
				Connection tmpCon = it.next();
				tmpCon.close();
				POOL_FREE_NUM++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
