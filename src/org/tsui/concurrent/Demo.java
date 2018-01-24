package org.tsui.concurrent;

public class Demo {
    public static void main(String[] args) {
        ConcurrentService service = new ConcurrentService();
        service.start(1000);

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    int percentage = service.getPercentage();
                    //System.out.println("当前进度：" + percentage);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
