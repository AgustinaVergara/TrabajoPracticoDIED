package gestores;

import javax.swing.*;

import clases.Camino;
import clases.OrdenDeProvision;
import clases.Sucursal;
import enums.EstadoOrden;
import interfaces.MyTableModel;
import interfaces.RenderTabla;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Grafo {
	private final List<Nodo> nodos;
	private final List<Arista> aristas;
	private double pageRank;

	public Grafo() {
		nodos = new ArrayList<>();
		aristas = new ArrayList<>();
		pageRank = 0.0;
	}

	public void agregarNodo(Nodo nodo) {
		nodos.add(nodo);
	}

	public void agregarArista(Arista arista) {
		aristas.add(arista);
	}

	public double getPageRank() {
		return pageRank;
	}

	public void setPageRank(double pageRank) {
		this.pageRank = pageRank;
	}

	public List<Nodo> getNodos() {
		return nodos;
	}

	public List<Arista> getAristas() {
		return aristas;
	}

	public int obtenerNumeroDeVecinos(Nodo nodo) {
		int numeroDeVecinos = 0;

		// Iterate through the edges in the graph and count neighbors of the given node
		for (Arista arista : aristas) {
			if (arista.getOrigen() == nodo || arista.getDestino() == nodo) {
				numeroDeVecinos++;
			}
		}

		return numeroDeVecinos;
	}

	public List<Arista> obtenerCaminoMasCorto(Nodo origen, Nodo destino) {
		// Implementa el algoritmo de Dijkstra para encontrar el camino más corto
		List<Arista> camino = new ArrayList<>();
		PriorityQueue<Nodo> colaPrioridad = new PriorityQueue<>();
		Nodo[] nodosAnteriores = new Nodo[nodos.size()];
		int[] distancias = new int[nodos.size()];

		// Inicializar distancias
		for (int i = 0; i < nodos.size(); i++) {
			distancias[i] = Integer.MAX_VALUE;
		}
		distancias[nodos.indexOf(origen)] = 0;

		colaPrioridad.add(origen);

		while (!colaPrioridad.isEmpty()) {
			Nodo nodoActual = colaPrioridad.poll();
			int indiceActual = nodos.indexOf(nodoActual);

			for (Arista arista : aristas) {
				if (arista.getOrigen() == nodoActual) {
					Nodo nodoDestino = arista.getDestino();
					int indiceDestino = nodos.indexOf(nodoDestino);
					int nuevaDistancia = distancias[indiceActual] + arista.getDistancia();

					if (nuevaDistancia < distancias[indiceDestino]) {
						distancias[indiceDestino] = nuevaDistancia;
						nodosAnteriores[indiceDestino] = nodoActual;
						colaPrioridad.add(nodoDestino);
					}
				}
			}
		}

		// Reconstruir el camino desde el nodo de destino hasta el origen
		int indiceDestino = nodos.indexOf(destino);
		while (nodosAnteriores[indiceDestino] != null) {
			for (Arista arista : aristas) {
				if (arista.getOrigen() == nodosAnteriores[indiceDestino]
						&& arista.getDestino() == nodos.get(indiceDestino)) {
					camino.add(arista);
					break;
				}
			}
			indiceDestino = nodos.indexOf(nodosAnteriores[indiceDestino]);
		}

		// Invertir el camino para que vaya desde el origen hasta el destino
		List<Arista> caminoInvertido = new ArrayList<>();
		for (int i = camino.size() - 1; i >= 0; i--) {
			caminoInvertido.add(camino.get(i));
		}

		return caminoInvertido;
	}

	// Para el flujo maximo
	public List<String> encontrarCaminosConPeso(Nodo origen, Nodo destino) {
		List<String> caminos = new ArrayList<>();
		encontrarCaminosConPesoRecursivo(origen, destino, new ArrayList<>(), caminos);
		return caminos;
	}

	public void encontrarCaminosConPesoRecursivo(Nodo actual, Nodo destino, List<Nodo> caminoActual,
			List<String> caminos) {
		caminoActual.add(actual);

		if (actual.equals(destino)) {
			// Hemos llegado al nodo de destino, agregamos el camino actual a la lista de
			// caminos
			int pesoTotal = calcularPesoCamino(caminoActual);
			StringBuilder camino = new StringBuilder();
			for (int i = 0; i < caminoActual.size(); i++) {
				camino.append(caminoActual.get(i).getNombre());
				if (i < caminoActual.size() - 1) {
					camino.append(" -> ");
				}
			}
			camino.append(" (Peso: ").append(pesoTotal).append(")");
			caminos.add(camino.toString());
		} else {
			// Explorar las aristas desde este nodo
			for (Arista arista : aristas) {
				if (arista.getOrigen() == actual && !caminoActual.contains(arista.getDestino())) {
					encontrarCaminosConPesoRecursivo(arista.getDestino(), destino, caminoActual, caminos);
					caminoActual.remove(caminoActual.size() - 1); // Retroceder para explorar otros caminos
				}
			}
		}
	}

	public int calcularPesoCamino(List<Nodo> camino) {
		int pesoTotal = 0;
		for (int i = 0; i < camino.size() - 1; i++) {
			Nodo origen = camino.get(i);
			Nodo destino = camino.get(i + 1);
			for (Arista arista : aristas) {
				if (arista.getOrigen() == origen && arista.getDestino() == destino) {
					pesoTotal += arista.getCapacidadMax();
					break;
				}
			}
		}
		return pesoTotal;
	}

	public Nodo buscarNodoPorNombre(String nombre) {
		for (Nodo nodo : nodos) {
			if (nodo.getNombre().equals(nombre)) {
				return nodo;
			}
		}
		return null;
	}

	public int calcularTiempoCamino(Nodo origen, Nodo destino, Grafo grafo) {
		List<Arista> camino = grafo.obtenerCaminoMasCorto(origen, destino);
		int tiempoTotal = 0;
		for (Arista arista : camino) {
			tiempoTotal += arista.getDistancia();
		}
		return tiempoTotal;
	}

	public List<Arista> obtenerCaminoPorLinea(String s, List<Nodo> nodosDelGrafo) {
		// Remove the "(Peso: ...)" part from the string
		int pesoIndex = s.indexOf("(Peso:");
		if (pesoIndex != -1) {
			s = s.substring(0, pesoIndex).trim();
		}

		// Split the string based on "->"
		String[] nodos = s.split("->");

		// Create a list to store the corresponding edges
		List<Arista> aristasDelCamino = new ArrayList<>();

		for (int i = 0; i < nodos.length - 1; i++) {
			String nombreOrigen = nodos[i].trim();
			String nombreDestino = nodos[i + 1].trim();

			// Find the nodes corresponding to the names
			Nodo nodoOrigen = null;
			Nodo nodoDestino = null;

			for (Nodo nodo : nodosDelGrafo) {
				if (nodo.getNombre().equals(nombreOrigen)) {
					nodoOrigen = nodo;
				} else if (nodo.getNombre().equals(nombreDestino)) {
					nodoDestino = nodo;
				}

				if (nodoOrigen != null && nodoDestino != null) {
					break;
				}
			}

			if (nodoOrigen != null && nodoDestino != null) {
				// Find the edge that connects the nodes
				for (Arista arista : aristas) {
					if (arista.getOrigen() == nodoOrigen && arista.getDestino() == nodoDestino) {
						aristasDelCamino.add(arista);
						break;
					}
				}
			}
		}

		return aristasDelCamino;
	}
}

