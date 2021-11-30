import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent; 
import javax.swing.event.ListSelectionListener; 
import java.lang.Integer;

public class MemoryByteViewAndController extends JPanel implements SimpleObserver
{

    private JPanel myPanel;
    private ObservableVM252Machine mySubjectModel;
    private JLabel myNotes;
    private JTable myTable;

    //
    // Accessors
    //

    private JPanel getPanel()
    {
        return myPanel;
    }

    private ObservableVM252Machine getSubjectModel()
    {
        return mySubjectModel;
    }

    private JLabel getLable()
    {
        return myNotes;
    }

    //
    // Mutators
    //

    private void setPanel(JPanel other)
    {
        myPanel = other;
    }

    private void setSubjectModel(ObservableVM252Machine other)
    {

        if (getSubjectModel() != null)
            getSubjectModel().detach(this);

        mySubjectModel = other;

        if (getSubjectModel() != null)
            getSubjectModel().attach(this);

    }

    private void setLabel(JLabel other)
    {
        myNotes = other;
    }


    //
    // Ctor
    //

    public MemoryByteViewAndController()
    {
        this(null);
    }

    public MemoryByteViewAndController(ObservableVM252Machine initialMachine)
    {
        setSubjectModel(initialMachine);

        //
        // Create a panel to display the state of the machine model
        //

        setPanel(new JPanel());

        //
        // Add the panel to the container
        //

        //
        // Create table to display memory bytes
        //

        myTable = new JTable(410, 21);

        // Row address counter for labeling
        int rowAddr = 0;

        int memoryValueIndex = 0;

       
            for(int row = 0; row < 410; ++row){
                myTable.setValueAt("Addr " + rowAddr, row, 0);
                for(int col = 1; col < 21; ++col){
                    if(memoryValueIndex != 8192){

                        //
                        // Convert each byte as we loop through to an int and mask it 
                        // so that we can convert into a hex string
                        //

                        int byteToInt  = (int) getSubjectModel().getMemoryValue()[memoryValueIndex] & 0xff;
                        String hexValue = Integer.toHexString(byteToInt);

                        // Pads hexValue with a zero if half a hex
                        if( hexValue.length() % 2 == 1){
                            hexValue = "0" + hexValue;
                        }
                        myTable.setValueAt(hexValue, row, col);
                        ++memoryValueIndex;
                    }
                    // else there is nothing left to populate table as all of the memory
                    //has been entered into the table
                }
                rowAddr = rowAddr + 20;
            }

        myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane myScrollPane = new JScrollPane(myTable);
        myScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        myScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        myScrollPane.setBounds(0, 0, 300, 150);

        setLayout(null);
        add(myScrollPane);


        // Not done, want someone else to have a look at my code because I don't think it works properly
        // but I don't know what else to do to fix it at this point.

        myTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                int changedRow = myTable.getSelectedRow();
                int changedColumn = myTable.getSelectedColumn();
                // This makes sure that user didn't change addr part of table
                if (changedColumn != 0){
                    String hexValue = myTable.getValueAt(changedRow, changedColumn).toString();
                    System.out.println(hexValue);
                    // I couldn't get Long.parseLong to work for some reason? I found something else that seems to work
                    int hexToInt = Integer.parseInt(hexValue, 16);
                    System.out.println(hexToInt);
                    int byteIndexToChange = changedRow * 20 + changedColumn;
                    byte intToByte = (byte) hexToInt;
                    System.out.println(intToByte);
                    getSubjectModel().getMemoryValue()[byteIndexToChange] = intToByte;
                    System.out.println(getSubjectModel().getMemoryValue()[byteIndexToChange]);
                }
                else{

                    // This should override user if they try to change anything in column 0
                    int rowAddr = changedRow * 20;
                    myTable.setValueAt("Addr " + rowAddr, changedRow, changedColumn);
                }
                
                

            }
        });

    }



    @Override
    public void update()
    {
        //
        // Re-loops through memoryValueIndex and updates value if there is any change 
        // in byte array
        //

        int memoryValueIndex = 0;
        for(int row = 0; row < 410; ++row){
            for(int col = 1; col < 21; ++col){
                if(memoryValueIndex != 8192){
                    
                    // Convert each byte as we loop through to an int and mask it 
                    // so that we can convert into a hex string

                    int byteToInt  = (int) getSubjectModel().getMemoryValue()[memoryValueIndex] & 0xff;
                    String hexValue = Integer.toHexString(byteToInt);

                    // Pads hexValue with a zero if half a hex
                    if( hexValue.length() % 2 == 1){
                        hexValue = "0" + hexValue;
                    }
                    myTable.setValueAt(hexValue, row, col);
                    ++memoryValueIndex;
                }
                // else there is nothing left to populate table as all of the memory
                //has been entered into the table
            }
        }
        ;
    }
}
