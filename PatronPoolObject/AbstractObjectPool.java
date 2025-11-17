import java.util.*;

public abstract class AbstractObjectPool implements IObjectPool {
    protected int max;
    protected int min;
    protected int timeout;

    protected int totalCreated = 0;

    protected Queue<IPoolableObject> available = new LinkedList<>();
    protected IObjectFactory factory;

    public AbstractObjectPool(int min, int max, int timeout, IObjectFactory factory) {
        this.min = min;
        this.max = max;
        this.timeout = timeout;
        this.factory = factory;

        for (int i = 0; i < min; i++) {
            available.add(factory.createNew());
            totalCreated++;
        }
    }
}
