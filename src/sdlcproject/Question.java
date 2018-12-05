/*
 * The JFrame Window that gives a quiz summarize
 *
 * Malcolm Wang, Peter Zhu, Dheyaa AlNajafi
 * December 4, 2018
 * ISC4U Unit 6, Project Management Project
 */
package sdlcproject;
import java.util.Arrays;
import java.util.Objects;

public class Question {

    private String content;
    //content of the Question
    private String[] selections;
    //selection content of the Question
    private int currentSelection;
    //the user's selection
    private int correctSelection;
    //the correct selection
    
    /**
     * default constructor
     * @param content = Question content
     * @param selections= Question selections' content
     * @param correctSelection = correct selection
     */
    public Question(String content, String[] selections, int correctSelection) {
        this.content = content;
        this.selections = selections;
        this.correctSelection = correctSelection;
        currentSelection=-1;
    }
    
    /**
     * secondary constructor
     * @param content = Question content
     * @param selections= Question selections' content
     * @param currentSelection = user seletion
     * @param correctSelection = correct selection
     */
    public Question(String content, String[] selections, int currentSelection, int correctSelection) {
        this(content,selections,currentSelection);
        this.correctSelection = correctSelection;
    }
    
    /**
     * get the question content
     * @return = a String containing the question content
     */
    public String getContent() {
        return content;
    }
    
    /**
     * set the question content
     * @param content = a String containing the question content
     */
    public void setContent(String content) {
        this.content = content;
    }
    
    /**
     * check if the Question is answered correctly
     * @return true if currentSelection = correctSelection, false if currentSelection != correctSelection
     */
    public boolean isCorrect(){
        return this.currentSelection == this.correctSelection;
    }
    
    /**
     * get the question selections' content
     * @return = a String array containing the question selections' content
     */
    public String[] getSelections() {
        return selections;
    }
    
    /**
     * get the user selection
     * @return = a String containing the user selection
     */
    public int getCurrentSelection() {
        return currentSelection;
    }
    
    /**
     * get the correct selection
     * @return = a String containing the correct selection
     */
    public int getCorrectSelection() {
        return correctSelection;
    }
    
    /**
     * set the question selections' content
     * @param selections = a String array containing the question selections' content
     */
    public void setSelections(String[] selections) {
        this.selections = selections;
    }
    
    /**
     * set the user selection
     * @param currentSelection  = a String containing the user selection
     */
    public void setCurrentSelection(int currentSelection) {
        this.currentSelection = currentSelection;
    }
    
    /**
     * set the correct selection
     * @param correctSelection  = a String containing the user correct
     */
    public void setCorrectSelection(int correctSelection) {
        this.correctSelection = correctSelection;
    }
    
    /**
     * Builds a String representation of the object for debugging/testing purposes
     * @return the String containing the state of the object
     */
    @Override
    public String toString() {
        return "Question{" + "content=" + content + ", selections=" + selections + ", currentSelection=" + currentSelection + ", correctSelection=" + correctSelection + '}';
    }

    /**
     * create a hashCode of the object for debugging/testing purposes
     * @return the hash code
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Arrays.deepHashCode(this.selections);
        hash = 71 * hash + this.currentSelection;
        hash = 71 * hash + this.correctSelection;
        return hash;
    }
    
    /**
     * compare this Question with an object
     * @param obj = the comparing object object
     * @return true if this Question equals to the object, false if not
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            //if obj is the same with this Question
            return true;
        }
        if (obj == null) {
            //if the obj is null
            return false;
        }
        if (getClass() != obj.getClass()) {
            //if the obj is not of class Question
            return false;
        }
        final Question other = (Question) obj;
        if (this.currentSelection != other.currentSelection) {
            //if the user selections are different
            return false;
        }
        if (this.correctSelection != other.correctSelection) {
            //if the correct selections are different
            return false;
        }
        if (!Objects.equals(this.content, other.content)) {
            //if the question contents are different
            return false;
        }
        if (!Arrays.deepEquals(this.selections, other.selections)) {
            //if the question selections' contents are different
            return false;
        }
        return true;
    }
    
    /**
     * clone the Question
     * @return  a clone of the Question
     */
    public Question cloneQuestion(){
        int current=currentSelection;
        return new Question(content, selections, current, correctSelection);
    }
    
    /**
     * clone the Object
     * @return a clone of the Object
     * @throws CloneNotSupportedException 
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
