package qa.app.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a Topic.
 *
 * @author Neelesh Bagga
 */
public class Topic {

    private final StringProperty topicName;
   
    /**
     * Default constructor.
     */
    public Topic() {
        this(null);
    }

    /**
     * Constructor with some initial data.
     * 
     * @param topicName
     * 
     */
    public Topic(String topicName) {
        this.topicName = new SimpleStringProperty(topicName);
            }

    public String getTopicName() {
        return topicName.get();
    }

    public void setTopicName(String firstName) {
        this.topicName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return topicName;
    }
}
