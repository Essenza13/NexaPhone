/*    */ package me.dervinocap.originphone.commands.chatcommand;
/*    */ 
/*    */ import it.realityrp.realitygeneral.database.ImpattoSilenziosoDB;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import me.dervinocap.originphone.commands.SubCommand;
/*    */ import me.dervinocap.originphone.commands.chatcommand.subcommands.ChatAddMemberCommand;
/*    */ import me.dervinocap.originphone.commands.chatcommand.subcommands.ChatRemoveMemberCommand;
/*    */ import me.dervinocap.originphone.commands.chatcommand.subcommands.ChatSetNameCommand;
/*    */ import me.dervinocap.originphone.commands.chatcommand.subcommands.ChatSetSymCommand;
/*    */ import me.dervinocap.originphone.commands.chatcommand.subcommands.admincommands.ChatCreateCommand;
/*    */ import me.dervinocap.originphone.commands.chatcommand.subcommands.admincommands.ChatDeleteCommand;
/*    */ import me.dervinocap.originphone.commands.chatcommand.subcommands.admincommands.ChatSetOwnerCommand;
/*    */ import me.dervinocap.originphone.utils.BasicsFunction;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class ChatMainCommand
/*    */   implements CommandExecutor
/*    */ {
/*    */   public static Map<String, SubCommand> getSubCommands() {
/* 24 */     return subCommands;
/* 25 */   } public static final Map<String, SubCommand> subCommands = new HashMap<>();
/*    */   public void register(String subCommandName, SubCommand command) {
/* 27 */     subCommands.put(subCommandName, command);
/*    */   }
/*    */   
/*    */   public ChatMainCommand() {
/* 31 */     register("create", (SubCommand)new ChatCreateCommand());
/* 32 */     register("delete", (SubCommand)new ChatDeleteCommand());
/* 33 */     register("setowner", (SubCommand)new ChatSetOwnerCommand());
/* 34 */     register("add", (SubCommand)new ChatAddMemberCommand());
/* 35 */     register("remove", (SubCommand)new ChatRemoveMemberCommand());
/* 36 */     register("setsym", (SubCommand)new ChatSetSymCommand());
/* 37 */     register("setname", (SubCommand)new ChatSetNameCommand());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
/* 43 */     String unknown_command = BasicsFunction.hex("&f &#F59F0BQuesto comando richiede ulteriori argomenti!");
/*    */     
/* 45 */     Player player = (Player)sender;
/*    */     
/* 47 */     if (ImpattoSilenziosoDB.checkIfEnabled()) {
/* 48 */       player.sendMessage("&f &#F59F0BSegnale non trovato, riprova piu' tardi.");
/* 49 */       return false;
/*    */     } 
/*    */     
/* 52 */     if (args.length == 0) {
/*    */       
/* 54 */       sender.sendMessage(BasicsFunction.hex(""));
/* 55 */       sender.sendMessage(BasicsFunction.hex("&#6FF2FF&lLista comandi"));
/* 56 */       sender.sendMessage(BasicsFunction.hex(" &#797979• &7/ᴄʜᴀᴛ-ᴍᴀɴᴀɢᴇ ᴀᴅᴅ (ᴄʜᴀᴛ) (ᴄɪᴛᴛᴀᴅɪɴᴏ)"));
/* 57 */       sender.sendMessage(BasicsFunction.hex(" &#797979• &7/ᴄʜᴀᴛ-ᴍᴀɴᴀɢᴇ ʀᴇᴍᴏᴠᴇ (ᴄʜᴀᴛ) (ᴄɪᴛᴛᴀᴅɪɴᴏ)"));
/* 58 */       sender.sendMessage(BasicsFunction.hex(" &#797979• &7/ᴄʜᴀᴛ-ᴍᴀɴᴀɢᴇ ѕᴇᴛѕʏᴍ (ᴄʜᴀᴛ) (ѕɪᴍʙᴏʟᴏ)"));
/* 59 */       sender.sendMessage(BasicsFunction.hex(" &#797979• &7/ᴄʜᴀᴛ-ᴍᴀɴᴀɢᴇ ѕᴇᴛɴᴀᴍᴇ (ᴄʜᴀᴛ) (ᴄɪᴛᴛᴀᴅɪɴᴏ) (ɴᴏᴍᴇ)"));
/* 60 */       if (sender.hasPermission("realityphone.chatadmin")) {
/* 61 */         sender.sendMessage(BasicsFunction.hex(" &#797979• &7/ᴄʜᴀᴛ-ᴍᴀɴᴀɢᴇ ᴄʀᴇᴀᴛᴇ (ᴄʜᴀᴛ) (ꜰᴏʀᴍᴀᴛ)"));
/* 62 */         sender.sendMessage(BasicsFunction.hex(" &#797979• &7/ᴄʜᴀᴛ-ᴍᴀɴᴀɢᴇ ᴅᴇʟᴇᴛᴇ (ᴄʜᴀᴛ)"));
/* 63 */         sender.sendMessage(BasicsFunction.hex(" &#797979• &7/ᴄʜᴀᴛ-ᴍᴀɴᴀɢᴇ ꜱᴇᴛᴏᴡɴᴇʀ (ᴄʜᴀᴛ) (ᴄɪᴛᴛᴀᴅɪɴᴏ)"));
/*    */       } 
/* 65 */       sender.sendMessage(BasicsFunction.hex(""));
/*    */       
/* 67 */       return false;
/*    */     } 
/*    */     
/* 70 */     String argument = args[0].toLowerCase();
/* 71 */     SubCommand commandToExecute = subCommands.get(argument);
/*    */     
/* 73 */     if (subCommands.get(argument) == null) {
/* 74 */       sender.sendMessage(unknown_command);
/* 75 */       return false;
/*    */     } 
/*    */     
/* 78 */     commandToExecute.execute(sender, args);
/*    */ 
/*    */     
/* 81 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\commands\chatcommand\ChatMainCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */