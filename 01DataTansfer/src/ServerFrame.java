import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

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
        
        JLabel label_1 = new JLabel("전송정보");
        label_1.setBounds(33, 462, 57, 15);
        frame.getContentPane().add(label_1);
        
        JLabel lblNewLabel = new JLabel("현재접속자");
        lblNewLabel.setBounds(638, 25, 77, 15);
        frame.getContentPane().add(lblNewLabel);
        
        //디렉토리 리스트
        JTree tree = new JTree();
        tree.setBounds(35, 60, 188, 391);
        initJtree(tree);
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
    
    //폴더 리스트 초기화
    private void initJtree(JTree tree)
    {
        DefaultMutableTreeNode root;
        DefaultTreeModel       treeModel;
        
        File fileRoot = new File("C:/");
        root          = new DefaultMutableTreeNode(new FileNode(fileRoot));
        treeModel     = new DefaultTreeModel(root);
        tree = new JTree(treeModel);
        tree.setShowsRootHandles(true);
        JScrollPane scrollPane = new JScrollPane(tree);

        // 파일 리스트 생성하는 다른 방법
        /*
        File[] file = File.listRoots(); // 루트배열을 생성
        for (int i = 0; i < file.length; i++)
        {// 루트길이 만큼 반복
            DefaultMutableTreeNode dmt = new DefaultMutableTreeNode(file[i]);// 트리목록 생성
            dmt.add(new DefaultMutableTreeNode("EMPTY")); // 초기값은 EMPTY로 설정하여 추가
            root.add(dmt); // 루트 트리에 목록 추가
        }
        */
        System.out.println("폴더리스트 확인");
        
    }
    
    class FileNode {

        private File file;

        public FileNode(File file) {
            this.file = file;
        }

        @Override
        public String toString() {
            String name = file.getName();
            if (name.equals("")) {
                return file.getAbsolutePath();
            } else {
                return name;
            }
        }
    }
}
