import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main
{
    public static void main(String [] commandLineArguments)
    {
        EventQueue.invokeLater(
            () ->
                {


                    //
                    // Create program frame
                    //

                        ProgramFrame frame = new ProgramFrame();

                    //
                    // Set frame's visibility and closing behavior
                    //

                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setVisible(true);

                    }

            );

    }
}

class ProgramFrame extends JFrame
{

    private static final int OUR_DEFAULT_WIDTH = 800;
    private static final int OUR_DEFAULT_HEIGHT = 900;

    private JPanel myPanel;

    //
    // Accessors
    //

    private JPanel getPanel()
    {
        return myPanel;
    }

    //
    // Mutators
    //

    private void setPanel(JPanel other)
    {
        myPanel = other;
    }

    //
    //Ctors
    //

    public ProgramFrame()
    {
        setTitle("VM252 Debugger");
        setSize(OUR_DEFAULT_WIDTH, OUR_DEFAULT_HEIGHT);

        //
        // Create Model Object
        //

        ObservableVM252Machine machine = new ObservableVM252Machine();

        //
        // Create function buttons Pannel
        //
        FunctionButtonsPanel buttonsPanel = new FunctionButtonsPanel(machine);

        //
        // Create display machine pannel
        //

        DisplayMachinePanel displayPanel = new DisplayMachinePanel(machine);

        //
        // Create display running Pannel
        //

        DisplayRunningPanel runningPanel = new DisplayRunningPanel(machine);

        //
        // ADD Panel to programe frame
        //

        setPanel(new JPanel());
        getPanel().setLayout(null);

        buttonsPanel.setBounds(0, 0, 800, 200);
        getPanel().add(buttonsPanel);

        displayPanel.setBounds(0, 200, 800, 300);
        getPanel().add(displayPanel);

        runningPanel.setBounds(0, 500, 800, 300);
        getPanel().add(runningPanel);
        getPanel().setBackground(new Color(255, 229, 204));
        add(getPanel());
    }
}
