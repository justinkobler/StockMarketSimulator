import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import java.util.Random;
import java.util.ArrayList;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.BorderStroke;  
import javafx.scene.layout.Border;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import java.io.*;
import javafx.scene.control.Tooltip;

public class StockMarket extends Application
{
	static Label currentCashLabel;
	static double currentCash = 0.0;
	private File saveLocation = null;
	String difficulty = "Medium";
	int numOfCompanies = 30;
	public static ArrayList<Company> listOfCompanies = new ArrayList<>();
	VBox rightPane;
	static Pane summaryBoxes;
	static VBox newsBoxes;
	int NUM_OF_COMPANIES = 30;
	VBox leftPane;
	Scene homeScene;
	Scene startScene;
	Scene gameOverScene;
	private Label gameOverText;
	private double clickCounter = 0;
	private boolean timerOn = true;
	private static double[] highScores = new double[5];
	
public void start(Stage primary)
{ 
	loadHighScores();
	VBox gameOverBox = new VBox();
	gameOverScene = new Scene(gameOverBox, 1024, 768);
	  Label gameOverLabel = new Label("GAME OVER");
	    gameOverLabel.setFont(new Font(100));
	  gameOverText = new Label("Your total wealth was " + shaveDouble(currentCash));
	    gameOverText.setFont(new Font(50));
	  Button quitGO = new Button("Quit");
		    quitGO.setMinHeight(100);
			quitGO.setMinWidth(150);
			quitGO.setFont(new Font(50));
			quitGO.setOnAction(e -> primary.setScene(startScene));
	gameOverBox.getChildren().addAll(gameOverLabel, gameOverText, quitGO);
	gameOverBox.setAlignment(Pos.CENTER);
	BorderPane bp3 = new BorderPane();
	Scene optionsScene = new Scene(bp3, 1024, 768);
      VBox optionsBox = new VBox(3);
        Button save = new Button("Save Game");
          save.setOnAction(e -> {
            saveGame(false);
            primary.setScene(homeScene);
          });
          save.setMinHeight(150);
          save.setMinWidth(500);
          save.setFont(new Font(50));
        Button saveAs = new Button("Save Game As");
          saveAs.setMinHeight(150);
          saveAs.setMinWidth(500);
          saveAs.setFont(new Font(50));
		  saveAs.setOnAction(e -> {
			  saveGame(true);
			  primary.setScene(homeScene);
		  });
        Button loadIn = new Button ("Load Game");
          loadIn.setOnAction(e -> {
			  loadGame();
			  primary.setScene(homeScene);
          });
		  loadIn.setMinHeight(150);
          loadIn.setMinWidth(500);
          loadIn.setFont(new Font(50));
		HBox lowerButtons = new HBox();
          lowerButtons.setMinHeight(150);
          lowerButtons.setMinWidth(500);
		  Button quit = new Button("Quit");
		    quit.setMinHeight(150);
			quit.setMinWidth(250);
			quit.setFont(new Font(50));
			quit.setOnAction(e -> primary.setScene(startScene));
		  Button back = new Button("Back");
		    back.setMinHeight(150);
			back.setMinWidth(250);
			back.setFont(new Font(50));
			back.setOnAction(e -> primary.setScene(homeScene));
		lowerButtons.getChildren().addAll(quit, back);
		lowerButtons.setAlignment(Pos.CENTER);
      optionsBox.setAlignment(Pos.CENTER);
      optionsBox.getChildren().addAll(save, saveAs, loadIn, lowerButtons);
    bp3.setCenter(optionsBox);
	BorderPane bp = new BorderPane();
	  bp.setStyle("-fx-background-color: #E0FFFF");
	homeScene = new Scene(bp, 1024, 768);
	  leftPane = new VBox(1);
	    Button buy = new Button("Buy");
          buy.setOnAction(e -> this.buyAction());
          buy.setMinHeight(70);
          buy.setMinWidth(200);
          buy.setFont(new Font(24));
        Button sell = new Button("Sell");
          sell.setOnAction(e -> this.sellAction());
          sell.setMinHeight(70);
          sell.setMinWidth(200);
          sell.setFont(new Font(24));
        Button contin = new Button("Continue");
          contin.setMinHeight(70);
          contin.setMinWidth(200);
          contin.setFont(new Font(24));
	      contin.setOnAction(e -> continueAction(primary));
		Button options = new Button("Options");
          options.setMinHeight(70);
          options.setMinWidth(200);
          options.setFont(new Font(24));
	      options.setOnAction(e -> primary.setScene(optionsScene));  
        summaryBoxes = new Pane();
        rightPane = new VBox();
        rightPane.getChildren().add(summaryBoxes);
        currentCashLabel = new Label("$" + currentCash);
          currentCashLabel.setFont(new Font(40));
          currentCashLabel.setStyle("-fx-border-width: 5; -fx-border-color: #A9A9A9; -fx-background-color: #EBEBEB");
        newsBoxes = new VBox();
		  newsBoxes.setMinHeight(400);
		  newsBoxes.setMinWidth(200);
      leftPane.getChildren().addAll(buy, sell, contin, options, newsBoxes, currentCashLabel);
    BorderPane bp2 = new BorderPane();
      bp2.setStyle("-fx-background-color: #98FB98");
    startScene = new Scene(bp2, 1024, 768);
      VBox startBox = new VBox(2);
        Button start = new Button("Start Game");
          start.setOnAction(e -> {
            this.startNewGame();
            primary.setScene(homeScene);
          });
          start.setMinHeight(150);
          start.setMinWidth(500);
          start.setFont(new Font(50));
        Button load = new Button("Load Game");
          load.setMinHeight(150);
          load.setMinWidth(500);
          load.setFont(new Font(50));
		  load.setOnAction(e -> {
			  loadGame();
			  primary.setScene(homeScene);
		  });
        Button difficulty = new Button ("Select Difficulty");
          difficulty.setOnAction(e -> this.difficultyAction());
          difficulty.setMinHeight(150);
          difficulty.setMinWidth(500);
          difficulty.setFont(new Font(50));
		Button highScoreButton = new Button ("High Scores");
          highScoreButton.setOnAction(e -> this.highScoreAction());
          highScoreButton.setMinHeight(150);
          highScoreButton.setMinWidth(500);
          highScoreButton.setFont(new Font(50));
		Button exit = new Button("Exit");
		  exit.setOnAction(e -> {
			exit();
			primary.close();
		  });
          exit.setMinHeight(150);
          exit.setMinWidth(500);
          exit.setFont(new Font(50));
      startBox.setAlignment(Pos.CENTER);
      startBox.getChildren().addAll(start, load, difficulty, highScoreButton, exit);
    bp2.setCenter(startBox);
    bp.setLeft(leftPane);
    bp.setRight(rightPane);
    primary.setTitle("Stock Market Sim");
    primary.setScene(startScene);
	primary.setOnCloseRequest(e -> exit());
	primary.setOnHidden(e -> exit());
    primary.show();
}
 
public void exit()
{
	try{
	BufferedWriter bw = new BufferedWriter(new FileWriter(new File(".highScores")));
	for(int i = 0; i < 5; i++)
	{
		bw.write(highScores[i]+"");
		bw.newLine();
	}
	bw.close();
	}
	catch(Exception e)
	{
		
	}
}
 
