package application;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.scene.shape.*;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import java.io.*;


//在这里用了两个图片，其中图片1是预览图和分成九个块
//而图片2就是那个遮罩，就是那个可以移动的小块

public class Main extends Application {
	
	public int sceneWidth=750;
	public int sceneHeight=750;
	public static StackPane menu=new StackPane();
	public static Scene mainScene=new Scene(menu);

    private static void configureFileChooser(
            final FileChooser fileChooser) {      
                fileChooser.setTitle("选择图片");
                fileChooser.setInitialDirectory(
                    new File(System.getProperty("user.home"))
                );                 
                fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All Images", "*.*"),
                    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                    new FileChooser.ExtensionFilter("PNG", "*.png")
                );
        }

    @Override
    public void start(Stage arg0) throws Exception {
    	//完成开始界面
    	
    	
    	Rectangle pink=new Rectangle(0,0,sceneWidth,sceneHeight);
    	pink.setFill(Color.BLACK);
    	
    	BgPane bg=new BgPane();//注意circle的初始化
 
    	RadioButton low=new RadioButton("低难度");
    	low.setTextFill(Color.WHITE);
    	low.setContentDisplay(ContentDisplay.RIGHT);
    	low.setSelected(false);
    	low.setPadding(new Insets(5,5,5,5));
    	//low.setStyle("-fx-border-color:black");设置边界
    	RadioButton medium=new RadioButton("中等难度");
    	medium.setTextFill(Color.WHITE);
    	medium.setContentDisplay(ContentDisplay.RIGHT);
    	medium.setSelected(false);
    	medium.setPadding(new Insets(5,5,5,5));
    	
    	RadioButton superior=new RadioButton("高难度");
    	superior.setTextFill(Color.WHITE);
    	superior.setContentDisplay(ContentDisplay.RIGHT);
    	superior.setSelected(false);
    	superior.setPadding(new Insets(5,5,5,5));
    	
    	ToggleGroup difficulty=new ToggleGroup();
    	low.setToggleGroup(difficulty);
    	medium.setToggleGroup(difficulty);
    	superior.setToggleGroup(difficulty);
    	
    	VBox users=new VBox(40);
    	users.setMaxWidth(300);
    	users.setMaxHeight(300);
    	
    	HBox hBoxForButton=new HBox(20);
    	hBoxForButton.setPadding(new Insets(5,5,5,5));
    	hBoxForButton.getChildren().addAll(low,medium,superior);
    	
    	Button chooseImage=new Button("选择图片");
    	Button startGame=new Button("开始");
    	
    	FileChooser fileChooser = new FileChooser();
        
    	chooseImage.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        configureFileChooser(fileChooser);
                        File file = fileChooser.showOpenDialog(arg0);
                        if (file != null) {
                          
                                Image imView = new Image(file.getAbsoluteFile().toURI().toString());
                                
                               /*startGame.setOnMouseClicked(e2->{
                        			mediumGame mediumscene=new mediumGame(arg0,imView);
                        		});*/
                               
                                medium.setOnAction(e2->
                            	startGame.setOnMouseClicked(e3->{
                            			mediumGame mediumscene=new mediumGame(arg0,imView);
                            		}));
                               
                                low.setOnAction(e2->
                            	startGame.setOnMouseClicked(e3->{
                            			lowGame lowscene=new lowGame(arg0,imView);
                            		}));
                                
                                superior.setOnAction(e2->
                            	startGame.setOnMouseClicked(e3->{
                            		SuperiorGame superiorscene=new SuperiorGame(arg0,imView);
                            		}));
                        }
                    }
                });
     
    
    	
    	TextArea aboutGame=new TextArea("关于本游戏"+"\n"
    			+"这是一个资深辣鸡SJ写出来的一个小小的游戏，本来图片拼图其实网上都有教程"+"\n"
    			+ "我在做的过程中也感觉太简单了，所以不如咱们就个性一点，做个和别人不一样的吧！"+"\n"
    			+ "其实不一样就是让背景炫酷一点了，由于之前对JS的背景特效特别感兴趣，但是很无奈看不懂JS代码"+"\n"
    			+ "就觉得我就模仿着做一个吧，万一能成呢，结果，我发现，真的好难做啊！"+"\n"
    			+ "真的挺打击人的，网上没有这方面的实例，人家大牛都是用JS做了,我却看不懂代码"+"\n"
    			+ "但是虽然是辣鸡，总还是要有一些梦想的，我这个游戏肯定比不上别人的那么厉害，还是自己做起来当做"+"\n"
    			+ "是一个怡情的好机会把，自己平时复习复习的累了，就敲几行代码，慢慢地把它做成把。"+"\n"
    			+ "课堂展示逼着我去做，心里面还真是难受的，慢慢做成一个成品了，就可以拿出去炫耀了哈哈！");
    	aboutGame.setEditable(false);
    	aboutGame.setPrefColumnCount(20);
    	aboutGame.setPrefRowCount(5);
    	
    	
    	aboutGame.setStyle("-fx-base:transparent;-fx-border-color:transparent;-fx-background-color:transparent;-fx-text-fill:white");
    	
    	ScrollPane scarollPane=new ScrollPane(aboutGame);//;-fx-border-color:red;-fx-background-color:green
    	users.getChildren().addAll(chooseImage,hBoxForButton,startGame,aboutGame);
    	
    	menu.getChildren().addAll(pink,bg,users);//
    	
    	arg0.setScene(mainScene);
    	arg0.setHeight(sceneHeight);
    	arg0.setWidth(sceneWidth);
    	arg0.setTitle("图片拼图——Made by SJ");
    	arg0.show();
    	
    	arg0.setResizable(false);
    	bg.requestFocus();
    	
    	medium.setOnAction(e->
    	startGame.setOnMouseClicked(e2->{
    			mediumGame mediumscene=new mediumGame(arg0);
    		}));
    	
    	low.setOnAction(e->
    	startGame.setOnMouseClicked(e2->{
    			lowGame lowscene=new lowGame(arg0);
    		}));
    	
    	superior.setOnAction(e->
    	startGame.setOnMouseClicked(e2->{
    		SuperiorGame superiorscene=new SuperiorGame(arg0);
    		}));
    }
    public static void main(String[] args) {
        launch(args);
    }

}