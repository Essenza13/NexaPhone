package net.kyori.adventure.text;

import net.kyori.adventure.util.Buildable;
import org.jetbrains.annotations.NotNull;

public interface BuildableComponent<C extends BuildableComponent<C, B>, B extends ComponentBuilder<C, B>> extends Buildable<C, B>, Component {
  @NotNull
  B toBuilder();
}


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\net\kyori\adventure\text\BuildableComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */