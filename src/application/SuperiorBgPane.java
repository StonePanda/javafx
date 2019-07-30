package application;

import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.scene.layout.*;

public class SuperiorBgPane extends Pane{
		public int i;
		public int numOfNode=200;
		private Random random=new Random(4);
		public double [] x=new double [numOfNode];
		public double[] y=new double[numOfNode];
		public Circle[] circle=new Circle[numOfNode];
		private Timeline animation;
		public int timeChange=100;
		public double[] radius=new double[numOfNode];
		public int sceneWidth=750;
		public int sceneHeight=750;
		public Rectangle blackBg=new Rectangle(0,0,sceneWidth,sceneHeight);
		
		public SuperiorBgPane(){
			setHeight(sceneHeight);
			setWidth(sceneWidth);

			for(i=0;i<numOfNode;i++){
				radius[i]=random.nextDouble();
			}
			
			//控制小点产生的位置
			for(i=0;i<numOfNode;i++){
				x[i]=random.nextDouble()*sceneWidth/2+sceneWidth/5;
				y[i]=random.nextDouble()*sceneWidth/2+sceneWidth/5;
			}
			
			
			for(i=0;i<numOfNode;i++){
				circle[i]=new Circle(radius[i]);
				circle[i].setCenterX(x[i]);
				circle[i].setCenterY(y[i]);
				circle[i].setFill(Color.WHITE.brighter());
				circle[i].setStroke(new Color(random.nextDouble(),random.nextDouble(),random.nextDouble(),1.0));
				circle[i].setStrokeWidth(2);
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
			double temCenterX;
			double temCenterY;
			double temRadius;
			double dx=1,dy=1;
			for(i=0;i<numOfNode;i++){
				if(circle[i].getCenterX()>sceneWidth||circle[i].getCenterX()<0
						||circle[i].getCenterY()>sceneHeight||circle[i].getCenterY()<0)
				{
					circle[i].setCenterX(random.nextDouble()*sceneWidth/2+sceneWidth/5);
					circle[i].setCenterY(random.nextDouble()*sceneWidth/2+sceneWidth/5);
					circle[i].setRadius(random.nextDouble());
				}
					
				temCenterX=circle[i].getCenterX();
				temCenterY=circle[i].getCenterY();
				temRadius=circle[i].getRadius();

				if(temCenterX<=sceneWidth/2&&temCenterY>=sceneHeight/2&&i<numOfNode/5){
						circle[i].setRadius(temRadius+dy/20);
						circle[i].setCenterX(temCenterX-dx*circle[i].getRadius()*2);
						circle[i].setCenterY(temCenterY+dy*circle[i].getRadius()*2);
						
					}
				else if(temCenterX>=sceneWidth/2&&temCenterY<=sceneHeight/2&&i<numOfNode/5){
					circle[i].setRadius(temRadius+dy/20);
						circle[i].setCenterX(temCenterX+dx*circle[i].getRadius()*2);
						circle[i].setCenterY(temCenterY-dy*circle[i].getRadius()*2);
						
					}
				else if(temCenterX>=sceneWidth/2&&temCenterY>=sceneHeight/2&&i<numOfNode/5){
					circle[i].setRadius(temRadius+dy/20);
						circle[i].setCenterX(temCenterX+dx*circle[i].getRadius()*2);
						circle[i].setCenterY(temCenterY+dy*circle[i].getRadius()*2);
						
					}
				else if(temCenterX<=sceneWidth/2&&temCenterY<=sceneHeight/2&&i<numOfNode/5){
					circle[i].setRadius(temRadius+dy/20);
						circle[i].setCenterX(temCenterX-dx*circle[i].getRadius()*2);
						circle[i].setCenterY(temCenterY-dy*circle[i].getRadius()*2);
					}

				else if(temCenterX<=sceneWidth/2&&temCenterY>=sceneHeight/2&&i<numOfNode/4){
					circle[i].setRadius(temRadius+dy/40);
					circle[i].setCenterX(temCenterX-dx*circle[i].getRadius()*2);
					circle[i].setCenterY(temCenterY+dy*circle[i].getRadius()/10);
					
				}
				else if(temCenterX>=sceneWidth/2&&temCenterY<=sceneHeight/2&&i<numOfNode/4){
				circle[i].setRadius(temRadius+dy/40);
					circle[i].setCenterX(temCenterX+dx*circle[i].getRadius()*2);
					circle[i].setCenterY(temCenterY-dy*circle[i].getRadius()/10);
					
				}
				else if(temCenterX>=sceneWidth/2&&temCenterY>=sceneHeight/2&&i<numOfNode/4){
				circle[i].setRadius(temRadius+dy/40);
					circle[i].setCenterX(temCenterX+dx*circle[i].getRadius()/10);
					circle[i].setCenterY(temCenterY+dy*circle[i].getRadius()*2);
					
				}
				else if(temCenterX<=sceneWidth/2&&temCenterY<=sceneHeight/2&&i<numOfNode/4){
					circle[i].setRadius(temRadius+dy/40);
					circle[i].setCenterX(temCenterX-dx*circle[i].getRadius()/10);
					circle[i].setCenterY(temCenterY-dy*circle[i].getRadius()*2);
					
				}
				else{
					if(temCenterX<=sceneWidth/2&&temCenterY>=sceneHeight/2){
						circle[i].setCenterX(temCenterX-dx*circle[i].getRadius());
						circle[i].setCenterY(temCenterY+dy*circle[i].getRadius());
						}
						else if(temCenterX>=sceneWidth/2&&temCenterY<=sceneHeight/2){
							circle[i].setCenterX(temCenterX+dx*circle[i].getRadius());
							circle[i].setCenterY(temCenterY-dy*circle[i].getRadius());
						}
					
				else if(temCenterX>=sceneWidth/2&&temCenterY>=sceneHeight/2){
						circle[i].setCenterX(temCenterX+dx*circle[i].getRadius());
						circle[i].setCenterY(temCenterY+dy*circle[i].getRadius());
						
					}
				else if(temCenterX<=sceneWidth/2&&temCenterY<=sceneHeight/2){
						circle[i].setCenterX(temCenterX-dx*circle[i].getRadius());
						circle[i].setCenterY(temCenterY-dy*circle[i].getRadius());
					}
					if(circle[i].getCenterX()<=sceneWidth/3||circle[i].getCenterX()>=sceneWidth*2/3
							||circle[i].getCenterY()<=sceneWidth/3||circle[i].getCenterY()>=sceneWidth*2/3){
						circle[i].setCenterX(random.nextDouble()*sceneWidth/2+sceneWidth/5);
						circle[i].setCenterY(random.nextDouble()*sceneWidth/2+sceneWidth/5);
						circle[i].setRadius(random.nextDouble());
					}
					
				}
				
		}
	}
			
		
		
}