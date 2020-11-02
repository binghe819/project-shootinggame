package com.binghe;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// 플레이어의 키 입력을 받는 클래스.
public class KeyControl extends KeyAdapter implements Runnable{
	
	static long skillTime = 0;
	
	boolean KeyUp = false; // 위로 이동.
	boolean KeyDown = false; // 밑으로 이동.
	boolean KeyLeft = false; // 왼쪽 이동.
	boolean KeyRight = false; // 오른쪽 이동.
	boolean KeySpace = false; // 총알발사.
	boolean KeyCtrl = false; // 아이템.
	boolean KeyZ = false; // 스킬.
	Player player;
	int cnt; // 총알 발사의 주기를 낮추기 위한 카운트.
	GameObject GameObject;
	Music 발사음;
//	boolean checkSkillReturn = true;
	
	public KeyControl(Player player, GameObject GameObject) {
		this.player = player;
		this.cnt = 0;
		this.GameObject = GameObject;
		발사음 = new Music("src/main/SOURCE/missileSound.wav");
	}

	// 키가 눌렸을 때.
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_UP :
			KeyUp = true;
			break;
		case KeyEvent.VK_DOWN :
			KeyDown = true;
			break;
		case KeyEvent.VK_LEFT :
			KeyLeft = true;
			break;
		case KeyEvent.VK_RIGHT :
			KeyRight = true;
			break;
		case KeyEvent.VK_SPACE:
			KeySpace = true;
			break;
		case KeyEvent.VK_CONTROL:
			KeyCtrl = true;
			break;
		case KeyEvent.VK_Z:
			KeyZ = true;
			break;
		}
	}

	// 키가 눌렸다 때어졌을 때.
	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_UP :
			KeyUp = false;
			break;
		case KeyEvent.VK_DOWN :
			KeyDown = false;
			break;
		case KeyEvent.VK_LEFT :
			KeyLeft = false;
			break;
		case KeyEvent.VK_RIGHT :
			KeyRight = false;
			break;
		case KeyEvent.VK_SPACE:
			KeySpace = false;
			break;
		case KeyEvent.VK_CONTROL:
			KeyCtrl = false;
			break;
		case KeyEvent.VK_Z:
			KeyZ = false;
			break;
		}
		
	}
	
	// 키를 눌렀을 때의 처리.
	public void keyProcess() {
		if(KeyUp == true) {
			if(this.player.posY >= 26)
				this.player.posY -= 5;
		}
		if(KeyDown == true) {
			if(this.player.posY < 584)
				this.player.posY += 5;
		}
		if(KeyLeft == true) {
			if(this.player.posX > 0)
				this.player.posX -= 5;
		}
		if(KeyRight == true) {
			if(this.player.posX < 740)
				this.player.posX +=5;
		}
		if(KeySpace == true) { // 총알 발사.
			// 총알 발사시, 총알 객체 만들고 총알들을 모아두는 리스트에 추가.
			if(cnt % 8 == 0) {
				발사음.playOnetime(2);// 발사때마다 효과음 발생.
				if(this.player.user.장착중인비행기.name == "F4K") {
					Bullet bullet = new Bullet(this.player,0,2);
					this.GameObject.Bullets.add(bullet);
				}
				else {
					Bullet top = new Bullet(this.player,330,2);
					this.GameObject.Bullets.add(top);
					
					Bullet middle = new Bullet(this.player,0,2);
					this.GameObject.Bullets.add(middle);

					Bullet bottom = new Bullet(this.player,30,2);
					this.GameObject.Bullets.add(bottom);
				}
			}
		}
		
		if(KeyCtrl == true) { // 아이템.
			if(this.player.user.장착중인아이템 != null) {
				this.player.HP += this.player.user.장착중인아이템.HPPlus;
				this.player.user.장착중인아이템 = null;
				if(this.player.HP >= this.player.user.장착중인비행기.HP) {
					this.player.HP = this.player.user.장착중인비행기.HP;
				}
			}
		}
		
		if(KeyZ == true) { // 스킬.
			if(this.GameObject.checkSkill == true) {
				if(this.player.user.장착중인비행기.name == "F15K") {
					for(int i = 0; i < 600; i+=30) {
						metheo bullet = new metheo(this.player,0,2,i);
						this.GameObject.Bullets.add(bullet);
					}
				}
				else if(this.player.user.장착중인비행기.name == "F35K") {
					// 적 총알 다 없애기. ( 시간차로 인해서 3번 반복문으로 확실히 다 없애준다. )
					for(int i = 0; i < this.GameObject.Ebullets.size(); i++) {
						this.GameObject.Ebullets.remove(i);
					}
					for(int i = 0; i < this.GameObject.Ebullets.size(); i++) {
						this.GameObject.Ebullets.remove(i);
					}
					for(int i = 0; i < this.GameObject.Ebullets.size(); i++) {
						this.GameObject.Ebullets.remove(i);
					}
				}
				this.GameObject.checkSkill = false;
				KeyControl.skillTime = System.currentTimeMillis()/1000;
			}
			
		}
	}
	
	// 플레이어 이미지에 대한 처리.
	public void PlayerProcess() {
		// 플레이어와 적 비행기끼리의 충돌시 처리.
		for(int j = 0; j < this.GameObject.Enemys.size(); j++) {
			if(GameObject.Crash(this.player.posX,this.player.posY,this.GameObject.Enemys.get(j).posX, this.GameObject.Enemys.get(j).posY, this.player.width, this.player.height, this.GameObject.Enemys.get(j).width, this.GameObject.Enemys.get(j).height)) {
				this.player.HP -= 1;
				this.GameObject.Enemys.get(j).HP -= 1;
				if(this.GameObject.Enemys.get(j).HP <= 0)
					this.GameObject.Enemys.remove(j);
			}
		}
	}
	
	// 플레이어가 쏜 총알에 대한 처리.
	public void PlayerBullet() {
		// 플레이어가 쏜 총알에 적이 맞았을 때에 대한 처리.
		for(int i = 0 ; i < this.GameObject.Bullets.size(); i++) {
			Bullet bullet = this.GameObject.Bullets.get(i);
			
			//플레이어 총알이 일반 적 비행기에 맞을때 처리.
			for(int j = 0; j < this.GameObject.Enemys.size(); j++) {
				if(GameObject.Crash(bullet.posX, bullet.posY, this.GameObject.Enemys.get(j).posX, this.GameObject.Enemys.get(j).posY, bullet.width, bullet.height, this.GameObject.Enemys.get(j).width, this.GameObject.Enemys.get(j).height)) {
					this.GameObject.Bullets.remove(i);
					this.GameObject.Enemys.get(j).HP -= 1;
					if(this.GameObject.Enemys.get(j).HP == 0) {
						this.GameObject.KilledEnemy += 1;
						Effect effect = new Effect(this.GameObject.Enemys.get(j).posX,this.GameObject.Enemys.get(j).posY);
						this.GameObject.Effects.add(effect);
						this.GameObject.Enemys.remove(j);
					}
				}
			}
			
			// 플레이어 총알이 보스가 맞을때 처리.
			for(int k = 0; k < this.GameObject.Bosses.size(); k++) {
				if(GameObject.Crash(bullet.posX, bullet.posY, this.GameObject.Bosses.get(k).posX, this.GameObject.Bosses.get(k).posY, bullet.width, bullet.height, this.GameObject.Bosses.get(k).width, this.GameObject.Bosses.get(k).height)) {
					this.GameObject.Bullets.remove(i);
					this.GameObject.Bosses.get(k).HP -= 1;
					if(this.GameObject.Bosses.get(k).HP == 0) {
						this.GameObject.KilledBoss += 1;
						BEffect beffect = new BEffect(this.GameObject.Bosses.get(k).posX, this.GameObject.Bosses.get(k).posY);
						this.GameObject.BEffects.add(beffect);
						this.GameObject.Bosses.remove(k);
						
					}
				}
			}
		}
	}
	
	// 적이 쏜 총알에 대한 처리.
	public void Enemybullet() {
		// 적이 총 쏘는 속도 처리.
		if(this.cnt % 30 == 0) {
			for(int i = 0 ; i < this.GameObject.Enemys.size(); i++) {
				Ebullet ebullet = new Enemybullet(this.GameObject.Enemys.get(i),180);
				this.GameObject.Ebullets.add(ebullet);
			}
			
			for(int i = 0 ; i < this.GameObject.Bosses.size(); i++) {
				Ebullet boss1Ebullet = new Boss1Bullet(this.GameObject.Bosses.get(i),this.GameObject);
			}
		}
		
		// 적이 쏜 총알이 플레이어에 맞았을때 처리.
		for(int i = 0; i < this.GameObject.Ebullets.size(); i++) {
			if(GameObject.Crash(this.GameObject.Ebullets.get(i).posX, this.GameObject.Ebullets.get(i).posY, this.player.posX, this.player.posY,this.GameObject.Ebullets.get(i).width , this.GameObject.Ebullets.get(i).height, this.player.width, this.player.height)) {
				this.player.HP -= 1; // 총에 맞았을때 체력깎기.
				this.GameObject.Ebullets.remove(i);
			}
		}
		
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				keyProcess();
				this.cnt++;
				PlayerProcess();
				PlayerBullet();
				Enemybullet();
				Thread.sleep(20);
				if(this.player.HP <=0 || (this.GameObject.Enemys.size() <= 0 && this.GameObject.Bosses.size() <= 0)) {
//					this.checkSkillReturn = false;
					break;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
