//15030493g Cheung Wing Hin

package javaapplication2;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class JavaApplication2 {
TextField text;
private static Vector<Vector<String>> data;
private static Vector<String> header;

    public static void main(String[] args){

        // create JFrame and JTable
        final JFrame frame = new JFrame("Leave Application System");
        final JPanel myPanel = new JPanel(){
            @Override public Dimension getPreferredSize() { 
                return new Dimension(100, 100); 
            }
        };
        final JTable table = new JTable(); 
        JLabel lb1 = new JLabel();
        JLabel lb2 = new JLabel();
        JLabel lb3 = new JLabel();
        JLabel lb4 = new JLabel();
        JLabel lb5 = new JLabel();
        final JLabel lb7 = new JLabel();

        // create a table model and set a Column Identifiers to this model 
        Object[] columns = {"Staff Id","Staff Name","Supervisor","Apply Leave Date Start", "Apply Leave Date End", "Sick Leave Approval"};
        final DefaultTableModel model = new DefaultTableModel(data,header);    
        model.setColumnIdentifiers(columns);    
        // set the model to the table
        table.setModel(model);
        
        // Change A JTable Background Color, Font Size, Font Color, Row Height
        table.setBackground(Color.WHITE);
        table.setForeground(Color.black);
        Font font = new Font("",1,22);
        table.setFont(font);
        table.setRowHeight(30);
        
        // create JTextFields
        final JTextField textId = new JTextField();
        final JTextField textStaffname = new JTextField();
        final JTextField textSupervisor = new JTextField();
        final JTextField textApplyLeavefrom = new JTextField(10);
        final JTextField textApplyLeaveto = new JTextField(10);
        final JTextField textApproved = new JTextField();
        String[] staff = new String[] {};
        final JComboBox comboBox = new JComboBox(staff);
        
        // create JButtons
        final JButton btnAdd = new JButton("Add");
        JButton btnDelete = new JButton("Delete");
        JButton btnUpdate = new JButton("Update"); 
        JButton btnApplyLeave = new JButton("Apply for a leave"); 
        
        lb1.setText("Staff Id:");
        lb2.setText("Staff Name:");
        lb3.setText("Supervisor:");
        lb4.setText("Apply Leave Date From:");
        lb5.setText("Apply Leave Date To:");
        lb7.setText("Cannot empty");
        
        lb1.setBounds(20, 220, 100, 25);
        lb2.setBounds(20, 250, 100, 25);
        lb3.setBounds(20, 280, 100, 25);
        lb4.setBounds(20, 310, 100, 25);
        
    
        textId.setBounds(140, 220, 100, 25);
        textStaffname.setBounds(140, 250, 100, 25);
        comboBox.setBounds(140, 280, 100, 25);

        
        lb7.setBounds(500, 220, 100, 25);
        
        btnAdd.setBounds(300, 220, 100, 25);
        btnUpdate.setBounds(300, 265, 100, 25);
        btnDelete.setBounds(300, 310, 100, 25);
        btnApplyLeave.setBounds(450, 220, 190, 25);
        
        // create JScrollPane
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 880, 200);
        
        frame.setLayout(null);
        
        frame.add(pane);
        
        // add JTextFields to the jframe
        frame.add(lb1);
        frame.add(lb2);
        frame.add(lb3);
        myPanel.add(lb4);
        myPanel.add(textApplyLeavefrom);
        myPanel.add(lb5);
        myPanel.add(textApplyLeaveto);
        
        frame.add(textId);
        frame.add(textStaffname);
        frame.add(textSupervisor);
        
        frame.add(comboBox);
    
        // add JButtons to the jframe
        frame.add(btnAdd);
        frame.add(btnDelete);
        frame.add(btnUpdate);
        frame.add(btnApplyLeave);
        
        textApplyLeavefrom.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textApplyLeavefrom.setText(new DatePicker(frame).setPickedDate());
            }
        });
        textApplyLeaveto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textApplyLeaveto.setText(new DatePicker(frame).setPickedDate());
            }
        });
        
        // create an array of objects to set the row data
        final Object[] row = new Object[6];

        // button add row     
        btnAdd.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                row[0] = textId.getText();
                row[1] = textStaffname.getText();
                comboBox.addItem(textStaffname.getText());
                //comboBox.addItem(textId.getText());
                int checkrow = table.getRowCount();
                if(checkrow == 0){
                    row[2] = "Director";
                }
                if(checkrow > 0){
                    row[2] = comboBox.getSelectedItem().toString();
                }
                row[3] = "";
                row[4] = "";
                row[5] = "Nil";
  
                // add row to the model
                 if (textId.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Staff ID Cannot be empty", "Alert", JOptionPane.INFORMATION_MESSAGE);
                    //btnAdd.setEnabled(false);
                }
                 else{
                    model.addRow(row);
                }
            }
        });
        
        // button remove row
        btnDelete.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                // i = the index of the selected row
                int i = table.getSelectedRow();
                if(i >= 0){
                    // remove a row from jtable
                    model.removeRow(i);
                    comboBox.removeItemAt(i);
                }
                else{
                    System.out.println("Delete Error");
                }
            }
        });
        
        // get selected row data From table to textfields 
        table.addMouseListener(new MouseAdapter(){
        
        @Override
        public void mouseClicked(MouseEvent e){
            
            // i = the index of the selected row
            int i = table.getSelectedRow();
            textId.setText(model.getValueAt(i, 0).toString());
            textStaffname.setText(model.getValueAt(i, 1).toString());
            //this row later edit
            //textSupervisor.setText(model.getValueAt(i, 2).toString());
            textApplyLeavefrom.setText(model.getValueAt(i, 3).toString());
        }
        });
        
        // button update row
        btnUpdate.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // i = the index of the selected row
                int i = table.getSelectedRow();
                
                if(i >= 0) 
                {
                   model.setValueAt(textId.getText(), i, 0);
                   model.setValueAt(textStaffname.getText(), i, 1);
                   model.setValueAt(comboBox.getSelectedItem(), i, 2);
                   //model.setValueAt(textSupervisor.getText(), i, 2);
                   //model.setValueAt(textApplyLeave.getText(), i, 3);
                }
                else{
                    System.out.println("Update Error");
                }
            }
        });
        
        // button sick leave apply
        btnApplyLeave.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
             
                // i = the index of the selected row
                int i = table.getSelectedRow();
                
                if(i >= 0) 
                {
                  
                  // System.out.print(model.getValueAt(i, 2));
                   String message = "Set the sick leave date";
                   int result = JOptionPane.showConfirmDialog(null, myPanel, message, JOptionPane.OK_CANCEL_OPTION);
                   model.setValueAt(textApplyLeavefrom.getText(), i, 3);
                   textApplyLeavefrom.setText("");
                   model.setValueAt(textApplyLeaveto.getText(), i, 4);
                   textApplyLeaveto.setText("");
               
                   String test2 = model.getValueAt(i, 2).toString();
                   String reject="";
                   while(test2 != "Director"){
                       int count=table.getRowCount();
                       //System.out.print(count);
                       for(int a=0;a<count;a++){
                           String test = table.getValueAt(a,1).toString();
                           if(test.equals(test2)){
                               if (result==JOptionPane.YES_OPTION) {
                                    int result1=JOptionPane.showOptionDialog(frame,"Approved for sick leave?", ""+test2, JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE,null,new String []{"endorse","decline"}, "default");
                                        if (result1==JOptionPane.YES_OPTION){
                                            model.setValueAt("Approved", i, 5);
                                            test2 = table.getValueAt(a,2).toString();
                                        }else{
                                            model.setValueAt("Rejected", i, 5);
                                            test2 = "Director";
                                        }
                                //System.exit(0);
                                }
                               else{
                                   
                               }
                            }
                        }       
                   }

                }
                else{
                    System.out.println("Applied Error");
                }
            }
        });
        
        
        frame.setSize(900,400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
    }
}
