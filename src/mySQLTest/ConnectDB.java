package mySQLTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.mysql.jdbc.ResultSetRow;
//import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;
import com.thoughtworks.selenium.SeleneseTestBase;

public class ConnectDB extends SeleneseTestBase
{
     WebDriver driver;
     String url ="";
     @BeforeTest

     public void setUp() throws Exception
     {

     }
     
     @Test
     public void CreateDB() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
     {
           //Prepare connection
           String url1 ="jdbc:mysql://10.112.13.59:3306/dev_content";
    	 //String url1 ="jdbc:mysql://mysql.qa-027.dp.discovery.com:3306/dev_content";
           // Load Microsoft SQL Server JDBC driver
           String dbClass = "com.mysql.jdbc.Driver";
           Class.forName(dbClass).newInstance();
           //Get connection to DB
           Connection con = DriverManager.getConnection(url1, "qa", "root");
           //Connection con = DriverManager.getConnection(url1, "qa","92nao2901"); // These are user and password from the DatabaseTasks.java file from qa-dws
           //Create Statement
           Statement stmt = (Statement) con.createStatement();
           // method which returns the requested information as rows of data
           // This is a test query. For demo purposes, the query should be executed twice, once before Vampire runs and updates
           // a specific video and the second time, after Vampire runs, in order to return the new status of the modified_by_etb field.
           // If all the videos are supposed to start with a NULL before Vampire runs, the query can be run just once, filtering for a 
           // specific video id 
           ResultSet result = (ResultSet) stmt.executeQuery("select dws_video_id from dev_content where modified_by_etb = NULL");
           while(result.next())
           {
        	   System.out.print(result.getString("dws_video_id"));
//        	   System.out.print(result.toString());
           }
     }
    
     @AfterTest
     public void tearDown()
     {
    	 driver.close();
     }
}