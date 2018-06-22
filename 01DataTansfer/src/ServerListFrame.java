import java.awt.Component;
import java.awt.EventQueue;
import java.awt.List;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JTextField;

public class ServerListFrame
{
    private JFrame frame;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    ServerFrame window = new ServerFrame();
                    window.frame.setVisible(true);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public ServerListFrame()
    {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize()
    {
        frame = new JFrame();
        frame.setBounds(100, 100, 999, 715);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JLabel label = new JLabel("현재위치");
        label.setBounds(33, 25, 57, 15);
        frame.getContentPane().add(label);
        
        textField = new JTextField();
        textField.setBounds(102, 22, 496, 21);
        frame.getContentPane().add(textField);
        textField.setColumns(10);
        
        List list = new List();
        list.setBounds(31, 59, 567, 392);
        frame.getContentPane().add(list);
        
        Component horizontalStrut = Box.createHorizontalStrut(20);
        horizontalStrut.setBounds(33, 461, 744, 134);
        frame.getContentPane().add(horizontalStrut);
        
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
    }
}
