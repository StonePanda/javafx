package application;
import javafx.animation.*;
import javafx.scene.shape.*;
import javafx.util.Duration;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.*;

public class BgPane extends Pane{
	public int i;
	private Random random=new Random(4);
	public int numOfNode=100;
	public double[] radius=new double[numOfNode];
	public double [] x=new double [numOfNode];
	public double[] y=new double[numOfNode];
	public double[] mouseNewX=new double[numOfNode];
	public double[] mouseNewY=new double[numOfNode];
	public Circle[] circle=new Circle[numOfNode];
	public Line[] line=new Line[numOfNode];
	public Line[] mouseLine=new Line[numOfNode];
	private double[] dx=new double[numOfNode];
	private double[] dy=new double[numOfNode];
	private Timeline animation;
	private Timeline animationOfLine;
	public int timeChange=100;
	public int sceneWidth=750;
	public int sceneHeight=750;
	public int distance=50;
	public int MouseClick=70;
	
	public BgPane(){
		setHeight(sceneHeight);
		setWidth(sceneWidth);
		for(i=0;i<numOfNode/2;i++){
			dx[i]=1;
		}
		for(i=0;i<numOfNode/2;i++){
			dy[i]=1;
		}
		for(i=numOfNode/2;i<numOfNode;i++){
			dx[i]=-1;
		}
		for(i=numOfNode/2;i<numOfNode;i++){
			dy[i]=-1;
		}
		for(i=0;i<numOfNode;i++){
			x[i]=random.nextDouble()*sceneWidth;
		}
		for(i=0;i<numOfNode;i++){
			y[i]=random.nextDouble()*sceneHeight;
		}
		for(i=0;i<numOfNode;i++){
			radius[i]=1;
		}
		for(i=0;i<numOfNode;i++){
			circle[i]=new Circle(radius[i]);
			circle[i].setCenterX(x[i]);
			circle[i].setCenterY(y[i]);
			circle[i].setFill(Color.WHITE);
		}
		getChildren().addAll(circle);
		
		animation =new Timeline(
				new KeyFrame(Duration.millis(timeChange),e->move()));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();
		
		animationOfLine =new Timeline(
				new KeyFrame(Duration.millis(timeChange),e->drawLine()));
		animationOfLine.setCycleCount(Timeline.INDEFINITE);
		animationOfLine.play();
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
	
	public void drawLine(){
		int j;
		for(i=0;i<numOfNode;i++){
			for(j=0;j<numOfNode&&j!=i;j++){
				if((x[i]-x[j])*(x[i]-x[j])+(y[i]-y[j])*(y[i]-y[j])<=distance*distance){
					line[i]=new Line(circle[i].getCenterX(),circle[i].getCenterY(),
							circle[j].getCenterX(),circle[j].getCenterY());
					line[i].setStroke(Color.WHITE);
					getChildren().add(line[i]);
					FadeTransition ft=new FadeTransition(Duration.millis(timeChange),line[i]);
					ft.setFromValue(1.0);
					ft.setToValue(0.0);
					ft.play();
				}
			}
		}
	}
}
