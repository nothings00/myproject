package algorithm.sort;


import java.util.Arrays;

/**
 * 快排
 * @author zenghh, 625111833@qq.com
 * @date 2019-09-18 14:29
 * @version 1.0.0
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] nums={12,45,23,67,7,1,5,21};
        quicksort(nums,0,nums.length-1);
    }

    /**
     * 快速排序
     * @param num 数组
     * @param start 数组起始位置
     * @param end 数组终止位置
     */
    public static void quicksort(int[] num,int start,int end){
        //如果数组长度为0或1，不需要排序
        if (num.length <= 1){
            return;
        }
        //如果开始大于结束
        if (start>=end){
            return;
        }

        //1.设置基准值:第一个值
        int valueStart = num[start];

        int keyStart = start;
        int keyEnd = end;
        int count = 0;

        //2.循环判断，交换左右的值
        while (keyStart < keyEnd){
            //3.1从右往左找，小于基准值
            while (num[keyEnd] > valueStart && keyStart < keyEnd){
                keyEnd--;
            }

            //3.2从左往右找，大于基准值
            while (num[keyStart] < valueStart && keyStart < keyEnd){
                keyStart++;
            }

            count++;
            System.out.println("交换前位置:"+ Arrays.toString(num));
            System.out.println(String.format("第%d交换。位置：%d,值：%d,与位置：%d,值：%d,交换",count,keyStart,num[keyStart],keyEnd,num[keyEnd]));
            //3.3 交换值的位置
            int temp = num[keyStart];
            num[keyStart] = num[keyEnd];
            num[keyEnd] = temp;
            System.out.println("交换后位置:"+ Arrays.toString(num)+"\n");
            quicksort(num,start,keyEnd-1);
            quicksort(num,keyStart+1,end);
        }
    }

}
