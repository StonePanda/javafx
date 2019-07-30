package application;
import java.util.Random;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.Button;

public class SuperiorGame {
	
		
		public Random random=new Random();
		public int gameImageLength=100;
		public int gameImageOnSide=4;
		public ImageView[] imageViews = new ImageView[gameImageOnSide*gameImageOnSide];
	    public GridPane gridPane = new GridPane();
	    public VBox deficiencyTip=new VBox();//��ʾ���ǿ��Ԥ�����ǿ�
	    public ImageView deficiency = new ImageView();//ȱʧ���ǿ�
	    public int sceneWidth=750;
		public int sceneHeight=750;
	    public int[] place=new int[gameImageOnSide*gameImageOnSide];
	    
	public SuperiorGame(Stage stage){
		    
		       Image image = new Image("image/1.jpg");
		       //ע��Ҫ��image
		        for(int i = 0, k = 0; i < gameImageOnSide; ++i) {
		            for(int j = 0; j < gameImageOnSide; ++j, ++k) {
		                imageViews[k] = new ImageView(image);        //��ʼ������
		                imageViews[k].setOnMouseClicked(new myevent());      //���õ���¼�
		                imageViews[k].setViewport(new Rectangle2D( gameImageLength* j, gameImageLength * i, gameImageLength,gameImageLength ));             //�и�ͼƬ
		            }
		        }
		        
		        
		        //imageView���±��൱�ڶ������������ֲ�α���
		        //����һ�������ֻ��0-3���ĸ���������
		        
		        place=random_num();
		        gridPane.add(imageViews[place[0]], 0, 0);                         
		        gridPane.add(imageViews[place[1]], 0, 1);
		        gridPane.add(imageViews[place[2]], 0, 2);
		        gridPane.add(imageViews[place[3]], 0, 3);
		        gridPane.add(imageViews[place[4]], 1, 0);                         
		        gridPane.add(imageViews[place[5]], 1, 1);
		        gridPane.add(imageViews[place[6]], 1, 2);
		        gridPane.add(imageViews[place[7]], 1, 3);
		        gridPane.add(imageViews[place[8]], 2, 0);                         
		        gridPane.add(imageViews[place[9]], 2, 1);
		        gridPane.add(imageViews[place[10]], 2,2);
		        gridPane.add(imageViews[place[11]], 2, 3);
		        gridPane.add(imageViews[place[12]], 3, 0);                         
		        gridPane.add(imageViews[place[13]], 3, 1);
		        gridPane.add(imageViews[place[14]], 3, 2);
		        
		        gridPane.add(imageViews[place[15]], 3, 3);
		        imageViews[place[15]].setVisible(false);//�Ȱ�������Ϊ���ɼ�
		        
		        //��ʾ��ȱʧ����һС��
		        deficiency.setImage(imageViews[place[gameImageOnSide*gameImageOnSide-1]].getImage());
		        deficiency.setViewport(imageViews[place[gameImageOnSide*gameImageOnSide-1]].getViewport());
		        deficiencyTip.getChildren().add(deficiency);//ȱʧ���ǿ飬����ʾ����
		    
		        
		        gridPane.setGridLinesVisible(true);
		        
		        Pane gamePane = new Pane();
		        gamePane.getChildren().add(gridPane);
		        gridPane.setLayoutX(gameImageLength/2);
		        gridPane.setLayoutY(gameImageLength/2);
		        // ����borderPane.setCenter(gridPane);
		        //�����Ǹ�Ԥ��ͼ
		        ImageView smallImage=new ImageView(image);
		        smallImage.setViewport(new Rectangle2D(0,0,
		        		gameImageLength*gameImageOnSide,gameImageLength*gameImageOnSide));
		        //minx,miny,width,height
		        smallImage.setFitHeight(gameImageLength);
		        smallImage.setFitWidth(gameImageLength);
		 
		        Button returnToMain=new Button("����������");
		        VBox right=new VBox(smallImage,deficiencyTip,returnToMain);
		        
		        
		        
		        gamePane.getChildren().add(right);
		        right.setLayoutX(sceneWidth-smallImage.getFitWidth());
		        right.setLayoutY(gridPane.getLayoutY());
		        
		        
		        returnToMain.setLayoutX(right.getLayoutX());
		        returnToMain.setLayoutY(gridPane.getLayoutY()+gridPane.getHeight());
		        
		        SuperiorBgPane bg=new SuperiorBgPane();
		        
		        
		        StackPane stackPane=new StackPane();
		        stackPane.getChildren().addAll(bg,returnToMain);//,gamePane
		        
		        Scene sixteenth = new Scene(stackPane);
		        //�������Ĺ��񳡾�    
		        
		        stage.setScene(sixteenth);
		        
		        returnToMain.setOnAction(e->{
		        	stage.setScene(Main.mainScene);
		        });
		        
		    }
		
