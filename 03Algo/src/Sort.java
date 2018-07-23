
public class Sort
{
    public static void main(String[] args)
    {
        final int arr[] = { 4, 54, 2, 8, 63, 7, 55, 56 };
//        insertionSort(arr);
        
        System.out.println();
        
        bubbleSort(arr);
    }

    /*
     * 선택정렬 제자리 정렬 알고리즘의 하나로, 전체 원소들 중에서 기준 위치에 맞는 원소를 선택하여 자리를 교환하는 방식 1. 주어진 원소중
     * 최소값을 찾는다. 2. 그 값을 첫 번째 원소와 교환 한다. 3. 그 다음 작은 원소를 찾아 다음 위치의 원소와 비교하여 교환한다.
     */
    public static void insertionSort(int[] data)
    {
        int[] arr = data;
        SortUtil.pArr(-1, arr);
        int size = arr.length;
        int min; // 최소값을 가진 데이터의 인덱스 저장 변수
        int temp;

        for (int i = 0; i < size - 1; i++)
        { // size-1 : 마지막 요소는 자연스럽게 정렬됨
            min = i;
            for (int j = i + 1; j < size; j++)
            {
                if (arr[min] > arr[j])
                {
                    min = j;
                }
            }
            temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
            SortUtil.pArr(i, arr);
        }
    }

    /*
     * 버블정렬 : 버블정렬은 인접한 두 값을 비교해서 정렬해주는 정렬방식입니다.
     */
    public static void bubbleSort(int[] data)
    {
        int cnt = 0;
        int[] arr = new int[1];
        arr = data;
        SortUtil.pArr(-1, arr);
        int num = 0;
        for (int i = 0; i < arr.length - 1; i++)
        {
            boolean sort =
            false;
            for(int k = 0; k < arr.length - 1; k++)
            if (arr[k] > arr[k+1])
            {
                sort = true;
                cnt++;
                num      = arr[k];
                arr[k]   = arr[k+1];
                arr[k+1] = num;
            }
            if(sort==false) break;
            SortUtil.pArr(i, arr);
        }
        System.out.println(cnt);
    }
}