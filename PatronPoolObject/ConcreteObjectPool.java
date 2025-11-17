import java.util.*;

public class ConcreteObjectPool extends AbstractObjectPool {

    public ConcreteObjectPool(int min, int max, int timeout, IObjectFactory factory) {
        super(min, max, timeout, factory);
    }

    @Override
    public synchronized IPoolableObject getObject() throws InterruptedException {
        long start = System.currentTimeMillis();

        while (available.isEmpty()) {
            if (totalCreated >= max) {
                long elapsed = System.currentTimeMillis() - start;

                if (elapsed >= timeout) {
                    throw new RuntimeException("TIMEOUT: No hay objetos disponibles en el pool.");
                }

                wait(timeout - elapsed);
            } else {
                IPoolableObject newObj = factory.createNew();
                totalCreated++;
                return newObj;
            }
        }

        return available.poll();
    }

    @Override
    public synchronized void releaseObject(IPoolableObject obj) {
        available.offer(obj);
        notify();
    }
}
