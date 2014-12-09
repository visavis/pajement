package org.wissenteil.pajement;

import java.util.Random;

public class Cale {

	public static void sortuj(int arrayLength, int varity, boolean printing) throws InterruptedException {
		
		Random gen = new Random();
		int[] myArray = new int[arrayLength];
		for (int i = 0; i < myArray.length; i++) {
			myArray[i] = gen.nextInt(varity);
		}
		int i, placeholder, j, innerCyclesGlobalCounter;
		innerCyclesGlobalCounter = 0;
//		for (int x = 0; x < myArray.length; x++) {
//			System.out.print(myArray[x] + ", ");
//		}
		for (i = 1; i < myArray.length; i++) {
			j = i;
			placeholder = myArray[i];
//			System.out.print("\n");
			while (j > 0 && myArray[j - 1] > placeholder) {
				myArray[j] = myArray[j - 1];
//				for (int x = 0; x < myArray.length; x++) {
//					if (x == j) {
//						System.out.print(" " + myArray[x] + " --> ");
//					} else {
//						System.out.print(myArray[x] + ", ");
//					}
//				}
//				System.out.print("\n");
				j--;
				innerCyclesGlobalCounter ++;
//				Thread.sleep(500);
			}
			myArray[j] = placeholder;
			
		}
//		System.out.println("Liczb do sortowania: " + i);
//		System.out.println("Wewnętrznyprzejśćch przejść średnio na liczbę: " + innerCyclesGlobalCounter / i);
//		System.out.println("Wewnętrznych przejść ogółem: " + innerCyclesGlobalCounter);			
//		System.out.print("\n");
		int v = varity;
		int c = innerCyclesGlobalCounter;
		if (printing) {
		System.out.println(" Ile liczb ogółem   Zróżnicowanie (szt)  Ilość przejść na liczbę  Ilość przejść ogółem ");
		System.out.println("        " + i + "                  " + v + "                     " + c / i + "                     " + c + "            ");
		}
//      wydruk zbiorów --- wyłączony dla czytelności
//		for (int x = 0; x < myArray.length; x++) {
//			System.out.print(myArray[x] + ", ");
//		}
	}

	public static void main(String[] args) throws InterruptedException {
		for (int i = 10; i <= 1000; i+= 10){
			if (i == 10 || i == 1000) {
				sortuj(i, i, true);
			} else {
				sortuj(i, i, false);
			}
		}
		
	}
}
