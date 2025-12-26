/*     */ package me.dervinocap.originphone.utils.customloader;
/*     */ import com.live.bemmamin.gps.api.GPSAPI;
/*     */ import java.util.concurrent.atomic.AtomicReference;
/*     */ import me.dervinocap.originphone.RealityPhone;
/*     */ import me.dervinocap.originphone.commands.chatcommand.ChatMainCommand;
/*     */ import me.dervinocap.originphone.commands.chatcommand.ChatTabComplete;
/*     */ import me.dervinocap.originphone.commands.chatcommand.subcommands.ChatCommand;
/*     */ import me.dervinocap.originphone.commands.sidecommands.CabinCreateCommand;
/*     */ import me.dervinocap.originphone.commands.sidecommands.ItemCommand;
/*     */ import me.dervinocap.originphone.commands.sidecommands.RispondiCommand;
/*     */ import me.dervinocap.originphone.commands.vodafonecommand.VodafoneMainCommand;
/*     */ import me.dervinocap.originphone.commands.vodafonecommand.VodafoneTabComplete;
/*     */ import me.dervinocap.originphone.database.ChatDatabase;
/*     */ import me.dervinocap.originphone.database.ChatNamesDatabase;
/*     */ import me.dervinocap.originphone.database.ContactDatabase;
/*     */ import me.dervinocap.originphone.database.NumberDatabase;
/*     */ import me.dervinocap.originphone.database.TwitterDatabase;
/*     */ import me.dervinocap.originphone.listeners.calls.CallKeyChat;
/*     */ import me.dervinocap.originphone.listeners.calls.CallValueChat;
/*     */ import me.dervinocap.originphone.listeners.emergency.EmergencyChatCall;
/*     */ import me.dervinocap.originphone.listeners.emergency.EmergencyResponseChat;
/*     */ import me.dervinocap.originphone.listeners.sms.SendMessageChat;
/*     */ import me.dervinocap.originphone.listeners.tweet.TitoloChat;
/*     */ import me.dervinocap.originphone.listeners.utilities.AbbonamentoJoin;
/*     */ import me.dervinocap.originphone.listeners.utilities.ChatSym;
/*     */ import me.dervinocap.originphone.listeners.utilities.DataJoin;
/*     */ import me.dervinocap.originphone.listeners.utilities.ItemToggle;
/*     */ import me.dervinocap.originphone.listeners.utilities.PayPalListener;
/*     */ import me.dervinocap.originphone.listeners.utilities.PhoneCabinUse;
/*     */ import me.dervinocap.originphone.listeners.utilities.PhoneOpenGUI;
/*     */ import me.dervinocap.originphone.listeners.utilities.WriteInChat;
/*     */ import me.dervinocap.originphone.tasks.BatteriaTask;
/*     */ import me.dervinocap.originphone.utils.PAPIExpansion;
/*     */ import net.milkbowl.vault.economy.Economy;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.command.CommandExecutor;
/*     */ import org.bukkit.command.TabCompleter;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.plugin.RegisteredServiceProvider;
/*     */ 
/*     */ public class PluginCustomLoader {
/*     */   public static PluginCustomLoader getInstance() {
/*  44 */     Object value = instance.get(); if (value == null) synchronized (instance) { value = instance.get(); if (value == null) { PluginCustomLoader actualValue = new PluginCustomLoader(); value = (actualValue == null) ? instance : actualValue; instance.set(value); }  }   return (value == instance) ? null : (PluginCustomLoader)value;
/*  45 */   } private static final AtomicReference<Object> instance = new AtomicReference();
/*     */   
/*  47 */   private final RealityPhone plugin = RealityPhone.getInstance();
/*     */   public static Economy getEcon() {
/*  49 */     return econ;
/*  50 */   } private static Economy econ = null;
/*     */   
/*  52 */   public String getPluginVersion() { return this.pluginVersion; } private final String pluginVersion = this.plugin
/*  53 */     .getDescription().getVersion();
/*     */   
/*  55 */   private GPSAPI gpsapi = null;
/*     */   
/*     */   private void loadConfig() {
/*  58 */     this.plugin.getLogger().info("Loading Config...");
/*  59 */     NumberDatabase.playerFolder();
/*  60 */     ChatDatabase.setupChatsData();
/*  61 */     ContactDatabase.setupContactData();
/*  62 */     TwitterDatabase.setupChatsData();
/*  63 */     ChatSymDatabase.setupChatsData();
/*  64 */     ChatNamesDatabase.setupChatsData();
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadMisc() {
/*  69 */     if (Bukkit.getPluginManager().getPlugin("GPS").isEnabled()) {
/*  70 */       this.gpsapi = new GPSAPI((Plugin)this.plugin);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void loadListeners() {
/*  76 */     this.plugin.getLogger().info("Loading Listeners...");
/*     */     
/*  78 */     Bukkit.getPluginManager().registerEvents((Listener)new DataJoin(), (Plugin)this.plugin);
/*  79 */     Bukkit.getPluginManager().registerEvents((Listener)new PhoneOpenGUI(), (Plugin)this.plugin);
/*  80 */     Bukkit.getPluginManager().registerEvents((Listener)new WriteInChat(), (Plugin)this.plugin);
/*  81 */     Bukkit.getPluginManager().registerEvents((Listener)new ItemToggle(), (Plugin)this.plugin);
/*  82 */     Bukkit.getPluginManager().registerEvents((Listener)new CallKeyChat(), (Plugin)this.plugin);
/*  83 */     Bukkit.getPluginManager().registerEvents((Listener)new CallValueChat(), (Plugin)this.plugin);
/*  84 */     Bukkit.getPluginManager().registerEvents((Listener)new SendMessageChat(), (Plugin)this.plugin);
/*  85 */     Bukkit.getPluginManager().registerEvents((Listener)new AbbonamentoJoin(), (Plugin)this.plugin);
/*  86 */     Bukkit.getPluginManager().registerEvents((Listener)new EmergencyChatCall(), (Plugin)this.plugin);
/*     */     
/*  88 */     Bukkit.getPluginManager().registerEvents((Listener)new ContenutoChat(), (Plugin)this.plugin);
/*  89 */     Bukkit.getPluginManager().registerEvents((Listener)new TitoloChat(), (Plugin)this.plugin);
/*  90 */     Bukkit.getPluginManager().registerEvents((Listener)new PayPalListener(), (Plugin)this.plugin);
/*  91 */     Bukkit.getPluginManager().registerEvents((Listener)new ChatSym(), (Plugin)this.plugin);
/*  92 */     Bukkit.getPluginManager().registerEvents((Listener)new EmergencyResponseChat(), (Plugin)this.plugin);
/*  93 */     Bukkit.getPluginManager().registerEvents((Listener)new PhoneCabinUse(), (Plugin)this.plugin);
/*     */     
/*  95 */     (new MinutesTask()).runTaskTimer((Plugin)this.plugin, 1L, 400L);
/*  96 */     (new CallingTask()).runTaskTimer((Plugin)this.plugin, 1L, 20L);
/*  97 */     (new BatteriaTask()).runTaskTimer((Plugin)this.plugin, 1L, 160L);
/*  98 */     (new PowerBankTask()).runTaskTimer((Plugin)this.plugin, 1L, 10L);
/*     */   }
/*     */ 
/*     */   
/*     */   private void loadCommands() {
/* 103 */     this.plugin.getLogger().info("Loading Commands...");
/* 104 */     Bukkit.getPluginCommand("rephone").setExecutor((CommandExecutor)new VodafoneMainCommand());
/* 105 */     Bukkit.getPluginCommand("chat-manage").setExecutor((CommandExecutor)new ChatMainCommand());
/* 106 */     Bukkit.getPluginCommand("chats").setExecutor((CommandExecutor)new ChatCommand());
/* 107 */     Bukkit.getPluginCommand("realityphone").setExecutor((CommandExecutor)new ItemCommand());
/* 108 */     Bukkit.getPluginCommand("emergency-respond").setExecutor((CommandExecutor)new RispondiCommand());
/* 109 */     Bukkit.getPluginCommand("cabina-create").setExecutor((CommandExecutor)new CabinCreateCommand());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void loadTabCompleters() {
/* 115 */     Bukkit.getPluginCommand("rephone").setTabCompleter((TabCompleter)new VodafoneTabComplete());
/* 116 */     Bukkit.getPluginCommand("chats").setTabCompleter((TabCompleter)new ChatTabComplete());
/* 117 */     Bukkit.getPluginCommand("chat-manage").setTabCompleter((TabCompleter)new ChatTabComplete());
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean setupEconomy() {
/* 122 */     if (this.plugin.getServer().getPluginManager().getPlugin("Vault") == null) {
/* 123 */       return false;
/*     */     }
/* 125 */     RegisteredServiceProvider<Economy> rsp = this.plugin.getServer().getServicesManager().getRegistration(Economy.class);
/* 126 */     if (rsp == null) {
/* 127 */       return false;
/*     */     }
/* 129 */     econ = (Economy)rsp.getProvider();
/* 130 */     return (econ != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void loadPlugin() {
/* 138 */     if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
/* 139 */       (new PAPIExpansion()).register();
/*     */     } else {
/* 141 */       this.plugin.getLogger().warning("Could not find PlaceholderAPI! All the placeholders wont work without.");
/*     */     } 
/*     */     
/* 144 */     if (!setupEconomy()) {
/* 145 */       this.plugin.getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", new Object[0]));
/* 146 */       this.plugin.getServer().getPluginManager().disablePlugin((Plugin)this.plugin);
/*     */       
/*     */       return;
/*     */     } 
/* 150 */     loadConfig();
/*     */     
/* 152 */     loadListeners();
/* 153 */     loadCommands();
/* 154 */     loadTabCompleters();
/* 155 */     this.plugin.saveDefaultConfig();
/* 156 */     loadMisc();
/*     */   }
/*     */   
/*     */   public void disablePlugin() {}
/*     */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphon\\utils\customloader\PluginCustomLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */