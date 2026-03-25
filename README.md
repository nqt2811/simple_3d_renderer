# simple_3d_renderer
# Simple 3D Renderer

A lightweight 3D rendering engine built from scratch using **Java**. This project focuses on the fundamental concepts of the **Graphics Pipeline**, implementing 3D-to-2D rasterization without relying on heavy external graphics libraries like OpenGL or DirectX.

## 🚀 Key Features

- **Wireframe & Rasterization:** Transforming 3D polygonal meshes into 2D screen pixels.
- **Transformation Pipeline:** Full support for Translation, Rotation, and Scaling using 4x4 Matrices.
- **Projection Mapping:** Implementing Perspective Projection to simulate depth and 3D perception.
- **Custom Math Engine:** Built-in classes for Vector math and Matrix operations.

## 🛠 Project Architecture (Core Components)

The project is modularized to mirror a real-world graphics engine's workflow:

| Component | Responsibility | Complexity | Description |
| :--- | :--- | :--- | :--- |
| `Vector3` | **Point & Vector Math** | **$O(1)$** | Handles coordinates and vector operations (Dot product, Cross product). |
| `Matrix4` | **Linear Transformations** | **$O(1)$** | Executes spatial transformations and Projection matrices for 3D space. |
| `Triangle` | **Primitive Geometry** | **$O(1)$** | The fundamental building block of 3D models and rendering logic. |
| `Renderer` | **Graphics Pipeline** | **$O(n)$** | Orchestrates the flow from vertex data to final pixel output on the screen. |

## 💻 Tech Stack

- **Language:** Java
- **UI Framework:** Java Swing / AWT (for window management and pixel-level drawing)
- **Build System:** Maven / Gradle (or standard IDE Project)