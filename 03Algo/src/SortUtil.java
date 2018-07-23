
public class SortUtil
{
    public static void pArr(int seq, int[] arr)
    {
        if (seq != -1)
            System.out.print(seq + " 번째 정렬 : ");
        else
            System.out.print("정렬전 배열 : ");
        for (int i = 0; i < arr.length; i++)
        {
            if (i == arr.length - 1)
                System.out.print(arr[i]);
            else
                System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
