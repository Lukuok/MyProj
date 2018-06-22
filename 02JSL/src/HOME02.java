import java.util.Scanner;

class Word
{
    static String reverseWord(String input)
    {
        String tmp = "";
        for(int i = input.length()-1; i >= 0; i--)
            tmp += input.charAt(i);
        return tmp;
    }
    
    static int countWord(String input)
    {
        int num = 0;
        input = input.toUpperCase();
        for(int i = 0; i < input.length(); i++)
        {
            char chk = input.charAt(i);
            if(chk == 'A' || chk == 'E' || chk == 'I' || chk == 'O' || chk == 'U')
                num++;
                
        }
        return num;
    }
    
    static String sliceWord(String input)
    {
        String tmp = "";
        for(int i = 0; i < input.length(); i++)
        {
            char chk = input.charAt(i);
            if(i == 0 || i == (input.length()-1))
                continue;
            tmp += chk;
        }
        return tmp;
    }
}

public class HOME02
{
    public static void main(String[] args)
    {
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        while(true)
        {
            System.out.print("1: 입력 순서 반대 / 2: 모음카운터 / 3 : 앞뒤 문자열 제거 / 4 : 종료 : ");
            int    num   = sc1.nextInt();
            if(num == 4) break;
            String input  = sc2.nextLine();
            String output = "";
            int   output_num = 0;
            switch(num)
            {
                case 1: output = Word.reverseWord(input); 
                         System.out.println(output);
                         break;
                case 2: output_num = Word.countWord(input);
                         System.out.println(output_num);
                         break;
                case 3: output = Word.sliceWord(input); 
                        System.out.println(output);
                        break;
                case 4: break;
            }
        }
    }

}
