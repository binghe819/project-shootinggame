package com.binghe;


class Battle {
	User user;
	public Battle(User user) {
		this.user = user;
	}
	
	public void printStageClear() {

		System.out.println("   _____ _______       _____ ______    _____ _      ______          _____  ");
		System.out.println("  / ____|__   __|/\\   / ____|  ____|  / ____| |    |  ____|   /\\   |  __ \\ ");
		System.out.println(" | (___    | |  /  \\ | |  __| |__    | |    | |    | |__     /  \\  | |__) |");
		System.out.println("  \\___ \\   | | / /\\ \\| | |_ |  __|   | |    | |    |  __|   / /\\ \\ |  _  / ");
		System.out.println("  ____) |  | |/ ____ \\ |__| | |____  | |____| |____| |____ / ____ \\| | \\ \\ ");
		System.out.println(" |_____/   |_/_/    \\_\\_____|______|  \\_____|______|______/_/    \\_\\_|  \\_\\");

	}
	
	public void printStageFailed() {
		
	}
	
	public void show() {
		for (int k = 0; k < 60; k++) System.out.println();
		System.out.print("┌");
		for(int i = 0; i < 40; i++)System.out.print("─");
		System.out.println("┐");
		
		System.out.println("  ----------------- 전투 ----------------  \n");
		
		System.out.print("  ◆ STAGE 1  -  ");
		if(this.user.stage1==false)
			System.out.print("◯");
		else if(this.user.stage1 == true)
			System.out.print("●");
		
		
		System.out.print("\n\n  ◆ STAGE 2  -  ");
		if(this.user.stage2 == false)
			System.out.print("◯");
		else if(this.user.stage2 == true)
			System.out.print("●");
		
		System.out.print("\n\n  ◆ STAGE 3  -  ");
		if(this.user.stage3==false)
			System.out.print("◯");
		else if(this.user.stage3 == true)
			System.out.print("●");
		
		System.out.println();
		System.out.println();
		System.out.print("  ◯ : 클리어 X  /  ● : 클리어 O  ");
		
		System.out.println();
		System.out.println();
		
		// 하단 부분.
		System.out.print("└");
		for(int i = 0; i < 40; i++)System.out.print("─");
		System.out.println("┘");
		
		while(true) {
			int choice = ETC.input("스테이지를 선택해주세요.", "STAGE 1","STAGE 2","STAGE 3","나가기");
			if(choice == 1 || choice == 2 || choice == 3) {
				if(this.user.stage1 == false && choice == 2) {System.out.println("[ 스테이지 1을 완수해야 스테이지2가 열립니다. ]");continue;}
				if((this.user.stage2 == false || this.user.stage1 == false) && choice == 3) {System.out.println("[ 스테이지 2을 완수해야 스테이지3가 열립니다. ]");continue;}
				ETC.printLoading();
				Player player = new Player(this.user);
				GameObject th = new GameObject(player,choice,this.user);
				Thread gameth = new Thread(th);
				gameth.start();
				for (int k = 0; k < 60; k++) System.out.println();
				if(choice == 1) {
					System.out.println(" ------------------------------------------------------- ");
					System.out.println("   _____   _______               _____   ______    __"); 
					System.out.println("  / ____| |__   __|     /\\      / ____| |  ____|  /_ |");
					System.out.println(" | (___      | |       /  \\    | |  __  | |__      | |");
					System.out.println("  \\___ \\     | |      / /\\ \\   | | |_ | |  __|     | |");
					System.out.println("  ____) |    | |     / ____ \\  | |__| | | |____    | |");
					System.out.println(" |_____/     |_|    /_/    \\_\\  \\_____| |______|   |_|\n");				                                                      
					System.out.println(" ------------------------------------------------------- ");
				}
				else if(choice == 2) {
					System.out.println(" ------------------------------------------------------- ");
					System.out.println("   _____   _______               _____   ______    ___");  
					System.out.println("  / ____| |__   __|     /\\      / ____| |  ____|  |__ \\"); 
					System.out.println(" | (___      | |       /  \\    | |  __  | |__        ) |");
					System.out.println("  \\___ \\     | |      / /\\ \\   | | |_ | |  __|      / /"); 
					System.out.println("  ____) |    | |     / ____ \\  | |__| | | |____    / /_"); 
					System.out.println(" |_____/     |_|    /_/    \\_\\  \\_____| |______|  |____|\n");
					System.out.println(" ------------------------------------------------------- ");                                                                                               
				}
				
				else if(choice == 3) {
					System.out.println(" ------------------------------------------------------- ");
					System.out.println("   _____   _______               _____   ______   ____");  
					System.out.println("  / ____| |__   __|     /\\      / ____| |  ____| |___ \\"); 
					System.out.println(" | (___      | |       /  \\    | |  __  | |__      __) |");
					System.out.println("  \\___ \\     | |      / /\\ \\   | | |_ | |  __|    |__ <"); 
					System.out.println("  ____) |    | |     / ____ \\  | |__| | | |____   ___) |");
					System.out.println(" |_____/     |_|    /_/    \\_\\  \\_____| |______| |____/"); 
					System.out.println(" ------------------------------------------------------- ");
				}
				
				// 게임 진행시 메인스레드 일시정지.
				try {
					gameth.join();
				} catch (InterruptedException e) {e.printStackTrace();}
				ETC.printLoading();
				return;
			}
			else if(choice == 4) {return;}
		}
	}
}
