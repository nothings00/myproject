package core.chapter14.list7;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author zenghh
 * @email 625111833@qq.com
 * @date 2020/6/2 5:29 下午
 * @version 1.0
 */
public class BankSyn {

    //ReentrantLock implements the Lock interface
    private Lock bankLock = new ReentrantLock();
    private final double[] accounts;
    private Condition sufficientFunds;


    public BankSyn(int n, double initialBalance){
        accounts = new double[n];
        Arrays.fill(accounts,initialBalance);
        sufficientFunds = bankLock.newCondition();
    }

    public void transfer(int from, int to, double amount) throws InterruptedException {
        bankLock.lock();
        try {
            while (accounts[from] < amount) {
                sufficientFunds.await();
            }
            System.out.print(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf(" %10.2f from %d to %d ",amount,from,to);
            accounts[to] += amount;
            System.out.printf(" Total Balance: %10.2f%n",getTotalBalance());
            sufficientFunds.signalAll();
        }finally {
            bankLock.unlock();
        }

    }

    public double getTotalBalance(){
        bankLock.lock();
        try {
            double sum = 0;
            for (double a : accounts){
                sum += a;
            }
            return sum;
        }finally {
            bankLock.unlock();
        }

    }

    public int size(){
        return accounts.length;
    }
}
