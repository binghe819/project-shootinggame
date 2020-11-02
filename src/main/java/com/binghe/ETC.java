package com.binghe;

import java.io.*;
import java.util.Scanner;

class ETC {
	
	static Scanner sc = new Scanner(System.in);
	
	// 여러가지 상황에서 유저의 선택을 받아야 할때 쓰는 질문메소드. ( 정적 변수 )
	static int input(String question, String...strings) { 
		Music 버튼효과음 = new Music("src/main/SOURCE/버튼음.wav"); // 효과음 설정.
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("[ "+question+" ]");
			for(int i = 0; i<strings.length; i++) {
				System.out.println((i+1)+". "+strings[i]);
			}
			int choice = sc.nextInt();
			버튼효과음.playOnetime(1); // 버튼 누를때마다 효과음 실행.
			if(choice == 7777777)
				return choice;
			if(choice > 0 && choice <= strings.length)
				return choice;
			else {
				System.out.println("[error!] 잘못된 입력입니다. 다시 입력해주세요.");
				return input(question, strings);
			}
				
		}
		catch(Exception e) {
			System.out.println("[error!] 잘못된 입력입니다. 다시 입력해주세요.");
			return input(question, strings);
		}
	}
	
	// 한글자 한글자 출력하는 스레드 메소드.
	static void printOneByOne(String story,int speed) {
		for(int i = 0 ; i < story.length(); i++) {
			System.out.print(story.charAt(i));
			try {
				Thread.sleep(speed);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	// 상태창에서 나가기 버튼을 만들기 위한 메소드.
	static int exit() {
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("  0. 나가기");
			int choice = sc.nextInt();
			if(choice != 0) {
				System.out.println("[error!] 잘못된 입력입니다. 다시 입력해주세요.");
				return exit();
			}
			else {
				return choice;
			}
		}
		catch(Exception e) {
			System.out.println("[error!] 잘못된 입력입니다. 다시 입력해주세요.");
			return exit();
		}
	}
	
	// 게임 저장 메소드.
	static void save(User user) {
		File file = new File("save.dat");
		try {
			FileOutputStream fout = new FileOutputStream(file);
			ObjectOutputStream oout = new ObjectOutputStream(fout);
			oout.writeObject(user);
			System.out.println("저장되었습니다.");
		}catch(IOException e) {e.printStackTrace();}
	}
	
	// 로딩화면 메소드.
	static void printLoading() {
		char[] box = new char[50];
		// char배열을 사용해서 로딩 박스 채우기.
		for(int i = 0 ; i < 50; i++) {
			box[i] = '□';
		}
		
		for(int i = 0; i < 50; i++) {
			for (int k = 0; k < 60; k++) System.out.println(); // 계속해서 엔터치기. ( 콘솔창 내용 지우기 역할 )
			
			// 상단 부분.
			System.out.print("┌");
			for(int q = 0; q < 60; q++)System.out.print("─");
			System.out.println("┐");
			
			
			
			System.out.println("\n                 2019 World War에 오신걸 환영합니다.\n");
			System.out.print("       ");
//			if(i <= 10)
//				System.out.println(" -------------- 인터넷 접속 확인중.... -------------- ");
//			else if(i > 10 && i <= 20)
//				System.out.println(" --------------  게임 서버 접속중.... -------------- ");
//			else if(i > 20 && i <= 30)
//				System.out.println(" -----------  사용자 접속 상태 확인중.... ----------- ");
//			else if(i > 30 && i <= 50)
//				System.out.println(" --------------- Loading . . . . --------------- ");
			System.out.println(" --------------- Loading . . . . --------------- ");
				
			// 로딩 박스 출력.
			System.out.print("       ");
			for(int j = 0 ; j < 49; j++) {System.out.print(box[j]);}
			System.out.println();
			
			
			// 하단 부분.
			System.out.println();
			System.out.print("└");
			for(int w = 0; w < 60; w++)System.out.print("─");
			System.out.print("┘");
			
			box[i] = '■'; // 로딩박스 채우기.
			
			// 진짜 로딩되는 것처럼 보이기 위해 간격두기위한 sleep.
			try {
				Thread.sleep(80);
				System.out.println();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (int k = 0; k < 60; k++) System.out.println(); // 계속해서 엔터치기. ( 콘솔창 내용 지우기 역할 )
	}
	
	// 로그인창 메소드. ( 첫 화면 )
	static User Login() {
		// 시작 로고 부분.
		System.out.println("\n" +
				"  ______    ______    ______    ______         __       __                      __        __        __       __                     \n" +
				" /      \\  /      \\  /      \\  /      \\       /  |  _  /  |                    /  |      /  |      /  |  _  /  |                    \n" +
				"/$$$$$$  |/$$$$$$  |/$$$$$$  |/$$$$$$  |      $$ | / \\ $$ |  ______    ______  $$ |  ____$$ |      $$ | / \\ $$ |  ______    ______  \n" +
				"$$____$$ |$$$  \\$$ |$$____$$ |$$$  \\$$ |      $$ |/$  \\$$ | /      \\  /      \\ $$ | /    $$ |      $$ |/$  \\$$ | /      \\  /      \\ \n" +
				" /    $$/ $$$$  $$ | /    $$/ $$$$  $$ |      $$ /$$$  $$ |/$$$$$$  |/$$$$$$  |$$ |/$$$$$$$ |      $$ /$$$  $$ | $$$$$$  |/$$$$$$  |\n" +
				"/$$$$$$/  $$ $$ $$ |/$$$$$$/  $$ $$ $$ |      $$ $$/$$ $$ |$$ |  $$ |$$ |  $$/ $$ |$$ |  $$ |      $$ $$/$$ $$ | /    $$ |$$ |  $$/ \n" +
				"$$ |_____ $$ \\$$$$ |$$ |_____ $$ \\$$$$ |      $$$$/  $$$$ |$$ \\__$$ |$$ |      $$ |$$ \\__$$ |      $$$$/  $$$$ |/$$$$$$$ |$$ |      \n" +
				"$$       |$$   $$$/ $$       |$$   $$$/       $$$/    $$$ |$$    $$/ $$ |      $$ |$$    $$ |      $$$/    $$$ |$$    $$ |$$ |      \n" +
				"$$$$$$$$/  $$$$$$/  $$$$$$$$/  $$$$$$/        $$/      $$/  $$$$$$/  $$/       $$/  $$$$$$$/       $$/      $$/  $$$$$$$/ $$/       \n" +
				"                                                                                                                                    \n" +
				"                                                                                                                                    \n" +
				"                                                                                                                                    \n");

		Scanner sc = new Scanner(System.in);
		int choice = ETC.input("2020 World War", "새 게임","불러오기","게임종료");
		if(choice == 1) { // 새 게임.
			System.out.println("[ 만드실 계정 이름을 입력해주세요. ]");
			System.out.print(" ◆ 계정 : ");
			String userName = sc.next();
			User user1 = new User(userName);
			ETC.printLoading();
			스토리 스토리 = new 스토리();
			return user1;
		}
		else if(choice == 2) { // 불러오기.
			User user1;
			File file = new File("save.dat");
			try {
				FileInputStream fin = new FileInputStream(file);
	            ObjectInputStream oin = new ObjectInputStream(fin);
	            user1 = (User)oin.readObject();
	            ETC.printLoading();
	            return user1;
			}catch(Exception e) {
				System.out.println("저장된 계정이 없습니다.");
				return Login();
			}
		}
		else if(choice == 3) { // 게임 종료.
			System.exit(0);
		}
		return null;
	}

	// 게임 설명 메소드.
	static void gameDescription() {
		for (int k = 0; k < 60; k++) System.out.println();
		// 상단 부분.
		System.out.print("┌");
		for(int i = 0; i < 56; i++)System.out.print("─");
		System.out.println("┐");
		
		//내용 부분
		System.out.println("\n -------------------------------------------------------- \n"); 
		System.out.println("        _____   _______    ____    _____   __     __");
		System.out.println("       / ____| |__   __|  / __ \\  |  __ \\  \\ \\   / /");
		System.out.println("      | (___      | |    | |  | | | |__) |  \\ \\_/ /");
		System.out.println("       \\___ \\     | |    | |  | | |  _  /    \\   /");
		System.out.println("       ____) |    | |    | |__| | | | \\ \\     | |");
		System.out.println("      |_____/     |_|     \\____/  |_|  \\_\\    |_|\n");
		System.out.println("\n -------------------------------------------------------- \n");      
		System.out.println(" 2019년 연이은 여러 나라들의 패권싸움과 갈등으로 인해 세계 대전이 발발한다.\n\n"
				+ " 지리적 전략적인 위치에 있는 대한민국은 여러 나라들의 표적이 되고 만다.\n\n"
				+ " 많은 나라들이 한국에 전투기를 보내 한국을 점령하려고 하고 있다.\n\n"
				+ " 이에 대한민국 공군은 공격해 오는 많은 나라들의 전투기로부터 영토를 지키려한다.\n\n"
				+ " 대한민국의 영토와 자주권은 여러분에게 달렸습니다. 꼭 지켜주기 바랍니다!\n\n");
		
		System.out.println();
		
		System.out.println(" -------------------------------------------------------- \n");
		System.out.println("                 _  __  ______  __     __");
		System.out.println("                | |/ / |  ____| \\ \\   / /");
		System.out.println("                | ' /  | |__     \\ \\_/ / ");
		System.out.println("                |  <   |  __|     \\   /  ");
		System.out.println("                | . \\  | |____     | |   ");
		System.out.println("                |_|\\_\\ |______|    |_|   ");
		System.out.println("\n\n -------------------------------------------------------- \n");
		System.out.println("          ◆ 방향키 : left ← / right → / up ↑ / down ↓\n");
		System.out.println("          ◆ 미사일 발사 : Space ( 스페이스 바 )\n");
		System.out.println("          ◆ 아이템 사용 : Ctrl ( 컨트롤 )\n");
		System.out.println("          ◆ 스 킬 발동 : Z ( Z키 )\n");
		
		// 하단 부분.
		System.out.print("\n└");
		for(int i = 0; i < 56; i++)System.out.print("─");
		System.out.println("┘");
		
		// 나가기 버튼.
		int choice = ETC.exit();
		if(choice == 0) {return;}
	}
} // ETC 클래스 끝.
