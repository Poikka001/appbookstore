package mate.academy.intro.exeption;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String messare) {
        super(messare);
    }
}
