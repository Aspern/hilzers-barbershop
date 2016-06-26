package de.hska.parsys.barbershop.participant;

import de.hska.parsys.barbershop.objects.CashRegsiter;
import de.hska.parsys.barbershop.objects.Sofa;

public class Barber implements Runnable {

    private final long id;

    private final Sofa sofa;
    private final CashRegsiter cashRegsiter;

    public Barber(long id, Sofa sofa, CashRegsiter cashRegsiter) {
        this.sofa = sofa;
        this.cashRegsiter = cashRegsiter;
        this.id = id;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(this + " sits in the chair waiting for a customer...");
            try {
                Customer customer = sofa.take();
                customer.getHairCut(this);
                cashRegsiter.acceptPayment(this, customer);
                System.out.println(customer + " leaves barbershop.");
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }


    @Override
    public String toString() {
        return "Barber[" + id + "]";
    }

}
