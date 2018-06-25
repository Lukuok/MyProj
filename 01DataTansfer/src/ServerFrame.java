import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.JTextField;
import java.awt.List;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.JList;

public class ServerFrame
{
    public JFrame frame;
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
    public ServerFrame()
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
        
        List list_1 = new List();
        list_1.setBounds(33, 483, 914, 135);
        frame.getContentPane().add(list_1);
        
        JLabel label_1 = new JLabel("전송정보");
        label_1.setBounds(33, 462, 57, 15);
        frame.getContentPane().add(label_1);
        
        JLabel lblNewLabel = new JLabel("현재접속자");
        lblNewLabel.setBounds(638, 25, 77, 15);
        frame.getContentPane().add(lblNewLabel);
        
        JTree tree = new JTree();
        tree.setBounds(35, 60, 188, 391);
        frame.getContentPane().add(tree);
        
        JList list = new JList();
        list.setBounds(235, 60, 363, 391);
        frame.getContentPane().add(list);
        
        JList list_2 = new JList();
        list_2.setBounds(638, 60, 302, 391);
        frame.getContentPane().add(list_2);
        
        JMenuBar menuBar = new JMenuBar();
        menuBar.setToolTipText("");
        frame.setJMenuBar(menuBar);
    }
}
