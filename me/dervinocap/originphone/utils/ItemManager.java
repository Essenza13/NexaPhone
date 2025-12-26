/*     */ package me.dervinocap.originphone.utils;
/*     */ 
/*     */ import de.tr7zw.nbtapi.NBTItem;
/*     */ import java.util.Arrays;
/*     */ import me.dervinocap.originphone.database.NumberDatabase;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.inventory.meta.SkullMeta;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemManager
/*     */ {
/*     */   public static ItemStack iphone14() {
/*  21 */     ItemStack item = new ItemStack(Material.STICK);
/*  22 */     NBTItem nbti = new NBTItem(item, true);
/*  23 */     ItemMeta meta = nbti.getItem().getItemMeta();
/*     */     
/*  25 */     meta.setCustomModelData(Integer.valueOf(10095));
/*  26 */     meta.setDisplayName("§fiPhone 14");
/*     */     
/*  28 */     nbti.getItem().setItemMeta(meta);
/*  29 */     nbti.setString("Phone", "iPhone14");
/*  30 */     nbti.setInteger("Carica", Integer.valueOf(100));
/*     */     
/*  32 */     return item;
/*     */   }
/*     */   
/*     */   public static ItemStack cabin() {
/*  36 */     ItemStack item = new ItemStack(Material.STICK);
/*  37 */     ItemMeta meta = item.getItemMeta();
/*  38 */     meta.setDisplayName("§fCabina");
/*  39 */     meta.setCustomModelData(Integer.valueOf(10334));
/*  40 */     meta.setUnbreakable(true);
/*  41 */     item.setItemMeta(meta);
/*  42 */     return item;
/*     */   }
/*     */   
/*     */   public static ItemStack ambulanza() {
/*  46 */     ItemStack item = new ItemStack(Material.STICK);
/*  47 */     ItemMeta meta = item.getItemMeta();
/*     */     
/*  49 */     meta.setDisplayName("");
/*     */     
/*  51 */     meta.setCustomModelData(Integer.valueOf(10133));
/*     */     
/*  53 */     item.setItemMeta(meta);
/*  54 */     return item;
/*     */   }
/*     */   
/*     */   public static ItemStack polizia() {
/*  58 */     ItemStack item = new ItemStack(Material.STICK);
/*  59 */     ItemMeta meta = item.getItemMeta();
/*     */     
/*  61 */     meta.setDisplayName("");
/*     */     
/*  63 */     meta.setCustomModelData(Integer.valueOf(10141));
/*     */     
/*  65 */     item.setItemMeta(meta);
/*  66 */     return item;
/*     */   }
/*     */   
/*     */   public static ItemStack vdf() {
/*  70 */     ItemStack item = new ItemStack(Material.STICK);
/*  71 */     ItemMeta meta = item.getItemMeta();
/*     */     
/*  73 */     meta.setDisplayName("");
/*     */     
/*  75 */     meta.setCustomModelData(Integer.valueOf(10148));
/*     */     
/*  77 */     item.setItemMeta(meta);
/*  78 */     return item;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ItemStack powerBank100() {
/*  83 */     ItemStack item = new ItemStack(Material.STICK);
/*  84 */     NBTItem nbti = new NBTItem(item, true);
/*  85 */     ItemMeta meta = nbti.getItem().getItemMeta();
/*     */     
/*  87 */     meta.setCustomModelData(Integer.valueOf(10380));
/*  88 */     meta.setDisplayName("§fPower Bank");
/*  89 */     meta.setLore(Arrays.asList(new String[] { BasicsFunction.hex("&8• &7Carica rimanente: &e⚡ 100%") }));
/*     */     
/*  91 */     nbti.getItem().setItemMeta(meta);
/*  92 */     nbti.setString("PowerBank", "100");
/*  93 */     nbti.setInteger("PowerCarica", Integer.valueOf(100));
/*     */     
/*  95 */     return item;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ItemStack powerBank200() {
/* 100 */     ItemStack item = new ItemStack(Material.STICK);
/* 101 */     NBTItem nbti = new NBTItem(item, true);
/* 102 */     ItemMeta meta = nbti.getItem().getItemMeta();
/*     */     
/* 104 */     meta.setCustomModelData(Integer.valueOf(10394));
/* 105 */     meta.setDisplayName("§fPower Bank");
/* 106 */     meta.setLore(Arrays.asList(new String[] { BasicsFunction.hex("&8• &7Carica rimanente: &e⚡ 200%") }));
/*     */     
/* 108 */     nbti.getItem().setItemMeta(meta);
/* 109 */     nbti.setString("PowerBank", "200");
/* 110 */     nbti.setInteger("PowerCarica", Integer.valueOf(200));
/*     */     
/* 112 */     return item;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ItemStack powerBank500() {
/* 117 */     ItemStack item = new ItemStack(Material.STICK);
/* 118 */     NBTItem nbti = new NBTItem(item, true);
/* 119 */     ItemMeta meta = nbti.getItem().getItemMeta();
/*     */     
/* 121 */     meta.setCustomModelData(Integer.valueOf(10393));
/* 122 */     meta.setDisplayName("§fPower Bank");
/* 123 */     meta.setLore(Arrays.asList(new String[] { BasicsFunction.hex("&8• &7Carica rimanente: &e⚡ 500%") }));
/*     */     
/* 125 */     nbti.getItem().setItemMeta(meta);
/* 126 */     nbti.setString("PowerBank", "500");
/* 127 */     nbti.setInteger("PowerCarica", Integer.valueOf(500));
/*     */     
/* 129 */     return item;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ItemStack nokia() {
/* 134 */     ItemStack item = new ItemStack(Material.STICK);
/* 135 */     NBTItem nbti = new NBTItem(item, true);
/* 136 */     ItemMeta meta = nbti.getItem().getItemMeta();
/*     */     
/* 138 */     meta.setCustomModelData(Integer.valueOf(10096));
/* 139 */     meta.setDisplayName("§fNokia 3310");
/*     */     
/* 141 */     nbti.getItem().setItemMeta(meta);
/* 142 */     nbti.setString("Phone", "Nokia");
/* 143 */     nbti.setInteger("Carica", Integer.valueOf(100));
/*     */     
/* 145 */     return item;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ItemStack ipadPro() {
/* 150 */     ItemStack item = new ItemStack(Material.STICK);
/* 151 */     NBTItem nbti = new NBTItem(item, true);
/* 152 */     ItemMeta meta = nbti.getItem().getItemMeta();
/*     */     
/* 154 */     meta.setCustomModelData(Integer.valueOf(10094));
/* 155 */     meta.setDisplayName("§fiPad Pro");
/*     */     
/* 157 */     nbti.getItem().setItemMeta(meta);
/* 158 */     nbti.setString("Phone", "iPadPro");
/* 159 */     nbti.setInteger("Carica", Integer.valueOf(100));
/*     */     
/* 161 */     return item;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ItemStack s22() {
/* 166 */     ItemStack item = new ItemStack(Material.STICK);
/* 167 */     NBTItem nbti = new NBTItem(item, true);
/* 168 */     ItemMeta meta = nbti.getItem().getItemMeta();
/*     */     
/* 170 */     meta.setCustomModelData(Integer.valueOf(10099));
/* 171 */     meta.setDisplayName("§fSamsung Galaxy S22");
/*     */     
/* 173 */     nbti.getItem().setItemMeta(meta);
/* 174 */     nbti.setString("Phone", "s22");
/* 175 */     nbti.setInteger("Carica", Integer.valueOf(100));
/*     */     
/* 177 */     return item;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ItemStack contactItem(Player player) {
/* 182 */     ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1, (short)3);
/* 183 */     SkullMeta itemMeta = (SkullMeta)item.getItemMeta();
/*     */     
/* 185 */     itemMeta.setOwner(player.getName());
/* 186 */     itemMeta.setDisplayName("§a" + player.getName());
/*     */     
/* 188 */     itemMeta.setLore(Arrays.asList(new String[] { "§8• §7Numero: §e" + NumberDatabase.getPhoneNumberFormatted(player), "§8» §7Tasto Destro per rimuovere il contatto" }));
/*     */     
/* 190 */     item.setItemMeta((ItemMeta)itemMeta);
/*     */     
/* 192 */     return item;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ItemStack contactItemOffline(OfflinePlayer player) {
/* 197 */     ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1, (short)3);
/* 198 */     SkullMeta itemMeta = (SkullMeta)item.getItemMeta();
/*     */     
/* 200 */     itemMeta.setOwner(player.getName());
/* 201 */     itemMeta.setDisplayName("§a" + player.getName());
/*     */     
/* 203 */     itemMeta.setLore(Arrays.asList(new String[] { "§8• §7Numero: §e" + NumberDatabase.getPhoneNumberOfflineFormatted(player), "§8» §7Tasto Destro per rimuovere il contatto" }));
/*     */     
/* 205 */     item.setItemMeta((ItemMeta)itemMeta);
/*     */     
/* 207 */     return item;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ItemStack guiCallContact() {
/* 212 */     ItemStack item = new ItemStack(Icon.home());
/* 213 */     ItemMeta meta = item.getItemMeta();
/*     */     
/* 215 */     meta.setDisplayName("§aChiama un contatto");
/* 216 */     meta.setLore(Arrays.asList(new String[] { "§8• §7Clicca per chiamare un contatto" }));
/*     */     
/* 218 */     item.setItemMeta(meta);
/*     */ 
/*     */     
/* 221 */     return item;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ItemStack guiMessageContact() {
/* 226 */     ItemStack item = new ItemStack(Icon.bulb());
/* 227 */     ItemMeta meta = item.getItemMeta();
/*     */     
/* 229 */     meta.setDisplayName("§eScrivi ad un contatto");
/* 230 */     meta.setLore(Arrays.asList(new String[] { "§8• §7Clicca per scrivere ad un contatto" }));
/*     */     
/* 232 */     item.setItemMeta(meta);
/*     */ 
/*     */     
/* 235 */     return item;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ItemStack playerSkull(String name) {
/* 240 */     ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1, (short)3);
/* 241 */     SkullMeta itemMeta = (SkullMeta)item.getItemMeta();
/*     */     
/* 243 */     itemMeta.setOwner(name);
/*     */     
/* 245 */     item.setItemMeta((ItemMeta)itemMeta);
/*     */     
/* 247 */     return item;
/*     */   }
/*     */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphon\\utils\ItemManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */