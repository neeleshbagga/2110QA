package qa.app.model;

import java.io.*;
import java.net.URL;
import java.util.*;

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
	Topic topic;
	int level;
	String qText;
	String qAns;
	String qExplain;
	
	
	while (scanner.hasNextLine()) {
		String line = scanner.nextLine();
		splitArray = line.split("\t");

		type = splitArray[0];
		topic = new Topic(splitArray[1]);
		level = Integer.parseInt(splitArray[2]);
		qText = splitArray[3];
		qAns = splitArray[4];
		qExplain = splitArray[5];
		Question question = null;

		switch (type) {
		case "TF": {
			question = new TFQ(qText, topic, level, new Answer(qAns,qExplain)); 
			break;
		}
		case "MCQ": {
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
}
