//package WWBS.wwbs;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//public class AuctionSystem implements Serializable{
//
//	public static Map<String, List> auctions = new HashMap();
//
//	public String itemName;
//	public String playerName;
//	public int price;
//	public boolean buy;
//	public boolean sell;
//
//	public AuctionSystem(String item, String player, int price)
//	{
//
//	}
//
//	/**adding an item to the auction so it can be bought by other players.
//	 * @arguments 
//	 * String item: the name if the item
//	 * String player, the name of the player selling
//	 * int price, the price of the item per piece*/
//	public void addAuction(String item, String player, int price)
//	{
//		List auctionsByType;
//		if(auctions.containsKey(itemName)){
//			auctionsByType = auctions.get("itemName");
//			auctionsByType.add(new AuctionSystem(item,player,price));
//		}else{
//			auctionsByType = Arrays.asList(new AuctionSystem(item,player, price));
//		}
//		auctions.put(itemName, auctionsByType);
//	}
//
//	public void searchAuction()
//	{
//		Set<String> itemNames = auctions.keySet();
//		List results = new ArrayList();
//		for(String itemCat: itemNames){
//			if(itemCat.toLowerCase().contains(searchText)){
//				results.addAll(auctions.get(itemCat));
//			}
//		}
//		if(itemCat.toLowerCase().contains(searchText.toLowerCase())){
//			results.addAll(auctions.get(itemCat));
//		}
//	}
//
//	// to search
//
//}
//
