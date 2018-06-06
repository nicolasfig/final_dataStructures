public class HanoiTowers{
    public static void solveTowers(int disks, int sourcePeg, int destinationPeg, int tempPeg){
        // base case only one disk to move
        if(disks == 1){
            System.out.printf("\n%d ---> %d",sourcePeg, destinationPeg);
            return;
        }
        // recursion step -- move (disk - 1) from sourcePeg to tempPeg using
        // destinationPeg
        solveTowers(disks - 1, sourcePeg, tempPeg, destinationPeg);

        // move the last disk from sourcepeg to destinationPeg
        System.out.printf("\n%d ---> %d",sourcePeg,destinationPeg);

        // move (disks - 1) from tempPeg to destinationPeg
        solveTowers(disks - 1, tempPeg, destinationPeg,sourcePeg);
    }

    public static void main(String[]args){
        int startPeg = 1;
        int tempPeg = 2;
        int endPeg = 3;
        int totalDisks = 5;

        // Initial non-recursive call: move all disks
        HanoiTowers.solveTowers(totalDisks, startPeg, endPeg, tempPeg);
    }
}	
