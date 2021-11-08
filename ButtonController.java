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
    private JButton hHelp, nCommand, qCommand, rCommand, baSubmit, stop, resume, increaseSpeed, decreaseSpeed ;
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
        // baSubmit = new JButton("ba Submit");
        stop = new JButton("Stop");
        resume = new JButton("Resume");
        increaseSpeed = new JButton("Increase Speed");
        decreaseSpeed = new JButton ("Decrease Speed");
        hHelp = new JButton(" Help ");

        // Add buttons to toolbar

        tb.setFloatable(false);
        tb.add(toolbarLabel);
        tb.add(nCommand);
        tb.add(qCommand);
        tb.add(rCommand);
        tb.add(baLabel);
        tb.add(textFieldForba);
        // tb.add(baSubmit);
        tb.add(stop);
        tb.add(resume);
        tb.add(increaseSpeed);
        tb.add(decreaseSpeed);


        setPanel(new JPanel());
        // JLabel notes = new JLabel("Ghazal's part");
        // getPanel().add(notes);
        getPanel().add(tb);
        getPanel().add(hHelp);

        //
        // Add the panel to the container
        //

        add(getPanel());
    }
}

