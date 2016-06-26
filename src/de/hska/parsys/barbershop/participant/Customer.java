package de.hska.parsys.barbershop.participant;

public abstract class Customer {

    private final long id;
    private final String name;

    Customer(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Customer " + name + "[" + id + "]";
    }

    public abstract void getHairCut(Barber barber);

    public abstract double pay();
}
