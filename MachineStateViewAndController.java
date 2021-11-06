import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MachineStateViewAndController extends JPanel implements SimpleObserver
{
    private static final int OUR_DEFAULT_FRAME_WIDTH = 300;
    private static final int OUR_DEFAULT_FRAME_HEIGHT = 300;

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

    public MachineStateViewAndController()
    {
        this(null);
    }

    public MachineStateViewAndController(ObservableVM252Machine initialMachine)
    {
        setSubjectModel(initialMachine);
        setLabel(new JLabel("Micah's part"));

        //
        // Create a panel to display the state of the machine model
        //

        setPanel(new JPanel());
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
        // Set the text in the text fields to display the state of the machine, 
        // which includes ACC, Counter, Next Instruction
        //
        ;
    }
}
