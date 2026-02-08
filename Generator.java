import java.util.ArrayList;
import java.util.Random;





/*
Ayla Ismayilzada, CSCI-3612 - 20966

This class generates random numbers using two different generators,
calculates statistics (n, mean, stddev, min, max), and displays results in a table.
*/

public class Generator {
    

/* 
    first step is to create a method that will create 
    n random values (double) in range [0,1)
    to do so, I will implement java Random and specifically,   
*/
public static final int JAVA_RANDOM = 1;
public static final int MATH_RANDOM = 2;


// numbering the generators will make it easier to implement

/*

to get information on java.util.Random, I used - 
https://www.geeksforgeeks.org/java/java-util-random-class-java/

*/

public ArrayList<Double> populate(int n, int randNumGen){ 
    /* 
    n is the quantity of numbers that we want, 
    randNumGe tells the method which random generator to use
    */ 

    ArrayList<Double> list = new ArrayList<>();  // an empty list in which we will store the random numbers
    Random r = new Random(); // obj instantiation 

    for (int i=0; i < n; i++){    
        double value;

        if (randNumGen == JAVA_RANDOM) {
                value = r.nextDouble();
            } else {
                value = Math.random();
            } 
            list.add(value);
    }
    return list;
    /*

    to check if the method is working i used: 

    public static void main(String[] args) {
    Generator g = new Generator();
    System.out.println(g.populate(5, MATH_RANDOM));
     }

     */

}



public ArrayList<Double> statistics(ArrayList<Double> values){ 
  
    ArrayList<Double> result = new ArrayList<>();

    double n = values.size();
    double sum = 0;
    double min = values.get(0);
    double max = values.get(0);

    // this for loop calculates sum, min, max 
    for (int i = 0; i < n; i++) {
        double num = values.get(i);
        sum += num;
        // simple comparison logic 
        if (num < min) min = num; 
        if (num > max) max = num;
    }

    double mean = sum / n;

    /* the formula for std is sqrt of (elements - mean)^2 / n-1 */

    double stdSum = 0;
    for (int i = 0; i < n; i++) {
        double num = values.get(i);
        stdSum += (num - mean) * (num - mean);
    }
    
    double stdDiv = Math.sqrt(stdSum/(n-1));

    // add to the list of results

    result.add(n);
    result.add(mean);
    result.add(stdDiv);
    result.add(min);
    result.add(max);

    return result;
}
    



// Now, when both method are ready, we need to work on  display


public void display(ArrayList<Double> results, boolean headerOn) {
    if (headerOn) {
        System.out.println("n\tMean\t\t\tStdDev\t\t\tMin\t\t\tMax");
    }

    System.out.println(
        results.get(0).intValue() + "\t" +
        results.get(1) + "\t" +
        results.get(2) + "\t" +
        results.get(3) + "\t" +
        results.get(4)
    );
}


public void execute() {

    int[] sizes = {10, 1000, 100000}; // sample sizes 
    int[] gens = {JAVA_RANDOM, MATH_RANDOM};

    for (int g = 0; g < gens.length; g++) { // g is the index of the generator in gens
        System.out.println("\nGenerator type: " + gens[g]);
        boolean header = true;

        for (int s = 0; s < sizes.length; s++) { // s is the index of the current sample size 
            ArrayList<Double> nums = populate(sizes[s], gens[g]);
            ArrayList<Double> stats = statistics(nums);
            display(stats, header);
            header = false;
        }
    }
}

public static void main(String[] args) {
    Generator g = new Generator();
    g.execute();
}


//  public static void main(String[] args) {
//         Generator g = new Generator();

//         // generate 5 random numbers
//         ArrayList<Double> nums = g.populate(5, MATH_RANDOM);
//         System.out.println("Random numbers: " + nums);

//         // calculate statistics
//         ArrayList<Double> stats = g.statistics(nums);
//         System.out.println("Statistics:");
//         for (int i = 0; i < stats.size(); i++) {
//             System.out.println(stats.get(i));
//         }
//     }

}



