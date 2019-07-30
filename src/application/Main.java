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


//��������������ͼƬ������ͼƬ1��Ԥ��ͼ�ͷֳɾŸ���
//��ͼƬ2�����Ǹ����֣������Ǹ������ƶ���С��

public class Main extends Application {
	
	public int sceneWidth=750;
	public int sceneHeight=750;
	public static StackPane menu=new StackPane();
	public static Scene mainScene=new Scene(menu);

    private static void configureFileChooser(
            final FileChooser fileChooser) {      
                fileChooser.setTitle("ѡ��ͼƬ");
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
    	//��ɿ�ʼ����
    	
    	
    	Rectangle pink=new Rectangle(0,0,sceneWidth,sceneHeight);
    	pink.setFill(Color.BLACK);
    	
    	BgPane bg=new BgPane();//ע��circle�ĳ�ʼ��
 
    	RadioButton low=new RadioButton("���Ѷ�");
    	low.setTextFill(Color.WHITE);
    	low.setContentDisplay(ContentDisplay.RIGHT);
    	low.setSelected(false);
    	low.setPadding(new Insets(5,5,5,5));
    	//low.setStyle("-fx-border-color:black");���ñ߽�
    	RadioButton medium=new RadioButton("�е��Ѷ�");
    	medium.setTextFill(Color.WHITE);
    	medium.setContentDisplay(ContentDisplay.RIGHT);
    	medium.setSelected(false);
    	medium.setPadding(new Insets(5,5,5,5));
    	
    	RadioButton superior=new RadioButton("���Ѷ�");
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
    	
    	Button chooseImage=new Button("ѡ��ͼƬ");
    	Button startGame=new Button("��ʼ");
    	
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
     
    
    	
    	TextArea aboutGame=new TextArea("���ڱ���Ϸ"+"\n"
    			+"����һ����������SJд������һ��СС����Ϸ������ͼƬƴͼ��ʵ���϶��н̳�"+"\n"
    			+ "�������Ĺ�����Ҳ�о�̫���ˣ����Բ������Ǿ͸���һ�㣬�����ͱ��˲�һ���İɣ�"+"\n"
    			+ "��ʵ��һ�������ñ����ſ�һ���ˣ�����֮ǰ��JS�ı�����Ч�ر����Ȥ�����Ǻ����ο�����JS����"+"\n"
    			+ "�;����Ҿ�ģ������һ���ɣ���һ�ܳ��أ�������ҷ��֣���ĺ���������"+"\n"
    			+ "���ͦ����˵ģ�����û���ⷽ���ʵ�����˼Ҵ�ţ������JS����,��ȴ����������"+"\n"
    			+ "������Ȼ���������ܻ���Ҫ��һЩ����ģ��������Ϸ�϶��Ȳ��ϱ��˵���ô�����������Լ�����������"+"\n"
    			+ "��һ������ĺû���ѣ��Լ�ƽʱ��ϰ��ϰ�����ˣ����ü��д��룬�����ذ������ɰѡ�"+"\n"
    			+ "����չʾ������ȥ���������滹�������ܵģ���������һ����Ʒ�ˣ��Ϳ����ó�ȥ��ҫ�˹�����");
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
    	arg0.setTitle("ͼƬƴͼ����Made by SJ");
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