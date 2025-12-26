/*    */ package me.dervinocap.originphone.database;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.UUID;
/*    */ import me.dervinocap.originphone.RealityPhone;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.configuration.file.YamlConfiguration;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ public class ContactDatabase
/*    */ {
/* 16 */   public static final RealityPhone plugin = RealityPhone.getInstance();
/*    */   
/*    */   public static File dataFile;
/*    */   
/*    */   public static YamlConfiguration contactData;
/*    */   
/*    */   public File getDataFile() {
/* 23 */     this; return dataFile;
/*    */   }
/*    */   
/*    */   public static YamlConfiguration getContactData() {
/* 27 */     return contactData;
/*    */   }
/*    */   
/*    */   public static void saveContactData() {
/*    */     try {
/* 32 */       contactData.save(dataFile);
/* 33 */     } catch (IOException e) {
/* 34 */       throw new RuntimeException(e);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void setupContactData() {
/* 39 */     dataFile = new File(plugin.getDataFolder(), "contacts.yml");
/* 40 */     if (!plugin.getDataFolder().exists())
/* 41 */       plugin.getDataFolder().mkdir(); 
/* 42 */     if (!dataFile.exists())
/*    */       try {
/* 44 */         dataFile.createNewFile();
/* 45 */       } catch (IOException e) {
/* 46 */         throw new RuntimeException(e);
/*    */       }  
/* 48 */     contactData = YamlConfiguration.loadConfiguration(dataFile);
/*    */   }
/*    */   
/*    */   public static void addContact(Player player, Player newContact) {
/* 52 */     List<String> playerContacts = getContactData().getStringList(".players." + player.getUniqueId() + "..contacts");
/* 53 */     playerContacts.add(newContact.getUniqueId().toString());
/* 54 */     getContactData().set(".players." + player.getUniqueId() + "..contacts", playerContacts);
/* 55 */     saveContactData();
/*    */   }
/*    */   
/*    */   public static void addOfflineContact(Player player, OfflinePlayer newContact) {
/* 59 */     List<String> playerContacts = getContactData().getStringList(".players." + player.getUniqueId() + "..contacts");
/* 60 */     playerContacts.add(newContact.getUniqueId().toString());
/* 61 */     getContactData().set(".players." + player.getUniqueId() + "..contacts", playerContacts);
/* 62 */     saveContactData();
/*    */   }
/*    */   
/*    */   public static void removeContact(Player player, Player contact) {
/* 66 */     List<String> playerContacts = getContactData().getStringList(".players." + player.getUniqueId() + "..contacts");
/* 67 */     playerContacts.remove(contact.getUniqueId().toString());
/* 68 */     getContactData().set(".players." + player.getUniqueId() + "..contacts", playerContacts);
/* 69 */     saveContactData();
/*    */   }
/*    */   
/*    */   public static void removeOfflineContact(Player player, OfflinePlayer contact) {
/* 73 */     List<String> playerContacts = getContactData().getStringList(".players." + player.getUniqueId() + "..contacts");
/* 74 */     playerContacts.remove(contact.getUniqueId().toString());
/* 75 */     getContactData().set(".players." + player.getUniqueId() + "..contacts", playerContacts);
/* 76 */     saveContactData();
/*    */   }
/*    */   
/*    */   public static List<UUID> getPlayerContactsUUID(Player player) {
/* 80 */     List<UUID> playerContactsUUID = new ArrayList<>();
/* 81 */     if (!getContactData().isSet(".players." + player.getUniqueId() + "..contacts")) {
/* 82 */       getContactData().createSection(".players." + player.getUniqueId() + "..contacts");
/* 83 */       saveContactData();
/*    */     } 
/* 85 */     for (String s : getContactData().getStringList(".players." + player.getUniqueId() + "..contacts")) {
/* 86 */       UUID contactUUID = UUID.fromString(s);
/* 87 */       playerContactsUUID.add(contactUUID);
/*    */     } 
/* 89 */     return playerContactsUUID;
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\database\ContactDatabase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */