import java.awt.*;


public class Tile {

    private int val;

    /**
     * Constructor for a tile with a predetermined value
     * @param val -> value of tile
     */
    public Tile(int val) {
        this.val =  val;
    }

    /**
     * Constructor for a tile without a predetermined value
     */
    public Tile() {
        if (Math.random() < 0.5) {
            val = 2;
        } else {
            val = 4;
        }
    }

    /**
     * Get the value of the tile
     * @return value of tile
     */
    public int getVal() {
        return val;
    }

    /**
     * Doubles the value and sets to merged
     */
    public int doubleVal() {
        val *= 2;
        return val;
    }

    /**
     * Draw the tile
     * @param g -> graphics
     * @param x -> x-coordinate
     * @param y -> y-coordinate
     */
    public void draw(Graphics g, int x, int y) {
        g.setFont(new Font("Serif", Font.BOLD, 30));
        switch (val) {
            case 0:
                g.setColor(Color.WHITE);
                g.fillRect(x, y, 100, 100);
                break;
            case 2:
                g.setColor(new Color(238, 228, 218));
                g.fillRect(x, y, 100, 100);
                g.setColor(new Color(119, 110, 101));
                g.drawString("2", x + 42, y + 57);
                break;
            case 4:
                g.setColor(new Color(237, 224, 200));
                g.fillRect(x, y, 100, 100);
                g.setColor(new Color(119, 110, 101));
                g.drawString("4", x + 42, y + 57);
                break;
            case 8:
                g.setColor(new Color(242, 177, 121));
                g.fillRect(x, y, 100, 100);
                g.setColor(Color.WHITE);
                g.drawString("8", x + 42, y + 57);
                break;
            case 16:
                g.setColor(new Color(245, 149, 99));
                g.fillRect(x, y, 100, 100);
                g.setColor(Color.WHITE);
                g.drawString("16", x + 35, y + 57);
                break;
            case 32:
                g.setColor(new Color(246, 124, 96));
                g.fillRect(x, y, 100, 100);
                g.setColor(Color.WHITE);
                g.drawString("32", x + 35, y + 57);
                break;
            case 64:
                g.setColor(new Color(246, 94, 59));
                g.fillRect(x, y, 100, 100);
                g.setColor(Color.WHITE);
                g.drawString("64", x + 35, y + 57);
                break;
            case 128:
                g.setColor(new Color(237, 207, 115));
                g.fillRect(x, y, 100, 100);
                g.setColor(Color.WHITE);
                g.drawString("128", x + 28, y + 57);
                break;
            case 256:
                g.setColor(new Color(237, 204, 98));
                g.fillRect(x, y, 100, 100);
                g.setColor(Color.WHITE);
                g.drawString("256", x + 28, y + 57);
                break;
            case 512:
                g.setColor(new Color(237, 200, 80));
                g.fillRect(x, y, 100, 100);
                g.setColor(Color.WHITE);
                g.drawString("512", x + 28, y + 57);
                break;
            case 1024:
                g.setColor(new Color(237, 197, 63));
                g.fillRect(x, y, 100, 100);
                g.setColor(Color.WHITE);
                g.drawString("1024", x + 21, y + 57);
                break;
            case 2048:
                g.setColor(new Color(237, 194, 45));
                g.fillRect(x, y, 100, 100);
                g.setColor(Color.WHITE);
                g.drawString("2048", x + 21, y + 57);
                break;
            default:
                g.setColor(new Color(237, 194, 45));
                g.fillRect(x, y, 100, 100);
                g.setColor(Color.WHITE);
                g.drawString(Integer.toString(val), x + 21, y + 57);
                break;
        }
    }

    /**
     * Override equals method
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Tile) {
            Tile tile = (Tile) other;
            return this.val == tile.getVal();
        }
        return false;
    }
}