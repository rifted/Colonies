package com.gravirift.riftes.colonies;

import net.minecraft.server.v1_7_R1.EntityVillager;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.gravirift.riftes.colonies.Buildings.RetrieveMap;

public class ColoniesBuilder implements Listener{
	
	@EventHandler
	public void placeEvent(BlockPlaceEvent e){
		if(e.getPlayer() instanceof Player){
			if(e.getBlock().getType().equals(Material.LAPIS_ORE)){
				e.getBlock().setType(Material.AIR);
					if(generate(e.getBlock().getLocation())){
					LivingEntity villager = (LivingEntity) e.getBlock().getWorld().spawnEntity(e.getBlock().getLocation(), EntityType.VILLAGER);
					villager.setCustomName("Farmer");
					villager.setCustomNameVisible(true);
					villager.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, (int) Double.POSITIVE_INFINITY, 20));
				}else{
					e.getPlayer().sendMessage("Can't build here!");
				}
			}
		}
	}
	@SuppressWarnings("deprecation")
	@EventHandler
	public void blockFall(EntityChangeBlockEvent event){
		if(event.getEntity() instanceof FallingBlock){
			FallingBlock fb = (FallingBlock) event.getEntity();
			Location loc = fb.getLocation();
			loc.setY(loc.getY()-1);
			Location loc2 = fb.getLocation();
			loc2.setY(loc.getY()-1);
			if(loc.getWorld().getBlockAt(loc).getType().equals(Material.WOOL)){
				loc.getWorld().getBlockAt(loc).setType(Material.AIR);
			}
			if(loc.getWorld().getBlockAt(loc).getType().equals(Material.SOIL)){
				loc.getWorld().getBlockAt(loc).setType(Material.ACACIA_STAIRS);
				loc.getWorld().getBlockAt(loc).setData((byte) 11);
			}
			if(loc2.getWorld().getBlockAt(loc2).getType().equals(Material.ACACIA_STAIRS)){
				if(loc2.getWorld().getBlockAt(loc2).getData() == (byte) 11){
					loc.getWorld().getBlockAt(loc).setType(Material.AIR);
					loc2.getWorld().getBlockAt(loc2).setType(Material.SOIL);
				}
			}
			if(loc.getWorld().getBlockAt(loc).getType().equals(Material.LAPIS_BLOCK)){
				loc.getWorld().getBlockAt(loc).setType(Material.WATER);
			}
		}
	}
	public static void buildBlock(Location loca, int x, double y, int z, Material mat, byte data){
		Location loc = new Location(loca.getWorld(),loca.getBlockX()+x,loca.getBlockY()+(y*2)+2.5,z+loca.getBlockZ());
		FallingBlock block = loc.getWorld().spawnFallingBlock(loc, mat, data);
		block.setFireTicks(9999);
	}
	public boolean generate(Location loc){
		Material[][][] map = RetrieveMap.Farm();
		int offsetX = 0, offsetZ = 0;
		for(int y = 0; y < map.length; y++){
			if(Math.ceil(map[y].length/2) > offsetX){
				offsetX = (int) Math.ceil(map[y].length/2);
			}
			for(int x = 0; x < map[y].length; x++){
				if(Math.ceil(map[y][x].length/2) > offsetZ){
					offsetZ = (int) Math.ceil(map[y][x].length/2);
				}
			}
		}
		boolean canBuild = true;
		for(int y = 0; y < map.length; y++){
			for(int x = 0; x < map[y].length; x++){
				for(int z = 0; z < map[y][x].length; z++){
					Location loca = new Location(loc.getWorld(),loc.getBlockX()+x-offsetX,loc.getBlockY()+y,z+loc.getBlockZ()-offsetZ);
					loc.getWorld().getBlockAt(loca).setType(Material.GLASS);
					if(!loc.getWorld().getBlockAt(loca).getType().equals(Material.AIR)){
						canBuild = false;
						break;
					}
				}
			}
		}
		double visInc = 0;
		if(canBuild){
			for(int y = 0; y < map.length; y++){
				for(int x = 0; x < map[y].length; x++){
					for(int z = 0; z < map[y][x].length; z++){
						buildBlock(loc,z-offsetZ,y+visInc,x-offsetX,map[y][x][z],(byte) 0);
						visInc += 0.2;
					}
				}
			}
		}else{
			return false;
		}
		return true;
	}
}
