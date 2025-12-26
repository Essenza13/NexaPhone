/*     */ package dev.triumphteam.gui.components.util;
/*     */ 
/*     */ import dev.triumphteam.gui.components.GuiType;
/*     */ import dev.triumphteam.gui.components.exception.GuiException;
/*     */ import dev.triumphteam.gui.guis.BaseGui;
/*     */ import dev.triumphteam.gui.guis.GuiItem;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class GuiFiller
/*     */ {
/*     */   private final BaseGui gui;
/*     */   
/*     */   public GuiFiller(BaseGui gui) {
/*  45 */     this.gui = gui;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fillTop(@NotNull GuiItem guiItem) {
/*  54 */     fillTop(Collections.singletonList(guiItem));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fillTop(@NotNull List<GuiItem> guiItems) {
/*  63 */     List<GuiItem> items = repeatList(guiItems);
/*  64 */     for (int i = 0; i < 9; i++) {
/*  65 */       if (!this.gui.getGuiItems().containsKey(Integer.valueOf(i))) this.gui.setItem(i, items.get(i));
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fillBottom(@NotNull GuiItem guiItem) {
/*  75 */     fillBottom(Collections.singletonList(guiItem));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fillBottom(@NotNull List<GuiItem> guiItems) {
/*  84 */     int rows = this.gui.getRows();
/*  85 */     List<GuiItem> items = repeatList(guiItems);
/*  86 */     for (int i = 9; i > 0; i--) {
/*  87 */       if (this.gui.getGuiItems().get(Integer.valueOf(rows * 9 - i)) == null) {
/*  88 */         this.gui.setItem(rows * 9 - i, items.get(i));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fillBorder(@NotNull GuiItem guiItem) {
/*  99 */     fillBorder(Collections.singletonList(guiItem));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fillBorder(@NotNull List<GuiItem> guiItems) {
/* 108 */     int rows = this.gui.getRows();
/* 109 */     if (rows <= 2)
/*     */       return; 
/* 111 */     List<GuiItem> items = repeatList(guiItems);
/*     */     
/* 113 */     for (int i = 0; i < rows * 9; i++) {
/* 114 */       if (i <= 8 || (i >= rows * 9 - 8 && i <= rows * 9 - 2) || i % 9 == 0 || i % 9 == 8)
/*     */       {
/*     */ 
/*     */         
/* 118 */         this.gui.setItem(i, items.get(i));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fillBetweenPoints(int rowFrom, int colFrom, int rowTo, int colTo, @NotNull GuiItem guiItem) {
/* 134 */     fillBetweenPoints(rowFrom, colFrom, rowTo, colTo, Collections.singletonList(guiItem));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fillBetweenPoints(int rowFrom, int colFrom, int rowTo, int colTo, @NotNull List<GuiItem> guiItems) {
/* 148 */     int minRow = Math.min(rowFrom, rowTo);
/* 149 */     int maxRow = Math.max(rowFrom, rowTo);
/* 150 */     int minCol = Math.min(colFrom, colTo);
/* 151 */     int maxCol = Math.max(colFrom, colTo);
/*     */     
/* 153 */     int rows = this.gui.getRows();
/* 154 */     List<GuiItem> items = repeatList(guiItems);
/*     */     
/* 156 */     for (int row = 1; row <= rows; row++) {
/* 157 */       for (int col = 1; col <= 9; col++) {
/* 158 */         int slot = getSlotFromRowCol(row, col);
/* 159 */         if (row >= minRow && row <= maxRow && col >= minCol && col <= maxCol)
/*     */         {
/*     */           
/* 162 */           this.gui.setItem(slot, items.get(slot));
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fill(@NotNull GuiItem guiItem) {
/* 173 */     fill(Collections.singletonList(guiItem));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fill(@NotNull List<GuiItem> guiItems) {
/*     */     int fill;
/* 182 */     if (this.gui instanceof dev.triumphteam.gui.guis.PaginatedGui) {
/* 183 */       throw new GuiException("Full filling a GUI is not supported in a Paginated GUI!");
/*     */     }
/*     */     
/* 186 */     GuiType type = this.gui.guiType();
/*     */ 
/*     */     
/* 189 */     if (type == GuiType.CHEST) {
/* 190 */       fill = this.gui.getRows() * type.getLimit();
/*     */     } else {
/* 192 */       fill = type.getLimit();
/*     */     } 
/*     */     
/* 195 */     List<GuiItem> items = repeatList(guiItems);
/* 196 */     for (int i = 0; i < fill; i++) {
/* 197 */       if (this.gui.getGuiItems().get(Integer.valueOf(i)) == null) this.gui.setItem(i, items.get(i));
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fillSide(@NotNull Side side, @NotNull List<GuiItem> guiItems) {
/* 207 */     switch (side) {
/*     */       case LEFT:
/* 209 */         fillBetweenPoints(1, 1, this.gui.getRows(), 1, guiItems);
/*     */       case RIGHT:
/* 211 */         fillBetweenPoints(1, 9, this.gui.getRows(), 9, guiItems);
/*     */       case BOTH:
/* 213 */         fillBetweenPoints(1, 1, this.gui.getRows(), 1, guiItems);
/* 214 */         fillBetweenPoints(1, 9, this.gui.getRows(), 9, guiItems);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List<GuiItem> repeatList(@NotNull List<GuiItem> guiItems) {
/* 226 */     List<GuiItem> repeated = new ArrayList<>();
/* 227 */     Objects.requireNonNull(repeated); Collections.<List<GuiItem>>nCopies(this.gui.getRows() * 9, guiItems).forEach(repeated::addAll);
/* 228 */     return repeated;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getSlotFromRowCol(int row, int col) {
/* 239 */     return col + (row - 1) * 9 - 1;
/*     */   }
/*     */   
/*     */   public enum Side {
/* 243 */     LEFT,
/* 244 */     RIGHT,
/* 245 */     BOTH;
/*     */   }
/*     */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\dev\triumphteam\gui\component\\util\GuiFiller.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */