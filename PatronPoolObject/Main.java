public class Main {
    public static void main(String[] args) {

        System.out.println("===== EJEMPLO 1: FUNCIONAMIENTO NORMAL =====");
        try {
            IObjectFactory factory1 = new ConcreteObjectFactory();
            IObjectPool pool1 = new ConcreteObjectPool(2, 3, 2000, factory1);

            System.out.println("Solicitando obj1");
            IPoolableObject obj1 = pool1.getObject();

            System.out.println("Solicitando obj2");
            IPoolableObject obj2 = pool1.getObject();

            System.out.println("Solicitando obj3");
            IPoolableObject obj3 = pool1.getObject();

            System.out.println("Liberando obj1");
            pool1.releaseObject(obj1);

            System.out.println("Solicitando nuevamente un objeto (reuse)");
            IPoolableObject obj4 = pool1.getObject();
            obj4.operation();

        } catch (Exception e) {
            System.out.println("EXCEPCIÓN en ejemplo 1: " + e.getMessage());
        }


        System.out.println("\n===== EJEMPLO 2: TIMEOUT POR SUPERAR MAX =====");
        try {
            IObjectFactory factory2 = new ConcreteObjectFactory();
            IObjectPool pool2 = new ConcreteObjectPool(2, 3, 2000, factory2);

            System.out.println("Solicitando obj1");
            IPoolableObject a1 = pool2.getObject();

            System.out.println("Solicitando obj2");
            IPoolableObject a2 = pool2.getObject();

            System.out.println("Solicitando obj3");
            IPoolableObject a3 = pool2.getObject();

            System.out.println("Intentando solicitar obj4 (supera max, debe esperar y luego timeout)");
            IPoolableObject a4 = pool2.getObject(); 

        } catch (Exception e) {
            System.out.println("EXCEPCIÓN en ejemplo 2: " + e.getMessage());
        }
    }
}