class Nodo implements Comparable<Nodo> {
	private final int x;
	private final int y;
	private final String nombre;
	private int distanciaDesdeOrigen; // Nueva propiedad para el cálculo del camino más corto
	private double pageRank;

	public Nodo(int x, int y, String nombre) {
		this.x = x;
		this.y = y;
		this.nombre = nombre;
		this.distanciaDesdeOrigen = Integer.MAX_VALUE; // Inicializamos la distancia a infinito
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public double getPageRank() {
		return pageRank;
	}

	public void setPageRank(double pageRank) {
		this.pageRank = pageRank;
	}

	public String getNombre() {
		return nombre;
	}

	public int getDistanciaDesdeOrigen() {
		return distanciaDesdeOrigen;
	}

	public void setDistanciaDesdeOrigen(int distancia) {
		this.distanciaDesdeOrigen = distancia;
	}

	@Override
	public int compareTo(Nodo otroNodo) {
		return Integer.compare(this.distanciaDesdeOrigen, otroNodo.distanciaDesdeOrigen);
	}
}

class Arista {
	private final Nodo origen;
	private final Nodo destino;
	private final int tiempo;
	private final double capacidadMax;

	public Arista(Nodo origen, Nodo destino, int distancia, double capacidadMax) {
		this.origen = origen;
		this.destino = destino;
		this.tiempo = distancia;
		this.capacidadMax = capacidadMax;
	}

