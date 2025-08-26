package fr.jinxss.e33.TeamSystem.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum TeamList {

	RED{
		@Override
		public ChatColor getChatColor() {
			return ChatColor.RED;
		}
		@Override
		public ItemStack getBannerColor() {
			return new ItemStack(Material.RED_BANNER);
		}
	},
	AQUA{
		@Override
		public ChatColor getChatColor() {
			return ChatColor.AQUA;
		}
		@Override
		public ItemStack getBannerColor() {
			return new ItemStack(Material.CYAN_BANNER);
		}
	},
	BLACK{
		@Override
		public ChatColor getChatColor() {
			return ChatColor.BLACK;
		}
		@Override
		public ItemStack getBannerColor() {
			return new ItemStack(Material.BLACK_BANNER);
		}
	},
	BLUE{
		@Override
		public ChatColor getChatColor() {
			return ChatColor.BLUE;
		}
		@Override
		public ItemStack getBannerColor() {
			return new ItemStack(Material.BLUE_BANNER);
		}
	},
	DARK_AQUA{
		@Override
		public ChatColor getChatColor() {
			return ChatColor.DARK_AQUA;
		}
		@Override
		public ItemStack getBannerColor() {
			return new ItemStack(Material.CYAN_BANNER);
		}
	},
	DARK_BLUE{
		@Override
		public ChatColor getChatColor() {
			return ChatColor.DARK_BLUE;
		}
		@Override
		public ItemStack getBannerColor() {
			return new ItemStack(Material.BLUE_BANNER);
		}
	},
	DARK_GRAY{
		@Override
		public ChatColor getChatColor() {
			return ChatColor.DARK_GRAY;
		}
		@Override
		public ItemStack getBannerColor() {
			return new ItemStack(Material.GRAY_BANNER);
		}
	},
	DARK_GREEN{
		@Override
		public ChatColor getChatColor() {
			return ChatColor.DARK_GREEN;
		}
		@Override
		public ItemStack getBannerColor() {
			return new ItemStack(Material.GREEN_BANNER);
		}
	},
	DARK_PURPLE{
		@Override
		public ChatColor getChatColor() {
			return ChatColor.DARK_PURPLE;
		}
		@Override
		public ItemStack getBannerColor() {
			return new ItemStack(Material.PURPLE_BANNER);
		}
	},
	DARK_RED{
		@Override
		public ChatColor getChatColor() {
			return ChatColor.DARK_RED;
		}
		@Override
		public ItemStack getBannerColor() {
			return new ItemStack(Material.RED_BANNER);
		}
	},
	GOLD{
		@Override
		public ChatColor getChatColor() {
			return ChatColor.GOLD;
		}
		@Override
		public ItemStack getBannerColor() {
			return new ItemStack(Material.BROWN_BANNER);
		}
	},
	GRAY{
		@Override
		public ChatColor getChatColor() {
			return ChatColor.GRAY;
		}
		@Override
		public ItemStack getBannerColor() {
			return new ItemStack(Material.LIGHT_GRAY_BANNER);
		}
	},
	GREEN{
		@Override
		public ChatColor getChatColor() {
			return ChatColor.GREEN;
		}
		@Override
		public ItemStack getBannerColor() {
			return new ItemStack(Material.LIME_BANNER);
		}
	},
	LIGHT_PURPLE{
		@Override
		public ChatColor getChatColor() {
			return ChatColor.LIGHT_PURPLE;
		}
		@Override
		public ItemStack getBannerColor() {
			return new ItemStack(Material.MAGENTA_BANNER);
		}
	},
	YELLOW{
		@Override
		public ChatColor getChatColor() {
			return ChatColor.YELLOW;
		}
		@Override
		public ItemStack getBannerColor() {
			return new ItemStack(Material.YELLOW_BANNER);
		}
	};
	
	
	public ChatColor getChatColor() {
		return ChatColor.RESET;
	}
	
	public ItemStack getBannerColor() {
		return new ItemStack(Material.WHITE_BANNER);
	}
	
}
