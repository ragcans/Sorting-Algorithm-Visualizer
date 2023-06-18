package sorting;


public class test {
    public static void main(String[] args) {
        Algorithms alg = new Algorithms();

        int[] arr = {2,0,435,3243,123,503,50,1,30,405,139,23,25,93,194,51,395};

        arr = alg.MergeSort(arr);

        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
