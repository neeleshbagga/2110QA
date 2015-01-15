package qa.app.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import qa.app.model.Question.Type;

public class TFQ extends Question {
	private Answer answer;
	private Type type; 
	private ObservableList<String> answerChoices;
	
	
	
	public TFQ(String q, Topic topic, int lvl, Answer a) {
		super(q,topic, lvl);
		this.type = Type.TF;
		this.answer = a;
		this.answerChoices = FXCollections.observableArrayList();
		this.answerChoices.add("True");
		this.answerChoices.add("False");
	}

	
	public Answer getAnswer() {
		return answer;
	}
	
	public ObservableList<String> getanswerChoices(){
		return this.answerChoices;
	}
}