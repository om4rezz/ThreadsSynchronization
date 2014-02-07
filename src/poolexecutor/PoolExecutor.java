/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poolexecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author om4rezz
 */
public class PoolExecutor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // create a fixed thread pool with maximum three threads
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // Submit runnable tasks ti the executor
        executorService.execute(new PrintChar('a', 100));
        executorService.execute(new PrintChar('b', 100));
        executorService.execute(new PrintNum(100));

        // Shut down the executor
        executorService.shutdown();

    }

}

// The task for printing a character a specified number of times
class PrintChar implements Runnable {

    private char charToPrint; // The character to print
    private int times; // The number of times to repeat

    /**
     * Construct a task with specified character and number of times to print
     * the character
     */
    public PrintChar(char c, int t) {
        charToPrint = c;
        times = t;
    }

    @Override
    /**
     * Override the run() method to tell the system what task to perform
     */
    public void run() {
        for (int i = 0; i < times; i++) {
            try {
                System.out.print(charToPrint);
                Thread.sleep(2);
            } catch (InterruptedException ex) {
                Logger.getLogger(PrintChar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

// The task class for printing numbers from 1 to n for a given n
class PrintNum implements Runnable {

    private int lastNum;

    /**
     * Construct a task for printing 1, 2, ..., n
     */
    public PrintNum(int lastNum) {
        this.lastNum = lastNum;
    }

    @Override
    /**
     * Tell the thread how to run
     */
    public void run() {
        for (int i = 1; i <= lastNum; i++) {
            try {
                System.out.print(" " + i);
                Thread.yield();
                Thread.sleep(2);
            } catch (InterruptedException ex) {
                Logger.getLogger(PrintNum.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
