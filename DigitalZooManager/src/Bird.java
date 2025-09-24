// NIM: 2111510141
// NAMA: Andri Septian Nugraha

public class Bird extends Animal {
    private boolean canFly;

    public Bird(String name, int age, boolean canFly) {
        super(name, age);
        this.canFly = canFly;
    }

    public boolean getCanFly() {
        return canFly;
    }

    public void setCanFly(boolean canFly) {
        this.canFly = canFly;
    }

    @Override
    public String makeSound() {
        return "The bird chirps.";
    }

    @Override
    public String getInfo() {
        String flyingStatus = canFly ? "Can Fly" : "Cannot Fly";
        return super.getInfo() + ", " + flyingStatus;
    }
}