 private void startNewGame()
 {
	 listOfCompanies.clear();
	Random r = new Random();
    for(int i = 0; i < NUM_OF_COMPANIES/3; i++)
    {
      listOfCompanies.add(new Company(NameFinder.generateName(), new Random().nextDouble()*801 + 150, new Color((float)r.nextDouble(), (float)r.nextDouble(), (float)r.nextDouble(), 1.0)));
    }
    for(int i = 0; i < NUM_OF_COMPANIES/3; i++)
    {
      listOfCompanies.add(new Company(NameFinder.generateName(), new Random().nextDouble()*76 + 75, new Color((float)r.nextDouble(), (float)r.nextDouble(), (float)r.nextDouble(), 1.0)));
    }
    for(int i = 0; i < NUM_OF_COMPANIES/3; i++)
    {
      listOfCompanies.add(new Company(NameFinder.generateName(), new Random().nextDouble()*71 + 5, new Color((float)r.nextDouble(), (float)r.nextDouble(), (float)r.nextDouble(), 1.0)));
    }
    switch(difficulty)
    {
    case "Easy":
      currentCash = 500.0;
      currentCashLabel.setText("$" + shaveDouble(currentCash));
      break;
    case "Medium":
      currentCash = 200.0;
      currentCashLabel.setText("$" + shaveDouble(currentCash));
      break;
    case "Hard":
      currentCash = 50.0;
      currentCashLabel.setText("$" + shaveDouble(currentCash));
      break;
    default:
      makePopup("ERROR: Choose Difficulty to Start");
      break;
    }

    ArrayList<Company> sortedList = new ArrayList<>();
    sortedList.addAll(sortByChange());
	summaryBoxes.getChildren().clear();
    for(int i = 0; i < 5; i++)
    {
      for(int j = 0; j < 3; j++)
      {
        summaryBoxes.getChildren().add(summaryBox(sortedList.get((i*3)+j), ((1024-200)/5)*i, 110*j));
      }
    }
    ArrayList<Company> thisOne = new ArrayList<>();
    for(int i = 0; i < 15; i++)
    {
	  thisOne.add(sortedList.get(i));
    }
	if(rightPane.getChildren().size() == 1)
	{
    rightPane.getChildren().add(0,makeGraph(thisOne, 1024-200, 768-330).draw());
	}
	else{
    rightPane.getChildren().set(0,makeGraph(thisOne, 1024-200, 768-330).draw());
		
	}
	saveLocation = null;
	saveGame(false);
 }
 
