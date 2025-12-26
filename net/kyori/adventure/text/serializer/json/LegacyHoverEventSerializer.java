package net.kyori.adventure.text.serializer.json;

import java.io.IOException;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.util.Codec;
import org.jetbrains.annotations.NotNull;

public interface LegacyHoverEventSerializer {
  HoverEvent.ShowItem deserializeShowItem(@NotNull Component paramComponent) throws IOException;
  
  @NotNull
  Component serializeShowItem(HoverEvent.ShowItem paramShowItem) throws IOException;
  
  HoverEvent.ShowEntity deserializeShowEntity(@NotNull Component paramComponent, Codec.Decoder<Component, String, ? extends RuntimeException> paramDecoder) throws IOException;
  
  @NotNull
  Component serializeShowEntity(HoverEvent.ShowEntity paramShowEntity, Codec.Encoder<Component, String, ? extends RuntimeException> paramEncoder) throws IOException;
}


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\net\kyori\adventure\text\serializer\json\LegacyHoverEventSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */