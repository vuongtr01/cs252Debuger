import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ButtonController extends JPanel
{
    private JPanel myPanel;
    private ObservableVM252Machine myModel;

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

        //
        // Create buttons to do fuction
        //

        setPanel(new JPanel());
        JLabel notes = new JLabel("Ghazal's part");
        getPanel().add(notes);

        //
        // Add the panel to the container
        //

        add(getPanel());
    }
}

