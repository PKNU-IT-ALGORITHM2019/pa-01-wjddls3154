
import java.io.File; //����

import java.io.FileInputStream; //������ ���� �����(����Ʈ)

import java.io.FileNotFoundException;

import java.io.FileReader; //char������ ������ �о����

import java.io.IOException;

import java.io.InputStreamReader;

import java.util.List; //����Ʈ

import java.util.ArrayList;

import java.util.Arrays;

import java.util.Scanner;

import java.io.BufferedReader;

import java.io.DataInputStream;


public class Dictionary {

	

	static int Begin; // ����

	static int End; // ��
	
	static int data=3000000; // data�� 300000���� �÷���.
	
	static int total=0; // read ������ ������ �� �ܾ� ����

	
	

	

	

	static int[] arraydata =new int[data]; // ����� �����迭 ����

	static String[] string = new String [data]; // �ܾ� ���� �����迭 ����

	static String[] form = new String [data];	// ���� ���� �����迭 ����

	static String[] detail = new String [data];	// ���μ��� ���� �����迭 ����  

 

 

	public static void main(String[] args) {

		

		while(true) { 

		Scanner search = new Scanner(System.in);
        // import java.util.Scanner;
		System.out.print("$:");

		String reply = search.next();

			

		if(reply.equals("read")) { 

			String result = search.next();

			read(result);

		}else if(reply.equals("total")){

			System.out.println(total);
			
			//read ������ �� �ܾ� ������ total ���.

		}else if(reply.equals("find")) {

			String result = search.next();

			int check = find(0,total-1,result);

			searching(check,result);

		}else if(reply.equals("exit")) {

			System.exit(0);

			//�����Ѵ�.
			
		}

	 }

	}

	 public static void searching(int check, String words) { // �̺κ��ϴٰ� ������ ���ϰپ ���ش� ������ �ø�����鲨 ���� �����ؼ� �߽��ϴ�..

			

			if (check < 0) {

				System.out.println("Not found.");

				System.out.println("- - -");

				System.out.println(string[check + 1] + " " + form[check + 1]);

			}

			else if (words.compareToIgnoreCase(string[check]) != 0){

				System.out.println("Not found.");

				System.out.println(string[check] + " " + form[check]);

				System.out.println("- - -");

				System.out.println(string[check + 1] + " " + form[check + 1]);

			}

			else {

				int m = 0;

				while(true) {

					if(check < 0) 

						break;

					if(string[check].compareToIgnoreCase(words) == 0) //ã�� �ܾ string�迭���� �ܾ�� ���ٸ� 

						arraydata[m++] = check; //arraydata�迭�ȿ� ������ ���ش�.

					check--;

				}

				check = check + 1;

				while(true) {

					if (check > (total-1))	

						break;

					if(string[check].compareToIgnoreCase(words) == 0) 

						arraydata[m++] = check;

					check++;

				}

				System.out.println("Found " + m + " items.");

				for(int j = 0; j < m; j++)

					System.out.println(string[arraydata[j]] + " " + form[arraydata[j]] + " " + detail[arraydata[j]]); //���

			}

		}

	 
	 // read �κ�
	public static void read(String file) {

	        try {

	        	Scanner read = new Scanner(new File(file)); //import java.io.File;
                 // import java.util.Scanner;
	        	
	            while (read.hasNext()) {// ���� �ִ��� Ȯ��.

	            	String line = null;

	            	line = read.nextLine();

	            	int end_point = line.indexOf(")"); // ��ȣ�� �������� ���¸� ����

	            	int start_point = line.indexOf("(");// ""

	            	if(!line.equals("")) { 

					string[total] = line.substring(0,start_point-1); //"("������ ���ڿ�(�ܾ�)�� string total �迭�� �������

					form[total] = line.substring(start_point,end_point+1); // "() "��ȣ ���� ���ڿ��� form total �迭�� �������

					detail[total] = line.substring(end_point+1);// ������(���λ��� ����) �������

					System.out.println(string[total]);

	              	total = total + 1; // read ������ ������ �� �ܾ��

	            	}

	            }

	            read.close();
	            
                //read ����.
	            
	        } catch (IOException e) {
                // import java.io.IOException;
	            e.printStackTrace();

	        }

	        

	}

	  public static int find(int start, int end, String word) {

	       
		    int middle = (start + end)/2;
                 //middle�� start�� end�� ���Ѱ��� ���� �� �߰�.
			if(end < start){

				 return -1;

			}

			if(string[middle].compareToIgnoreCase(word)>0){ // ��ҹ��� �����ϸ鼭  ���ڿ��� �� �Ѵ�.

				return find(start,middle-1,word);

			}

			else if (string[middle].equalsIgnoreCase(word)){

				return middle;

			}

			else 

				return find(middle+1,end,word);

			

		}

	 

  }