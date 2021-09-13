package ru.philosophyit.internship.javabase.locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class LiveLock {

    private final String name;
    private final ReentrantLock lock = new ReentrantLock();

    public LiveLock(String name) {
        this.name = name;
    }

    public static void main(String[] args) throws InterruptedException {
        LiveLock first = new LiveLock("first"),
                second = new LiveLock("second");

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<?> firstFuture = executorService.submit(getRunnable(first, second)),
                secondFuture = executorService.submit(getRunnable(second, first));

        Thread.sleep(5000);
        firstFuture.cancel(true);
        secondFuture.cancel(true);
        executorService.shutdownNow();
    }

    private static Runnable getRunnable(LiveLock first, LiveLock second) {
        return () -> {
            while (true) {
                try {
                    if (first.callAnother(second)) break;
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());

                    return;
                }
            }
        };
    }

    boolean callAnother(LiveLock other) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + ": trying get locks for " + name + ", " + other.name);
        if (!lock.tryLock(100, TimeUnit.MILLISECONDS)) {
            return false;
        }
        System.out.println(Thread.currentThread().getName() + ": " + name + " lock got");
        if (!other.lock.tryLock(100, TimeUnit.MILLISECONDS)) {
            return false;
        }
        System.out.println(Thread.currentThread().getName() + ": " + other.name + " OTHER LOCK GOT!!!");

        lock.unlock();
        System.out.println(Thread.currentThread().getName() + ": " + name + " lock released");
        other.lock.unlock();
        System.out.println(Thread.currentThread().getName() + ": " + other.name + " other lock released");
        System.out.println("Both locks unlocked");
        return true;
    }

}
