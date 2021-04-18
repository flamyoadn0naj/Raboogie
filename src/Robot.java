public class Robot extends Sprite {
    public static final String[] name = {"Resources Root/Robot/Jump", "Resources Root/Robot/Slide", "Resources " +
            "Root/Robot/Idle", "Resources Root/Robot/Run"};

    public Robot(double x, double y, int action) {
        super(x, y,80 ,118 , action, name, 9, 10, "png");
    }
}