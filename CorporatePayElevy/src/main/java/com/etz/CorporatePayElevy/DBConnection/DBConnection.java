package com.etz.CorporatePayElevy.DBConnection;



	import java.sql.Connection;
	import java.sql.DriverManager;



	public class DBConnection {
		
			
			private static Connection connection ;
			
			public static void main(String [] args) {
				
				System.out.println(dbConnection( ));
				
			}
			public static Connection dbConnection( ){
				try {
					
			    String jdbcURL = Config.getValue("jdbcURL");
				String dbusername = Config.getValue("dbusername");
				String password = Config.getValue("password");
			    //Class.forName(Config.getProperty("driver"));
				  Class.forName("com.mysql.cj.jdbc.Driver");
			    connection = DriverManager.getConnection(jdbcURL,dbusername,password);

				
			}	 catch (Exception e) {
				e.printStackTrace();
			}
			return connection;
		}
				}