	public Nodo getOrigen() {
		return origen;
	}

	public Nodo getDestino() {
		return destino;
	}

	public int getDistancia() {
		return tiempo;
	}

	public double getCapacidadMax() {
		return capacidadMax;
	}
}

class PageRankCalculator {
	public static void calcularPageRank(Grafo grafo, int numIteraciones) {
		// Inicializar el PageRank de todos los nodos
		for (Nodo nodo : grafo.getNodos()) {
			nodo.setPageRank(1.0);
		}

		// Realizar un número fijo de iteraciones
		for (int iteracion = 0; iteracion < numIteraciones; iteracion++) {
			// Copiar el PageRank actual de todos los nodos a un nuevo arreglo
			double[] nuevoPageRank = new double[grafo.getNodos().size()];
			for (int i = 0; i < nuevoPageRank.length; i++) {
				nuevoPageRank[i] = grafo.getNodos().get(i).getPageRank();
			}

			// Calcular el nuevo PageRank para cada nodo
			for (int i = 0; i < grafo.getNodos().size(); i++) {
				Nodo nodoActual = grafo.getNodos().get(i);
				double sumaPageRank = 0.0;

				for (int j = 0; j < grafo.getNodos().size(); j++) {
					if (i != j) {
						Nodo nodoVecino = grafo.getNodos().get(j);
						int numVecinos = grafo.obtenerNumeroDeVecinos(nodoVecino);

						// Calcular la contribución del nodo vecino al PageRank del nodo actual
						double contribucion = nodoVecino.getPageRank() / numVecinos;
						sumaPageRank += contribucion;
					}
				}

				// Asignar el nuevo PageRank al nodo actual
				nuevoPageRank[i] = sumaPageRank;
			}

			// Actualizar el PageRank de todos los nodos después de cada iteración
			for (int i = 0; i < nuevoPageRank.length; i++) {
				grafo.getNodos().get(i).setPageRank(nuevoPageRank[i]);
			}
		}

		// Normalizar los valores de PageRank para que sumen 1
		double sumaTotalPageRank = 0.0;
		for (Nodo nodo : grafo.getNodos()) {
			sumaTotalPageRank += nodo.getPageRank();
		}
		for (Nodo nodo : grafo.getNodos()) {
			nodo.setPageRank(nodo.getPageRank() / sumaTotalPageRank);
		}
	}
}

class GrafoPanel extends JPanel {
	private final Grafo grafo;
	private final List<Arista> caminoSeleccionado;
	private final List<Arista> caminoMaximo;

	public GrafoPanel(Grafo grafo) {
		this.grafo = grafo;
		this.caminoSeleccionado = new ArrayList<>();
		this.caminoMaximo = new ArrayList<>();
	}

	public void setCaminoSeleccionado(List<Arista> camino) {
		caminoSeleccionado.clear();
		caminoSeleccionado.addAll(camino);
		repaint(); // Volver a pintar el panel cuando se establezca el camino seleccionado
	}

