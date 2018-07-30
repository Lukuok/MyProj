package datachecker;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

public class DataChecker extends TimerTask implements Serializable
{
    private static final long serialVersionUID = 1L;

    transient Timer timer = null;
    
    DecimalFormat form = new DecimalFormat("#,##0");
    
    long startTime;                 // 시작시간
    long nowTime;                   // 시작시간에서 빼주기 위한 시간
    int sec;                        // 지난 시간(초)

    int allMsgCnt;                  // 전체 메시지
    int oneSecMsgCnt;               // 1초 동안 메시지
    
    long allDataSize;               // 전체 데이터 크기
    long oneSecDataSize;            // 전체 데이터 크기(Mbyte 단위)

    double allMByteDataSize;        // 1초당 데이터 크기
    double oneSecMByteDataSize;     // 1초당 데이터 크기(Mbyte 단위)

    double avgMByteDataSpeed;       // 평균 데이터 전송 속도
    
    double msgDelay;

    double msgCntAvr;
    int cnt;
    
    StringBuffer sb = null;
    
    String hdfs = null;
    String file = null;
    
    public DataChecker(String hdfs, String file)
    {
        this.hdfs = hdfs;
        this.file = file;
        
        startTime = System.currentTimeMillis();
        sec = 0;

        allMsgCnt = 0;
        oneSecMsgCnt = 0;
        
        allDataSize = 0;
        oneSecDataSize = 0;

        allMByteDataSize = 0;
        oneSecMByteDataSize = 0;

        avgMByteDataSpeed = 0;
        
        msgCntAvr = 0;
        
        cnt = 0;
        
        sb = new StringBuffer();
        
        msgDelay = 0;
    }
    
    public void run() 
    {
        setString(allDataSize, oneSecDataSize, allMsgCnt, oneSecMsgCnt);
    }
    
    public void setString(long allDataSize, long oneSecDataSize_t, int allMsgCnt, int oneSecMsgCnt_t)
    {
        long tmpData = oneSecDataSize_t;
        oneSecDataSize = 0;
        
        long tmpMsg = oneSecMsgCnt_t;
        oneSecMsgCnt = 0;
        
        sec++; // 지난 시각

        allMByteDataSize = (double) allDataSize / (double) (1024 * 1024); //전체  데이터 크기(Mbyte단위)

        oneSecMByteDataSize = (double) tmpData / (double) (1024 * 1024); // 1초당 데이터  크기(Mbyte단위)

        avgMByteDataSpeed = (double) allMByteDataSize / (double) sec;
        
        msgDelay = (double) sec / (double) allMsgCnt;
        
        msgCntAvr = (double) allMsgCnt / (double) sec;

        if(cnt == 0)
        {
//            System.out.println("초당 메시지\t초당 처리량\t평균 지연시간\t평균 전송속도\t진행시간\t총 처리량\t총 메시지");
            sb.append("초당 메시지\t초당 전송속도\t평균 지연시간\t평균 메세지\t평균 전송속도\t진행시간\t총 처리량\t총 메시지\n");
            sb.append("EA/Sec\tMByte/Sec\tSec/EA(average)\tEA/Sec(average)\tMByte/Sec(average)\tSec\tMB\tEA\n");
            cnt++;
        }
//        System.out.println("시간 : " + sec + "초 || " + "총 데이터 : " + String.format("%.2f", allMByteDataSize) + "MByte || "
//              + "평균 전송속도 : " + String.format("%.2f", avgMByteDataSpeed) + "MByte/sec || " + "1초당 전송속도 : "
//              + String.format("%.2f", oneSecMByteDataSize) + " MByte/sec ");
        sb.append(form.format(tmpMsg)+"\t");                       //초당 메시지
        sb.append(String.format("%.2f", oneSecMByteDataSize)+"\t");//초당 전송속도
        sb.append(String.format("%.6f", msgDelay) + "\t");         //평균 지연시간
        sb.append(String.format("%.2f", msgCntAvr) + "\t");         //평균 메세지
        sb.append(String.format("%.2f", avgMByteDataSpeed)+"\t");  //평균 전송속도
        sb.append(sec+"\t");                                       //진행시간
        sb.append(String.format("%.2f", allMByteDataSize)+"\t");   //총 처리량
        sb.append(form.format(allMsgCnt)+"\n");                    //총 메시지
        
        sb = new StringBuffer();
    }
    
    ///////////////////////////////////////////////////////////////////////////
    
    public void start()
    {
        if (timer != null) close();
        timer = new Timer();
        timer.schedule(this, 0, 1000);
    }
    
    public void close()
    {
        if (timer != null) timer.cancel();
        timer = null;
    }
    
    public void setValue(int size)
    {
        allDataSize = allDataSize + size;       // 전체 데이터 크기
        oneSecDataSize = oneSecDataSize + size; // 1초당 데이터 크기
        
        allMsgCnt++;
        oneSecMsgCnt++;
    }
}
