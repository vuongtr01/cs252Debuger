import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JList;

public class DisplayRunningPanel extends JPanel implements SimpleObserver
{
    private static final int OUR_DEFAULT_WIDTH = 500;
    private static final int OUR_DEFAULT_HEIGHT = 250;

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
        setSubject(machine);
        setContents(getSubject().getDisplayContents());

        setPanel(new JPanel());

        String displayString = "";

        for (String content : getContents())
        {
            displayString = displayString + content + "\n";
        }
        //
        // Initially display the model's display contents
        //

        JTextArea displayBox = new JTextArea(displayString, 200, 1);
        displayBox.setBounds(150, 25, OUR_DEFAULT_WIDTH, OUR_DEFAULT_HEIGHT);
        displayBox.setBackground(new Color(32, 32, 32));
        displayBox.setForeground(Color.WHITE);
        displayBox.setLineWrap(true);

        JScrollPane scroll = new JScrollPane(displayBox, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(150, 25, OUR_DEFAULT_WIDTH, OUR_DEFAULT_HEIGHT);

        setLayout(null);
        add(scroll);

    }

    @Override
    public void update()
    {
        setContents(getSubject().getDisplayContents());

        repaint();
    }
}

