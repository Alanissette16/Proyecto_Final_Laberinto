package Modelo;


import java.util.*;


public class Laberinto {
    private Celda inicio;
    private Celda fin;
    
    
    public List<Celda> path = new ArrayList<>();

    public void setInicio(Celda inicio) {
        this.inicio = inicio;
    }

    public void setFin(Celda fin) {
        this.fin = fin;
    }

    // Método Recursivo Simple
    public List<Celda> findPathRecursive(boolean[][] grid) {
        path.clear();  // Limpiar el camino previo
        System.out.println("Entra funcion find path");
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            System.out.println("Entra grid");
            System.out.println(path);
            return path;
        }
        if (findPath(grid, inicio.row, inicio.col)) {
            System.out.println("Entra if find path");
            System.out.println(path);
            return path;
        }
        return null;
    }

    // Método creado para encontrar el camino
    private boolean findPath(boolean[][] grid, int row, int col) {
        if (row >= grid.length || col >= grid[0].length || row < 0 || col < 0 || !grid[row][col]) {
            return false;
        }
        Celda current = new Celda(row, col);
        if (path.contains(current)) {
            return false;
        }
        path.add(current);
        if (row == fin.row && col == fin.col) {
            return true;
        }
        if (findPath(grid, row, col + 1) || findPath(grid, row + 1, col) || findPath(grid, row - 1, col) || findPath(grid, row, col - 1)) {
            return true;
        }
        path.remove(path.size() - 1);
        return false;
    }

    // Método aplicando cache (Programación Dinámica)
    public List<Celda> findPathWithCache(boolean[][] grid) {
        path.clear();  // Limpiar el camino previo
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return path;
        }
        Map<Celda, Boolean> cache = new HashMap<>();
        if (findPath(grid, inicio.row, inicio.col, cache)) {
            return path;
        }
        return null;
    }

    private boolean findPath(boolean[][] grid, int row, int col, Map<Celda, Boolean> cache) {
        if (row >= grid.length || col >= grid[0].length || row < 0 || col < 0 || !grid[row][col]) {
            return false;
        }
        Celda current = new Celda(row, col);
        if (path.contains(current)) {
            return false;
        }
        path.add(current);
        if (row == fin.row && col == fin.col) {
            return true;
        }
        if (findPath(grid, row, col + 1, cache) || findPath(grid, row + 1, col, cache) || findPath(grid, row - 1, col, cache) || findPath(grid, row, col - 1, cache)) {
            return true;
        }
        path.remove(path.size() - 1);
        return false;
    }

    // Método BFS - Búsqueda en Anchura
    public List<Celda> findPathBFS(boolean[][] grid) {
        path.clear();  // Limpiar el camino previo
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return path;
        }
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Queue<List<Celda>> queue = new LinkedList<>();
        List<Celda> start = new ArrayList<>();
        start.add(new Celda(inicio.row, inicio.col));
        queue.add(start);

        while (!queue.isEmpty()) {
            List<Celda> currentPath = queue.poll();
            Celda current = currentPath.get(currentPath.size() - 1);


            if (current.row == fin.row && current.col == fin.col) {
                path = currentPath;
                return path;
            }

            for (Celda neighbor : getNeighbors(current, grid)) {
                if (!visited[neighbor.row][neighbor.col]) {
                    visited[neighbor.row][neighbor.col] = true;
                    List<Celda> newPath = new ArrayList<>(currentPath);
                    newPath.add(neighbor);
                    queue.add(newPath);
                }
            }
        }
        return null;
    }

    private List<Celda> getNeighbors(Celda current, boolean[][] grid) {
        List<Celda> neighbors = new ArrayList<>();
        int[] dRow = {0, 1, 0, -1};
        int[] dCol = {1, 0, -1, 0};

        for (int i = 0; i < 4; i++) {
            int newRow = current.row + dRow[i];
            int newCol = current.col + dCol[i];
            if (newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid[0].length && grid[newRow][newCol]) {
                neighbors.add(new Celda(newRow, newCol));
            }
        }
        return neighbors;
    }

    public List<Celda> getDfsTraversal(boolean[][] grid) {
        List<Celda> dfsRecorrido = new ArrayList<>();
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return dfsRecorrido;
        }
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        getDfsTraversalUtil(grid, inicio.row, inicio.col, visited, dfsRecorrido);
        return dfsRecorrido;
    }
    private void getDfsTraversalUtil(boolean[][] grid, int row, int col, boolean[][] visited, List<Celda> dfsRecorrido) {
        if (row >= grid.length || col >= grid[0].length || row < 0 || col < 0 || !grid[row][col] || visited[row][col]) {
            return;
        }
        visited[row][col] = true;
        dfsRecorrido.add(new Celda(row, col));

        getDfsTraversalUtil(grid, row + 1, col, visited, dfsRecorrido);
        getDfsTraversalUtil(grid, row, col + 1, visited, dfsRecorrido);
        getDfsTraversalUtil(grid, row - 1, col, visited, dfsRecorrido);
        getDfsTraversalUtil(grid, row, col - 1, visited, dfsRecorrido);
    }

    // Método DFS - Búsqueda en Profundidad
    public List<Celda> findPathDFS(boolean[][] grid) {
        path.clear();  // Limpiar el camino previo
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return path;
        }
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        if (findPathDFSUtil(grid, inicio.row, inicio.col, visited)) {
            return path;
        }
        return null;
    }

    private boolean findPathDFSUtil(boolean[][] grid, int row, int col, boolean[][] visited) {
        if (row >= grid.length || col >= grid[0].length || row < 0 || col < 0 || !grid[row][col] || visited[row][col]) {
            return false;
        }
        visited[row][col] = true;
        Celda current = new Celda(row, col);
        path.add(current);

        if (row == fin.row && col == fin.col) {
            return true;
        }

        if (findPathDFSUtil(grid, row + 1, col, visited) || findPathDFSUtil(grid, row, col + 1, visited) || findPathDFSUtil(grid, row - 1, col, visited) || findPathDFSUtil(grid, row, col - 1, visited)) {
            return true;
        }

        path.remove(path.size() - 1);
        return false;
    }
}