	public void setCaminoMaximo(List<Arista> camino) {
		caminoMaximo.clear();
		caminoMaximo.addAll(camino);
		repaint(); // Volver a pintar el panel cuando se establezca el camino máximo
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		// Dibujar aristas
		for (Arista arista : grafo.getAristas()) {
			g2d.setColor(new Color(186, 85, 211)); // Color de arista (violeta)
			g2d.setStroke(new BasicStroke(2)); // Grosor de línea
			int x1 = arista.getOrigen().getX() + 10;
			int y1 = arista.getOrigen().getY() + 10;
			int x2 = arista.getDestino().getX() + 10;
			int y2 = arista.getDestino().getY() + 10;
			int nodeSize = 30; // Tamaño del nodo
			double angle = Math.atan2(y2 - y1, x2 - x1);

			// Calcula la posición para la punta de flecha
			int arrowX = (int) (x2 - (nodeSize / 2) * Math.cos(angle));
			int arrowY = (int) (y2 - (nodeSize / 2) * Math.sin(angle));

			g2d.drawLine(x1, y1, x2, y2);

			// Dibuja la punta de flecha
			Path2D arrowHead = new Path2D.Double();
			arrowHead.moveTo(arrowX, arrowY);
			arrowHead.lineTo(arrowX - 8 * Math.cos(angle - Math.PI / 6), arrowY - 8 * Math.sin(angle - Math.PI / 6));
			arrowHead.moveTo(arrowX, arrowY);
			arrowHead.lineTo(arrowX - 8 * Math.cos(angle + Math.PI / 6), arrowY - 8 * Math.sin(angle + Math.PI / 6));
			g2d.setColor(new Color(186, 85, 211)); // Color de punta de flecha (violeta)
			g2d.draw(arrowHead);
		}

		// Dibujar nodos
		for (Nodo nodo : grafo.getNodos()) {
			int x = nodo.getX();
			int y = nodo.getY();
			int nodeSize = 30; // Tamaño del nodo

			// Dibuja un círculo relleno
			g2d.setColor(new Color(135, 206, 250)); // Color de nodo (celeste)
			g2d.fill(new Ellipse2D.Double(x, y, nodeSize, nodeSize));

			// Dibuja el nombre del nodo
			g2d.setColor(Color.BLACK);
			g2d.setFont(new Font("Arial", Font.BOLD, 14)); // Fuente y tamaño de texto
			g2d.drawString(nodo.getNombre(), x + nodeSize / 4, y + nodeSize / 2);
		}

		// Resaltar el camino seleccionado en verde
		g2d.setColor(new Color(0, 255, 0)); // Color de camino seleccionado (verde)
		for (Arista arista : caminoSeleccionado) {
			int x1 = arista.getOrigen().getX() + 10;
			int y1 = arista.getOrigen().getY() + 10;
			int x2 = arista.getDestino().getX() + 10;
			int y2 = arista.getDestino().getY() + 10;
			int nodeSize = 30; // Tamaño del nodo
			double angle = Math.atan2(y2 - y1, x2 - x1);

			// Calcula la posición para la punta de flecha
			int arrowX = (int) (x2 - (nodeSize / 2) * Math.cos(angle));
			int arrowY = (int) (y2 - (nodeSize / 2) * Math.sin(angle));

			g2d.drawLine(x1, y1, x2, y2);

			// Dibuja la punta de flecha
			Path2D arrowHead = new Path2D.Double();
			arrowHead.moveTo(arrowX, arrowY);
			arrowHead.lineTo(arrowX - 8 * Math.cos(angle - Math.PI / 6), arrowY - 8 * Math.sin(angle - Math.PI / 6));
			arrowHead.moveTo(arrowX, arrowY);
			arrowHead.lineTo(arrowX - 8 * Math.cos(angle + Math.PI / 6), arrowY - 8 * Math.sin(angle + Math.PI / 6));
			g2d.setColor(new Color(0, 255, 0)); // Color de punta de flecha (verde)
			g2d.draw(arrowHead);

		}
		g2d.setColor(new Color(255, 0, 0)); // Color de camino máximo (rojo)
		for (Arista arista : caminoMaximo) {
			int x1 = arista.getOrigen().getX() + 10;
			int y1 = arista.getOrigen().getY() + 10;
			int x2 = arista.getDestino().getX() + 10;
			int y2 = arista.getDestino().getY() + 10;
			int nodeSize = 30; // Tamaño del nodo
			double angle = Math.atan2(y2 - y1, x2 - x1);

			// Calcula la posición para la punta de flecha
			int arrowX = (int) (x2 - (nodeSize / 2) * Math.cos(angle));
			int arrowY = (int) (y2 - (nodeSize / 2) * Math.sin(angle));

			g2d.drawLine(x1, y1, x2, y2);

			// Dibuja la punta de flecha
			Path2D arrowHead = new Path2D.Double();
			arrowHead.moveTo(arrowX, arrowY);
			arrowHead.lineTo(arrowX - 8 * Math.cos(angle - Math.PI / 6), arrowY - 8 * Math.sin(angle - Math.PI / 6));
			arrowHead.moveTo(arrowX, arrowY);
			arrowHead.lineTo(arrowX - 8 * Math.cos(angle + Math.PI / 6), arrowY - 8 * Math.sin(angle + Math.PI / 6));
			g2d.setColor(new Color(255, 0, 0)); // Color de punta de flecha (rojo)
			g2d.draw(arrowHead);
		}
	}

}

class NodoComparator implements Comparator<Nodo> {
    @Override
    public int compare(Nodo nodo1, Nodo nodo2) {
        // Compara los PageRank de manera descendente
        return Double.compare(nodo2.getPageRank(), nodo1.getPageRank());
    }
}

public class GestorGrafo {
	private static GestorGrafo gestor;
	private MyTableModel modelTablePageRank;

