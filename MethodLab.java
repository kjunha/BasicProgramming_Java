import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MethodLab extends JPanel {

    ArrayList<String> instructions;

    public MethodLab() {
        instructions = new ArrayList<>();
        String filename = JOptionPane.showInputDialog("File Name?");
        File file = new File(filename);
        try {
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                instructions.add(s.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        for(int i = 0; i < instructions.size(); i++) {
            parseCommand(instructions.get(i),g);
        }
    }

    public void parseCommand(String command, Graphics g) {
        String[] input = command.split(" ");
        if(input[0].equals("COLOR")) {
            changeColor(Integer.parseInt(input[1]), Integer.parseInt(input[2]), Integer.parseInt(input[3]), g);
        } else if(input[0].equals("TRIANGLE")) {
            drawTriangle(Integer.parseInt(input[1]), Integer.parseInt(input[2]), Integer.parseInt(input[3]), Integer.parseInt(input[4]), Integer.parseInt(input[5]), Integer.parseInt(input[6]), g);
        } else if(input[0].equals("CIRCLE")) {
            drawCircle(Integer.parseInt(input[1]),Integer.parseInt(input[2]),Integer.parseInt(input[3]),g);
        }
    }

    public void changeColor(int red, int green, int blue, Graphics g) {
        g.setColor(new Color(red, green, blue));
    }

    public void drawTriangle(int x1, int y1, int x2, int y2, int x3, int y3, Graphics g) {
        int[] x_coordinate = {x1, x2, x3};
        int[] y_coordinate = {y1, y2, y3};
        g.drawPolygon(x_coordinate, y_coordinate, 3);
    }

    public void drawCircle(int xc, int yc, int r, Graphics g) {
        g.drawOval(xc - r, yc - r, r * 2, r *2);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200,200);
        frame.setContentPane(new MethodLab());
        frame.setVisible(true);
    }
}
