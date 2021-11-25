package NFARegex;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.*;

//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Example of how a cubic curve works, drag the anchors around to change the curve.fne-curve-with-arrow-head
 * Original code by jewelsea: https://stackoverflow.com/questions/13056795/cubiccurve-javafx
 */
public class Main extends Application {

    public static void main(String[] args) throws Exception { launch(args); }
    @Override
    public void start(Stage primaryStage) {

        Text t1 = new Text("n");
        t1.setLayoutX(160);
        t1.setLayoutY(25);
        t1.setFill(Color.BLACK);

        Text t2 = new Text("i");
        t2.setLayoutX(360);
        t2.setLayoutY(25);
        t2.setFill(Color.BLUEVIOLET);

        Text t3 = new Text("c");
        t3.setLayoutX(460);
        t3.setLayoutY(120);
        t3.setFill(Color.RED);

        Text t4 = new Text("a");
        t4.setLayoutX(200);
        t4.setLayoutY(150);
        t4.setFill(Color.SPRINGGREEN);

        Text t5 = new Text("l");
        t5.setLayoutX(162);
        t5.setLayoutY(220);
        t5.setFill(Color.ORANGE);

        Button btn=new Button("Details");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                BorderPane pane = new BorderPane();
                Scene scene = new Scene(pane, 600, 600);
                Text text = new Text();
                StringBuilder builder = new StringBuilder();

                ArrayList<String> words;
                File f = new File("C:\\Users\\Home\\Desktop\\mr.txt");
                String founded_in_nfa = "";

                words = MFile.getWordsFromFile(f);
                for (String cur : words) {
                    founded_in_nfa = getMaxStrAcceptedInNFA(cur,builder);
                    if (!founded_in_nfa.isEmpty())
                        builder.append("*** " + founded_in_nfa + " Accepted By NFA IN " + cur + " ***\n");
                    builder.append("\n");
                }

                text.setText(builder.toString());
                pane.setCenter(text);
                stage.setScene(scene);
                stage.show();
            }
        });


        Circle s1 = new Circle();
        StackPane st1 = new StackPane();
        Circle s2 = new Circle();
        StackPane st2 = new StackPane();
        Circle s3 = new Circle();
        StackPane st3 = new StackPane();
        Circle s4 = new Circle();
        StackPane st4 = new StackPane();

        s1.setCenterX(200f);
        s1.setCenterY(100.0f);
        s1.setRadius(30.0f);
        s1.setFill(Color.BLUE);
        Text txt=new Text("1");
        st1.getChildren().addAll(s1,txt);
        st1.setLayoutX(150);
        st1.setLayoutY(70);




        s2.setCenterX(300.0f);
        s2.setCenterY(100.0f);
        s2.setRadius(30.0f);
        s2.setFill(Color.BLUE);
        Text txt1=new Text("2");
        st2.getChildren().addAll(s2,txt1);
        st2.setLayoutX(270);
        st2.setLayoutY(70);


        s3.setCenterX(400.0f);
        s3.setCenterY(100.0f);
        s3.setRadius(30.0f);
        s3.setFill(Color.BLUE);
        Text txt2=new Text("3");
        st3.getChildren().addAll(s3,txt2);
        st3.setLayoutX(370);
        st3.setLayoutY(70);
        st3.getChildren().add(btn);


        s4.setCenterX(300.0f);
        s4.setCenterY(200.0f);
        s4.setRadius(30.0f);
        s4.setFill(Color.OLIVE);
        Text txt3=new Text("f");
        st4.getChildren().addAll(s4,txt3);
        st4.setLayoutX(270);
        st4.setLayoutY(170);

        //Creating a Group object
        Group root = new Group();
        CubicCurve curve1 = new CubicCurve(150,100,0,0,300,-20, 300, 70);
        curve1.setStroke(Color.BLACK);
        curve1.setStrokeWidth(1);
        curve1.setFill( null);

        CubicCurve curve2 = new CubicCurve(300,70,300,0,520,-40,400,69);
        curve2.setStroke(Color.BLUEVIOLET);
        curve2.setStrokeWidth(1);
        curve2.setFill( null);

        CubicCurve curve3 = new CubicCurve(430,100,400,0,1000,0,300,168);
        curve3.setStroke(Color.RED);
        curve3.setStrokeWidth(1);
        curve3.setFill( null);

        CubicCurve curve4 = new CubicCurve(150, 100, 100, 700, 700, 0, 330,198);
        curve4.setStroke(Color.ORANGE);
        curve4.setStrokeWidth(1);
        curve4.setFill( null);

        CubicCurve curve5 = new CubicCurve(212, 100, 300,250, 70, 100, 300,230);
        curve5.setStroke(Color.SPRINGGREEN);
        curve5.setStrokeWidth(1);
        curve5.setFill( null);


        double size=Math.max(curve1.getBoundsInLocal().getWidth(),
                curve1.getBoundsInLocal().getHeight());
        double scale=size/4d;

        Point2D ori=eval(curve5,0);
        Point2D tan=evalDt(curve5,0).normalize().multiply(scale);
        Path arrowIni=new Path();
        arrowIni.getElements().add(new MoveTo(ori.getX()+0.2*tan.getX()-0.2*tan.getY(),
                ori.getY()+0.2*tan.getY()+0.2*tan.getX()));
        arrowIni.getElements().add(new LineTo(ori.getX(), ori.getY()));
        arrowIni.getElements().add(new LineTo(ori.getX()+0.2*tan.getX()+0.2*tan.getY(),
                ori.getY()+0.2*tan.getY()-0.2*tan.getX()));
        ori=eval(curve1,1);
        tan=evalDt(curve1,1).normalize().multiply(scale);
        Path arrow1=new Path();
        arrow1.getElements().add(new MoveTo(ori.getX()-0.2*tan.getX()-0.2*tan.getY(),
                ori.getY()-0.2*tan.getY()+0.2*tan.getX()));
        arrow1.getElements().add(new LineTo(ori.getX(), ori.getY()));
        arrow1.getElements().add(new LineTo(ori.getX()-0.2*tan.getX()+0.2*tan.getY(),
                ori.getY()-0.2*tan.getY()-0.2*tan.getX()));
        ori = eval(curve2,1);
        tan = evalDt(curve2,1).normalize().multiply(scale);
        Path arrow2 = new Path();
        arrow2.getElements().add(new MoveTo(ori.getX()-0.2*tan.getX()-0.2*tan.getY(),
                ori.getY()-0.2*tan.getY()+0.2*tan.getX()));
        arrow2.getElements().add(new LineTo(ori.getX(), ori.getY()));
        arrow2.getElements().add(new LineTo(ori.getX()-0.2*tan.getX()+0.2*tan.getY(),
                ori.getY()-0.2*tan.getY()-0.2*tan.getX()));


        ori = eval(curve3,1);
        tan = evalDt(curve3,1).normalize().multiply(scale);
        Path arrow3 = new Path();
        arrow3.getElements().add(new MoveTo(ori.getX()-0.2*tan.getX()-0.2*tan.getY(),
                ori.getY()-0.2*tan.getY()+0.2*tan.getX()));
        arrow3.getElements().add(new LineTo(ori.getX(), ori.getY()));
        arrow3.getElements().add(new LineTo(ori.getX()-0.2*tan.getX()+0.2*tan.getY(),
                ori.getY()-0.2*tan.getY()-0.2*tan.getX()));

        ori = eval(curve4,1);
        tan = evalDt(curve4,1).normalize().multiply(scale);
        Path arrow4 = new Path();
        arrow4.getElements().add(new MoveTo(ori.getX()-0.2*tan.getX()-0.2*tan.getY(),
                ori.getY()-0.2*tan.getY()+0.2*tan.getX()));
        arrow4.getElements().add(new LineTo(ori.getX(), ori.getY()));
        arrow4.getElements().add(new LineTo(ori.getX()-0.2*tan.getX()+0.2*tan.getY(),
                ori.getY()-0.2*tan.getY()-0.2*tan.getX()));




        root.getChildren().addAll( st1,st2,st3,st4,curve1,curve2,curve3,curve4,curve5,arrow1,arrow2,arrow3,arrow4,arrowIni,t1,t2,t3,t4,t5);
        root.getChildren().add(btn);

        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();

    }

    private Point2D eval(CubicCurve c, float t){
        Point2D p=new Point2D(Math.pow(1-t,3)*c.getStartX()+
                3*t*Math.pow(1-t,2)*c.getControlX1()+
                3*(1-t)*t*t*c.getControlX2()+
                Math.pow(t, 3)*c.getEndX(),
                Math.pow(1-t,3)*c.getStartY()+
                        3*t*Math.pow(1-t, 2)*c.getControlY1()+
                        3*(1-t)*t*t*c.getControlY2()+
                        Math.pow(t, 3)*c.getEndY());
        return p;
    }
    private Point2D evalDt(CubicCurve c, float t){
        Point2D p=new Point2D(-3*Math.pow(1-t,2)*c.getStartX()+
                3*(Math.pow(1-t, 2)-2*t*(1-t))*c.getControlX1()+
                3*((1-t)*2*t-t*t)*c.getControlX2()+
                3*Math.pow(t, 2)*c.getEndX(),
                -3*Math.pow(1-t,2)*c.getStartY()+
                        3*(Math.pow(1-t, 2)-2*t*(1-t))*c.getControlY1()+
                        3*((1-t)*2*t-t*t)*c.getControlY2()+
                        3*Math.pow(t, 2)*c.getEndY());
        return p;
    }

    private  static  String getMaxStrAcceptedInNFA(String word, StringBuilder builder){

        ArrayList<String> founded=getStrsAcceptedInNFA(word,builder);
        String max_str="";


        if(founded.size()!=0)
            for (String str:founded   ) {
                if(max_str.length()<str.length())

                    max_str=str;
            }
        else
            builder.append("!!!! "+word+" doesnt accepted by NFA !!!\n");
//            System.out.println();
        builder.append("\n");
        if(!max_str.isEmpty())
            builder.append("max str is : "+max_str+"\n");
//            System.out.println();
        builder.append("\n");
        return max_str;

        //check the word trough NFA
    }

    private static ArrayList<String>  getStrsAcceptedInNFA(String word, StringBuilder builder) {

        ArrayList<String> total_accepted=new ArrayList<>();
        String accepted_str="";
        char current;
        String state;
        NFA nfa=NFA.getInstance();
        for(int i=0; i<word.length();i++){

            current=word.charAt(i);
            state=nfa.nextState(current);
            //System.out.println("next state = "+state);

            if(state!=null)
            {
                nfa.setCur_state(state);
                accepted_str+=current;

                if(state.equals("q3")){

                    //put last index " "
                    total_accepted.add(accepted_str);
//                    System.out.println();
//                    System.out.println("");
                    builder.append("accepted str is = "+accepted_str+"\n\n");
                }

            }
            else {
                accepted_str = "";
                nfa.setCur_state("q0");
            }
        }
        return  total_accepted;
    }
// a draggable anchor displayed around a point.
}
