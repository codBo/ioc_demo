package DI_demo;

/**
 * Created by brian on 2017/8/19.
 */
public class Person1 implements Speakable{

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
