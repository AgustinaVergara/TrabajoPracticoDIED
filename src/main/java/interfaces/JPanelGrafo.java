package interfaces;

import javax.swing.JPanel;


import clases.Grafo;

import clases.Puntos;

import java.util.ArrayList;



import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;


public class JPanelGrafo extends JPanel {
	//GestorGrafos gestor = new GestorGrafos().getInstance();
	//ArrayList<Vertice> vertices;
	//ArrayList<Arista> aristas;
	ArrayList<Point> puntosVertices;
	ArrayList<Puntos> puntosAristas;
	 private Map<Integer, Point> posicionNodo;
	    private int[][] matrizAdy;
	/**
	 * Create the panel.
	 */
	Grafo grafo1;
	public JPanelGrafo() {
		//this.grafo1= grafo1;
		setLayout(null);
			

	        // Inicializar el mapa de posiciones de nodos y la matriz de adyacencia
	        posicionNodo = new HashMap<>();
	        matrizAdy = new int[5][5]; // Cambia el tamaño según tu grafo

	        // Definir las posiciones de los nodos (aquí se asumen 5 nodos)
	        posicionNodo.put(0, new Point(100, 100));
	        posicionNodo.put(1, new Point(200, 100));
	        posicionNodo.put(2, new Point(150, 200));
	        posicionNodo.put(3, new Point(300, 100));
	        posicionNodo.put(4, new Point(250, 200));
	        
	        // Definir la matriz de adyacencia para representar las aristas (0 indica no conexión, 1 indica conexión)
	        matrizAdy[0][1] = 1;
	        matrizAdy[0][2] = 1;
	        matrizAdy[1][0] = 1;
	        matrizAdy[2][0] = 1;
	        matrizAdy[1][3] = 1;
	        matrizAdy[3][1] = 1;
	        matrizAdy[3][4] = 1;
	        matrizAdy[4][3] = 1;
	       
	        Graphics grafico = null;
	        
	        paintComponent(grafico);


	}
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar aristas
        g.setColor(Color.BLACK);
        for (int i = 0; i < matrizAdy.length; i++) {
            for (int j = 0; j < matrizAdy[i].length; j++) {
                if (matrizAdy[i][j] == 1) {
                    Point startPoint = posicionNodo.get(i);
                    Point endPoint = posicionNodo.get(j);
                    g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
                }
            }
        }

        // Dibujar nodos
        g.setColor(Color.BLUE);
        for (Integer nodeId : posicionNodo.keySet()) {
            Point nodePosition = posicionNodo.get(nodeId);
            g.fillOval(nodePosition.x - 10, nodePosition.y - 10, 20, 20);
            g.drawString(Integer.toString(nodeId), nodePosition.x - 5, nodePosition.y + 5);
        }
	
}
	
}