 private void continueAction(Stage primary)
 {
	clickCounter++;
	primary.setTitle("Stock Market Sim (Turn: " + clickCounter + ")");
	if(timerOn && clickCounter == 120.0)
	{
		for(Company c: listOfCompanies)
		{
			if(c.getNumOfSharesOwned() > 0)
			{
				for(int i = 0; i < c.getNumOfSharesOwned(); i++)
				{
					currentCash+=c.getPrice();
				}
			}
		}
		gameOverText.setText("Your total wealth was $" + shaveDouble(currentCash));
		primary.setTitle("Stock Market Sim");
		primary.setScene(gameOverScene);
		updateHighScores(currentCash);
	}
	newsBoxes.getChildren().clear();
	Random r = new Random();
	for(Company c: listOfCompanies)
	{	
		c.setPrice(c.getPrice());
		c.setPercentChange(r.nextDouble()*11-5);
		if(r.nextDouble() > 0.85)
		{
			c.setPercentChange(c.getPercentChange()+c.chooseNews().getChange());
		}
		else
		{
			c.setCurrentNews(null);
		}
		c.getPastPrices().add(c.getPrice());
		if(c.getPastPrices().size()>120)
		{
			c.getPastPrices().remove(0);
		}
		if(c.getCurrentNews() != null && newsBoxes.getChildren().size() < 5)
		{
			newsBoxes.getChildren().add(newsBox(c));
		}
	}
	if(clickCounter%3 == 0)
	{
		for(Company c: listOfCompanies)
		{
			if(c.getNumOfSharesOwned() > 0)
			{
				currentCash += c.getPrice()*c.getNumOfSharesOwned()*c.getDividend()/100.0; 
				
			}
		}
		currentCashLabel.setText("$"+shaveDouble(currentCash));
	}
	ArrayList<Company> sortedList = new ArrayList<>();
	sortedList.addAll(sortByChange());
	for(int i = 0; i < 5; i++)
	{
		for(int j = 0; j < 3; j++)
		{
			summaryBoxes.getChildren().set((i*3)+j,summaryBox(sortedList.get((i*3)+j), ((1024-200)/5)*i, 110*j));
		}
	}
	
	ArrayList<Company> thisOne = new ArrayList<>();
	for(int i = 0; i < 15; i++)
	{
		thisOne.add(sortedList.get(i));
	}
	rightPane.getChildren().set(0,makeGraph(thisOne, 1024-200, 768-330).draw());
 }
 
private void difficultyAction()
{
	Stage difficultyStage = new Stage();
	BorderPane bp = new BorderPane();
	bp.setStyle("-fx-background-color: #8470FF");
	Scene difficultyScene = new Scene(bp, 600, 384);
      VBox difficultyMenu = new VBox(2);
        Button easy = new Button("Easy");
          easy.setOnAction(e -> {
            difficulty = "Easy";
            difficultyStage.close();
        });
          easy.setMinHeight(100);
          easy.setMinWidth(300);
          easy.setFont(new Font(35));
        Button medium = new Button("Medium");
          medium.setOnAction(e -> {
            difficulty = "Medium";
            difficultyStage.close();
          });
          medium.setMinHeight(100);
          medium.setMinWidth(300);
          medium.setFont(new Font(35));
        Button hard = new Button("Hard");
          hard.setOnAction(e -> {
            difficulty = "Hard";
            difficultyStage.close();
          });
          hard.setMinHeight(100);
          hard.setMinWidth(300);
          hard.setFont(new Font(35));
      difficultyMenu.setAlignment(Pos.CENTER);
      difficultyMenu.getChildren().addAll(easy, medium, hard);
	  VBox modeMenu = new VBox(2);
	    Button freePlay = new Button("Freeplay");
		  freePlay.setOnAction(e -> {
            timerOn = false;
            difficultyStage.close();
          });
		  freePlay.setMinHeight(100);
          freePlay.setMinWidth(300);
          freePlay.setFont(new Font(35));
		Button timerPlay = new Button("Time Trial");
		  timerPlay.setOnAction(e -> {
            timerOn = true;
            difficultyStage.close();
		  });
		  timerPlay.setMinHeight(100);
          timerPlay.setMinWidth(300);
          timerPlay.setFont(new Font(35));
	  modeMenu.getChildren().addAll(freePlay, timerPlay);
      modeMenu.setAlignment(Pos.CENTER);
	  HBox menus = new HBox();
	    menus.getChildren().addAll(difficultyMenu, modeMenu);
    bp.setCenter(menus);
    difficultyStage.setScene(difficultyScene);
    difficultyStage.initModality(Modality.APPLICATION_MODAL);
    difficultyStage.show();
}
 
