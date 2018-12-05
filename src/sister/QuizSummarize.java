/*
 * The JFrame Window that gives a quiz summarize
 *
 * Malcolm Wang, Peter Zhu, Dheyaa AlNajafi
 * December 4, 2018
 * ISC4U Unit 6, Project Management Project
 */
package sister;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class QuizSummarize extends javax.swing.JFrame {

    private MainMenu mainMenu;
    //the main menu
    private ArrayList<Question> questionData = new ArrayList();
    //an ArrayList of Questions
    private static String summarize = "";
    //the output summarize String

    /**
     * Creates new form QuizSummarize default constructor
     */
    public QuizSummarize() {
        initComponents();
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                //listen for the window closing event
                exitOption(windowEvent);
            }

        });
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        //this window will not exit directly on close
        this.setTitle("Quiz Result"); // set the title of window
    }

    /**
     * Creates new form QuizSummarize secondary constructor
     *
     * @param mainMenu = the main menu
     * @param questionData = an ArrayList of Questions each Question contains
     * the question content, selection contents, user selection, and the correct
     * answer
     */
    public QuizSummarize(MainMenu mainMenu, ArrayList<Question> questionData) {
        this();
        //link to the default constructor
        this.mainMenu = mainMenu;
        //set mainMenu equal to the @param Mainmenu
        this.questionData = questionData;
        //set questionData equal to the @param questionData

        giveSummarize();
        //call the giveSummarize() method
    }

    /**
     * generate the quiz summarize String
     */
    private void giveSummarize() {
        int userScore = questionData.size();
        //set the userScore to full mark

        for (int i = 0; i < questionData.size(); i++) {
            //search through the ArrayList questionData, and inspect each Question
            if (questionData.get(i).getCurrentSelection() != questionData.get(i).getCorrectSelection()) {
                //if the user's answer is not correct
                summarize += "<pre><b>WRONG: </b>";
                //add a bold "WRONG: " before the question content
                userScore--;
                //subtract the userScore by 1
            } else {
                summarize += "<pre><b>CORRECT: </b>";
                //add a bold "CORRECT: " before the question content
            }
            summarize += questionData.get(i).getContent();
            //add the question content to the summarize output

            for (int j = 0; j < questionData.get(i).getSelections().length; j++) {
                //summarize+="<li>";
                summarize += "<pre>   ";
                //add a tab before each selection

                if (j == questionData.get(i).getCurrentSelection() && j == questionData.get(i).getCorrectSelection()) {
                    //if the selection is the user's answer, and is also the correct answer
                    summarize += "<b><font size=+0.5><u>" + questionData.get(i).getSelections()[j] + "(Correct Answer)</u></font></b>";
                    //add bold, underlined, and enlarged selection content to the summarize output
                } else if (j == questionData.get(i).getCurrentSelection()) {
                    //if the selection is the user's answer
                    summarize += "<b>" + questionData.get(i).getSelections()[j] + "</b>";
                    //add bold selection content to the summarize output
                } else if (j == questionData.get(i).getCorrectSelection()) {
                    //if the selection is the correct answer
                    summarize += "<u>" + questionData.get(i).getSelections()[j] + "(Correct Answer)</u>";
                    //add underlined selection content to the summarize output
                } else {
                    ////if the selection is neither the user's answer, nor the correct answer
                    summarize += questionData.get(i).getSelections()[j];
                    //add selection content to the summarize output
                }
                //summarize+="</li>";
                summarize += "<br/>";
                //each selection begins at a new line
            }
            summarize += "<br/>";
            //add another line after each question
        }
        scoreLabel.setText(userScore + "/" + questionData.size());
        //set the scoreLabel's text to the form of (user's score/full score)
        summarizeEditorPane.setText(summarize);
        //output the quiz summarize
    }

    /**
     * the action to perform when exit is clicked
     *
     * @param windowEvent
     */
    private void exitOption(java.awt.event.WindowEvent windowEvent) {

        Object[] options = {"Yes", "Cancel"};
        //the option buttons for the confirm message will be "Yes", and "Cancel"

        int comfirm = JOptionPane.showOptionDialog(null,
                "Do you want to return to the main menu?",
                "Do you want to submit?",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        //present the confirm message

        if (comfirm == JOptionPane.YES_OPTION) {
            //if the user choose "Yes"

            dispose();
            //dispose this quiz summarize window
            mainMenu.setVisible(true);
            //return to the main menu
        }
        //do nothing if the user choose "Cancel"
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleLabel = new javax.swing.JLabel();
        scoreLabel = new javax.swing.JLabel();
        summarizeScrollPane = new javax.swing.JScrollPane();
        summarizeEditorPane = new javax.swing.JEditorPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        titleLabel.setFont(new java.awt.Font("Lucida Bright", 0, 24)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        titleLabel.setText("Quiz Summarize:");

        scoreLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        scoreLabel.setText("-/-");

        summarizeEditorPane.setEditable(false);
        summarizeEditorPane.setContentType("text/html"); // NOI18N
        summarizeEditorPane.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        summarizeEditorPane.setSize(new java.awt.Dimension(560, 16));
        summarizeScrollPane.setViewportView(summarizeEditorPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(scoreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(summarizeScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 860, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleLabel)
                    .addComponent(scoreLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(summarizeScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel scoreLabel;
    private javax.swing.JEditorPane summarizeEditorPane;
    private javax.swing.JScrollPane summarizeScrollPane;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
