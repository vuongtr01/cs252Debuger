import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ButtonController extends JPanel
{
    private JPanel myPanel;
    private ObservableVM252Machine myModel;

    // toolbar
    private JToolBar tb;

    // buttons
    private JButton hHelp, nCommand, qCommand, rCommand, stop, resume, increaseSpeed, decreaseSpeed ;
    private JLabel toolbarLabel, baLabel;
    private JTextField textFieldForba;

    //
    // Accessors
    //

    private JPanel getPanel()
    {
        return myPanel;
    }

    private ObservableVM252Machine getModel()
    {
        return myModel;
    }

    //
    // Mutators
    //

    private void setPanel(JPanel other)
    {
        myPanel = other;
    }

    private void setModel(ObservableVM252Machine other)
    {
        myModel = other;
    }

    //
    // Ctors
    //

    public ButtonController()
    {
        this(null);
    }

    public ButtonController(ObservableVM252Machine initialModel)
    {
        setModel(initialModel);

        // create a toolbar
        tb = new JToolBar();

        // Create buttons

        toolbarLabel = new JLabel("Toolbar ");
        nCommand = new JButton(" n ");
        qCommand = new JButton(" q ");
        rCommand = new JButton(" r ");
        baLabel = new JLabel(" ba: ");
        textFieldForba = new JTextField("enter value for ba", 10);
        stop = new JButton("Stop");
        resume = new JButton("Resume");
        increaseSpeed = new JButton("Increase Speed");
        decreaseSpeed = new JButton ("Decrease Speed");
        hHelp = new JButton(" Help ");

        // add the help functionality
        // to print what all the other commands do
        // using an eventlistenr

        hHelp.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
                String [] helpContents = {"ba MA = Set a breakpoint at address MA", 
                "help = Print this help message", 
                "n = Execute next machine instruction",
                "q = Quit",
                "r = Run machine until error occurs or stop instruction is executed"
                };
		        getModel().setDisplayContents(helpContents);
            }});

        // add the stop functionality
        // quit the program up clicking on the stop button
        // using an event listener for this function

        ActionListener stopActListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };

        stop.addActionListener(stopActListener);

        //
        // Add action listener for r command button
        //

        RunButtonActionListener runListener = new RunButtonActionListener();
        rCommand.addActionListener(runListener);

        // Add buttons to toolbar

        tb.setFloatable(false);
        tb.add(toolbarLabel);
        tb.add(nCommand);
        tb.add(qCommand);
        tb.add(rCommand);
        tb.add(baLabel);
        tb.add(textFieldForba);
        tb.add(stop);
        tb.add(resume);
        tb.add(increaseSpeed);
        tb.add(decreaseSpeed);
        tb.addSeparator(new Dimension(5, 1));
        tb.add(hHelp);


        setPanel(new JPanel());
        getPanel().add(tb);

        // add color to the background of the toolbar
        // to sdistinguich it from other part

        tb.setBackground(new Color(200,200,200));


        // Add the panel to the container

        add(getPanel());
    }

    private class RunButtonActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            while ( ! getModel().getHaltStatus())
            {
                getModel().runProgram();
            }
        }
    }
}