 private void highScoreAction()
{
	Stage highScoreStage = new Stage();
	BorderPane bp = new BorderPane();
	bp.setStyle("-fx-background-color: #8470FF");
	Scene highScoreScene = new Scene(bp, 600, 384);
      VBox highScoreMenu = new VBox(2);
	    for(int i = 0; i < 5; i++)
		{
			Label l = new Label((i+1) + ") $" + shaveDouble(highScores[i]));
			l.setFont(new Font(35));
			highScoreMenu.getChildren().add(l);
		}
      highScoreMenu.setAlignment(Pos.CENTER);
    bp.setCenter(highScoreMenu);
    highScoreStage.setScene(highScoreScene);
    highScoreStage.initModality(Modality.APPLICATION_MODAL);
    highScoreStage.show();
}
 
 private void buyAction()
 {
    Stage buyStage = new Stage();
    BorderPane bp = new BorderPane();
    bp.setStyle("-fx-background-color: #98FB98");
    Scene buyScene = new Scene(bp, 1024, 768);
	  Pane buyBoxes = new Pane();
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < 6; j++)
			{
				buyBoxes.getChildren().add((i*3)+j,makeBuyBox(listOfCompanies.get((i*6)+j), ((1024)/5)*i, 111*j));
			}
		}
	  HBox bottom = new HBox();
	    Button exit = new Button("Exit");
	      exit.setOnAction(e -> 
		  {
			leftPane.getChildren().add(currentCashLabel);
			buyStage.close();
		  });
		  exit.setMinWidth(200);
		  exit.setMinHeight(66);
	  bottom.getChildren().addAll(currentCashLabel, exit);
	bp.setCenter(buyBoxes);
	bp.setBottom(bottom);
    buyStage.setScene(buyScene);
    buyStage.initModality(Modality.APPLICATION_MODAL);
	buyStage.setTitle("Buy Screen");
    buyStage.show(); 
 }
 
 private BorderPane makeBuyBox(Company c, double posX, double posY)
 {
	Label stocks = new Label();
	BorderPane bp = new BorderPane();
	  VBox inputArea = new VBox();
	    TextField inputField = new TextField();
		  inputField.setMinWidth(170/3);
		  inputField.setMaxWidth(170/3);
		  inputField.setMinHeight(40);
		  inputField.setMaxHeight(40);
		Button enter = new Button("Enter");
		  enter.setMinWidth(170/3);
		  enter.setMaxWidth(170/3);
		  enter.setMinHeight(40);
		  enter.setMaxHeight(40);
		  enter.setOnAction(e -> {
		    try
		    {
			  int input = Integer.parseInt(inputField.getText());
			  if(input > 0)
			  {
			    if(input*c.getPrice() <= currentCash)
			    {
			      c.setNumOfSharesOwned(c.getNumOfSharesOwned() + input);
			      currentCash -= input*c.getPrice();
				  stocks.setText(""+c.getNumOfSharesOwned());
				  currentCashLabel.setText("$"+shaveDouble(currentCash));
				}
				else
				{
				  makePopup("That is too expensive.");
				}
			  }
			  else
			  {
			  makePopup("Input needs to be positive.");
			  }
			}
			catch(Exception ex)
			{
				makePopup("Input needs to be an integer");
			}
		  });
	  inputArea.getChildren().addAll(inputField, enter);
	  VBox mid = new VBox();
        Label name = new Label(c.getName());
        Label moneyChange = new Label("$"+shaveDouble(c.getDollarChange()));
        Label price = new Label("$"+shaveDouble(c.getPrice()));
		stocks.setText(""+c.getNumOfSharesOwned());
		Label dividend = new Label(shaveDouble(c.getDividend())+"%");
        mid.getChildren().addAll(name, moneyChange, price, stocks, dividend);
	  mid.setStyle("-fx-border-width: 3; -fx-border-color: #030303");
	  bp.setMaxWidth((1024)/5-15);
	  bp.setMaxHeight(110);
	  bp.setMinHeight(110);
	  bp.setMinWidth((1024)/5-15);
	  bp.setLayoutX(posX);
	  bp.setLayoutY(posY);
	  bp.setCenter(mid);
	  bp.setLeft(inputArea);
	  return bp;
 }
 
 private void sellAction()
 {
    Stage sellStage = new Stage();
    BorderPane bp = new BorderPane();
    bp.setStyle("-fx-background-color: #98FB98");
    Scene sellScene = new Scene(bp, 1024, 768);
	  Pane sellBoxes = new Pane();
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < 6; j++)
			{
				sellBoxes.getChildren().add((i*3)+j,makeSellBox(listOfCompanies.get((i*6)+j), ((1024)/5)*i, 111*j));
			}
		}
	  HBox bottom = new HBox();
	    Button exit = new Button("Exit");
	      exit.setOnAction(e -> 
		  {
			leftPane.getChildren().add(currentCashLabel);
			sellStage.close();
		  });
		  exit.setMinWidth(200);
		  exit.setMinHeight(66);
	  bottom.getChildren().addAll(currentCashLabel, exit);
	bp.setCenter(sellBoxes);
	bp.setBottom(bottom);
    sellStage.setScene(sellScene);
    sellStage.initModality(Modality.APPLICATION_MODAL);
    sellStage.setTitle("Sell Screen");
	sellStage.show(); 
 }
 
 private BorderPane makeSellBox(Company c, double posX, double posY)
 {
	Label stocks = new Label();
	BorderPane bp = new BorderPane();
	  VBox inputArea = new VBox();
	    TextField inputField = new TextField();
		  inputField.setMinWidth(170/3);
		  inputField.setMaxWidth(170/3);
		  inputField.setMinHeight(40);
		  inputField.setMaxHeight(40);
		Button enter = new Button("Enter");
		  enter.setMinWidth(170/3);
		  enter.setMaxWidth(170/3);
		  enter.setMinHeight(40);
		  enter.setMaxHeight(40);
		  enter.setOnAction(e -> {
		    try
		    {
			  int input = Integer.parseInt(inputField.getText());
			  if(input <= c.getNumOfSharesOwned())
			  {
				if(input > 0)
				{
				  c.setNumOfSharesOwned(c.getNumOfSharesOwned() - input);
			      currentCash += input*c.getPrice();
				  stocks.setText(""+c.getNumOfSharesOwned());
				  currentCashLabel.setText("$"+shaveDouble(currentCash));
			    }
			    else
			    {
			      makePopup("Input needs to be positive.");
			    }
			  }
			  else
			  {
				makePopup("You cannot sell stocks you don't own.");  
			  }
			}
			catch(Exception ex)
			{
				makePopup("Input needs to be an integer");
			}
		  });
	  inputArea.getChildren().addAll(inputField, enter);
	  VBox mid = new VBox();
        Label name = new Label(c.getName());
        Label moneyChange = new Label("$"+shaveDouble(c.getDollarChange()));
        Label price = new Label("$"+shaveDouble(c.getPrice()));
		stocks.setText(""+c.getNumOfSharesOwned());
		Label dividend = new Label(shaveDouble(c.getDividend())+"%");
        mid.getChildren().addAll(name, moneyChange, price, stocks, dividend);
	  mid.setStyle("-fx-border-width: 3; -fx-border-color: #030303");
	  bp.setMaxWidth((1024)/5-15);
	  bp.setMaxHeight(110);
	  bp.setMinHeight(110);
	  bp.setMinWidth((1024)/5-15);
	  bp.setLayoutX(posX);
	  bp.setLayoutY(posY);
	  bp.setCenter(mid);
	  bp.setLeft(inputArea);
	  return bp;
 }
 public static BorderPane summaryBox(Company company, int posX, int posY)
 {
	BorderPane bp = new BorderPane();
	bp.setMaxWidth((1024-200)/5);
	bp.setMaxHeight(100);
	bp.setMinHeight(100);
	bp.setMinWidth((1024-200)/5);
	bp.setLayoutX(posX);
	bp.setLayoutY(posY);
	Pane pain = new Pane();
	if(company.getPercentChange() >= 0.0)
    {
      pain.setStyle("-fx-background-color: #00FF00");
    }
    else
    {
	  pain.setStyle("-fx-background-color: #FF0000");
    }
    bp.setLeft(pain);
    pain.setMinWidth(25);
    VBox mid = new VBox();
      Label name = new Label(company.getName());
      Label percent = new Label(""+shaveDouble(company.getPercentChange())+"%");
      Label moneyChange = new Label("$"+shaveDouble(company.getDollarChange()));
      Label price = new Label("$"+shaveDouble(company.getPrice()));
	  Rectangle colorPreview = new Rectangle(100,10,Paint.valueOf(company.getColor().toString()));
    mid.getChildren().addAll(name, percent, moneyChange, price, colorPreview);
    bp.setCenter(mid);
    mid.setStyle("-fx-border-width: 3; -fx-border-color: #030303");
    return bp;
 }
 private static String shaveDouble(double d)
 {
    String s = d+"";
    int i = s.indexOf('.');
    if(s.substring(i+1).length() < 2)
    {
      s = s + "0";
    }
    else if(s.substring(i+1).length() > 2)
    {
      s = s.substring(0,i+3);
    }
    return s;
 }
 private LineGraph makeGraph(ArrayList<Company> cList, double sizeX, double sizeY)
 {
    LineGraph lg = new LineGraph();
    for(Company c: cList)
    {
      lg.getDataLines().add(new DataLine(c.getPastPrices(), c.getColor()));
    }
    lg.setSizeX(sizeX);
    lg.setSizeY(sizeY);
    return lg;
 }
 private ArrayList<Company> sortByChange()
 {
    ArrayList<Company> returnArray = new ArrayList<>();
    returnArray.add(new Company("FALSE COMPANY", 0.00));
    for(Company c: listOfCompanies)
    {
      for(Company k: returnArray)
      {
        if(Math.abs(c.getPercentChange()) >= Math.abs(k.getPercentChange()))
        {
          returnArray.add(returnArray.indexOf(k),c);
          break;
        }
      }
    }
    return returnArray;
 }
 
 private void saveGame(boolean saveAs)
 {
	 if(saveAs || saveLocation == null)
	 {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("Stock Market File", "*.broker"));
		saveLocation = fc.showSaveDialog(new Stage());
	 }
	 try
	 {
		BufferedWriter bw = new BufferedWriter(new FileWriter(saveLocation));
		for(Company c: listOfCompanies)
		{
			bw.write("BEGIN");
			bw.newLine();
			bw.write(c.getName());
			bw.newLine();
			bw.write(""+c.getPrice());
			bw.newLine();
			bw.write(""+c.getPercentChange());
			bw.newLine();
			bw.write(""+c.getDollarChange());
			bw.newLine();
			bw.write(""+c.getColor());
			bw.newLine();
			bw.write(""+c.getNumOfSharesOwned());
			bw.newLine();
			bw.write(""+c.getDividend());
			bw.newLine();
			bw.write("END");
			bw.newLine();
		}
		bw.write("BEGIN2");
		bw.newLine();
		bw.write(""+currentCash);
		bw.newLine();
		bw.write(difficulty);
		bw.newLine();
		bw.write(""+clickCounter);
		bw.newLine();
		bw.write(""+timerOn);
		bw.newLine();
		bw.close();
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveLocation.getPath()+"~"));
		for(Company c: listOfCompanies)
		{
			oos.writeObject(c.getPastPrices());
			oos.writeObject(c.getCurrentNews());
		}
		oos.close();
	 }
	 catch(Exception e)
	 {
		 e.printStackTrace();
	 }
 }
 
 