	public static GestorGrafo getInstance() {
		if (gestor == null) {
			gestor = new GestorGrafo();
		}
		return gestor;
	}

	// Funcion que grafica el grafo y con color resalta el camino
	public void graficarGrafoConCamino(String nodoO, String nodoD) {

		GestorSucursal gestorSucursal = GestorSucursal.getInstance();
		List<Sucursal> listaSucursales = gestorSucursal.getSucursales();

		GestorCamino gestorCamino = GestorCamino.getInstance();
		List<Camino> listaCaminos = gestorCamino.getCaminos();

		Grafo grafo = new Grafo();

		int contador = 0;
		int[] x = { 50, 150, 150, 150, 250, 250, 250, 350, 350, 350, 450, 450, 450 };
		int[] y = { 150, 50, 150, 250, 50, 150, 250, 50, 150, 250, 150, 50, 250 };

		for (Sucursal s : listaSucursales) {
			Nodo nodo = new Nodo(x[contador], y[contador], s.getNombre());
			grafo.agregarNodo(nodo);
			contador++;
		}
		Nodo nodoOrigen = grafo.buscarNodoPorNombre(nodoO);
		Nodo nodoDestino = grafo.buscarNodoPorNombre(nodoD);

		for (Camino c : listaCaminos) {
			grafo.agregarArista(new Arista(grafo.buscarNodoPorNombre(c.getSO().getNombre()),
					grafo.buscarNodoPorNombre(c.getSD().getNombre()), c.getTiempoTransito(), c.getCapacidadMax()));
		}

		JFrame frame = new JFrame("Grafo Estilizado");

		frame.setSize(550, 400);

		GrafoPanel panel = new GrafoPanel(grafo);
		frame.add(panel);
		frame.setVisible(true);

		List<Arista> camino = grafo.obtenerCaminoMasCorto(nodoOrigen, nodoDestino);
		panel.setCaminoSeleccionado(camino);
	}

