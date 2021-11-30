import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MachineStateViewAndController extends JPanel implements SimpleObserver
{
    private static final int OUR_DEFAULT_FRAME_WIDTH = 300;
    private static final int OUR_DEFAULT_FRAME_HEIGHT = 300;

    private JPanel myPanel;
    private JTextField myAccTextField;
    private JTextField myPcTextField;
    private JTextField myNextInstructionTextField;

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

    private JTextField getAccTextField()
    {
        return myAccTextField;
    }

    private JTextField getPcTextField()
    {
        return myPcTextField;
    }

    private JTextField getNextInstructionTextField()
    {
        return myNextInstructionTextField;
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

    private void setAccTextField( JTextField other )
    {
        myAccTextField = other;
    }

    private void setPcTextField( JTextField other )
    {
        myPcTextField = other;
    }

    private void setNextInstructionTextField( JTextField other )
    {
        myNextInstructionTextField = other;
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
        setAccTextField(new JTextField("" + getSubjectModel().getACCValue()));
        ActionListener setAccValue = new ActionListener(){
	        public void actionPerformed(ActionEvent accChange){
                getSubjectModel().resetDisplayContents();
		        getSubjectModel().setACCValue(Short.valueOf(getAccTextField().getText()));
                getSubjectModel().setDisplayContents(new String[] {"Set ACC value to " + getAccTextField().getText()});

          }};
        getAccTextField().addActionListener(setAccValue);

        JLabel counterLabel = new JLabel("Counter");
        setPcTextField(new JTextField("" + getSubjectModel().getPCValue()));

        // Add action listener to counter,
        // when enter is hit on the keyboard
        // PC is going to be stored using setPCValue()

        ActionListener setPcValue = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getSubjectModel().resetDisplayContents();
                getSubjectModel().setPCValue(Short.valueOf(getPcTextField().getText()));
                getSubjectModel().setDisplayContents(new String[] {"Set PC value to " + getPcTextField().getText()});
                // pc is set to counter.getText()
            }
        };
        getPcTextField().addActionListener(setPcValue);


        JLabel nextInstructionLabel = new JLabel("Next Instruction");
        setNextInstructionTextField(new JTextField(getSubjectModel().getNextInst()));
        getNextInstructionTextField().setEditable(false);

        GridLayout grid = new GridLayout(3,2);

        //
        // Create a panel to display the state of the machine model
        //

        setPanel(new JPanel());
        getPanel().setSize(OUR_DEFAULT_FRAME_WIDTH, OUR_DEFAULT_FRAME_HEIGHT);
        getPanel().setLayout(grid);

        getPanel().add(accLabel);
        getPanel().add(getAccTextField());
        getPanel().add(counterLabel);
        getPanel().add(getPcTextField());
        getPanel().add(nextInstructionLabel);
        getPanel().add(getNextInstructionTextField());

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

        getAccTextField().setText("" + getSubjectModel().getACCValue());
        getPcTextField().setText("" + getSubjectModel().getPCValue());
        getNextInstructionTextField().setText("" + getSubjectModel().getNextInst());

    }
}
