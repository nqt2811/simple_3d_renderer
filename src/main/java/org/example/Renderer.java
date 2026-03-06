package org.example;

import javax.swing.*;
import java.awt.*;

public class Renderer extends JPanel {
    private Mesh cube = Mesh.createCube();
    private double angle = 0;

    public Renderer() {
        Timer timer = new Timer(16, e -> {
            angle += 0.015;
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());

        Matrix4 rotation = Matrix4.rotationY(angle).multiply(Matrix4.rotationX(angle * 0.5));
        Matrix4 translation = Matrix4.translationMatrix(0, 0, -5);
        Matrix4 projection = Matrix4.perspectiveProjection((double)getWidth()/getHeight(), Math.toRadians(60), 0.1, 100);

        Vector3 lightDir = new Vector3(0, 0, 1).normalize();

        for (Face face : cube.getFaces()) {
            // Lấy 3 đỉnh thực tế từ danh sách vertices dựa trên index trong Face
            Vector3 v1 = cube.getVertices().get(face.v1);
            Vector3 v2 = cube.getVertices().get(face.v2);
            Vector3 v3 = cube.getVertices().get(face.v3);

            Vector3 w1 = rotation.transform(v1);
            Vector3 w2 = rotation.transform(v2);
            Vector3 w3 = rotation.transform(v3);

            Vector3 side1 = w2.subtract(w1);
            Vector3 side2 = w3.subtract(w1);
            Vector3 normal = side1.cross(side2).normalize();

            if (normal.getZ() > 0) {
                Vector3 p1 = projection.multiply(translation).transform(w1);
                Vector3 p2 = projection.multiply(translation).transform(w2);
                Vector3 p3 = projection.multiply(translation).transform(w3);

                double intensity = Math.max(0.1, normal.dot(lightDir));

                g2.setColor(new Color(0, (int)(200 * intensity), (int)(50 * intensity)));
                renderFace(g2, p1, p2, p3, true);

                g2.setColor(Color.BLACK);
                renderFace(g2, p1, p2, p3, false);
            }
        }
    }

    private void renderFace(Graphics2D g, Vector3 v1, Vector3 v2, Vector3 v3, boolean fill) {
        int midX = getWidth() / 2;
        int midY = getHeight() / 2;

        int[] x = {
                (int)(v1.getX() * midX + midX),
                (int)(v2.getX() * midX + midX),
                (int)(v3.getX() * midX + midX)
        };
        int[] y = {
                (int)(-v1.getY() * midY + midY),
                (int)(-v2.getY() * midY + midY),
                (int)(-v3.getY() * midY + midY)
        };

        if (fill) {
            g.fillPolygon(x, y, 3);
        } else {
            g.drawPolygon(x, y, 3);
        }
    }
}