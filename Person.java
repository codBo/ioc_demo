package DI_demo;

/**
 * Created by root on 8/16/17.
 */
public class Person implements Speakable {

    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public void speak(String message) {
        System.out.print(this.name + " say : " + message);
    }
}
