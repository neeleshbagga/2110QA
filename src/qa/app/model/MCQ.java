package qa.app.model;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import qa.app.model.Question.Type;

public class MCQ extends Question{
	private Answer answer;
	private Type type; 
	private ObservableList<String> answerChoices =  FXCollections.observableArrayList();
	
	
	public MCQ(String q, String topic, int lvl, Answer a, ObservableList<String> choices) {
		super(q, topic, lvl);
	
		this.type = Type.TF;
		this.answer = a;
		this.answerChoices = choices;
	}


	@Override
	public Answer getAnswer() {
		// TODO Auto-generated method stub
		return answer;
	}


	@Override
	public ObservableList<String> getanswerChoices() {
		// TODO Auto-generated method stub
		return answerChoices;
	}
	
	

}
