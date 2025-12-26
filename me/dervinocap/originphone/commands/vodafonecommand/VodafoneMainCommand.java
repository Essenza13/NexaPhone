/*    */ package me.dervinocap.originphone.commands.vodafonecommand;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import me.dervinocap.originphone.commands.CMDExecutionValidator;
/*    */ import me.dervinocap.originphone.commands.SubCommand;
/*    */ import me.dervinocap.originphone.commands.vodafonecommand.subcommands.AddAbbonamentoCommand;
/*    */ import me.dervinocap.originphone.commands.vodafonecommand.subcommands.AssignPhoneCommand;
/*    */ import me.dervinocap.originphone.commands.vodafonecommand.subcommands.CreaNumeroCommand;
/*    */ import me.dervinocap.originphone.commands.vodafonecommand.subcommands.DelNumeroCommand;
/*    */ import me.dervinocap.originphone.utils.BasicsFunction;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ public class VodafoneMainCommand
/*    */   implements CommandExecutor
/*    */ {
/*    */   public static Map<String, SubCommand> getSubCommands() {
/* 20 */     return subCommands;
/* 21 */   } public static final Map<String, SubCommand> subCommands = new HashMap<>();
/*    */   public void register(String subCommandName, SubCommand command) {
/* 23 */     subCommands.put(subCommandName, command);
/*    */   }
/*    */   
/*    */   public VodafoneMainCommand() {
/* 27 */     register("creanumero", (SubCommand)new CreaNumeroCommand());
/* 28 */     register("deletenumero", (SubCommand)new DelNumeroCommand());
/* 29 */     register("addabbonamento", (SubCommand)new AddAbbonamentoCommand());
/* 30 */     register("assegna", (SubCommand)new AssignPhoneCommand());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
/* 36 */     String unknown_command = BasicsFunction.hex("&f &#F59F0BQuesto comando richiede ulteriori argomenti!");
/*    */     
/* 38 */     if (!sender.hasPermission("realityphone.rephone")) {
/* 39 */       sender.sendMessage(BasicsFunction.hex("&f &#DE2727Non possiedi abbastanza permessi per eseguire questo comando!"));
/* 40 */       return false;
/*    */     } 
/*    */     
/* 43 */     if (args.length == 0) {
/* 44 */       sender.sendMessage("");
/* 45 */       sender.sendMessage(BasicsFunction.hex("&#6FF2FF&lLista comandi"));
/* 46 */       sender.sendMessage(BasicsFunction.hex(" &#797979• &7/ʀᴇᴘʜᴏɴᴇ ᴄʀᴇᴀɴᴜᴍᴇʀᴏ (ᴄɪᴛᴛᴀᴅɪɴᴏ)"));
/* 47 */       sender.sendMessage(BasicsFunction.hex(" &#797979• &7/ʀᴇᴘʜᴏɴᴇ ᴀᴅᴅᴀʙʙᴏɴᴀᴍᴇɴᴛᴏ (ᴄɪᴛᴛᴀᴅɪɴᴏ) (ᴀʙʙᴏɴᴀᴍᴇɴᴛᴏ)"));
/* 48 */       sender.sendMessage(BasicsFunction.hex(" &#797979• &7/ʀᴇᴘʜᴏɴᴇ ᴀꜱꜱᴇɢɴᴀ (ᴄɪᴛᴛᴀᴅɪɴᴏ)"));
/* 49 */       if (sender.hasPermission("realityphone.rephone.admin")) {
/* 50 */         sender.sendMessage(BasicsFunction.hex(" &#797979• &7/ʀᴇᴘʜᴏɴᴇ ᴅᴇʟᴇᴛᴇɴᴜᴍᴇʀᴏ (ᴄɪᴛᴛᴀᴅɪɴᴏ)"));
/*    */       }
/* 52 */       sender.sendMessage(BasicsFunction.hex(""));
/* 53 */       return false;
/*    */     } 
/*    */     
/* 56 */     String argument = args[0].toLowerCase();
/* 57 */     SubCommand commandToExecute = subCommands.get(argument);
/*    */     
/* 59 */     if (subCommands.get(argument) == null) {
/* 60 */       sender.sendMessage(unknown_command);
/* 61 */       return false;
/*    */     } 
/*    */     
/* 64 */     if (!CMDExecutionValidator.validate(commandToExecute, sender)) return false;
/*    */     
/* 66 */     commandToExecute.execute(sender, args);
/*    */     
/* 68 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\commands\vodafonecommand\VodafoneMainCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */