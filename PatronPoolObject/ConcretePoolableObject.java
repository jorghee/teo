public class ConcretePoolableObject implements IPoolableObject {
    @Override
    public void operation() {
        System.out.println("Operación ejecutada por un objeto del pool.");
    }
}