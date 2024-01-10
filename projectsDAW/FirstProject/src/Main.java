public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello World!");
        //happense-before
        new SayHi().start();
        for(int i = 0; i != 10; i++) {
            System.out.println("And another time HI!");
            Thread.sleep(300);
        }
    }
    public static class SayHi extends Thread {
        @Override
        public void run() {
            for (char item : "Hello Juan".toCharArray()) {
                System.out.println(item);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.getStackTrace();
                }
            }
        }
    }
}
