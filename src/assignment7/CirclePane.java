/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment7;

// Assignment #7: Arizona State University CSE205
//          Name:
//     StudentID:
//       Lecture: (MWF 8:00am)
//   Description: CirclePane class creates a pane where we can use
//                mouse key to drag on canvass and there will be some color following
//                the mouse. We can also use combo boxes to change its colors and stroke widths
//                //--- is where you need to add your own code

//import any necessary classes here
//----
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import static javafx.scene.control.Alert.AlertType.INFORMATION;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CirclePane extends BorderPane
{
    //instance variables - check assignment's description
    //----

    private ComboBox<String> primaryColorCombo;
    private ComboBox<String> bgColorCombo;
    private ComboBox<String> widthCombo;
    private Circle[][] circleArray;

    private final int NUM_COL =6, NUM_ROW = 6, RADIUS = 40;
    private GridPane canvas;	//this is where circles are drawn
    private javafx.scene.paint.Color PrimaryColor=Color.WHITE;
    private javafx.scene.paint.Color Secondary=Color.GRAY;
    private javafx.scene.paint.Color Background=Color.GRAY;
    private int StrokeWidth=1;
    //constructor
    public CirclePane()
    {
        //Step #1: Initialize instance varibles and set up the layout
		//----
        primaryColorCombo=new ComboBox();
        bgColorCombo=new ComboBox();
        widthCombo=new ComboBox();
        primaryColorCombo.getItems().add("Black");
        primaryColorCombo.getItems().add("Red");
        primaryColorCombo.getItems().add("Green");
        primaryColorCombo.getItems().add("Blue");
        bgColorCombo.getItems().add("White");
        bgColorCombo.getItems().add("Beige");
        bgColorCombo.getItems().add("AliceBlue");
        bgColorCombo.getItems().add("Gold");
        
        
        widthCombo.getItems().add("1");
        widthCombo.getItems().add("3");
        widthCombo.getItems().add("5");

        //Instantiate the two dimensional circleArray that contains
        //6 columns and 6 rows of circles (36 in total)
        //----
         circleArray=new Circle[NUM_ROW][NUM_COL];
        //instantiate canvas and set its width and height
        canvas = new GridPane();
        canvas.setPadding(new Insets(10, 10, 10, 10));
        canvas.setPrefWidth(2*RADIUS * NUM_COL);
        canvas.setPrefHeight(2*RADIUS * NUM_ROW);

        //Use nested loop to instantiate the 6 columns by 6 rows of
        //Circle objects, add them inside the circleArrary
        //----
        //----
        for(int i=0;i<NUM_ROW;i++)
        {
            for(int j=0;j<NUM_COL;j++)
            {
                circleArray[i][j]=new Circle(40,PrimaryColor);
                circleArray[i][j].setStrokeWidth(3);
                circleArray[i][j].setStroke(Color.BLACK);
                canvas.add(circleArray[i][j], j, i);
                
            }
        }
        
        //leftPane is a VBox, it should contain labels and the 3 comboBox
        VBox leftPane = new VBox();
        leftPane.setSpacing(20);
        leftPane.setPadding(new Insets(10, 10, 10, 10));
        leftPane.setStyle("-fx-border-color: black");
        //----
        leftPane.getChildren().add(new Label("Primary Color"));
        leftPane.getChildren().add(primaryColorCombo);
        leftPane.getChildren().add(new Label("Backdround Color"));
        leftPane.getChildren().add(bgColorCombo);
        leftPane.getChildren().add(new Label("Stroke Width"));
        leftPane.getChildren().add(widthCombo);
        //add leftPane and canvas to CirclePane
        //----
        super.setLeft(leftPane);
        super.setRight(canvas);
        //CirclePane.setAlignment(canvas, Pos.TOP_RIGHT);
        //Step 3: register the source nodes with its handler objects
        //----
        primaryColorCombo.setOnAction(new PrimaryColorHandler());
        bgColorCombo.setOnAction(new BackgroundColorHandler());
        widthCombo.setOnAction(new WidthHandler());
        canvas.setOnMouseDragged(new MouseHandler());
    }

    //Step #2(A) - MouseHandler
    private class MouseHandler implements EventHandler<MouseEvent>
    {
        public void handle(MouseEvent event)
        {
           if(event.getEventType()== MouseEvent.MOUSE_DRAGGED)
           {
              int column=canvas.getColumnIndex((Node)event.getTarget());
              int row=canvas.getRowIndex((Node)event.getTarget());
               System.out.println(column+"   "+row);
               circleArray[row][column].setFill(PrimaryColor);
               if(row-1>=0)
               {
                   circleArray[row-1][column].setFill(Secondary);
               }
               if(row+1<NUM_ROW)
               {
                   circleArray[row+1][column].setFill(Secondary);
               }
               if(column-1>=0)
               {
                   circleArray[row][column-1].setFill(Secondary);
               }
               if(column+1<NUM_COL)
               {
                   circleArray[row][column+1].setFill(Secondary);
               }
           }
			
          
        }
    }//end MouseHandler

	//Step #2(B) - Primary and secondary color handler
    private class PrimaryColorHandler implements EventHandler<ActionEvent>
    {
        public void handle(ActionEvent event)
        {
            String selecteditem=primaryColorCombo.getSelectionModel().getSelectedItem().toString();
            System.out.println(selecteditem);
            
            if(selecteditem.equals("Black"))
            {
                PrimaryColor=Color.BLACK;
                Secondary=Color.GRAY;
            }
            else if(selecteditem.equals("Red"))
            {
                PrimaryColor=Color.RED;
                Secondary=Color.PINK;
            }
            else if(selecteditem.equals("Green"))
            {
                PrimaryColor=Color.GREEN;
                Secondary=Color.LIGHTGREEN;
            }
            else
            {
                PrimaryColor=Color.BLUE;
                Secondary=Color.POWDERBLUE;
            }
			//Write your own code here
			//----

		}
    }//end PrimaryColorHandler

    //Step #2(C): background color handler
    //Write a private class called BackgroundColorHandler that handles the background
    //color changes
    //----
    //----
    private class BackgroundColorHandler implements EventHandler<ActionEvent>
    {
        public void handle(ActionEvent event)
        {
            String selecteditem=bgColorCombo.getSelectionModel().getSelectedItem().toString();
            //System.out.println(selecteditem);
            
            if(selecteditem.equals("White"))
            {
                Background=Color.WHITE;
            }
            else if(selecteditem.equals("Beige"))
            {
                 Background=Color.BEIGE;
                 System.out.println(selecteditem);
            }
            else if(selecteditem.equals("AliceBlue"))
            {
                 Background=Color.ALICEBLUE;
                 System.out.println(selecteditem);

            }
            else
            {
                 Background=Color.GOLD;
            }
        for(int i=0;i<NUM_ROW;i++)
        {
            for(int j=0;j<NUM_COL;j++)
            {
                circleArray[i][j].setFill(Background);
                
                
            }
        }
            
			//Write your own code here
			//----

		}
    }//end PrimaryColorHandler


    //Step #2(D): handle the stroke width
    private class WidthHandler implements EventHandler<ActionEvent>
    {

        @Override
        public void handle(ActionEvent event) {
           String selecteditem=widthCombo.getSelectionModel().getSelectedItem().toString();
            //System.out.println(selecteditem);
            
            if(selecteditem.equals("1"))
            {
                StrokeWidth=1;
            }
            else if(selecteditem.equals("3"))
            {
                StrokeWidth=3;
            }
            else
            {
                StrokeWidth=5;
            }
        for(int i=0;i<NUM_ROW;i++)
        {
            for(int j=0;j<NUM_COL;j++)
            {
                circleArray[i][j].setStrokeWidth(StrokeWidth);
                
                
            }
        }
        }
        //write your own code
        //----
        //----


    }//end of WidthHandler

} //end of CirclePane