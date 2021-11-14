import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MachineStateViewAndController extends JPanel implements SimpleObserver
{
    private static final int OUR_DEFAULT_FRAME_WIDTH = 300;
    private static final int OUR_DEFAULT_FRAME_HEIGHT = 300;

    private JPanel myPanel;
    private ObservableVM252Machine mySubjectModel;

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

    //
    // Mutators
    //

    private void setPanel(JPanel other)
    {
        myPanel = other;
        myPanel.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    private void setSubjectModel(ObservableVM252Machine other)
    {

        if (getSubjectModel() != null)
            getSubjectModel().detach(this);

        mySubjectModel = other;

        if (getSubjectModel() != null)
            getSubjectModel().attach(this);

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
        JLabel accLabel = new JLabel("ACC");
        JTextField acc = new JTextField("acc text");
        //acc.addActionListener(accListener);

        JLabel counterLabel = new JLabel("Counter");
        JTextField counter = new JTextField("counter text");
        //acc.addActionListener(counterListener);

        JLabel nextInstructionLabel = new JLabel("Next Instruction");
        JTextField nextInstruction = new JTextField("instruction text");
        nextInstruction.setEditable(false);

        GridLayout grid = new GridLayout(3,2);

        //
        // Create a panel to display the state of the machine model
        //

        setPanel(new JPanel());
        getPanel().setSize(OUR_DEFAULT_FRAME_WIDTH, OUR_DEFAULT_FRAME_HEIGHT);
        getPanel().setLayout(grid);

        getPanel().add(accLabel);
        getPanel().add(acc);
        getPanel().add(counterLabel);
        getPanel().add(counter);
        getPanel().add(nextInstructionLabel);
        getPanel().add(nextInstruction);

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
        
    }
}
