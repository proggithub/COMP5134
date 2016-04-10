Cheung Wing Hin 15030493g
In order to read my source code, I have already add suitable comments before each of the method.
Here are the following things that I have created,
1.create JFrame and JTable
2.create a table model and set a Column Identifiers to this model
3.create JTextFields
4.create JButtons and set bounds
5.create JScrollPane
6.add JTextFields,JButtons to the jframe
7.addMouseListener of textApplyLeavefrom, textApplyLeaveto and table
8.create an array of objects to set the row data
9.button add row,button remove row, button update row,button sick leave apply


First, when you started the program, you will see a panel. The upper part is the information display and the lower part is the item you can insert.

Second, you will see Staff id, Staff name, Supervisor field. As the fisrt one is default as director so no supervisor is needed. Then, you can set 
the supervisor from the jcombobox. As any staff can supervise multiple staff but each staff has only one supervisor.

Third, after select and insert all the things, press the "Add" button to insert the data. If all the field is empty, then a alert box will pop up to 
remind you. If you want to change anythings, you can resert again and then press "Update" button. You can also select a row to delete.

Forth, if any staff want to take a sick leave, then he/she need to click the "Apply for a leave" button and select the date from x to y by using 
a datepicker. After that, his/her supervisor will pop up a notification to accept or decline the sick leave application. If any one of the supervisor 
is rejected, then sick leave is not approved. Otherwise, it will past to next supervisor.