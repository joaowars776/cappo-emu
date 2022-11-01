/*  1:   */ package cappo.game.roomengine.roomlisting;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.engine.threadpools.RoomTask;
/*  5:   */ import cappo.game.navigator.NavigatorCategories;
/*  6:   */ import cappo.game.roomengine.RoomData;
/*  7:   */ import cappo.protocol.messages.composers.navigator.GuestRoomSearchResultComposer;
/*  8:   */ import java.util.ArrayList;
/*  9:   */ import java.util.Collections;
/* 10:   */ import java.util.List;
/* 11:   */ import java.util.Map;
/* 12:   */ import java.util.concurrent.ConcurrentHashMap;
/* 13:   */ 
/* 14:   */ public class RoomListing
/* 15:   */ {
/* 16:   */   public static enum ListingRoomState
/* 17:   */   {
/* 18:22 */     RANKED,  UNRANKED;
/* 19:   */   }
/* 20:   */   
/* 21:25 */   public static MessageWriter PopularRooms = GuestRoomSearchResultComposer.compose(-1, "1", new ArrayList(1));
/* 22:26 */   public static MessageWriter ScoreRooms = GuestRoomSearchResultComposer.compose(-1, "2", new ArrayList(1));
/* 23:   */   public static Map<Integer, MessageWriter> ByCatRooms;
/* 24:   */   public static Map<Integer, RoomListingPopular> listingByCategory;
/* 25:   */   
/* 26:   */   public static void Init()
/* 27:   */   {
/* 28:31 */     ByCatRooms = new ConcurrentHashMap();
/* 29:32 */     listingByCategory = new ConcurrentHashMap();
/* 30:33 */     for (NavigatorCategories cat : NavigatorCategories.roomCategories.values())
/* 31:   */     {
/* 32:34 */       listingByCategory.put(Integer.valueOf(cat.id), new RoomListingPopular(2 + cat.id));
/* 33:35 */       ByCatRooms.put(Integer.valueOf(cat.id), GuestRoomSearchResultComposer.compose(-1, "1", new ArrayList(1)));
/* 34:   */     }
/* 35:   */   }
/* 36:   */   
/* 37:   */   private static MessageWriter updateRoom(RoomTask room, RoomListingBase listing, String type)
/* 38:   */   {
/* 39:40 */     synchronized (listing)
/* 40:   */     {
/* 41:41 */       if (room.roomListingState[listing.listingID] != ListingRoomState.RANKED)
/* 42:   */       {
/* 43:42 */         int size = listing.rankedList.size();
/* 44:43 */         if (size > 49)
/* 45:   */         {
/* 46:44 */           RoomTask lastRoom = (RoomTask)listing.rankedList.get(49);
/* 47:45 */           if (listing.compare(lastRoom, room) != 1) {
/* 48:46 */             return null;
/* 49:   */           }
/* 50:48 */           listing.rankedList.remove(49);
/* 51:49 */           lastRoom.roomListingState[listing.listingID] = ListingRoomState.UNRANKED;
/* 52:   */         }
/* 53:51 */         listing.rankedList.add(room);
/* 54:52 */         room.roomListingState[listing.listingID] = ListingRoomState.RANKED;
/* 55:   */       }
/* 56:55 */       Collections.sort(listing.rankedList, listing);
/* 57:57 */       for (int i = listing.rankedList.size() - 1; i >= 0; i--)
/* 58:   */       {
/* 59:58 */         RoomTask last = (RoomTask)listing.rankedList.get(i);
/* 60:59 */         if ((last.userCount < 1) || (last.roomData.room != last)) {
/* 61:61 */           listing.rankedList.remove(i);
/* 62:   */         }
/* 63:   */       }
/* 64:68 */       return GuestRoomSearchResultComposer.compose2(-1, type, listing.rankedList);
/* 65:   */     }
/* 66:   */   }
/* 67:   */   
/* 68:   */   public static void updatePopularRooms(RoomTask room)
/* 69:   */   {
/* 70:74 */     MessageWriter tmp = updateRoom(room, RoomListingPopular.mainInstance, "1");
/* 71:75 */     if (tmp != null) {
/* 72:77 */       PopularRooms = tmp;
/* 73:   */     }
/* 74:81 */     RoomListingPopular listing = (RoomListingPopular)listingByCategory.get(Integer.valueOf(room.roomData.category));
/* 75:82 */     if (listing != null)
/* 76:   */     {
/* 77:83 */       tmp = updateRoom(room, listing, "1");
/* 78:84 */       if (tmp != null) {
/* 79:86 */         ByCatRooms.put(Integer.valueOf(room.roomData.category), tmp);
/* 80:   */       }
/* 81:   */     }
/* 82:   */   }
/* 83:   */   
/* 84:   */   public static void updateMostScoreRooms(RoomTask room)
/* 85:   */   {
/* 86:92 */     MessageWriter tmp = updateRoom(room, RoomListingScore.mainInstance, "2");
/* 87:93 */     if (tmp != null) {
/* 88:95 */       ScoreRooms = tmp;
/* 89:   */     }
/* 90:   */   }
/* 91:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.roomlisting.RoomListing
 * JD-Core Version:    0.7.0.1
 */