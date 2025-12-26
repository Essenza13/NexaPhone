package net.kyori.adventure.text;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface NBTComponentBuilder<C extends NBTComponent<C, B>, B extends NBTComponentBuilder<C, B>> extends ComponentBuilder<C, B> {
  @Contract("_ -> this")
  @NotNull
  B nbtPath(@NotNull String paramString);
  
  @Contract("_ -> this")
  @NotNull
  B interpret(boolean paramBoolean);
  
  @Contract("_ -> this")
  @NotNull
  B separator(@Nullable ComponentLike paramComponentLike);
}


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\net\kyori\adventure\text\NBTComponentBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */