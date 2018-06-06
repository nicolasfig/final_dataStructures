import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Estudiante
 */
public class Sorting {

    public void bubbleSort(int [] unsorted){
        boolean swap;
        int temp;
        do{
            swap = false;
            for(int i = 0; i < unsorted.length - 1; i++){
                if(unsorted[i] > unsorted[i+1]){
                    temp = unsorted[i];
                    unsorted[i] = unsorted[i+1];
                    unsorted[i+1] = temp;
                    swap = true;
                }        
            }
        }while(swap);
    }
    
    public void insertionSort(int [] unsorted){
        int valueToInsert;
        int holePosition;
        
        for(int i = 0; i < unsorted.length; i++){
            valueToInsert = unsorted[i];
            holePosition = i;
            
            while(holePosition > 0 && unsorted[holePosition - 1] > valueToInsert){
                unsorted[holePosition] = unsorted[holePosition - 1];
                holePosition -= 1;
            }
            unsorted[holePosition] = valueToInsert;
        }
    }
    
    public int [] mergeSort(int [] unsorted){
        int size = unsorted.length / 2; 
        if(unsorted.length > 1){
            int [] leftSubArray = new int[size];
            int [] rightSubArray = new int[unsorted.length - size];
            
            System.arraycopy(unsorted, 0, leftSubArray, 0, size);
            
            for(int i = size; i < unsorted.length; i++){
                rightSubArray[i - size] = unsorted[i];
            }
            
            leftSubArray = mergeSort(leftSubArray);
            rightSubArray = mergeSort(rightSubArray);
            
            return merge(leftSubArray, rightSubArray);
            
        }
        
        return unsorted;
    }
    
    public int[] merge(int [] leftSubArray, int [] rightSubArray){
        int len = leftSubArray.length + rightSubArray.length;
        int [] mergedArray = new int[len];
        
        int indexLeft, indexRight, indexMerged;
        indexLeft = indexRight = indexMerged = 0; 
        
        while(indexLeft < leftSubArray.length && indexRight < rightSubArray.length){
            if(leftSubArray[indexLeft] < rightSubArray[indexRight]){
                mergedArray[indexMerged] = leftSubArray[indexLeft];
                indexLeft += 1;
            }else{
                mergedArray[indexMerged] = rightSubArray[indexRight];
                indexRight += 1;
            }
                indexMerged += 1;
        }
        
        while(indexLeft < leftSubArray.length){
            mergedArray[indexMerged] = leftSubArray[indexLeft];
            indexMerged += 1;
            indexLeft += 1;
        }
        
        while(indexRight < rightSubArray.length){
            mergedArray[indexMerged] = rightSubArray[indexRight];
            indexMerged += 1;
            indexRight += 1;
        }
        
        return mergedArray;
    }
    public int findMax(int [] unsorted){
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < unsorted.length; i++){
            if(unsorted[i]>max) max = unsorted[i];
        }
        return max;
    }
    
    public void countingSort(int [] unsorted){
        
        int [] aux_array = new int[findMax(unsorted)+1];
        int index = 0;
        for(int i = 0; i < unsorted.length; i++){
            aux_array[unsorted[i]] += 1;
        }
        
        for(int i = 0; i < aux_array.length; i++){
            for(int j = 0; j < aux_array[i]; j++){
                unsorted[index] = i;
                index += 1;
            }
        }
    }
    
    /* Binary search */
    public static int BinarySearch(int[] list, int query){
        int lowerBound = 1;
        int upperBound = list.length - 1;
        
        int index = -1;
        
        while(lowerBound < upperBound){
            int middlePoint = (lowerBound + upperBound) / 2;
            
            if(query == list[middlePoint]){
                index = middlePoint;
                break;
            }else{
                if(query < list[middlePoint]){
                    upperBound = middlePoint - 1;
                }else{
                    lowerBound = middlePoint + 1;
                }
            }
        }
        if((lowerBound == upperBound) && (list[lowerBound] == query)){
            index = lowerBound;
        }
        return index;
    }
    public int BinarySearchRecursive(int [] arr, int query, int lower, int upper){
        
        int middle = (lower+upper)/2;
        if(lower == upper){
            if(query == arr[middle]){
                return middle; 
            }else{
                return -1;
            }
        }else{
            if(query == arr[middle]){
                return middle;
            }else{
                if(query < arr[middle]){
                    return BinarySearchRecursive(arr, query, lower, middle);
                }else{
                    return BinarySearchRecursive(arr, query, middle + 1, upper);
                }
            }
        }
        
    }
    public int [] bigArray(int size){
        Random rd = new Random();  
        int [] temp = new int[size];
        
        for(int i = 0; i < size; i++){
            temp[i] = rd.nextInt(100000);
        }
        return temp;
    }
    
    public int[] nonRepArray(int size){
        int[] arr = new int[size];
        for(int i = 0; i < size; i++){
            arr[i] = i;
        }
        // Fisher-Yates shuffle
        int n = arr.length;
        Random random = new Random();

        for (int i = 0; i < arr.length; i++) {
            int randomValue = i + random.nextInt(n - i);

            int randomElement = arr[randomValue];
            arr[randomValue] = arr[i];
            arr[i] = randomElement;
        }
        
        return arr;
    }
    
    public void printArray(int [] array) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 0; i < array.length; i++){
            bw.write(array[i] + " ");
        }
        bw.write("\n");
        bw.flush();
    }
    
    public static void main(String[] args) throws IOException {
        Sorting s = new Sorting();
        int [] test = s.bigArray(100000);
        int [] testB = test;
        
        int [] testN = s.nonRepArray(100000);
        System.out.println("RepNumbers");
        s.printArray(test);
        
        long startTime = System.currentTimeMillis();
        
        s.countingSort(test); // time : 0
        
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Counting: " + elapsedTime);
        s.printArray(test);
        
        startTime = System.currentTimeMillis();
        
        s.mergeSort(testB);
        
        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        System.out.println("Merge: " + elapsedTime);
        s.printArray(test);
        
        // for non repeating arrays  
        System.out.println("NonRep Nums");
        s.printArray(testN);
        startTime = System.currentTimeMillis();
        
        testN = s.mergeSort(testN);
        
        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        System.out.println("Merge non rep: " + elapsedTime);
        s.printArray(testN);
        
        // Binary search 
        startTime = System.currentTimeMillis();
        
        System.out.println("index:" + BinarySearch(testN, 10));
        
        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        System.out.println("Binary Search : " + elapsedTime);

    }
}

