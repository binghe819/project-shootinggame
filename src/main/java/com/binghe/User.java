package com.binghe;

import java.io.Serializable;
import java.util.ArrayList;

class User implements Serializable{
	String name;
	int userMoney;
	Jet 장착중인비행기 = null;
	ArrayList<Jet> 보유중인비행기 = new ArrayList<Jet>();
	Item 장착중인아이템 = null;
	ArrayList<Item> 보유중인아이템 = new ArrayList<Item>();
	boolean stage1 = false;
	boolean stage2 = false;
	boolean stage3 = false;
	
	User(String name) {
		this.name = name;
		this.userMoney = 1000;
		this.장착중인비행기 = new F4K();
	}
	
	void showItem() {
		if(this.보유중인아이템.isEmpty()) {
			System.out.print("보유중인 아이템이 없습니다.");
		}
		else {
			for(int i = 0; i < this.보유중인아이템.size(); i++) {
				Item item = this.보유중인아이템.get(i);
				System.out.print(item.name + " ");
			}
		}
		System.out.println();
	}
	
	void showFlight() {
		if(this.보유중인비행기.isEmpty()) {
			System.out.print("격납고에 전투기가 없습니다.");
		}
		else {
			for(int i = 0; i < this.보유중인비행기.size(); i++) {
				System.out.print(this.보유중인비행기.get(i).name + " ");
			}
		}
		System.out.println();
	}
	
	void showUsedFlight() {
		if(this.장착중인비행기 == null) {
			System.out.print("사용중인 비행기가 없습니다.");
		}
		else {
			System.out.print(this.장착중인비행기.name);
		}
		System.out.println();
	}
	
	void showUsedItem() {
		if(this.장착중인아이템 == null) {
			System.out.print("사용중인 아이템이 없습니다.");
		}
		else {
			System.out.print(this.장착중인아이템.name);
		}
		System.out.println();
	}
}
