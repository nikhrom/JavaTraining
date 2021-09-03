package core.threads.lesson25.practice;

import java.util.Queue;

public class ConsumerThread extends Thread{

    private final Queue<Integer> list;

    public ConsumerThread(Queue<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        synchronized (list){
            while(true){
                if(!list.isEmpty()){
                    Integer removedValue = list.remove();
                    System.out.println("consumer get value: " + removedValue + ". Size " + list.size());
                }else{
                    System.out.println("consumer is waiting, list is empty");
                }

                try{
                    list.wait(10L);
                    System.out.println("consumer wait: " + 10L);
                }catch (InterruptedException exception){
                    exception.printStackTrace();
                }
            }


        }
    }

}
