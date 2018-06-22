
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreePath;

public class FileSearch extends JFrame implements TreeWillExpandListener, ActionListener
{

    private Container              con;
    private JSplitPane             sp       = new JSplitPane();
    private JSplitPane             sp1      = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    private DefaultMutableTreeNode root     = new DefaultMutableTreeNode("탐색기 By koLee"); // 첫번째 트리를 만듬
    private JTree                  tree_jt  = new JTree(root);                            // 첫번째 루트로 트리를 생성 루트로 시작
    private JScrollPane            tree_jsp = new JScrollPane(tree_jt);                   // 트리에 스크롤 추가
    private Vector                 view_vc  = new Vector();                               // 벡터 클래스 인스턴스화
    private JList                  view_li  = new JList(view_vc);
    private JScrollPane            view_jsp = new JScrollPane(view_li);                   // 리스트에 스크롤 추가

    // 버튼 추가
    private JButton                view_bt  = new JButton("View");
    private JButton                edit_bt  = new JButton("Edit");
    private JButton                del_bt   = new JButton("Delete");
    private JButton                end_bt   = new JButton("Exit");

    // 텍스트 구역추가
    private JTextArea              data_ta  = new JTextArea();
    private JScrollPane            data_jsp = new JScrollPane(data_ta);                   // 텍스트 구역에 스크롤 추가
    private Dimension              screen;                                                // 디멘션 클래스 선언

    // 생성자
    public FileSearch()
    {
        super("탐색기 By koLee"); // 프레임 생성자 호출
        screen = Toolkit.getDefaultToolkit().getScreenSize(); // 스크린 사이즈를 가져옴
        this.init();// 초기화 함수
        this.start(); // 시작 함수
        this.setSize((int) screen.getWidth(), (int) screen.getHeight()); // 프레임의 사이즈를 스크린 사이즈와 동일시함
        this.setLocation(0, 0); // 위치는 0, 0
        this.setVisible(true); // 프레임 띄우기
    }

