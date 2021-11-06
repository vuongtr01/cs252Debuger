import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MemoryByteViewAndController extends JPanel implements SimpleObserver
{

    private JPanel myPanel;
    private ObservableVM252Machine mySubjectModel;
    private JLabel myNotes;

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
        setLabel(new JLabel("Tess's part"));

        //
        // Create a panel to display the state of the machine model
        //

        setPanel(new JPanel());
        getPanel().setBackground(new Color(255, 255, 204));
        getPanel().add(getLable());

        //
        // Add the panel to the container
        //

        add(getPanel());
    }

    @Override
    public void update()
    {
        //
        // Set the table to display all the memory bytes of the machine's memory
        //
        ;
    }
}

