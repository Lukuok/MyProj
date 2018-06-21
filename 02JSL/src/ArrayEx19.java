class ArrayEx19
{
    public static void main(String[] args)
    {
        int a[][]      = new int[5][5];
        int arr_center = a.length / 2; 
        int carry      = 0;
        
        for(int i = 0; i < a.length; i++)
            for(int k = 0; k < a[i].length; k++)
                a[i][k] = 0;
        
        for(int i = 0; i < a.length; i++)
        {
            for(int k = 0; k < a[i].length; k++)
            {
                a[i][k] = 1;
            }
            if(arr_center > carry)
                carry+=2;
            else
                carry-=2;
        }
        
        for(int i = 0; i < a.length; i++)
        {
            for(int k = 0; k < a[i].length; k++)
                System.out.print(a[i][k] + " ");
         System.out.println();   
        }
        
                
    }
}
