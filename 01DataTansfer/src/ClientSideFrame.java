import javax.swing.JFrame;

public class ClientSideFrame extends JFrame
{
    ClientSideFrame()
    {
        setSize(500, 500);
        setVisible(true);   // 화면에 프레임 창을 표시
        System.out.println("ClientSideFrame 실행됨");
    }
}