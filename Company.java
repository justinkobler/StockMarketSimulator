import java.util.ArrayList;
import java.util.Random;
import javafx.scene.paint.Color;

public class Company
{
 private String name;
 private double price;
 private double percentChange;
 private double dollarChange;
 private ArrayList<Double> pastPrices = new ArrayList<>();
 private News currentNews;
 private ArrayList<News> listOfNews = new ArrayList<>();
 private Color color;
 private int numOfSharesOwned;
 private double dividend;
 
 public Company()
 {
  this.generateNewsList();
  this.name = null;
  this.price = 0.0;
  this.percentChange = 0.0;
  this.dollarChange = 0.0;
  this.currentNews = chooseNews();
  Random r = new Random();
  this.dividend = r.nextDouble()*4+1;
 }
 public Company(String name, double price)
 {
  this.name = name;
  this.generateNewsList();
  this.price = price;
  this.percentChange = 0.0;
  this.dollarChange = 0.0;
  this.currentNews = chooseNews();
  this.color = Color.GREEN;
  Random r = new Random();
  this.dividend = r.nextDouble()*4+1;
 }
 public Company(String name, double price, Color color)
 {
  this.name = name;
  this.generateNewsList();
  this.price = price;
  this.percentChange = 0.0;
  this.dollarChange = 0.0;
  this.currentNews = chooseNews();
  this.color = color;
  this.pastPrices.add(this.price);
  Random r = new Random();
  this.dividend = r.nextDouble()*4+1; 
 }
 public Company(String name, double price, double percentChange, double dollarChange, Color color, int numOfSharesOwned, double dividend)
 {
  this.name = name;
  this.generateNewsList();
  this.price = price;
  this.percentChange = percentChange;
  this.dollarChange = dollarChange;
  this.color = color;
  this.dividend = dividend;
  this.numOfSharesOwned = numOfSharesOwned;
  Random r = new Random();
  this.dividend = dividend;
 }
 
 private void generateNewsList()
 {
  listOfNews.add(new News("New CEO", name + " has hired a new CEO.", 35.0, -15.0));
  listOfNews.add(new News("New Product Released", name + " released a highly anticipated new product.", 65.0, 30.0));
  listOfNews.add(new News("Fraud Trial", name + " has been found guilty of fraud by the SEC.", -25.0, -45.0));
  listOfNews.add(new News("Employees Laid Off", name + " looks to layoff a large amount of employees.", 10.0, -10.0));
  listOfNews.add(new News("Product Recall", name + " has recalled a new product.", 0.0, -15.0));
  listOfNews.add(new News("New Product Released", name + " has released a new product.", 25.0, 0.0));
  listOfNews.add(new News("Stock Buyback", name + " buys back many of its shares.", 35.0, 10.0));
  listOfNews.add(new News("Stock Splits", name + " splits stocks in hopes that more will look to invest.", -15.0, -40.0));
  listOfNews.add(new News("Competitor Declares Bankruptcy", name + "'s major competitor declares bankruptcy.", 75.0, 20.0));
  listOfNews.add(new News("Missed Earnings", name + " misses earnings by a significant amount.", -10.0, -45.0));
  listOfNews.add(new News("Beat Earnings", name + " beats earnings by a significant amount.", 25.0, 5.0));
  listOfNews.add(new News("Expired Patent", name + "'s patent expires on high selling product.", 0, -45.0));
  listOfNews.add(new News("Competitor Develops Product", name + "'s major competitor develops a groundbreaking new product.", -10.0, -45.0));
  listOfNews.add(new News("Major Lawsuit", name + " receives major lawsuit.", -10.0, -55.0));
  listOfNews.add(new News("Merge with Larger Company", name + " merges with a larger company.", 10.0, 1.0));
  listOfNews.add(new News("Merge with Smaller Company", name + " merges with a smaller company.", 15.0, -10.0));
  listOfNews.add(new News("Store Closings", name + " closes several of its stores/locations in an effort to reduce expenses.", 2.0, -2.0));
  listOfNews.add(new News("Product Leads the Market", "Reports show that " + name + "'s products continue to lead the market", 45.0, 25.0));
  listOfNews.add(new News("Competitor's Products Favored", "According to surveys conducted within the general public, consumers typically prefer the products of " + name + "'s major competitor rather than" + name + "'s products", -10.0, -40.0));
  listOfNews.add(new News("Lawsuit of Another Company", name + " sues a competitor for violating a patent on their product", 40.0, 10.0));
 }
 public News chooseNews()
 {
  Random r = new Random();
  this.currentNews = listOfNews.get(r.nextInt(listOfNews.size()));
  return currentNews;
 }
 
 public String getName()
 {
  return name;
 }


 public double getPrice()
 {
  return price + getDollarChange();
 }
 
 public void setPrice(double price)
 {
  this.price = price;
 }
 
 public double getPercentChange()
 {
  return percentChange;
 }
 
 public void setPercentChange(double percentChange)
 {
  this.percentChange = percentChange;
 }
 
 public double getDollarChange()
 {
  return percentChange/100*price;
 }
 
 public int getNumOfSharesOwned()
 {
  return numOfSharesOwned;
 }
 
 public void setNumOfSharesOwned(int numOfSharesOwned)
 {
  this.numOfSharesOwned = numOfSharesOwned;
 }
 
 public double getDividend()
 {
  return dividend;
 }
 
 public News getCurrentNews()
 {
  return currentNews;
 }
 
 public void setCurrentNews(News currentNews)
 {
  this.currentNews = currentNews;
 }
 
 public Color getColor()
 {
  return color;
 }

 public ArrayList<Double> getPastPrices()
 {
  return pastPrices;
 }
}