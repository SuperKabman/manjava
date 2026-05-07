package manjava;

public class MancalaTester {
    public static void main(String[] args) {
        MancalaGameModel model = new MancalaGameModel();
        MancalaView view = new MancalaView();

        MancalaController controller = new MancalaController(model, view);

        view.setVisible(true);
    }
}
