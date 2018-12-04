/*
 * The JFrame Window that presents a Quiz concerning SDLC
 *
 * Malcolm Wang, Peter Zhu, Dheyaa AlNajafi
 * December 4, 2018
 * ISC4U Unit 6, Project Management Project
 */
package sister;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

/**
 *
 * @author zhuxiaoyu
 */
public class QuizMenu extends javax.swing.JFrame implements KeyListener{
    private MainMenu newMainMenu;
    //the main menu
    private ArrayList<Question> questionData=new ArrayList();
    //an ArrayList of Questions
    private int[] beginningSelection;
    //an Array to store the user's answers
    private int currentIndex=0;
    //the current question's index
    private int totalQuestionNum;
    //the total number of questions
    private boolean countA=false,countB=false,countC=false,countD=false,countE=false;
    //boolean values that indicate whether each selection from A to E is selected
    
    /**
     * Creates new form QuizMenu
     * default constructor
     */
    public QuizMenu() {
        initComponents();
        
        setFocusable(true);
        addKeyListener(this);
        selectionARadioButton.addKeyListener(this);
        selectionBRadioButton.addKeyListener(this);
        selectionCRadioButton.addKeyListener(this);
        selectionDRadioButton.addKeyListener(this);
        selectionERadioButton.addKeyListener(this);
        questionContentTextArea.addKeyListener(this);
        saveAnswerButton.addKeyListener(this);
        submitAnswerButton.addKeyListener(this);
        nextQuestionButton.addKeyListener(this);
        lastQuestionButton.addKeyListener(this);
        //add key listeners for the JFrame, selection radio buttons, question content text area, next/last/save/submit buttons
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                //listen for the window closing event
                exitOption(windowEvent);
            }
            
        });
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        //this window will not exit directly on close
    }
    
    /**
     * secondary constructor
     * @param mainmenu = the main menu
     * @param questionData = an ArrayList of Questions
     * each Question contains the question content, selection contents, user selection, and the correct answer
     */
    public QuizMenu(MainMenu mainmenu,ArrayList<Question> questionData){
        this();
        //link to the default constructor
        this.newMainMenu=mainmenu;
        //set mainMenu equal to the @param Mainmenu
        
        for(int i=0;i<questionData.size();i++){
            this.questionData.add(questionData.get(i).cloneQuestion());
        }
        //set questionData equal to the clone of @param questionData
        
        setContent(questionData.get(currentIndex));
        //call the setContent(Question q) method to present the currentIndex-th question
        totalQuestionNum=questionData.size();
        //set the total question number of questions
        totalQuestionLabel.setText("/"+totalQuestionNum);
        //set the text of the totalQuestion label
        beginningSelection=new int[totalQuestionNum];
        //initiate the begnningSelection array
        storeBegnningSelections();
        //call the storeBegnningSelections() method to store the current user selections
    }
    
    /**
     * store the current user selections in the begnningSelection
     */
    public void storeBegnningSelections(){
        for(int i=0;i<questionData.size();i++){
            beginningSelection[i]=questionData.get(i).getCurrentSelection();
        }
    }
    
    /**
     * check if any change has been made since starting the quiz
     * @return if any change has been made
     */
    private boolean checkChanges(){
        boolean change=false;
        //set the change value to false
        for(int i=0;i<questionData.size();i++){
            if(beginningSelection[i]!=questionData.get(i).getCurrentSelection()){
                //if the stored user selection is not equal to the current user selection
                change=true;
                //set change to true
                break;
            }
        }
        return change;
    }
    
    /**
     * check if any change has been made compared to a blank quiz
     * @return if any change has been made compared to a blank quiz
     */
    private boolean checkAnyChange(){
        boolean change=false;
        //set the change value to false
        for(int i=0;i<questionData.size();i++){
            if(questionData.get(i).getCurrentSelection()!=-1){
                //if the current user selection is not equal -1
                change=true;
                //set change to true
                break;
            }
        }
        return change;
    }
    
    /**
     * the action to perform when exit is clicked
     * @param windowEvent 
     */
    private void exitOption(java.awt.event.WindowEvent windowEvent){
        storeAnswer(questionData.get(currentIndex));
        //call the storeAnswer(Question q) metod to store the user selection of the current question
        if(checkAnyChange()){
            //call the checkAnyChange() method to check if the quiz is blank
            if(checkChanges()){
                //call the checkChanges() method to check if the quiz is changed comparing to the stored user selections
                Object[] options = {"Yes","No","Cancel"};
                //the option buttons for the confirm message will be "Yes", "No", and "Cancel"
                int exitComfirm=JOptionPane.showOptionDialog(null, 
                        "Do you want to save this quiz before exiting?", "Save Quiz?", 
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
                //present the confirming message

                if ( exitComfirm== JOptionPane.NO_OPTION){
                    //if the user choose "No"
                    this.dispose();
                    //dispose this quiz menu window
                    newMainMenu.storeUnfinishQuiz(null);
                    //clear the quiz menu stored in th main menu
                    newMainMenu.setVisible(true);
                    //return to the main menu
                }
                else if ( exitComfirm== JOptionPane.YES_OPTION){
                    //if the user choose "Yes"
                    storeBegnningSelections();
                    //store the 
                    this.setVisible(false);
                    newMainMenu.storeUnfinishQuiz(this);
                    newMainMenu.setVisible(true);
                }
            }
            else{
                Object[] options = {"Yes","Cancel"};
                int exitComfirm=JOptionPane.showOptionDialog(null, 
                        "Do you want to exit this quiz?", "Exit quiz?", 
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,null,options,options[0]);

                if ( exitComfirm== JOptionPane.YES_OPTION){
                    this.setVisible(false);
                    newMainMenu.storeUnfinishQuiz(this);
                    newMainMenu.setVisible(true);
                }
            }
        }
        else{
            this.dispose();
            newMainMenu.storeUnfinishQuiz(null);
            newMainMenu.setVisible(true);
        }
    }
    
    public void storeMainMenu(MainMenu menu){
        this.newMainMenu=menu;
    }
    
    private void storeAnswer(Question q){
        if(selectionARadioButton.isSelected()){
            q.setCurrentSelection(0);
        }
        else if(selectionBRadioButton.isSelected()){
            q.setCurrentSelection(1);
        }
        else if(selectionCRadioButton.isSelected()){
            q.setCurrentSelection(2);
        }
        else if(selectionDRadioButton.isSelected()){
            q.setCurrentSelection(3);
        }
        else if(selectionERadioButton.isSelected()){
            q.setCurrentSelection(4);
        }
        else{
            q.setCurrentSelection(-1);
        }
    }
    
    private void setContent(Question q){
        questionContentTextArea.setText(q.getContent());
        selectionARadioButton.setText(q.getSelections()[0]);
        selectionBRadioButton.setText(q.getSelections()[1]);
        selectionCRadioButton.setText(q.getSelections()[2]);
        selectionDRadioButton.setText(q.getSelections()[3]);
        
        countA=false;
        countB=false;
        countC=false;
        countD=false;
        countE=false;
        
        if(q.getSelections().length>4){
            selectionERadioButton.setVisible(true);
            selectionERadioButton.setText(q.getSelections()[4]);
        }
        else{
            selectionERadioButton.setVisible(false);
        }
        int s=q.getCurrentSelection();
        
        if(s==0){
            selectionARadioButton.setSelected(true);
            countA=true;
        }
        else if(s==1){
            selectionBRadioButton.setSelected(true);
             countB=true;
        }
        else if(s==2){
            selectionCRadioButton.setSelected(true);
             countC=true;
        }
        else if(s==3){
            selectionDRadioButton.setSelected(true);
             countD=true;
        }
        else if(s==4){
            selectionERadioButton.setSelected(true);
             countE=true;
        }
        else{
            selections.clearSelection();
        }
        questionIndexInputTextField.setText(currentIndex+1+"");
        pack();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        selections = new javax.swing.ButtonGroup();
        textareaPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        questionContentTextArea = new javax.swing.JTextArea();
        selectionRadioButtonPanel = new javax.swing.JPanel();
        selectionARadioButton = new javax.swing.JRadioButton();
        selectionBRadioButton = new javax.swing.JRadioButton();
        selectionCRadioButton = new javax.swing.JRadioButton();
        selectionDRadioButton = new javax.swing.JRadioButton();
        selectionERadioButton = new javax.swing.JRadioButton();
        buttonPanel = new javax.swing.JPanel();
        nextQuestionButton = new javax.swing.JButton();
        lastQuestionButton = new javax.swing.JButton();
        questionIndexInputTextField = new javax.swing.JTextField();
        totalQuestionLabel = new javax.swing.JLabel();
        submitAnswerButton = new javax.swing.JButton();
        saveAnswerButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        questionContentTextArea.setEditable(false);
        questionContentTextArea.setColumns(20);
        questionContentTextArea.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        questionContentTextArea.setLineWrap(true);
        questionContentTextArea.setRows(5);
        questionContentTextArea.setText("1.");
        questionContentTextArea.setWrapStyleWord(true);
        questionContentTextArea.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        questionContentTextArea.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(questionContentTextArea);

        javax.swing.GroupLayout textareaPanelLayout = new javax.swing.GroupLayout(textareaPanel);
        textareaPanel.setLayout(textareaPanelLayout);
        textareaPanelLayout.setHorizontalGroup(
            textareaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(textareaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        textareaPanelLayout.setVerticalGroup(
            textareaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(textareaPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        selections.add(selectionARadioButton);
        selectionARadioButton.setText("jRadioButton1");
        selectionARadioButton.setActionCommand("jRadioBu \ntton1");
        selectionARadioButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        selectionARadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectionARadioButtonActionPerformed(evt);
            }
        });

        selections.add(selectionBRadioButton);
        selectionBRadioButton.setText("jRadioButton2");
        selectionBRadioButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        selectionBRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectionBRadioButtonActionPerformed(evt);
            }
        });

        selections.add(selectionCRadioButton);
        selectionCRadioButton.setText("jRadioButton3");
        selectionCRadioButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        selectionCRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectionCRadioButtonActionPerformed(evt);
            }
        });

        selections.add(selectionDRadioButton);
        selectionDRadioButton.setText("jRadioButton4");
        selectionDRadioButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        selectionDRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectionDRadioButtonActionPerformed(evt);
            }
        });

        selections.add(selectionERadioButton);
        selectionERadioButton.setText("jRadioButton5");
        selectionERadioButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        selectionERadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectionERadioButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout selectionRadioButtonPanelLayout = new javax.swing.GroupLayout(selectionRadioButtonPanel);
        selectionRadioButtonPanel.setLayout(selectionRadioButtonPanelLayout);
        selectionRadioButtonPanelLayout.setHorizontalGroup(
            selectionRadioButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selectionRadioButtonPanelLayout.createSequentialGroup()
                .addGroup(selectionRadioButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(selectionARadioButton)
                    .addComponent(selectionBRadioButton)
                    .addComponent(selectionCRadioButton)
                    .addComponent(selectionDRadioButton)
                    .addComponent(selectionERadioButton))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        selectionRadioButtonPanelLayout.setVerticalGroup(
            selectionRadioButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selectionRadioButtonPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(selectionARadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectionBRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectionCRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectionDRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectionERadioButton))
        );

        nextQuestionButton.setText(">");
        nextQuestionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextQuestionButtonActionPerformed(evt);
            }
        });

        lastQuestionButton.setText("<");
        lastQuestionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastQuestionButtonActionPerformed(evt);
            }
        });

        questionIndexInputTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        questionIndexInputTextField.setText("1");
        questionIndexInputTextField.setPreferredSize(new java.awt.Dimension(18, 28));
        questionIndexInputTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                questionIndexInputTextFieldMousePressed(evt);
            }
        });
        questionIndexInputTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                questionIndexInputTextFieldKeyPressed(evt);
            }
        });

        totalQuestionLabel.setText("/10");

        submitAnswerButton.setText("submit");
        submitAnswerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitAnswerButtonActionPerformed(evt);
            }
        });

        saveAnswerButton.setText("save");
        saveAnswerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAnswerButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonPanelLayout = new javax.swing.GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonPanelLayout);
        buttonPanelLayout.setHorizontalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lastQuestionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(questionIndexInputTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(totalQuestionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nextQuestionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 223, Short.MAX_VALUE)
                .addComponent(saveAnswerButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(submitAnswerButton))
        );
        buttonPanelLayout.setVerticalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lastQuestionButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(questionIndexInputTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(nextQuestionButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(totalQuestionLabel)
                .addComponent(submitAnswerButton)
                .addComponent(saveAnswerButton))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(textareaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(selectionRadioButtonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(textareaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(selectionRadioButtonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nextQuestionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextQuestionButtonActionPerformed
        // TODO add your handling code here:
        storeAnswer(questionData.get(currentIndex));
        if(currentIndex==totalQuestionNum-1){
            
        }
        else{
            currentIndex++;
            setContent(questionData.get(currentIndex));
        }
    }//GEN-LAST:event_nextQuestionButtonActionPerformed

    private void lastQuestionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastQuestionButtonActionPerformed
        // TODO add your handling code here:
        storeAnswer(questionData.get(currentIndex));
        if(currentIndex==0){
            
        }
        else{
            currentIndex--;
            setContent(questionData.get(currentIndex));
        }
    }//GEN-LAST:event_lastQuestionButtonActionPerformed

    private void questionIndexInputTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_questionIndexInputTextFieldKeyPressed
        // TODO add your handling code here:
        storeAnswer(questionData.get(currentIndex));
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            try{
            int index=Integer.parseInt(questionIndexInputTextField.getText())-1;
            if(index<0){
                index=0;
            }
            else if(index>=totalQuestionNum){
                index=totalQuestionNum-1;
            }
            currentIndex=index;
            setContent(questionData.get(currentIndex));
            }
            catch(NumberFormatException e){
                System.out.println("Error: "+e);
            }
            questionIndexInputTextField.selectAll();
        }
    }//GEN-LAST:event_questionIndexInputTextFieldKeyPressed

    private void questionIndexInputTextFieldMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_questionIndexInputTextFieldMousePressed
        // TODO add your handling code here:
            questionIndexInputTextField.selectAll();
    }//GEN-LAST:event_questionIndexInputTextFieldMousePressed

    private void selectionARadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectionARadioButtonActionPerformed
        // TODO add your handling code here:
        if(countA){
            selections.clearSelection();
            countA=false;
        }
        else{
            countA=true;
        }
        countB=false;
        countC=false;
        countD=false;
        countE=false;
    }//GEN-LAST:event_selectionARadioButtonActionPerformed

    private void selectionBRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectionBRadioButtonActionPerformed
        // TODO add your handling code here:
        if(countB){
            selections.clearSelection();
            countB=false;
            
        }
        else{
            countB=true;
        }
        countA=false;
        countC=false;
        countD=false;
        countE=false;
    }//GEN-LAST:event_selectionBRadioButtonActionPerformed

    private void selectionCRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectionCRadioButtonActionPerformed
        // TODO add your handling code here:
        if(countC){
            selections.clearSelection();
            countC=false;
        }
        else{
            countC=true;
        }
        countA=false;
        countB=false;
        countD=false;
        countE=false;
    }//GEN-LAST:event_selectionCRadioButtonActionPerformed

    private void selectionDRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectionDRadioButtonActionPerformed
        // TODO add your handling code here:
        if(countD){
            selections.clearSelection();
            countD=false;
        }
        else{
            countD=true;
        }
        countA=false;
        countB=false;
        countC=false;
        countE=false;
    }//GEN-LAST:event_selectionDRadioButtonActionPerformed

    private void selectionERadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectionERadioButtonActionPerformed
        // TODO add your handling code here:
        if(countE){
            selections.clearSelection();
            countE=false;
        }
        else{
            countE=true;
        }
        countA=false;
        countB=false;
        countC=false;
        countD=false;
    }//GEN-LAST:event_selectionERadioButtonActionPerformed

    private void saveAnswerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAnswerButtonActionPerformed
        // TODO add your handling code here:
        storeAnswer(questionData.get(currentIndex));
        storeBegnningSelections();
    
    }//GEN-LAST:event_saveAnswerButtonActionPerformed

    private void submitAnswerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitAnswerButtonActionPerformed
        // TODO add your handling code here:
        String unfinishedQuestion="";
        boolean submit=true;
        
        storeAnswer(questionData.get(currentIndex));
        int countBlank=0;
        for(int i=0;i<questionData.size();i++){
            if(questionData.get(i).getCurrentSelection()<0){
                countBlank++;
                unfinishedQuestion+=(i+1)+", ";
            }
        }
        String confirmQuestion;
        if(countBlank==0){
            confirmQuestion=" Are you sure about submitting the quiz?";
        }
        else if(countBlank==1){
            confirmQuestion="Question "+unfinishedQuestion.substring(0, unfinishedQuestion.length()-2)
                        +" is left blank, do you still want to submit?";
        }
        else{
            confirmQuestion="Question "+unfinishedQuestion.substring(0, unfinishedQuestion.length()-2)
                        +" are left blank, do you still want to submit?";
        }
        Object[] options = {"Submit",
                "Cancel"};
        int comfirm=JOptionPane.showOptionDialog(null, 
                confirmQuestion, 
                "Do you want to submit?", 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
        
        if(comfirm==JOptionPane.YES_OPTION){
            QuizSummarize summarize=new QuizSummarize(newMainMenu,questionData);
            dispose();
            summarize.setVisible(true);
        }
    }//GEN-LAST:event_submitAnswerButtonActionPerformed
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JPanel buttonPanel;
    javax.swing.JScrollPane jScrollPane1;
    javax.swing.JButton lastQuestionButton;
    javax.swing.JButton nextQuestionButton;
    javax.swing.JTextArea questionContentTextArea;
    javax.swing.JTextField questionIndexInputTextField;
    javax.swing.JButton saveAnswerButton;
    javax.swing.JRadioButton selectionARadioButton;
    javax.swing.JRadioButton selectionBRadioButton;
    javax.swing.JRadioButton selectionCRadioButton;
    javax.swing.JRadioButton selectionDRadioButton;
    javax.swing.JRadioButton selectionERadioButton;
    javax.swing.JPanel selectionRadioButtonPanel;
    javax.swing.ButtonGroup selections;
    javax.swing.JButton submitAnswerButton;
    javax.swing.JPanel textareaPanel;
    javax.swing.JLabel totalQuestionLabel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            nextQuestionButton.doClick();
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            lastQuestionButton.doClick();
        }
        else if(e.getKeyChar()=='1'||e.getKeyChar()=='a'||e.getKeyChar()=='A'){
            selectionARadioButton.doClick();
        }
        else if(e.getKeyChar()=='2'||e.getKeyChar()=='b'||e.getKeyChar()=='B'){
            selectionBRadioButton.doClick();
        }
        else if(e.getKeyChar()=='3'||e.getKeyChar()=='c'||e.getKeyChar()=='C'){
            selectionCRadioButton.doClick();
        }
        else if(e.getKeyChar()=='4'||e.getKeyChar()=='d'||e.getKeyChar()=='D'){
            selectionDRadioButton.doClick();
        }
        else if(e.getKeyChar()=='5'||e.getKeyChar()=='e'||e.getKeyChar()=='E'){
            if(questionData.get(currentIndex).getSelections().length>4){
                selectionERadioButton.doClick();
            }
        }
        else if(e.getKeyCode() == KeyEvent.VK_ENTER){
            submitAnswerButton.doClick();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
    @Override
    public void setFocusable(boolean b) {
        super.setFocusable(b);
    }
}
