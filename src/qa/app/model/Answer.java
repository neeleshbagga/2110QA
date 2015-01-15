package qa.app.model;
public class Answer{
	private String explanation;
	private String value; 
	public Answer(String value, String exp){
		this.value = value;
		this.explanation = exp;
	}
	//public abstract boolean checkAnswer(String a);
	
	public String getExplain(){
		return explanation;
	}
	
	public String getValue(){
		return value;
	}
}