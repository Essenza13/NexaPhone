package dev.triumphteam.gui.components;

@FunctionalInterface
public interface GuiAction<T extends org.bukkit.event.Event> {
  void execute(T paramT);
}


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\dev\triumphteam\gui\components\GuiAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */