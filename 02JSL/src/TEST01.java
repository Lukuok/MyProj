import java.util.Arrays;

public class TEST01
{
    public static void main(String[] args)
    {
        int[] n = new int[]
        { 1, 2, 3, 4, 5 };

        printArr(n); // 배열의 모든요소를 출력
        sortArr(n); // 배열을 정렬
        printArr(n); // 정렬후 결과를 출력

    }

    static void printArr(int[] n)
    { // 배열을 모든요소를 출력
        System.out.print("[");

        for (int i : n) // 향상된 for문
            System.out.print(i + ",");
        System.out.println("]");
    }

    static void sortArr(int[] n)
    { // 배열을 오름차순으로 정렬
        for (int i = 0; i < n.length - 1; i++)
            for (int j = 0; j < n.length - 1 - i; j++)
                if (n[j] > n[j + 1])
                {
                    int tmp = n[j];
                    n[j] = n[j + 1];
                    n[j + 1] = tmp;

                }
    }
}
