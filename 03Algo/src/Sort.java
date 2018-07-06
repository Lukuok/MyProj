
public class Sort
{
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        int arr[] = { 3,9,6,1,2 };
        printArr(-1, arr);
        insertionSort(arr);
    }
    
    public static void insertionSort(int[] data){
        int size = data.length;
        int min; //최소값을 가진 데이터의 인덱스 저장 변수
        int temp;
        
        for(int i=0; i<size-1; i++){ // size-1 : 마지막 요소는 자연스럽게 정렬됨
            min = i;
            for(int j=i+1; j<size; j++){
                if(data[min] > data[j]){
                    min = j;
                }
            }
            temp = data[min];
            data[min] = data[i];
            data[i] = temp;
            printArr(i, data);
        }
    }

    public static void printArr(int seq,int [] arr)
    {
        System.out.print(seq + " 번째 정렬 : ");
        for(int i = 0; i < arr.length; i++)
        {
            if   (i == arr.length - 1) System.out.print(arr[i]);
            else System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}