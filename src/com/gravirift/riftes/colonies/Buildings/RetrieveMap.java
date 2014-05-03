package com.gravirift.riftes.colonies.Buildings;

import org.bukkit.Material;

public class RetrieveMap {
	public static Material[][][] Farm(){
		Material W = Material.LOG;
		Material A = Material.LAPIS_BLOCK;
		Material S = Material.WOOL;
		Material F = Material.FENCE;
		Material D = Material.SOIL;
		Material C = Material.COBBLESTONE;
		Material G = Material.GLOWSTONE;
		Material[][][] map = {
			{
				{W,W,G,W,W},
				{W,D,A,D,W},
				{W,D,A,D,W},
				{W,D,A,D,W},
				{W,D,A,D,W},
				{W,D,A,D,W},
				{W,D,A,D,W},
				{W,W,G,W,W}
			},
			{
				{F,S,S,S,F},
				{S,S,S,S,S},
				{S,S,S,S,S},
				{S,S,S,S,S},
				{S,S,S,S,S},
				{S,S,S,S,S},
				{S,S,S,S,S},
				{F,S,S,S,F}
			},
			{
				{F,S,S,S,F},
				{S,S,S,S,S},
				{S,S,S,S,S},
				{S,S,S,S,S},
				{S,S,S,S,S},
				{S,S,S,S,S},
				{S,S,S,S,S},
				{F,S,S,S,F}
			},
			{
				{C,C,C,C,C},
				{C,C,C,C,C},
				{C,C,C,C,C},
				{C,C,C,C,C},
				{C,C,C,C,C},
				{C,C,C,C,C},
				{C,C,C,C,C},
				{C,C,C,C,C}
			}
		};
		return map;
	}
}
