package core.chapter14.list5;
/**
 *
 * @author zenghh
 * @email 625111833@qq.com
 * @date 2020/6/2 5:27 下午
 * @version 1.0
 */
public class UnsynchBank {
    public static final int NACCOUNTS = 100;
    public static final double INITIAL_BALANCE =1000;
    public static final double MAX_AMOUNT = 1000;
    public static final int DELAY = 10;

    public static void main(String[] args) {
        Bank bank = new Bank(NACCOUNTS,INITIAL_BALANCE);
        for (int i = 0; i < NACCOUNTS; i++) {
            int fromAccount = i;
            Runnable r = () ->{
                try {
                    while (true){
                        int toAccount = (int)(bank.size()*Math.random());
                        double amount = MAX_AMOUNT * Math.random();
                        bank.transfer(fromAccount,toAccount,amount);
                        Thread.sleep((int) (DELAY * Math.random()));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            Thread t = new Thread(r);
            t.start();
        }
    }
}
