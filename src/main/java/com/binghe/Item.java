package com.binghe;

import java.io.Serializable;

class Item implements Serializable{
	String name; // 아이템들만의 이름.
	int price; // 아이템들만의 가격.
	int HPPlus;
	String description;
}

class HP플러스10 extends Item{
	public HP플러스10() {
		this.name = "HP플러스10";
		this.price = 300;
		this.HPPlus = 10;
		this.description = "전투중 전투기의 HP를 10만큼 올릴 수 있는 아이템입니다.";
	}
}

class HP플러스30 extends Item{
	HP플러스30(){
		this.name = "HP플러스30";
		this.price = 700;
		this.HPPlus = 30;
		this.description = "전투중 전투기의 HP를 30만큼 올릴 수 있는 아이템입니다.";
	}
}