@SuppressWarnings("unchecked")
 private void loadGame()
 { 
	FileChooser fc = new FileChooser();
	fc.getExtensionFilters().add(new ExtensionFilter("Stock Market File", "*.broker"));
	saveLocation = fc.showOpenDialog(new Stage());
	try
	{
		BufferedReader br = new BufferedReader(new FileReader(saveLocation));
		String read;
		listOfCompanies = new ArrayList<Company>();
		while((read = br.readLine()) != null)
		{			
			if(read.equals("BEGIN"))
			{
				listOfCompanies.add(new Company(br.readLine(), Double.parseDouble(br.readLine()), Double.parseDouble(br.readLine()), Double.parseDouble(br.readLine()), Color.web(br.readLine()), Integer.parseInt(br.readLine()), Double.parseDouble(br.readLine())));
			}
			if(read.equals("BEGIN2"))
			{
				currentCash = Double.parseDouble(br.readLine());
				currentCashLabel.setText("$" + shaveDouble(currentCash));
				difficulty = br.readLine();
				clickCounter = Double.parseDouble(br.readLine());
				timerOn = Boolean.parseBoolean(br.readLine());
			}
		}
		br.close();
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveLocation.getPath() + "~"));
		for(Company c: listOfCompanies)
		{
			c.getPastPrices().addAll((ArrayList<Double>)ois.readObject());
			c.setCurrentNews((News)ois.readObject());
			if(c.getCurrentNews() != null && newsBoxes.getChildren().size() < 5)
			{
			newsBoxes.getChildren().add(newsBox(c));
			}
		}
		summaryBoxes.getChildren().clear();
		ArrayList<Company> sortedList = new ArrayList<>();
		sortedList.addAll(sortByChange());
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				summaryBoxes.getChildren().add(summaryBox(sortedList.get((i*3)+j), ((1024-200)/5)*i, 110*j));
			}
		}
		rightPane.getChildren().clear();
		rightPane.getChildren().add(summaryBoxes);
		ArrayList<Company> thisOne = new ArrayList<>();
		for(int i = 0; i < 15; i++)
		{
			thisOne.add(sortedList.get(i));
		}
		rightPane.getChildren().add(0,makeGraph(thisOne, 1024-200, 768-330).draw());
		ois.close();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
 }
 
 public static BorderPane newsBox(Company company)
 {
	BorderPane bp = new BorderPane();
	bp.setMaxWidth(200);
	bp.setMaxHeight(75);
	bp.setMinHeight(75);
	bp.setMinWidth(200);
	Pane pain = new Pane();
		bp.setLeft(pain);
		pain.setMinWidth(25);
		VBox mid = new VBox();
			Label name = new Label(company.getName());
			Label newsTitle = new Label(company.getCurrentNews().getTitle());
			  newsTitle.setWrapText(true);
			  newsTitle.setTooltip(new Tooltip(company.getCurrentNews().getText()));
		Rectangle colorPreview = new Rectangle(10,75,Paint.valueOf(company.getColor().toString()));
		mid.getChildren().addAll(name, newsTitle);
    bp.setCenter(mid);
	bp.setLeft(colorPreview);
    mid.setStyle("-fx-border-width: 3; -fx-border-color: #030303");
    return bp;
}
private void loadHighScores()
{
	try{
	BufferedReader br = new BufferedReader(new FileReader(new File(".highScores")));
	highScores[0] = Double.parseDouble(br.readLine());
	highScores[1] = Double.parseDouble(br.readLine());
	highScores[2] = Double.parseDouble(br.readLine());
	highScores[3] = Double.parseDouble(br.readLine());
	highScores[4] = Double.parseDouble(br.readLine());
	br.close();
	}
	catch(Exception e)
	{
		
	}
}
private void updateHighScores(double newScore)
{
	for(int i = 0; i < 5; i++)
	{
		if(highScores[i] < newScore)
		{
			for(int j = 3; j > i; j--)
			{
				highScores[j+1] = highScores[j];
			}
			highScores[i] = newScore;
			break;
		}
	}
}
 
private void makePopup(String text)
{
	Stage stage = new Stage(StageStyle.UTILITY);
	HBox hb = new HBox();
	Scene scene = new Scene(hb, 240, 90);
	  Label l = new Label(text);
	    l.setWrapText(true);
	hb.getChildren().add(l);
	hb.setAlignment(Pos.CENTER);
    stage.initModality(Modality.APPLICATION_MODAL);
	stage.setScene(scene);
	stage.setTitle("ERROR");
	stage.show();
}
 
 public static void main(String args[])
 {
    launch(args);
 }
}