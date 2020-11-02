package com.binghe;

import java.awt.*;

// 게임 출현하는 모든 유닛 클래스.
class Unit {
	int HP;
	int posX;
	int posY;
	int width;
	int height;
	Image img;
	// 이미지를 불러오는 역할.
	Toolkit tk = Toolkit.getDefaultToolkit();
}

class Effect extends Unit{
	int cnt = 0;
	public Effect(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	public void show() {
		this.cnt++;
	}
}

class BEffect extends Unit{
	int cnt = 0;
	public BEffect(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	public void show() {
		this.cnt++;
	}
}

// 플레이어.
class Player extends Unit{
	User user;
	public Player(User user) {
		this.user = user;
		int playerHP = this.user.장착중인비행기.HP;
		this.HP = playerHP;
		this.posX = 100;
		this.posY = 100;
		this.width = 60;
		this.height = 13;
		if(this.user.장착중인비행기.name == "F4K")
			this.img = tk.getImage("src/main/SOURCE/F4K.png");
		else if(this.user.장착중인비행기.name == "F15K")
			this.img = tk.getImage("src/main/SOURCE/F15K.png");
		else if(this.user.장착중인비행기.name == "F35K")
			this.img = tk.getImage("src/main/SOURCE/F35K.png");
	}
}

// 플레이어의 총알.
class Bullet extends Unit{
	Player player;
	int angle;
	int speed;
	public Bullet(Player player, int angle, int speed) {
		this.player = player;
		this.angle = angle;
		this.posX = this.player.posX + 10;
		this.posY = this.player.posY;
		this.width = 30;
		this.height = 13;
		this.speed = speed;
		this.img = tk.getImage("src/main/SOURCE/Playerbullet.png");
	}
	
	public void move() {
		this.posX += Math.cos(Math.toRadians(this.angle))*this.speed;
		this.posY += Math.sin(Math.toRadians(this.angle))*this.speed;
	}
}

class metheo extends Bullet{

	public metheo(Player player, int angle, int speed, int Y) {
		super(player, angle, speed);
		this.posY = Y;
	}
}

class Enemy extends Unit{
	int moveSpeed;
	
	public void move() {
	}
}

// 적비행기 (제일 기본적인 앞으로 전진하는 비행기)
class MovingEnemy extends Enemy{
	public MovingEnemy(int posX, int posY) {
		this.HP = 5;
		this.posX = posX;
		this.posY = posY;
		this.width = 60;
		this.height = 13;
		this.img = tk.getImage("src/main/SOURCE/enemyFlight.png");
	}
	
	@Override
	public void move() {
		this.posX -= 0.2;
		if(this.posX == 1) {
			this.posX = 740;
		}
	}
}

class Boss extends Enemy{
	
}

class Boss1 extends Boss{
	public Boss1(int posX, int posY) {
		this.HP = 20;
		this.posX = posX;
		this.posY = posY;
		this.width = 120;
		this.height = 40;
		this.img = tk.getImage("src/main/SOURCE/Boss1.png");
	}
	
	@Override
	public void move() {
		
	}
}

class Boss2 extends Boss{
	boolean check = false;
	int cnt = 0;
	int firstPosY;
	
	public Boss2(int posX, int posY) {
		this.HP = 30;
		firstPosY = posY;
		this.posX = posX;
		this.posY = posY;
		this.width = 120;
		this.height = 40;
		this.img = tk.getImage("src/main/SOURCE/Boss2.png");
	}
	
	@Override
	public void move() {
		if(cnt %3 == 0) {
			if(check == false) {
				this.posY -= 1;
			}
			else if( check == true) {
				this.posY += 1;
			}
			
			if(this.posY == this.firstPosY-50)
				check = true;
			else if(this.posY == this.firstPosY+50)
				check = false;
		}
		cnt++;
	}
}

class Ebullet extends Unit{
	
	public void move() {
		
	}
}

// 적비행기의 총알
class Enemybullet extends Ebullet{
	Enemy enemy;
	int angle;
	public Enemybullet(Enemy enemy,int angle) {
		this.enemy = enemy;
		this.posX = this.enemy.posX-25;
		this.posY = this.enemy.posY;
		this.width = 30;
		this.height = 13;
		this.angle = angle;
		this.img = tk.getImage("src/main/SOURCE/enemyBullet.png");
	}
	
	@Override
	public void move() {
		this.posX += Math.cos(Math.toRadians(this.angle))*2;
		this.posY += Math.sin(Math.toRadians(this.angle))*2;
	}
}

// 보스1의 총알.
class Boss1Bullet extends Ebullet {
	Enemy enemy;
	public Boss1Bullet(Enemy enemy,GameObject GameObject) {
		this.enemy = enemy;
		this.posX = this.enemy.posX;
		this.posY = this.enemy.posY;
		Ebullet top = new Enemybullet(this.enemy, 150);
		GameObject.Ebullets.add(top);
		
		Ebullet middle = new Enemybullet(this.enemy, 160);
		GameObject.Ebullets.add(middle);
				
		Ebullet bottom = new Enemybullet(this.enemy, 210);
		GameObject.Ebullets.add(bottom);
		
		Ebullet bomidle = new Enemybullet(this.enemy, 120);
		GameObject.Ebullets.add(bomidle);
		
		Ebullet topmidle = new Enemybullet(this.enemy, 240);
		GameObject.Ebullets.add(topmidle);
		
	}
}







