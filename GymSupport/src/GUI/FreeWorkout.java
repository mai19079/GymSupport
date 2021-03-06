package GUI;


import gym.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonStreamParser;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vasilis
 */
public class FreeWorkout extends Workout {

    //GymSupportUI mainFrame;
    private ArrayList<WorkoutPlan> workoutList = new ArrayList();
    //private User currentUser;
   
    /**
     * Creates new form Workout
     */
    public FreeWorkout(GymSupportUI mainFrame, boolean modal) {
        super(mainFrame, modal);
        this.mainFrame = mainFrame;
        currentUser = mainFrame.getCurrentUser();
        initComponents();
        this.setLocationRelativeTo(mainFrame);
        loadWorkout("/datasource/MenBulk.json");
        loadWorkout("/datasource/MenLean.json");
        loadWorkout("/datasource/WomenBulk.json");
        loadWorkout("/datasource/WomenLean.json");
        for (WorkoutPlan wp : workoutList) {
            System.out.println(wp.displayWorkout());
        }
    }
    
    private void loadWorkout(String workoutFile) {
        try{
            InputStream is = getClass().getResourceAsStream(workoutFile);
            Reader r = new InputStreamReader(is, "UTF-8");
            Gson gson = new GsonBuilder().create();
            JsonStreamParser p = new JsonStreamParser(r);

            while (p.hasNext()) {
                JsonElement e = p.next();
                if (e.isJsonObject()) {
                    WorkoutPlan wp = gson.fromJson(e, WorkoutPlan.class);
                    wp.setCurrentUser(mainFrame.getCurrentUser());
                    workoutList.add(wp);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

  
    private class WorkoutPlan {
        
        private User currentUser;
        
        private String programid;
        private String name;
        private String caloriesperday;
        private String daysoftraining;
        private String trainer;
        private Map workout;
        
        public WorkoutPlan(String programid, String name, String caloriesperday, String daysoftraining, String trainer, Map workout){
            this.programid = programid;
            this.name = name;
            this.caloriesperday = caloriesperday;
            this.trainer = trainer;
            this.workout = workout;
        }
        public String getProgramid() {
            return programid;
        }
        public Map getWorkout(){
            return workout;
        }
        public void setCurrentUser(User u) {
            this.currentUser = u;
        }
        public User getCurrentUser(){
            return currentUser;
        }
        public String getCalories(){
            return caloriesperday;
        }
        public String getDaysoftraining(){
            return daysoftraining;
        }
    
        public String getTrainer() {
            return trainer;
        }
        
       
        
        public String displayWorkout() {
            String workout = "Workout: " + name;
            return workout;
        }
        
            public String info(){
            String s = this.displayWorkout();
            s += "\nCalories per Day: " + this.getCalories();
            s += "\nDays of Training: " + this.getDaysoftraining();
            s += "\n=================================================";
            s += "\n"+"Exercises :";
            Set<String> keySet = getWorkout().keySet();
            for (String key : keySet) {
                s += "\n" + key + ": " + getWorkout().get(key);
            }
            return s;
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        workoutType = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        workoutPlanText = new javax.swing.JTextArea();
        leanChoice = new javax.swing.JRadioButton();
        bulkChoice = new javax.swing.JRadioButton();
        closeBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Workout ");

        workoutPlanText.setColumns(20);
        workoutPlanText.setRows(5);
        jScrollPane1.setViewportView(workoutPlanText);

        workoutType.add(leanChoice);
        leanChoice.setText("Lean");
        leanChoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leanChoiceActionPerformed(evt);
            }
        });

        workoutType.add(bulkChoice);
        bulkChoice.setText("Bulk");
        bulkChoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bulkChoiceActionPerformed(evt);
            }
        });

        closeBtn.setText("Close");
        closeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(leanChoice)
                            .addComponent(bulkChoice)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addComponent(closeBtn)))
                .addContainerGap(183, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(leanChoice)
                .addGap(5, 5, 5)
                .addComponent(bulkChoice)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(closeBtn)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBtnActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_closeBtnActionPerformed

    private void leanChoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leanChoiceActionPerformed
        // TODO add your handling code here:
        if(mainFrame.getCurrentUser().getGender().equals("Male")){
            this.workoutPlanText.setText(workoutList.get(1).info());
        }
            else{
                this.workoutPlanText.setText(workoutList.get(3).info());
            }
    }//GEN-LAST:event_leanChoiceActionPerformed

    private void bulkChoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bulkChoiceActionPerformed
        // TODO add your handling code here:
        if(mainFrame.getCurrentUser().getGender().equals("Male")){
            this.workoutPlanText.setText(workoutList.get(0).info());
        }
        else{
            this.workoutPlanText.setText(workoutList.get(2).info());
        }
        
    }//GEN-LAST:event_bulkChoiceActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FreeWorkout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FreeWorkout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FreeWorkout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FreeWorkout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FreeWorkout dialog = new FreeWorkout((GymSupportUI) new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton bulkChoice;
    private javax.swing.JButton closeBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton leanChoice;
    private javax.swing.JTextArea workoutPlanText;
    private javax.swing.ButtonGroup workoutType;
    // End of variables declaration//GEN-END:variables
}
