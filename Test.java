import java.util.ArrayList;
import java.util.Stack;

class TreeNode {

    int data;
    TreeNode left;
    TreeNode right;

}

class Pair {
    int left;
    int right;
    Pair(int l, int r){
        left = l;
        right = r;
    }
}

public class Test {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        int k = 9;
        TreeNode root = buildTree(arr, 0, 7);
        // printTree(root);
        ArrayList<Pair> res = pairSum(root, k);
        printPair(res);
    }

    public static ArrayList<Pair> pairSum(TreeNode root, int k){
        ArrayList<Pair> res = new ArrayList<Pair>();

        // Time Complexity - O(n)
        // Spack complexity - O(logn) - for the stacks

        boolean shouldRun = true;
        Stack<TreeNode> s1 = new Stack<TreeNode>();
        Stack<TreeNode> s2 = new Stack<TreeNode>();

        boolean runForward = true;
        boolean runBackward = true;

        TreeNode curr1 = root;
        TreeNode curr2 = root;
        TreeNode left = null;
        TreeNode right = null;

        while(shouldRun){
            if((s1.isEmpty() && curr1 == null)) break;
            if((s2.isEmpty() && curr2 == null)) break;
            while(runForward){
                if(curr1 == null){
                    left = s1.pop();
                    if(left.right != null){
                        curr1 = left.right;
                    }
                    break;
                }else {
                    s1.push(curr1);
                    curr1 = curr1.left;
                }
            }


            while(runBackward){
                if(curr2 == null){
                    right = s2.pop();
                    if(right.left != null){
                        curr2 = right.left;
                    }
                    break;
                }else {
                    s2.push(curr2);
                    curr2 = curr2.right;
                }
            }


            if(left == null || right == null){
                // compares objects not values 
                shouldRun = false;
                break;
            }else {
                if(left.data >right.data){
                    shouldRun = false;
                    break;
                }else if(left.data +right.data > k){
                    // let right run
                    runBackward = true;
                    runForward = false;
                }else if(left.data +right.data < k){
                    runForward = true;
                    runBackward = false;
                }else {
                    runForward = true;
                    runBackward = true;
                    res.add(new Pair(left.data, right.data));
                }
            }

        }


        return res;
    }


    public static TreeNode buildTree(int[] arr, int l, int r){
        if(l > r) {
            return null;
        }

        TreeNode root = new TreeNode();
        int mid = (l+r)/2;
        root.data = arr[mid];

        root.left = buildTree(arr, l, mid-1);
        root.right = buildTree(arr, mid+1, r);
        return root;
    }

    public static void printTree(TreeNode root){
        if(root == null) return;

        printTree(root.left);
        System.out.println(root.data);
        printTree(root.right);
    }

    public static void printPair(ArrayList<Pair> ap){

        for(int i=0; i<ap.size(); i++){
            System.out.println(ap.get(i).left+" -- "+ap.get(i).right);
        }

    }

}
