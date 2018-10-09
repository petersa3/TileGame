package dev.alpete.tilegame.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import dev.alpete.tilegame.Handler;
import dev.alpete.tilegame.gfx.Assets;
import dev.alpete.tilegame.gfx.Text;
import dev.alpete.tilegame.items.Item;

public class Inventory {
	
	private Handler handler;
	private boolean active = false;
	private ArrayList<Item> inventoryItems;
	
	//Inventory Screen Size (Centered).
	private int invX = 64, invY = 48, invWidth = 512, invHeight = 384,
			invListCenterX = invX +  171,
			invListCenterY = invY + invHeight / 2 + 5;
	
	public Inventory(Handler handler) {
		this.handler = handler;
		inventoryItems = new ArrayList<Item>();
		
		//misc items here, start with 5 apples for testing purposes.
		addItem(Item.appleItem.createNew(5));
	}
	
	public void tick() {
		//Activate inventory
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E))
			active = !active;
		
		if(!active)
			return;
		
		//Inventory Test No Apple?
		System.out.println("Inventory: ");
		for(Item i : inventoryItems) {
			System.out.println(i.getName() + "  " + i.getCount());
		}
		
	}
	
	public void render(Graphics g) {
		if(!active)
			return;
		
		//Draw screen
		g.drawImage(Assets.inventoryScreen, invX , invY, invWidth, invHeight, null);
		
		//Draw items in screen
		Text.drawString(g, "You get <item>", invListCenterX, invListCenterY, true, Color.WHITE, Assets.font28);
	}
	//Inventory Methods
	public void addItem(Item item) {
		for(Item i : inventoryItems) {
			if(i.getId() == item.getId()) {
				i.setCount(i.getCount() + item.getCount());
				return;
			}
		}
		inventoryItems.add(item);
	}
	
	//GETTERS AND SETTERS
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}	
}