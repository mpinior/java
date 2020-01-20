public class Elevator {
    // tworzymy 3 wątki
    static ElevatorCar car = new ElevatorCar();
    static ExternalPanelsAgent externalPanelAgent = new ExternalPanelsAgent(car);
    static InternalPanelAgent internalPanelAgent = new InternalPanelAgent(car);

    // symulacja przywołania windy z panelu zewnętrznego
    static void makeExternalCall(int atFloor,boolean directionUp){
        try {
            externalPanelAgent.input.put(new ExternalPanelsAgent.ExternalCall(atFloor,directionUp));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // symulacja wyboru pietra w panelu wewnętrznym
    static void makeInternalCall(int toFloor){
        try {
            internalPanelAgent.input.put(new InternalPanelAgent.InternalCall(toFloor));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // uruchomienie wątków
    static void init(){
        car.start();
        externalPanelAgent.start();
        internalPanelAgent.start();
    }

    // miesjce na kod testowy
    public static void main(String[] args) throws InterruptedException {
        init();
//        makeExternalCall(4,false);
//        Thread.currentThread().sleep(100);
//        makeInternalCall(2);

        //1
        makeExternalCall(2,true);
        Thread.currentThread().sleep(100);
        makeInternalCall(9);
        makeExternalCall(1,false);
        makeExternalCall(8,false);
        Thread.currentThread().sleep(100);

        //2
//        makeExternalCall(0,true);
//        makeInternalCall(2);
//        makeExternalCall(4,true);
//        Thread.currentThread().sleep(100);
//        makeInternalCall(0);

        //3
//        makeExternalCall(8,false);
//        Thread.currentThread().sleep(100);
//        makeInternalCall(3);

        //4
//        makeExternalCall(2, true);
//        Thread.currentThread().sleep(100);
//        makeInternalCall(3);
//        Thread.currentThread().sleep(100);
//        makeExternalCall(6,true);

        //5
//        makeExternalCall(7,false);
//        Thread.currentThread().sleep(5000);
//        makeInternalCall(2);
//        makeInternalCall(5);
//        Thread.currentThread().sleep(1000);
//        makeExternalCall(2,false);
    }

    static void printTab(boolean[] a){
        for (int i = 0; i < a.length; i++) {
            System.out.print(" "+a[i]);
        }
        System.out.println();
    }
}