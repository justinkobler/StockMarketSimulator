import java.util.ArrayList;
import javafx.scene.paint.Color;

public class DataLine
{
	private ArrayList<Double> dataPoints = new ArrayList<>();
	private Color color;
	
	public DataLine(ArrayList<Double> dataPoints, Color color)
	{
		this.dataPoints = dataPoints;
		this.color = color;
	}
	
	public ArrayList<Double> getDataPoints()
	{
		return dataPoints;
	}
	public Color getColor()
	{
		return this.color;
	}
}