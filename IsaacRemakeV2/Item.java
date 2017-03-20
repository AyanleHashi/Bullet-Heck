public interface Item {
    Player player = new Player();
    double x = 0;
    double y = 0;

    void modifier();
    void draw(double x, double y);
    void pickup(double x, double y);
}
