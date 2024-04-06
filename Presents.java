import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Presents {
    static int numberPresents = 500000;
    static int numberServants = 4;
    static ArrayList<Integer> bag = new ArrayList<>();        //create the unordered bag
    //static ConcurrentLinkedDeque<Integer> list = new ConcurrentLinkedDeque<>();
    static ArrayList<Integer> list = new ArrayList<>();  
    static int thankYous = 0;      
    static int presentsCollected = 0;

    static boolean[] presentsReceived = new boolean[numberPresents]; // to keep track of presents received

    static Random random = new Random();


    public static void main(String[] args) throws InterruptedException {

        Thread[] threads = new Thread[numberServants];


        // Fill the bag with tags from 1 to 500000
        for (int i = 1; i <= 500000; i++) {
            bag.add(i);
        }

        // Shuffle the tags
        Collections.shuffle(bag);

        for (int i = 0; i < numberServants; i++) {
            threads[i] = new Thread(Presents::startServing);
            threads[i].start();
        }

        // Join threads
        for (Thread thread : threads) {
            thread.join();
        }

        // Print final statistics
        System.out.println("There are " + thankYous + " thank you cards.");

        //end threads bc all thankyou notes are done
        
    }

    public static void sortPresent() {
        synchronized (bag) {
            if (!bag.isEmpty()) {
                int present = bag.remove(0);
                // Place present into the list
                list.add(present);
                Collections.sort(list);
            }
        }
    }
    public static void writeThankYou() {
        synchronized (list) {
            if (!list.isEmpty()) {
                int present = list.remove(0);
                presentsReceived[present-1] = true;
                thankYous++;
                //write thank you
            }
        }
    }
    public static boolean checkTag(int tag) {

        for(int i=0;i<list.size();i++){
            if(tag == list.get(i)){
                return true;
            }
        }

        return false;
    }
    public static void startServing(){
        while( thankYous < 500000){
            int randomNumber = random.nextInt(2) + 1;
            if(randomNumber == 1){
                sortPresent();
                writeThankYou();
            }
            //simulate the minotaur asking about a particular tag
            else if(randomNumber == 2){
                //simulate the random tag number
                int tagNumber = random.nextInt(50000) + 1; // Generates random number between 0 (inclusive) and 50000 (exclusive), then adds 1

                if(checkTag(tagNumber) == true){
                    System.out.println("Present " + tagNumber + " is in the list");
                }
                else{
                    System.out.println("Present " + tagNumber + " is not in the list");
                }
            }
        }
    }
}
