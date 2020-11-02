package com.binghe;

import java.io.Serializable;

public class Jet implements Serializable{
	String name; // 비행기 이름
	int HP; // 비행기의 HP
	int bulletPower; // 비행기 총알 파워
	char design;
	int price; // 비행기 가격
	String description;
}

class F4K extends Jet{
	public F4K() {
		this.name = "F4K";
		this.HP = 20;
		this.bulletPower = 1;
		this.price = 500;
		this.description = "기본 비행기.";
	}
}

class F15K extends Jet{
	public F15K() {
		this.name = "F15K";
		this.HP = 25;
		this.bulletPower = 2;
		this.price = 1000;
		this.description = "총알 세 발 + 지원사격스킬";
	}
}

class F35K extends Jet{
	public F35K() {
		this.name = "F35K";
		this.HP = 30;
		this.bulletPower = 2;
		this.price = 2000;
		this.description = "총알 세 발 + 지우기스킬";
	}
}
