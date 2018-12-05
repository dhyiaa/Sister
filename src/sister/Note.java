/*
 * The Note class
 *
 * Malcolm Wang, Peter Zhu, Dheyaa AlNajafi
 * December 4, 2018
 * ISC4U Unit 6, Project Management Project
 */
package sister;

import java.util.Objects;

public class Note {

    private String value;
    //content of the Note
    private String title;
    //title of the Note

    /**
     * constructor
     *
     * @param value = content of the note
     * @param title = title of the note
     */
    public Note(String value, String title) {
        this.value = value;
        this.title = title;
    }

    /**
     * get the content
     *
     * @return a String containing the content
     */
    public String getValue() {
        return value;
    }

    /**
     * get the title
     *
     * @return a String containing the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * set the content
     *
     * @param value = String of the content
     */
    public void setValue(String value) {

        this.value = value;

    }

    /**
     * set the title
     *
     * @param title = String of the title
     */
    public void setTitle(String title) {

        this.title = title;

    }

    /**
     * create a hashCode of the object for debugging/testing purposes
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.value);
        hash = 89 * hash + Objects.hashCode(this.title);
        return hash;
    }

    /**
     * Builds a String representation of the object for debugging/testing
     * purposes
     *
     * @return the String containing the state of the object
     */
    @Override
    public String toString() {
        return "Note{" + "value=" + value + ", title=" + title + '}';
    }

    /**
     * clone the Object
     *
     * @return a clone of the Object
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * compare this Note with an object
     *
     * @param obj = the comparing object object
     * @return true if this Note equals to the object, false if not
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            //if obj is the same with this Note
            return true;
        }
        if (obj == null) {
            //if the obj is null
            return false;
        }
        if (getClass() != obj.getClass()) {
            //if the obj is not of class Note
            return false;
        }
        final Note other = (Note) obj;
        if (!Objects.equals(this.value, other.value)) {
            //if the values are different
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            //if the titles are different
            return false;
        }
        return true;
    }

}
