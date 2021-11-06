import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JList;

public class DisplayRunningPanel extends JPanel implements SimpleObserver
{
    private static final int OUR_DEFAULT_WIDTH = 600;
    private static final int OUR_DEFAULT_HEIGHT = 300;

    private JPanel myPanel;
    private ObservableVM252Machine mySubject;
    private String [] myContents;

    //
    // Accessors
    //

    private JPanel getPanel()
    {
        return myPanel;
    }

    private ObservableVM252Machine getSubject()
    {
        return mySubject;
    }

    private String [] getContents()
    {
        return myContents;
    }

    //
    // Mutators
    //

    private void setPanel(JPanel other)
    {
        myPanel = other;
    }

    private void setContents(String [] other)
    {
        myContents = other;
    }

    private void setSubject(ObservableVM252Machine other)
    {
        if (getSubject() != null)
            getSubject().detach(this);

        mySubject = other;

        if (getSubject() != null)
            getSubject().attach(this);

    }

    //
    // Ctors
    //

    public DisplayRunningPanel()
    {
        this(null);
    }

    public DisplayRunningPanel(ObservableVM252Machine machine)
    {
        setSize(OUR_DEFAULT_WIDTH, OUR_DEFAULT_HEIGHT);

        setPanel(new JPanel());
        getPanel().setBackground(new Color(153, 255, 51));


        //
        // Initially display the model's display contents
        //

        setSubject(machine);
        setContents(getSubject().getDisplayContents());

    }

    @Override
    public void update()
    {
        setContents(getSubject().getDisplayContents());

        repaint();
    }
}

