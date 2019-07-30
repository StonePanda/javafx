package application;
import javafx.animation.*;
import javafx.scene.shape.*;
import javafx.util.Duration;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.*;

public class LowBgPane extends Pane{
	public int i;
	private Random random=new Random(4);
	public int numOfNode=100;
	public double[] radius=new double[numOfNode];
	public double [] x=new double [numOfNode];
	public double[] y=new double[numOfNode];
	public double[] mouseNewX=new double[numOfNode];
	public double[] mouseNewY=new double[numOfNode];
	public Circle[] circle=new Circle[numOfNode];
	public Circle shootingCircle=new Circle();
	public Line shootingCircleLine=new Line();
	public Line[] line=new Line[numOfNode];
	private double[] dx=new double[numOfNode];
	private double[] dy=new double[numOfNode];
	private Timeline animation;
	private Timeline shootingStar;
	public int timeChange=100;
	public int sceneWidth=750;
	public int sceneHeight=750;
	public int distance=50;
	public int MouseClick=70;
	public Rectangle blackBg=new Rectangle(0,0,sceneWidth,sceneHeight);
	
	
	public LowBgPane(){
		setHeight(sceneHeight);
		setWidth(sceneWidth);
		for(i=0;i<numOfNode/2;i++){
			dx[i]=0.1;
		}
		for(i=0;i<numOfNode/2;i++){
			dy[i]=0.1;
		}
		for(i=numOfNode/2;i<numOfNode;i++){
			dx[i]=-0.1;
		}
		for(i=numOfNode/2;i<numOfNode;i++){
			dy[i]=-0.1;
		}
		for(i=0;i<numOfNode;i++){
			x[i]=random.nextDouble()*sceneWidth;
		}
		for(i=0;i<numOfNode;i++){
			y[i]=random.nextDouble()*sceneHeight;
		}
		for(i=0;i<numOfNode;i++){
			radius[i]=random.nextDouble()+0.4;
		}
		for(i=0;i<numOfNode;i++){
			circle[i]=new Circle(radius[i]);
			circle[i].setCenterX(x[i]);
			circle[i].setCenterY(y[i]);
			circle[i].setFill(Color.WHITE);
		}
		
		//·É¹ýµÄÁ÷ÐÇ
		
		
		/*shootingCircleLine.setStartX(10);
		shootingCircleLine.setStartX(10);
		shootingCircleLine.setEndX(100);
		shootingCircleLine.setEndX(100);
		shootingCircleLine.setStroke(Color.WHITE);
		shootingCircleLine.setStrokeWidth(10);
		*/
		blackBg.setFill(Color.BLACK);
    	getChildren().addAll(blackBg);
		getChildren().addAll(circle);
		getChildren().addAll(shootingCircleLine);
		
		//shootingCircle,
		
		animation =new Timeline(
				new KeyFrame(Duration.millis(timeChange),e->move()));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();
		
		shootingStar=new Timeline(
				new KeyFrame(Duration.millis(timeChange),e->shootingStar()));
		shootingStar.setCycleCount(Timeline.INDEFINITE);
		shootingStar.play();
	}
	
	public void move(){
		for(i=0;i<numOfNode;i++){
			if(x[i]<radius[i]||x[i]>sceneWidth-radius[i]){
				dx[i]*=-1;
			}
			if(y[i]<radius[i]||y[i]>sceneHeight-radius[i]){
				dy[i]*=-1;
			}
		
			
			x[i]+=dx[i];
			
			y[i]+=dy[i];
			
			circle[i].setCenterX(x[i]);
			circle[i].setCenterY(y[i]);
			
		}
	}
	public void shootingStar(){
		if(random.nextBoolean()==true){
		if(shootingCircle.getCenterX()>sceneWidth*2||shootingCircle.getCenterY()>sceneHeight*2
				||shootingCircle.getCenterX()<0||shootingCircle.getCenterY()<0){
			shootingCircle.setCenterX(random.nextDouble()*sceneWidth);
			shootingCircle.setCenterY(random.nextDouble()*sceneHeight);
			shootingCircle.setRadius(random.nextDouble()*10);
		}
		shootingCircle.setRadius(1);
		shootingCircle.setStroke(Color.WHITE);
		shootingCircle.setFill(Color.WHITE);
		
		shootingCircleLine.setStartX(shootingCircle.getCenterX());
		shootingCircleLine.setStartY(shootingCircle.getCenterY());
		shootingCircleLine.setEndX(shootingCircle.getCenterX()+100);
		shootingCircleLine.setEndY(shootingCircle.getCenterY()+100);
		//shootingCircleLine.setFill(Color.WHITE);
		shootingCircleLine.setStroke(Color.WHITE);
		shootingCircleLine.setStrokeWidth(1);
		
		
		double yuanX;
		
		yuanX=shootingCircle.getCenterX()+100;
		
		double yuanY=shootingCircle.getCenterY()+100;
		shootingCircle.setCenterX(yuanX);
		shootingCircle.setCenterY(yuanY);
		
		FadeTransition ft=new FadeTransition(Duration.millis(timeChange),shootingCircleLine);
		ft.setFromValue(1.0);
		ft.setToValue(0.0);
		ft.play();
		}
	}
}
