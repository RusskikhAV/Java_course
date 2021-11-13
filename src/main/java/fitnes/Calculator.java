package fitnes;

@FunctionalInterface
public interface Calculator<T> {

    double calculateFees(T clubID);

}
