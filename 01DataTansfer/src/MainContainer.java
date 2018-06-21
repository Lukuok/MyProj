import java.awt.Container;

import javax.swing.JFrame;

public class MainContainer extends Container
{
    public MainContainer()
    {
        setSize(500, 500);
        setVisible(true);   // 화면에 프레임 창을 표시
        
        ServerListFrame slf = new ServerListFrame();
        ClientSideFrame csf = new ClientSideFrame();
        
        this.add(slf);
        this.add(csf);
        
        System.out.println("실행됨");
    }
}
