import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.control.Label;

public class LineGraph
{
 private double posX;
 private double posY;
 private double sizeX;
 private double sizeY;
 private double startMax;
 private ArrayList<DataLine> dataLines = new ArrayList<>();
 private ArrayList<DataLine> changedDataLines = new ArrayList<>();
 
 public LineGraph()
 {
  this.dataLines = new ArrayList<>();
  this.sizeX = 500;
  this.sizeY = 500;
 }
 
 public LineGraph(ArrayList<DataLine> dataLines)
 {
  this.dataLines = dataLines;
 
 }
 public LineGraph(ArrayList<DataLine> dataLines, double sizeX, double sizeY)
 {
  this.dataLines = dataLines;
  this.sizeX = sizeX;
  this.sizeY = sizeY;
 }
 
 private double getMax()
 {
  double max = 0;
  for(int i = 0; i < dataLines.size(); i++)
  {
   for(int j = 0; j < dataLines.get(i).getDataPoints().size(); j++)
   {
    if(max < dataLines.get(i).getDataPoints().get(j))
    {
     max = dataLines.get(i).getDataPoints().get(j);
    }
   }
  }
  startMax = max;
  return max;
 }
 
 private void normalizeValues()
 {
	changedDataLines = dataLines;
  double max = getMax();
  for(int i = 0; i < dataLines.size(); i++)
  {
   for(int j = 0; j < dataLines.get(i).getDataPoints().size(); j++)
   {
    changedDataLines.get(i).getDataPoints().set(j, dataLines.get(i).getDataPoints().get(j)/max);
   }
  }
 }
 
 public Pane draw()
 {
  normalizeValues();
  int numOfPoints = dataLines.get(0).getDataPoints().size();
  double incrementHorizontal = sizeX/(numOfPoints-1);
  Pane mainPane = new Pane();
  for(int i = 0; i < dataLines.size(); i++)
  {
   for(int j = 0; j < dataLines.get(i).getDataPoints().size()-1; j++)
   {
    Line l = new Line(incrementHorizontal*j, -changedDataLines.get(i).getDataPoints().get(j)*sizeY+sizeY, incrementHorizontal*(j+1), -changedDataLines.get(i).getDataPoints().get(j+1)*sizeY+sizeY);
    l.setStroke(Paint.valueOf(changedDataLines.get(i).getColor().toString()));
    mainPane.getChildren().add(l);
   }
  }
  mainPane.getChildren().add(new Label(startMax + ""));
  Label q = new Label(startMax/2 + "");
  q.setLayoutY(sizeY/2);
  mainPane.getChildren().add(q);
  Label p = new Label(0.0 + "");
  p.setLayoutY(sizeY-20);
  mainPane.getChildren().add(p);
  for(int i = 0; i < dataLines.size(); i++)
  {
   for(int j = 0; j < dataLines.get(i).getDataPoints().size(); j++)
   {
    changedDataLines.get(i).getDataPoints().set(j, dataLines.get(i).getDataPoints().get(j)*startMax);
   }
  }
  return mainPane;
 }
 
 public ArrayList<DataLine> getDataLines()
 {
  return dataLines;
 }
 public void setSizeX(double sizeX)
 {
  this.sizeX = sizeX;
 }
 public void setSizeY(double sizeY)
 {
  this.sizeY = sizeY;
 }
}