package net.kyori.adventure.text.flattener;

import net.kyori.adventure.text.format.Style;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface FlattenerListener {
  default void pushStyle(@NotNull Style style) {}
  
  void component(@NotNull String paramString);
  
  default void popStyle(@NotNull Style style) {}
}


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\net\kyori\adventure\text\flattener\FlattenerListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */