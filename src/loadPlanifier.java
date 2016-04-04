
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import sun.misc.IOUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author horac
 */
public class loadPlanifier extends javax.swing.JFrame {

    /**
     * Creates new form loadPlanifier
     */
    public loadPlanifier() throws FileNotFoundException, IOException {
        initComponents();
        
        int aulaNum = 1;
        
        classAsignation assignation = new classAsignation();
        
        TreeModel model = assignation.jt_assignments.getModel();
        DefaultMutableTreeNode root =(DefaultMutableTreeNode) model.getRoot();
       
        
        ArrayList <String> teachers = new ArrayList();
        ArrayList <String> classes = new ArrayList();
        //ArrayList <String> students = new ArrayList();
        
        ArrayList <Hour> hours = new ArrayList();
        ArrayList <Clase> clases = new ArrayList();
        ArrayList <String> students = new ArrayList();
        
        String []teachersParse;
        String []classesParse;
        String []studentsParse;
        
        DefaultTableModel teachersModel = (DefaultTableModel) jt_teachersTable.getModel();
        //DefaultTableModel classesModel = (DefaultTableModel) jt_classesTable.getModel();
        DefaultTableModel studentsModel = (DefaultTableModel) jt_studentsTable.getModel();
        
        //******************************TEACHERS PARSE********************************************//
        
        try (BufferedReader br = new BufferedReader(new FileReader("teachers.txt"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            teachersParse = line.split(",");
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = sb.toString();
            String [] teachersParseLine = everything.split("\n");
            for (int i = 0; i < teachersParseLine.length; i++) {
                teachersParse = teachersParseLine[i].split(",");
                //System.out.println("found line");
                teachersModel.addRow(new Object[]{teachersParse[1], teachersParse[0], teachersParse[2]});
            }
            jt_teachersTable.setModel(teachersModel);
            for (int i = 0; i < teachersParseLine.length; i++) {
                String [] parsedClass = teachersParseLine[i].split(",");
                System.out.println("--------");        
                DefaultMutableTreeNode possibleHourNode = new DefaultMutableTreeNode(parsedClass[2]);
                DefaultMutableTreeNode possibleTeacherNode = new DefaultMutableTreeNode(parsedClass[0]);
                DefaultMutableTreeNode possibleClassNode = new DefaultMutableTreeNode(parsedClass[1]+", Aula Num: "+aulaNum);
                aulaNum++;
                possibleHourNode.insert(possibleTeacherNode, 0);
                possibleTeacherNode.insert(possibleClassNode, 0);
                root.insert(possibleHourNode,0);
                assignation.jt_assignments.setModel(model);
                
                //System.out.println(teachersParseLine[i]);
                
            }
            assignation.show();   
            
            String[] generalTeacherLines = everything.split("\n");
            for (int i = 0; i < generalTeacherLines.length; i++) {
                String[] actualTeachersParse = generalTeacherLines[i].split(",");
                Clase claseTemp = new Clase(actualTeachersParse[0], actualTeachersParse[2], actualTeachersParse[1],aulaNum);
                aulaNum++;
                System.out.println("aula esta en: "+aulaNum);
                hours.add(new Hour(actualTeachersParse[2], claseTemp));
            }
            System.out.println("Numero de clases en el sistema: " + hours.size());

        }
       
        
        ////////////////////////////////////////////////////////////////////////////////////////////
        
        try (BufferedReader br = new BufferedReader(new FileReader("students.txt"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            studentsParse = line.split(",");
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = sb.toString();
            String [] studentsParseLine = everything.split("\n");
            for (int i = 0; i < studentsParseLine.length; i++) {
                studentsParse = studentsParseLine[i].split(",");
                //System.out.println("found line");
                studentsModel.addRow(new Object[]{studentsParse[0], studentsParse[1]});
                
            }
            
            for (int i = 0; i < studentsParseLine.length; i++) {
                System.out.println(studentsParseLine[i]);
                String [] studentsParseList = studentsParseLine[i].split(",");
                for (int j = 0; j < hours.size(); j++) {
                    if((studentsParseList[1].equals(hours.get(j).getClase().getNombreClase()))&&(studentsParseList[2].equals(hours.get(j).getClase().getHora()))){
                        hours.get(j).getClase().matricularAlumno(studentsParseList[0]);
                        System.out.println("Se matricula "+studentsParseList[0]+" en la clase "+hours.get(j).getClase().getNombreClase());
                    }
                }
            }
            
            jt_studentsTable.setModel(studentsModel);
            System.out.println("********************IMPRESION FINAL*****************");
            for (int i = 0; i < hours.size(); i++) {
                System.out.println("Hora: "+hours.get(i).getClase().getHora());
                System.out.println("Clase: "+hours.get(i).getClase().getNombreClase());
                System.out.println("Docente: "+hours.get(i).getClase().getMaestro());
                System.out.println("Alumnos: "+hours.get(i).getClase().getAlumnos());
                System.out.println("-------");
            }
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jt_teachersTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jt_studentsTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jt_teachersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Class", "Teachers", "Shedule"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jt_teachersTable);

        jt_studentsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student", "Class"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jt_studentsTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addGap(468, 468, 468))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(loadPlanifier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(loadPlanifier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(loadPlanifier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(loadPlanifier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new loadPlanifier().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(loadPlanifier.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jt_studentsTable;
    private javax.swing.JTable jt_teachersTable;
    // End of variables declaration//GEN-END:variables

}
