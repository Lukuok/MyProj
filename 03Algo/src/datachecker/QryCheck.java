package datachecker;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

public class QryCheck extends TimerTask implements Serializable
{
    transient Timer timer = null;
    DecimalFormat form = new DecimalFormat("#,##0");
    
    long startTime;                 // 시작시간
    int  sec;                       // 지난 시간(초)
    
    int  oneSecMsgCnt;               // 1초 동안 메시지
    int  allMsgCnt;                  // 전체 질의 갯수
    
    double msgDelay;                //메시지 1초당 질의 시간
    double msgAvrDelay;            // 메시지 전체 평균 질의 시간
    
    StringBuffer sb;
    
    int cnt = 0;
    
    String hdfs = null;
    String file = null;
    
    public QryCheck(String hdfs, String file)
    {
        this.hdfs = hdfs;
        this.file = file;
        
        startTime = 0;
        sec = 0;

        oneSecMsgCnt = 0;
        allMsgCnt = 0;
        
        msgDelay = 0;
        msgAvrDelay = 0;
        
        sb = new StringBuffer();
    }
    
    public void run() 
    {
        setString(allMsgCnt, oneSecMsgCnt);
    }
    
    public void setString(int allMsgCnt_t, int oneSecMsgCnt_t)
    {
        long tmpMsg = oneSecMsgCnt_t;
        oneSecMsgCnt = 0;
        
        sec++; // 지난 시각
        
        msgDelay = (double) 1 / (double) tmpMsg;
        msgAvrDelay = (double) sec / (double) allMsgCnt_t;
        
        double avrQryCnt = (double) allMsgCnt_t / (double) sec;
        
        if(cnt == 0)
        {
            sb.append("1초당 질의 속도\t평균 질의 속도\t1초당 질의 갯수\t평균 질의 갯수\t전체 질의 갯수\t진행 시간\n");
            sb.append("msg/sec\tmsg/sec\tsec/msg\tsec/msg\tmsg\tsec\n");
            cnt++;
        }
        
        sb.append(String.format("%.3f", msgDelay)+"\t");            //1초당 질의 속도       msg/sec
        sb.append(String.format("%.3f", msgAvrDelay)+"\t");         //평균 질의 속도        msg/sec 
        sb.append(form.format(oneSecMsgCnt_t)+"\t");                //1초당 질의 갯수       sec/msg
        sb.append(String.format("%.1f", avrQryCnt)+"\t");           //평균 질의 갯수        sec/msg 
        sb.append(form.format(allMsgCnt_t) + "\t");                 //전체 질의 갯수        msg 
        sb.append(sec + "\n");                                      //진행 시간             sec 
        
        sb = new StringBuffer();
    }
    //////////////////    //////////////////    //////////////////
    
    public void start()
    {
        if (timer != null) close();
        startTime = System.currentTimeMillis();
        timer = new Timer();
        timer.schedule(this, 0, 1000);
    }
    
    public void close()
    {
        if (timer != null) timer.cancel();
        timer = null;
    }
    
    public void setValue()
    {
        allMsgCnt++;
        oneSecMsgCnt++;
    }
}