	public SuperiorGame(Stage stage,Image image) {
		    
		       //ע��Ҫ��image
		        for(int i = 0, k = 0; i < gameImageOnSide; ++i) {
		            for(int j = 0; j < gameImageOnSide; ++j, ++k) {
		                imageViews[k] = new ImageView(image);        //��ʼ������
		                imageViews[k].setOnMouseClicked(new myevent());      //���õ���¼�
		                //�и�ͼƬ
		                imageViews[k].setViewport(new Rectangle2D(gameImageLength* j, gameImageLength * i, gameImageLength,gameImageLength ));             
		                
		            }
		        }

		        //imageView���±��൱�ڶ������������ֲ�α���
		        //����һ�������ֻ��0-3���ĸ���������
		        place=random_num();
		        gridPane.add(imageViews[place[0]], 0, 0);                         
		        gridPane.add(imageViews[place[1]], 0, 1);
		        gridPane.add(imageViews[place[2]], 0, 2);
		        gridPane.add(imageViews[place[3]], 0, 3);
		        gridPane.add(imageViews[place[4]], 1, 0);                         
		        gridPane.add(imageViews[place[5]], 1, 1);
		        gridPane.add(imageViews[place[6]], 1, 2);
		        gridPane.add(imageViews[place[7]], 1, 3);
		        gridPane.add(imageViews[place[8]], 2, 0);                         
		        gridPane.add(imageViews[place[9]], 2, 1);
		        gridPane.add(imageViews[place[10]], 2,2);
		        gridPane.add(imageViews[place[11]], 2, 3);
		        gridPane.add(imageViews[place[12]], 3, 0);                         
		        gridPane.add(imageViews[place[13]], 3, 1);
		        gridPane.add(imageViews[place[14]], 3, 2);
		        
		        gridPane.add(imageViews[place[15]], 3, 3);
		        imageViews[place[15]].setVisible(false);//�Ȱ�������Ϊ���ɼ�
		        
		        //��ʾ��ȱʧ����һС��
		        deficiency.setImage(imageViews[place[gameImageOnSide*gameImageOnSide-1]].getImage());
		        deficiency.setViewport(imageViews[place[gameImageOnSide*gameImageOnSide-1]].getViewport());
		        deficiencyTip.getChildren().add(deficiency);//ȱʧ���ǿ飬����ʾ����
		    
		        
		        gridPane.setGridLinesVisible(true);
		        
		        Pane gamePane = new Pane();
		        gamePane.getChildren().add(gridPane);
		        gridPane.setLayoutX(gameImageLength/2);
		        gridPane.setLayoutY(gameImageLength/2);
		        // ����borderPane.setCenter(gridPane);
		        //�����Ǹ�Ԥ��ͼ
		        ImageView smallImage=new ImageView(image);
		        smallImage.setViewport(new Rectangle2D(0,0,
		        		gameImageLength*gameImageOnSide,gameImageLength*gameImageOnSide));
		        //minx,miny,width,height
		        smallImage.setFitHeight(gameImageLength);
		        smallImage.setFitWidth(gameImageLength);
		 
		        
		       

		        Button returnToMain=new Button("����������");
		        VBox right=new VBox(smallImage,deficiencyTip,returnToMain);
		        
		        gamePane.getChildren().add(right);
		        right.setLayoutX(sceneWidth-smallImage.getFitWidth());
		        right.setLayoutY(gridPane.getLayoutY());
		       
		        returnToMain.setLayoutX(right.getLayoutX());
		        returnToMain.setLayoutY(gridPane.getLayoutY()+gridPane.getHeight());
		        
		        SuperiorBgPane bg=new SuperiorBgPane();
		        
		        
		        StackPane stackPane=new StackPane();
		        stackPane.getChildren().addAll(bg,gamePane);
		        
		        Scene sixteenth = new Scene(stackPane);
		        //�������Ĺ��񳡾�    
		        
		        stage.setScene(sixteenth);
		        
		        returnToMain.setOnAction(e->{
		        	stage.setScene(Main.mainScene);
		        });
		    }
		    

