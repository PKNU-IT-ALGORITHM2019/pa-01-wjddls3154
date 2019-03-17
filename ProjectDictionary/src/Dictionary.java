
import java.io.File; //파일

import java.io.FileInputStream; //간단한 파일 입출력(바이트)

import java.io.FileNotFoundException;

import java.io.FileReader; //char단위로 파일을 읽어들임

import java.io.IOException;

import java.io.InputStreamReader;

import java.util.List; //리스트

import java.util.ArrayList;

import java.util.Arrays;

import java.util.Scanner;

import java.io.BufferedReader;

import java.io.DataInputStream;


public class Dictionary {

	

	static int Begin; // 시작

	static int End; // 끝
	
	static int data=3000000; // data값 300000으로 늘려놈.
	
	static int total=0; // read 했을때 나오는 총 단어 개수

	
	

	

	

	static int[] arraydata =new int[data]; // 출력할 변수배열 선언

	static String[] string = new String [data]; // 단어 전역 변수배열 선언

	static String[] form = new String [data];	// 형태 전역 변수배열 선언

	static String[] detail = new String [data];	// 세부설명 전역 변수배열 선언  

 

 

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
			
			//read 했을때 총 단어 개수가 total 출력.

		}else if(reply.equals("find")) {

			String result = search.next();

			int check = find(0,total-1,result);

			searching(check,result);

		}else if(reply.equals("exit")) {

			System.exit(0);

			//종료한다.
			
		}

	 }

	}

	 public static void searching(int check, String words) { // 이부분하다가 도저히 못하겟어서 이해는 했으나 올린사람들꺼 보구 참고해서 했습니당..

			

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

					if(string[check].compareToIgnoreCase(words) == 0) //찾는 단어가 string배열안의 단어와 같다면 

						arraydata[m++] = check; //arraydata배열안에 저장을 해준다.

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

					System.out.println(string[arraydata[j]] + " " + form[arraydata[j]] + " " + detail[arraydata[j]]); //출력

			}

		}

	 
	 // read 부분
	public static void read(String file) {

	        try {

	        	Scanner read = new Scanner(new File(file)); //import java.io.File;
                 // import java.util.Scanner;
	        	
	            while (read.hasNext()) {// 값이 있는지 확인.

	            	String line = null;

	            	line = read.nextLine();

	            	int end_point = line.indexOf(")"); // 괄호를 기준으로 형태를 나눔

	            	int start_point = line.indexOf("(");// ""

	            	if(!line.equals("")) { 

					string[total] = line.substring(0,start_point-1); //"("이전의 문자열(단어)을 string total 배열에 집어넣음

					form[total] = line.substring(start_point,end_point+1); // "() "괄호 안의 문자열을 form total 배열에 집어넣음

					detail[total] = line.substring(end_point+1);// 나머지(세부사항 설명) 집어넣음

					System.out.println(string[total]);

	              	total = total + 1; // read 했을때 나오는 총 단어수

	            	}

	            }

	            read.close();
	            
                //read 종료.
	            
	        } catch (IOException e) {
                // import java.io.IOException;
	            e.printStackTrace();

	        }

	        

	}

	  public static int find(int start, int end, String word) {

	       
		    int middle = (start + end)/2;
                 //middle은 start와 end를 더한것의 절반 딱 중간.
			if(end < start){

				 return -1;

			}

			if(string[middle].compareToIgnoreCase(word)>0){ // 대소문자 무시하면서  문자열을 비교 한다.

				return find(start,middle-1,word);

			}

			else if (string[middle].equalsIgnoreCase(word)){

				return middle;

			}

			else 

				return find(middle+1,end,word);

			

		}

	 

  }