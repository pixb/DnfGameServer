package com.dnfm.game.bag.model;

import com.dnfm.mina.protobuf.PT_STACKABLE;
import java.util.HashMap;
import java.util.Map;

public class BookmarkBox {
   private int maxcount = 40;
   private Map<Integer, PT_STACKABLE> bookmarkMap = new HashMap();

   public void updateBookmarkSub(int index, int count) {
      PT_STACKABLE bookmark = (PT_STACKABLE)this.bookmarkMap.get(index);
      bookmark.count = bookmark.count - count;
      if (bookmark.count <= 0) {
         this.bookmarkMap.remove(index);
      }

   }

   public PT_STACKABLE getBookmark(int index) {
      return (PT_STACKABLE)this.bookmarkMap.get(index);
   }

   public void putBookmark(PT_STACKABLE bookmark) {
      this.bookmarkMap.put(bookmark.index, bookmark);
   }

   public void updateBookmarkAdd(int index, int count) {
      PT_STACKABLE bookmark = (PT_STACKABLE)this.bookmarkMap.get(index);
      if (bookmark == null) {
         bookmark = new PT_STACKABLE();
         bookmark.index = index;
         bookmark.count = count;
         this.bookmarkMap.put(index, bookmark);
      } else {
         bookmark.count = bookmark.count + count;
      }

   }

   public void updateBookmark(PT_STACKABLE bookmark) {
      PT_STACKABLE oldBookmark = (PT_STACKABLE)this.bookmarkMap.get(bookmark.index);
      if (oldBookmark == null) {
         this.bookmarkMap.put(bookmark.index, bookmark);
      } else {
         oldBookmark.count = oldBookmark.count + bookmark.count;
      }

   }

   public int getMaxcount() {
      return this.maxcount;
   }

   public Map<Integer, PT_STACKABLE> getBookmarkMap() {
      return this.bookmarkMap;
   }

   public void setMaxcount(int maxcount) {
      this.maxcount = maxcount;
   }

   public void setBookmarkMap(Map<Integer, PT_STACKABLE> bookmarkMap) {
      this.bookmarkMap = bookmarkMap;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof BookmarkBox)) {
         return false;
      } else {
         BookmarkBox other = (BookmarkBox)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getMaxcount() != other.getMaxcount()) {
            return false;
         } else {
            Object this$bookmarkMap = this.getBookmarkMap();
            Object other$bookmarkMap = other.getBookmarkMap();
            if (this$bookmarkMap == null) {
               if (other$bookmarkMap != null) {
                  return false;
               }
            } else if (!this$bookmarkMap.equals(other$bookmarkMap)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof BookmarkBox;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getMaxcount();
      Object $bookmarkMap = this.getBookmarkMap();
      result = result * 59 + ($bookmarkMap == null ? 43 : $bookmarkMap.hashCode());
      return result;
   }

   public String toString() {
      return "BookmarkBox(maxcount=" + this.getMaxcount() + ", bookmarkMap=" + this.getBookmarkMap() + ")";
   }
}
