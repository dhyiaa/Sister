/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sister;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

/**
 *
 * @author zhuxiaoyu
 */
public class QuizMenu extends javax.swing.JFrame {
    private MainMenu newMainMenu;
    private ArrayList<Question> questionData=new ArrayList();
    private int[] beginningSelection;
    private int currentIndex=0;
    private int total;
    private boolean countA=false,countB=false,countC=false,countD=false,countE=false;
    /**
     * Creates new form QuizMenu
     */
    public QuizMenu() {
        initComponents();
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                exitOption(windowEvent);
                
            }
            
        });
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
    
    public QuizMenu(MainMenu mainmenu,ArrayList<Question> questionData){
        this();
        this.newMainMenu=mainmenu;
        //this.questionData=questionData;
        for(int i=0;i<questionData.size();i++){
            this.questionData.add(questionData.get(i).cloneQuestion());
        }
        setContent(questionData.get(currentIndex));
        total=questionData.size();
        totalQuestion.setText("/"+total);
        beginningSelection=new int[total];
        storeBegnningSelections();
    }
    
    public void storeBegnningSelections(){
        for(int i=0;i<questionData.size();i++){
            beginningSelection[i]=questionData.get(i).getCurrentSelection();
        }
    }
    
    private boolean checkChanges(){
        boolean change=false;
        for(int i=0;i<questionData.size();i++){
            if(beginningSelection[i]!=questionData.get(i).getCurrentSelection()){
                change=true;
                break;
            }
        }
        return change;
    }
    
    private boolean checkAnyChange(){
        boolean change=false;
        for(int i=0;i<questionData.size();i++){
            if(questionData.get(i).getCurrentSelection()!=-1){
                change=true;
                break;
            }
        }
        return change;
    }
    
    private void exitOption(java.awt.event.WindowEvent windowEvent){
        storeAnswer(questionData.get(currentIndex));
        if(checkAnyChange()){
            if(checkChanges()){
                Object[] options = {"Yes","No","Cancel"};
                int exitComfirm=JOptionPane.showOptionDialog(null, 
                        "Do you want to save this quiz before exiting?", "Save Quiz?", 
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,null,options,options[0]);

                if ( exitComfirm== JOptionPane.NO_OPTION){
                    this.dispose();
                    newMainMenu.storeUnfinishQuiz(null);
                    newMainMenu.setVisible(true);
                }
                else if ( exitComfirm== JOptionPane.YES_OPTION){
                    storeBegnningSelections();
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
        if(selectionA.isSelected()){
            q.setCurrentSelection(0);
        }
        else if(selectionB.isSelected()){
            q.setCurrentSelection(1);
        }
        else if(selectionC.isSelected()){
            q.setCurrentSelection(2);
        }
        else if(selectionD.isSelected()){
            q.setCurrentSelection(3);
        }
        else if(selectionE.isSelected()){
            q.setCurrentSelection(4);
        }
        else{
            q.setCurrentSelection(-1);
        }
    }
    
    private void setContent(Question q){
        questionContent.setText(q.getContent());
        selectionA.setText(q.getSelections()[0]);
        selectionB.setText(q.getSelections()[1]);
        selectionC.setText(q.getSelections()[2]);
        selectionD.setText(q.getSelections()[3]);
        
        countA=false;
        countB=false;
        countC=false;
        countD=false;
        countE=false;
        
        if(q.getSelections().length>4){
            selectionE.setVisible(true);
            selectionE.setText(q.getSelections()[4]);
        }
        else{
            selectionE.setVisible(false);
        }
        int s=q.getCurrentSelection();
        
        if(s==0){
            selectionA.setSelected(true);
            countA=true;
        }
        else if(s==1){
            selectionB.setSelected(true);
             countB=true;
        }
        else if(s==2){
            selectionC.setSelected(true);
             countC=true;
        }
        else if(s==3){
            selectionD.setSelected(true);
             countD=true;
        }
        else if(s==4){
            selectionE.setSelected(true);
             countE=true;
        }
        else{
            selections.clearSelection();
        }
        questionIndexInput.setText(currentIndex+1+"");
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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        questionContent = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        selectionA = new javax.swing.JRadioButton();
        selectionB = new javax.swing.JRadioButton();
        selectionC = new javax.swing.JRadioButton();
        selectionD = new javax.swing.JRadioButton();
        selectionE = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        nextQuestion = new javax.swing.JButton();
        lastQuestion = new javax.swing.JButton();
        questionIndexInput = new javax.swing.JTextField();
        totalQuestion = new javax.swing.JLabel();
        submitAnswer = new javax.swing.JButton();
        saveAnswer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        questionContent.setEditable(false);
        questionContent.setColumns(20);
        questionContent.setRows(5);
        questionContent.setText("1.");
        questionContent.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        questionContent.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(questionContent);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        selections.add(selectionA);
        selectionA.setText("jRadioButton1");
        selectionA.setActionCommand("jRadioBu \ntton1");
        selectionA.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        selectionA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectionAActionPerformed(evt);
            }
        });

        selections.add(selectionB);
        selectionB.setText("jRadioButton2");
        selectionB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        selectionB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectionBActionPerformed(evt);
            }
        });

        selections.add(selectionC);
        selectionC.setText("jRadioButton3");
        selectionC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        selectionC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectionCActionPerformed(evt);
            }
        });

        selections.add(selectionD);
        selectionD.setText("jRadioButton4");
        selectionD.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        selectionD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectionDActionPerformed(evt);
            }
        });

        selections.add(selectionE);
        selectionE.setText("jRadioButton5");
        selectionE.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        selectionE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectionEActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(selectionA)
                    .addComponent(selectionB)
                    .addComponent(selectionC)
                    .addComponent(selectionD)
                    .addComponent(selectionE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(selectionA)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectionB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectionC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectionD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectionE))
        );

        nextQuestion.setText(">");
        nextQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextQuestionActionPerformed(evt);
            }
        });

        lastQuestion.setText("<");
        lastQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastQuestionActionPerformed(evt);
            }
        });

        questionIndexInput.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        questionIndexInput.setText("1");
        questionIndexInput.setPreferredSize(new java.awt.Dimension(18, 28));
        questionIndexInput.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                questionIndexInputMousePressed(evt);
            }
        });
        questionIndexInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                questionIndexInputKeyPressed(evt);
            }
        });

        totalQuestion.setText("/10");

        submitAnswer.setText("submit");
        submitAnswer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitAnswerActionPerformed(evt);
            }
        });

        saveAnswer.setText("save");
        saveAnswer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAnswerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lastQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(questionIndexInput, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(totalQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nextQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 223, Short.MAX_VALUE)
                .addComponent(saveAnswer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(submitAnswer))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lastQuestion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(questionIndexInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(nextQuestion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(totalQuestion)
                .addComponent(submitAnswer)
                .addComponent(saveAnswer))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nextQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextQuestionActionPerformed
        // TODO add your handling code here:
        storeAnswer(questionData.get(currentIndex));
        if(currentIndex==total-1){
            
        }
        else{
            currentIndex++;
            setContent(questionData.get(currentIndex));
        }
    }//GEN-LAST:event_nextQuestionActionPerformed

    private void lastQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastQuestionActionPerformed
        // TODO add your handling code here:
        storeAnswer(questionData.get(currentIndex));
        if(currentIndex==0){
            
        }
        else{
            currentIndex--;
            setContent(questionData.get(currentIndex));
        }
    }//GEN-LAST:event_lastQuestionActionPerformed

    private void questionIndexInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_questionIndexInputKeyPressed
        // TODO add your handling code here:
        storeAnswer(questionData.get(currentIndex));
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            try{
            int index=Integer.parseInt(questionIndexInput.getText())-1;
            if(index<0){
                index=0;
            }
            else if(index>=total){
                index=total-1;
            }
            currentIndex=index;
            setContent(questionData.get(currentIndex));
            }
            catch(NumberFormatException e){
                System.out.println("Error: "+e);
            }
            questionIndexInput.selectAll();
        }
    }//GEN-LAST:event_questionIndexInputKeyPressed

    private void questionIndexInputMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_questionIndexInputMousePressed
        // TODO add your handling code here:
        questionIndexInput.selectAll();
    }//GEN-LAST:event_questionIndexInputMousePressed

    private void selectionAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectionAActionPerformed
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
    }//GEN-LAST:event_selectionAActionPerformed

    private void selectionBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectionBActionPerformed
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
    }//GEN-LAST:event_selectionBActionPerformed

    private void selectionCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectionCActionPerformed
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
    }//GEN-LAST:event_selectionCActionPerformed

    private void selectionDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectionDActionPerformed
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
    }//GEN-LAST:event_selectionDActionPerformed

    private void selectionEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectionEActionPerformed
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
    }//GEN-LAST:event_selectionEActionPerformed

    private void saveAnswerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAnswerActionPerformed
        // TODO add your handling code here:
        storeAnswer(questionData.get(currentIndex));
        storeBegnningSelections();
    
    }//GEN-LAST:event_saveAnswerActionPerformed

    private void submitAnswerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitAnswerActionPerformed
        // TODO add your handling code here:
        String unfinishedQuestion="";
        boolean submit=true;
        
        storeAnswer(questionData.get(currentIndex));
        for(int i=0;i<questionData.size();i++){
            if(questionData.get(i).getCurrentSelection()<0){
                unfinishedQuestion+=(i+1)+", ";
            }
        }
        if(unfinishedQuestion.length()>0){
            Object[] options = {"Submit",
                    "Cancel"};
            int comfirm=JOptionPane.showOptionDialog(null, 
                    "Question "+unfinishedQuestion.substring(0, unfinishedQuestion.length()-2)
                            +" is left blank, do you still want to submit?", 
                    "Blank questions", 
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
            if(comfirm==JOptionPane.NO_OPTION){
                submit=false;
            }
        }
        if(submit){
            QuizSummarize summarize=new QuizSummarize(newMainMenu,questionData);
            setVisible(false);
            summarize.setVisible(true);
        }
    }//GEN-LAST:event_submitAnswerActionPerformed
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton lastQuestion;
    private javax.swing.JButton nextQuestion;
    private javax.swing.JTextArea questionContent;
    private javax.swing.JTextField questionIndexInput;
    private javax.swing.JButton saveAnswer;
    private javax.swing.JRadioButton selectionA;
    private javax.swing.JRadioButton selectionB;
    private javax.swing.JRadioButton selectionC;
    private javax.swing.JRadioButton selectionD;
    private javax.swing.JRadioButton selectionE;
    private javax.swing.ButtonGroup selections;
    private javax.swing.JButton submitAnswer;
    private javax.swing.JLabel totalQuestion;
    // End of variables declaration//GEN-END:variables
}
