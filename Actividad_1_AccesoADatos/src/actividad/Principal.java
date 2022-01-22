package actividad;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;







public class Principal {

	public static void main(String[] args) {
		
		//Requerimiento 1
		
		File fichero = new File(Coche.nombreFichero);
		
		List<Coche> listaCoches= new ArrayList<Coche>();
		
		//Si existe el fichero lo copio a una lista
		if (fichero.exists()) { 
			try (FileInputStream fis = new FileInputStream(fichero);
					 ObjectInputStream ois = new ObjectInputStream(fis);) {
					
					List<Coche> CopiaListaCoches = (List<Coche>)ois.readObject();
					
					
				
				}catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			
			return;
			
			// Si no existe creo lista coches 
		}
		
		
		
		// Creando interfaz
		
		System.out.println("-----Menú de opciones------");
		System.out.println("Elija una de las siguientes opciones");
		System.out.println("1- Añadir coche");
		System.out.println("2- Borrar coche por id");
		System.out.println("3- consultar coche por id");
		System.out.println("4- Listado de coches");
		System.out.println("5- Exportar coches a archivo de texto");
		System.out.println("6- Terminar programa");
		
		
		//Creando el lector de opción elegida
		Scanner sc= new Scanner(System.in);
		
		int opcion= sc.nextInt();
		
		//Creo variables intermedias
		
		String id="";
		String matricula=""; 
		String marca=""; 
		String modelo =""; 
		String color="";
		 
		
		//Manejador de opciones 
		
		switch (opcion) {
		 
		//Añadir coche
		case 1:
			System.out.println("Ha elegido la opción 1 Añadir coche");
			
			System.out.println("Introduzca un ID");
			id= sc.nextLine();
			System.out.println("Intoduzca una matricula");
			matricula=sc.nextLine();
			System.out.println("Introduzca la marca");
			marca=sc.nextLine();
			System.out.println("Introduzca el modelo");
			modelo= sc.nextLine();
			System.out.println("Introduzca el color");
			color=sc.nextLine();
			
			Coche coche= new Coche(id,matricula,marca,modelo,color);
			System.out.println(coche.toString());
			
			listaCoches.add(coche);
			/** Prueba de si lo guarda en la lista
			for(Coche c:listaCoches )
				System.out.println(c);
				**/
			break;
		//Borrar coche	
		case 2:
			System.out.println("Introduzca el id del coche que quiere borrar");
			id= sc.nextLine();
			
			int indice = listaCoches.indexOf(id);
			
			if (indice != -1) {
				listaCoches.remove(id);	
				
			}
			
			break;
		//Consultar coche
		case 3:
			System.out.println("Introduzca el id del coche que quiere consultar");
			id= sc.nextLine();
			 indice = listaCoches.indexOf(id);
			 System.out.println(listaCoches.get(indice).toString());
			
			break;
		//Listado de coches	
		case 4:
			System.out.println("Listado de coches");
			for(Coche c : listaCoches){
				System.out.println(c);
			}
			break;
		//Exportar coche	
		case 5:
			try (FileOutputStream fos = new FileOutputStream(fichero);
					 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
					oos.writeObject(listaCoches);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
				System.out.println("Cerrando programa");
			
			break;
		//Terminar	
		case 6:

			File file = new File("coches.txt");
		
			try (FileOutputStream fos = new FileOutputStream(file);
				 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
				//oos = new ObjectOutputStream(new FileOutputStream(new File(nombreFichero)));
				oos.writeObject(listaCoches);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			break;

		
		}
		
		
		
	

	}

}