	public int[] random_num() {      //����4�����ظ���
		        int r[] = new int[gameImageOnSide*gameImageOnSide];
		        for(int i = 0; i < gameImageOnSide*gameImageOnSide; ++i) {
		            r[i] = random.nextInt(gameImageOnSide*gameImageOnSide);
		            for(int j = 0;j < i; ++j) {
		                while(r[i] == r[j]) {
		                    i--;
		                    break;
		                }
		            }
		        }
		        return r;
		    }

	class myevent implements EventHandler<MouseEvent> {               //����¼���ʵ��

		    @Override
		        public void handle(MouseEvent arg0) {
		            ImageView img = (ImageView) arg0.getSource();
		            double sx = img.getLayoutX();
		            double sy = img.getLayoutY();
		            double dispx = sx - imageViews[place[gameImageOnSide*gameImageOnSide-1]].getLayoutX();
		            double dispy = sy - imageViews[place[gameImageOnSide*gameImageOnSide-1]].getLayoutY();
		            //����Ŀո���ߵĸ���
		            if((dispx == -gameImageLength) && (dispy == 0 )) {
		                swapimg(img, imageViews[place[gameImageOnSide*gameImageOnSide-1]]);             //����imageView
		                if(issucc()) {  
		                	//�ж��Ƿ�ƴ�ɹ�
		                    Alert alert = new Alert(AlertType.WARNING, "�ɹ���");
		                    imageViews[place[gameImageOnSide*gameImageOnSide-1]].setVisible(true);
		                    alert.show();
		                }
		            }

		            //����ĸ���
		            else if ((dispx == 0) && (dispy == -gameImageLength)) { 
		                swapimg(img, imageViews[place[gameImageOnSide*gameImageOnSide-1]]);
		                if(issucc()) {
		                	Alert alert = new Alert(AlertType.WARNING, "�ɹ���");
		                    imageViews[place[gameImageOnSide*gameImageOnSide-1]].setVisible(true);
		                    alert.show();
		                }
		            }
		               //�ұߵĸ���
		            else if((dispx == gameImageLength) && (dispy == 0)) { 
		                swapimg(img, imageViews[place[gameImageOnSide*gameImageOnSide-1]]);
		                if(issucc()) {
		                	Alert alert = new Alert(AlertType.WARNING, "�ɹ���");
		                    imageViews[place[gameImageOnSide*gameImageOnSide-1]].setVisible(true);
		                    alert.show();
		                }
		            }
		                //����ĸ���
		            else if((dispx == 0) && (dispy == gameImageLength)) { 
		                swapimg(img, imageViews[place[gameImageOnSide*gameImageOnSide-1]]);
		                if(issucc()) {
		                	Alert alert = new Alert(AlertType.WARNING, "�ɹ���");
		                    imageViews[place[gameImageOnSide*gameImageOnSide-1]].setVisible(true);
		                    alert.show();
		                }
		            }
		        }
		        public void swapimg(ImageView i1, ImageView i2) {              //��������imageView��ʵ��
		            int row1 = GridPane.getRowIndex(i1);
		            int colu1 = GridPane.getColumnIndex(i1);
		            int row2 = GridPane.getRowIndex(i2);
		            int colu2 = GridPane.getColumnIndex(i2);

		            GridPane.setRowIndex(i1, row2);
		            GridPane.setColumnIndex(i1, colu2);
		            GridPane.setRowIndex(i2, row1);
		            GridPane.setColumnIndex(i2, colu1);
		        }
		    }
	public boolean issucc() {//�ж��Ƿ�ƴ�ɹ�
		//row����
		 for(int i = 0; i < gameImageOnSide*gameImageOnSide ; ++i) {
	         if(i != gameImageOnSide * GridPane.getRowIndex(imageViews[i]) + GridPane.getColumnIndex(imageViews[i])) {
	             return false;
	         }
	     }
	        return true;
		    	
	}
	}
