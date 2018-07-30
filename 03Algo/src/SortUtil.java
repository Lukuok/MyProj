
public class SortUtil
{
    public static void pArr(int seq, int[] arr)
    {
        if (seq != -1)
            System.out.print((seq + 1) + " 번째 정렬 : ");
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

    public static void swap(int a[], int idx1, int idx2)
    {
        int temp = a[idx1];
        a[idx1]  = a[idx2];
        a[idx2]  = temp;
    }
}
