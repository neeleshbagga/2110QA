package qa.app.model;

import javafx.collections.ObservableList;


public abstract class Question implements Comparable {
	
	
	
	private String text;
	private int difficulty;
	private Topic topic;
	

	public enum Type {
				MCQ, TF, FR; 
			}
	
	
	public Question(String q, Topic topic, int lvl) {
		text = q;
		difficulty = lvl;
		this.topic = topic;
	}

	public abstract Answer getAnswer();
	public abstract ObservableList<String> getanswerChoices();

	public int getDifficulty() {
		return difficulty;
	}
	
	public Topic getTopic(){
		return this.topic;
	}
	
	public String getText(){
		return this.text;
	}
	public @Override int compareTo(Object q) {
		return (this.difficulty-((Question)q).getDifficulty());
	}
}