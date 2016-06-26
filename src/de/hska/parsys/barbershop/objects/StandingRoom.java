package de.hska.parsys.barbershop.objects;


import de.hska.parsys.barbershop.participant.Customer;

import java.util.concurrent.ArrayBlockingQueue;

public class StandingRoom extends ArrayBlockingQueue<Customer> {

    public StandingRoom() {
        super(16, true);
    }

}
