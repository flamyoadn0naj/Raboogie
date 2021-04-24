
public class Soldier extends Sprite {
	public static final String[] name = {"Resources Root/Soldier/g_up", "Resources Root/Soldier/g_dn", "Resources Root/Soldier/g_lt", "Resources Root/Soldier/g_rt"};
	
	public Soldier(int x, int y, int action) {
		super(x, y, 80, 118, action, name, 9, 5, "gif");
	}

}