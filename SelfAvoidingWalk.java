import javax.swing.*;
import java.awt.*;

public class SelfAvoidingWalk extends JFrame {
    private final int gridSize = 20; // Size of each cell in the grid
    private final int numCells = 30; // Number of cells in each row/column
    private boolean[][] visited;
    private int currentX;
    private int currentY;

    public SelfAvoidingWalk() {
        visited = new boolean[numCells][numCells];
        currentX = numCells / 2;
        currentY = numCells / 2;

        setTitle("Self-Avoiding Walk");
        setSize(numCells * gridSize, numCells * gridSize);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);

        // Draw the self-avoiding walk
        for (int x = 0; x < numCells; x++) {
            for (int y = 0; y < numCells; y++) {
                if (visited[x][y]) {
                    int posX = x * gridSize;
                    int posY = y * gridSize;
                    g2d.fillRect(posX, posY, gridSize, gridSize);
                }
            }
        }
    }

    public void move() {
        visited[currentX][currentY] = true;

        // Randomly choose the next direction
        int direction = (int) (Math.random() * 4); // 0: up, 1: right, 2: down, 3: left

        // Move to the next cell in the chosen direction, if possible
        switch (direction) {
            case 0: // Up
                if (currentY > 0 && !visited[currentX][currentY - 1]) {
                    currentY--;
                }
                break;
            case 1: // Right
                if (currentX < numCells - 1 && !visited[currentX + 1][currentY]) {
                    currentX++;
                }
                break;
            case 2: // Down
                if (currentY < numCells - 1 && !visited[currentX][currentY + 1]) {
                    currentY++;
                }
                break;
            case 3: // Left
                if (currentX > 0 && !visited[currentX - 1][currentY]) {
                    currentX--;
                }
                break;
        }

        repaint(); // Redraw the grid
    }

    public static void main(String[] args) {
        SelfAvoidingWalk walk = new SelfAvoidingWalk();
        walk.setVisible(true);

        // Simulate the self-avoiding walk
        int numSteps = walk.numCells * walk.numCells;
        for (int i = 0; i < numSteps; i++) {
            walk.move();
            try {
                Thread.sleep(100); // Delay between each step (100 milliseconds)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

