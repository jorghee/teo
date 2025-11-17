public class ConcreteObjectFactory implements IObjectFactory {
    @Override
    public IPoolableObject createNew() {
        return new ConcretePoolableObject();
    }
}