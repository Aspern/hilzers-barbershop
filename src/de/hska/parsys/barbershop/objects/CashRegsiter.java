package de.hska.parsys.barbershop.objects;

import de.hska.parsys.barbershop.participant.Barber;
import de.hska.parsys.barbershop.participant.Customer;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CashRegsiter {

    private final Lock lock = new ReentrantLock(true);

    public void acceptPayment(Barber barber, Customer customer) {
        try {
            lock.lock();
            System.out.println("Accepted money [" + customer.pay() + "] from " + customer + " to " + barber);
        } finally {
            lock.unlock();
        }
    }

}
