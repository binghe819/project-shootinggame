package com.binghe;

// 상점.
class Shop {
	User user;
	Item item;
	Jet jet;
	
	public Shop(User user, Item item, Jet jet) {
		this.user = user;
		this.item = item;
		this.jet = jet;
	}
	
	void buyFlight(Jet flight, User user) {
		while(true) {
			for (int k = 0; k < 60; k++) System.out.println();
			System.out.printf("비행기 기종 : %s\n",flight.name);
			System.out.printf("비행기 가격 : %d\n",flight.price);
			System.out.printf("설명 : %s\n",flight.description);
			int choice = ETC.input("구매하시겠습니까?", "예","아니오");
			if(choice == 1) {
				if(flight.price > user.userMoney) {
					System.out.println("[ 잔액이 부족합니다. ]");
					break;
				}
				/*
				 * 동일 기종이 있을시 구매를 막는 메소드.. 마지막에 추가하자.
				 */
//				else if(user.보유중인비행기.contains(flight)) {
//					System.out.println("동일 기종을 이미 가지고있습니다.");
//					break;
//				}
				else {
					System.out.printf("%s를 구매하셨습니다.\n",flight.name);
					user.userMoney -= flight.price;
					user.보유중인비행기.add(flight);
					break;
				}
			}
			else if(choice == 2) 
				return;
		}
	}
	
	void buyItem(Item item, User user) {
		while(true) {
			for (int k = 0; k < 60; k++) System.out.println();
			System.out.printf("아이템 이름 : %s\n",item.name);
			System.out.printf("아이템 가격 : %d\n",item.price);
			System.out.printf("아이템 설명 : %s\n",item.description);
			int choice = ETC.input("구매하시겠습니까?", "예","아니오");
			if(choice == 1) {
				if(item.price > user.userMoney) {
					System.out.println("[ 잔액이 부족합니다. ]");
					break;
				}
				else {
					System.out.printf("%s를 구매하셨습니다.\n",item.name);
					user.보유중인아이템.add(item);
					user.userMoney -= item.price;
					break;
				}
			}
			else if(choice == 2)
				return;
		}
	}
	
	// 보여주는 메소드.
	public void show() {
		while(true) {
			for (int k = 0; k < 60; k++) System.out.println();
			System.out.println("[ ---------상점--------- ]");
			int choice = ETC.input("어떤 상점으로 가시겠습니까?", "아이템상점","비행기상점","나간다");
			switch(choice) {
			case 1: // 아이템.
				item:
				while(true) {
					for (int k = 0; k < 60; k++) System.out.println();
					choice = ETC.input("무엇을 구매하시겠습니까?", "HP플러스+10 - 300원","HP플러스+30 - 700원", "상점으로 돌아간다.");
					if(choice == 1) {
						Item HP10 = new HP플러스10();
						buyItem(HP10, this.user);
					}
					else if(choice == 2) {
						Item HP30 = new HP플러스30();
						buyItem(HP30, this.user);
					}
					else if(choice == 3) 
						break;
				} // item 반복문의 끝.
				break; // case1의 끝.

			case 2: // 비행기.
				while(true) {
					for (int k = 0; k < 60; k++) System.out.println();
					choice = ETC.input("무엇을 구매하시겠습니까?", "F4K - 500원","F15K - 1000원","F35K - 2000원","상점으로 돌아간다.");
					// 
					if(choice == 1) {
						Jet F4K = new F4K();
						buyFlight(F4K, this.user);
					}
					else if(choice == 2) {
						Jet F15K = new F15K();
						buyFlight(F15K, this.user);
					}
					else if(choice == 3) {
						Jet F35K = new F35K();
						buyFlight(F35K, this.user);
					}
					else if(choice == 4) // 비행기 상점을 나간다.
						break;
				} // flight 반복문의 끝.
				break; // case 2의 끝.
				
			case 3: // 나간다.
				return;
			}
		}
	}// show함수 끝.
	
	
}// 클래스 끝.
