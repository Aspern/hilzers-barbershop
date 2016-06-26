package de.hska.parsys.barbershop;

import de.hska.parsys.barbershop.objects.CashRegsiter;
import de.hska.parsys.barbershop.participant.Man;
import de.hska.parsys.barbershop.participant.Woman;
import de.hska.parsys.barbershop.objects.Sofa;
import de.hska.parsys.barbershop.objects.StandingRoom;
import de.hska.parsys.barbershop.participant.Barber;
import de.hska.parsys.barbershop.participant.Customer;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class Barbershop implements Runnable {

    private static final int NUM_BARBERS = 3;

    private final AtomicLong idGenerator = new AtomicLong(0);
    private final ScheduledExecutorService threadpool = Executors.newScheduledThreadPool(5);
    private final Sofa sofa = new Sofa();
    private final StandingRoom standingRoom = new StandingRoom();
    private final CashRegsiter cashRegsiter = new CashRegsiter();


    @Override
    public void run() {
        for (long i = 1; i <= NUM_BARBERS; i++) {
            threadpool.execute(new Barber(i, sofa, cashRegsiter));
        }

        //New Customer enters the barbershop every 2 seconds (Shop is very famous).
        threadpool.scheduleAtFixedRate(() -> {
            try {
                //enterShop()
                Customer customer = nextCustomer();
                if (standingRoom.remainingCapacity() > 0) {
                    System.out.println(customer + " enters the shop and waits in standing room.");
                    standingRoom.add(customer);
                } else {
                    System.err.println("Barbershop is full and " + customer + " leaves.");
                }

                if (sofa.remainingCapacity() > 0) {
                    Customer standingCustmer = standingRoom.take();
                    System.out.println(standingCustmer + " sits down on sofa.");
                    //sitOnSofa
                    sofa.add(standingCustmer);
                }
            } catch (IllegalStateException e) {
                System.err.println("Sofa is full!");
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
        }, 0, 2, TimeUnit.SECONDS);
    }

    private Customer nextCustomer() {
        final boolean random = ThreadLocalRandom.current().nextBoolean();
        final long id = idGenerator.incrementAndGet();
        if (random) {
            return new Man(id);
        }
        return new Woman(id);
    }

    public static void main(String[] args) {
        new Thread(new Barbershop()).run();
    }
}
