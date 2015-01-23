package qa.app.model;

import java.io.*;
import java.net.URL;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class QAUtils {

	/**
	 * Parse the questions file.
	 * 
	 * @param filename
	 * @throws FileNotFoundException
	 */
	public static ArrayList<Question> readQuestions(BufferedReader reader)
			throws FileNotFoundException {
		
		ArrayList<Question> questions = new ArrayList<Question>();	
	//Scanner scanner = new Scanner(new FileReader(file));
	Scanner scanner = new Scanner(reader);
	String[] splitArray = null;

	String type;
	String topic;
	int level;
	String qText;
	String qAns;
	String qExplain;
	
	
	while (scanner.hasNextLine()) {
		String line = scanner.nextLine();
		if(line.length() == 0)
			continue;
		splitArray = line.split("\t");

		type = splitArray[0].trim();
		
		topic = splitArray[1].trim();
		level = Integer.parseInt(splitArray[2].trim());
		qText = splitArray[3].trim();
		qAns = splitArray[4].trim();
		qExplain = splitArray[5].trim();
		Question question = null;

		switch (type) {
		case "TF": {
			question = new TFQ(qText, topic, level, new Answer(qAns,qExplain)); 
			break;
		}
		case "MCQ": {
			
			String choices = splitArray[6];
			ObservableList<String> choice = FXCollections.observableArrayList(choices.split(" "));
			
			question = new MCQ(qText, topic, level, new Answer(qAns,qExplain), choice); 
			break;
			
		}
		default: // FR

		}
		
		questions.add(question);
	}
		scanner.close();
		
	return questions;
	
	}
	
	/**
	 * Return the questions corresponding to the given topic
	 */
	public static ArrayList<Question> filterQuestionsByTopic(ArrayList<Question> questions, Topic topic){
		ArrayList<Question> filtered = new ArrayList<Question>();
		for(Question q: questions){
			if(q.getTopic().getTopicName().equals(topic.getTopicName()))
					filtered.add(q);
		}
		
		return filtered;
	}
	
	public static HashMap<String, Integer> initTopicLengthMap(ArrayList<Question> questions){
		
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		for(Question q: questions){
			String topicName = q.getTopic().getTopicName();
			if(!result.containsKey(topicName))
				result.put(topicName, 1);
			else
				result.put(topicName, result.get(topicName)+1);
			
		}
		
		return result;
	}
}
