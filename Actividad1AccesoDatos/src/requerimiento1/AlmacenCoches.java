package requerimiento1;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class AlmacenCoches {

	public static void main(String[] args) throws ClassNotFoundException {
		ArrayList<Coche> arraCoches = new ArrayList<Coche>();
		
		try (FileInputStream file = new FileInputStream("coches.dat");
				ObjectInputStream buffer = new ObjectInputStream(file);){
			
			boolean eof = false;
			Coche coche;
			
			while (!eof) {
				try {
					coche = (Coche) buffer.readObject();
					arraCoches.add(coche);
					
				} catch (EOFException e1) {
					eof = true;
				} catch (IOException e2) {
					System.out.println("Error al leer los coches del almacén");
					System.out.println(e2.getMessage());
				} catch (ClassNotFoundException e3) {
					System.out.println("La clase Coche no está cargada en memoria");
					System.out.println(e3.getMessage());
				}
			}
		} catch (IOException e) {
			System.out.println("Error al leer en el fichero");
			System.out.println(e.getMessage());
			
		}
		
		try (Scanner sc = new Scanner(System.in)){
			int opcion;
			String texto = "";
			boolean continuar = true;
			
			
			do {
				escribirMenu();
				opcion = Integer.parseInt(sc.nextLine());
				Coche coche = new Coche();
				
				switch (opcion) {
				case 1:
					boolean repetido = false;
					
					System.out.println("************ ALTA COCHE *************");					
					System.out.println("Introduce el ID del coche:");
					texto = sc.nextLine();
					coche.setId(Integer.parseInt(texto));
					
					System.out.println("Introduce la matrícula del coche:");
					texto = sc.nextLine();
					coche.setMatricula(texto);
					
					System.out.println("Introduce la marca del coche:");
					texto = sc.nextLine();
					coche.setMarca(texto);
					
					System.out.println("Introduce el modelo del coche:");
					texto = sc.nextLine();
					coche.setModelo(texto);	
					
					System.out.println("Introduce el color del coche:");
					texto = sc.nextLine();
					coche.setColor(texto);	
					
					for (Coche c : arraCoches) {
						if(coche.getId() == c.getId()) {
							System.out.println("El ID introducido ya está en el almacén, por favor vuelva a introducir el coche con un ID diferente");
							repetido = true;						
						}
						
						if(coche.getMatricula().equalsIgnoreCase(c.getMatricula())) {
							System.out.println("La matricula introducida ya está en el almacén, por favor vuelva a introducir el coche con una matrícula diferente");
							repetido = true;
						}	
					}
					
					if(!repetido) {
						arraCoches.add(coche);
						System.out.println("Coche añadido al almacén");
					}
					break;
					
				case 2:			
					boolean encontrado = false;
					
					System.out.println("********** BORRAR COCHE **************");
					System.out.println("Introduce el id del coche:");
					texto = sc.nextLine();
					
					for(Coche c : arraCoches) {
						if(c.getId() == Integer.parseInt(texto)) {
							arraCoches.remove(c);
							encontrado = true;
							break;
						}
					}
					
					if(!encontrado) {
						System.out.println("El ID del coche no se encuentra en el almacén");
					}else {
						System.out.println("Coche borrado correctamente");
					}
					
					break;
					
				case 3:
					boolean existe = false;
					
					System.out.println("********* CONSULTA COCHE *************");
					System.out.println("Introduce el id del coche:");
					texto = sc.nextLine();
					
					for(Coche c : arraCoches) {
						if(c.getId() == Integer.parseInt(texto)) {
							System.out.println(c);
							existe = true;
						}
					}

					if(!existe) {
						System.out.println("El ID del coche no se encuentra en el almacén");
					}
					
					break;
					
				case 4:
					System.out.println("************ LISTADO COCHES ***************");
					
					for (Coche c : arraCoches) {
						System.out.println(c);
						
					}						
					break;
					
				case 5:
					System.out.println("******************************************");		
					System.out.println("******** Parando el programa *********");
					
					try(FileOutputStream file = new FileOutputStream("coches.dat");
							ObjectOutputStream escritor = new ObjectOutputStream(file);) {
						
						for (Coche c : arraCoches) {
							escritor.writeObject(c);
							
						}
						System.out.println("El almacén se ha guardado con éxito");
						
					} catch (IOException e) {
						e.printStackTrace();
						System.out.println("No se ha podido abrir el almacén");
						System.out.println(e.getMessage());
					}	
					
					continuar = false;
					break;

				case 6:
					System.out.println("******************************************");		
					System.out.println("******** Exportando almacén a texto *********");
					
					try(FileWriter fw = new FileWriter("cochesTexto.txt");
							BufferedWriter pw = new BufferedWriter(fw);) {

						for (Coche c : arraCoches) {
							texto = c.getId() + "-" + c.getMatricula() + "-" + c.getMarca() + "-" 
									+ c.getModelo() + "-" + c.getColor();
							
							pw.write(texto);
							pw.newLine();
						}
						
						pw.flush();
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 

					System.out.println("Fichero creado y rellenado");
					break;
					
				default:
					System.out.println("Opción incorrecta");
				}
				
			}while(continuar);

		} catch (Exception e) {
			System.err.println("Error -> " + e);
			e.printStackTrace();
		}
		
		System.out.println("Fin del programa");	
	}
	
	private static void escribirMenu() {
		System.out.println();
		System.out.println("Elige la opción deseada:");
		System.out.println("--------------------------");
		System.out.println("1 = Añadir nuevo coche");
		System.out.println("2 = Borrar coche por ID");
		System.out.println("3 = Consulta coche por ID");
		System.out.println("4 = Listado de coches");
		System.out.println("5 = Salir de la aplicación");
		System.out.println("6 = Exportar almacén a un documento de texto");
		System.out.println("--------------------------");
		System.out.println("¿Qué opción eliges?");
	}

}
