package com.page;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Imdb {
	public Imdb() {
		// TODO Auto-generated constructor stub
	}

	WebDriver driver;

	public List<String> MovieNamesList = new ArrayList<String>();
	public List<String> MovieReleaseYearList = new ArrayList<String>();
	public List<String> MoviesRatingList = new ArrayList<String>();
	int m1;
	int y1;
	int r1;
	By movieNames = By.xpath("//*[@id='main']/div/span/div/div/div[3]/table/tbody/tr/td[2]/a");
	By movieYear = By.xpath(".//*[@id='main']/div/span/div/div/div[3]/table/tbody/tr/td[3]/strong");
	By movieRating = By.xpath("//*[@id='main']/div/span/div/div/div[3]/table/tbody/tr/td[2]/span");

	public Imdb(WebDriver driver) {
		this.driver = driver;
		// PageFactory.initElements(driver, this);
	}

	public List<String> getMovieNames() {
		ArrayList<WebElement> movieName = (ArrayList<WebElement>) driver.findElements(movieNames);
		for (int i = 0; i < movieName.size(); i++) {
			String movieNames = movieName.get(i).getText();
			MovieNamesList.add(movieNames);
		}
		return MovieNamesList;
	}

	public List<String> getMovieReleaseYears() {
		ArrayList<WebElement> moviesYears = (ArrayList<WebElement>) driver.findElements(movieYear);
		for (int i = 0; i < moviesYears.size(); i++) {
			String year = moviesYears.get(i).getText();
			MovieReleaseYearList.add(year);
		}
		return MovieReleaseYearList;
	}

	public List<String> getMovieRatings() {
		ArrayList<WebElement> rating = (ArrayList<WebElement>) driver.findElements(movieRating);
		for (int i = 0; i < rating.size(); i++) {
			String movieRating = rating.get(i).getText();
			MoviesRatingList.add(movieRating);
		}
		return MoviesRatingList;
	}
}