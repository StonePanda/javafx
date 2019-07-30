package application;

import java.util.Random;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.Button;

public class mediumGame {
	
	public int m;          //m是不在随机数组的那个数字
    public ImageView[] imageViews = new ImageView[9];
    public GridPane gridPane = new GridPane();
    public VBox deficiencyTip=new VBox();
    public ImageView deficiency = new ImageView();
	
	public mediumGame(Stage stage) {
	    
		 //自定义的函数，产生逆序数为偶数的不重复数组
	       int[] n = random();             
	    
	       Image image = new Image("image/1.jpg");
	     
	        for(int i = 0, k = 0; i <= 2; ++i) {
	            for(int j = 0; j <= 2; ++j, ++k) {
	                imageViews[k] = new ImageView(image);        //初始化数组
	                imageViews[k].setOnMouseClicked(new myevent());      //设置点击事件
	                imageViews[k].setViewport(new Rectangle2D( 100* j, 100 * i, 100,100 ));             //切割图片
	            }
	        }

	        gridPane.add(imageViews[n[0]], 0, 0);                         //按照产生的随机数将imageView数组加入面板
	        gridPane.add(imageViews[n[1]], 1, 0);
	        gridPane.add(imageViews[n[2]], 2, 0);
	        gridPane.add(imageViews[n[3]], 0, 1);
	        gridPane.add(imageViews[n[4]], 1, 1);
	        gridPane.add(imageViews[n[5]], 2, 1);
	        gridPane.add(imageViews[n[6]], 0, 2);
	        gridPane.add(imageViews[n[7]], 1, 2);
	  
	      //找出那个不在随机数组里面的数字
	        m = findnum(n);    
	        
	      //用于显示空格子的图片
	        deficiency.setImage(imageViews[m].getImage());
	        deficiency.setViewport(imageViews[m].getViewport());
	        deficiencyTip.getChildren().add(deficiency);//缺失的那块，得提示出来
	    
	       
	        gridPane.add(imageViews[m], 2, 2);
	        imageViews[m].setVisible(false);
	        //添加到右下角那个块里
	        gridPane.setGridLinesVisible(true);
	        
	        Pane gamePane = new Pane();
	        gamePane.getChildren().add(gridPane);
	        gridPane.setLayoutX(50);
	        gridPane.setLayoutY(100);
	        // 就是borderPane.setCenter(gridPane);
	        //就是那个预览图
	        ImageView smallImage=new ImageView(image);
	        smallImage.setViewport(new Rectangle2D(0,0,300,300));
	        //minx,miny,width,height
	        smallImage.setFitHeight(100);
	        smallImage.setFitWidth(100);
	 
	        VBox preview = new VBox(smallImage);
	        
	        Button returnToMain=new Button("返回主界面");
	        VBox right=new VBox(preview,deficiencyTip,returnToMain);
	        right.setPadding(new Insets(5,5,5,5));
	        
	        returnToMain.setLayoutX(right.getLayoutX());
	        returnToMain.setLayoutY(gridPane.getLayoutY()+gridPane.getHeight());
	        
	        gamePane.getChildren().add(right);
	        right.setLayoutX(400);
	        right.setLayoutY(100);
	       
	        
	        MediumBgPane bg=new MediumBgPane();
	        StackPane stackPane=new StackPane();
	        stackPane.getChildren().addAll(bg,returnToMain);//,gamePane
	        
	        Scene nine = new Scene(stackPane);
	        //以上是九宫格场景    
	        
	        stage.setScene(nine);
	        
	        returnToMain.setOnAction(e->{
	        	stage.setScene(Main.mainScene);
	        });
	        
	    }
	
	public mediumGame(Stage stage,Image image) {
	    
		 //自定义的函数，产生逆序数为偶数的不重复数组
	       int[] n = random();             
	    
	      // Image image = new Image("image/1.png");
	     
	        for(int i = 0, k = 0; i <= 2; ++i) {
	            for(int j = 0; j <= 2; ++j, ++k) {
	                imageViews[k] = new ImageView(image);        //初始化数组
	                imageViews[k].setOnMouseClicked(new myevent());      //设置点击事件
	                imageViews[k].setViewport(new Rectangle2D( 100* j, 100 * i, 100,100 ));             //切割图片
	            }
	        }

	        gridPane.add(imageViews[n[0]], 0, 0);                         //按照产生的随机数将imageView数组加入面板
	        gridPane.add(imageViews[n[1]], 1, 0);
	        gridPane.add(imageViews[n[2]], 2, 0);
	        gridPane.add(imageViews[n[3]], 0, 1);
	        gridPane.add(imageViews[n[4]], 1, 1);
	        gridPane.add(imageViews[n[5]], 2, 1);
	        gridPane.add(imageViews[n[6]], 0, 2);
	        gridPane.add(imageViews[n[7]], 1, 2);
	  
	      //找出那个不在随机数组里面的数字
	        m = findnum(n);    
	        
	      //用于显示空格子的图片
	        deficiency.setImage(imageViews[m].getImage());
	        deficiency.setViewport(imageViews[m].getViewport());
	        deficiencyTip.getChildren().add(deficiency);//缺失的那块，得提示出来
	    
	        gridPane.add(imageViews[m], 2, 2);
	        imageViews[m].setVisible(false);
	        //添加到右下角那个块里
	        
	        gridPane.setGridLinesVisible(true);
	        
	        Pane gamePane = new Pane();
	        gamePane.getChildren().add(gridPane);
	        gridPane.setLayoutX(50);
	        gridPane.setLayoutY(0);
	        // 就是borderPane.setCenter(gridPane);
	        //就是那个预览图
	        ImageView smallImage=new ImageView(image);
	        smallImage.setViewport(new Rectangle2D(0,0,300,300));
	        //minx,miny,width,height
	        smallImage.setFitHeight(100);
	        smallImage.setFitWidth(100);
	 
	        VBox preview = new VBox(smallImage);
	        deficiencyTip.setLayoutX(600);
	        deficiencyTip.setLayoutY(300);
	        Button returnToMain=new Button("返回主界面");
	        VBox right=new VBox(preview,deficiencyTip,returnToMain);
   
	        
	        
	        returnToMain.setLayoutX(right.getLayoutX());
	        returnToMain.setLayoutY(gridPane.getLayoutY()+gridPane.getHeight());
	        
	        
	        gamePane.getChildren().add(right);
	        right.setLayoutX(400);
	        right.setLayoutY(0);
	       
	        
	        
	        MediumBgPane bg=new MediumBgPane();
	        StackPane stackPane=new StackPane();
	        stackPane.getChildren().addAll(bg,gamePane);
	        
	        Scene nine = new Scene(stackPane);
	        //以上是九宫格场景    
	        
	        stage.setScene(nine);
	        returnToMain.setOnAction(e->{
	        	stage.setScene(Main.mainScene);
	        });
	      
	    }
	    
	
	    public int[] random() {             //生成8个不重复的逆序数为偶数的数字
	        int[] ran = new int[8];
	        while(iso(ran) == false) {
	            ran = random_num();
	        }
	        return ran;

	    }

