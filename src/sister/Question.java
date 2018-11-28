package sister;
import java.util.Arrays;

/**
 *
 * @author dhiaa
 */
public class Question {

    private String[] selections;
    private int currentSelection;
    private int correctSelection;

    public Question(String[] selections, int currentSelection, int correctSelection) {
        this.selections = selections;
        this.currentSelection = currentSelection;
        this.correctSelection = correctSelection;
    }
    public boolean isCorrect(){
        return this.currentSelection == this.correctSelection;
    }
    public String[] getSelections() {
        return selections;
    }

    public int getCurrentSelection() {
        return currentSelection;
    }

    public int getCorrectSelection() {
        return correctSelection;
    }

    public void setSelections(String[] selections) {
        this.selections = selections;
    }

    public void setCurrentSelection(int currentSelection) {
        this.currentSelection = currentSelection;
    }

    public void setCorrectSelection(int correctSelection) {
        this.correctSelection = correctSelection;
    }

    @Override
    public String toString() {
        return "Question{" + "selections=" + selections + ", currentSelection=" + currentSelection + ", correctSelection=" + correctSelection + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Arrays.deepHashCode(this.selections);
        hash = 71 * hash + this.currentSelection;
        hash = 71 * hash + this.correctSelection;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Question other = (Question) obj;
        if (this.currentSelection != other.currentSelection) {
            return false;
        }
        if (this.correctSelection != other.correctSelection) {
            return false;
        }
        if (!Arrays.deepEquals(this.selections, other.selections)) {
            return false;
        }
        return true;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
