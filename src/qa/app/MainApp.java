package qa.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;







import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.sun.javafx.scene.control.skin.VirtualFlow.ArrayLinkedList;

import qa.app.model.QAUtils;
import qa.app.model.Topic;
import qa.app.view.QuestionOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import qa.app.model.Question;
import qa.app.model.Question;
public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    
    /**
     * The data as an observable list of topics.
     */
    private ObservableList<Topic> topics = FXCollections.observableArrayList();
    
    /**
     * The questions data
     */
    
    
    
    private ArrayList<Question> questions ;
   
    
    /**
     * Constructor
     * @throws FileNotFoundException 
     */
    public MainApp() throws FileNotFoundException {
        // Add some sample data
    	
    	try{
    	InputStream io = this.getClass().getClassLoader().getResourceAsStream("data/questionData.txt");
    	BufferedReader reader = new BufferedReader(new InputStreamReader(io));
    	questions = QAUtils.readQuestions(reader);
    	ArrayList<String> topicStr = new ArrayList<String>();
        for (Question q: questions){
        	if(!topicStr.contains(q.getTopic().getTopicName()))
        	{
        		topicStr.add(q.getTopic().getTopicName());
        		topics.add(q.getTopic());
        	}
        }
    	}
    	catch (FileNotFoundException e) {
    		e.printStackTrace();
    	}
    	
    	
    }

    /**
     * Returns the data as an observable list of Persons. 
     * @return
     */
    public ObservableList<Topic> getTopics() {
        return topics;
    }


    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("CS 2110 Q&A");

        initRootLayout();
        
        showQuestionOverview();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the question overview inside the root layout.
     */
    public void showQuestionOverview() {
        try {
        	FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/QuestionOverview.fxml"));
            AnchorPane QuestionOverview = (AnchorPane) loader.load();

            // Set quest. overview into the center of root layout.
            rootLayout.setCenter(QuestionOverview);
            
            // Give the controller access to the main app.
            QuestionOverviewController controller = loader.getController();
            controller.setMainApp(this);
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Return the questions
     * @return
     */
     public ArrayList<Question> getQuestions(){
    	 return this.questions;
     }
     
    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}