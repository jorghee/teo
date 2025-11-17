public interface IObjectPool {
    IPoolableObject getObject() throws InterruptedException;
    void releaseObject(IPoolableObject obj);
}