	    public int[] random_num() {      //生成8个不重复数
	        int r[] = new int[8];
	        Random random = new Random();
	        for(int i = 0; i < 8; ++i) {
	            r[i] = random.nextInt(9);
	            for(int j = 0;j < i; ++j) {
	                while(r[i] == r[j]) {
	                    i--;
	                    break;
	                }
	            }
	        }
	        return r;
	    }

	    public boolean iso(int[] num) {          //判断逆序数是否为偶数
	        int sum = 0;
	        for(int i = 0; i <= 6; ++i) {
	            for(int j = i; j <= 7; j++) {
	                if(num[i] > num[j]) {
	                    sum++;
	                }
	            }
	        }
	        if((sum % 2) == 0 && sum != 0) {
	            return true;
	        }

	        return false;

	    }

	    class myevent implements EventHandler<MouseEvent> {               //点击事件的实现

	    @Override
	        public void handle(MouseEvent arg0) {
	        // TODO Auto-generated method stub
	            ImageView img = (ImageView) arg0.getSource();
	            double sx = img.getLayoutX();
	            double sy = img.getLayoutY();
	            double dispx = sx - imageViews[m].getLayoutX();
	            double dispy = sy - imageViews[m].getLayoutY();
	           // if((dispx == -width) && (dispy == 0 )) {               //点击的空格左边的格子
	            if((dispx == -100) && (dispy == 0 )) {
	                swapimg(img, imageViews[m]);             //交换imageView
	                if(issucc(imageViews)) {  
	                	//判断是否拼成功
	                    Alert alert = new Alert(AlertType.WARNING, "成功！");
	                    //gridPane.clearConstraints(imageViews[m]);
	                    imageViews[m].setVisible(true);
	                    alert.show();
	                }
	            }

	           // else if ((dispx == 0) && (dispy == -height)) {        //上面的格子
	            else if ((dispx == 0) && (dispy == -100)) { 
	                swapimg(img, imageViews[m]);
	                if(issucc(imageViews)) {
	                    Alert alert = new Alert(AlertType.WARNING, "成功！");
	                    imageViews[m].setVisible(true);
	                    alert.show();
	                }
	            }
	            //else if((dispx == width) && (dispy == 0)) {               //右边的格子
	            else if((dispx == 100) && (dispy == 0)) { 
	                swapimg(img, imageViews[m]);
	                if(issucc(imageViews)) {
	                    Alert alert = new Alert(AlertType.WARNING, "成功！");//弹出的对话框
	                    imageViews[m].setVisible(true);
	                    alert.show();
	                }
	            }
	            //else if((dispx == 0) && (dispy == height)) {                //下面的格子
	            else if((dispx == 0) && (dispy == 100)) { 
	                swapimg(img, imageViews[m]);
	                if(issucc(imageViews)) {
	                    Alert alert = new Alert(AlertType.WARNING, "成功！");
	                    imageViews[m].setVisible(true);
	                    alert.show();
	                }
	            }
	        }
	        public void swapimg(ImageView i1, ImageView i2) {              //交换两个imageView的实现
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
	    
	    public boolean issucc(ImageView[] imageViews) {                                //判断是否拼成功
	        for(int i = 0; i <= 8; ++i) {
	            if(i != 3 * GridPane.getRowIndex(imageViews[i]) + GridPane.getColumnIndex(imageViews[i])) {
	                return false;
	            }
	        }
	            return true;
	    }
	    //辅助函数
	    public int findnum(int[] n) {                                             //找出m
	        for(int j = 0; j <= 8; ++j) {
	            if((j == n[0]) || (j == n[1]) || (j == n[2]) || (j == n[3]) || (j == n[4]) || (j == n[5]) || (j == n[6]) || (j == n[7])) {
	            }
	            else {
	                return j;
	            }
	        }
	        return -1;
	    }
	
}
