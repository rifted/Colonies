package com.gravirift.riftes.colonies.Buildings;

import org.bukkit.Location;
import org.bukkit.Material;

public class Structure {
	public Material[][][] getMap(){
		return null;
	}
	public static void buildBlock(Location loca, int x, int y, int z, Material mat, byte data){
		Location loc = new Location(loca.getWorld(),loca.getBlockX()+x,loca.getBlockY()+(y*2)+0.5,z+loca.getBlockZ());
		loc.getWorld().spawnFallingBlock(loc, mat, data);
	}
	public void generate(Location loc){
		Material[][][] map = getMap();
		for(int y = 0; y < map.length; y++){
			for(int z = 0; z < map[y].length; z++){
				for(int x = 0; x < map[y][z].length; x++){
					buildBlock(loc,z,y,x,map[y][x][z],(byte) 0);
				}
			}
		}
	}
}
