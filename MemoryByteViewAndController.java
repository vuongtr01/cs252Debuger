import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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

        // for loop that loops through every row, labels the addr
        // then another for loop that loops through every other cell
        // and inserts memory byte. Because we have no
        // file to run off of, we are displaying everything as empty, aka 00
        //
        // When we get to using the commands with the table, we will need
        // getColumn(), getRow(), getValue(), setValue(), and maybe a few others
        // all set up in the model so that we can allow user to edit values
        // and so we can store them too. Along with an event listener. Techincally
        // the table is already editable, but do we want to re-manually change it
        // so the event listener is built in and so that only certain cells are editable?

        int memoryValueIndex = 0;

       
            for(int row = 0; row < 410; ++row){
                myTable.setValueAt("Addr " + rowAddr, row, 0);
                for(int col = 1; col < 21; ++col){
                    if(memoryValueIndex != 8192){
                        myTable.setValueAt(getSubjectModel().getMemoryValue()[memoryValueIndex], row, col);
                        ++memoryValueIndex;
                    }
                    // else there is nothing left to populate table as all of the memory
                    //has been entered into the table
                }
                rowAddr = rowAddr + 20;
            }

        myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane myScrollPane=new JScrollPane(myTable);
        myScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        myScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        myScrollPane.setBounds(0, 0, 300, 150);

        setLayout(null);
        add(myScrollPane);

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
                    myTable.setValueAt(getSubjectModel().getMemoryValue()[memoryValueIndex], row, col);
                    ++memoryValueIndex;
                }
                // else there is nothing left to populate table as all of the memory
                //has been entered into the table
            }
        }
        ;
    }
}
