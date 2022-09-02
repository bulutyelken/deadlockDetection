package deadlockpackage;
import java.util.LinkedList;
import java.util.Scanner;

public class Deadlock {
	public static boolean arrayKarsilastir(int satir[],int available[]) {
		for(int i=0; i<satir.length; i++) {
			if(satir[i] > available[i]) {
				return false;
			}
		}
		return true;
	}
	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the number of processes: ");
		int processSayisi = input.nextInt();						
		System.out.println("Process sayisi: "+processSayisi);
		
		System.out.print("Enter the number of resources: ");
		int resourceSayisi = input.nextInt();						
		System.out.println("Resource sayisi: "+resourceSayisi);	


		int allocMatrix[][] = new int[processSayisi][resourceSayisi];
		int reqMatrix[][] = new int[processSayisi][resourceSayisi];
		int resSayilari[] = new int[resourceSayisi];
		int available[] = new int[resourceSayisi];
		boolean finish[] = new boolean[processSayisi];
		boolean deadlockVar=false;
		LinkedList<String> calismaSirasi = new LinkedList<String>();
		
		
		//finish degerleri baslangicta hepsi false
		for(int i=0; i<processSayisi; i++) {
			finish[i] = false;
		}
		
		
		

		//Allocation Matrix'i al
		for (int i=0; i<processSayisi; i++) {
			for(int j=0; j<resourceSayisi; j++) {
				System.out.print("Enter Allocation for process "+i+", Resource "+j+" : ");
				allocMatrix[i][j] = input.nextInt();
			}
		}
		
		//Request Matrix'i al
		for (int i=0; i<processSayisi; i++) {
			for(int j=0; j<resourceSayisi; j++) {
				System.out.print("Enter Requested Resource "+j+" for process "+i+";");
				reqMatrix[i][j] = input.nextInt();
			}
		}
		
		
		
		//Resource Sayilarini al
		for (int i=0; i<resourceSayisi; i++) {
			System.out.print("Enter the number of Resource R"+i+":");
			resSayilari[i]=input.nextInt();
		}
		
		//Baslangicta Resource sayilarini available'a atıyor, sonradan dusecek
		for(int i=0; i<resSayilari.length; i++) {
			available[i] = resSayilari[i];
		}
		
		//Available'yi hesapla
		for (int i=0; i<processSayisi; i++) {
			for(int j=0; j<resourceSayisi; j++) {
				if(allocMatrix[i][j] != 0) {
					available[j] -= allocMatrix[i][j];
				}
				
			}
		}
		System.out.println("");
		
		
		//Available Bastir
		System.out.println("");
		System.out.println("Available Resources");
		System.out.println("-----------------------------");
		for(int i=0; i<available.length ; i++) {
			System.out.print(available[i]+"  ");
			
		}
		System.out.println("");
		System.out.println("");
		
		
		//Allocation matrix'i bastir
		System.out.println("Allocation Matrix");
		System.out.println("-----------------------------");
		for (int i=0; i<processSayisi; i++) {
			for(int j=0; j<resourceSayisi; j++) {
				System.out.print(allocMatrix[i][j]+"  ");
			}
			System.out.println("");
			
		}
		
		System.out.println("");
		//Request matrix'i bastir
		System.out.println("Request Matrix");
		System.out.println("-----------------------------");
		for (int i=0; i<processSayisi; i++) {
			for(int j=0; j<resourceSayisi; j++) {
				System.out.print(reqMatrix[i][j]+"  ");
			}
			System.out.println("");
			
		}
		
		int satir[] = new int[resourceSayisi];
		
		
		
		
		
		int i=0;
		
		input.close(); //artik input almayacagiz
		for(int t=0; t<4; t++) {
			i=0;
			while(i<processSayisi) {
				if(!finish[i]) {
					for (int j=0; j<resourceSayisi; j++) {
						satir[j] = reqMatrix[i][j];
					}
					if(arrayKarsilastir(satir, available)) {

						for(int z=0; z<resourceSayisi; z++) {
							available[z] += allocMatrix[i][z];
						}
						/*System.out.println("Available");
						System.out.println("-------------------------");
						for(int z=0; z<resourceSayisi; z++) {
							System.out.print(available[z]+"  ");			
						}*/     
						//Her iterasyonda available degerlerini gormek istersek yukariyi uncommentleyebiliriz.
						finish[i % processSayisi] = true;
						calismaSirasi.add("P"+Integer.toString(i));
					}
					
				}
				i++;
			}//while end
		}
		
		
		for(i=0; i<finish.length; i++) {
			if(!finish[i]) {
				deadlockVar=true;
			}
		}
		if(!deadlockVar) {
			System.out.println("");
			System.out.println("-----------------------");
			System.out.println("Deadlock not Detected");
			System.out.println("-----------------------");
			System.out.println("System is in Safe State");
			System.out.println("-----------------------");
		}
		else {
			System.out.println("");
			System.out.println("-----------------------");
			System.out.println("Deadlock Detected");
			System.out.println("-----------------------");
			System.out.println("System is not in Safe State");
			System.out.println("-----------------------");
		}
		System.out.println("");
		System.out.println("Calisma Sirasi");
		System.out.println("-----------------------");
		System.out.println(calismaSirasi);
		
		

		
	}

}