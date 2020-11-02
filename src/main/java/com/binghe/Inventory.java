package com.binghe;

// 인벤토리.
class Inventory {
	User user;
	public Inventory(User user) {
		this.user = user;
	}
	
	// 인벤토리에 대한 정보를 보여주는 메소드 ( UI )
	public void show() {
		inventory:
		while(true) {
			for (int k = 0; k < 60; k++) System.out.println();
			// 상단 부분.
			System.out.print("┌");
			for(int i = 0; i < 40; i++)System.out.print("─");
			System.out.println("┐");
			
			System.out.printf("  보유 금액 : %d원.\n",this.user.userMoney);
			
			System.out.printf("  ---------------- 격납고 ----------------  \n",this.user.name);
			// 보유 아이템 표시.
			System.out.print("  보유 아이템 : ");
			user.showItem();
			System.out.print("  보유 비행기 : ");
			user.showFlight();
			
			System.out.println("  --------------- 전투기 상태 -------------  ");
			System.out.printf("  사용중인 비행기 : ");
			user.showUsedFlight();
			System.out.printf("  장착한 아이템 : ");
			user.showUsedItem();
			
			System.out.println("  -----------------------------------  ");
			
			int choice = ETC.input("다음중 무엇을 하시겠습니까?", "비행기 교체", "아이템 장착", "나가기");
			switch(choice) {
			case 1: // 비행기 교체.
				for (int k = 0; k < 60; k++) System.out.println();
				// 보유중인 비행기 기종 불러오기.
				System.out.print("보유하신 기종 : ");
				user.showFlight();
				
				// 선택지 만들기위한 배열.
				String[] flightsName = new String[user.보유중인비행기.size()];
				for(int i = 0; i < user.보유중인비행기.size(); i++) {
					flightsName[i] = user.보유중인비행기.get(i).name;
				}
				
				// 보유중인 비행기가 없을때 다시 인벤토리로 back.
				if(user.보유중인비행기.isEmpty()) {System.out.println("교체할 비행기가 없습니다.");continue inventory;}
				
				// 바꿀 비행기 기종 선택.
				choice = ETC.input("어떤 기종으로 바꾸시겠습니까?", flightsName);
				// 처음 비행기를 사용할 때의 if.
				if(user.장착중인비행기 == null) {
					user.장착중인비행기 = user.보유중인비행기.get(choice-1);
					user.보유중인비행기.remove(choice-1);
					System.out.printf("%s 전투기를 발진합니다.\n",user.장착중인비행기.name);
				}
				// 장착중인 비행기가 있고, 보유중인 비행기에서 교체를 할 때의 if.
				else {
					Jet flightTemp;
					flightTemp = user.장착중인비행기;
					user.장착중인비행기 = user.보유중인비행기.get(choice-1);
					user.보유중인비행기.remove(choice-1);
					user.보유중인비행기.add(flightTemp);
				}
				break;
				
			case 2: // 아이템 장착.
				for (int k = 0; k < 60; k++) System.out.println();
				// 보유중인 아이템 불러오기.
				System.out.print("\n보유 아이템 : ");
				user.showItem();
				
				// 선택지를 만들기위한 배열.
				String[] itemsName = new String[user.보유중인아이템.size()];
				for(int i = 0; i < user.보유중인아이템.size(); i++) {
					itemsName[i] = user.보유중인아이템.get(i).name;
				}
				
				// 보유중인 아이템이 없을때 다시 인벤토리로 back.
				if(user.보유중인아이템.isEmpty()) {System.out.println("장착할 아이템이 없습니다.");continue inventory;}
				
				// 장착할 아이템 선택.
				choice = ETC.input("어떤 아이템을 장착하시겠습니까?", itemsName);
				// 처음 아이템을 장착할 때의 if.
				if(user.장착중인아이템 == null) {
					user.장착중인아이템 = user.보유중인아이템.get(choice-1);
					user.보유중인아이템.remove(choice-1);
					System.out.printf("%s 아이템을 장착했습니다.\n",user.장착중인아이템.name);
				}
				// 장착한 아이템이 있고, 보유중인 아이템도 있을 때 교체를 하는 if.
				else {
					Item itemTemp;
					itemTemp = user.장착중인아이템;
					user.장착중인아이템  = user.보유중인아이템.get(choice - 1);
					user.보유중인아이템.remove(choice-1);
					user.보유중인아이템.add(itemTemp);
				}
				
				break;
				
			case 3: // 나가기.
				return;
			}
		}
		
	}// show 메소드 끝.
}
