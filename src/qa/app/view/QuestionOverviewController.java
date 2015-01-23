package qa.app.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.controlsfx.dialog.Dialogs;

import qa.app.model.QAUtils;
import qa.app.model.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import qa.app.model.Topic;
import qa.app.MainApp;

public class QuestionOverviewController {
	@FXML
	private TableView<Topic> topicTable;
	@FXML
	private TableColumn<Topic, String> firstNameColumn;
	@FXML
	private TextArea textArea;
	@FXML
	private Button next;
	@FXML
	private Button submit;
	@FXML
	private Button explain;
	
	@FXML
	private TextField userSolCorrect;
	
	@FXML
	private TextArea explainArea;
	
	@FXML
	private ChoiceBox<String> choiceBox;
	
	@FXML
	private ProgressBar progressBar;
	 
	// Questions corresponding to currently selected topic
	private Iterator<Question> currentQuestions;
	
	
	private ArrayList<Question> allQuestions;
	
	private ObservableList<String> answerChoices;
	// Reference to the main application.
	private MainApp mainApp;
	
	// the current question that the user sees.
	private Question currQ;
	
	private int count = 0;
	
	
	private HashMap<String, Integer> topicLength = new HashMap<String, Integer>();

	/**
	 * The constructor. The constructor is called before the initialize()
	 * method.
	 */
	public QuestionOverviewController() {
		
	}
	
	
	/**
	 * Next button pressed
	 */
	@FXML
	public void nextPressed(){
		displayNextQuestion();
	}
	
	

	/**
	 * Submit button pressed
	 */
	@FXML
	public void submitPressed(){
		String selection = choiceBox.getSelectionModel().getSelectedItem();
		
		if(selection.equals(currQ.getAnswer().getValue()))
			userSolCorrect.setText("Your answer was CORRECT");
		else
			userSolCorrect.setText("Your answer was INCORRECT");
		
		userSolCorrect.setVisible(true);
	}
	
	/**
	 * Explain button pressed
	 */
	@FXML
	public void explainPressed(){
		explainArea.setText(currQ.getAnswer().getExplain());
		explainArea.setVisible(true);
	}
	
	
	/**
	 * Switch the display, transition to next question if one exists.
	 */
	private void displayNextQuestion(){
		
		
		userSolCorrect.setVisible(false);
		explainArea.setVisible(false);
		count ++;
		progressBar.setProgress((double)count/topicLength.get(currQ.getTopic().getTopicName()));
		
		if(currentQuestions.hasNext()){
			currQ = currentQuestions.next();
			textArea.setText(currQ.getText());
			updateAnswerChoices(currQ);
			
			
		}
		else
		{
			
			Dialogs.create()
            .title("Topic Completed")
            .masthead("No more questions")
            .message("Kudos. You have completed all the questions in this topic. Please select another topic to proceed")
            .showWarning();
		}
	}
	
	/**
	 * Fills all text fields to show details about the person.
	 * If the specified person is null, all text fields are cleared.
	 * 
	 * @param person the person or null
	 */
	private void showTopicQuestions(Topic topic) {
		count = 0;
		progressBar.setProgress(0);
		userSolCorrect.setVisible(false);
		explainArea.setVisible(false);
		
		currentQuestions = QAUtils.filterQuestionsByTopic(allQuestions, topic).iterator();
	    
	   
	    if(currentQuestions.hasNext()){
	    	currQ = currentQuestions.next();
	    	textArea.setText(currQ.getText());
	    	updateAnswerChoices(currQ);
	    }
	}
	
	private void updateAnswerChoices(Question currQ){
		answerChoices = currQ.getanswerChoices();
    	choiceBox.setItems(answerChoices);
	}
	
	
	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// Initialize the person table with the two columns.
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue()
				.firstNameProperty());
		
	    //  showTopicQuestions(null);

	    // Listen for selection changes and show the person details when changed.
	    topicTable.getSelectionModel().selectedItemProperty().addListener(
	            (observable, oldValue, newValue) -> showTopicQuestions(newValue));
	    
	    progressBar.setProgress(0);
	}

	
	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	
		// Add observable list data to the table
		topicTable.setItems(mainApp.getTopics());
		allQuestions = mainApp.getQuestions();
		topicLength = QAUtils.initTopicLengthMap(allQuestions);
		
	}
	
	
	
}