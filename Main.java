import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;
import vm252utilities.VM252Utilities;

public class Main
{
    public static void main(String [] commandLineArguments)
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter VM252 object-file name: ");
        String objectFileName = in.nextLine();

        byte [] program = VM252Utilities.readObjectCodeFromObjectFile(objectFileName);

        if (program == null)
            System.out.println(
                    "File does not exist or isn't a valid VM252 object-code file"
                    );
        else{
            EventQueue.invokeLater(
                () ->
                    {


                        //
                        // Create program frame
                        //

                            ProgramFrame frame = new ProgramFrame(program);

                        //
                        // Set frame's visibility and closing behavior
                        //

                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            frame.setVisible(true);

                        }

                );
        }

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

    public ProgramFrame(byte[] program)
    {
        setTitle("VM252 Debugger");
        setSize(OUR_DEFAULT_WIDTH, OUR_DEFAULT_HEIGHT);

        //
        // Create Model Object
        //

        ObservableVM252Machine machine = new ObservableVM252Machine(program);

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

        add(getPanel());
    }
}
