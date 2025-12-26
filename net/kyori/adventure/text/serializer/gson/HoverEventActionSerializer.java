/*    */ package net.kyori.adventure.text.serializer.gson;
/*    */ 
/*    */ import com.google.gson.TypeAdapter;
/*    */ import net.kyori.adventure.text.event.HoverEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class HoverEventActionSerializer
/*    */ {
/* 30 */   static final TypeAdapter<HoverEvent.Action<?>> INSTANCE = IndexedSerializer.lenient("hover action", HoverEvent.Action.NAMES);
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\net\kyori\adventure\text\serializer\gson\HoverEventActionSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */