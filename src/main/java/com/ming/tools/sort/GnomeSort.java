package com.ming.tools.sort;

public class GnomeSort {

    public static void main(String[] args) {
        int[] arr = {9,2,6,4,1,0,7,5};
        int n= arr.length;
        gnome(arr,n);
    }

    public static void gnome(int[] arr, int n) {
        int i=0;
        while(i<n){// 只要不交换就自增 1，如果发生了n次，表示元素就有序了
            if(i==0) {  // i如果是 0，啥也不干，自增变成 1 再说
                i++;
            }
            if(arr[i]<arr[i-1]){
                int t = arr[i-1];
                arr[i-1]=arr[i];
                arr[i]=t;
                i--;   // 只要发生交换 i 就减 1
            }else {
                i++; // 只要不交换就自增 1
            }
        }
    }

}
