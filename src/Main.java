

public class Main {

    public static void main(String[] args){
        Game_Model model = new Game_Model();
        Game_Control controller = new Game_Control(model);
        Game_View view = new Game_View(model, controller);
    }
}
