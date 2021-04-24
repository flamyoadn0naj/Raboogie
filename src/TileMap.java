import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TileMap {
    String[] map;

    Image[]  tile;
    String[] tile_name;

    Image    background;
    String   background_name;

    int S;


    public TileMap(String[] map, Image[] tile, Image background, int scale)
    {
        this.map        = map;
        this.tile       = tile;
        this.background = background;

        S = scale;
    }
/*
    public TileMap(String filename, int scale)
    {
        loadMap(filename);

        loadAssets();

        S = scale;
    }

 */
/*
    public void loadMap(String filename)
    {
        File file = new File(filename);

        try
        {
            BufferedReader input = new BufferedReader(new FileReader(file));

            int n = Integer.parseInt(input.readLine());  // How many rows in the map?

            map = new String[n];

            for(int row = 0; row < n; row++)
            {
                map[row] = input.readLine();
            }

            n = Integer.parseInt(input.readLine());     // How many tiles?

            tile_name = new String[n];

            for(int i = 0; i < n; i++)
            {
                tile_name[i] = input.readLine();
            }

            background_name = input.readLine();

            input.close();
        }
        catch(IOException x) {};
    }

    public void loadAssets()
    {
        tile      = new Image[tile_name.length];

        for(int i = 0; i < tile.length; i++)
        {
            tile[i] = getImage(tile_name[i]);
        }

        background = getImage(background_name);
    }

 */

    public boolean clearAbove(Robot r)
    {
        int top    = r.y;
        int left   = r.x;
        int right  = r.x + S-1;

        return (valueAt(top-S/4, left) == '.')  && (valueAt(top-S/4, right) == '.');
    }

    public boolean clearBelow(Robot r)
    {
        int bottom = r.y + S-1;
        int left   = r.x;
        int right  = r.x + S-1;

        return  (valueAt((int) (bottom+r.vy+1), left) ==  '.')  && (valueAt((int) (bottom+r.vy+1), right) == '.');
    }

    public boolean clearLeftOf(Robot r)
    {
        int top    = r.y;
        int bottom = r.y + S-1;
        int left   = r.x;

        return (valueAt(top, left-S/8) == '.')  && (valueAt(bottom, left-S/8) == '.');
    }

    public boolean clearRightOf(Robot r)
    {
        int top    = r.y;
        int bottom = r.y + S-1;
        int right  = r.x + S-1;

        return (valueAt(top, right+S/8) == '.')  && (valueAt(bottom, right+S/8) == '.');
    }

    public char valueAt(int y, int x)
    {
        int row = y / S;
        int col = x / S;

        return map[row].charAt(col);
    }

 //*/


    public void draw(Graphics g)
    {
        g.drawImage(background, 0, 0, null);

        for(int row = 0; row < map.length; row++)
        {
            for(int col = 0; col < map[row].length(); col++)
            {
                char c = map[row].charAt(col);

                if(c != '.')

                    g.drawImage(tile[c - 'A'], S*col - Camera.x + Camera.x_origin, S*row - Camera.y + Camera.y_origin, S, S, null);
            }
        }

    }


    public Image getImage(String filename)
    {
        return Toolkit.getDefaultToolkit().getImage(filename);
    }
}
