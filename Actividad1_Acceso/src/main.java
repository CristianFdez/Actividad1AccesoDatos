//paso1: importamos la clase file.
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class main {

	public static void main(String[] args)  throws ClassNotFoundException {

		
		ArrayList<Coche> coches = new ArrayList<Coche>();		
		int contador_id = 0;


		File file = new File("coches.dat");
		
		try (FileInputStream fis = new FileInputStream(file);
			 ObjectInputStream ois = new ObjectInputStream(fis);) {
			
			coches = (ArrayList<Coche>) ois.readObject();
			
			System.out.println("Fichero dat de coches leido correctamente");
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		int opcion = 0;
		Scanner sc = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);

		while(opcion != 1 && opcion != 2 && opcion != 3 && opcion!=4 && opcion != 5 && opcion != 6) {
			System.out.println("Seleccione una opcion");
			System.out.println("1: Añadir nuevo coche");
			System.out.println("2: Borrar coche por id");
			System.out.println("3: Consulta coche por id");
			System.out.println("4: Listado de coches");
			System.out.println("5: Exportar coches a archivo de texto");
			System.out.println("6: Terminar el programa");
			opcion = sc.nextInt();
			
			if(opcion == 1) {
				String matricula = "";
				String marca = "";
				String modelo = "";
				String color = "";
				
				System.out.println("Añadir nuevo coche");
				System.out.println("Introduzca la matricula: ");
				matricula = sc2.nextLine();
				//para saber si hay una matricula repetida volver al programa
				boolean matricula_repetida = false;
				for(int i=0;i<=coches.size()-1;i++) {
					if(coches.get(i).getMatricula().equals(matricula)) {
						matricula_repetida = true;
					}
				}
				if(matricula_repetida) {
					System.out.println("Error ya hay un coche con esa matricula, intentelo de nuevo");
					System.out.println("Volviendo a menú principal");
					System.out.println();
				}
				else {
					System.out.println("Introduzca la marca: ");
					marca = sc2.nextLine();
					System.out.println("Introduzca el modelo");
					modelo = sc2.nextLine();
					System.out.println("Introduza el color");
					color = sc2.nextLine();
					
					
					// sacamos el ultimo id de los coches si existe
					if(coches.size() > 0) {
						contador_id = coches.get(coches.size()-1).getId();					
					}
					contador_id++;
					Coche coche_nuevo = new Coche(contador_id, matricula, marca, modelo, color );
					coches.add(coche_nuevo);
					System.out.println("Coche añadido correctamente");
					System.out.println();
				}
				
				opcion = 0;//para que vuelva al menu principal.
			}
			if (opcion == 2) {
				System.out.println("Borrar coche por id");
				System.out.println("Introduzca el id del coche que quieres borrar");
				int id_borrar = sc2.nextInt();
				boolean encontrado = false;
				for(int i=0; i<=coches.size()-1;i++) {
					if(coches.get(i).getId() == id_borrar) {
						coches.remove(i);
						System.out.println("coche eliminado correctamente");
						encontrado = true;
					}					
				}
				if(!encontrado) {
					System.out.println("No se ha encontrado ningun coche con ese id");
				}
				System.out.println();
				opcion = 0;
			}
			if(opcion == 3) {
				System.out.println("Consultar coche por id");
				System.out.println("Introduzca el id del coche que quieres consultar");
				int id_consultar = sc2.nextInt();
				boolean encontrado = false;
				for(int i=0; i<=coches.size()-1;i++) {
					if(coches.get(i).getId() == id_consultar) {
						System.out.println(coches.get(i).toString());
						encontrado = true;
					}					
				}
				if(!encontrado) {
					System.out.println("No se ha encontrado ningun coche con ese id");
				}
				System.out.println();
				opcion = 0;
			}
			if(opcion == 4 ) {
				System.out.println("Listado coches");
				for(int i=0; i<=coches.size()-1;i++) {
					System.out.println(coches.get(i).toString());
				}
				System.out.println();
				opcion = 0;
			}
			if (opcion == 5) {
				String fichero_texto_coches = "coches.txt";
				System.out.println("Exportar coches a archivo de texto");
				
				FileWriter fw = null;
				PrintWriter pw = null;
				
				try {
					
					fw = new FileWriter(fichero_texto_coches);					
					pw = new PrintWriter(fw);
					
					String cadena = "";
					
					for(int i=0;i<=coches.size()-1; i++) {
						cadena = "";
						cadena = cadena + coches.get(i).getId() + "-" + coches.get(i).getMatricula();
						cadena = cadena + "-" + coches.get(i).getMarca() + "-" + coches.get(i).getModelo();
						cadena = cadena + "-" + coches.get(i).getColor();
						fw.write(cadena);
						fw.write(System.getProperty( "line.separator" ));
					}
															
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					
					if(fw != null){
						
						try {
							fw.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				System.out.println("Archivo txt de coches creado correctamente");
				System.out.println();
				opcion = 0;
			}
			if(opcion == 6 ) {
				System.out.println("Programa terminado");	
				try (FileOutputStream fos = new FileOutputStream("coches.dat");
						 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
						//oos = new ObjectOutputStream(new FileOutputStream(new File(nombreFichero)));
						oos.writeObject(coches);
						System.out.println("Listado de coches guardado en el fichero");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
			}
		}
	}

	
}
