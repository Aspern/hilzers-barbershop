package de.hska.parsys.barbershop.objects;

import de.hska.parsys.barbershop.participant.Customer;

import java.util.concurrent.ArrayBlockingQueue;

public class Sofa extends ArrayBlockingQueue<Customer> {

    public Sofa() {
        super(4, true);
    }

}
