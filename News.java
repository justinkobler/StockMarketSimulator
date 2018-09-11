import java.util.Random;
import java.io.Serializable;

public class News implements Serializable
{
 private String title;
 private String text;
 private double upperVariance;
 private double lowerVariance;
 
 News()
 {
  this.title = null;
  this.text = null;
  this.upperVariance = 0.0;
  this.lowerVariance = 0.0;
 }
 News(String title, String text, double upperVariance, double lowerVariance)
 {
  this.title = title;
  this.text = text;
  this.upperVariance = upperVariance;
  this.lowerVariance = lowerVariance;
 }
 
 public double getChange()
 {
  return (new Random().nextDouble()*(upperVariance - lowerVariance))+lowerVariance;
 }
 
 public String getTitle()
 {
  return title;
 }
 
 public String getText()
 {
  return text;
 }

}