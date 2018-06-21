import java.util.Scanner;

class Customer {
    String name;
    String id;
    String pw;
    
    boolean login(String id, String pw) {
       id = "";
       pw = "";
       if(id == "test" && pw =="1111") {
          System.out.println("회원입니다.");
          return true;
       } else {
          System.out.println("회원이 아닙니다.");
          return false;
       }   
       
       
    }
 }
 public class TEST01 {
    public static void main(String[] args)
    {
       Customer x = new Customer();
       Scanner scan = new Scanner(System.in);
       System.out.println("ID를 입력해주세요.");
       String id = scan.nextLine();
       System.out.println("PW를 입력해주세요.");
       String pw = scan.nextLine();
       
       boolean result = x.login(id, pw);
       
       System.out.println(result);
    }
 }