	public void graficarGrafo() {

		GestorSucursal gestorSucursal = GestorSucursal.getInstance();
		List<Sucursal> listaSucursales = gestorSucursal.getSucursales();

		GestorCamino gestorCamino = GestorCamino.getInstance();
		List<Camino> listaCaminos = gestorCamino.getCaminos();

		Grafo grafo = new Grafo();

		int contador = 0;
		int[] x = { 50, 150, 150, 150, 250, 250, 250, 350, 350, 350, 450, 450, 450 };
		int[] y = { 150, 50, 150, 250, 50, 150, 250, 50, 150, 250, 150, 50, 250 };

		for (Sucursal s : listaSucursales) {
			Nodo nodo = new Nodo(x[contador], y[contador], s.getNombre());
			grafo.agregarNodo(nodo);
			contador++;
		}

		for (Camino c : listaCaminos) {
			grafo.agregarArista(new Arista(grafo.buscarNodoPorNombre(c.getSO().getNombre()),
					grafo.buscarNodoPorNombre(c.getSD().getNombre()), c.getTiempoTransito(), c.getCapacidadMax()));
		}

		JFrame frame = new JFrame("Grafo Estilizado");

		frame.setSize(550, 400);

		GrafoPanel panel = new GrafoPanel(grafo);
		frame.add(panel);
		frame.setVisible(true);

	}

	public Grafo crearGrafo() {
		GestorSucursal gestorSucursal = GestorSucursal.getInstance();
		List<Sucursal> listaSucursales = gestorSucursal.getSucursalesOperativas();

		GestorCamino gestorCamino = GestorCamino.getInstance();
		List<Camino> listaCaminos = gestorCamino.getCaminos();

		Grafo grafo = new Grafo();

		int contador = 0;
		int[] x = { 50, 150, 150, 150, 250, 250, 250, 350, 350, 350, 450, 450, 450 };
		int[] y = { 150, 50, 150, 250, 50, 150, 250, 50, 150, 250, 150, 50, 250 };

		for (Sucursal s : listaSucursales) {
			Nodo nodo = new Nodo(x[contador], y[contador], s.getNombre());
			grafo.agregarNodo(nodo);
			contador++;
		}

		for (Camino c : listaCaminos) {
			grafo.agregarArista(new Arista(grafo.buscarNodoPorNombre(c.getSO().getNombre()),
					grafo.buscarNodoPorNombre(c.getSD().getNombre()), c.getTiempoTransito(), c.getCapacidadMax()));
		}

		return grafo;
	}

	public int calcularTiempoEntreSucursales(String SO, String SD, Grafo grafo) {
		return grafo.calcularTiempoCamino(grafo.buscarNodoPorNombre(SO), grafo.buscarNodoPorNombre(SD), grafo);
	}

	public void flujoMaximo(String fuente, String sumidero) {
		Grafo grafo = new Grafo();
		GestorGrafo gestorGrafo = GestorGrafo.getInstance();

		grafo = gestorGrafo.crearGrafo();

		List<String> caminosConPeso = grafo.encontrarCaminosConPeso(grafo.buscarNodoPorNombre(fuente),
				grafo.buscarNodoPorNombre(sumidero));
		System.out.println("Caminos desde NodoPuerto a NodoCentro con Peso:");

		int pesoMaximo = Integer.MIN_VALUE;
		List<String> caminosConPesoMaximo = new ArrayList<>();

		int contadorAux = 1;
		for (String caminoConPeso : caminosConPeso) {
			System.out.println(contadorAux + ". " + caminoConPeso);

			// Obtener el peso del camino actual desde el texto
			int inicioPeso = caminoConPeso.indexOf("(Peso: ") + 7;
			int finPeso = caminoConPeso.indexOf(")");
			int pesoActual = Integer.parseInt(caminoConPeso.substring(inicioPeso, finPeso));

			// Comparar con el peso máximo encontrado hasta ahora
			if (pesoActual > pesoMaximo) {
				pesoMaximo = pesoActual;
				caminosConPesoMaximo.clear();
				caminosConPesoMaximo.add(caminoConPeso);
			} else if (pesoActual == pesoMaximo) {
				caminosConPesoMaximo.add(caminoConPeso);
			}
			contadorAux++;
		}

		System.out.println("\nCaminos con el Peso Máximo (" + pesoMaximo + "):");

		JFrame frame = new JFrame("Grafo");
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

		// Dar al nuevo frame que muestra el flujo maximo el mismo tamaño de pantalla

		// Tamaño deseado para el JFrame
		int width = 625;
		int height = 400;

		// Obtenemos el tamaño de la pantalla
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;

		// Calculamos las coordenadas (x, y) para centrar el JFrame
		int x = (screenWidth - width) / 2;
		int y = (screenHeight - height) / 2;

		// Establecemos las coordenadas y el tamaño
		frame.setBounds(x, y, width, height);

		GrafoPanel panel = new GrafoPanel(grafo);

		JLabel tituloFM = new JLabel("FLUJO MAXIMO");
		tituloFM.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		frame.add(tituloFM);

		for (String caminoMaximo : caminosConPesoMaximo) {
			System.out.println(caminoMaximo);
			List<Arista> caminoAristas = grafo.obtenerCaminoPorLinea(caminoMaximo, grafo.getNodos());
			panel.setCaminoMaximo(caminoAristas);
			JLabel info = new JLabel(caminoMaximo);
			info.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			frame.add(info);
		}

		frame.add(panel);

		frame.setVisible(true);

	}

