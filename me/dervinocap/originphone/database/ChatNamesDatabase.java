/*    */ package me.dervinocap.originphone.database;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import me.dervinocap.originphone.RealityPhone;
/*    */ import org.bukkit.configuration.file.YamlConfiguration;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ public class ChatNamesDatabase
/*    */ {
/* 12 */   public static final RealityPhone plugin = RealityPhone.getInstance();
/*    */   
/*    */   public static File dataFile;
/*    */   
/*    */   public static YamlConfiguration chatsData;
/*    */   
/*    */   public File getDataFile() {
/* 19 */     this; return dataFile;
/*    */   }
/*    */   
/*    */   public static YamlConfiguration getChatsData() {
/* 23 */     return chatsData;
/*    */   }
/*    */   
/*    */   public static void saveChatsData() {
/*    */     try {
/* 28 */       chatsData.save(dataFile);
/* 29 */     } catch (IOException e) {
/* 30 */       throw new RuntimeException(e);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void setupChatsData() {
/* 35 */     dataFile = new File(plugin.getDataFolder(), "chatnames.yml");
/* 36 */     if (!plugin.getDataFolder().exists())
/* 37 */       plugin.getDataFolder().mkdir(); 
/* 38 */     if (!dataFile.exists())
/*    */       try {
/* 40 */         dataFile.createNewFile();
/* 41 */       } catch (IOException e) {
/* 42 */         throw new RuntimeException(e);
/*    */       }  
/* 44 */     chatsData = YamlConfiguration.loadConfiguration(dataFile);
/*    */   }
/*    */   
/*    */   public static void createPlayerChatNames(Player player, String chatName, String name) {
/* 48 */     getChatsData().set(".player." + player.getName() + ".chat." + chatName + ".name", name);
/* 49 */     saveChatsData();
/*    */   }
/*    */   
/*    */   public static void setName(Player player, String chatName, String name) {
/* 53 */     getChatsData().set(".player." + player.getName() + ".chat." + chatName + ".name", name);
/* 54 */     saveChatsData();
/*    */   }
/*    */   
/*    */   public static String getName(Player player, String chatName) {
/* 58 */     return getChatsData().getString(".player." + player.getName() + ".chat." + chatName + ".name");
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\database\ChatNamesDatabase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */