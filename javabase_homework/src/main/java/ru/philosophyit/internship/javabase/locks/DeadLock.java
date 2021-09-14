package ru.philosophyit.internship.javabase.locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLock {

    private final String name;
    private final ReentrantLock lock = new ReentrantLock();

    public DeadLock(String name) {
        this.name = name;
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        DeadLock first = new DeadLock("first"),
                second = new DeadLock("second");

        Future<?> firstFuture = executorService.submit(getRunnableInterruptibility(first, second)),
                secondFuture = executorService.submit(getRunnableInterruptibility(second, first));

        Thread.sleep(5000);
        firstFuture.cancel(true);
        secondFuture.cancel(true);
        executorService.shutdownNow();
    }

    private static Runnable getRunnable(DeadLock first, DeadLock second) {
        return () -> {
            try {
                first.callAnother(second);
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        };
    }

    private static Runnable getRunnableInterruptibility(DeadLock first, DeadLock second) {
        return () -> {
            try {
                first.callAnotherInterruptibility(second);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        };
    }

    void callAnother(DeadLock other) {
        System.out.println(Thread.currentThread().getName() + ": trying get locks for " + name + ", " + other.name);
        lock.lock();
        System.out.println(Thread.currentThread().getName() + ": " + name + " lock got");
        other.lock.lock();
        System.out.println(Thread.currentThread().getName() + ": " + other.name + " other lock got");
        lock.unlock();
        System.out.println(Thread.currentThread().getName() + ": " + name + " lock released");
        other.lock.unlock();
        System.out.println(Thread.currentThread().getName() + ": " + other.name + " other lock released");
        System.out.println("Both locks unlocked");
    }

    void callAnotherInterruptibility(DeadLock other) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + ": trying get locks for " + name + ", " + other.name);
        lock.lockInterruptibly();
        System.out.println(Thread.currentThread().getName() + ": " + name + " lock got");
        other.lock.lockInterruptibly();
        System.out.println(Thread.currentThread().getName() + ": " + other.name + " other lock got");
        lock.unlock();
        System.out.println(Thread.currentThread().getName() + ": " + name + " lock released");
        other.lock.unlock();
        System.out.println(Thread.currentThread().getName() + ": " + other.name + " other lock released");
        System.out.println("Both locks unlocked");
    }

}
