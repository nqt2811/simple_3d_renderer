package org.example;

import java.util.ArrayList;
import java.util.List;

public class Mesh {
    private List<Vector3> vertices;
    private List<Face> faces;

    public Mesh() {
        vertices = new ArrayList<>();
        faces = new ArrayList<>();
    }

    public void addVertex(Vector3 v) { vertices.add(v); }
    public void addFace(int v1, int v2, int v3) { faces.add(new Face(v1, v2, v3)); }

    public List<Vector3> getVertices() { return vertices; }
    public List<Face> getFaces() { return faces; }

    public static Mesh createCube() {
        Mesh mesh = new Mesh();
        mesh.addVertex(new Vector3(-1, -1,  1));
        mesh.addVertex(new Vector3( 1, -1,  1));
        mesh.addVertex(new Vector3( 1,  1,  1));
        mesh.addVertex(new Vector3(-1,  1,  1));
        mesh.addVertex(new Vector3(-1, -1, -1));
        mesh.addVertex(new Vector3( 1, -1, -1));
        mesh.addVertex(new Vector3( 1,  1, -1));
        mesh.addVertex(new Vector3(-1,  1, -1));

        mesh.addFace(0, 1, 2); mesh.addFace(0, 2, 3);
        mesh.addFace(5, 4, 7); mesh.addFace(5, 7, 6);
        mesh.addFace(3, 2, 6); mesh.addFace(3, 6, 7);
        mesh.addFace(4, 5, 1); mesh.addFace(4, 1, 0);
        mesh.addFace(4, 0, 3); mesh.addFace(4, 3, 7);
        mesh.addFace(1, 5, 6); mesh.addFace(1, 6, 2);

        return mesh;
    }

    public static Mesh createPyramid() {
        Mesh mesh = new Mesh();

        mesh.addVertex(new Vector3( 0,  1,  0));  // 0: Đỉnh chóp
        mesh.addVertex(new Vector3(-1, -1,  1));
        mesh.addVertex(new Vector3( 1, -1,  1));
        mesh.addVertex(new Vector3( 1, -1, -1));
        mesh.addVertex(new Vector3(-1, -1, -1));

        mesh.addFace(0, 1, 2);
        mesh.addFace(0, 2, 3);
        mesh.addFace(0, 3, 4);
        mesh.addFace(0, 4, 1);

        mesh.addFace(1, 4, 3);
        mesh.addFace(1, 3, 2);

        return mesh;
    }
}
