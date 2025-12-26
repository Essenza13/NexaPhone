/*    */ package me.dervinocap.originphone.database;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import me.dervinocap.originphone.RealityPhone;
/*    */ import org.bukkit.configuration.file.YamlConfiguration;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChatSymDatabase
/*    */ {
/* 16 */   public static final RealityPhone plugin = RealityPhone.getInstance();
/*    */   
/*    */   public static File dataFile;
/*    */   
/*    */   public static YamlConfiguration chatsData;
/*    */   
/*    */   public File getDataFile() {
/* 23 */     this; return dataFile;
/*    */   }
/*    */   
/*    */   public static YamlConfiguration getChatsData() {
/* 27 */     return chatsData;
/*    */   }
/*    */   
/*    */   public static void saveChatsData() {
/*    */     try {
/* 32 */       chatsData.save(dataFile);
/* 33 */     } catch (IOException e) {
/* 34 */       throw new RuntimeException(e);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void setupChatsData() {
/* 39 */     dataFile = new File(plugin.getDataFolder(), "chatsym.yml");
/* 40 */     if (!plugin.getDataFolder().exists())
/* 41 */       plugin.getDataFolder().mkdir(); 
/* 42 */     if (!dataFile.exists())
/*    */       try {
/* 44 */         dataFile.createNewFile();
/* 45 */       } catch (IOException e) {
/* 46 */         throw new RuntimeException(e);
/*    */       }  
/* 48 */     chatsData = YamlConfiguration.loadConfiguration(dataFile);
/*    */   }
/*    */   
/*    */   public static void createPlayerChatSym(Player player, String chatName, String sym) {
/* 52 */     getChatsData().set(".player." + player.getName() + ".chat." + chatName + ".chatsym", sym);
/* 53 */     saveChatsData();
/*    */   }
/*    */   
/*    */   public static void setSym(Player player, String chatName, String chatSym) {
/* 57 */     getChatsData().set(".player." + player.getName() + ".chat." + chatName + ".chatsym", chatSym);
/* 58 */     saveChatsData();
/*    */   }
/*    */   
/*    */   public static String getSym(Player player, String chatName) {
/* 62 */     return getChatsData().getString(".player." + player.getName() + ".chat." + chatName + ".chatsym");
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\database\ChatSymDatabase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */