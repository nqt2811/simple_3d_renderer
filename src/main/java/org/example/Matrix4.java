package org.example;

public class Matrix4 {
    private double[] matrix;

    Matrix4 () {
        this.matrix = new double[16];
    }

    public static Matrix4 identityMatrix() {
        Matrix4 mat = new Matrix4();
        mat.matrix[0] = mat.matrix[5] = mat.matrix[10] = mat.matrix[15] = 1.0;
        return mat;
    }

    public static Matrix4 translationMatrix(double tx, double ty, double tz) {
        Matrix4 mat = identityMatrix();
        mat.matrix[3] = tx;
        mat.matrix[7] = ty;
        mat.matrix[11] = tz;

        return mat;
    }

    public static Matrix4 scalingMatrix(double sx, double sy, double sz) {
        Matrix4 mat = identityMatrix();
        mat.matrix[0] = sx;
        mat.matrix[5] = sy;
        mat.matrix[10] = sz;

        return mat;
    }

    public static Matrix4 rotationX(double angle) {
        Matrix4 mat = identityMatrix();
        double c = Math.cos(angle), s = Math.sin(angle);
        mat.matrix[5] = c;  mat.matrix[6] = -s;
        mat.matrix[9] = s;  mat.matrix[10] =  c;
        return mat;
    }

    public static Matrix4 rotationY(double angle) {
        Matrix4 mat = identityMatrix();
        double c = Math.cos(angle), s = Math.sin(angle);
        mat.matrix[0] =  c;  mat.matrix[2] = s;
        mat.matrix[8] = -s;  mat.matrix[10] = c;
        return mat;
    }

    public static Matrix4 rotationZ(double angle) {
        Matrix4 mat = identityMatrix();
        double c = Math.cos(angle), s = Math.sin(angle);
        mat.matrix[0] = c;  mat.matrix[1] = -s;
        mat.matrix[4] = s;  mat.matrix[5] =  c;
        return mat;
    }

    public static Matrix4 perspectiveProjection(double aspectRatio, double fov, double near, double far) {
        double f = 1 / (Math.tan(fov / 2.0));
        Matrix4 mat = new Matrix4();
        mat.matrix[0] = f / aspectRatio;
        mat.matrix[5] = f;
        mat.matrix[10] = (near + far) / (near - far);
        mat.matrix[11] = (2.0 * near * far) / (near - far);
        mat.matrix[14] = -1.0;

        return mat;
    }

    public Matrix4 multiply(Matrix4 other) {
        Matrix4 result = new Matrix4();
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                double sum = 0;
                for (int k = 0; k < 4; k++) {
                    sum += this.matrix[row * 4 + k] * other.matrix[k * 4 + col];
                }
                result.matrix[row * 4 + col] = sum;
            }
        }
        return result;
    }

    public Vector3 transform(Vector3 v) {
        double x = v.getX(), y = v.getY(), z = v.getZ();

        double nx = matrix[0] * x + matrix[1] * y + matrix[2] * z + matrix[3];
        double ny = matrix[4] * x + matrix[5] * y + matrix[6] * z + matrix[7];
        double nz = matrix[8] * x + matrix[9] * y + matrix[10] * z + matrix[11];
        double nw = matrix[12] * x + matrix[13] * y + matrix[14] * z + matrix[15];

        if (nw != 0 && nw != 1.0) {
            return new Vector3(nx / nw, ny / nw, nz / nw);
        }
        return new Vector3(nx, ny, nz);
    }
}
