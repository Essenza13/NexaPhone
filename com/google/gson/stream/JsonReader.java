/*      */ package com.google.gson.stream;
/*      */ 
/*      */ import com.google.gson.internal.JsonReaderInternalAccess;
/*      */ import com.google.gson.internal.bind.JsonTreeReader;
/*      */ import java.io.Closeable;
/*      */ import java.io.EOFException;
/*      */ import java.io.IOException;
/*      */ import java.io.Reader;
/*      */ import java.util.Arrays;
/*      */ import java.util.Objects;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class JsonReader
/*      */   implements Closeable
/*      */ {
/*      */   private static final long MIN_INCOMPLETE_INTEGER = -922337203685477580L;
/*      */   private static final int PEEKED_NONE = 0;
/*      */   private static final int PEEKED_BEGIN_OBJECT = 1;
/*      */   private static final int PEEKED_END_OBJECT = 2;
/*      */   private static final int PEEKED_BEGIN_ARRAY = 3;
/*      */   private static final int PEEKED_END_ARRAY = 4;
/*      */   private static final int PEEKED_TRUE = 5;
/*      */   private static final int PEEKED_FALSE = 6;
/*      */   private static final int PEEKED_NULL = 7;
/*      */   private static final int PEEKED_SINGLE_QUOTED = 8;
/*      */   private static final int PEEKED_DOUBLE_QUOTED = 9;
/*      */   private static final int PEEKED_UNQUOTED = 10;
/*      */   private static final int PEEKED_BUFFERED = 11;
/*      */   private static final int PEEKED_SINGLE_QUOTED_NAME = 12;
/*      */   private static final int PEEKED_DOUBLE_QUOTED_NAME = 13;
/*      */   private static final int PEEKED_UNQUOTED_NAME = 14;
/*      */   private static final int PEEKED_LONG = 15;
/*      */   private static final int PEEKED_NUMBER = 16;
/*      */   private static final int PEEKED_EOF = 17;
/*      */   private static final int NUMBER_CHAR_NONE = 0;
/*      */   private static final int NUMBER_CHAR_SIGN = 1;
/*      */   private static final int NUMBER_CHAR_DIGIT = 2;
/*      */   private static final int NUMBER_CHAR_DECIMAL = 3;
/*      */   private static final int NUMBER_CHAR_FRACTION_DIGIT = 4;
/*      */   private static final int NUMBER_CHAR_EXP_E = 5;
/*      */   private static final int NUMBER_CHAR_EXP_SIGN = 6;
/*      */   private static final int NUMBER_CHAR_EXP_DIGIT = 7;
/*      */   private final Reader in;
/*      */   private boolean lenient = false;
/*      */   static final int BUFFER_SIZE = 1024;
/*  239 */   private final char[] buffer = new char[1024];
/*  240 */   private int pos = 0;
/*  241 */   private int limit = 0;
/*      */   
/*  243 */   private int lineNumber = 0;
/*  244 */   private int lineStart = 0;
/*      */   
/*  246 */   int peeked = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private long peekedLong;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int peekedNumberLength;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String peekedString;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  270 */   private int[] stack = new int[32];
/*  271 */   private int stackSize = 0; private String[] pathNames; private int[] pathIndices;
/*      */   public JsonReader(Reader in) {
/*  273 */     this.stack[this.stackSize++] = 6;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  284 */     this.pathNames = new String[32];
/*  285 */     this.pathIndices = new int[32];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  291 */     this.in = Objects.<Reader>requireNonNull(in, "in == null");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setLenient(boolean lenient) {
/*  334 */     this.lenient = lenient;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean isLenient() {
/*  341 */     return this.lenient;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void beginArray() throws IOException {
/*  349 */     int p = this.peeked;
/*  350 */     if (p == 0) {
/*  351 */       p = doPeek();
/*      */     }
/*  353 */     if (p == 3) {
/*  354 */       push(1);
/*  355 */       this.pathIndices[this.stackSize - 1] = 0;
/*  356 */       this.peeked = 0;
/*      */     } else {
/*  358 */       throw new IllegalStateException("Expected BEGIN_ARRAY but was " + peek() + locationString());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endArray() throws IOException {
/*  367 */     int p = this.peeked;
/*  368 */     if (p == 0) {
/*  369 */       p = doPeek();
/*      */     }
/*  371 */     if (p == 4) {
/*  372 */       this.stackSize--;
/*  373 */       this.pathIndices[this.stackSize - 1] = this.pathIndices[this.stackSize - 1] + 1;
/*  374 */       this.peeked = 0;
/*      */     } else {
/*  376 */       throw new IllegalStateException("Expected END_ARRAY but was " + peek() + locationString());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void beginObject() throws IOException {
/*  385 */     int p = this.peeked;
/*  386 */     if (p == 0) {
/*  387 */       p = doPeek();
/*      */     }
/*  389 */     if (p == 1) {
/*  390 */       push(3);
/*  391 */       this.peeked = 0;
/*      */     } else {
/*  393 */       throw new IllegalStateException("Expected BEGIN_OBJECT but was " + peek() + locationString());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endObject() throws IOException {
/*  402 */     int p = this.peeked;
/*  403 */     if (p == 0) {
/*  404 */       p = doPeek();
/*      */     }
/*  406 */     if (p == 2) {
/*  407 */       this.stackSize--;
/*  408 */       this.pathNames[this.stackSize] = null;
/*  409 */       this.pathIndices[this.stackSize - 1] = this.pathIndices[this.stackSize - 1] + 1;
/*  410 */       this.peeked = 0;
/*      */     } else {
/*  412 */       throw new IllegalStateException("Expected END_OBJECT but was " + peek() + locationString());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasNext() throws IOException {
/*  420 */     int p = this.peeked;
/*  421 */     if (p == 0) {
/*  422 */       p = doPeek();
/*      */     }
/*  424 */     return (p != 2 && p != 4 && p != 17);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JsonToken peek() throws IOException {
/*  431 */     int p = this.peeked;
/*  432 */     if (p == 0) {
/*  433 */       p = doPeek();
/*      */     }
/*      */     
/*  436 */     switch (p) {
/*      */       case 1:
/*  438 */         return JsonToken.BEGIN_OBJECT;
/*      */       case 2:
/*  440 */         return JsonToken.END_OBJECT;
/*      */       case 3:
/*  442 */         return JsonToken.BEGIN_ARRAY;
/*      */       case 4:
/*  444 */         return JsonToken.END_ARRAY;
/*      */       case 12:
/*      */       case 13:
/*      */       case 14:
/*  448 */         return JsonToken.NAME;
/*      */       case 5:
/*      */       case 6:
/*  451 */         return JsonToken.BOOLEAN;
/*      */       case 7:
/*  453 */         return JsonToken.NULL;
/*      */       case 8:
/*      */       case 9:
/*      */       case 10:
/*      */       case 11:
/*  458 */         return JsonToken.STRING;
/*      */       case 15:
/*      */       case 16:
/*  461 */         return JsonToken.NUMBER;
/*      */       case 17:
/*  463 */         return JsonToken.END_DOCUMENT;
/*      */     } 
/*  465 */     throw new AssertionError();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   int doPeek() throws IOException {
/*  471 */     int peekStack = this.stack[this.stackSize - 1];
/*  472 */     if (peekStack == 1)
/*  473 */     { this.stack[this.stackSize - 1] = 2; }
/*  474 */     else if (peekStack == 2)
/*      */     
/*  476 */     { int i = nextNonWhitespace(true);
/*  477 */       switch (i) {
/*      */         case 93:
/*  479 */           return this.peeked = 4;
/*      */         case 59:
/*  481 */           checkLenient(); break;
/*      */         case 44:
/*      */           break;
/*      */         default:
/*  485 */           throw syntaxError("Unterminated array");
/*      */       }  }
/*  487 */     else { if (peekStack == 3 || peekStack == 5) {
/*  488 */         this.stack[this.stackSize - 1] = 4;
/*      */         
/*  490 */         if (peekStack == 5) {
/*  491 */           int j = nextNonWhitespace(true);
/*  492 */           switch (j) {
/*      */             case 125:
/*  494 */               return this.peeked = 2;
/*      */             case 59:
/*  496 */               checkLenient(); break;
/*      */             case 44:
/*      */               break;
/*      */             default:
/*  500 */               throw syntaxError("Unterminated object");
/*      */           } 
/*      */         } 
/*  503 */         int i = nextNonWhitespace(true);
/*  504 */         switch (i) {
/*      */           case 34:
/*  506 */             return this.peeked = 13;
/*      */           case 39:
/*  508 */             checkLenient();
/*  509 */             return this.peeked = 12;
/*      */           case 125:
/*  511 */             if (peekStack != 5) {
/*  512 */               return this.peeked = 2;
/*      */             }
/*  514 */             throw syntaxError("Expected name");
/*      */         } 
/*      */         
/*  517 */         checkLenient();
/*  518 */         this.pos--;
/*  519 */         if (isLiteral((char)i)) {
/*  520 */           return this.peeked = 14;
/*      */         }
/*  522 */         throw syntaxError("Expected name");
/*      */       } 
/*      */       
/*  525 */       if (peekStack == 4) {
/*  526 */         this.stack[this.stackSize - 1] = 5;
/*      */         
/*  528 */         int i = nextNonWhitespace(true);
/*  529 */         switch (i) {
/*      */           case 58:
/*      */             break;
/*      */           case 61:
/*  533 */             checkLenient();
/*  534 */             if ((this.pos < this.limit || fillBuffer(1)) && this.buffer[this.pos] == '>') {
/*  535 */               this.pos++;
/*      */             }
/*      */             break;
/*      */           default:
/*  539 */             throw syntaxError("Expected ':'");
/*      */         } 
/*  541 */       } else if (peekStack == 6) {
/*  542 */         if (this.lenient) {
/*  543 */           consumeNonExecutePrefix();
/*      */         }
/*  545 */         this.stack[this.stackSize - 1] = 7;
/*  546 */       } else if (peekStack == 7) {
/*  547 */         int i = nextNonWhitespace(false);
/*  548 */         if (i == -1) {
/*  549 */           return this.peeked = 17;
/*      */         }
/*  551 */         checkLenient();
/*  552 */         this.pos--;
/*      */       }
/*  554 */       else if (peekStack == 8) {
/*  555 */         throw new IllegalStateException("JsonReader is closed");
/*      */       }  }
/*      */     
/*  558 */     int c = nextNonWhitespace(true);
/*  559 */     switch (c) {
/*      */       case 93:
/*  561 */         if (peekStack == 1) {
/*  562 */           return this.peeked = 4;
/*      */         }
/*      */ 
/*      */       
/*      */       case 44:
/*      */       case 59:
/*  568 */         if (peekStack == 1 || peekStack == 2) {
/*  569 */           checkLenient();
/*  570 */           this.pos--;
/*  571 */           return this.peeked = 7;
/*      */         } 
/*  573 */         throw syntaxError("Unexpected value");
/*      */       
/*      */       case 39:
/*  576 */         checkLenient();
/*  577 */         return this.peeked = 8;
/*      */       case 34:
/*  579 */         return this.peeked = 9;
/*      */       case 91:
/*  581 */         return this.peeked = 3;
/*      */       case 123:
/*  583 */         return this.peeked = 1;
/*      */     } 
/*  585 */     this.pos--;
/*      */ 
/*      */     
/*  588 */     int result = peekKeyword();
/*  589 */     if (result != 0) {
/*  590 */       return result;
/*      */     }
/*      */     
/*  593 */     result = peekNumber();
/*  594 */     if (result != 0) {
/*  595 */       return result;
/*      */     }
/*      */     
/*  598 */     if (!isLiteral(this.buffer[this.pos])) {
/*  599 */       throw syntaxError("Expected value");
/*      */     }
/*      */     
/*  602 */     checkLenient();
/*  603 */     return this.peeked = 10;
/*      */   }
/*      */   private int peekKeyword() throws IOException {
/*      */     String keyword, keywordUpper;
/*      */     int peeking;
/*  608 */     char c = this.buffer[this.pos];
/*      */ 
/*      */ 
/*      */     
/*  612 */     if (c == 't' || c == 'T') {
/*  613 */       keyword = "true";
/*  614 */       keywordUpper = "TRUE";
/*  615 */       peeking = 5;
/*  616 */     } else if (c == 'f' || c == 'F') {
/*  617 */       keyword = "false";
/*  618 */       keywordUpper = "FALSE";
/*  619 */       peeking = 6;
/*  620 */     } else if (c == 'n' || c == 'N') {
/*  621 */       keyword = "null";
/*  622 */       keywordUpper = "NULL";
/*  623 */       peeking = 7;
/*      */     } else {
/*  625 */       return 0;
/*      */     } 
/*      */ 
/*      */     
/*  629 */     int length = keyword.length();
/*  630 */     for (int i = 1; i < length; i++) {
/*  631 */       if (this.pos + i >= this.limit && !fillBuffer(i + 1)) {
/*  632 */         return 0;
/*      */       }
/*  634 */       c = this.buffer[this.pos + i];
/*  635 */       if (c != keyword.charAt(i) && c != keywordUpper.charAt(i)) {
/*  636 */         return 0;
/*      */       }
/*      */     } 
/*      */     
/*  640 */     if ((this.pos + length < this.limit || fillBuffer(length + 1)) && 
/*  641 */       isLiteral(this.buffer[this.pos + length])) {
/*  642 */       return 0;
/*      */     }
/*      */ 
/*      */     
/*  646 */     this.pos += length;
/*  647 */     return this.peeked = peeking;
/*      */   }
/*      */   
/*      */   private int peekNumber() throws IOException {
/*      */     int j;
/*  652 */     char[] buffer = this.buffer;
/*  653 */     int p = this.pos;
/*  654 */     int l = this.limit;
/*      */     
/*  656 */     long value = 0L;
/*  657 */     boolean negative = false;
/*  658 */     boolean fitsInLong = true;
/*  659 */     int last = 0;
/*      */     
/*  661 */     int i = 0;
/*      */ 
/*      */     
/*  664 */     for (;; i++) {
/*  665 */       if (p + i == l) {
/*  666 */         if (i == buffer.length)
/*      */         {
/*      */           
/*  669 */           return 0;
/*      */         }
/*  671 */         if (!fillBuffer(i + 1)) {
/*      */           break;
/*      */         }
/*  674 */         p = this.pos;
/*  675 */         l = this.limit;
/*      */       } 
/*      */       
/*  678 */       char c = buffer[p + i];
/*  679 */       switch (c) {
/*      */         case '-':
/*  681 */           if (last == 0) {
/*  682 */             negative = true;
/*  683 */             last = 1; break;
/*      */           } 
/*  685 */           if (last == 5) {
/*  686 */             last = 6;
/*      */             break;
/*      */           } 
/*  689 */           return 0;
/*      */         
/*      */         case '+':
/*  692 */           if (last == 5) {
/*  693 */             last = 6;
/*      */             break;
/*      */           } 
/*  696 */           return 0;
/*      */         
/*      */         case 'E':
/*      */         case 'e':
/*  700 */           if (last == 2 || last == 4) {
/*  701 */             last = 5;
/*      */             break;
/*      */           } 
/*  704 */           return 0;
/*      */         
/*      */         case '.':
/*  707 */           if (last == 2) {
/*  708 */             last = 3;
/*      */             break;
/*      */           } 
/*  711 */           return 0;
/*      */         
/*      */         default:
/*  714 */           if (c < '0' || c > '9') {
/*  715 */             if (!isLiteral(c)) {
/*      */               break;
/*      */             }
/*  718 */             return 0;
/*      */           } 
/*  720 */           if (last == 1 || last == 0) {
/*  721 */             value = -(c - 48);
/*  722 */             last = 2; break;
/*  723 */           }  if (last == 2) {
/*  724 */             if (value == 0L) {
/*  725 */               return 0;
/*      */             }
/*  727 */             long newValue = value * 10L - (c - 48);
/*  728 */             j = fitsInLong & ((value > -922337203685477580L || (value == -922337203685477580L && newValue < value)) ? 1 : 0);
/*      */             
/*  730 */             value = newValue; break;
/*  731 */           }  if (last == 3) {
/*  732 */             last = 4; break;
/*  733 */           }  if (last == 5 || last == 6) {
/*  734 */             last = 7;
/*      */           }
/*      */           break;
/*      */       } 
/*      */     
/*      */     } 
/*  740 */     if (last == 2 && j != 0 && (value != Long.MIN_VALUE || negative) && (value != 0L || false == negative)) {
/*  741 */       this.peekedLong = negative ? value : -value;
/*  742 */       this.pos += i;
/*  743 */       return this.peeked = 15;
/*  744 */     }  if (last == 2 || last == 4 || last == 7) {
/*      */       
/*  746 */       this.peekedNumberLength = i;
/*  747 */       return this.peeked = 16;
/*      */     } 
/*  749 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isLiteral(char c) throws IOException {
/*  755 */     switch (c) {
/*      */       case '#':
/*      */       case '/':
/*      */       case ';':
/*      */       case '=':
/*      */       case '\\':
/*  761 */         checkLenient();
/*      */       case '\t':
/*      */       case '\n':
/*      */       case '\f':
/*      */       case '\r':
/*      */       case ' ':
/*      */       case ',':
/*      */       case ':':
/*      */       case '[':
/*      */       case ']':
/*      */       case '{':
/*      */       case '}':
/*  773 */         return false;
/*      */     } 
/*  775 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String nextName() throws IOException {
/*      */     String result;
/*  786 */     int p = this.peeked;
/*  787 */     if (p == 0) {
/*  788 */       p = doPeek();
/*      */     }
/*      */     
/*  791 */     if (p == 14) {
/*  792 */       result = nextUnquotedValue();
/*  793 */     } else if (p == 12) {
/*  794 */       result = nextQuotedValue('\'');
/*  795 */     } else if (p == 13) {
/*  796 */       result = nextQuotedValue('"');
/*      */     } else {
/*  798 */       throw new IllegalStateException("Expected a name but was " + peek() + locationString());
/*      */     } 
/*  800 */     this.peeked = 0;
/*  801 */     this.pathNames[this.stackSize - 1] = result;
/*  802 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String nextString() throws IOException {
/*      */     String result;
/*  814 */     int p = this.peeked;
/*  815 */     if (p == 0) {
/*  816 */       p = doPeek();
/*      */     }
/*      */     
/*  819 */     if (p == 10) {
/*  820 */       result = nextUnquotedValue();
/*  821 */     } else if (p == 8) {
/*  822 */       result = nextQuotedValue('\'');
/*  823 */     } else if (p == 9) {
/*  824 */       result = nextQuotedValue('"');
/*  825 */     } else if (p == 11) {
/*  826 */       result = this.peekedString;
/*  827 */       this.peekedString = null;
/*  828 */     } else if (p == 15) {
/*  829 */       result = Long.toString(this.peekedLong);
/*  830 */     } else if (p == 16) {
/*  831 */       result = new String(this.buffer, this.pos, this.peekedNumberLength);
/*  832 */       this.pos += this.peekedNumberLength;
/*      */     } else {
/*  834 */       throw new IllegalStateException("Expected a string but was " + peek() + locationString());
/*      */     } 
/*  836 */     this.peeked = 0;
/*  837 */     this.pathIndices[this.stackSize - 1] = this.pathIndices[this.stackSize - 1] + 1;
/*  838 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean nextBoolean() throws IOException {
/*  849 */     int p = this.peeked;
/*  850 */     if (p == 0) {
/*  851 */       p = doPeek();
/*      */     }
/*  853 */     if (p == 5) {
/*  854 */       this.peeked = 0;
/*  855 */       this.pathIndices[this.stackSize - 1] = this.pathIndices[this.stackSize - 1] + 1;
/*  856 */       return true;
/*  857 */     }  if (p == 6) {
/*  858 */       this.peeked = 0;
/*  859 */       this.pathIndices[this.stackSize - 1] = this.pathIndices[this.stackSize - 1] + 1;
/*  860 */       return false;
/*      */     } 
/*  862 */     throw new IllegalStateException("Expected a boolean but was " + peek() + locationString());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void nextNull() throws IOException {
/*  873 */     int p = this.peeked;
/*  874 */     if (p == 0) {
/*  875 */       p = doPeek();
/*      */     }
/*  877 */     if (p == 7) {
/*  878 */       this.peeked = 0;
/*  879 */       this.pathIndices[this.stackSize - 1] = this.pathIndices[this.stackSize - 1] + 1;
/*      */     } else {
/*  881 */       throw new IllegalStateException("Expected null but was " + peek() + locationString());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double nextDouble() throws IOException {
/*  897 */     int p = this.peeked;
/*  898 */     if (p == 0) {
/*  899 */       p = doPeek();
/*      */     }
/*      */     
/*  902 */     if (p == 15) {
/*  903 */       this.peeked = 0;
/*  904 */       this.pathIndices[this.stackSize - 1] = this.pathIndices[this.stackSize - 1] + 1;
/*  905 */       return this.peekedLong;
/*      */     } 
/*      */     
/*  908 */     if (p == 16) {
/*  909 */       this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
/*  910 */       this.pos += this.peekedNumberLength;
/*  911 */     } else if (p == 8 || p == 9) {
/*  912 */       this.peekedString = nextQuotedValue((p == 8) ? 39 : 34);
/*  913 */     } else if (p == 10) {
/*  914 */       this.peekedString = nextUnquotedValue();
/*  915 */     } else if (p != 11) {
/*  916 */       throw new IllegalStateException("Expected a double but was " + peek() + locationString());
/*      */     } 
/*      */     
/*  919 */     this.peeked = 11;
/*  920 */     double result = Double.parseDouble(this.peekedString);
/*  921 */     if (!this.lenient && (Double.isNaN(result) || Double.isInfinite(result))) {
/*  922 */       throw new MalformedJsonException("JSON forbids NaN and infinities: " + result + 
/*  923 */           locationString());
/*      */     }
/*  925 */     this.peekedString = null;
/*  926 */     this.peeked = 0;
/*  927 */     this.pathIndices[this.stackSize - 1] = this.pathIndices[this.stackSize - 1] + 1;
/*  928 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long nextLong() throws IOException {
/*  942 */     int p = this.peeked;
/*  943 */     if (p == 0) {
/*  944 */       p = doPeek();
/*      */     }
/*      */     
/*  947 */     if (p == 15) {
/*  948 */       this.peeked = 0;
/*  949 */       this.pathIndices[this.stackSize - 1] = this.pathIndices[this.stackSize - 1] + 1;
/*  950 */       return this.peekedLong;
/*      */     } 
/*      */     
/*  953 */     if (p == 16) {
/*  954 */       this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
/*  955 */       this.pos += this.peekedNumberLength;
/*  956 */     } else if (p == 8 || p == 9 || p == 10) {
/*  957 */       if (p == 10) {
/*  958 */         this.peekedString = nextUnquotedValue();
/*      */       } else {
/*  960 */         this.peekedString = nextQuotedValue((p == 8) ? 39 : 34);
/*      */       } 
/*      */       try {
/*  963 */         long l = Long.parseLong(this.peekedString);
/*  964 */         this.peeked = 0;
/*  965 */         this.pathIndices[this.stackSize - 1] = this.pathIndices[this.stackSize - 1] + 1;
/*  966 */         return l;
/*  967 */       } catch (NumberFormatException numberFormatException) {}
/*      */     }
/*      */     else {
/*      */       
/*  971 */       throw new IllegalStateException("Expected a long but was " + peek() + locationString());
/*      */     } 
/*      */     
/*  974 */     this.peeked = 11;
/*  975 */     double asDouble = Double.parseDouble(this.peekedString);
/*  976 */     long result = (long)asDouble;
/*  977 */     if (result != asDouble) {
/*  978 */       throw new NumberFormatException("Expected a long but was " + this.peekedString + locationString());
/*      */     }
/*  980 */     this.peekedString = null;
/*  981 */     this.peeked = 0;
/*  982 */     this.pathIndices[this.stackSize - 1] = this.pathIndices[this.stackSize - 1] + 1;
/*  983 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String nextQuotedValue(char quote) throws IOException {
/*  998 */     char[] buffer = this.buffer;
/*  999 */     StringBuilder builder = null;
/*      */     while (true) {
/* 1001 */       int p = this.pos;
/* 1002 */       int l = this.limit;
/*      */       
/* 1004 */       int start = p;
/* 1005 */       while (p < l) {
/* 1006 */         int c = buffer[p++];
/*      */         
/* 1008 */         if (c == quote) {
/* 1009 */           this.pos = p;
/* 1010 */           int len = p - start - 1;
/* 1011 */           if (builder == null) {
/* 1012 */             return new String(buffer, start, len);
/*      */           }
/* 1014 */           builder.append(buffer, start, len);
/* 1015 */           return builder.toString();
/*      */         } 
/* 1017 */         if (c == 92) {
/* 1018 */           this.pos = p;
/* 1019 */           int len = p - start - 1;
/* 1020 */           if (builder == null) {
/* 1021 */             int estimatedLength = (len + 1) * 2;
/* 1022 */             builder = new StringBuilder(Math.max(estimatedLength, 16));
/*      */           } 
/* 1024 */           builder.append(buffer, start, len);
/* 1025 */           builder.append(readEscapeCharacter());
/* 1026 */           p = this.pos;
/* 1027 */           l = this.limit;
/* 1028 */           start = p; continue;
/* 1029 */         }  if (c == 10) {
/* 1030 */           this.lineNumber++;
/* 1031 */           this.lineStart = p;
/*      */         } 
/*      */       } 
/*      */       
/* 1035 */       if (builder == null) {
/* 1036 */         int estimatedLength = (p - start) * 2;
/* 1037 */         builder = new StringBuilder(Math.max(estimatedLength, 16));
/*      */       } 
/* 1039 */       builder.append(buffer, start, p - start);
/* 1040 */       this.pos = p;
/* 1041 */       if (!fillBuffer(1)) {
/* 1042 */         throw syntaxError("Unterminated string");
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String nextUnquotedValue() throws IOException {
/* 1052 */     StringBuilder builder = null;
/* 1053 */     int i = 0;
/*      */ 
/*      */     
/*      */     label34: while (true) {
/* 1057 */       for (; this.pos + i < this.limit; i++)
/* 1058 */       { switch (this.buffer[this.pos + i])
/*      */         { case '#':
/*      */           case '/':
/*      */           case ';':
/*      */           case '=':
/*      */           case '\\':
/* 1064 */             checkLenient(); break label34;
/*      */           case '\t': break label34;
/*      */           case '\n': break label34;
/*      */           case '\f': break label34;
/*      */           case '\r': break label34;
/*      */           case ' ': break label34;
/*      */           case ',':
/*      */             break label34;
/*      */           case ':':
/*      */             break label34;
/*      */           case '[':
/*      */             break label34;
/*      */           case ']':
/*      */             break label34;
/*      */           case '{':
/*      */             break label34;
/*      */           case '}':
/* 1081 */             break label34; }  }  if (i < this.buffer.length) {
/* 1082 */         if (fillBuffer(i + 1)) {
/*      */           continue;
/*      */         }
/*      */ 
/*      */         
/*      */         break;
/*      */       } 
/*      */       
/* 1090 */       if (builder == null) {
/* 1091 */         builder = new StringBuilder(Math.max(i, 16));
/*      */       }
/* 1093 */       builder.append(this.buffer, this.pos, i);
/* 1094 */       this.pos += i;
/* 1095 */       i = 0;
/* 1096 */       if (!fillBuffer(1)) {
/*      */         break;
/*      */       }
/*      */     } 
/*      */     
/* 1101 */     String result = (null == builder) ? new String(this.buffer, this.pos, i) : builder.append(this.buffer, this.pos, i).toString();
/* 1102 */     this.pos += i;
/* 1103 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   private void skipQuotedValue(char quote) throws IOException {
/* 1108 */     char[] buffer = this.buffer;
/*      */     while (true) {
/* 1110 */       int p = this.pos;
/* 1111 */       int l = this.limit;
/*      */       
/* 1113 */       while (p < l) {
/* 1114 */         int c = buffer[p++];
/* 1115 */         if (c == quote) {
/* 1116 */           this.pos = p; return;
/*      */         } 
/* 1118 */         if (c == 92) {
/* 1119 */           this.pos = p;
/* 1120 */           readEscapeCharacter();
/* 1121 */           p = this.pos;
/* 1122 */           l = this.limit; continue;
/* 1123 */         }  if (c == 10) {
/* 1124 */           this.lineNumber++;
/* 1125 */           this.lineStart = p;
/*      */         } 
/*      */       } 
/* 1128 */       this.pos = p;
/* 1129 */       if (!fillBuffer(1))
/* 1130 */         throw syntaxError("Unterminated string"); 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void skipUnquotedValue() throws IOException {
/*      */     do {
/* 1136 */       int i = 0;
/* 1137 */       for (; this.pos + i < this.limit; i++) {
/* 1138 */         switch (this.buffer[this.pos + i]) {
/*      */           case '#':
/*      */           case '/':
/*      */           case ';':
/*      */           case '=':
/*      */           case '\\':
/* 1144 */             checkLenient();
/*      */           case '\t':
/*      */           case '\n':
/*      */           case '\f':
/*      */           case '\r':
/*      */           case ' ':
/*      */           case ',':
/*      */           case ':':
/*      */           case '[':
/*      */           case ']':
/*      */           case '{':
/*      */           case '}':
/* 1156 */             this.pos += i;
/*      */             return;
/*      */         } 
/*      */       } 
/* 1160 */       this.pos += i;
/* 1161 */     } while (fillBuffer(1));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int nextInt() throws IOException {
/* 1175 */     int p = this.peeked;
/* 1176 */     if (p == 0) {
/* 1177 */       p = doPeek();
/*      */     }
/*      */ 
/*      */     
/* 1181 */     if (p == 15) {
/* 1182 */       int i = (int)this.peekedLong;
/* 1183 */       if (this.peekedLong != i) {
/* 1184 */         throw new NumberFormatException("Expected an int but was " + this.peekedLong + locationString());
/*      */       }
/* 1186 */       this.peeked = 0;
/* 1187 */       this.pathIndices[this.stackSize - 1] = this.pathIndices[this.stackSize - 1] + 1;
/* 1188 */       return i;
/*      */     } 
/*      */     
/* 1191 */     if (p == 16) {
/* 1192 */       this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
/* 1193 */       this.pos += this.peekedNumberLength;
/* 1194 */     } else if (p == 8 || p == 9 || p == 10) {
/* 1195 */       if (p == 10) {
/* 1196 */         this.peekedString = nextUnquotedValue();
/*      */       } else {
/* 1198 */         this.peekedString = nextQuotedValue((p == 8) ? 39 : 34);
/*      */       } 
/*      */       try {
/* 1201 */         int i = Integer.parseInt(this.peekedString);
/* 1202 */         this.peeked = 0;
/* 1203 */         this.pathIndices[this.stackSize - 1] = this.pathIndices[this.stackSize - 1] + 1;
/* 1204 */         return i;
/* 1205 */       } catch (NumberFormatException numberFormatException) {}
/*      */     }
/*      */     else {
/*      */       
/* 1209 */       throw new IllegalStateException("Expected an int but was " + peek() + locationString());
/*      */     } 
/*      */     
/* 1212 */     this.peeked = 11;
/* 1213 */     double asDouble = Double.parseDouble(this.peekedString);
/* 1214 */     int result = (int)asDouble;
/* 1215 */     if (result != asDouble) {
/* 1216 */       throw new NumberFormatException("Expected an int but was " + this.peekedString + locationString());
/*      */     }
/* 1218 */     this.peekedString = null;
/* 1219 */     this.peeked = 0;
/* 1220 */     this.pathIndices[this.stackSize - 1] = this.pathIndices[this.stackSize - 1] + 1;
/* 1221 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void close() throws IOException {
/* 1228 */     this.peeked = 0;
/* 1229 */     this.stack[0] = 8;
/* 1230 */     this.stackSize = 1;
/* 1231 */     this.in.close();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void skipValue() throws IOException {
/* 1250 */     int count = 0;
/*      */     do {
/* 1252 */       int p = this.peeked;
/* 1253 */       if (p == 0) {
/* 1254 */         p = doPeek();
/*      */       }
/*      */       
/* 1257 */       switch (p) {
/*      */         case 3:
/* 1259 */           push(1);
/* 1260 */           count++;
/*      */           break;
/*      */         case 1:
/* 1263 */           push(3);
/* 1264 */           count++;
/*      */           break;
/*      */         case 4:
/* 1267 */           this.stackSize--;
/* 1268 */           count--;
/*      */           break;
/*      */         
/*      */         case 2:
/* 1272 */           if (count == 0) {
/* 1273 */             this.pathNames[this.stackSize - 1] = null;
/*      */           }
/* 1275 */           this.stackSize--;
/* 1276 */           count--;
/*      */           break;
/*      */         case 10:
/* 1279 */           skipUnquotedValue();
/*      */           break;
/*      */         case 8:
/* 1282 */           skipQuotedValue('\'');
/*      */           break;
/*      */         case 9:
/* 1285 */           skipQuotedValue('"');
/*      */           break;
/*      */         case 14:
/* 1288 */           skipUnquotedValue();
/*      */           
/* 1290 */           if (count == 0) {
/* 1291 */             this.pathNames[this.stackSize - 1] = "<skipped>";
/*      */           }
/*      */           break;
/*      */         case 12:
/* 1295 */           skipQuotedValue('\'');
/*      */           
/* 1297 */           if (count == 0) {
/* 1298 */             this.pathNames[this.stackSize - 1] = "<skipped>";
/*      */           }
/*      */           break;
/*      */         case 13:
/* 1302 */           skipQuotedValue('"');
/*      */           
/* 1304 */           if (count == 0) {
/* 1305 */             this.pathNames[this.stackSize - 1] = "<skipped>";
/*      */           }
/*      */           break;
/*      */         case 16:
/* 1309 */           this.pos += this.peekedNumberLength;
/*      */           break;
/*      */         
/*      */         case 17:
/*      */           return;
/*      */       } 
/*      */       
/* 1316 */       this.peeked = 0;
/* 1317 */     } while (count > 0);
/*      */     
/* 1319 */     this.pathIndices[this.stackSize - 1] = this.pathIndices[this.stackSize - 1] + 1;
/*      */   }
/*      */   
/*      */   private void push(int newTop) {
/* 1323 */     if (this.stackSize == this.stack.length) {
/* 1324 */       int newLength = this.stackSize * 2;
/* 1325 */       this.stack = Arrays.copyOf(this.stack, newLength);
/* 1326 */       this.pathIndices = Arrays.copyOf(this.pathIndices, newLength);
/* 1327 */       this.pathNames = Arrays.<String>copyOf(this.pathNames, newLength);
/*      */     } 
/* 1329 */     this.stack[this.stackSize++] = newTop;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean fillBuffer(int minimum) throws IOException {
/* 1338 */     char[] buffer = this.buffer;
/* 1339 */     this.lineStart -= this.pos;
/* 1340 */     if (this.limit != this.pos) {
/* 1341 */       this.limit -= this.pos;
/* 1342 */       System.arraycopy(buffer, this.pos, buffer, 0, this.limit);
/*      */     } else {
/* 1344 */       this.limit = 0;
/*      */     } 
/*      */     
/* 1347 */     this.pos = 0;
/*      */     int total;
/* 1349 */     while ((total = this.in.read(buffer, this.limit, buffer.length - this.limit)) != -1) {
/* 1350 */       this.limit += total;
/*      */ 
/*      */       
/* 1353 */       if (this.lineNumber == 0 && this.lineStart == 0 && this.limit > 0 && buffer[0] == '﻿') {
/* 1354 */         this.pos++;
/* 1355 */         this.lineStart++;
/* 1356 */         minimum++;
/*      */       } 
/*      */       
/* 1359 */       if (this.limit >= minimum) {
/* 1360 */         return true;
/*      */       }
/*      */     } 
/* 1363 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int nextNonWhitespace(boolean throwOnEof) throws IOException {
/* 1381 */     char[] buffer = this.buffer;
/* 1382 */     int p = this.pos;
/* 1383 */     int l = this.limit;
/*      */     while (true) {
/* 1385 */       if (p == l) {
/* 1386 */         this.pos = p;
/* 1387 */         if (!fillBuffer(1)) {
/*      */           break;
/*      */         }
/* 1390 */         p = this.pos;
/* 1391 */         l = this.limit;
/*      */       } 
/*      */       
/* 1394 */       int c = buffer[p++];
/* 1395 */       if (c == 10) {
/* 1396 */         this.lineNumber++;
/* 1397 */         this.lineStart = p; continue;
/*      */       } 
/* 1399 */       if (c == 32 || c == 13 || c == 9) {
/*      */         continue;
/*      */       }
/*      */       
/* 1403 */       if (c == 47) {
/* 1404 */         this.pos = p;
/* 1405 */         if (p == l) {
/* 1406 */           this.pos--;
/* 1407 */           boolean charsLoaded = fillBuffer(2);
/* 1408 */           this.pos++;
/* 1409 */           if (!charsLoaded) {
/* 1410 */             return c;
/*      */           }
/*      */         } 
/*      */         
/* 1414 */         checkLenient();
/* 1415 */         char peek = buffer[this.pos];
/* 1416 */         switch (peek) {
/*      */           
/*      */           case '*':
/* 1419 */             this.pos++;
/* 1420 */             if (!skipTo("*/")) {
/* 1421 */               throw syntaxError("Unterminated comment");
/*      */             }
/* 1423 */             p = this.pos + 2;
/* 1424 */             l = this.limit;
/*      */             continue;
/*      */ 
/*      */           
/*      */           case '/':
/* 1429 */             this.pos++;
/* 1430 */             skipToEndOfLine();
/* 1431 */             p = this.pos;
/* 1432 */             l = this.limit;
/*      */             continue;
/*      */         } 
/*      */         
/* 1436 */         return c;
/*      */       } 
/* 1438 */       if (c == 35) {
/* 1439 */         this.pos = p;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1445 */         checkLenient();
/* 1446 */         skipToEndOfLine();
/* 1447 */         p = this.pos;
/* 1448 */         l = this.limit; continue;
/*      */       } 
/* 1450 */       this.pos = p;
/* 1451 */       return c;
/*      */     } 
/*      */     
/* 1454 */     if (throwOnEof) {
/* 1455 */       throw new EOFException("End of input" + locationString());
/*      */     }
/* 1457 */     return -1;
/*      */   }
/*      */ 
/*      */   
/*      */   private void checkLenient() throws IOException {
/* 1462 */     if (!this.lenient) {
/* 1463 */       throw syntaxError("Use JsonReader.setLenient(true) to accept malformed JSON");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void skipToEndOfLine() throws IOException {
/* 1473 */     while (this.pos < this.limit || fillBuffer(1)) {
/* 1474 */       char c = this.buffer[this.pos++];
/* 1475 */       if (c == '\n') {
/* 1476 */         this.lineNumber++;
/* 1477 */         this.lineStart = this.pos; break;
/*      */       } 
/* 1479 */       if (c == '\r') {
/*      */         break;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean skipTo(String toFind) throws IOException {
/* 1489 */     int length = toFind.length();
/*      */     
/* 1491 */     for (; this.pos + length <= this.limit || fillBuffer(length); this.pos++) {
/* 1492 */       if (this.buffer[this.pos] == '\n') {
/* 1493 */         this.lineNumber++;
/* 1494 */         this.lineStart = this.pos + 1;
/*      */       } else {
/*      */         
/* 1497 */         int c = 0; while (true) { if (c < length) {
/* 1498 */             if (this.buffer[this.pos + c] != toFind.charAt(c))
/*      */               break;  c++;
/*      */             continue;
/*      */           } 
/* 1502 */           return true; } 
/*      */       } 
/* 1504 */     }  return false;
/*      */   }
/*      */   
/*      */   public String toString() {
/* 1508 */     return getClass().getSimpleName() + locationString();
/*      */   }
/*      */   
/*      */   String locationString() {
/* 1512 */     int line = this.lineNumber + 1;
/* 1513 */     int column = this.pos - this.lineStart + 1;
/* 1514 */     return " at line " + line + " column " + column + " path " + getPath();
/*      */   }
/*      */   
/*      */   private String getPath(boolean usePreviousPath) {
/* 1518 */     StringBuilder result = (new StringBuilder()).append('$');
/* 1519 */     for (int i = 0; i < this.stackSize; i++) {
/* 1520 */       int pathIndex; switch (this.stack[i]) {
/*      */         case 1:
/*      */         case 2:
/* 1523 */           pathIndex = this.pathIndices[i];
/*      */           
/* 1525 */           if (usePreviousPath && pathIndex > 0 && i == this.stackSize - 1) {
/* 1526 */             pathIndex--;
/*      */           }
/* 1528 */           result.append('[').append(pathIndex).append(']');
/*      */           break;
/*      */         case 3:
/*      */         case 4:
/*      */         case 5:
/* 1533 */           result.append('.');
/* 1534 */           if (this.pathNames[i] != null) {
/* 1535 */             result.append(this.pathNames[i]);
/*      */           }
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     } 
/* 1544 */     return result.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getPreviousPath() {
/* 1561 */     return getPath(true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getPath() {
/* 1579 */     return getPath(false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private char readEscapeCharacter() throws IOException {
/*      */     char result;
/*      */     int i, end;
/* 1593 */     if (this.pos == this.limit && !fillBuffer(1)) {
/* 1594 */       throw syntaxError("Unterminated escape sequence");
/*      */     }
/*      */     
/* 1597 */     char escaped = this.buffer[this.pos++];
/* 1598 */     switch (escaped) {
/*      */       case 'u':
/* 1600 */         if (this.pos + 4 > this.limit && !fillBuffer(4)) {
/* 1601 */           throw syntaxError("Unterminated escape sequence");
/*      */         }
/*      */         
/* 1604 */         result = Character.MIN_VALUE;
/* 1605 */         for (i = this.pos, end = i + 4; i < end; i++) {
/* 1606 */           char c = this.buffer[i];
/* 1607 */           result = (char)(result << 4);
/* 1608 */           if (c >= '0' && c <= '9') {
/* 1609 */             result = (char)(result + c - 48);
/* 1610 */           } else if (c >= 'a' && c <= 'f') {
/* 1611 */             result = (char)(result + c - 97 + 10);
/* 1612 */           } else if (c >= 'A' && c <= 'F') {
/* 1613 */             result = (char)(result + c - 65 + 10);
/*      */           } else {
/* 1615 */             throw new NumberFormatException("\\u" + new String(this.buffer, this.pos, 4));
/*      */           } 
/*      */         } 
/* 1618 */         this.pos += 4;
/* 1619 */         return result;
/*      */       
/*      */       case 't':
/* 1622 */         return '\t';
/*      */       
/*      */       case 'b':
/* 1625 */         return '\b';
/*      */       
/*      */       case 'n':
/* 1628 */         return '\n';
/*      */       
/*      */       case 'r':
/* 1631 */         return '\r';
/*      */       
/*      */       case 'f':
/* 1634 */         return '\f';
/*      */       
/*      */       case '\n':
/* 1637 */         this.lineNumber++;
/* 1638 */         this.lineStart = this.pos;
/*      */ 
/*      */       
/*      */       case '"':
/*      */       case '\'':
/*      */       case '/':
/*      */       case '\\':
/* 1645 */         return escaped;
/*      */     } 
/*      */     
/* 1648 */     throw syntaxError("Invalid escape sequence");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private IOException syntaxError(String message) throws IOException {
/* 1657 */     throw new MalformedJsonException(message + locationString());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void consumeNonExecutePrefix() throws IOException {
/* 1665 */     nextNonWhitespace(true);
/* 1666 */     this.pos--;
/*      */     
/* 1668 */     if (this.pos + 5 > this.limit && !fillBuffer(5)) {
/*      */       return;
/*      */     }
/*      */     
/* 1672 */     int p = this.pos;
/* 1673 */     char[] buf = this.buffer;
/* 1674 */     if (buf[p] != ')' || buf[p + 1] != ']' || buf[p + 2] != '}' || buf[p + 3] != '\'' || buf[p + 4] != '\n') {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/* 1679 */     this.pos += 5;
/*      */   }
/*      */   
/*      */   static {
/* 1683 */     JsonReaderInternalAccess.INSTANCE = new JsonReaderInternalAccess() {
/*      */         public void promoteNameToValue(JsonReader reader) throws IOException {
/* 1685 */           if (reader instanceof JsonTreeReader) {
/* 1686 */             ((JsonTreeReader)reader).promoteNameToValue();
/*      */             return;
/*      */           } 
/* 1689 */           int p = reader.peeked;
/* 1690 */           if (p == 0) {
/* 1691 */             p = reader.doPeek();
/*      */           }
/* 1693 */           if (p == 13) {
/* 1694 */             reader.peeked = 9;
/* 1695 */           } else if (p == 12) {
/* 1696 */             reader.peeked = 8;
/* 1697 */           } else if (p == 14) {
/* 1698 */             reader.peeked = 10;
/*      */           } else {
/* 1700 */             throw new IllegalStateException("Expected a name but was " + reader
/* 1701 */                 .peek() + reader.locationString());
/*      */           } 
/*      */         }
/*      */       };
/*      */   }
/*      */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\com\google\gson\stream\JsonReader.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */