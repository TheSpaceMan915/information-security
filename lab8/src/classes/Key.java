package classes;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class Key {
    private final int firstNumber;
    private final int secondNumber;

    @Override
    public String toString() {
        return String.format("number: %s, N: %s %n", firstNumber, secondNumber);
    }
}
