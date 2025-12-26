package me.dervinocap.originphone.commands;

import org.bukkit.command.CommandSender;

public abstract class SubCommand {
  public abstract boolean isPlayerOnly();
  
  public abstract String getPermission();
  
  public abstract void execute(CommandSender paramCommandSender, String[] paramArrayOfString);
}


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\commands\SubCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */