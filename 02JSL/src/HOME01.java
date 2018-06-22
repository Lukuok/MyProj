class BackAccount
{
    private int balance = 0;
    
    void deposit(int amount)
    {
        balance += amount;
        System.out.println("+ " + balance + " 원이 입금되었습니다.");
    }
    
    void withdraw(int amount)
    {
        if(amount > balance)
        {
            System.out.println("잔고가 부족합니다.");
            return;
        }
        balance -= amount;
        System.out.println("- " + amount  + " 원을 출금합니다.");
        System.out.println("# " + balance + " 원이 계좌에 있습니다.");
    }
    
    int getBalance()
    {
        return balance;
    }
    
    void getInterest()
    {
        int interest = (int)(balance * 0.075f);
        balance = balance + interest;
        System.out.println("++" + interest + "원의 이자 지급");
    }
}

class BackAccountTest
{
    static void test()
    {
        BackAccount ba = new BackAccount();
        ba.deposit(1000000); //백만원 입금
        ba.withdraw(500000); //50만원 출금
        ba.getInterest();    //이자 지급
        System.out.println("현재 " + ba.getBalance() + " 원이 계좌에 있습니다.");
    }
}

public class HOME01
{
    public static void main(String[] args)
    {
        BackAccountTest.test();
    }

}
