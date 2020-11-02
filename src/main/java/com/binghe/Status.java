package com.binghe;

// 상태창.
class Status {
	User user;
	public Status(User user) {
		this.user = user;
	}
	
	// 상태창에 대한 정보를 보여주는 메소드. ( UI )
	public void show() {
		for (int k = 0; k < 60; k++) System.out.println();
		// 상단 부분.
		System.out.print("┌");
		for(int i = 0; i < 40; i++)System.out.print("─");
		System.out.println("┐");
		System.out.printf("  보유 금액 : %d원.\n",this.user.userMoney);
		
		System.out.printf("  ------------- 사용중인 비행기 ------------  \n",this.user.name);
		if(user.장착중인비행기 == null) {
			System.out.print("  사용중인 비행기가 없습니다.\n");
		}
		else {
			System.out.printf("  비행기 기종 : %s\n",user.장착중인비행기.name);
			System.out.printf("  HP : %d\n",user.장착중인비행기.HP);
			System.out.printf("  총알 파워 : %d\n",user.장착중인비행기.bulletPower);
			System.out.printf("  스킬 : %s\n",user.장착중인비행기.description);
			System.out.println("  ------------- 장착중인 아이템 ------------  ");
			System.out.print("  장착 아이템 : ");user.showUsedItem();
		}
		
		System.out.println("  --------------------------------------  ");
		// 하단 부분.
		System.out.print("└");
		for(int i = 0; i < 40; i++)System.out.print("─");
		System.out.println("┘");
		int choice = ETC.exit();
		if(choice == 0) {return;}
	}
	
	
}
