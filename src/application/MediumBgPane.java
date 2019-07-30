package application;

import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.scene.layout.*;

public class MediumBgPane extends Pane{
	public int i;
	public int numOfNode=400;
	private Random random=new Random(4);
	public double [] x=new double [numOfNode];
	public double[] y=new double[numOfNode];
	public Circle[] circle=new Circle[numOfNode];
	public double[] dOpacity=new double[numOfNode];
	private Timeline animation;
	public int timeChange=100;
	public double[] radius=new double[numOfNode];
	public int sceneWidth=750;
	public int sceneHeight=750;
	public Rectangle blackBg=new Rectangle(0,0,sceneWidth,sceneHeight);
	
	public MediumBgPane(){
		setHeight(sceneHeight);
		setWidth(sceneWidth);

		for(i=0;i<numOfNode;i++){
			radius[i]=random.nextDouble()*4;
		}
		
		for(i=0;i<numOfNode;i++){
			dOpacity[i]=0.1;
		}
		
		//控制小点产生的位置
		for(i=0;i<numOfNode;i++){
			if(radius[i]<=2){
				x[i]=random.nextDouble()*sceneWidth/3+sceneWidth/3;
				y[i]=random.nextDouble()*sceneHeight/3+sceneHeight/3;
			}
			else{
				if(i<numOfNode/4){
					x[i]=random.nextDouble()*sceneWidth/3;
					y[i]=random.nextDouble()*sceneWidth;
				}
				else if(i<numOfNode/2){
					x[i]=random.nextDouble()*sceneWidth/3+sceneWidth/3;
					y[i]=random.nextDouble()*sceneWidth/3+sceneWidth*2/3;
				}
				else if(i<=numOfNode*3/4){
					x[i]=random.nextDouble()*sceneWidth/3+sceneWidth*2/3;
					y[i]=random.nextDouble()*sceneWidth;
				}
				else{
					x[i]=random.nextDouble()*sceneWidth/3+sceneWidth/3;
					y[i]=random.nextDouble()*sceneWidth/3;
				}
			}
		}
		
		
		for(i=0;i<numOfNode;i++){
			circle[i]=new Circle(radius[i]);
			circle[i].setCenterX(x[i]);
			circle[i].setCenterY(y[i]);
			circle[i].setFill(Color.WHITE.brighter());
			circle[i].setOpacity(random.nextDouble());//.darker()
			circle[i].setStroke(Color.rgb(31, 69, 132));
			circle[i].setStrokeWidth(radius[i]);
		}
		
		blackBg.setFill(Color.rgb(5,13,25));
		
		getChildren().addAll(blackBg);
		getChildren().addAll(circle);
		
		animation =new Timeline(
				new KeyFrame(Duration.millis(timeChange),e->move()));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();

	}
	
	public void move(){
		double temOpacity;
		double temCenterX;
		double temCenterY;
		for(i=numOfNode/4;i<numOfNode/2;i++){
			if(circle[i].getOpacity()>=0.9||circle[i].getOpacity()<=0.1)
				dOpacity[i]*=-1;
			
			temOpacity=circle[i].getOpacity();
			circle[i].setOpacity(temOpacity+dOpacity[i]);
		}
		
		for(i=0;i<numOfNode;i++){
			if(circle[i].getCenterX()>sceneWidth||circle[i].getCenterX()<0
					||circle[i].getCenterY()>sceneHeight||circle[i].getCenterY()<0)
			{
				circle[i].setCenterX(random.nextDouble()*sceneWidth);
				circle[i].setCenterY(random.nextDouble()*sceneHeight);
			}
				
			temCenterX=circle[i].getCenterX();
			temCenterY=circle[i].getCenterY();
			if(temCenterX<=sceneWidth/2&&temCenterY>=sceneHeight/2){
				circle[i].setCenterX(temCenterX+dOpacity[i]*2);
				circle[i].setCenterY(temCenterY+dOpacity[i]*2);
			}
			else if(temCenterX>=sceneWidth/2&&temCenterY<=sceneHeight/2){
				circle[i].setCenterX(temCenterX-dOpacity[i]*2);
				circle[i].setCenterY(temCenterY-dOpacity[i]*2);
			}
			else if(temCenterX>=sceneWidth/2&&temCenterY>=sceneHeight/2){
				circle[i].setCenterX(temCenterX+dOpacity[i]*2);
				circle[i].setCenterY(temCenterY-dOpacity[i]*2);
			}
			else if(temCenterX<=sceneWidth/2&&temCenterY<=sceneHeight/2){
				circle[i].setCenterX(temCenterX-dOpacity[i]*2);
				circle[i].setCenterY(temCenterY+dOpacity[i]*2);
			}
		}
		
		
		
	}
	

}