    private void start()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 이벤트 호출
        tree_jt.addTreeWillExpandListener(this);
        view_bt.addActionListener(this);
        edit_bt.addActionListener(this);
        del_bt.addActionListener(this);
        end_bt.addActionListener(this);
    }

    private void init()
    {

        con = this.getContentPane();
        con.setLayout(new BorderLayout()); // 콘테이너 레이아웃 설정
        con.add("Center", sp); // 스플릿 추가
        tree_jsp.setPreferredSize(new Dimension(200, (int) screen.getHeight())); // 넓이는 200, 높이는 스크린의 사이즈를 가져옴
        sp.setLeftComponent(tree_jsp); // 트리팬을 왼쪽에 놓음
        JPanel jp = new JPanel(new BorderLayout());// 패널 생성
        view_jsp.setPreferredSize(new Dimension((int) screen.getWidth() - 220, 300));// 스크로랜의 사이즈 고정
        jp.add("Center", view_jsp); // 패널에 스크롭 추가
        JPanel jp1 = new JPanel(new GridLayout(1, 4, 5, 5)); // 패널2생성
        jp1.add(view_bt); // 버튼 추가
        jp1.add(edit_bt);
        jp1.add(del_bt);
        jp1.add(end_bt);
        jp.add("South", jp1); // 패널2를 패널에 넣음
        sp1.setTopComponent(jp);
        sp1.setBottomComponent(data_jsp);
        sp.setRightComponent(sp1);

        File[] file = File.listRoots(); // 루트배열을 생성
        for (int i = 0; i < file.length; i++)
        {// 루트길이 만큼 반복
            DefaultMutableTreeNode dmt = new DefaultMutableTreeNode(file[i]);// 트리목록 생성
            dmt.add(new DefaultMutableTreeNode("EMPTY")); // 초기값은 EMPTY로 설정하여 추가
            root.add(dmt); // 루트 트리에 목록 추가
        }
    }

    @Override
    public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void treeWillExpand(TreeExpansionEvent e) throws ExpandVetoException
    {
        if (e.getSource() == tree_jt)
        {
            tree_jt.setSelectionPath(e.getPath()); // 선택된 경로 설정
            TreePath tp = tree_jt.getSelectionPath(); // 대입
            StringTokenizer stk = new StringTokenizer(tp.toString(), "[,]");
            stk.nextToken();
            if (stk.hasMoreTokens())
            { // 토큰이 존재하면
                String filepath = stk.nextToken().trim(); // 다음토큰을 경로에 대입
                while (stk.hasMoreTokens())
                {// 토큰이 있을때까지
                    filepath += stk.nextToken().trim() + "/"; // 경로를 만들어줌
                }
                File dir = new File(filepath); // 경로 객체 생성
                File[] data = dir.listFiles(); // 파일 객체배열에 파일리스트 넣음
                if (data == null)
                { // 데이터가 널이면
                    return; // 리턴
                }
                DefaultMutableTreeNode temp = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
                temp.removeAllChildren();
                view_vc.clear(); // 벡터 청소
                if (data.length == 0)
                {
                    temp.add(new DefaultMutableTreeNode("EMPTY"));// 어떤한 하위 폴더도 없으면 Empty 출력
                } else
                {
                    int count = -1;
                    for (int i = 0; i < data.length; i++)
                    {
                        if (data[i].isDirectory())
                        {// 디렉토리가 있으면
                            DefaultMutableTreeNode dtm = new DefaultMutableTreeNode(data[i].getName());// 디렉토리노드생성
                            dtm.add(new DefaultMutableTreeNode("EMPTY"));// 추가
                            temp.add(dtm); // 디렉토리 넣음
                            count++;
                        } else
                        {
                            view_vc.add((data[i].getName() + " (" + data[i].length() + "byte, "
                                    + new Date(data[i].lastModified()) + ")"));
                        }
                    }
                    if (count == -1)
                    {
                        temp.add(new DefaultMutableTreeNode("EMPTY"));
                    }
                }
                view_li.setListData(view_vc);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == view_bt)
        { // 뷰 버튼일때
            TreePath tp = tree_jt.getSelectionPath(); // 트리로 부터 선택된 경로를 가져옴
            StringTokenizer stk = new StringTokenizer(tp.toString(), "[,]");
            stk.nextToken();
            if (stk.hasMoreTokens())
            {
                String filepath = stk.nextToken().trim(); // 파일경로
                while (stk.hasMoreTokens())
                {
                    filepath += stk.nextToken().trim() + "/"; // 슬러시를 붙이면서 파일경로 생성
                }
                String filename = (String) view_li.getSelectedValue(); // 파일네임
                filename = filename.substring(0, filename.indexOf("(")).trim();
                File f = new File(filepath, filename); // 파일 열기
                try
                {
                    BufferedReader in = new BufferedReader(new FileReader(f));
                    data_ta.setText("");
                    while (true)
                    {// 무한루프
                        String str = in.readLine();// 라인씩 가져옴
                        if (str == null)
                        {// 라인이 없으면 나감
                            break;
                        }
                        data_ta.append(str + "\n"); // 개행문자를 붙여줌
                    }
                    in.close();
                } catch (IOException ee)
                {
                }
            }
        } else if (e.getSource() == edit_bt)
        {
            TreePath tp = tree_jt.getSelectionPath();
            StringTokenizer stk = new StringTokenizer(tp.toString(), "[,]");
            stk.nextToken();
            if (stk.hasMoreTokens())
            {
                String filepath = stk.nextToken().trim();
                while (stk.hasMoreTokens())
                {
                    filepath += stk.nextToken().trim() + "/";
                }
                String filename = (String) view_li.getSelectedValue(); // 뷰리스트에서 선택된 값을 넣음
                filename = filename.substring(0, filename.indexOf("(")).trim(); // 파일네임 생성
                File f = new File(filepath, filename);// 파일열기
                int xx = JOptionPane.showConfirmDialog(this, "정말 수정하시겠습니까?", "수정", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (xx == 0)
                {
                    try
                    {
                        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
                        byte[] data = data_ta.getText().trim().getBytes(); // 파일수정
                        dos.write(data); // 수정된 텍스트에어리어있는 것을 파일에 쓰기
                        dos.close(); // 파일닫기
                    } catch (IOException ee)
                    {
                    }
                }
            }
        } else if (e.getSource() == del_bt)
        {
            TreePath tp = tree_jt.getSelectionPath();
            StringTokenizer stk = new StringTokenizer(tp.toString(), "[,]");
            stk.nextToken();
            if (stk.hasMoreTokens())
            {
                String filepath = stk.nextToken().trim();
                while (stk.hasMoreTokens())
                {
                    filepath += stk.nextToken().trim() + "/";
                }
                String filename = (String) view_li.getSelectedValue();
                filename = filename.substring(0, filename.indexOf("(")).trim();
                File f = new File(filepath, filename);
                int xx = JOptionPane.showConfirmDialog(this, "정말 삭제하시겠습니까?", "삭제", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (xx == 0)
                {
                    f.delete(); // 파일삭제
                    view_vc.clear(); // 초기화
                    view_li.setListData(view_vc);// 다시 읽어옴
                    data_ta.setText("");

                }
            }
        } else if (e.getSource() == end_bt)
        {
            System.exit(0);
        }
    }

    public static void main(String args[])
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        FileSearch ex = new FileSearch();
    }
}
