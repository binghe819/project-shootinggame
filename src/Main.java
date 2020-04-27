

public class Main {

    public static void main(String[] args){
        Game_View game_view = new Game_View();
        new Thread(game_view).start();
    }
}