	public void pageRank() {
	    Grafo grafo = new Grafo();
	    GestorGrafo gestorGrafo = GestorGrafo.getInstance();
	    PageRankCalculator pageRank = new PageRankCalculator();
	    grafo = gestorGrafo.crearGrafo();
	    pageRank.calcularPageRank(grafo, 20);

	    JFrame frame = new JFrame("Grafo");
	    frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

		// Dar al nuevo frame que muestra el PageRank del mismo tamaño de pantalla

		// Tamaño deseado para el JFrame
		int width = 625;
		int height = 400;

		// Obtenemos el tamaño de la pantalla
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;

		// Calculamos las coordenadas (x, y) para centrar el JFrame
		int x = (screenWidth - width) / 2;
		int y = (screenHeight - height) / 2;

		// Establecemos las coordenadas y el tamaño
		frame.setBounds(x, y, width, height);

		JLabel tituloPR = new JLabel("PAGERANK");
		tituloPR.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		frame.add(tituloPR);

	    JPanel panel = new JPanel();
	    JTable tablePageRank;

	    JScrollPane scrollPanePR = new JScrollPane();
	    scrollPanePR.setBounds(20, 56, 470, 89);
	    panel.add(scrollPanePR);

	    // Defino mi propio default table model para cargar los datos en la tabla
	    modelTablePageRank = new MyTableModel();
	    tablePageRank = new JTable(modelTablePageRank); // Asociar el modelo de tabla al JTable
	    
	    // Añadir las columnas al modelo antes de agregar los datos
	    modelTablePageRank.addColumn("Sucursal");
	    modelTablePageRank.addColumn("Valor PageRank");
	    modelTablePageRank.addColumn("Porcentaje");

	 // Supongamos que tienes una lista de nodos llamada "nodos"
	    List<Nodo> nodos = grafo.getNodos();

	    // Utiliza el comparador personalizado para ordenar la lista
	    Collections.sort(nodos, new NodoComparator());

	    // Imprime la lista ordenada
	    for (Nodo nodo : nodos) {
	        System.out.println("PageRank de " + nodo.getNombre() + ": " + nodo.getPageRank());
	    }
	    //Esto ordenará la lista nodos de mayor a menor según el valor de PageRank y luego imprimirá los resultados ordenados.
	    for (Nodo n : nodos) {
	        Object[] fila = new Object[3];
	        fila[0] = n.getNombre();
	        fila[1] = n.getPageRank();
	        fila[2] = n.getPageRank() *100+"%";
	        modelTablePageRank.addRow(fila);
	    }

	    tablePageRank.setDefaultRenderer(Object.class, new RenderTabla()); // renderizar tabla

	    scrollPanePR.setViewportView(tablePageRank);

	    frame.add(panel);
	    frame.setVisible(true);
	}

}
