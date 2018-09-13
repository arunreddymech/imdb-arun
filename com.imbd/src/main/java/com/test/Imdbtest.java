package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.page.Imdb;

import databaseconnection.DBUtil;

public class Imdbtest {
	Imdb ib;
	WebDriver driver;
	WebDriverWait w;
	ArrayList<WebElement> moviename;
	ArrayList<WebElement> releasedyear;
	ArrayList<WebElement> ratings;

	@BeforeClass
	public void method1() {
		System.setProperty("webdriver.chrome.driver", "E://batch237//chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.imdb.com/chart/top");
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		ib = new Imdb(driver);
	}

	@Test
	public void InsertMovieDataToDatabse() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getDBConnection();
			String query = "INSERT INTO MOVIES(MOVIENAME,MOVIEYEAR,MOVIERATING) VALUES(?,?,?)";
			pstmt = conn.prepareStatement(query);
			Iterator<String> movieNames = ib.getMovieNames().iterator();
			Iterator<String> movieReleaseYear = ib.getMovieReleaseYears().iterator();
			Iterator<String> movieRating = ib.getMovieRatings().iterator();
			while (movieNames.hasNext() && movieReleaseYear.hasNext() && movieRating.hasNext()) {
				pstmt.setString(1, movieNames.next());
				pstmt.setString(2, movieReleaseYear.next());
				pstmt.setString(3, movieRating.next());
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			conn.close();
			pstmt.close();
		}
		System.out.println("Successfuly inserted!!!!!!!!!!!!!!");
	}

	/*
	 * @Test public void year() { ib.getMovieYears(); }
	 * 
	 * @Test public void rating() { ib.getRatings(); }
	 */

	@AfterClass
	public void method2() {
		driver.close();
	}

}
